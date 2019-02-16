package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.repositories;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.Utils;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.api.APIInterface;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.api.RetrofitClient;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.albums.AlbumsContract;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumsRepository implements AlbumsContract.IAlbumsModel {
    private static AlbumsRepository INSTANCE;
    private APIInterface api;

    public static AlbumsRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AlbumsRepository();
            INSTANCE.api = RetrofitClient.getApi();
        }
        return INSTANCE;
    }

    @Override
    public void loadAlbums(final Utils.LoadData<List<Album>> callback) {

        api.getAlbums().enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                if (response.body() == null) {
                    callback.onFailure();
                    return;
                }
                callback.onData(response.body());
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                callback.onFailure();
            }
        });
    }
}
