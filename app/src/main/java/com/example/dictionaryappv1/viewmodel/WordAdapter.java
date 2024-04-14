package com.example.dictionaryappv1.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dictionaryappv1.DefinitionActivity;
import com.example.dictionaryappv1.databinding.WordRowItemBinding;
import com.example.dictionaryappv1.model.WordAndDefinition;

import java.util.ArrayList;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> implements Filterable {
    private ArrayList<WordAndDefinition> wordAndDefinitions;
    private ArrayList<WordAndDefinition> originalWordAndDefinitions;
    private Context context;

    public WordAdapter(ArrayList<WordAndDefinition> wordAndDefinitions, Context context) {
        this.wordAndDefinitions = wordAndDefinitions;
        this.originalWordAndDefinitions = new ArrayList<>(wordAndDefinitions);
        this.context = context;
    }

    public WordAdapter(ArrayList<WordAndDefinition> wordAndDefinitions) {
        this.wordAndDefinitions = wordAndDefinitions;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WordRowItemBinding binding = WordRowItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setWord(wordAndDefinitions.get(position));
    }

    @Override
    public int getItemCount() {
        return wordAndDefinitions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        WordRowItemBinding binding;
        public ViewHolder(WordRowItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

                    binding.tvWord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
//                        WordAndDefinition wordAndDefinition = wordAndDefinitions.get(position);
                        Intent intent = new Intent(context, DefinitionActivity.class);
                        intent.putExtra("word", wordAndDefinitions.get(position).getWord());
                        intent.putExtra("definition", wordAndDefinitions.get(position).getDefinition());
                        context.startActivity(intent);
                    }
                }
            });
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                ArrayList<WordAndDefinition> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(originalWordAndDefinitions);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (WordAndDefinition item : originalWordAndDefinitions) {
                        if (item.getWord().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                wordAndDefinitions.clear();
                wordAndDefinitions.addAll((ArrayList) results.values);
                notifyDataSetChanged();
            }
        };
    }

}