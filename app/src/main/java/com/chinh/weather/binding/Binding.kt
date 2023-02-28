package com.chinh.weather.binding

import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chinh.weather.R
import com.chinh.weather.ui.adapter.GlideApp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

fun ImageView.loadImage(url: String?, placeHolderImage: Int? = null, errorImage: Int? = null) {
    val placeHolder = placeHolderImage ?: R.drawable.ic_news_svgrepo_com
    val error = errorImage ?: R.drawable.ic_news_svgrepo_com
    val scaleType = this.scaleType
    if (url.isNullOrEmpty()) {
        var request = GlideApp.with(this.context)
            .load(error)
        if (scaleType == ImageView.ScaleType.CENTER_CROP)
            request = request.centerCrop()
        request.into(this)
        return
    }
    GlideApp.with(this.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .placeholder(placeHolder)
        .error(error)
        .into(this)
}

fun SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {

    val query = MutableStateFlow("")

    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            query.value = newText
            return true
        }
    })

    return query

}