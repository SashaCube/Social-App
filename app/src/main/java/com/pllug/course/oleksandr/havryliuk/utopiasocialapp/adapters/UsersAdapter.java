package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.R;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.models.User;
import com.truizlop.fabreveallayout.FABRevealLayout;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private Context context;
    private User user;
    private ViewHolder viewHolder;
    private View.OnClickListener mOnClickListener;

    public UsersAdapter(Context context, User user, View.OnClickListener onClickListener){
        this.user = user;
        this.context = context;
        mOnClickListener = onClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final ViewHolder vh = viewHolder;
        this.viewHolder = viewHolder;

        vh.nameTextView.setText(user.getName());
        vh.companyTextView.setText(user.getCompany().getName());
        vh.phoneTextView.setText(user.getPhone());
        vh.addressTextView.setText(user.getAddress().getCity());
        vh.emailTextView.setText(user.getEmail());
        vh.websiteTextView.setText(user.getWebsite());
        vh.locationLayout.setOnClickListener(mOnClickListener);
        vh.callImageView.setOnClickListener(mOnClickListener);
        vh.websiteImageView.setOnClickListener(mOnClickListener);
        vh.emailImageView.setOnClickListener(mOnClickListener);
        vh.fabRevealLayout.setOnClickListener(mOnClickListener);
        vh.detailInfoLayout.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nameTextView, companyTextView, addressTextView,
                emailTextView, phoneTextView, websiteTextView;
        LinearLayout locationLayout;
        ImageView emailImageView, callImageView, websiteImageView;
        FABRevealLayout fabRevealLayout;
        RelativeLayout detailInfoLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_tv);
            companyTextView = itemView.findViewById(R.id.company_tv);
            addressTextView = itemView.findViewById(R.id.address_tv);
            emailTextView = itemView.findViewById(R.id.email_tv);
            phoneTextView = itemView.findViewById(R.id.phone_tv);
            websiteTextView = itemView.findViewById(R.id.website_tv);

            locationLayout = itemView.findViewById(R.id.location_layout);
            fabRevealLayout = itemView.findViewById(R.id.fab_reveal_layout);
            detailInfoLayout = itemView.findViewById(R.id.secondary_view);

            emailImageView = itemView.findViewById(R.id.email_image);
            callImageView = itemView.findViewById(R.id.call_image);
            websiteImageView = itemView.findViewById(R.id.website_image);
        }
    }

    public void setUser(User user){
        this.user = user;
        notifyDataSetChanged();
    }

    public void mainView(){
        viewHolder.fabRevealLayout.revealMainView();
    }

    public void secondaryView(){
        viewHolder.fabRevealLayout.revealSecondaryView();
    }
}