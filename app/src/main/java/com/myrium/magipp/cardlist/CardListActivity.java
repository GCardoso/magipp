package com.myrium.magipp.cardlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.myrium.magipp.R;
import com.myrium.magipp.model.Card;
import com.myrium.magipp.model.CardsResponse;
import com.myrium.magipp.service.CardService;
import com.myrium.magipp.service.ServiceFactory;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CardListActivity extends AppCompatActivity {

    private static final String TAG = "CardListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);

        CardService service = ServiceFactory.createRetrofitService(CardService.class,
                CardService.SERVICE_ENDPOINT);

        final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_cards);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        final CardAdapter mCardAdapter = new CardAdapter();
        mRecyclerView.setAdapter(mCardAdapter);


        service.getCards()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CardsResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CardsResponse cardResponse) {
                        for (Card card: cardResponse.getCards()) {
                            mCardAdapter.addData(card);
                        }
                    }
                });
    }
}
