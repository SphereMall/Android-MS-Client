package com.spheremall.spheremallandroidsdk.view

import android.view.View
import com.spheremall.core.entities.products.Product
import com.spheremall.spheremallandroidsdk.R
import com.spheremall.spheremallandroidsdk.common.BaseViewHolder
import com.spheremall.spheremallandroidsdk.common.BaseViewHoldersFactory

class ProductsViewHolderProvider : BaseViewHoldersFactory {

    fun type(product: Product): Int = R.layout.product_item_layout

    override fun holder(type: Int, view: View): BaseViewHolder<*> {
        return when (type) {
            R.layout.product_item_layout -> ProductsViewHolder(view)
            else -> throw IllegalArgumentException("Unknown type of ViewModel")
        }
    }
}