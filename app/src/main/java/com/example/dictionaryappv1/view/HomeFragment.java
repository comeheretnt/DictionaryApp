package com.example.dictionaryappv1.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dictionaryappv1.R;
import com.example.dictionaryappv1.databaseUtils.Loading;

public class HomeFragment extends Fragment {
    static TextView tvLoadEnViDic;
    static TextView tvLoadViEnDic;
    Loading loading = Loading.getInstance();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvLoadEnViDic = getActivity().findViewById(R.id.tv_load_en_vi_dic);
        tvLoadViEnDic = getActivity().findViewById(R.id.tv_load_vi_en_dic);
        if (loading.isEnViDicLoaded()) {
            tvLoadEnViDic.setText("Loaded");
        }
        if (loading.isViEnDicLoaded()) {
            tvLoadViEnDic.setText("Loaded");
        }
    }

    public static void setTvLoadEnViDic(String text) {
        tvLoadEnViDic.setText(text);
    }
    public static void setTvLoadViEnDic(String text) {
        tvLoadViEnDic.setText(text);
    }
}