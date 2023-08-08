package com.example.cocktailbar.storage.photo

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhotoStorage(
    context: Context
) {

    private val contentResolver = context.contentResolver

    suspend fun getPhotos(amount: Int = 10): List<Uri> = withContext(Dispatchers.IO) {
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATE_ADDED
        )

        val cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            "${MediaStore.Images.Media.DATE_ADDED} DESC"
        ) ?: return@withContext emptyList()

        val images = mutableListOf<Uri>()

        cursor.use {
            val readAmount = 0
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            while (cursor.moveToNext() && readAmount != amount) {
                val id = cursor.getLong(idColumn)
                images.add(
                    ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                )
                amount.inc()
            }
        }
        Log.w("wwwwww", images.toString())

        return@withContext images

    }
}