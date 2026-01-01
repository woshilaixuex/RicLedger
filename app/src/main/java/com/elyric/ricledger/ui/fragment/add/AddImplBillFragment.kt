package com.elyric.ricledger.ui.fragment.add

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.elyric.ricledger.R
import com.elyric.ricledger.data.repository.BillStoreRepository
import com.elyric.ricledger.databinding.FragmentAddImplBillBinding
import com.elyric.ricledger.ui.MainActivity
import com.elyric.ricledger.ui.view.custom.AppToolBarOwner
import com.elyric.ricledger.ui.view.custom.AppToolBarState

/**
 * @exception: 添加账单的表单
 */
class AddImplBillFragment : Fragment(), AppToolBarOwner{
    lateinit var addBillViewModel: AddBillViewModel
    private var _binding: FragmentAddImplBillBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appContext = requireContext().applicationContext
        val repository = BillStoreRepository.getInstance(appContext)
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
        // ViewBinding
        _binding = FragmentAddImplBillBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onResume() {
        super.onResume()
        val dispatcher = (requireActivity() as MainActivity).getToolbarDispatcher()
        dispatcher.publish(provideToolBarState())
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingEvent()
    }
    fun bindingEvent(){
        // 添加监听事件
        binding.etTitle.doAfterTextChanged {
            addBillViewModel.updateTitle(it.toString())
        }
        binding.etMoney.doAfterTextChanged {
            addBillViewModel.updateMoney(it.toString())
        }
        binding.btnSave.setOnClickListener{
            Log.i("ADD_IMPL","添加账单")
            addBillViewModel.commitBill()
        }
        // 添加ViewModel监听者
        addBillViewModel.bill.observe(viewLifecycleOwner) { bill ->
            if (binding.etTitle.text.toString() != bill.title) {
                binding.etTitle.setText(bill.title)
                binding.etTitle.setSelection(bill.title.length)
            }
        }
    }

    override fun provideToolBarState(): AppToolBarState {
        return AppToolBarState.NormalFragment(
            fragmentId = R.id.addImplBillFragment,
            title = "添加今日账单页面"
        )
    }
}