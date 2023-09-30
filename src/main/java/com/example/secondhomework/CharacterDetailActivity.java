package com.example.secondhomework;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterDetailActivity extends AppCompatActivity {

    private TextView name;
    private TextView birthday;
    private TextView status;
    private int id;
    private java.lang.Character character;
    private ArrayList<String> occ;
    private ListView listViewOccupation;
    private ImageView imageViewD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",1);
        name = findViewById(R.id.detname);
        birthday = findViewById(R.id.detbirthDay);
        status = findViewById(R.id.detstatus);
        listViewOccupation = findViewById(R.id.detoccupation);
        imageViewD = findViewById(R.id.imageViewD);
        fetchCharacters();

    }

    private void fetchCharacters() {

        Client.getRetrofitClient().getCharacterById(id).enqueue(new Callback<List<java.lang.Character>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<java.lang.Character>> call, Response<List<java.lang.Character>> response) {
                if(response.isSuccessful() && response.body() != null){
                    character = new java.lang.Character(response.body());
                    name.setText(character.getName());
                    birthday.setText(character.getBirthday());
                    occ = character.getOccupation();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),  android.R.layout.simple_list_item_1, occ);
                    listViewOccupation.setAdapter(adapter);
                    Picasso.with(getApplicationContext())
                            .load(character.getImg())
                            .placeholder(R.drawable.ic_baseline_person_24)
                            .fit()
                            .into(imageViewD);
                    status.setText(character.getStatus());

                }
            }

            @Override
            public void onFailure(Call<List<java.lang.Character>> call, Throwable t) {
                Toast.makeText(CharacterDetailActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }
}