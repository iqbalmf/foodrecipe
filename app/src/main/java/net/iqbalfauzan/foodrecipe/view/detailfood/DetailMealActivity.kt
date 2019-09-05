package net.iqbalfauzan.foodrecipe.view.detailfood

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_meal.*
import net.iqbalfauzan.foodrecipe.R


class DetailMealActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailMealViewModel
    private var wireframe = DetailMealWireframe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_meal)
        init()
        observeViewModel()
    }

    private fun init() {
        viewModel = ViewModelProviders.of(this@DetailMealActivity).get(DetailMealViewModel::class.java)
        viewModel.prepareDetailsFood(intent.getStringExtra(DetailMealWireframe.FOOD_NAME))

        toolbar_back.setOnClickListener {
            onBackPressed()
        }
        collapsing_toolbar.title = intent.getStringExtra(DetailMealWireframe.FOOD_NAME)

        collapsing_toolbar.setCollapsedTitleTextColor(
            ContextCompat.getColor(this, android.R.color.white)
        )

    }

    private fun observeViewModel() {
        viewModel.mealsDetail.observe(this@DetailMealActivity, Observer { foodName ->
            foodName.let {
                Glide.with(this@DetailMealActivity).load(it.first().mealImage).into(detailFoodImage)
            }

        })
    }
}
