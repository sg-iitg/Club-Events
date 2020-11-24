package com.example.clubevents;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class CustomListAdapter extends ArrayAdapter {
    private final Activity context;
    private final ArrayList<String> PostTitle;
    private final ArrayList<String> PostContent;
    private final ArrayList<String> PostLink;
    private final ArrayList<String> ImageLink;


    public CustomListAdapter(Activity context, ArrayList<String> TitleArray, ArrayList<String> ContentArray,
                             ArrayList<String> LinkArray, ArrayList<String> ImgArray){
        super(context,R.layout.card_row);
        this.context=context;
        this.PostTitle = TitleArray;
        this.PostContent = ContentArray;
        this.PostLink = LinkArray;
        this.ImageLink = ImgArray;
    }

    @Override
    public int getCount() {
        return PostTitle.size();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View cardView=inflater.inflate(R.layout.card_row, null,true);

        TextView title = cardView.findViewById(R.id.CardTitle);
        TextView content = cardView.findViewById(R.id.CardContent);
        TextView link = cardView.findViewById(R.id.CardLink);
        ImageView imgView = cardView.findViewById(R.id.ImageView);

        title.setText(PostTitle.get(position));
        if(!ImageLink.get(position).equals("NA"))
        {
            imgView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            new ImageLoadTask(ImageLink.get(position),imgView).execute();
        }
        content.setText(PostContent.get(position));
        link.setText(PostLink.get(position));

        return cardView;
    };
}
