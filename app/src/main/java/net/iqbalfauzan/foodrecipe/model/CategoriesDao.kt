package net.iqbalfauzan.foodrecipe.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * Created by Iqbalmf on 2019-08-17
 */

@Dao
interface CategoriesDao {
    @Insert
    suspend fun insertAllCategory(vararg categories: Categories)

    @Query("SELECT * FROM category")
    suspend fun getAllCategory() : List<Category>

    @Query("SELECT * FROM category WHERE uuid = :categoryId")
    suspend fun getCategory(categoryId: Int) : Category

    @Query("DELETE FROM category")
    suspend fun deleteAllCategory()
}