package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.albums;

import android.view.View;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.Utils;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.Album;

import java.util.List;

public interface AlbumsContract {

    interface IAlbumsView {

        void init(View view);

        void setPresenter(IAlbumsPresenter presenter);

        void showAlbums(List<Album> albums);

    }

    interface IAlbumsPresenter {

        void loadAlbums();

    }

    interface IAlbumsModel {

        void loadAlbums(final Utils.LoadData<List<Album>> albums);

    }
}
