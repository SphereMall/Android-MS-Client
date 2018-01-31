package com.spheremall.spheremallandroidsdk.mvi

import com.spheremall.core.entities.products.Product
import com.spheremall.spheremallandroidsdk.common.BaseViewHoldersFactory
import com.spheremall.spheremallandroidsdk.common.ViewModel
import com.spheremall.spheremallandroidsdk.view.ProductsViewHolderProvider

class ProductViewModel(val product: Product) : ViewModel() {

    override fun type(viewHoldersFactory: BaseViewHoldersFactory): Int {
        return (viewHoldersFactory as ProductsViewHolderProvider).type(product = product)
    }
}