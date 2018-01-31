package com.spheremall.spheremallandroidsdk.mvi

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ProductsPresenter : ProductsContract.Presenter {

    private val model: ProductsContract.Model = ProductsModel(ProductsState(
            products = arrayListOf(),
            error = null,
            loading = false,
            stateType = StateType.IDLE))

    private lateinit var mainView: ProductsContract.View
    private val subscription = CompositeDisposable()

    override fun onViewCreated(view: ProductsContract.View) {
        mainView = view
        model.init()

        model.state
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::render, this::render)

        model.loadProducts()
    }

    private fun render(state: ProductsState) {
        when (state.stateType) {
            StateType.LOADING -> mainView.showLoading(state.loading)
            StateType.ERROR -> render(state.error)
            StateType.LOADED -> mainView.showProducts(state.products)
            StateType.IDLE -> {
            }
        }
    }

    private fun render(error: Throwable?) {
        if (error != null) {
            mainView.showError(error.localizedMessage)
        } else {
            mainView.showError("Unknown error")
        }
    }

    override fun onViewDestroyed() {
        if (!subscription.isDisposed) subscription.dispose()
    }
}