package com.spheremall.spheremallandroidsdk.mvi

import com.spheremall.spheremallandroidsdk.common.Contract
import com.spheremall.spheremallandroidsdk.common.ViewModel

data class ProductsState(
        val products: ArrayList<ViewModel>,
        val error: Throwable?,
        val loading: Boolean,
        val stateType: StateType
) : Contract.State