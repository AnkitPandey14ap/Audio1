package com.example.ankit.audio1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

//import static com.example.ankit.audio1.R.id.moviesRecyckerView;

public class RecordingList extends AppCompatActivity {

    private RecyclerView recordingRecyclerView;
    private File mFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recording_list);

        ArrayList<File> recordings = new ArrayList<File>();

        mFileName = Folder.getFolder();

            /*String secStore = System.getenv("SECONDARY_STORAGE");
            mFileName  = new File(secStore);
*/
        //mFileName += "/audio001.m4a";
        //Recordings.add(mFileName);
        recordings=findSongs(mFileName);


        recordingRecyclerView = (RecyclerView) findViewById(R.id.recordingRecyclerView);
        LinearLayoutManager layoutManger=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recordingRecyclerView.setLayoutManager(layoutManger);
        RecordingAdapter recordingAdapter=new RecordingAdapter(this,recordings);
        recordingRecyclerView.setAdapter(recordingAdapter);
    }


    public ArrayList<File> findSongs(File root) {
        ArrayList<File> al = new ArrayList<File>();
        File[] files = root.listFiles();
        for (File singleFile : files){
            if(singleFile.isDirectory() && !singleFile.isHidden())
                al.addAll(findSongs(singleFile));
            else{
                if (singleFile.getName().endsWith(".m4a")){
                    al.add(singleFile);
                }
            }

        }

        return al;
    }
}
