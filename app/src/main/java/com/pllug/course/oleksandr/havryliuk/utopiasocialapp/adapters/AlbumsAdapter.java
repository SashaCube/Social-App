package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.R;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.Utils;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.Album;

import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.ViewHolder> {

    private Context context;
    private List<Album> albums;
    private View.OnClickListener mOnClickListener;

    public AlbumsAdapter(Context context, List<Album> albums, View.OnClickListener onClickListener) {
        this.albums = albums;
        this.context = context;
        this.mOnClickListener = onClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_album, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final ViewHolder vh = viewHolder;

        Album album = albums.get(i);

        vh.title.setText(album.getTitle());
        vh.amountImages.setText(Utils.getRandom(200) + " images");
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, amountImages;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            amountImages = itemView.findViewById(R.id.amount_images_tv);
        }
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
        notifyDataSetChanged();
    }
}
