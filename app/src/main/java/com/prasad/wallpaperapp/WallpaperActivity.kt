package com.prasad.wallpaperapp

import android.app.WallpaperManager
import android.content.Intent
import android.graphics.Bitmap
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.shashank.sony.fancytoastlib.FancyToast
import java.io.IOException

class WallpaperActivity : AppCompatActivity() {
    lateinit var IVWallpaper : ImageView
    lateinit var btnSetWallpaper : Button
    lateinit var imgUrl :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallpaper)

        IVWallpaper = findViewById(R.id.IVWallpaper)
        btnSetWallpaper = findViewById(R.id.btnSetWallpaper)
        imgUrl = intent.getStringExtra("imgUrl").toString()
        Glide.with(this).load(imgUrl).into(IVWallpaper)
        val wallpaperManager = WallpaperManager.getInstance(applicationContext)

        btnSetWallpaper.setOnClickListener {
                    Glide.with(this).asBitmap().load(imgUrl).listener(object:
                        RequestListener<Bitmap> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            Toast.makeText(this@WallpaperActivity,"Failed to load Image",Toast.LENGTH_SHORT).show()
                            return false
                        }

                        override fun onResourceReady(
                            resource: Bitmap?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            try {
                                wallpaperManager.setBitmap(resource)
                            }catch(e : IOException){
                                e.printStackTrace()
                                Toast.makeText(this@WallpaperActivity,"Failed to set Wallpaper",Toast.LENGTH_SHORT).show()

                            }
                            return false
                        }

                    }).submit()


            FancyToast.makeText(this@WallpaperActivity,"Wallpaper Set to Screen",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show()
        }


    }
}