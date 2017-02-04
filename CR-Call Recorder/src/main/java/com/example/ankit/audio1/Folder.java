package com.example.ankit.audio1;

import android.os.Environment;

import java.io.File;

/**
 * Created by ankit on 3/2/17.
 */

public class Folder {
    static String name="CallRecorderTest";
    static File folder;
    static File getFolder(){

        folder = new File(Environment.getExternalStorageDirectory() + File.separator +name);
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        return folder;
    }
}
