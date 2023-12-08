package com.example.wallpaperapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.wallpaperapp.databinding.ActivityDetailBinding;
import com.example.wallpaperapp.viewmodel.DetailViewModel;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    String image;
    DetailViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        initView();
        setWallPaper();
        setDownLoad();

    }

    public void initView(){
        Bundle b = getIntent().getExtras();
        image = b.getString("imageLarge");
        Glide.with(this)
                .load(image)
                .into(binding.detailImage);
    }

    public void setWallPaper(){
        binding.setWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.SetWallpaperEvent(binding.getRoot(),binding.detailImage);
            }
        });
    }

    public void setDownLoad(){
        binding.downLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.downloadImageNew(binding.getRoot(),"Thông báo",image);
            }
        });
    }
}