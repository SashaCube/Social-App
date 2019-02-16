package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.albums;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.R;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.adapters.AlbumsAdapter;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.Album;

import java.util.List;

public class AlbumsView implements AlbumsContract.IAlbumsView, View.OnClickListener {

    private View root;
    private RecyclerView recyclerView;
    private AlbumsAdapter adapter;
    private AlbumsContract.IAlbumsPresenter presenter;

    @Override
    public void init(View root) {
        this.root = root;
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void setPresenter(AlbumsContract.IAlbumsPresenter presenter) {
        this.presenter = presenter;
        presenter.loadAlbums();
    }

    @Override
    public void showAlbums(List<Album> albums) {
        if (adapter == null) {
            adapter = new AlbumsAdapter(root.getContext(), albums, this);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setAlbums(albums);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
