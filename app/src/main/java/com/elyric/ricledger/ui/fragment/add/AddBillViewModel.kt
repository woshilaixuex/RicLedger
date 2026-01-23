package com.elyric.ricledger.ui.fragment.add

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elyric.ricledger.common.StringUtil
import com.elyric.ricledger.data.repository.BillStoreRepository
import com.elyric.ricledger.domain.model.Bill
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddBillViewModel(private val billStoreRepository: BillStoreRepository): ViewModel()  {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    private val _bill = MutableLiveData<Bill>()
    val bill: LiveData<Bill> = _bill
    init {
        initBill()
    }
    private fun initBill() {
        val now = Date()
        val dateStr = dateFormat.format(now)
        val timeStr = timeFormat.format(now)
        _bill.value = Bill(
            id = 0,
            date = dateStr,
            time = timeStr,
            title = "",
            money = 0.0,
            info = null,
            tag = null
        )
    }

    fun commitBill(onSuccess: (() -> Unit)? = null) {
        val currentBill = _bill.value ?: return
        if (currentBill.title.isBlank() || currentBill.money <= 0) {
            Log.w("AddBillViewModel","[method:commitBill]-标题或金额不能为空:title:${currentBill.title} money:${currentBill.money}")
            return
        }
        viewModelScope.launch {
            billStoreRepository.addBill(currentBill)
            // 提交成功后执行回调
            onSuccess?.invoke()
        }
    }
    fun updateTitle(title: kotlin.String) {
        val current = _bill.value ?: return
        if (current.title == title) return
        _bill.value = current.copy(title = title)
    }
    fun updateMoney(money: kotlin.String){
        val current = _bill.value ?: return
        val moneyDouble = money.toDoubleOrNull() ?: 0.0
        if (current.money == moneyDouble) return
        _bill.value = current.copy(money = moneyDouble)
    }
    fun updateDate(dateStr:String) {
        val current = _bill.value ?: return
        if (current.date == dateStr) return
        _bill.value = current.copy(date = dateStr)
    }
    fun updateTime(timeStr:String) {
        val current = _bill.value ?: return
        if (current.time == timeStr) return
        _bill.value = current.copy(time = timeStr)
    }
    fun updateInfo(info: String){
        val current = _bill.value ?: return
        if (current.info == info) return
        _bill.value = current.copy(info = info)
    }
    fun updateTag(tag: String){
        val current = _bill.value ?: return
        if (current.tag == tag) return
        _bill.value = current.copy(tag = tag)
    }
}

