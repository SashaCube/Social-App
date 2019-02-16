package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.auth.signup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.R;

public class SignUpFragment extends Fragment {

    private SignUpContract.ISignUpView view;
    private SignUpContract.ISignUpPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_signup, container, false);

        initView(root);
        initPresenter();

        return root;
    }

    private void initView(View root) {
        view = new SignUpView();
        view.init(root);
    }

    private void initPresenter() {
        presenter = new SignUpPresenter(this, view);
        view.setPresenter(presenter);
    }
}


