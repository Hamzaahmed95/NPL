package com.example.hamzaahmed.npl;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.fragment;
import static android.R.attr.targetActivity;

/**
 * Created by Hamza Ahmed on 14-Jul-17.
 */

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

    private ArrayList<Image> galleryList;
    Dialog dialog;
    private Context context;
    private List<Image> l1;
    String[] array1 = new String[10];

    public ImagesAdapter(Context context, ArrayList<Image> galleryList) {
        this.galleryList = galleryList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_image, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImagesAdapter.ViewHolder viewHolder, int i) {
        // viewHolder.title.setText(galleryList.get(i).getImage_title());
        Image image = galleryList.get(i);
        viewHolder.bindDeals(image, i);
    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Button title;
        private ImageView img;
        int pos1;
        private Image image1;

        public ViewHolder(View view) {
            super(view);
            //title = (Button) view.findViewById(R.id.title);
            img = (ImageView) view.findViewById(R.id.img);
            view.setOnClickListener(this);
        }

        public void bindDeals(Image image, int pos) {
            image1 = image;
            //    System.out.println("imageid"+image1.getImage_ID());
            Glide.with(img.getContext())
                    .load(image1.getImage_ID())
                    .into(img);
            pos1 = pos;
            System.out.println("pos: " + pos1);
        }

        @Override
        public void onClick(View view) {
            // Toast.makeText(context, "hello"+image1.getId(), Toast.LENGTH_SHORT).show();
            System.out.println(pos1);
            System.out.println("imageid" + image1.getImage_ID());
            /*for (int i=0;i<getItemCount();i++){
                array1[i]=image1.getImage_ID().toString();
            }
            for (int i=0;i<getItemCount();i++){
                System.out.println(array1[i]=image1.getImage_ID().toString());
            }*/
            showDialog(image1.getImage_ID());

        }

        private void showDialog(String url) {
            // custom dialog
            dialog = new Dialog(context);
            dialog.setContentView(R.layout.text);

            // set the custom dialog components - text, image and button

            // Close Button

            // Buy Button

            ImageView v1 = (ImageView) dialog.findViewById(R.id.janbaz);

            Glide.with(v1.getContext())
                  .load(url)
                .into(v1);


            ImageButton Close = (ImageButton) dialog.findViewById(R.id.close1);
            Close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dialog.dismiss();
                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }
    }
}
