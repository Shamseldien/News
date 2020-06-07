package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import com.example.fragment.BusinessFragment;
import com.example.fragment.HealthFragment;
import com.example.fragment.SourcesFragment;
import com.example.fragment.NewsFragment;
import com.example.fragment.SportsFragment;
import com.example.fragment.TechnologyFragment;
import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {
    SNavigationDrawer sNavigationDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        sNavigationDrawer = findViewById(R.id.drawer);
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Latest News", R.drawable.news_bg));
        menuItems.add(new MenuItem("Business", R.drawable.bussiness));
        menuItems.add(new MenuItem("Sports", R.drawable.sports));
        menuItems.add(new MenuItem("Technology", R.drawable.technology));
        menuItems.add(new MenuItem("Health", R.drawable.health));
        menuItems.add(new MenuItem("Our Sources", R.drawable.source));
        sNavigationDrawer.setMenuItemList(menuItems);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new NewsFragment()).addToBackStack(null).commit();
        sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClicked(int position) {
                Fragment selected = null;
                switch (position) {
                    case 0:
                        selected = new NewsFragment();
                        break;
                    case 1:
                        selected =new BusinessFragment();
                        break;
                    case 2:
                        selected = new SportsFragment();
                        break;
                    case 3:
                        selected= new TechnologyFragment();
                        break;
                    case 4:
                        selected = new HealthFragment();

                        break;
                    case 5:
                        selected = new SourcesFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selected).commit();


            }
        });
        sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {
            @Override
            public void onDrawerOpening() {

            }

            @Override
            public void onDrawerClosing() {

            }

            @Override
            public void onDrawerOpened() {

            }

            @Override
            public void onDrawerClosed() {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}

