package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.R;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.Todo;

import java.util.List;

import co.dift.ui.SwipeToAction;

public class TodosAdapter extends RecyclerView.Adapter<TodosAdapter.ViewHolder> {

    private Context context;
    private List<Todo> todos;
    private View.OnClickListener mOnClickListener;

    public TodosAdapter(Context context, List<Todo> todos, View.OnClickListener onClickListener) {
        this.todos = todos;
        this.context = context;
        this.mOnClickListener = onClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_todo, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final ViewHolder vh = viewHolder;

        Todo todo = todos.get(i);

        vh.title.setText(todo.getTitle());
        if (todo.getCompleted()) {
            vh.completedIcon.setImageResource(R.drawable.ic_completed_main);
        } else{
            vh.completedIcon.setImageResource(R.color.none);
        }
        vh.data = todo;
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public class ViewHolder extends SwipeToAction.ViewHolder<Todo> {

        TextView title;
        ImageView completedIcon;

        public ViewHolder  (@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_tv);
            completedIcon = itemView.findViewById(R.id.completed_iv);
        }
    }

    public void add(int pos, Todo todo){
        todos.add(pos, todo);
    }

    public int remove(Todo todo){
        int pos = todos.indexOf(todo);
        todos.remove(todo);
        return pos;
    }

    public void setTodos(List<Todo> todos){
        this.todos = todos;
    }
}

