package net.iqbalfauzan.foodrecipe.utils

import android.annotation.TargetApi
import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build

/**
 * Project foodrecipe
 *
 * Created by IqbalMF on 8/20/2019
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
fun Activity.start(target: Class<*>, requestCode: Int? = null, func: (Intent.() -> Unit)? = null) {
    val intent = Intent(this, target)
    val activityOption = ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
    func?.let { intent.it() }
    when {
        requestCode != null -> startActivityForResult(intent, requestCode)
        func != null -> startActivity(intent, activityOption)
        else -> startActivity(intent)
    }
}