package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.todos;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.R;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.adapters.TodosAdapter;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.Todo;

import java.util.List;

import co.dift.ui.SwipeToAction;

public class TodosView implements TodosContract.ITodosView,
        SwipeToAction.SwipeListener<Todo>, View.OnClickListener {

    private View root;
    private RecyclerView recyclerView;
    private TodosAdapter adapter;
    private TodosContract.ITodosPresenter presenter;

    @Override
    public void init(View root) {
        this.root = root;
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setHasFixedSize(true);

        SwipeToAction swipeToAction = new SwipeToAction(recyclerView, this);
    }

    @Override
    public void setPresenter(TodosContract.ITodosPresenter presenter) {
        this.presenter = presenter;
        presenter.loadTodos();
    }

    @Override
    public void showTodos(List<Todo> todos) {
        if (adapter == null) {
            adapter = new TodosAdapter(root.getContext(), todos, this);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setTodos(todos);
        }
    }

    private void setCompleted(Todo todo) {
        if (todo.getCompleted() == false) {
            todo.setCompleted(true);
        } else {

            todo.setCompleted(false);
        }
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public boolean swipeLeft(final Todo itemData) {
        final int pos = presenter.removeSwipe(itemData);
        displaySnackbar(itemData.getTitle() + " removed", "Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addSwipe(pos, itemData);
            }
        });
        return true;
    }

    @Override
    public boolean swipeRight(Todo itemData) {
        presenter.complete(itemData);
        setCompleted(itemData);
        displaySnackbar(itemData.getTitle() + " completed", null, null);
        return true;
    }

    @Override
    public void onClick(Todo itemData) {
    }

    @Override
    public void onLongClick(Todo itemData) {
    }

    private void displaySnackbar(String text, String actionName, View.OnClickListener action) {
        Snackbar snack = Snackbar.make(root.getRootView().findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG)
                .setAction(actionName, action);

        View v = snack.getView();
        v.setBackgroundColor(root.getContext().getResources().getColor(R.color.independence));
        ((TextView) v.findViewById(android.support.design.R.id.snackbar_text)).setTextColor(Color.WHITE);
        ((TextView) v.findViewById(android.support.design.R.id.snackbar_action)).setTextColor(Color.BLACK);

        snack.show();
    }

    @Override
    public void onClick(View v) {

    }
}
