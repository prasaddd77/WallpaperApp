package com.prasad.wallpaperapp

import android.content.Context
import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class WallpaperRVAdapter(
    private val wallpaperRVArrayList : ArrayList<String>,
    private val context : Context
) : RecyclerView.Adapter<WallpaperRVAdapter.WallpaperViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.wallpaper_rv_item,parent,false)
        return WallpaperRVAdapter.WallpaperViewHolder(view)
    }

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        Glide.with(context).load(wallpaperRVArrayList[position]).into(holder.imgWallpaper)
        holder.itemView.setOnClickListener{
                val intent = Intent(context,WallpaperActivity::class.java)
                intent.putExtra("imgUrl",wallpaperRVArrayList[position])
                context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return wallpaperRVArrayList.size
    }

    class WallpaperViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgWallpaper : ImageView = itemView.findViewById(R.id.imgWallpaper)
    }

}


