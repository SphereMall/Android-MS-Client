package com.spheremall.spheremallandroidsdk.view

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Toast
import com.spheremall.core.SMClient
import com.spheremall.core.entities.products.Product
import com.spheremall.core.filters.elasticsearch.ESSearchFilter
import com.spheremall.core.filters.elasticsearch.compound.BoolFilter
import com.spheremall.core.filters.elasticsearch.criterions.SortFilter
import com.spheremall.core.filters.elasticsearch.criterions.TermsFilterCriteria
import com.spheremall.core.filters.elasticsearch.facets.ESAttributesFilterCriteria
import com.spheremall.core.filters.elasticsearch.facets.ESCatalogFilterImpl
import com.spheremall.core.filters.elasticsearch.facets.configs.*
import com.spheremall.core.filters.elasticsearch.terms.TermsFilter
import com.spheremall.spheremallandroidsdk.R
import com.spheremall.spheremallandroidsdk.common.GridAdapter
import com.spheremall.spheremallandroidsdk.common.ViewModel
import com.spheremall.spheremallandroidsdk.mvi.ProductViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val productsAdapter: GridAdapter = GridAdapter(ProductsViewHolderProvider())
    private val sphereMallClient: SMClient = SMClient.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setUpRecyclerView()
//        displayProducts()
        Thread(Runnable {

            val isMain = TermsFilterCriteria("isMain", "1")
            val isMainFilter = TermsFilter(isMain)

            val channel = TermsFilterCriteria("channelIds", "5")
            val channelFilter = TermsFilter(channel)

            val boolFilter = BoolFilter()
            boolFilter.must(isMainFilter, channelFilter)

            val elasticSearchFilter = ESSearchFilter()
            elasticSearchFilter.index("sm-products")
            elasticSearchFilter.source("scope")
            elasticSearchFilter.query(boolFilter)

            val entities = sphereMallClient.elasticSearch()
                    .filters(elasticSearchFilter.asFilter())
                    .limit(50)
                    .fetch()
            println("size" + entities.data().size.toString())
        }).start()
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
            return@fromCallable sphereMallClient.products().detail().data()
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
