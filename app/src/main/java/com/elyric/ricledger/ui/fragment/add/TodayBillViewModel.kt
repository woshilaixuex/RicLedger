package com.elyric.ricledger.ui.fragment.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elyric.ricledger.data.model.Bill

class TodayBillViewModel: ViewModel() {
    val billData = MutableLiveData<Bill>()
}