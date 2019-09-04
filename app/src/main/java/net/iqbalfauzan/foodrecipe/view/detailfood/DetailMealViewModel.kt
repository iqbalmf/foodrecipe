package net.iqbalfauzan.foodrecipe.view.detailfood

import android.app.Application
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import net.iqbalfauzan.foodrecipe.base.BaseViewModel
import net.iqbalfauzan.foodrecipe.model.Meals
import net.iqbalfauzan.foodrecipe.rest.FoodRecipeApiService

class DetailMealViewModel(application: Application) : BaseViewModel(application = application) {
    private val detailMealService = FoodRecipeApiService()
    private val disposable = CompositeDisposable()

    val mealsDetail = MutableLiveData<List<Meals.Meal>>()
    val shouldShowLoading = MutableLiveData<Boolean>()
    val foodName = MutableLiveData<String>()

    fun prepareDetailsFood(nameFood: String?){
        foodName.value = nameFood
        nameFood?.let {

        }
    }

    private fun GetDetailsFood(nameFood: String){
        shouldShowLoading.value = true
        disposable.add(
            detailMealService.getDetailsMeal(nameFood)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Meals>(){
                    override fun onSuccess(t: Meals) {

                    }

                    override fun onError(e: Throwable) {
                        shouldShowLoading.value = false
                    }
                })
        )
    }

    private fun detailsMealRetrieved(foodDetails: List<Meals.Meal>){
        this.mealsDetail.value = foodDetails
        shouldShowLoading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}