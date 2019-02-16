package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.auth.signin;

import android.text.TextUtils;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.auth.AuthenticationActivity;

public class SignInPresenter implements SignInContract.ISignInPresenter {

    private SignInContract.ISignInView view;
    private SignInFragment fragment;

    public SignInPresenter(SignInContract.ISignInView view, SignInFragment fragment) {
        this.view = view;
        this.fragment = fragment;
    }

    @Override
    public void signInClick() {
        validateInput();
    }


    @Override
    public void googleSignInClick()
    {
        ((AuthenticationActivity) fragment.getActivity()).googleSignIn();
    }

    @Override
    public void facebookSignInClick(){
        ((AuthenticationActivity) fragment.getActivity()).facebookSignIn();
    }

    private void validateInput() {
        view.hideLoginError();
        view.hidePasswordError();

        String login = view.getLoginText();
        String password = view.getPasswordText();
        if (TextUtils.isEmpty(login)) {
            view.showLoginError();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            view.showPasswordError();
            return;
        }

        ((AuthenticationActivity) fragment.getActivity()).signIn(login, password);
    }

    @Override
    public void showSignUp() {
        ((AuthenticationActivity) fragment.getActivity()).showSignUp();
    }

    @Override
    public void showForgotPassword() {
        ((AuthenticationActivity) fragment.getActivity()).showForgotPassword();
    }
}