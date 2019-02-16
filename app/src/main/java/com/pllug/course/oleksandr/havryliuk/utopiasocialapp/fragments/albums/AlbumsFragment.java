package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.albums;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.R;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.api.APIInterface;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.api.RetrofitClient;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.repositories.AlbumsRepository;

public class AlbumsFragment extends Fragment {

    private AlbumsContract.IAlbumsView view;
    private AlbumsContract.IAlbumsPresenter presenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_albums, container, false);

        initView(root);
        initPresenter();

        return root;
    }

    public void initView(View root){
        view = new AlbumsView();
        view.init(root);
    }

    public void initPresenter(){
        presenter = new AlbumsPresenter(AlbumsRepository.getInstance(), view);
        view.setPresenter(presenter);
    }
}
