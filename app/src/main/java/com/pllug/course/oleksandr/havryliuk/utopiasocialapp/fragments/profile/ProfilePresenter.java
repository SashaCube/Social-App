package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.profile;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.Utils;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.User;

public class ProfilePresenter implements ProfileContract.IProfilePresenter {

    private ProfileContract.IProfileView view;
    private ProfileContract.IProfileModel model;

    public ProfilePresenter(ProfileContract.IProfileModel model,
                            ProfileContract.IProfileView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void loadUser() {
       model.loadUser(new Utils.LoadData<User>() {
           @Override
           public void onData(User data) {
               view.showUser(data);
           }

           @Override
           public void onFailure() {

           }
       });
    }

    @Override
    public void infoFabClick() {
        view.showMoreInfo();
    }

    @Override
    public void infoClick() {
        view.hideMoreInfo();
    }
}
