package com.spheremall.spheremallandroidsdk.view

import android.view.View
import android.webkit.URLUtil
import com.spheremall.spheremallandroidsdk.mvi.ProductViewModel
import com.spheremall.spheremallandroidsdk.R
import com.spheremall.spheremallandroidsdk.common.BaseViewHolder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_item_layout.view.*

class ProductsViewHolder(itemView: View?) : BaseViewHolder<ProductViewModel>(itemView) {

    override fun bind(item: ProductViewModel) {

        itemView.tvName.text = item.product.title
        itemView.tvPrice.text = "$".plus(item.product.price.toString())

        if (item.product.images != null && item.product.images.size > 0) {
            if (URLUtil.isHttpUrl(item.product.images[0].path) || URLUtil.isHttpsUrl(item.product.images[0].path)) {
                Picasso.with(itemView.context).load(item.product.images[0].path).into(itemView.ivImage)
            } else {
                Picasso.with(itemView.context).load(R.drawable.ic_notfound).into(itemView.ivImage)
            }
        } else {
            Picasso.with(itemView.context).load(R.drawable.ic_notfound).into(itemView.ivImage)
        }
    }
}