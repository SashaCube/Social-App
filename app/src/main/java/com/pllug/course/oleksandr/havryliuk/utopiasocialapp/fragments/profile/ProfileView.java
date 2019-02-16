package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.profile;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.R;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.adapters.UsersAdapter;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.User;

public class ProfileView implements ProfileContract.IProfileView, View.OnClickListener {

    private View root;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ProfileContract.IProfilePresenter presenter;
    private UsersAdapter adapter;
    private User user;

    public void init(View root) {
        this.root = root;
        recyclerView = root.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
    }

    public void setPresenter(ProfileContract.IProfilePresenter presenter) {
        this.presenter = presenter;
        presenter.loadUser();
    }

    public void showUser(User user) {
        this.user = user;
        if (adapter == null) {
            adapter = new UsersAdapter(root.getContext(), user, this);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setUser(user);
        }
    }

    @Override
    public void showMoreInfo() {
        adapter.secondaryView();
    }

    @Override
    public void hideMoreInfo() {
        adapter.mainView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.location_layout:
                locationClick();
                break;
            case R.id.website_image:
                websiteClick();
                break;
            case R.id.call_image:
                callClick();
                break;
            case R.id.email_image:
                emailClick();
                break;
            case R.id.fab_reveal_layout:
                presenter.infoFabClick();
            case R.id.secondary_view:
                presenter.infoClick();
        }
    }

    public void locationClick() {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("google.navigation:q=" + user.getAddress().getCity()));
        root.getContext().startActivity(intent);
    }

    public void websiteClick() {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com.ua"));
        root.getContext().startActivity(intent);
    }

    public void callClick() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + user.getPhone()));
        if (intent.resolveActivity(root.getContext().getPackageManager()) != null) {
            root.getContext().startActivity(intent);
        }

    }

    public void emailClick() {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, user.getEmail());
        intent.putExtra(Intent.EXTRA_SUBJECT, "Social App");
        intent.putExtra(Intent.EXTRA_TEXT, "I'm emailClick body.");
        root.getContext().startActivity(intent);
    }


}
