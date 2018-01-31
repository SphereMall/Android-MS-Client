package com.spheremall.spheremallandroidsdk.mvi

import com.spheremall.spheremallandroidsdk.common.Contract
import com.spheremall.spheremallandroidsdk.common.ViewModel

interface ProductsContract {

    interface View : Contract.View {
        fun showLoading(show: Boolean)
        fun showProducts(products: ArrayList<ViewModel>)
        fun showError(message: String)
    }

    interface Presenter : Contract.Presenter<View>

    interface Model : Contract.Model<ProductsState> {
        fun loadProducts()
    }
}