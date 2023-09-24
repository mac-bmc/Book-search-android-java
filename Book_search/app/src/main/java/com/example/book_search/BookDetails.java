package com.example.book_search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class BookDetails extends AppCompatActivity {

    String imageUrl;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        TextView titleMain = findViewById(R.id.titleMain);
        TextView publisher = findViewById(R.id.detailPublisher);
        TextView pageNo = findViewById(R.id.pageNo);
        ImageView bookCoverImageView = findViewById(R.id.bookCoverImageView);
        TextView title = findViewById(R.id.title);
        ImageButton imageSearchButton = findViewById(R.id.imageSearchButton);
        TextView authorMain = findViewById(R.id.detailAuthor);
        titleMain.setText(Objects.requireNonNull(getIntent().getExtras()).getString("title"));
        title.setText(Objects.requireNonNull(getIntent().getExtras()).getString("title"));
        pageNo.setText(Objects.requireNonNull(getIntent().getExtras()).getString("pageNo"));
        publisher.setText(Objects.requireNonNull(getIntent().getExtras()).getString("publisher"));
        authorMain.setText(Objects.requireNonNull(getIntent().getExtras()).getString("author"));
        imageUrl = Objects.requireNonNull(getIntent().getExtras()).getString("imageUrl");
        String bookTitle = getIntent().getExtras().getString("title");
        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error)
                .into(bookCoverImageView);

        imageSearchButton.setOnClickListener(view -> {
            Uri bmpUri = getLocalBitmapUri(bookCoverImageView);
            if (bmpUri != null) {

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this book: " + bookTitle + "\n" + imageUrl);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, bookTitle);
                shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
                shareIntent.setType("image/*");

                startActivity(Intent.createChooser(shareIntent, "Share Image"));
            } else {
                Toast.makeText(this, getString(R.string.share_failed), Toast.LENGTH_SHORT).show();
            }


        });

    }

    public Uri getLocalBitmapUri(ImageView imageView) {
        Context context = BookDetails.this;
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp;
        if (drawable instanceof BitmapDrawable) {
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }
        Uri bmpUri = null;
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
            Objects.requireNonNull(file.getParentFile()).mkdirs();
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