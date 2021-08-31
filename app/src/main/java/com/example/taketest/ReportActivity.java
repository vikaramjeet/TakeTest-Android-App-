package com.example.taketest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.taketest.db.DBHelper;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
//Activity to load the report  page
public class ReportActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    private DBHelper dBHelper;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //opens db context
        dBHelper = new DBHelper(this);
        setTitle("Score Report");

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.pager);

        pagerAdapter = new PagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    if(position == 0){
                        tab.setText("List");
                    }else{
                        tab.setText("Graph");
                    }
                }
        ).attach();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
        //creates the pageradapter to show list view and graph view
    public class PagerAdapter extends FragmentStateAdapter {

        public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {

            Fragment fragment;

            if(position == 1){
                fragment = ReportGraphFragment.newInstance();
            }else {
                fragment = ReportListFragment.newInstance();
            }
            return fragment;
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}