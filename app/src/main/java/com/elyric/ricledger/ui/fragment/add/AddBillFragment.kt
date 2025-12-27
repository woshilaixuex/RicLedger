package com.elyric.ricledger.ui.fragment.add

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.elyric.ricledger.R

/**
 * @exception:今日的账单
 */
class AddBillFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_bill, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLister(view)
    }

    private fun initLister(view: View) {
        val goToAddImplButton = view.findViewById<Button>(R.id.goToAddImplButton)
        goToAddImplButton.setOnClickListener {
            findNavController().navigate(R.id.action_addBillFragment_to_addImplBillFragment)
        }
    }
}