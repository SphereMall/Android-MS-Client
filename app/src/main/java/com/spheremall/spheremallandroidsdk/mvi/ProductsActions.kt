package com.spheremall.spheremallandroidsdk.mvi

import com.spheremall.spheremallandroidsdk.common.Contract
import com.spheremall.spheremallandroidsdk.common.ViewModel

sealed class ProductsActions : Contract.Action {
    class Loading(val loading: Boolean) : ProductsActions()
    class ProductsLoaded(val products: ArrayList<ViewModel>) : ProductsActions()
    class Error(val throwable: Throwable) : ProductsActions()
}