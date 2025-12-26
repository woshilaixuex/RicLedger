package com.elyric.ricledger.ui.fragment.add

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elyric.ricledger.R
import com.elyric.ricledger.data.model.Bill

class TodayBillAdapter(
    private val data: List<Bill>,
    private val onEditClick: (Bill) -> Unit,
    private val onDeleteClick: (Bill) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class BillViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val btnEdit: Button = itemView.findViewById(R.id.btnEdit)
        val btnDelete: Button = itemView.findViewById(R.id.btnDelete)
        val gridContent: GridLayout = itemView.findViewById(R.id.gridContent)
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
    }

    override fun getItemCount(): Int = data.size
}