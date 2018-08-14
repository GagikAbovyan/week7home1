package com.example.student.week7homework1.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.student.week7homework1.models.ImageDownload;
import com.example.student.week7homework1.activities.MainActivity;
import com.example.student.week7homework1.R;
import com.example.student.week7homework1.providers.DataProvider;

import java.util.List;

public class DownloadAdapter extends RecyclerView.Adapter<DownloadAdapter.DownloadViewHolder>{

    private final Context context;
    private final List<ImageDownload> list;

    public DownloadAdapter(final Context context, final List<ImageDownload> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DownloadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
        return new DownloadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DownloadViewHolder holder, final int position) {
        final String image[] = context.getResources().getStringArray(R.array.items);
        holder.textViewItem.setText(image[position]);
        itemClick(holder, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class DownloadViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewItem;
        @SuppressLint("CutPasteId")
        DownloadViewHolder(View itemView) {
            super(itemView);
            textViewItem = itemView.findViewById(R.id.item);
        }
    }

    private void itemClick(final DownloadViewHolder holder, final int position){
        holder.textViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataProvider.setPosition(position);
                ((MainActivity)context).updateText(position);
            }
        });
    }

}
