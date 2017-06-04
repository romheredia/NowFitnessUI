package com.now.fitness.nowfitnessui.Model;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.now.fitness.nowfitnessui.Object.RssFeedItem;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Rom on 4/06/2017.
 */

public class RssFeedReader extends AsyncTask<Void, Void, Void> {

        private String rssFeedUrl = "http://rss.nytimes.com/services/xml/rss/nyt/Nutrition.xml";

    private Context mContext;
    private SwipeRefreshLayout mSwipeLayout;
    private RecyclerView mRecyclerView;
    private ArrayList<RssFeedItem> rssFeedItems;
    private URL url;

    ProgressDialog mProgressDialog;

    public RssFeedReader (Context context, RecyclerView recyclerView, SwipeRefreshLayout swipeRefreshLayout) {
        this.mContext = context;
        this.mRecyclerView = recyclerView;
        this.mSwipeLayout = swipeRefreshLayout;
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setMessage("Loading...");
    }

    @Override
    protected void onPreExecute() {
//        super.onPreExecute();
//        mProgressDialog.show();
        mSwipeLayout.setRefreshing(true);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
//        mProgressDialog.dismiss();
        mSwipeLayout.setRefreshing(false);

        RssFeedAdapter rssFeedAdapter = new RssFeedAdapter(mContext, rssFeedItems);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(rssFeedAdapter);
    }

    @Override
    protected Void doInBackground(Void... params) {
        RssFeedParser(getRssFeedData());
        return null;
    }

    private void RssFeedParser(Document data) {
        if (data != null) {
            rssFeedItems = new ArrayList<>();

            Element root = data.getDocumentElement();
            Node channel = root.getChildNodes().item(1);
            NodeList items = channel.getChildNodes();

            for (int i = 0; i < items.getLength(); i++) {
                Node currentChild = items.item(i);

                if (currentChild.getNodeName().equalsIgnoreCase("item")) {
                    RssFeedItem item = new RssFeedItem();
                    NodeList itemChilds = currentChild.getChildNodes();

                    for (int j = 0; j < itemChilds.getLength(); j++) {
                        Node current = itemChilds.item(j);
                        if(current.getNodeName().equalsIgnoreCase("title")){
                            item.setTitle(current.getTextContent());
                        } else if (current.getNodeName().equalsIgnoreCase("link")){
                            item.setLink(current.getTextContent());
                        } else if (current.getNodeName().equalsIgnoreCase("description")){
                            item.setDescription(current.getTextContent());
                        } else if (current.getNodeName().equalsIgnoreCase("pubDate")){
                            item.setPubDate(current.getTextContent());
                        } else if (current.getNodeName().equalsIgnoreCase("media:content")){
                            String imageUrl = current.getAttributes().item(0).getTextContent();
                            item.setImageUrl(imageUrl);
                        }
                    }
                    rssFeedItems.add(item);
                }
            }
        }
    }

    public Document getRssFeedData() {
        try {
            url = new URL(rssFeedUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDocument = builder.parse(inputStream);
            return xmlDocument;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
