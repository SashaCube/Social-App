package com.pllug.course.oleksandr.havryliuk.utopiasocialapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.auth.Auth;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.auth.AuthenticationActivity;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.albums.AlbumsFragment;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.comments.CommentsFragment;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.images.ImagesFragment;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.posts.PostsFragment;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.profile.ProfileFragment;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.settings.SettingsFragment;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.todos.TodosFragment;

import io.saeid.fabloading.LoadingView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private LoadingView mLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();
        initLoading();
        initMenu();
        profile();
    }

    private void requestPermission() {
        int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
        }
    }



    public void initLoading() {

        mLoadingView = findViewById(R.id.loading_view);
        final FrameLayout frameLayout = findViewById(R.id.content_frame);
        mLoadingView.addAnimation(R.color.dark_sky_blue, R.color.dark_sky_blue, LoadingView.FROM_BOTTOM);
        mLoadingView.addAnimation(R.color.diamond, R.color.diamond, LoadingView.FROM_LEFT);
        mLoadingView.addAnimation(R.color.independence, R.color.independence, LoadingView.FROM_TOP);
        mLoadingView.addAnimation(R.color.slate_gray, R.color.slate_gray, LoadingView.FROM_RIGHT);

        mLoadingView.addListener(new LoadingView.LoadingListener() {
            @Override
            public void onAnimationStart(int currentItemPosition) {
            }

            @Override
            public void onAnimationRepeat(int nextItemPosition) {
                if (nextItemPosition == 1) {
                    frameLayout.setVisibility(View.VISIBLE);
                    mLoadingView.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(int nextItemPosition) {
            }
        });

        mLoadingView.startAnimation();
    }

    public void initMenu() {
        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();


                        switch (menuItem.getItemId()) {
                            case R.id.nav_profile:
                                profile();
                                break;
                            case R.id.nav_posts:
                                posts();
                                break;
                            case R.id.nav_comments:
                                comments();
                                break;
                            case R.id.nav_albums:
                                albums();
                                break;
                            case R.id.nav_images:
                                images();
                                break;
                            case R.id.nav_todos:
                                todos();
                                break;
                            case R.id.nav_sign_out:
                                signOut();
                            case R.id.nav_settings:
                                settings();
                                break;
                            default:
                        }
                        return true;
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void signOut() {
        Auth.signOut();
        Intent intent = new Intent(this, AuthenticationActivity.class);
        startActivity(intent);
        finish();
    }

    public void profile() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (Utils.isFragmentInBackstack(getSupportFragmentManager(),
                ProfileFragment.class.getName())) {
            getSupportFragmentManager().popBackStackImmediate(ProfileFragment.class.getName(), 0);
        } else {
            Fragment fragment = new ProfileFragment();
            transaction.replace(R.id.fragment, fragment)
                    .addToBackStack(ProfileFragment.class.getName())
                    .commit();
        }
    }

    public void posts() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (Utils.isFragmentInBackstack(getSupportFragmentManager(),
                PostsFragment.class.getName())) {
            getSupportFragmentManager().popBackStackImmediate(PostsFragment.class.getName(), 0);
        } else {
            Fragment fragment = new PostsFragment();
            transaction.replace(R.id.fragment, fragment)
                    .addToBackStack(PostsFragment.class.getName())
                    .commit();
        }
    }

    public void comments() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (Utils.isFragmentInBackstack(getSupportFragmentManager(),
                CommentsFragment.class.getName())) {
            getSupportFragmentManager().popBackStackImmediate(CommentsFragment.class.getName(), 0);
        } else {
            Fragment fragment = new CommentsFragment();
            transaction.replace(R.id.fragment, fragment)
                    .addToBackStack(CommentsFragment.class.getName())
                    .commit();
        }
    }

    public void albums() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (Utils.isFragmentInBackstack(getSupportFragmentManager(),
                AlbumsFragment.class.getName())) {
            getSupportFragmentManager().popBackStackImmediate(AlbumsFragment.class.getName(), 0);
        } else {
            Fragment fragment = new AlbumsFragment();
            transaction.replace(R.id.fragment, fragment)
                    .addToBackStack(AlbumsFragment.class.getName())
                    .commit();
        }
    }

    public void images() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (Utils.isFragmentInBackstack(getSupportFragmentManager(),
                ImagesFragment.class.getName())) {
            getSupportFragmentManager().popBackStackImmediate(ImagesFragment.class.getName(), 0);
        } else {
            Fragment fragment = new ImagesFragment();
            transaction.replace(R.id.fragment, fragment)
                    .addToBackStack(ImagesFragment.class.getName())
                    .commit();
        }
    }

    public void todos() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (Utils.isFragmentInBackstack(getSupportFragmentManager(),
                TodosFragment.class.getName())) {
            getSupportFragmentManager().popBackStackImmediate(TodosFragment.class.getName(), 0);
        } else {
            Fragment fragment = new TodosFragment();
            transaction.replace(R.id.fragment, fragment)
                    .addToBackStack(TodosFragment.class.getName())
                    .commit();
        }
    }

    public void settings() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (Utils.isFragmentInBackstack(getSupportFragmentManager(),
                SettingsFragment.class.getName())) {
            getSupportFragmentManager().popBackStackImmediate(SettingsFragment.class.getName(), 0);
        } else {
            Fragment fragment = new SettingsFragment();
            transaction.replace(R.id.fragment, fragment)
                    .addToBackStack(SettingsFragment.class.getName())
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
