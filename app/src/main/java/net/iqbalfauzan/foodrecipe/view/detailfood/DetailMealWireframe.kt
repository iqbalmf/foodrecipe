package net.iqbalfauzan.foodrecipe.view.detailfood

import android.app.Activity
import net.iqbalfauzan.foodrecipe.utils.start

class DetailMealWireframe {
    companion object {
        fun startDetailFood(source: Activity) {
            source.start(DetailMealActivity::class.java)
        }
    }
}