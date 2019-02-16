package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.profile;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.R;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.api.APIInterface;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.api.RetrofitClient;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.auth.forgotpassword.ForgotPasswordPresenter;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.auth.forgotpassword.ForgotPasswordView;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.repositories.UsersRepository;

public class ProfileFragment extends Fragment {

    private ProfileContract.IProfileView view;
    private ProfileContract.IProfilePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        initView(root);
        initPresenter();

        return root;
    }

    private void initView(View root) {
        view = new ProfileView();
        view.init(root);
    }

    private void initPresenter() {
        presenter = new ProfilePresenter(UsersRepository.getInstance(), view);
        view.setPresenter(presenter);
    }
}
