package com.example.dictionaryappv1.databaseUtils;


import com.example.dictionaryappv1.MainActivity;
import com.example.dictionaryappv1.model.WordAndDefinition;
import com.example.dictionaryappv1.view.HomeFragment;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Loading {
    private static Loading instance;
    private ArrayList<WordAndDefinition> wordAndDefinitionsEnVi;
    private ArrayList<WordAndDefinition> wordAndDefinitionsViEn;
    private ArrayList<String> anhVietWord;
    private ArrayList<String> anhVietDefinition;

    private ArrayList<String> vietAnhWord;
    private ArrayList<String> vietAnhDefinition;
    private Loading() {
        loadDatabase();
    }
    public static Loading getInstance() {
        if (instance == null) {
            instance = new Loading();
        }
        return instance;
    }
    @SuppressLint("StaticFieldLeak")
    public void loadDatabase() {
        new AsyncTask<Void, Void, List<String>>() {
            @Override
            protected List<String> doInBackground(Void... voids) {
                DatabaseAccessAnhViet databaseAccess = DatabaseAccessAnhViet.getInstance(MainActivity.getContext());
                databaseAccess.open();
                wordAndDefinitionsEnVi = databaseAccess.getWordAndDefinition();
                databaseAccess.close();
                return null;
            }
            @Override
            protected void onPostExecute(List<String> words) {
                anhVietWord = new ArrayList<>();
                for (WordAndDefinition wordAndDefinition : wordAndDefinitionsEnVi) {
                    anhVietWord.add(wordAndDefinition.getWord());
                }
                anhVietDefinition = new ArrayList<>();
                for (WordAndDefinition wordAndDefinition : wordAndDefinitionsEnVi) {
                    anhVietDefinition.add(wordAndDefinition.getDefinition());
                }
                HomeFragment.setTvLoadEnViDic("Loaded");
                Log.d("Debug", "Done load anh_viet");
            }
        }.execute();

        new AsyncTask<Void, Void, List<String>>() {
            @Override
            protected List<String> doInBackground(Void... voids) {
                DatabaseAccessVietAnh databaseAccess = DatabaseAccessVietAnh.getInstance(MainActivity.getContext());
                databaseAccess.open();
                wordAndDefinitionsViEn = databaseAccess.getWordAndDefinition();
                databaseAccess.close();
                return null;
            }
            @Override
            protected void onPostExecute(List<String> words) {
                vietAnhWord = new ArrayList<>();
                for (WordAndDefinition wordAndDefinition : wordAndDefinitionsViEn) {
                    vietAnhWord.add(wordAndDefinition.getWord());
                }
                vietAnhDefinition = new ArrayList<>();
                for (WordAndDefinition wordAndDefinition : wordAndDefinitionsViEn) {
                    vietAnhDefinition.add(wordAndDefinition.getDefinition());
                }
                HomeFragment.setTvLoadViEnDic("Loaded");
                Log.d("Debug", "Done load viet_anh");
            }
        }.execute();
    }


    public boolean isEnViDicLoaded() {
        return wordAndDefinitionsEnVi != null;
    }
    public boolean isViEnDicLoaded() {
        return wordAndDefinitionsViEn != null;
    }

    public ArrayList<WordAndDefinition> getWordAndDefinitionsEnVi() {
        return wordAndDefinitionsEnVi;
    }

    public ArrayList<WordAndDefinition> getWordAndDefinitionsViEn() {
        return wordAndDefinitionsViEn;
    }


}