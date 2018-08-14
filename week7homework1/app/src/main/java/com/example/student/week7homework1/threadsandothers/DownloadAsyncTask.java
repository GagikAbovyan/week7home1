package com.example.student.week7homework1.threadsandothers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;
import com.example.student.week7homework1.activities.MainActivity;
import com.example.student.week7homework1.providers.DataProvider;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class DownloadAsyncTask extends AsyncTask<String, Void, Bitmap> {


    @SuppressLint("StaticFieldLeak")
    private final Context context;

    public DownloadAsyncTask(final Context context) {
        this.context = context;
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
            Log.i("fffff", "fail");
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
        MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Title" + String.valueOf(DataProvider.getPosition()), "descr");
    }

}
