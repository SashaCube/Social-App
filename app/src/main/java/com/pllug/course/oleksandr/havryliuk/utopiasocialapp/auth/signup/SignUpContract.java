package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.auth.signup;

import android.view.View;

public interface SignUpContract {

    interface ISignUpView{
        void init(View root);

        void setPresenter(ISignUpPresenter presenter);

        void hideLoginError();

        void showLoginError();

        void hideEmailError();

        void showEmailError();

        void showPasswordError();

        void hidePasswordError();

        void showConfirmError();

        void hideConfirmError();

        String getLogin();

        String getPassword();

        String getEmail();

        String getConfirmPassword();
    }

    interface ISignUpPresenter{
        void signUpClick();

        void showSignIn();
    }
}
