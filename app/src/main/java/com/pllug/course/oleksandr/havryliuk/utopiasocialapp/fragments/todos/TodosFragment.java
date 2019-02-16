package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.todos;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.R;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.adapters.TodosAdapter;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.api.APIInterface;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.api.RetrofitClient;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.Todo;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.repositories.PostsRepository;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.repositories.TodosRepository;

import java.util.ArrayList;
import java.util.List;

import co.dift.ui.SwipeToAction;

public class TodosFragment extends Fragment{

    private TodosContract.ITodosView view;
    private TodosContract.ITodosPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_todos, container, false);

        initView(root);
        initPresenter();

        return root;
    }

    public void initView(View root){
        view = new TodosView();
        view.init(root);
    }

    public void initPresenter(){
        presenter = new TodosPresenter(TodosRepository.getInstance(), view);
        view.setPresenter(presenter);
    }

}
