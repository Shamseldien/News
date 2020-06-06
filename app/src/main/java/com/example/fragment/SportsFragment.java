package com.example.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.Adapter.NewsAdapter;
import com.example.model.Article;
import com.example.model.Headlines;
import com.example.news.R;
import com.example.retrofite.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SportsFragment extends Fragment {
    private static final String API_KEY = "2f89fcd3e0214937853f64711ef5bfb6";
    RecyclerView recyclerView;
    NewsAdapter adapter;
    List<Article> articleList = new ArrayList<>();
    String contry;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progressBar;
    EditText etQuery;
    Button bt_search;
    public SportsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity(). getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sports, container, false);
        etQuery = view.findViewById(R.id.sports_et_query);
        bt_search = view.findViewById(R.id.sports_btn_search);
        recyclerView = view.findViewById(R.id.sports_recy);
        recyclerView.setHasFixedSize(true);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh);
        progressBar = view.findViewById(R.id.loader);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        contry = "eg";
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getSports("sports","", contry, API_KEY);
            }
        });
        getSports("sports","", contry, API_KEY);
        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etQuery.getText().toString().equals("")){
                    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            getSports("sports",etQuery.getText().toString(),contry,API_KEY);
                        }
                    });
                    getSports("sports",etQuery.getText().toString(),contry, API_KEY);
                }else {
                    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            getSports("sports","",contry, API_KEY);
                        }
                    });
                    getSports("sports","",contry, API_KEY);
                }
            }
        });
        return view;
    }
    public void getSports(String category,String Query, String country, String apikey) {
        swipeRefreshLayout.setRefreshing(true);
        Call<Headlines> call;
        if (!etQuery.getText().toString().equals("")){
            call = ApiClient.getInstance().getApi().searchQuery(Query, apikey);

        }else {
            call = ApiClient.getInstance().getApi().getSports(category,country, apikey);

        }
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if (response.isSuccessful() && response.body().getArticles() != null) {
                    swipeRefreshLayout.setRefreshing(false);
                    articleList.clear();
                    articleList = response.body().getArticles();
                    adapter = new NewsAdapter(articleList, getContext());
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
