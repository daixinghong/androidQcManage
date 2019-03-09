package com.sinano.utils;

import android.graphics.Bitmap;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {

    public static File bitmapToFile(Bitmap bitmap, File filepath){
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filepath));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filepath;
    }


}
