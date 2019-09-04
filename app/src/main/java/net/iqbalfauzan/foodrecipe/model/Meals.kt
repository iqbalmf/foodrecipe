package net.iqbalfauzan.foodrecipe.model

import com.google.gson.annotations.SerializedName

data class Meals(
    @SerializedName("meals")
    var meals: List<Meal>
) {
    data class Meal(
        @SerializedName("strMeal")
        val mealName: String?,
        @SerializedName("strMealThumb")
        val mealImage: String?,
        @SerializedName("idMeal")
        val mealId: String?,
        @SerializedName("strCategory")
        val category: String?,
        @SerializedName("strInstruction")
        val instruction: String?,
        @SerializedName("strTags")
        val tags: String?,
        @SerializedName("strYoutube")
        val linkYoutube: String?,
        @SerializedName("strSource")
        val linkSource: String?
    )
}