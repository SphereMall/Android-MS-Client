package com.spheremall.spheremallandroidsdk.mvi

import com.spheremall.core.SMClient
import com.spheremall.core.entities.products.Product
import io.reactivex.Observable

class ProductsInteractor {

    private val sphereMallClient: SMClient = SMClient.get()

    fun loadProducts(): Observable<List<Product>> {
        return Observable.fromCallable { sphereMallClient.products().full().data() }
    }
}