package com.example.secondhomework;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Characters extends Fragment implements CharacterAdapter.OnItemNoteListener{
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    CharacterAdapter charactersAdapter;
    List<Character> charactersList= new ArrayList<>();


    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_characters,container,false);
        recyclerView = layout.findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        charactersAdapter = new CharacterAdapter(charactersList, this.getContext(), this);
        recyclerView.setAdapter(charactersAdapter);
        fetchCharacters();
        return layout;
    }

    private void fetchCharacters() {

        Client.getRetrofitClient().getCharacters().enqueue(new Callback<List<Character>>() {
            @Override
            public void onResponse(@NonNull Call<List<Character>> call, Response<List<Character>> response) {
                if(response.isSuccessful() && response.body() != null){
                    charactersList.addAll(response.body());
                    charactersAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<Character>> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onNoteClick(int position) {
        Character character = charactersList.get(position);
        Intent intent = new Intent(this.getContext(), CharacterDetailActivity.class);
        intent.putExtra("id",character.getChar_id());
        startActivity(intent);
    }
}