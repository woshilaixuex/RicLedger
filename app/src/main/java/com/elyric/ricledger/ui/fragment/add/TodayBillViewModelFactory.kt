package com.elyric.ricledger.ui.fragment.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elyric.ricledger.data.repository.BillStoreRepository

class TodayBillViewModelFactory(
    private val repository: BillStoreRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodayBillViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TodayBillViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}