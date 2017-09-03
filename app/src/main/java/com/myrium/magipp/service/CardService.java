package com.myrium.magipp.service;

import com.myrium.magipp.model.Card;
import com.myrium.magipp.model.CardsResponse;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;


/**
 * Created by guilhermecardoso on 9/1/17.
 */

public interface CardService {
    String SERVICE_ENDPOINT = "https://api.magicthegathering.io/v1";

    @GET("/cards/{id}")
    Observable<Card> getCard(@Path("id") String id);

    @GET("/cards")
    Observable<CardsResponse> getCards();
}
