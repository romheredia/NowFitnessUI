package com.now.fitness.nowfitnessui.Model;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.now.fitness.nowfitnessui.Object.RssFeedItem;
import com.now.fitness.nowfitnessui.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Rom on 3/06/2017.
 */

public class RssFeedAdapter extends RecyclerView.Adapter<RssFeedAdapter.RssFeedViewHolder> {

    private Context mContext;
    private ArrayList<RssFeedItem> mRssFeedItems;

    View mView;

    public RssFeedAdapter(Context context, ArrayList<RssFeedItem> rssFeedItems) {
        this.mContext = context;
        this.mRssFeedItems = rssFeedItems;
    }

    @Override
    public RssFeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.news_item, parent, false);
        RssFeedViewHolder viewHolder = new RssFeedViewHolder(mView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RssFeedViewHolder holder, int position) {
        RssFeedItem rssFeedItem = mRssFeedItems.get(position);

        holder.titleText.setText(rssFeedItem.getTitle());
        holder.titleText.setText(rssFeedItem.getTitle());
        holder.titleDescription.setText(rssFeedItem.getDescription());
        Picasso.with(mContext).load(rssFeedItem.getImageUrl()).into(holder.titleImage);
//        holder.pubDate.setText(rssFeedItem.getPubDate());
    }

    @Override
    public int getItemCount() { return mRssFeedItems.size(); }

    public class RssFeedViewHolder extends RecyclerView.ViewHolder {
        private TextView titleText, titleDescription, pubDate;
        private ImageView titleImage;
        private CardView cardView;

        public RssFeedViewHolder(View rssFeedItemView) {
            super(rssFeedItemView);
            titleText = ((TextView) rssFeedItemView.findViewById(R.id.textView_TitleText));
            titleDescription = ((TextView) rssFeedItemView.findViewById(R.id.textView_DescriptionText));
            titleImage = ((ImageView) rssFeedItemView.findViewById(R.id.imageView_TitleImage));
//            pubDate = ((TextView) rssFeedItemView.findViewById(R.id.textView_PubDate));
            cardView = (CardView) rssFeedItemView.findViewById(R.id.cardView);

        }
    }
}
