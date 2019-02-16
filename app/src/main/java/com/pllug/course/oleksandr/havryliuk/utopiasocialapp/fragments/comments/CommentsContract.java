package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.comments;

import android.view.View;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.Utils;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.Comment;

import java.util.List;

public interface CommentsContract {
    interface ICommentsView {

        void init(View root);

        void showComments(List<Comment> comments);

        void setPresenter(ICommentPresenter presenter);

    }

    interface ICommentPresenter {

        void loadComments();

    }

    interface ICommentModel {

        void loadComments(final Utils.LoadData<List<Comment>> callback);

    }

}
