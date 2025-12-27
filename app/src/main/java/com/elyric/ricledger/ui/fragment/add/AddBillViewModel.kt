package com.elyric.ricledger.ui.fragment.add

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
            return
        }
        viewModelScope.launch {
            billStoreRepository.addBill(currentBill)
        }
    }
    fun updateTitle(title: kotlin.String) {
        _bill.value = _bill.value?.copy(title = title)
    }
}

