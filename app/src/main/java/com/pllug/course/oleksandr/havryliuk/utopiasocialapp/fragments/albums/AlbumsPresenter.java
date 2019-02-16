package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.albums;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.Utils;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.Album;

import java.util.List;

public class AlbumsPresenter implements AlbumsContract.IAlbumsPresenter {

    private AlbumsContract.IAlbumsView view;
    private AlbumsContract.IAlbumsModel model;

    public AlbumsPresenter(AlbumsContract.IAlbumsModel model, AlbumsContract.IAlbumsView view){
        this.view = view;
        this.model = model;
    }

    @Override
    public void loadAlbums() {
        model.loadAlbums(new Utils.LoadData<List<Album>>() {
            @Override
            public void onData(List<Album> data) {
                view.showAlbums(data);
            }

            @Override
            public void onFailure() {

            }
        });
    }
}
