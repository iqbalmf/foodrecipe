package net.iqbalfauzan.foodrecipe.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import net.iqbalfauzan.foodrecipe.model.Categories
import net.iqbalfauzan.foodrecipe.model.Category

/**
 * Created by Iqbalmf on 2019-08-17
 */

@Dao
interface CategoriesDao {
    @Insert
    suspend fun insertAllCategory(categories: List<Category>)

    @Query("SELECT * FROM categories_db")
    suspend fun getAllCategory() : List<Category>

    @Query("SELECT * FROM categories_db WHERE uuid = :categoryId")
    suspend fun getCategory(categoryId: Int) : Category

    @Query("DELETE FROM categories_db")
    suspend fun deleteAllCategory()
}