package com.example.ankit.audio1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;


public class MainActivityRecording extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 20;
    boolean flag=true;

    CallRecorder callRecorder ;
    private Button recordButton;
    private Button listButton;
    private static String mFileName=null;
    Database database;

    PlayRecords playRecords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recording);


        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                Log.i("Ankit", "inner if");

            } else {
                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                Log.i("Ankit", "requsted READ PHONE_STATE");


            }
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECORD_AUDIO)) {

                Log.i("Ankit", "inner if");

            } else {
                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                Log.i("Ankit", "requsted READ PHONE_STATE");


            }
        }




        database = new Database(this);
        database.open();

        database.readRecord();



        recordButton = (Button) findViewById(R.id.recordButton);
        listButton= (Button) findViewById(R.id.listButton);
        recordButton.setText("Start Recording");
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }

        });
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(this, RecordingList.class);

                Intent intent=new Intent(MainActivityRecording.this, RecordingList.class);
                startActivity(intent);
            }
        });

    }

    /*private void play() {
        int count=1;
        mFileName = Folder.getFolder().toString();
        mFileName += "/audio00"+count+".m4a";
        playRecords = new PlayRecords();
        playRecords.play(mFileName);

    }*/

    void start(){
        if(flag==true){
            callRecorder= new CallRecorder();
            recordButton.setText("Stop Recording");
            flag=false;
            try {
                callRecorder.startRecording(database.readRecord()+1);
                database.insertRecord("Audio");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            callRecorder.stopRecording();
            recordButton.setText("Start Recording");
            flag=true;


        }
    }


}
