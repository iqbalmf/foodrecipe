package net.iqbalfauzan.foodrecipe.view.categorymenu

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_category_menu.*
import net.iqbalfauzan.foodrecipe.R
import net.iqbalfauzan.foodrecipe.viewmodel.CategoryMenuViewModel

class CategoryMenuActivity : AppCompatActivity() {
    private lateinit var viewModel: CategoryMenuViewModel
    private lateinit var categoryMenuAdapter: CategoryMenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_menu)
        init()
        observViewModel()
    }

    private fun init() {
        viewModel = ViewModelProviders.of(this@CategoryMenuActivity).get(CategoryMenuViewModel::class.java)

        viewModel.fetch(intent.getStringExtra(CategoryMenuWireframe.CATEGORY_NAME))

        populateListItem()
        menuCategoryList.apply {
            layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            adapter = categoryMenuAdapter
        }
    }

    private fun observViewModel() {
        viewModel.categoryName.observe(this@CategoryMenuActivity, Observer {
            Toast.makeText(this@CategoryMenuActivity, it, Toast.LENGTH_LONG).show()
        })

        viewModel.mealCategory.observe(this@CategoryMenuActivity, Observer { mealsCategory ->
            mealsCategory.let {
                categoryMenuAdapter.updateMenuCategory(it)
            }
        })
    }

    private fun populateListItem(){
        categoryMenuAdapter = CategoryMenuAdapter(arrayListOf())
    }
}
