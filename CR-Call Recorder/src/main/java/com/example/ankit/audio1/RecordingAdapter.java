package com.example.ankit.audio1;

import android.content.Context;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by ankit on 26/1/17.
 */
public class RecordingAdapter extends RecyclerView.Adapter<RecordingAdapter.ViewHolder> {
    private final ArrayList<File> recordings;
    private LayoutInflater layoutInflater;

    public RecordingAdapter(Context context, ArrayList<File> recordings) {
        layoutInflater=LayoutInflater.from(context);
        this.recordings = recordings;
    }

    @Override
    public RecordingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.string_layout,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final RecordingAdapter.ViewHolder holder, int position) {
        final String fileName=recordings.get(position).getName();
        holder.nameTextView.setText(recordings.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayRecords.play(Folder.getFolder().toString()+"/"+fileName);
                Log.i("Ankit",Folder.getFolder().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return recordings.size();
    }

    static public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView= (TextView) itemView.findViewById(R.id.nameTextView);
        }
    }
}
