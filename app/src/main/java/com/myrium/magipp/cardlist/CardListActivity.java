package com.myrium.magipp.cardlist;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.myrium.magipp.R;
import com.myrium.magipp.model.Card;
import com.myrium.magipp.model.CardsResponse;
import com.myrium.magipp.service.CardService;
import com.myrium.magipp.service.ServiceFactory;
import com.myrium.magipp.util.scrolllisteners.EndlessScrollListener;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CardListActivity extends AppCompatActivity {

    private static final String TAG = "CardListActivity";
    private CardAdapter mCardAdapter;
    private CardService service;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);

        service = ServiceFactory.createRetrofitService(CardService.class,
                CardService.SERVICE_ENDPOINT);

        final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_cards);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCardAdapter = new CardAdapter();
        mRecyclerView.setAdapter(mCardAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addOnScrollListener(new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                fetchCards(page);
            }
        });

        fetchCards(1);
    }

    private void fetchCards(int page) {
        service.getCards(page, "name")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CardsResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
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
