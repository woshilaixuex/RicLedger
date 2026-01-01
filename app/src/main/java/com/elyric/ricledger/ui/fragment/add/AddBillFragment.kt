package com.elyric.ricledger.ui.fragment.add

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.elyric.ricledger.R
import com.elyric.ricledger.data.repository.BillStoreRepository
import com.elyric.ricledger.databinding.FragmentAddBillBinding
import com.elyric.ricledger.ui.MainActivity
import com.elyric.ricledger.ui.view.custom.AppToolBarOwner
import com.elyric.ricledger.ui.view.custom.AppToolBarState


/**
 * @exception:今日的账单
 */
class AddBillFragment : Fragment(), AppToolBarOwner {
    lateinit var todayBillViewModel: TodayBillViewModel
    private var _binding: FragmentAddBillBinding? = null
    private val binding
        get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val applicationContext = requireContext().applicationContext
        val repository = BillStoreRepository.getInstance(applicationContext)
        todayBillViewModel = ViewModelProvider(
            this,
            TodayBillViewModelFactory(repository)
        )[TodayBillViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBillBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val dispatcher = (requireActivity() as MainActivity).getToolbarDispatcher()
        dispatcher.publish(provideToolBarState())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TodayBillAdapter(
            onEditClick = { bill ->
                Log.d("TodayBill", "edit: $bill")
            },
            onDeleteClick = { bill ->
                Log.d("TodayBill", "delete: $bill")
            }
        )
        binding.todayBillList.layoutManager = LinearLayoutManager(requireContext())
        binding.todayBillList.adapter = adapter
        todayBillViewModel.billList.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
        Log.i("AddBillFragment",todayBillViewModel.billList.value?.toString()?:"")
        initLister(view)
    }

    private fun initLister(view: View) {
        binding.btnGoToAddImpl.setOnClickListener {
            findNavController().navigate(R.id.action_addBillFragment_to_addImplBillFragment)
        }
    }

    override fun provideToolBarState(): AppToolBarState {
        return AppToolBarState.TopFragment(
            fragmentId = R.id.addImplBillFragment,
            title = "今日账单展示页面"
        )
    }
}