package net.iqbalfauzan.foodrecipe.view.home

import android.app.Application
import android.widget.Toast
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
import net.iqbalfauzan.foodrecipe.model.Meals
import net.iqbalfauzan.foodrecipe.utils.SharedPreferencesHelper

/**
 * Created by Iqbalmf on 2019-08-11
 */
class HomeViewModel(application: Application) : BaseViewModel(application) {

    private var sharedPreferencesHelper = SharedPreferencesHelper(getApplication())
    private var refreshedTime =  5 * 1000 * 1000 *1000L

    private val categoryService = FoodRecipeApiService()
    private val disposeable = CompositeDisposable()

    val categories = MutableLiveData<List<Category>>()
    val latestMeal = MutableLiveData<List<Meals.Meal>>()
    val shouldShowError = MutableLiveData<Boolean>()
    val shouldShowLoading = MutableLiveData<Boolean>()
    val shouldOpenCategoryList = MutableLiveData<String>()
    val shouldShowMessage = MutableLiveData<String>()
    val shouldOpenMealDetail = MutableLiveData<String>()

    fun onClickCategory(category: Category){
        shouldOpenCategoryList.value = category.nameCategory
    }

    fun onClickLatestMeal(latestMeals: Meals.Meal){
        shouldOpenMealDetail.value = latestMeals.mealName
    }

    override fun onCleared() {
        super.onCleared()
        disposeable.clear()
    }

    fun refresh() {
//        val updateTime = sharedPreferencesHelper.getUpdateTime()
//        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshedTime){
//            fetchFromDatabase()
//        }else{
//            fetchFromRemote()
//        }
        fetchFromRemote()
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
        disposeable.addAll(
            categoryService.getCategories()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Categories>() {
                    override fun onSuccess(t: Categories) {
                        categoryRetrived(t.categories)
                    }

                    override fun onError(e: Throwable) {
                        shouldShowError.value = true
                        shouldShowLoading.value = false
                    }

                }),
            categoryService.getLatestMeal()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Meals>(){
                    override fun onSuccess(t: Meals) {
                        shouldShowMessage.value = t.toString()
                        latestFoodRetrieved(t.meals)
                    }

                    override fun onError(e: Throwable) {
                        shouldShowError.value = true
                        shouldShowLoading.value = false
                    }
                })
        )
    }

    private fun latestFoodRetrieved(latestMeals: List<Meals.Meal>){
        latestMeal.value = latestMeals
        shouldShowLoading.value = false
        shouldShowError.value = false
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
