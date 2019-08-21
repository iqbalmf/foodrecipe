package net.iqbalfauzan.foodrecipe.rest

import io.reactivex.Single
import net.iqbalfauzan.foodrecipe.model.Categories
import net.iqbalfauzan.foodrecipe.model.Meals
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Iqbalmf on 2019-08-15
 */
interface FoodRecipeApi {
    @GET("api/json/v1/1/categories.php")
    fun getCategory(): Single<Categories>

    @GET("api/json/v1/1/filter.php")
    fun getMealCategory(@Query("c") category: String): Single<Meals>
}