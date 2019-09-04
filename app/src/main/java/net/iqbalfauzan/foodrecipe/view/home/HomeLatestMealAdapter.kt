package net.iqbalfauzan.foodrecipe.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import net.iqbalfauzan.foodrecipe.R
import net.iqbalfauzan.foodrecipe.databinding.ItemLatestMealBinding
import net.iqbalfauzan.foodrecipe.model.Meals
import kotlin.collections.ArrayList

class HomeLatestMealAdapter(
    val latestMeal: ArrayList<Meals.Meal>,
    private val listener: EventListener?
) :
    RecyclerView.Adapter<HomeLatestMealAdapter.HomeLatestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeLatestViewHolder {
        return HomeLatestViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_latest_meal, parent, false)
        )
    }

    override fun getItemCount(): Int = latestMeal.size

    override fun onBindViewHolder(holder: HomeLatestViewHolder, position: Int) {
        holder.apply {
            holder.binding.latestFood = latestMeal[position]
            holder.binding.latestFoodImage.setOnClickListener {
                listener?.onClickLatest(latestMeal[position])
            }
        }
    }

    fun updateLatestMeal(latestMeals: List<Meals.Meal>){
        latestMeal.clear()
        latestMeal.addAll(latestMeals)
        notifyDataSetChanged()
    }

    inner class HomeLatestViewHolder(val binding: ItemLatestMealBinding) : RecyclerView.ViewHolder(binding.root)

    interface EventListener {
        fun onClickLatest(latestMeals: Meals.Meal)
    }
}