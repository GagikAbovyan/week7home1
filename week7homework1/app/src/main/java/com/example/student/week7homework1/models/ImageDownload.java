package com.example.student.week7homework1.models;

import android.os.Parcelable;

import java.io.Serializable;

public class ImageDownload implements Serializable {

    private final String http;
    private boolean isSaved;

    public ImageDownload(final String http, final boolean isSaved) {
        this.http = http;
        this.isSaved = isSaved;
    }

    public String getHttp() {
        return http;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(final boolean saved) {
        isSaved = saved;
    }
}
