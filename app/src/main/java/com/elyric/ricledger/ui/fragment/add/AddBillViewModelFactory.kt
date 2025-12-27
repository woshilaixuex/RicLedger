package com.elyric.ricledger.ui.fragment.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elyric.ricledger.data.repository.BillStoreRepository

class AddBillViewModelFactory(
    private val repository: BillStoreRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddBillViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddBillViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
