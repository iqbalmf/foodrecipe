package net.iqbalfauzan.foodrecipe.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import net.iqbalfauzan.foodrecipe.base.BaseViewModel
import net.iqbalfauzan.foodrecipe.model.Categories
import net.iqbalfauzan.foodrecipe.model.Category

/**
 * Project foodrecipe
 *
 * Created by IqbalMF on 8/20/2019
 */
class CategoryMenuViewModel(application: Application) : BaseViewModel(application = application){
    val categoryName = MutableLiveData<String>()

    fun fetch(category: String?){
        categoryName.value = category
    }
}