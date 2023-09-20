package com.example.book_search_kotlin

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.ImageView
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.Objects

class ImageSharing(private val context: Context, private val imageView: ImageView) {
    fun getBitmapUrl(title: String) {
        val drawable = imageView.drawable
        var bitmap: Bitmap? = null
        var bitmapUri: Uri? = null
        if (drawable is BitmapDrawable) {
            bitmap = (imageView.drawable as BitmapDrawable).bitmap
        } else {
            Log.d("bitmap", "failed")
        }

        try {
            val file = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                "share_image$title.png"
            )
            Objects.requireNonNull(file.parentFile).mkdirs()
            val out = FileOutputStream(file)
            bitmap?.compress(Bitmap.CompressFormat.PNG, 90, out)
            out.close()
            bitmapUri = FileProvider
                .getUriForFile(
                    context, context.applicationContext
                        .packageName + ".provider", file
                )
        } catch (e: IOException) {
            e.printStackTrace()
        }
        sharingFunction(bitmapUri, title)


    }

    private fun sharingFunction(bitmapUri: Uri?, bookTitle: String) {
        if (bitmapUri != null) {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Check out this book: $bookTitle"
            )
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, bookTitle)
            shareIntent.putExtra(Intent.EXTRA_STREAM, bitmapUri)
            shareIntent.type = "image/*"

            context.startActivity(Intent.createChooser(shareIntent, "Share Image"))
        } else {
            Log.d("bitmap uri", "failed")
        }
    }


}