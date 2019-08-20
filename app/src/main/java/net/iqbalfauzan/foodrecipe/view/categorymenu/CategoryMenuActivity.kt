package net.iqbalfauzan.foodrecipe.view.categorymenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import net.iqbalfauzan.foodrecipe.R
import net.iqbalfauzan.foodrecipe.viewmodel.CategoryMenuViewModel

class CategoryMenuActivity : AppCompatActivity() {
    private lateinit var viewModel: CategoryMenuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_menu)
        init()
        observViewModel()
    }

    private fun init(){
        viewModel = ViewModelProviders.of(this@CategoryMenuActivity).get(CategoryMenuViewModel::class.java)
        viewModel.fetch(intent.getStringExtra(CategoryMenuWireframe.CATEGORY_NAME))
    }

    private fun observViewModel(){
        viewModel.categoryName.observe(this@CategoryMenuActivity, Observer {
            Toast.makeText(this@CategoryMenuActivity, it, Toast.LENGTH_LONG).show()
        })
    }
}
