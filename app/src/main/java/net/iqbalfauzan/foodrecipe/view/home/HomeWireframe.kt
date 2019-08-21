package net.iqbalfauzan.foodrecipe.view.home

import android.annotation.TargetApi
import android.app.Activity
import android.app.ActivityOptions
import android.os.Build
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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun openCategoryList(source: Activity, category: String){
        CategoryMenuWireframe.startCategoryMenu(source = source, category = category)
    }
}