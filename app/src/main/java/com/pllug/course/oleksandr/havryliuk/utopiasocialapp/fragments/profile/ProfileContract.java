package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.profile;

import android.view.View;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.Utils;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.User;

public interface ProfileContract {
    interface IProfileView{
        void init(View root);

        void setPresenter(IProfilePresenter presenter);

        void showUser(User user);

        void showMoreInfo();

        void hideMoreInfo();
    }

    interface IProfilePresenter{
        void loadUser();

        void infoFabClick();

        void infoClick();
    }


    interface IProfileModel {

        void loadUser(final Utils.LoadData<User> callback);

    }
}
