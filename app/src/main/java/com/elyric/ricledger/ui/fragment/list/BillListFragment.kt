package com.elyric.ricledger.ui.fragment.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.elyric.ricledger.R
import com.elyric.ricledger.ui.MainActivity
import com.elyric.ricledger.ui.view.custom.AppToolBarOwner
import com.elyric.ricledger.ui.view.custom.AppToolBarState



class BillListFragment : Fragment() , AppToolBarOwner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bill_total, container, false)
    }

    override fun onResume() {
        super.onResume()
        val dispatcher = (requireActivity() as MainActivity).getToolbarDispatcher()
        dispatcher.publish(provideToolBarState())
    }

    override fun provideToolBarState(): AppToolBarState {
        return AppToolBarState.TopFragment(
            R.id.billListFragment,
            "账单展示页面"
        )
    }

}