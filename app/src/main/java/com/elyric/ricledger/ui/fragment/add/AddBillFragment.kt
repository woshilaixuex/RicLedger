package com.elyric.ricledger.ui.fragment.add

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.elyric.ricledger.R
import com.elyric.ricledger.databinding.FragmentAddBillBinding


/**
 * @exception:今日的账单
 */
class AddBillFragment : Fragment() {
    private var _binding: FragmentAddBillBinding? = null
    private val binding
        get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBillBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLister(view)
    }

    private fun initLister(view: View) {
        binding.btnGoToAddImpl.setOnClickListener {
            findNavController().navigate(R.id.action_addBillFragment_to_addImplBillFragment)
        }
    }
}