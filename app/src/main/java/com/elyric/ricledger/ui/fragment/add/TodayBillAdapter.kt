package com.elyric.ricledger.ui.fragment.add

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elyric.ricledger.R
import com.elyric.ricledger.databinding.ItemTodayBillBinding
import com.elyric.ricledger.domain.model.Bill
import com.elyric.ricledger.ui.fragment.add.swipe.SwipeItemLayout

class TodayBillAdapter(
    private val onEditClick: (Bill) -> Unit,
    private val onDeleteClick: (Bill) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val data = mutableListOf<Bill>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<Bill>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }
    inner class BillViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTodayBillBinding.bind(itemView)

        val swipeLayout = itemView as SwipeItemLayout
        val tvTitle = binding.tvTitle
        val btnEdit = binding.btnEdit
        val btnDelete = binding.btnDelete
        val tvTime = binding.tvTime
        val tvMoney = binding.tvMoney
        val tvTag = binding.tvTag
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_today_bill, parent, false)
        return BillViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val vh = holder as BillViewHolder
        val bill = data[position]

        holder.tvTitle.text = bill.title

        holder.btnEdit.setOnClickListener {
            onEditClick(bill)
            holder.swipeLayout.close()
        }

        holder.btnDelete.setOnClickListener {
            onDeleteClick(bill)
            holder.swipeLayout.close()
        }

        holder.swipeLayout.close()
        holder.tvTime.text = bill.time
        holder.tvMoney.text = "¥${bill.money}"
        holder.tvTag.text = bill.tag ?: "无标签"
    }

    override fun getItemCount(): Int = data.size
}