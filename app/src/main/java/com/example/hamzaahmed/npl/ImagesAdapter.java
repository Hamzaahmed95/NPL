package com.example.hamzaahmed.npl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Hamza Ahmed on 14-Jul-17.
 */

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {
    private ArrayList<Image> galleryList;
    private Context context;

    public ImagesAdapter(Context context, ArrayList<Image> galleryList) {
        this.galleryList = galleryList;
        this.context = context;
    }

    @Override
    public ImagesAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_image, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImagesAdapter.ViewHolder viewHolder, int i) {
        // viewHolder.title.setText(galleryList.get(i).getImage_title());
        viewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        viewHolder.img.setImageResource((galleryList.get(i).getImage_ID()));
    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Button title;
        private ImageView img;
        public ViewHolder(View view) {
            super(view);

            //title = (Button) view.findViewById(R.id.title);
            img = (ImageView) view.findViewById(R.id.img);
        }
        @Override
        public void onClick(View view) {
            Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show();
        }
    }
}
