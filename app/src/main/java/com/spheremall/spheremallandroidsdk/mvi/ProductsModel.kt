package com.spheremall.spheremallandroidsdk.mvi

import com.spheremall.core.entities.products.Product
import com.spheremall.spheremallandroidsdk.common.AbstractModel
import com.spheremall.spheremallandroidsdk.common.Reducer
import com.spheremall.spheremallandroidsdk.common.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ProductsModel(defaultState: ProductsState) : AbstractModel<ProductsState, ProductsActions>(defaultState), ProductsContract.Model {

    private val productsInteractor = ProductsInteractor()
    private lateinit var subscriptions: CompositeDisposable

    override fun init() {
        subscriptions = CompositeDisposable()
    }

    override fun release() {
        if (!subscriptions.isDisposed) subscriptions.dispose()
    }

    override fun reducer(): Reducer<ProductsState, ProductsActions> = ProductsReducer()

    override fun loadProducts() {
        action(ProductsActions.Loading(true))

        subscriptions.add(
                productsInteractor.loadProducts()
                        .doOnNext { action(ProductsActions.Loading(false)) }
                        .doOnError { action(ProductsActions.Error(it)) }
                        .map { mapToViewModels(it) }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ action(ProductsActions.ProductsLoaded(it)) }))
    }

    private fun mapToViewModels(products: List<Product>): ArrayList<ViewModel> {
        val productsViewModels = arrayListOf<ViewModel>()
        productsViewModels.addAll(products.map { ProductViewModel(it) })
        return productsViewModels
    }
}