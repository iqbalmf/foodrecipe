package net.iqbalfauzan.foodrecipe.view.home

import android.app.Activity
import android.content.Intent
import net.iqbalfauzan.foodrecipe.utils.start
import net.iqbalfauzan.foodrecipe.view.categorymenu.CategoryMenuWireframe

/**
 * Project foodrecipe
 *
 * Created by IqbalMF on 8/20/2019
 */
class HomeWireframe {
    companion object{
        fun startHomeActivity(source: Activity){
            source.start(HomeActivity::class.java)
        }
    }

    fun openCategoryList(source: Activity, category: String){
        CategoryMenuWireframe.startCategoryMenu(source = source, category = category)
    }
}