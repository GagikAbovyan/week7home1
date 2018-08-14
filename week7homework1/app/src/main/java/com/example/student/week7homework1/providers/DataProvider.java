package com.example.student.week7homework1.providers;

import com.example.student.week7homework1.models.ImageDownload;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {
    private static List<ImageDownload> list = new ArrayList<>();
    private static int position = 0;
    public static List<ImageDownload> getList() {
        return list;
    }

    public static int getPosition() {
        return position;
    }

    public static void setPosition(int position) {
        DataProvider.position = position;
    }
}
