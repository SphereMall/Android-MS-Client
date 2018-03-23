package com.spheremall.spheremallandroidsdk.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Toast
import com.spheremall.core.SMClient
import com.spheremall.core.entities.products.Product
import com.spheremall.spheremallandroidsdk.R
import com.spheremall.spheremallandroidsdk.common.GridAdapter
import com.spheremall.spheremallandroidsdk.common.ViewModel
import com.spheremall.spheremallandroidsdk.mvi.ProductViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val productsAdapter: GridAdapter = GridAdapter(ProductsViewHolderProvider())
    private val sphereMallClient: SMClient = SMClient.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
        displayProducts()
    }

    private fun setUpRecyclerView() {
        val layoutManager = GridLayoutManager(this, 2)
        rvProducts.layoutManager = layoutManager
        rvProducts.setHasFixedSize(true)
        rvProducts.adapter = productsAdapter
    }

    private fun displayProducts() {
        showLoading(true)
        loadProducts()
                .map { mapToViewModels(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { showLoading(false) }
                .subscribe({ setProductsToAdapter(it) }, { showError(it) })
    }

    private fun loadProducts(): Observable<List<Product>> {
        return Observable.fromCallable {
            return@fromCallable sphereMallClient.products().full().data()
        }
    }

    private fun mapToViewModels(products: List<Product>): ArrayList<ViewModel> {
        val productsViewModels = arrayListOf<ViewModel>()
        productsViewModels.addAll(products.map { ProductViewModel(it) })
        return productsViewModels
    }

    private fun showLoading(show: Boolean) {
        progress.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun setProductsToAdapter(products: ArrayList<ViewModel>) {
        productsAdapter.updateItems(products)
    }

    private fun showError(error: Throwable) {
        Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
    }
}
