package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.R;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.Comment;
import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder>{

    private Context context;
    private List<Comment> comments;
    private View.OnClickListener mOnClickListener;

    public CommentsAdapter(Context context, List<Comment> comments, View.OnClickListener onClickListener){
        this.comments = comments;
        this.context = context;
        mOnClickListener = onClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final ViewHolder vh = viewHolder;

        Comment comment = comments.get(i);

        vh.name.setText(comment.getName());
        vh.body.setText(comment.getBody());
        vh.email.setText(comment.getId().toString());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name, body, email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_tv);
            body = itemView.findViewById(R.id.body_tv);
            email = itemView.findViewById(R.id.email_tv);
        }
    }

    public void setComments(List<Comment> comments){
        this.comments = comments;
        notifyDataSetChanged();
    }
}