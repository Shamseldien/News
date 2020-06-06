package com.example.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.Article;
import com.example.model.SourceDetails;
import com.example.news.R;

import java.util.ArrayList;
import java.util.List;

public class SourcesAdapter extends RecyclerView.Adapter<SourcesAdapter.ViewHolder> {
    List<SourceDetails> sourcesModels = new ArrayList<>();
    Context context;

    public SourcesAdapter(List<SourceDetails> sourcesModels, Context context) {
        this.sourcesModels = sourcesModels;
        this.context = context;
    }

    @NonNull
    @Override
    public SourcesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.source_item,null);
        return new SourcesAdapter.ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull final SourcesAdapter.ViewHolder holder, int position) {
      final SourceDetails details = sourcesModels.get(position);
      holder.tvdesc.setText(details.getDescription());
      holder.tvname.setText(details.getName());
      holder.tvurl.setText(details.getUrl());
      holder.tvurl.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String url = details.getUrl();
              Intent intent = new Intent(Intent.ACTION_VIEW);
              intent.setData(Uri.parse(url));
              context.startActivity(intent);
          }
      });
    }

    @Override
    public int getItemCount() {
        return sourcesModels.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvname,tvdesc,tvurl;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname=itemView.findViewById(R.id.name);
            tvdesc=itemView.findViewById(R.id.description);
            tvurl=itemView.findViewById(R.id.url);
            cardView=itemView.findViewById(R.id.source_rec);
        }
    }
}
