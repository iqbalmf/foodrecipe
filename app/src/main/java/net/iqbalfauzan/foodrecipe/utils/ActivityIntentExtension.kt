package net.iqbalfauzan.foodrecipe.utils

import android.app.Activity
import android.content.Intent

/**
 * Project foodrecipe
 *
 * Created by IqbalMF on 8/20/2019
 */
fun Activity.start(target: Class<*>, requestCode: Int? = null, func: (Intent.() -> Unit)? = null) {
    val intent = Intent(this, target)
    func?.let { intent.it() }
    if (requestCode != null) {
        startActivityForResult(intent, requestCode)
    } else {
        startActivity(intent)
    }
}