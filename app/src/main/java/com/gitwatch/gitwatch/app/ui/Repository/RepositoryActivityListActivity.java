package com.gitwatch.gitwatch.app.ui.Repository;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.gitwatch.gitwatch.R;
import com.gitwatch.gitwatch.app.ui.MainActivity;
import com.gitwatch.gitwatch.core.Domain.Model.Repository;
import com.gitwatch.gitwatch.infrastructure.github.Helpers.AlertHelper;
import com.gitwatch.gitwatch.infrastructure.github.Services.RepositoryService;

import org.json.JSONException;

import java.util.List;

public class RepositoryActivityListActivity extends AppCompatActivity {

    private List<Repository> repositoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositoryactivity_list);

        String keyword = getIntent().getStringExtra("keyword");
        try {
            repositoryList = new RepositoryService().getByKeywords(keyword);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(repositoryList.isEmpty()){
            AlertHelper.showInfoAlert(this, "Keine Suchresultate", "Für diesen Suchbegriff konnten keine Suchresultate gefunden werden", "Ok", MainActivity.class);
            return;
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(this.getTitle());

        View recyclerView = findViewById(R.id.repositoryactivity_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new RepositoryRecyclerAdapter(repositoryList));
    }

}
