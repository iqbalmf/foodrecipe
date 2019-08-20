package net.iqbalfauzan.foodrecipe.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Iqbalmf on 2019-08-11
 */
@Entity(tableName = "categories_db")
data class Category(
    @ColumnInfo(name = "category_id")
    @SerializedName("idCategory")
    val idCategory: String?,

    @ColumnInfo(name = "category_name")
    @SerializedName("strCategory")
    val nameCategory: String?,

    @ColumnInfo(name = "category_url")
    @SerializedName("strCategoryThumb")
    val imageCategory: String?,

    @ColumnInfo(name = "category_desc")
    @SerializedName("strCategoryDescription")
    val descCategory: String?
){
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}