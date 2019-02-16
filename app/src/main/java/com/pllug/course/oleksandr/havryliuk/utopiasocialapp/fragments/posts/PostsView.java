package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.posts;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.R;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.adapters.PostsAdapter;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.Post;

import java.util.List;

public class PostsView implements PostsContract.IPostsView, View.OnClickListener {

    private View root;
    private RecyclerView recyclerView;
    private PostsAdapter adapter;
    private PostsContract.IPostsPresenter presenter;

    @Override
    public void showPosts(List<Post> posts) {
        if (adapter == null) {
            adapter = new PostsAdapter(root.getContext(), posts, this);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setPosts(posts);
        }
    }

    @Override
    public void init(View root) {
        this.root = root;
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void setPresenter(PostsContract.IPostsPresenter presenter) {
        this.presenter = presenter;
        presenter.loadPosts();
    }

    @Override
    public void onClick(View v) {

    }
}
