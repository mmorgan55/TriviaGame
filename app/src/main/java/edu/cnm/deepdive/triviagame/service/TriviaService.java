package edu.cnm.deepdive.triviagame.service;

import edu.cnm.deepdive.triviagame.model.pojo.TriviaPojo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TriviaService {

  @GET("api.php")
  Call<TriviaPojo> get(@Query("amount") int amount, @Query("category") int category,
      @Query("type") String type);
}
