package com.prasad.wallpaperapp

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity(),CategoryRVAdapter.CategoryClickInterface {
    lateinit var etSearchBar : EditText
    lateinit var imgSearchBar : ImageView
    lateinit var categoryRV : RecyclerView
    lateinit var wallpaperRV: RecyclerView
    lateinit var progressBar : ProgressBar
    lateinit var wallpaperArrayList : ArrayList<String>
    lateinit var categoryRVModalArrayList : ArrayList<CategoryRVModal>
    lateinit var categoryRVAdapter : CategoryRVAdapter
    lateinit var wallpaperRVAdapter : WallpaperRVAdapter
    //563492ad6f91700001000001c500f83838eb4760801ab9edd5edb307

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etSearchBar = findViewById(R.id.etSearchBar)
        imgSearchBar = findViewById(R.id.imgSearchBar)
        categoryRV = findViewById(R.id.categoryRV)
        wallpaperRV = findViewById(R.id.wallpaperRV)
        progressBar = findViewById(R.id.progressBar)
        wallpaperArrayList = ArrayList()
        categoryRVModalArrayList = ArrayList()
        val linearLayoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        categoryRV.layoutManager = linearLayoutManager
        categoryRVAdapter = CategoryRVAdapter(categoryRVModalArrayList,this,this)
        categoryRV.adapter = categoryRVAdapter

        val gridLayoutManager = GridLayoutManager(this,2)
        wallpaperRV.layoutManager = gridLayoutManager
        wallpaperRVAdapter = WallpaperRVAdapter(wallpaperArrayList,this)
        wallpaperRV.adapter = wallpaperRVAdapter

        getCategories()
        getWallpapers()

        imgSearchBar.setOnClickListener {
                val etSearchBar = etSearchBar.text.toString()
                if(etSearchBar.isEmpty()){
                    Toast.makeText(this,"Please enter your search query",Toast.LENGTH_SHORT).show()
                }else{
                    getWallpapersByCategory(etSearchBar)

                }
        }
    }
    private fun getWallpapersByCategory(category : String) {
        wallpaperArrayList.clear()
        progressBar.visibility = View.VISIBLE
        val url: String =
            buildString {
        append("https://api.pexels.com/v1/search?query=")
        append(category)
        append("&page=1&per_page=30")
    }
        val requestQueue = Volley.newRequestQueue(this)
        val jsonObjectRequest = object: JsonObjectRequest(
            Request.Method.GET, url, null,
            {
                    progressBar.visibility = View.GONE
                    try {
                        val photoArray: JSONArray = it.getJSONArray("photos")
                        for (i in 0..photoArray.length()) {
                            val photoObj: JSONObject = photoArray.getJSONObject(i)
                            val imgUrl: String = photoObj.getJSONObject("src").getString("portrait")
                            wallpaperArrayList.add(imgUrl)
                        }
                        wallpaperRVAdapter.notifyDataSetChanged()

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                },
            {
                    Toast.makeText(this,"Failed to load wallpapers..",Toast.LENGTH_SHORT).show()
            })
            {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers : HashMap<String,String> = HashMap()
                    headers.put(
                        "Authorization",
                        "563492ad6f91700001000001c500f83838eb4760801ab9edd5edb307"
                    )
                    return headers
            }
        }
        requestQueue.add(jsonObjectRequest)
    }



    private fun getCategories(){
        categoryRVModalArrayList.add(CategoryRVModal("Technology", "https://images.unsplash.com/photo-1526374965328-7f61d4dc18c5?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fHRlY2hub2xvZ3l8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"))
        categoryRVModalArrayList.add(CategoryRVModal("Programming","https://images.unsplash.com/photo-1542831371-29b0f74f9713?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8cHJvZ3JhbW1pbmd8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"))
        categoryRVModalArrayList.add(CategoryRVModal("Nature","https://images.pexels.com/photos/2387873/pexels-photo-2387873.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"))
        categoryRVModalArrayList.add(CategoryRVModal("Travel","https://images.pexels.com/photos/672358/pexels-photo-672358.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"))
        categoryRVModalArrayList.add(CategoryRVModal("Architecture","https://images.pexels.com/photos/256150/pexels-photo-256150.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"))
        categoryRVModalArrayList.add(CategoryRVModal("Arts","https://images.pexels.com/photos/1194420/pexels-photo-1194420.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"))
        categoryRVModalArrayList.add(CategoryRVModal("Music","https://images.pexels.com/photos/4348093/pexels-photo-4348093.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"))
        categoryRVModalArrayList.add(CategoryRVModal("Abstract","https://images.pexels.com/photos/2110951/pexels-photo-2110951.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"))
        categoryRVModalArrayList.add(CategoryRVModal("Cars","https://images.pexels.com/photos/3802510/pexels-photo-3802510.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"))
        categoryRVModalArrayList.add(CategoryRVModal("Flowers","https://images.pexels.com/photos/1086178/pexels-photo-1086178.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"))
        categoryRVAdapter.notifyDataSetChanged()



    }

    private fun getWallpapers(){
        wallpaperArrayList.clear()
        progressBar.visibility = View.VISIBLE
        val url : String = "https://api.pexels.com/v1/curated?page=1&per_page=30"
        val requestQueue : RequestQueue = Volley.newRequestQueue(this)
        val jsonObjectRequest : JsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET, url, null,
            {
                    progressBar.visibility = View.GONE
                    try {

                        val photoArray : JSONArray = it.getJSONArray("photos")
                        for(i in 0..photoArray.length()){
                            val photoObj : JSONObject = photoArray.getJSONObject(i)
                            val imgUrl : String = photoObj.getJSONObject("src").getString("portrait")
                            wallpaperArrayList.add(imgUrl)
                        }
                        wallpaperRVAdapter.notifyDataSetChanged()

                    }catch (e : JSONException){
                        e.printStackTrace()
                    }
            },
            {
                    Toast.makeText(this,"Failed to load wallpapers..",Toast.LENGTH_SHORT).show()
            })
            {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers : HashMap<String,String> = HashMap()
                    headers.put(
                        "Authorization",
                        "563492ad6f91700001000001c500f83838eb4760801ab9edd5edb307"
                    )
                    return headers

                }
            }
        requestQueue.add(jsonObjectRequest)
    }

    override fun onCategoryClick(position: Int) {
        val category : String = categoryRVModalArrayList[position].getCategory()
        getWallpapersByCategory(category)
    }
}




