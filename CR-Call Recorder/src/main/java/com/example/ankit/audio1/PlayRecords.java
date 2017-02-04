package com.example.ankit.audio1;

import android.media.MediaDataSource;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;

import java.io.IOException;

/**
 * Created by ankit on 26/1/17.
 */

public class PlayRecords {
    static void play(String mFileName){
        MediaPlayer mediaPlayer=new MediaPlayer();
        try {
            mediaPlayer.setDataSource(mFileName);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*void pause() {
        mediaPlayer.pause();
    }
    void resume(){
        mediaPlayer.start();
    }*/
}
