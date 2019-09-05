package net.iqbalfauzan.foodrecipe.view.categorymenu

import android.app.Activity
import net.iqbalfauzan.foodrecipe.utils.start
import net.iqbalfauzan.foodrecipe.view.detailfood.DetailMealWireframe

/**
 * Project foodrecipe
 *
 * Created by IqbalMF on 8/20/2019
 */
class CategoryMenuWireframe {
    companion object {
        const val CATEGORY_NAME = "category_name"
        fun startCategoryMenu(
            source: Activity,
            category: String
        ) {
            source.start(target = CategoryMenuActivity::class.java, requestCode = null) {
                putExtra(CATEGORY_NAME, category)
            }
        }
    }

    fun openDetailFood(source: Activity, foodName: String) {
        DetailMealWireframe.startDetailFood(source, foodName)
    }
}