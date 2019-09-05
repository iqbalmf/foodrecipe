package net.iqbalfauzan.foodrecipe.view.categorymenu

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_category_menu.*
import net.iqbalfauzan.foodrecipe.R
import net.iqbalfauzan.foodrecipe.model.Meals

class CategoryMenuActivity : AppCompatActivity() {
    private lateinit var viewModel: CategoryMenuViewModel
    private lateinit var categoryMenuAdapter: CategoryMenuAdapter
    private var wireframe = CategoryMenuWireframe()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_menu)
        init()
        observViewModel()
    }

    private fun init() {
        viewModel =
            ViewModelProviders.of(this@CategoryMenuActivity).get(CategoryMenuViewModel::class.java)

        viewModel.fetch(intent.getStringExtra(CategoryMenuWireframe.CATEGORY_NAME))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = intent.getStringExtra(CategoryMenuWireframe.CATEGORY_NAME)
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

        viewModel.shouldShowLoading.observe(this, Observer { isLoading ->
            isLoading.let {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.shouldOpenDetailFood.observe(this@CategoryMenuActivity, Observer {
            wireframe.openDetailFood(this@CategoryMenuActivity, it)
        })
    }

    private fun populateListItem() {
        categoryMenuAdapter =
            CategoryMenuAdapter(arrayListOf(), object : CategoryMenuAdapter.EventListener {
                override fun onClickListener(categoryMenu: Meals.Meal) {
                    categoryMenu.mealName?.let { viewModel.onClickDetailFood(it) }
                }
            })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
