package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.repositories;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.Utils;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.api.APIInterface;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.api.RetrofitClient;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.profile.ProfileContract;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersRepository implements ProfileContract.IProfileModel {

    private static UsersRepository INSTANCE;
    private APIInterface api;

    public static UsersRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UsersRepository();
            INSTANCE.api = RetrofitClient.getApi();
        }
        return INSTANCE;
    }

    @Override
    public void loadUser(final Utils.LoadData<User> callback) {

        api.getUserById("1").enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() == null) {
                    callback.onFailure();
                    return;
                }
                callback.onData(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onFailure();
            }
        });
    }
}
