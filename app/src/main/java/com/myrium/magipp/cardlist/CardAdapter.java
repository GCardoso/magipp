package com.myrium.magipp.cardlist;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.myrium.magipp.R;
import com.myrium.magipp.model.Card;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by guilhermecardoso on 9/2/17.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{
    private List<Card> mItems;

    CardAdapter() {
        super();
        mItems = new ArrayList<Card>();
    }

    void addData(Card card) {
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
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CardAdapter.ViewHolder holder, int position) {
        if (mItems == null ) {
            return;
        }
        Card card = mItems.get(position);

        if (card == null ) {
            return;
        }

        holder.name.setText(card.getName());
        holder.cost.setText(card.getManaCost());
        holder.typeSubtype.setText(card.getTypeSubtype());


//        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(card.getImageUrl()))
//                .build();
//
//        DraweeController controller = Fresco.newDraweeControllerBuilder()
//                .setImageRequest(request)
//                .setTapToRetryEnabled(true)
//                .setOldController(holder.imageCard.getController())
//                .build();
        String imageUrl = card.getImageUrl();
        if (imageUrl != null) {
            Uri uri = Uri.parse(imageUrl);
            holder.imageCard.setImageURI(uri);
        }
//        holder.imageCard.setController(controller);


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name) TextView name;
        @BindView(R.id.cost) TextView cost;
        @BindView(R.id.type) TextView typeSubtype;
        @BindView(R.id.imageView_card_cell) SimpleDraweeView imageCard;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
