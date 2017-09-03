package com.myrium.magipp.cardlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myrium.magipp.R;
import com.myrium.magipp.model.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guilhermecardoso on 9/2/17.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{
    List<Card> mItems;

    public CardAdapter() {
        super();
        mItems = new ArrayList<Card>();
    }

    public void addData(Card card) {
        mItems.add(card);
        notifyDataSetChanged();
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_list_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CardAdapter.ViewHolder holder, int position) {
        Card card = mItems.get(position);
        holder.name.setText(card.getName());
        holder.cost.setText(card.getManaCost());
        holder.text.setText(card.getText());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView cost;
        public TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            cost = (TextView) itemView.findViewById(R.id.cost);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
