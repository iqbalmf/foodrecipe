package net.iqbalfauzan.foodrecipe.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import net.iqbalfauzan.foodrecipe.R
import net.iqbalfauzan.foodrecipe.databinding.ItemCategoryBinding
import net.iqbalfauzan.foodrecipe.model.Category


/**
 * Created by Iqbalmf on 2019-08-11
 */
class HomeCategoryListAdapter(val categories: ArrayList<Category>, private val listener: EventListener?) :
    RecyclerView.Adapter<HomeCategoryListAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_category, parent, false)
        )
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.apply {
            holder.binding.category = categories[position]
            holder.binding.categoryView.setOnClickListener{
                listener?.onClickCategory(categories[position])
            }
        }
    }

    fun updateCategories(foodCategories: List<Category>) {
        categories.clear()
        categories.addAll(foodCategories)
        notifyDataSetChanged()
    }

    inner class CategoryViewHolder(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.getRoot())

    interface EventListener {
        fun onClickCategory(categor: Category)
    }
}