package net.iqbalfauzan.foodrecipe.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import net.iqbalfauzan.foodrecipe.R
import net.iqbalfauzan.foodrecipe.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeViewModel
    private val categoryListAdapter = CategoryListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        observeViewModel()
    }

    private fun init() {
        viewModel = ViewModelProviders.of(this@HomeActivity).get(HomeViewModel::class.java)
        viewModel.refresh()

        listCategory.apply {
            layoutManager = GridLayoutManager(this@HomeActivity, 3, RecyclerView.VERTICAL, false)
            adapter = categoryListAdapter
        }

    }

    private fun observeViewModel() {
        viewModel.categories.observe(this@HomeActivity, Observer { categories ->
            categories.let {
                categoryListAdapter.updateCategories(it)
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

        viewModel.shouldShowToast.observe(this@HomeActivity, Observer {
            Toast.makeText(this@HomeActivity, it, Toast.LENGTH_SHORT).show()
        })
    }
}
