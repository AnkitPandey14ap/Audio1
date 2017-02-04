package com.example.ankit.audio1;

import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.IOException;

/**
 * Created by ankit on 26/1/17.
 */


public class CallRecorder {
    MediaRecorder recorder;
    private String mFileName;

    CallRecorder(){
        mFileName = Folder.getFolder().toString();
    }

    void startRecording(int count) throws IOException {
        this.mFileName = mFileName;
        recorder=new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        //recorder.setOutputFile("/abc.3gp");
        Log.i("Ankit123", String.valueOf(count));
        mFileName += "/audio00"+count+".m4a";
        recorder.setOutputFile(mFileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        recorder.prepare();
        recorder.start();
    }

    void stopRecording() {
        recorder.stop();
        recorder = null;
    }

    ;
}
