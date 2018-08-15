package com.example.student.week7homework1.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.student.week7homework1.R;
import com.example.student.week7homework1.adapters.DownloadAdapter;
import com.example.student.week7homework1.dialogs.ImageDialog;
import com.example.student.week7homework1.models.ImageDownload;
import com.example.student.week7homework1.threadsandothers.DownloadAsyncTask;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView publicText;
    private boolean flag = false;
    private int position;
    private List<ImageDownload> list = new ArrayList<>();

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initData();
        startRecView();
        downloadImage();
    }

    private void downloadImage() {
        final Button downloadButton = findViewById(R.id.download_button);
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"CommitTransaction", "StringFormatInvalid"})
            @Override
            public void onClick(View view) {
                if (!flag) {
                    isCorrect();
                    flag = true;
                }
                if (!list.get(position).isSaved()) {
                    askPermissions();
                    new DownloadAsyncTask(MainActivity.this, position).execute(publicText.getText().toString());
                    list.get(position).setSaved(true);
                }else {
                    final Bundle bundle = new Bundle();
                    bundle.putInt(getString(R.string.my_key), position);
                    bundle.putSerializable(getString(R.string.my_list_key), (Serializable) list);
                    ImageDialog imageDialog = new ImageDialog();
                    imageDialog.setArguments(bundle);
                    imageDialog.show(getFragmentManager().beginTransaction(), "commit");
                }
            }
        });
    }

    private void askPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) +
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale
                    (this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
                    ActivityCompat.shouldShowRequestPermissionRationale
                            (this, Manifest.permission.READ_CONTACTS)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission
                                .WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS},
                        1001);
            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_CONTACTS
                        },
                        1001);
            }
        } else {
            Toast.makeText(this, getString(R.string.not_resorces), Toast.LENGTH_SHORT).show();
        }
    }

    private void isCorrect(){
        if (publicText.getText().toString().isEmpty()){
            Toast.makeText(this, getString(R.string.incorrect_argument), Toast.LENGTH_SHORT).show();
        }
    }

    private void startRecView() {
        DownloadAdapter downloadAdapter = new DownloadAdapter(this, getResources().getStringArray(R.array.items));

        final RecyclerView recyclerView = findViewById(R.id.rec_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(downloadAdapter);
    }

    public void setVisibility(final boolean flag){
        final ProgressBar progressBar = findViewById(R.id.progress);
        final TextView textLoading = findViewById(R.id.message_state);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (flag){
                    progressBar.setVisibility(View.VISIBLE);
                    textLoading.setVisibility(View.VISIBLE);
                }else {
                    progressBar.setVisibility(View.INVISIBLE);
                    textLoading.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public void updateText(final int position){
        final String url = list.get(position).getHttp();
        this.position = position;
        publicText.setText(url);
    }

    private void findViews() {
        publicText = findViewById(R.id.text_https);
    }

    private void initData(){
        list.add(new ImageDownload(getString(R.string.http1), false));
        list.add(new ImageDownload(getString(R.string.http2), false));
        list.add(new ImageDownload(getString(R.string.http3), false));
    }

}
