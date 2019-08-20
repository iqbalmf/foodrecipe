package net.iqbalfauzan.foodrecipe.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Iqbalmf on 2019-08-15
 */
data class Categories(
    @SerializedName("categories")
    val categories: List<Category>
)