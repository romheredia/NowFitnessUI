package com.now.fitness.nowfitnessui.Model;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

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
 * This class is for retrieving data from RssFeed
 * @author Romeric Heredia on 4/06/2017.
 */

public class RssFeedReader extends AsyncTask<Void, Void, Boolean> {

    private String rssFeedUrl = "http://rss.nytimes.com/services/xml/rss/nyt/Nutrition.xml";

    private Context mContext;
    private SwipeRefreshLayout mSwipeLayout;
    private RecyclerView mRecyclerView;
    private ArrayList<RssFeedItem> rssFeedItems;
    private URL url;

    ProgressDialog mProgressDialog;

    public RssFeedReader(Context context, RecyclerView recyclerView, SwipeRefreshLayout swipeRefreshLayout) {
        this.mContext = context;
        this.mRecyclerView = recyclerView;
        this.mSwipeLayout = swipeRefreshLayout;
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setMessage("Loading...");
    }

    @Override
    protected void onPreExecute() {
        mSwipeLayout.setRefreshing(true);
    }

    @Override
    protected void onPostExecute(Boolean success) {
        mSwipeLayout.setRefreshing(false);

        if(success) {
            RssFeedAdapter rssFeedAdapter = new RssFeedAdapter(mContext, rssFeedItems);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            mRecyclerView.setAdapter(rssFeedAdapter);
        } else {
            Toast.makeText(mContext, "Failed to get RSS Feed.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            if (getRssFeedData() != null) {
                RssFeedParser(getRssFeedData());
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Extracts data items from xml retrieved from RssFeedData
     * @param data
     */
    private void RssFeedParser(Document data) {
        try {
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
                            if (current.getNodeName().equalsIgnoreCase("title")) {
                                item.setTitle(current.getTextContent());
                            } else if (current.getNodeName().equalsIgnoreCase("link")) {
                                item.setLink(current.getTextContent());
                            } else if (current.getNodeName().equalsIgnoreCase("description")) {
                                item.setDescription(current.getTextContent());
                            } else if (current.getNodeName().equalsIgnoreCase("pubDate")) {
                                item.setPubDate(current.getTextContent());
                            } else if (current.getNodeName().equalsIgnoreCase("media:content")) {
                                String imageUrl = current.getAttributes().item(0).getTextContent();
                                item.setImageUrl(imageUrl);
                            }
                        }
                        rssFeedItems.add(item);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * getRssFeedData returns Document data retrieved from a URL
     *
     */
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
