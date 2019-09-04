package net.iqbalfauzan.foodrecipe.view.home

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import net.iqbalfauzan.foodrecipe.R
import net.iqbalfauzan.foodrecipe.model.Category
import net.iqbalfauzan.foodrecipe.model.Meals
import net.iqbalfauzan.foodrecipe.view.categorymenu.CategoryMenuActivity
import net.iqbalfauzan.foodrecipe.view.categorymenu.CategoryMenuWireframe
import net.iqbalfauzan.foodrecipe.viewmodel.HomeViewModel


class HomeActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeViewModel
    private var wireframe = HomeWireframe()
    private lateinit var categoryListAdapter: HomeCategoryListAdapter
    private lateinit var latestMealAdapter: HomeLatestMealAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        observeViewModel()
    }

    private fun init() {
        viewModel = ViewModelProviders.of(this@HomeActivity).get(HomeViewModel::class.java)
        viewModel.refresh()
        populateListItem()
        listCategory.apply {
            layoutManager = GridLayoutManager(this@HomeActivity, 3, RecyclerView.VERTICAL, false)
            adapter = categoryListAdapter
        }

        listLatestMeal.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity, RecyclerView.HORIZONTAL, false)
            adapter = latestMealAdapter

            LinearSnapHelper().attachToRecyclerView(listLatestMeal)
        }
    }

    private fun observeViewModel() {
        viewModel.categories.observe(this@HomeActivity, Observer { categories ->
            categories.let {
                categoryListAdapter.updateCategories(it)
            }
        })

        viewModel.latestMeal.observe(this@HomeActivity, Observer { latestFood ->
            latestFood.let {
                latestMealAdapter.updateLatestMeal(it)
            }
        })

        viewModel.shouldShowError.observe(this@HomeActivity, Observer { isError ->
            isError.let {
                labelErrorCategory.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.shouldShowLoading.observe(this@HomeActivity, Observer { isLoading ->
            isLoading.let {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.shouldOpenCategoryList.observe(this@HomeActivity, Observer {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startActivity(
                    Intent(this, CategoryMenuActivity::class.java)
                        .putExtra(CategoryMenuWireframe.CATEGORY_NAME, it),
                    ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
                )
            } else {
                wireframe.openCategoryList(this@HomeActivity, it)
            }
        })

        viewModel.shouldShowMessage.observe(this@HomeActivity, Observer {
            Log.e("LATEST_TAG", it)
        })
    }

    private fun populateListItem() {
        categoryListAdapter =
            HomeCategoryListAdapter(arrayListOf(), object : HomeCategoryListAdapter.EventListener {
                override fun onClickCategory(categor: Category) {
                    viewModel.onClickCategory(categor)
                }
            })

        latestMealAdapter =
            HomeLatestMealAdapter(arrayListOf(), object : HomeLatestMealAdapter.EventListener {
                override fun onClickLatest(latestMeals: Meals.Meal) {
                    viewModel.onClickLatestMeal(latestMeals)
                }
            })
    }
}
