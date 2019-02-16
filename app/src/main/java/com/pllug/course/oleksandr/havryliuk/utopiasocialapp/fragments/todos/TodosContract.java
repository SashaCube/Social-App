package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.todos;

import android.view.View;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.Utils;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.Todo;

import java.util.List;

public interface TodosContract {

    interface ITodosView{

        void init(View root);

        void setPresenter(ITodosPresenter presenter);

        void showTodos(List<Todo> todos);


    }

    interface ITodosPresenter {

        void loadTodos();

        void addSwipe(int pos, Todo todo);

       int  removeSwipe(Todo todo);

       void complete(Todo todo);

    }

    interface ITodosModel{

        void loadTodos(final Utils.LoadData<List<Todo>> callback);

        void add(int pos, Todo todo);

        int remove(Todo todo);

        void complete(Todo todo);
    }
}
