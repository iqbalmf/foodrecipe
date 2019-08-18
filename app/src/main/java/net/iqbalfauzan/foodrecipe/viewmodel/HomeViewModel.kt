package net.iqbalfauzan.foodrecipe.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import net.iqbalfauzan.foodrecipe.base.BaseViewModel
import net.iqbalfauzan.foodrecipe.model.Categories
import net.iqbalfauzan.foodrecipe.model.CategoriesApiService
import net.iqbalfauzan.foodrecipe.model.CategoryDatabase

/**
 * Created by Iqbalmf on 2019-08-11
 */
class HomeViewModel(application: Application) : BaseViewModel(application) {

    private val categoryService = CategoriesApiService()
    private val disposeable = CompositeDisposable()

    val categories = MutableLiveData<Categories>()
    val shouldShowError = MutableLiveData<Boolean>()
    val shouldShowLoading = MutableLiveData<Boolean>()

    override fun onCleared() {
        super.onCleared()
        disposeable.clear()
    }

    fun refresh() {
        fetchFromRemote()
    }

    private fun fetchFromRemote() {
        shouldShowLoading.value = true
        disposeable.add(
            categoryService.getCategories()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Categories>() {
                    override fun onSuccess(t: Categories) {
                        storeToLocally(t)
                    }

                    override fun onError(e: Throwable) {
                        shouldShowError.value = true
                        shouldShowLoading.value = false
                    }

                })
        )
    }

    private fun categoryRetrived(category: Categories) {
        categories.value = category
        shouldShowLoading.value = false
        shouldShowError.value = false
    }

    private fun storeToLocally(list: Categories) {
        launch {
            val dao = CategoryDatabase(getApplication()).categoryDao()
            dao.deleteAllCategory()
            val result = CategoryDatabase(getApplication()).categoryDao()
            result.insertAllCategory(list)

            categoryRetrived(list)
        }
    }
}
