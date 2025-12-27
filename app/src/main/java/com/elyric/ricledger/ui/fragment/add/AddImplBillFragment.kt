package com.elyric.ricledger.ui.fragment.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.elyric.ricledger.R
import com.elyric.ricledger.data.repository.BillStoreRepository

/**
 * @exception: 添加账单的表单
 */
class AddImplBillFragment : Fragment() {
    lateinit var addBillViewModel: AddBillViewModel
    lateinit var etTitle: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = BillStoreRepository.getInstance(requireContext())
        // 初始化ViewModel
        addBillViewModel = ViewModelProvider(
            this,
            AddBillViewModelFactory(repository)
        )[AddBillViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_impl_bill, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 查找视图View
        val etTitle = view.findViewById<EditText>(R.id.etTitle)
        // 添加监听事件
        etTitle.doAfterTextChanged { text ->
            addBillViewModel.updateTitle(text.toString())
        }
        // 添加ViewModel监听者
        addBillViewModel.bill.observe(viewLifecycleOwner) { bill ->
            if (etTitle.text.toString() != bill.title) {
                etTitle.setText(bill.title)
                etTitle.setSelection(bill.title.length)
            }
        }
    }

}