package net.iqbalfauzan.foodrecipe.rest

import io.reactivex.Single
import net.iqbalfauzan.foodrecipe.api.ApiService
import net.iqbalfauzan.foodrecipe.model.Categories
import net.iqbalfauzan.foodrecipe.model.Meals

/**
 * Created by Iqbalmf on 2019-08-15
 */
class FoodRecipeApiService {
    private var apiService: ApiService = ApiService()

    fun getCategories(): Single<Categories> {
        return apiService.api.getCategory()
    }

    fun getMealCategory(categoryName: String): Single<Meals> {
        return apiService.api.getMealCategory(categoryName)
    }

    fun getLatestMeal(): Single<Meals> {
        return apiService.api.getLatestMeal()
    }

    fun getDetailsMeal(nameFood: String): Single<Meals>{
        return apiService.api.getDetailsMeal(nameFood = nameFood)
    }
}