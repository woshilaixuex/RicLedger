package com.elyric.ricledger.ui.fragment.add

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elyric.ricledger.common.TimeHelper
import com.elyric.ricledger.data.repository.BillStoreRepository
import com.elyric.ricledger.domain.model.Bill
import kotlinx.coroutines.launch
import java.lang.String

class AddBillViewModel(private val billStoreRepository: BillStoreRepository): ViewModel()  {
    private val _bill = MutableLiveData<Bill>()
    val bill: LiveData<Bill> = _bill
    init {
        initBill()
    }
    private fun initBill() {
        val now = System.currentTimeMillis()
        val date = TimeHelper.getTodayStartMillis()
        _bill.value = Bill(
            id = 0,
            date = date,
            time = now,
            title = "",
            money = 0.0,
            info = null,
            tag = null
        )
    }

    fun commitBill() {
        val currentBill = _bill.value ?: return
        if (currentBill.title.isBlank() || currentBill.money <= 0) {
            Log.w("AddBillViewModel","[method:commitBill]-标题或金额不能为空:title:${currentBill.title} money:${currentBill.money}")
            return
        }
        val billWithId = currentBill.copy(id = currentBill.time)
        _bill.value = billWithId
        viewModelScope.launch {
            billStoreRepository.addBill(currentBill)
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
}

