package com.pllug.course.oleksandr.havryliuk.utopiasocialapp.fragments.images;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.R;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.adapters.ImagesAdapter;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.api.APIInterface;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.api.RetrofitClient;
import com.pllug.course.oleksandr.havryliuk.utopiasocialapp.repositories.ImagesRepository;

public class ImagesFragment extends Fragment {

    private View view;
    private GridView gridView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_images, container, false);

        initView();
        loadImages();

        return view;
    }

    public void initView(){
        gridView = (GridView) view.findViewById(R.id.gridview);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Toast.makeText(getContext(), "Image " + position, Toast.LENGTH_SHORT);
            }
        });
    }

    public void loadImages(){
        ImagesRepository imageRepository = new ImagesRepository(RetrofitClient.getApi(), getContext(), gridView);
        imageRepository.loadImages();
    }
}
