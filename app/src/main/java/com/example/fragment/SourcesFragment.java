package com.example.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Adapter.NewsAdapter;
import com.example.Adapter.SourcesAdapter;
import com.example.model.SourceDetails;
import com.example.model.SourcesSttus;
import com.example.news.R;
import com.example.retrofite.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SourcesFragment extends Fragment {
    TextView tvname,tvdesc,tvurl,tvlang,tvcountry;
    ProgressBar progressBar;
    SourcesAdapter adapter;
    RecyclerView recyclerView;
    List<SourceDetails>sourceDetails = new ArrayList<>();
    private static final String API_KEY = "2f89fcd3e0214937853f64711ef5bfb6";

    public SourcesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sources, container, false);
        tvname=view.findViewById(R.id.name);
        tvdesc=view.findViewById(R.id.description);
        tvurl=view.findViewById(R.id.url);
        recyclerView=view.findViewById(R.id.source_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
         getSources(API_KEY);

        return view;
    }

    public void getSources(String apikey) {
        Call<SourcesSttus> call = ApiClient.getInstance().getApi().getSources(API_KEY);
        call.enqueue(new Callback<SourcesSttus>() {
            @Override
            public void onResponse(Call<SourcesSttus> call, Response<SourcesSttus> response) {
                if (response.isSuccessful() && response.body().getSources()!=null){
                    sourceDetails.clear();
                    sourceDetails = response.body().getSources();
                    adapter = new SourcesAdapter(sourceDetails, getContext());
                    recyclerView.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<SourcesSttus> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
