package com.example.student.week7homework1.dialogs;

import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.student.week7homework1.R;
import com.example.student.week7homework1.models.ImageDownload;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageDialog extends DialogFragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_dialog, container, false);
        final int position = getArguments().getInt(getString(R.string.my_key));
        final List<ImageDownload> list = (List<ImageDownload>) getArguments().getSerializable(getString(R.string.my_list_key));
        final ImageView imageView = view.findViewById(R.id.image_dilaog);
        final String url = list.get(position).getHttp();
        Picasso.get().load(Uri.parse(url)).into(imageView);
        closeButton(view);
        return view;
    }

    private void closeButton(final View view){
        final ImageButton closeButton = view.findViewById(R.id.close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
