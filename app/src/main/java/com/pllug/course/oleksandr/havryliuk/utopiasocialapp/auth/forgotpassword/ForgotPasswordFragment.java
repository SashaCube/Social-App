package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.auth.forgotpassword;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.R;


public class ForgotPasswordFragment extends Fragment {

    private View root;
    private ForgotPasswordPresenter  presenter;
    private ForgotPasswordView view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        initView(root);
        initPresenter();

        return root;
    }

    private void initView(View root) {
        view = new ForgotPasswordView();
        view.init(root);
    }

    private void initPresenter() {
        presenter = new ForgotPasswordPresenter(this, view);
        view.setPresenter(presenter);
    }
}
