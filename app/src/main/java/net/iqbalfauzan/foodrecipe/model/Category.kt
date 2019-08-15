package net.iqbalfauzan.foodrecipe.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Iqbalmf on 2019-08-11
 */
data class Category(
    @SerializedName("idCategory")
    val idCategory: String?,

    @SerializedName("strCategory")
    val nameCategory: String?,

    @SerializedName("strCategoryThumb")
    val imageCategory: String?,

    @SerializedName("strCategoryDescription")
    val descCategory: String?
)