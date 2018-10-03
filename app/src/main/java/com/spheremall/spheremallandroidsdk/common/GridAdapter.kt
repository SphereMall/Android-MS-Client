package com.spheremall.spheremallandroidsdk.common

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

@Suppress("UNCHECKED_CAST")
open class GridAdapter(
        protected val viewHoldersFactory: BaseViewHoldersFactory
) : RecyclerView.Adapter<BaseViewHolder<ViewModel>>() {

    protected val items = arrayListOf<ViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ViewModel> {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return viewHoldersFactory.holder(viewType, view) as BaseViewHolder<ViewModel>
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewModel>, position: Int) {
        holder.bind(item = items[position])
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = items[position].type(viewHoldersFactory)

    fun updateItems(newItems: ArrayList<ViewModel>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}