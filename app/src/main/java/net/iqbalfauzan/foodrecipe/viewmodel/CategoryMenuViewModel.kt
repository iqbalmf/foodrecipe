package net.iqbalfauzan.foodrecipe.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import net.iqbalfauzan.foodrecipe.base.BaseViewModel
import net.iqbalfauzan.foodrecipe.model.Categories
import net.iqbalfauzan.foodrecipe.model.Category
import net.iqbalfauzan.foodrecipe.model.Meals
import net.iqbalfauzan.foodrecipe.rest.FoodRecipeApiService

/**
 * Project foodrecipe
 *
 * Created by IqbalMF on 8/20/2019
 */
class CategoryMenuViewModel(application: Application) : BaseViewModel(application = application){
    private val mealCategoryService = FoodRecipeApiService()
    private val disposable = CompositeDisposable()

    val mealCategory = MutableLiveData<List<Meals.Meal>>()
    val shouldShowError = MutableLiveData<Boolean>()
    val shouldShowLoading = MutableLiveData<Boolean>()
    val categoryName = MutableLiveData<String>()

    fun fetch(category: String?){
        categoryName.value = category
        category?.let { GetCategoryMenu(it) }
    }

    private fun GetCategoryMenu(category: String){
        shouldShowLoading.value = true
        disposable.add(
            mealCategoryService.getMealCategory(category)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Meals>(){
                    override fun onSuccess(t: Meals) {
                        mealCategoryRetrieved(t.meals)
                    }

                    override fun onError(e: Throwable) {
                        shouldShowError.value = true
                        shouldShowLoading.value = false
                    }
                })
        )
    }

    private fun mealCategoryRetrieved(mealCategory: List<Meals.Meal>){
        this.mealCategory.value = mealCategory
        shouldShowLoading.value = false
        shouldShowError.value = false
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}