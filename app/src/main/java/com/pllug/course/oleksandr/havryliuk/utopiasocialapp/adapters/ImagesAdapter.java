package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.Image;

import java.util.List;

public class ImagesAdapter extends BaseAdapter {
    private Context context;
    private List<Image> images;

    public ImagesAdapter(Context context, List<Image> images) {
        this.context = context;
        this.images = images;
    }

    public int getCount() {
        return images.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(2, 2, 2, 2);
        } else {
            imageView = (ImageView) convertView;
        }

       getImageFromUrl(imageView, images.get(position));
        return imageView;
    }

    public void getImageFromUrl(ImageView imageView, Image image){
        Glide.with(context)
                .load(image.getThumbnailUrl())
                .into(imageView);
    }
}
