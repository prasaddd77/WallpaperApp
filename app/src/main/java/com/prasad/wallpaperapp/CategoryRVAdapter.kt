package com.prasad.wallpaperapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CategoryRVAdapter(
    private val categoryRVModalArrayList: ArrayList<CategoryRVModal>,
    private val context: Context,
    private val categoryClickInterface : CategoryClickInterface
) : RecyclerView.Adapter<CategoryRVAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.category_rv_item,parent,false)
        return CategoryRVAdapter.CategoryViewHolder(view)

    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categoryRVModal : CategoryRVModal = categoryRVModalArrayList[position]
        holder.txtIVCategory.setText(categoryRVModal.getCategory())
        Glide.with(context).load(categoryRVModal.getCategoryIVURL()).into(holder.imgIVCategory)
        holder.itemView.setOnClickListener{
                categoryClickInterface.onCategoryClick(position)
        }
    }

    override fun getItemCount(): Int {
        return categoryRVModalArrayList.size
    }

     class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val txtIVCategory : TextView  = itemView.findViewById(R.id.txtIVCategory)
         val imgIVCategory : ImageView = itemView.findViewById(R.id.imgIVCategory)
     }

    interface CategoryClickInterface{
        fun onCategoryClick(position : Int)

    }
}