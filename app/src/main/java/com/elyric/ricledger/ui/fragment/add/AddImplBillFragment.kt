package com.elyric.ricledger.ui.fragment.add

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.elyric.ricledger.R
import com.elyric.ricledger.data.repository.BillStoreRepository
import com.elyric.ricledger.databinding.FragmentAddImplBillBinding
import com.elyric.ricledger.ui.MainActivity
import com.elyric.ricledger.ui.view.custom.AppToolBarOwner
import com.elyric.ricledger.ui.view.custom.AppToolBarState
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
        initViewInformation()
    }
    fun initViewInformation() {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val now = Date()
        addBillViewModel.updateDate(sdf.format(now))
        addBillViewModel.updateTime(timeFormat.format(now))
    }
    fun bindingEvent(){
        // 添加监听事件
        binding.etTitle.doAfterTextChanged {
            addBillViewModel.updateTitle(it.toString())
        }
        binding.etMoney.doAfterTextChanged {
            addBillViewModel.updateMoney(it.toString())
        }
        binding.etInfo.doAfterTextChanged {
            addBillViewModel.updateInfo(it.toString())
        }
        binding.etTag.doAfterTextChanged {
            addBillViewModel.updateTag(it.toString())
        }
        // 提交订单
        binding.btnSave.setOnClickListener{
            Log.i("ADD_IMPL","添加账单")
            addBillViewModel.commitBill {
                // 显示成功提示
                Snackbar.make(binding.root, "账单添加成功", Snackbar.LENGTH_SHORT).show()
                // 提交成功后返回上一页
                findNavController().navigateUp()
            }
        }
        binding.tvDate.setOnClickListener {
            // data 选择器
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("选择日期")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
            datePicker.show(parentFragmentManager, "DATE_PICKER")
            datePicker.addOnPositiveButtonClickListener { selection ->
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val dateStr = sdf.format(Date(selection))
                addBillViewModel.updateDate(dateStr)
            }
        }
        binding.tvTime.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setTitleText("选择时间")
                .build()
            timePicker.show(parentFragmentManager, "TIME_PICKER")
            timePicker.addOnPositiveButtonClickListener {
                val timeStr = String.format("%02d:%02d", timePicker.hour, timePicker.minute)
                addBillViewModel.updateTime(timeStr)
            }
        }
        // 添加ViewModel监听者
        addBillViewModel.bill.observe(viewLifecycleOwner) { bill ->
            if (binding.etTitle.text.toString() != bill.title) {
                binding.etTitle.setText(bill.title)
                binding.etTitle.setSelection(bill.title.length)
            }
        }
        addBillViewModel.bill.observe(viewLifecycleOwner) { bill ->
            // 标题
            if (binding.etTitle.text.toString() != bill.title) {
                binding.etTitle.setText(bill.title)
                binding.etTitle.setSelection(bill.title.length)
            }

            // 金额
            val moneyStr = if (bill.money == 0.0) "" else bill.money.toString()
            if (binding.etMoney.text.toString() != moneyStr) {
                binding.etMoney.setText(moneyStr)
            }

            // 日期
            if (binding.tvDate.text.toString() != bill.date) {
                binding.tvDate.text = bill.date
            }

            // 时间
            if (binding.tvTime.text.toString() != bill.time) {
                binding.tvTime.text = bill.time
            }

            // 备注
            if (binding.etInfo.text.toString() != (bill.info ?: "")) {
                binding.etInfo.setText(bill.info ?: "")
            }

            // 标签
            if (binding.etTag.text.toString() != (bill.tag ?: "")) {
                binding.etTag.setText(bill.tag ?: "")
            }
        }
    }

    override fun provideToolBarState(): AppToolBarState {
        return AppToolBarState.NormalFragment(
            fragmentId = R.id.addImplBillFragment,
            title = "添加账单",
            onBack = {
                findNavController().navigateUp()
            }
        )
    }
}