package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.todos;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.Utils;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.Todo;

import java.util.List;

public class TodosPresenter implements TodosContract.ITodosPresenter {

    private TodosContract.ITodosView view;
    private TodosContract.ITodosModel model;

    public TodosPresenter(TodosContract.ITodosModel model, TodosContract.ITodosView view){
        this.view = view;
        this.model = model;
    }

    @Override
    public void loadTodos() {
        model.loadTodos(new Utils.LoadData<List<Todo>>() {
            @Override
            public void onData(List<Todo> data) {
                view.showTodos(data);
            }

            @Override
            public void onFailure() {

            }
        });
    }

    @Override
    public void addSwipe(int pos, Todo todo) {
        model.add(pos, todo);
    }

    @Override
    public int removeSwipe(Todo todo) {
        return model.remove(todo);
    }

    @Override
    public void complete(Todo todo) {
        model.complete(todo);
    }
}
