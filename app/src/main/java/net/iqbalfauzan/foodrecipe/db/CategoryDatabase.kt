package net.iqbalfauzan.foodrecipe.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.iqbalfauzan.foodrecipe.model.Categories
import net.iqbalfauzan.foodrecipe.model.Category

/**
 * Created by Iqbalmf on 2019-08-17
 */
@Database(entities = [Category::class], exportSchema = false, version = 1)
abstract class CategoryDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoriesDao

    companion object {
        @Volatile
        private var instance: CategoryDatabase? = null
        private var LOCK = Any()
        var DB_NAME: String = "DB_CATEGORY"

        operator fun invoke(context: Context) = instance ?: synchronized(
            LOCK
        ) {
            instance
                ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CategoryDatabase::class.java,
            DB_NAME
        ).build()
    }
}