package com.example.Adapter;

import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.Article;
import com.example.news.DetailsActivity;
import com.example.news.R;
import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    List<Article>newsModels = new ArrayList<>();
    Context context;

    public NewsAdapter(List<Article> newsModels, Context context) {
        this.newsModels = newsModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_items,null);
        return new NewsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Article model = newsModels.get(position);
        holder.title.setText(model.getTitle());
        holder.source.setText(model.getSource().getName());
        holder.date.setText("\u2022"+prettyTime(model.getPublishedAt()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailsActivity.class);
                i.putExtra("title",model.getTitle());
                i.putExtra("source",model.getSource().getName());
                i.putExtra("date",prettyTime(model.getPublishedAt()));
                i.putExtra("imageUrl",model.getUrlToImage());
                i.putExtra("url",model.getUrl());
                i.putExtra("desc",model.getDescription());
                context.startActivity(i);



            }
        });
        String imageurl = model.getUrlToImage();
        Picasso.get().load(imageurl).into(holder.imag);
    }

    @Override
    public int getItemCount() {
        return newsModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,date,source;
        ImageView imag;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.tvId);
            date=itemView.findViewById(R.id.tvDate);
            source=itemView.findViewById(R.id.tvSource);
            imag=itemView.findViewById(R.id.imag);
            cardView=itemView.findViewById(R.id.card_view);
        }
    }
    public String prettyTime(String time){
        PrettyTime prettyTime = new PrettyTime(new Locale(getCountry()));
        String t=null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:",Locale.ENGLISH);
            Date date = simpleDateFormat.parse(time);
            t=prettyTime.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return t;
    }
    public String getCountry(){
        TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        String locale = tm.getNetworkCountryIso();
        return locale.toLowerCase();

    }
    }
