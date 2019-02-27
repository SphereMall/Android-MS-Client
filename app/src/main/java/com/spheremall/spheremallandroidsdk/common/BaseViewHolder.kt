package com.spheremall.spheremallandroidsdk.common

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder<in ItemType : ViewModel>(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    abstract fun bind(item: ItemType)
}