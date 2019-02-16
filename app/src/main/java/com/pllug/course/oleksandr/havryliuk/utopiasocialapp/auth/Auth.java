package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.auth;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Auth {

    public final static int RC_SIGN_IN = 2;
    private static GoogleSignInClient googleSignInClient;
    private static CallbackManager callbackManager;

    public static void signIn(String email, String password, final Activity activity) {
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //if login success
                    Toast.makeText(getApplicationContext(), "Success Sign in", Toast.LENGTH_SHORT).show();
                    auth.getCurrentUser();
                    startMainActivity(activity);
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(getApplicationContext(), "Failed Sign in", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void signUp(String email, String password, final Activity activity) {
        //register user with Firebase
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "You are signed up.", Toast.LENGTH_SHORT).show();
                    sendSignUpConfirm(auth.getCurrentUser());
                    startMainActivity(activity);
                } else {
                    Toast.makeText(getApplicationContext(), "Failed sign up", Toast.LENGTH_SHORT).show();
                    //currentUser = null;
                }
            }
        });
    }

    private static void sendSignUpConfirm(FirebaseUser user) {
        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Email sent: success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Email isn't sent: error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void signOut() {
        if (googleSignInClient != null) {
            googleSignInClient.revokeAccess();
        }

        LoginManager.getInstance().logOut();

        FirebaseAuth.getInstance().signOut();
    }

    public static void facebookSignIn(final Activity activity) {
        //Facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        getCallbackManager();

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookToken(loginResult.getAccessToken(), activity);
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "User cancelled facebook login", Toast.LENGTH_SHORT);
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT);
            }
        });
    }

    //configure Facebook
    private static void handleFacebookToken(AccessToken accessToken, final Activity activity) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithCredential(credential).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(getApplicationContext(), "Authentication success.(Firebase)",
                            Toast.LENGTH_SHORT).show();
                    auth.getCurrentUser();
                    startMainActivity(activity);
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(getApplicationContext(), "Authentication failed.(Firebase)",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void googleSignIn(Activity activity) {
        GoogleSignInClient googleSignInClient = getGoogleClient();

        Intent signInIntent = googleSignInClient.getSignInIntent();
        activity.startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public static void sendRecoverCode(String email) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Email sent: success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Email not sent: error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private static void firebaseAuthWithGoogle(GoogleSignInAccount acct,
                                               final Activity activity) {
        //connecting to firebase
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getApplicationContext(), "Google authinication success", Toast.LENGTH_SHORT).show();
                            auth.getCurrentUser();
                            startMainActivity(activity);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Google authinication failed(Firebase part)", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public static void onActivityResult(int requestCode, int resultCode, Intent data,
                                        Activity activity) {
        //facebook callback
        getCallbackManager().onActivityResult(requestCode, resultCode, data);

        //google responce
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Auth.firebaseAuthWithGoogle(account, activity);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(getApplicationContext(), "Google authinication failed(Google part)", Toast.LENGTH_SHORT).show();
                // ...
            }
        }
    }

    public static boolean isUserAuth() {
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            return false;
        }
        return true;
    }

    public static GoogleSignInClient getGoogleClient() {
        if (googleSignInClient == null) {
            //Google
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getApplicationContext().getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();

            googleSignInClient = GoogleSignIn.getClient(getApplicationContext(), gso);
        }
        return googleSignInClient;
    }

    public static CallbackManager getCallbackManager() {
        if (callbackManager == null) {
            callbackManager = CallbackManager.Factory.create();
        }
        return callbackManager;
    }

    public static void startMainActivity(Activity activity) {
        ((AuthenticationActivity) activity).startMainActivity();
    }
}
