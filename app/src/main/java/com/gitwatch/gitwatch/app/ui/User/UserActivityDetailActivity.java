package com.gitwatch.gitwatch.app.ui.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.gitwatch.gitwatch.R;

public class UserActivityDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useractivity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(UserActivityDetailFragment.USERNAME,
                    getIntent().getStringExtra(UserActivityDetailFragment.USERNAME));
            arguments.putString(UserActivityDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(UserActivityDetailFragment.ARG_ITEM_ID));
            UserActivityDetailFragment fragment = new UserActivityDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.useractivity_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            navigateUpTo(new Intent(this, UserActivityListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
