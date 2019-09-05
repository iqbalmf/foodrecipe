package net.iqbalfauzan.foodrecipe.view.categorymenu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import net.iqbalfauzan.foodrecipe.R
import net.iqbalfauzan.foodrecipe.databinding.ItemMenuCategoryBinding
import net.iqbalfauzan.foodrecipe.model.Meals
import java.util.*
import kotlin.collections.ArrayList

class CategoryMenuAdapter(val categoryMenu: ArrayList<Meals.Meal>,
                          val listener: EventListener?) :
    RecyclerView.Adapter<CategoryMenuAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_menu_category, parent, false
            )
        )
    }

    override fun getItemCount(): Int = categoryMenu.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            holder.binding.categoryName = categoryMenu[position]
            holder.binding.categoryNameImage.setOnClickListener {
                listener?.onClickListener(categoryMenu = categoryMenu[position])
            }
        }
    }

    fun updateMenuCategory(menuCategory: List<Meals.Meal>) {
        categoryMenu.clear()
        categoryMenu.addAll(menuCategory)
        notifyDataSetChanged()
    }


    inner class ViewHolder(val binding: ItemMenuCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface EventListener {
        fun onClickListener(categoryMenu: Meals.Meal)
    }
}