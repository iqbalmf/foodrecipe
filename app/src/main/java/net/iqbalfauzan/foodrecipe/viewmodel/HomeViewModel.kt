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
import net.iqbalfauzan.foodrecipe.rest.FoodRecipeApiService
import net.iqbalfauzan.foodrecipe.db.CategoryDatabase
import net.iqbalfauzan.foodrecipe.model.Category
import net.iqbalfauzan.foodrecipe.utils.SharedPreferencesHelper

/**
 * Created by Iqbalmf on 2019-08-11
 */
class HomeViewModel(application: Application) : BaseViewModel(application) {

    private var sharedPreferencesHelper = SharedPreferencesHelper(getApplication())
    private var refreshedTime = 5 * 60 * 1000 * 1000 *1000L

    private val categoryService = FoodRecipeApiService()
    private val disposeable = CompositeDisposable()

    val categories = MutableLiveData<List<Category>>()
    val shouldShowError = MutableLiveData<Boolean>()
    val shouldShowLoading = MutableLiveData<Boolean>()
    val shouldOpenCategoryList = MutableLiveData<String>()

    fun onClickCategory(category: Category){
        shouldOpenCategoryList.value = category.nameCategory
    }

    override fun onCleared() {
        super.onCleared()
        disposeable.clear()
    }

    fun refresh() {
        val updateTime = sharedPreferencesHelper.getUpdateTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshedTime){
            fetchFromDatabase()
        }else{
            fetchFromRemote()
        }
    }

    private fun fetchFromDatabase(){
        shouldShowLoading.value = true
        launch {
            val categories = CategoryDatabase(getApplication()).categoryDao().getAllCategory()
            categoryRetrived(categories)
        }
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

    private fun categoryRetrived(category: List<Category>) {
        categories.value = category
        shouldShowLoading.value = false
        shouldShowError.value = false
    }

    private fun storeToLocally(list: Categories) {
        launch {
            val dao = CategoryDatabase(getApplication()).categoryDao()
            dao.deleteAllCategory()
            val result = CategoryDatabase(getApplication()).categoryDao()
            result.insertAllCategory(list.categories)

            categoryRetrived(list.categories)

            sharedPreferencesHelper.saveUpdateTime(System.nanoTime())
        }
    }
}
