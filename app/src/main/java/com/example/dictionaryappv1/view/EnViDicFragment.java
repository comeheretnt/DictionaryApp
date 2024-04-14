package com.example.dictionaryappv1.view;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ListView;

import com.example.dictionaryappv1.MainActivity;
import com.example.dictionaryappv1.R;
import com.example.dictionaryappv1.databaseUtils.DatabaseAccessAnhViet ;
import com.example.dictionaryappv1.databaseUtils.Loading;
import com.example.dictionaryappv1.databinding.FragmentEnViDicBinding;
import com.example.dictionaryappv1.model.WordAndDefinition;
import com.example.dictionaryappv1.viewmodel.WordAdapter;

import java.util.ArrayList;
import java.util.List;

public class EnViDicFragment extends Fragment {
    private FragmentEnViDicBinding binding;
    private Loading loading = Loading.getInstance();
    private WordAdapter wordAdapter;
    private ArrayList<WordAndDefinition> wordAndDefinitions;
    private ArrayList<WordAndDefinition> wordAndDefinitionsOriginal;

    @SuppressLint("StaticFieldLeak")
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
        // Inflate the layout for this fragment
        binding = FragmentEnViDicBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("EnViDicFragment", "isEnViDicLoaded: " + loading.isEnViDicLoaded());

        if (loading.isEnViDicLoaded()) {
            wordAndDefinitionsOriginal = new ArrayList<>(loading.getWordAndDefinitionsEnVi());
            wordAndDefinitions = new ArrayList<>(loading.getWordAndDefinitionsEnVi());
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
