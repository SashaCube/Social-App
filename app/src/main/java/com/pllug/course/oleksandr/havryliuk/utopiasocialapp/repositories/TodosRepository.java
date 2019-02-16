package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.repositories;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.Utils;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.api.APIInterface;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.api.RetrofitClient;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.todos.TodosContract;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.Todo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodosRepository implements TodosContract.ITodosModel {
    private static TodosRepository INSTANCE;
    private APIInterface api;

    public static TodosRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TodosRepository();
            INSTANCE.api = RetrofitClient.getApi();
        }
        return INSTANCE;
    }

    @Override
    public void loadTodos(final Utils.LoadData<List<Todo>> callback) {

        api.getTodos().enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                if (response.body() == null) {
                    callback.onFailure();
                    return;
                }
                callback.onData(response.body());
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                callback.onFailure();
            }
        });
    }

    @Override
    public void add(int pos, Todo todo) {
        //add to repository
    }

    @Override
    public int remove(Todo todo) {
        //delete from repository
        return 0;
    }

    @Override
    public void complete(Todo todo) {
        //update in repository
    }
}
