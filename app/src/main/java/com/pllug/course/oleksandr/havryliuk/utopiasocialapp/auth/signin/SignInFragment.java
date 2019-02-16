package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.auth.signin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.R;

public class SignInFragment extends Fragment {

    private View root;

    private SignInContract.ISignInView view;
    private SignInContract.ISignInPresenter presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_signin, container, false);

        initView(root);
        initPresenter();

        return root;
    }

    private void initView(View root) {
        view = new SignInView();
        view.init(root);
    }

    private void initPresenter() {
        presenter = new SignInPresenter(view, this);
        view.setPresenter(presenter);
    }

}