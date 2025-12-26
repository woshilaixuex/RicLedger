package com.elyric.ricledger.ui.fragment.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elyric.ricledger.data.local.entity.BillStoreEntity
import com.elyric.ricledger.data.model.Bill
import com.elyric.ricledger.data.repository.BillStoreRepository
import kotlinx.coroutines.launch


class TodayBillViewModel(private val billStoreRepository: BillStoreRepository): ViewModel() {
    // MutableLiveData内部给仓储层操作.LiveData给Ui层操作
    private val _billList = MutableLiveData<List<Bill>>()
    val billList: LiveData<List<Bill>> = _billList
    fun loadTodayBills() {
        viewModelScope.launch {
            val list = billStoreRepository.getBillListWithTodayTime()
            _billList.postValue(list)
        }
    }
}