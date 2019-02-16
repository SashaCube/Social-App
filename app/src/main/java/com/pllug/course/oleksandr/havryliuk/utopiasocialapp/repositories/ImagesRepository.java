package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.repositories;

import android.content.Context;
import android.widget.GridView;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.adapters.ImagesAdapter;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.api.APIInterface;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.Image;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImagesRepository {

    private APIInterface client;
    private Context context;
    private GridView gridView;

    public ImagesRepository(APIInterface client, Context context, GridView gridView){
        this.client = client;
        this.context = context;
        this.gridView = gridView;
    }

    public void loadImages(){
        if(gridView == null)
            return;

        Call<List<Image>> call = client.getImage();
        call.enqueue(new Callback<List<Image>>() {
            @Override
            public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
                List<Image> images = response.body();
                gridView.setAdapter(new ImagesAdapter(context, images));
            }

            @Override
            public void onFailure(Call<List<Image>> call, Throwable t) {

            }
        });
    }

    public void loadImageById(final String id){
        if(gridView == null)
            return;

        Call<Image> call = client.getImageById(id);
        call.enqueue(new Callback<Image>() {
            @Override
            public void onResponse(Call<Image> call, Response<Image> response) {
                Image image = response.body();
                //set adapter of launch
            }

            @Override
            public void onFailure(Call<Image> call, Throwable t) {

            }
        });
    }
}
