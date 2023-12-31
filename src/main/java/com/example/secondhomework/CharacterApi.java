package com.example.secondhomework;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CharacterApi {
    @GET("api/characters")
    Call<List<Character>> getCharacters();

    @GET("api/characters/{char_id}")
    Call<List<Character>> getCharacterById(@Path("char_id") Integer char_id);
}