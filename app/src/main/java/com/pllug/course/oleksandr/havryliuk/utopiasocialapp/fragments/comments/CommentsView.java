package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.comments;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.R;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.adapters.CommentsAdapter;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.Comment;

import java.util.List;


public class CommentsView implements CommentsContract.ICommentsView, View.OnClickListener {

    private View root;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private CommentsContract.ICommentPresenter presenter;
    private CommentsAdapter adapter;

    @Override
    public void init(View root) {
        this.root = root;
        recyclerView = root.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void showComments(List<Comment> comments) {
        if(adapter == null){
            adapter = new CommentsAdapter(root.getContext(), comments, this);
            recyclerView.setAdapter(adapter);
        }else{
            adapter.setComments(comments);
        }
    }

    @Override
    public void setPresenter(CommentsContract.ICommentPresenter presenter) {
        this.presenter = presenter;
        presenter.loadComments();
    }

    @Override
    public void onClick(View v) {

    }
}
