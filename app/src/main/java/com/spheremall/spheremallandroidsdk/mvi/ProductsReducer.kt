package com.spheremall.spheremallandroidsdk.mvi

import com.spheremall.spheremallandroidsdk.common.Reducer

class ProductsReducer : Reducer<ProductsState, ProductsActions> {

    override fun reduce(state: ProductsState, action: ProductsActions): ProductsState {
        return when (action) {
            is ProductsActions.Loading -> state.copy(loading = action.loading, stateType = StateType.LOADING)
            is ProductsActions.ProductsLoaded -> state.copy(products = action.products, stateType = StateType.LOADED)
            is ProductsActions.Error -> state.copy(error = action.throwable, stateType = StateType.ERROR)
        }
    }
}