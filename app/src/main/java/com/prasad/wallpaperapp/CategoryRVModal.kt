package com.prasad.wallpaperapp

class CategoryRVModal( private var category: String,  private var categoryIVURL: String) {
    fun getCategory(): String {
        return category
    }

    fun setCategory(category : String) {
        this.category = category
    }

    fun getCategoryIVURL() : String{
        return categoryIVURL
    }

    fun setCategoryIVURL(categoryIVURL: String){
        this.categoryIVURL = categoryIVURL


    }


}