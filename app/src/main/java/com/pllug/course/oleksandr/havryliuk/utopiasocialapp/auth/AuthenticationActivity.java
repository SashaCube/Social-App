package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.MainActivity;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.R;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.Utils;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.auth.forgotpassword.ForgotPasswordFragment;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.auth.signin.SignInFragment;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.auth.signup.SignUpFragment;

public class AuthenticationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        if (Auth.isUserAuth()) {
            startMainActivity();
        } else {
            showSignIn();
        }
    }

    public void showSignIn() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (Utils.isFragmentInBackstack(getSupportFragmentManager(),
                SignInFragment.class.getName())) {
            getSupportFragmentManager().popBackStackImmediate(SignInFragment.class.getName(), 0);
        } else {
            Fragment fragment = new SignInFragment();
            transaction.replace(R.id.fragment, fragment)
                    .addToBackStack(SignInFragment.class.getName())
                    .commit();
        }
    }

    public void showSignUp() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (Utils.isFragmentInBackstack(getSupportFragmentManager(),
                SignUpFragment.class.getName())) {
            getSupportFragmentManager().popBackStackImmediate(SignUpFragment.class.getName(), 0);
        } else {
            Fragment fragment = new SignUpFragment();
            transaction.replace(R.id.fragment, fragment)
                    .addToBackStack(SignUpFragment.class.getName())
                    .commit();
        }
    }

    public void showForgotPassword() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (Utils.isFragmentInBackstack(getSupportFragmentManager(),
                ForgotPasswordFragment.class.getName())) {
            getSupportFragmentManager().popBackStackImmediate(ForgotPasswordFragment.class.getName(), 0);
        } else {
            Fragment fragment = new ForgotPasswordFragment();
            transaction.replace(R.id.fragment, fragment)
                    .addToBackStack(ForgotPasswordFragment.class.getName())
                    .commit();
        }
    }

    public void signIn(String email, String password) {
        Auth.signIn(email, password, this);
    }

    public void signUp(String login, String email, String password) {
        Auth.signUp(email, password, this);
    }

    public void googleSignIn() {
        Auth.googleSignIn(this);
    }

    public void facebookSignIn() {
        Auth.facebookSignIn(this);
    }

    public void sendRecoverCode(String email) {
        Auth.sendRecoverCode(email);
    }

    public void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Auth.onActivityResult(requestCode, resultCode, data, this);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
