package net.iqbalfauzan.foodrecipe.view.detailfood

import android.app.Activity
import net.iqbalfauzan.foodrecipe.utils.start

class DetailMealWireframe {
    companion object {
        const val FOOD_NAME = "DetailMealWireframe.FOOD_NAME"
        fun startDetailFood(source: Activity, foodName: String) {
            source.start(DetailMealActivity::class.java) {
                putExtra(FOOD_NAME, foodName)
            }
        }
    }
}