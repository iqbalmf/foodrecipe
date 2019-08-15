package net.iqbalfauzan.foodrecipe.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Iqbalmf on 2019-08-15
 */
data class Categories(
    @SerializedName("categories")
    val categories: List<Category>?
)