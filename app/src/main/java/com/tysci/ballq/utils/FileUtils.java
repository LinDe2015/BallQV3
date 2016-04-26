package com.tysci.ballq.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by QianBao on 2016/4/26.
 * utils for file
 */
public class FileUtils {
    private FileUtils() {
    }

    public static File getEnvironmentFile(String fileName) {
        return new File(Environment.getExternalStorageDirectory(), fileName);
    }
}
