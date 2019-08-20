package net.iqbalfauzan.foodrecipe.view.categorymenu

import android.app.Activity
import android.content.Intent
import net.iqbalfauzan.foodrecipe.utils.start

/**
 * Project foodrecipe
 *
 * Created by IqbalMF on 8/20/2019
 */
class CategoryMenuWireframe {
    companion object{
        const val CATEGORY_NAME = "category_name"
        fun startCategoryMenu(source: Activity, category: String){
            source.start(CategoryMenuActivity::class.java){
                putExtra(CATEGORY_NAME, category)
            }
        }
    }

    private fun openDetailFood(source: Activity){

    }
}