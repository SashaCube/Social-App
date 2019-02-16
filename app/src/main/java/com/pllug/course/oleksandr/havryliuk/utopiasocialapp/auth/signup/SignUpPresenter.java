package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.auth.signup;

import android.text.TextUtils;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.auth.AuthenticationActivity;

public class SignUpPresenter implements SignUpContract.ISignUpPresenter {

    private SignUpFragment fragment;
    private SignUpContract.ISignUpView view;

    public SignUpPresenter(SignUpFragment fragment, SignUpContract.ISignUpView view){
        this.fragment = fragment;
        this.view = view;
    }
    @Override
    public void signUpClick() {
        validateInput();
    }

    @Override
    public void showSignIn() {
        ((AuthenticationActivity) fragment.getActivity()).showSignIn();
    }

    private void validateInput(){

        view.hideLoginError();
        view.hideEmailError();
        view.hidePasswordError();
        view.hideConfirmError();

        String login = view.getLogin();
        String email = view.getEmail();
        String password = view.getPassword();
        String confirmPassword = view.getConfirmPassword();

        if (TextUtils.isEmpty(login)) {
            view.showLoginError();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            view.showEmailError();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            view.showPasswordError();
            return;
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            view.showConfirmError();
            return;
        }

        if (!password.equals(confirmPassword)) {
            view.showConfirmError();
            return;
        }


        ((AuthenticationActivity) fragment.getActivity()).signUp(login, email, password);

    }
}
