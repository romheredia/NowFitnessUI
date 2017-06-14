package com.now.fitness.nowfitnessui.Model;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.now.fitness.nowfitnessui.Object.RssFeedItem;
import com.now.fitness.nowfitnessui.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * This class is the adapter for RssFeed
 * @author Romeric Heredia on 3/06/2017.
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
        final RssFeedItem rssFeedItem = mRssFeedItems.get(position);

        holder.titleText.setText(rssFeedItem.getTitle());
        holder.titleText.setText(rssFeedItem.getTitle());
        holder.titleDescription.setText(rssFeedItem.getDescription());
        Picasso.with(mContext).load(rssFeedItem.getImageUrl()).into(holder.titleImage);
//        holder.pubDate.setText(rssFeedItem.getPubDate());

        holder.newsItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, rssFeedItem.getLink(), Toast.LENGTH_LONG).show();
                Uri uriUrl = Uri.parse(rssFeedItem.getLink());
                Intent openBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                mContext.startActivity(openBrowser);
            }
        });
    }

    @Override
    public int getItemCount() { return mRssFeedItems.size(); }

    /**
     * RssFeedViewHolder attaches rss feed items to recycleview
     *
     */
    public class RssFeedViewHolder extends RecyclerView.ViewHolder {
        private TextView titleText, titleDescription, pubDate;
        private ImageView titleImage;
        private RelativeLayout newsItem;
        private CardView cardView;

        public RssFeedViewHolder(View rssFeedItemView) {
            super(rssFeedItemView);
            titleText = ((TextView) rssFeedItemView.findViewById(R.id.textView_TitleText));
            titleDescription = ((TextView) rssFeedItemView.findViewById(R.id.textView_DescriptionText));
            titleImage = ((ImageView) rssFeedItemView.findViewById(R.id.imageView_TitleImage));
//            pubDate = ((TextView) rssFeedItemView.findViewById(R.id.textView_PubDate));
            cardView = (CardView) rssFeedItemView.findViewById(R.id.cardView);
            newsItem = (RelativeLayout) rssFeedItemView.findViewById(R.id.relativeLayout_NewsItem);

        }
    }
}
