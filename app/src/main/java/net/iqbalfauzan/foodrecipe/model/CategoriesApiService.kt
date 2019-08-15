package net.iqbalfauzan.foodrecipe.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Iqbalmf on 2019-08-15
 */
class CategoriesApiService {
    private val BASE_URL = "https://www.themealdb.com/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CategoriesApi::class.java)

    fun getCategories(): Single<Categories>{
        return api.getCategory()
    }
}