package net.iqbalfauzan.foodrecipe.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import net.iqbalfauzan.foodrecipe.model.Categories
import net.iqbalfauzan.foodrecipe.model.CategoriesApiService
import net.iqbalfauzan.foodrecipe.model.Category

/**
 * Created by Iqbalmf on 2019-08-11
 */
class HomeViewModel : ViewModel() {

    private val categoryService = CategoriesApiService()
    private val disposeable = CompositeDisposable()

    val categories = MutableLiveData<Categories>()
    val shouldShowError = MutableLiveData<Boolean>()
    val shouldShowLoading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchFromRemote()
    }

    private fun fetchFromRemote() {
        shouldShowLoading.value = true
        disposeable.add(
            categoryService.getCategories()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Categories>(){
                    override fun onSuccess(t: Categories) {
                        categories.value = t
                        shouldShowLoading.value = false
                        shouldShowError.value = false
                    }

                    override fun onError(e: Throwable) {
                        shouldShowError.value = true
                        shouldShowLoading.value = false
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposeable.clear()
    }
}
