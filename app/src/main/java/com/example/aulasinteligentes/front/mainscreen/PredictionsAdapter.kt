package com.example.aulasinteligentes.front.mainscreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.evrencoskun.tableview.adapter.AbstractTableAdapter
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder
import com.example.aulasinteligentes.R
import com.example.aulasinteligentes.entities.PredictionTemp

class PredictionsAdapter(context: Context): AbstractTableAdapter<String, String, String>() {

    override fun getColumnHeaderItemViewType(position: Int): Int {
        // Define your column header view type here
        return 0
    }

    override fun getRowHeaderItemViewType(position: Int): Int {
        // Define your row header view type here
        return 0
    }

    override fun getCellItemViewType(position: Int): Int {
        // Define your cell view type here
        return 0
    }

    override fun onCreateCellViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder {
        TODO("Not yet implemented")
    }

    override fun onCreateColumnHeaderViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AbstractViewHolder {
        TODO("Not yet implemented")
    }

    override fun onCreateRowHeaderViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder {
        TODO("Not yet implemented")
    }

    override fun onCreateCornerView(parent: ViewGroup): View {
        TODO("Not yet implemented")
    }

    override fun onBindRowHeaderViewHolder(
        holder: AbstractViewHolder,
        rowHeaderItemModel: String?,
        rowPosition: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun onBindColumnHeaderViewHolder(
        holder: AbstractViewHolder,
        columnHeaderItemModel: String?,
        columnPosition: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun onBindCellViewHolder(
        holder: AbstractViewHolder,
        cellItemModel: String?,
        columnPosition: Int,
        rowPosition: Int
    ) {
        TODO("Not yet implemented")
    }

}