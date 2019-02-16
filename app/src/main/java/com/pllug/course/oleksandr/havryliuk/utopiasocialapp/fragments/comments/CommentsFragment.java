package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.comments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.R;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.repositories.CommentsRepository;

public class CommentsFragment extends Fragment {

    private CommentsContract.ICommentsView view;
    private CommentsContract.ICommentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_comments, container, false);

        initView(root);
        initPresenter();

        return root;
    }

    public void initView(View root) {
       view = new CommentsView();
       view.init(root);
    }

    public void initPresenter(){
        presenter = new CommentsPresenter(CommentsRepository.getInstance(), view);
        view.setPresenter(presenter);
    }
}
