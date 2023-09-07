package com.example.book_search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Book_details extends AppCompatActivity {

    String imageurl;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        TextView titlemain=(TextView) findViewById(R.id.titlemain);
        TextView publisher=(TextView) findViewById(R.id.detailpublisher);
        TextView pgno=(TextView) findViewById(R.id.pageno);
        ImageView bookCoverImageView=(ImageView) findViewById(R.id.bookCoverImageView);
        TextView title=(TextView) findViewById(R.id.title);
        ImageButton imagesearchButton = (ImageButton) findViewById(R.id.imagesearchButton);
        TextView authormain=(TextView) findViewById(R.id.detailauthor);
        titlemain.setText(getIntent().getExtras().getString("title"));
        title.setText(getIntent().getExtras().getString("title"));
        pgno.setText(getIntent().getExtras().getString("pageno"));
        publisher.setText(getIntent().getExtras().getString("publisher"));
        authormain.setText(getIntent().getExtras().getString("author"));
        imageurl=getIntent().getExtras().getString("imageurl");
        String bookTitle=getIntent().getExtras().getString("title");
        Glide.with(this)
                .load(imageurl)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error)
                .into(bookCoverImageView);

        imagesearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri bmpUri = getLocalBitmapUri(bookCoverImageView);
                if (bmpUri != null) {
                    // Construct a ShareIntent with link to image
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this book: " + bookTitle + "\n" + imageurl);
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, bookTitle);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
                    shareIntent.setType("image/*");
                    // Launch sharing dialog for image
                    startActivity(Intent.createChooser(shareIntent, "Share Image"));
                } else {
                    // ...sharing failed, handle error
                }


            }
        });

    }

    /*public Uri getLocalBitmapUri(ImageView imageView) {
        // Extract Bitmap from ImageView drawable
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable){
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }
        // Store image to default external storage directory
        Uri bmpUri = null;
        try {
            File file =  new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;

    }*/
    /*private Bitmap getLocalBitmapUri(View view)
    {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),view.getHeight()
                ,Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }*/
    public Uri getLocalBitmapUri(ImageView imageView) {
        // Extract Bitmap from ImageView
        Context context = Book_details.this;
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable){
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }
        // Store image to default external storage directory
        Uri bmpUri = null;
        try {
            File file =  new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
            bmpUri = FileProvider
                    .getUriForFile(context, context.getApplicationContext()
                            .getPackageName() + ".provider", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }


}