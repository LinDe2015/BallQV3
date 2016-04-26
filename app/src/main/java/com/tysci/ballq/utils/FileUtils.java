package com.tysci.ballq.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by QianBao on 2016/4/26.
 * util for File
 */
public class FileUtils {
    public static String readCache(Context c, String fileName) {
        return read(new File(c.getCacheDir(), fileName));
    }

    public static boolean writeCache(Context c, String fileName, String writeString) {
        return writeCache(c, fileName, writeString, false);
    }

    public static boolean writeCache(Context c, String fileName, String writeString, boolean append) {
        return write(new File(c.getCacheDir(), fileName), writeString, append);
    }

    public static String readFile(Context c, String fileName) {
        return read(new File(c.getFilesDir(), fileName));
    }

    public static boolean writeFile(Context c, String fileName, String writeString) {
        return writeFile(c, fileName, writeString, false);
    }

    public static boolean writeFile(Context c, String fileName, String writeString, boolean append) {
        return write(new File(c.getFilesDir(), fileName), writeString, append);
    }

    /**
     * @param file File
     * @return 读取字符串
     */
    @Nullable
    public static String read(File file) {
        String result = "";
        FileInputStream fis = null;
        //noinspection SpellCheckingInspection
        ByteArrayOutputStream baos = null;
        try {
            fis = new FileInputStream(file);
            //new一个缓冲区
            byte[] buffer = new byte[256];
            //使用ByteArrayOutputStream类来处理输出流
            baos = new ByteArrayOutputStream();
            int length;
            //写入数据
            while ((length = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            result = new String(baos.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 保存字符串
     *
     * @param file        File
     * @param writeString String
     * @param append      false 覆盖 true 添加
     * @return 写入成功
     */
    public static boolean write(final File file, final String writeString, boolean append) {
        boolean result = false;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file, append);
            byte[] b = writeString.getBytes();
            fos.write(b);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * @param fileDirectory 文件夹
     * @param picName       文件名(含后缀)
     * @param bitmap        图片{@link Bitmap}
     * @return 保存成功
     */
    public static boolean saveBitmap(final String fileDirectory, final String picName, final Bitmap bitmap) {
        try {
            final File file = new File(fileDirectory, picName);
            if (bitmap == null) {
                return false;
            }
            if (file.exists()) {
                //noinspection ResultOfMethodCallIgnored
                file.delete();
            }
            boolean result = false;
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                if (picName.endsWith(".png") || picName.endsWith(".PNG")) {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    fos.flush();
                    result = true;
                } else if (picName.endsWith(".jpg") || picName.endsWith(".JPG") || picName.endsWith(".jpeg") || picName.endsWith(".JPEG")) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.flush();
                    result = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Bitmap getBitmap(final String fileDirectory, final String picName) {
        try {
            final File file = new File(fileDirectory, picName);
            return getBitmap(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Bitmap getBitmap(final File file) {
        Bitmap bitmap = null;
        try {
            if (file.exists()) {
                bitmap = BitmapFactory.decodeFile(file.getPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
