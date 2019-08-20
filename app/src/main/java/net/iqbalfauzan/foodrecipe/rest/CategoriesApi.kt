package net.iqbalfauzan.foodrecipe.rest

import io.reactivex.Single
import net.iqbalfauzan.foodrecipe.model.Categories
import retrofit2.http.GET

/**
 * Created by Iqbalmf on 2019-08-15
 */
interface CategoriesApi {
    @GET("api/json/v1/1/categories.php")
    fun getCategory(): Single<Categories>
}