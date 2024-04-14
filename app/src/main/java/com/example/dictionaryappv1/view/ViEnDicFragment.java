package com.example.dictionaryappv1.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.dictionaryappv1.databaseUtils.Loading;
import com.example.dictionaryappv1.R;
import com.example.dictionaryappv1.databinding.FragmentViEnDicBinding;
import com.example.dictionaryappv1.model.WordAndDefinition;
import com.example.dictionaryappv1.viewmodel.WordAdapter;

import java.util.ArrayList;

public class ViEnDicFragment extends Fragment {
    private FragmentViEnDicBinding binding;
    private Loading loading = Loading.getInstance();
    private WordAdapter wordAdapter;
    private ArrayList<WordAndDefinition> wordAndDefinitions;
    private ArrayList<WordAndDefinition> wordAndDefinitionsOriginal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentViEnDicBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("EnViDicFragment", "isEnViDicLoaded: " + loading.isEnViDicLoaded());

        if (loading.isEnViDicLoaded()) {
            wordAndDefinitionsOriginal = new ArrayList<>(loading.getWordAndDefinitionsViEn());
            wordAndDefinitions = new ArrayList<>(loading.getWordAndDefinitionsViEn());
            Log.d("EnViDicFragment", "wordAndDefinitions size: " + wordAndDefinitions.size());
            wordAdapter = new WordAdapter(wordAndDefinitions, getContext());
            binding.rvEnViDic.setAdapter(wordAdapter);
            binding.rvEnViDic.setLayoutManager(new LinearLayoutManager(getContext()));
            wordAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                wordAndDefinitions.clear();
                if (query.isEmpty()) {
                    wordAndDefinitions.addAll(wordAndDefinitionsOriginal);
                    return false;
                }
                for (WordAndDefinition wordAndDefinition : wordAndDefinitionsOriginal) {
                    if (wordAndDefinition.getWord().toLowerCase().contains(query.toLowerCase())) {
                        wordAndDefinitions.add(wordAndDefinition);
                    }
                }
                wordAdapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Filter filter = wordAdapter.getFilter();
//                filter.filter(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//
//        });
    }
}
