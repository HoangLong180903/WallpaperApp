package com.example.wallpaperapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.wallpaperapp.adapters.WallpaperAdapter;
import com.example.wallpaperapp.databinding.ActivityMainBinding;
import com.example.wallpaperapp.models.Photos;
import com.example.wallpaperapp.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MainViewModel viewModel;
    WallpaperAdapter adapter;
    boolean notLoading = true;
    GridLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
        getListData();
        getListSearch();
        loadMore();
    }

    public void initView() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        manager = new GridLayoutManager(this, 2);
        binding.rcWallpaper.setLayoutManager(manager);
        adapter = new WallpaperAdapter(this, new ArrayList<>());
        binding.rcWallpaper.setAdapter(adapter);
    }

    public void getListData() {
        viewModel.getListWallpaper().observe(this, photos -> {
            adapter.setData(photos);
            adapter.notifyDataSetChanged();
        });
    }

    public void getListSearch() {
        binding.imageSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getSearchWallpaper(binding.edSearchView.getText().toString()).observe(MainActivity.this, photos -> {
                    adapter.setData(photos);
                    adapter.notifyDataSetChanged();
                });
            }
        });

        binding.edSearchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    getListData();
                }
            }
        });
    }


    public void loadMore(){
        viewModel.getPhotosLiveData().observe(this, new Observer<List<Photos>>() {
            @Override
            public void onChanged(List<Photos> photos) {
                binding.pbLoading.setVisibility(View.GONE);
                binding.rcWallpaper.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        if (notLoading && manager.findLastCompletelyVisibleItemPosition() == photos.size() - 1){
                            final Handler handler = new Handler();
                            binding.pbLoading.setVisibility(View.VISIBLE);
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    viewModel.getNextPage();
                                    notLoading =false;
                                }
                            }, 2000);
                        }else{
                            notLoading = true;
                            binding.pbLoading.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }
}