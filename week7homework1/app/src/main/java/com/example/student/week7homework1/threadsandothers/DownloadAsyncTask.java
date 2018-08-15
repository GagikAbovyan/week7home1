package com.example.student.week7homework1.threadsandothers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.student.week7homework1.R;
import com.example.student.week7homework1.activities.MainActivity;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class DownloadAsyncTask extends AsyncTask<String, Void, Bitmap> {


    @SuppressLint("StaticFieldLeak")
    private final Context context;
    private final int position;

    public DownloadAsyncTask(final Context context, final int position) {
        this.context = context;
        this.position = position;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        ((MainActivity)context).setVisibility(true);
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        final String urlDisplay = strings[0];
        Bitmap bitmap;
        try {
            final InputStream inputStream = new URL(urlDisplay).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            Toast.makeText(context, context.getString(R.string.try_again), Toast.LENGTH_SHORT).show();
            bitmap = null;
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        ((MainActivity)context).setVisibility(false);
        saveImage(bitmap);
    }

    private void saveImage(final Bitmap bitmap){
        MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, context.getString(R.string.title) + String.valueOf(position), context.getString(R.string.description));
    }

}
