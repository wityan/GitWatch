package com.gitwatch.gitwatch.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gitwatch.gitwatch.R;
import com.gitwatch.gitwatch.app.ui.Repository.RepositoryActivityListActivity;
import com.gitwatch.gitwatch.app.ui.User.UserActivityListActivity;
import com.gitwatch.gitwatch.infrastructure.github.Helpers.AlertHelper;
import com.gitwatch.gitwatch.infrastructure.github.Helpers.NetworkStateHelper;

public class MainActivity extends AppCompatActivity {

    private EditText searchfield;
    private TextView searchfieldLable;
    private Button searchbutton;
    private Class activityClass = UserActivityListActivity.class;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_user:
                    searchfield.setHint("Benutzersuche");
                    searchfieldLable.setText("Nach Benutzer suchen");
                    activityClass = UserActivityListActivity.class;
                    return true;
                case R.id.navigation_repository:
                    searchfield.setHint("Repositorysuche");
                    searchfieldLable.setText("Nach Repository suchen");
                    activityClass = RepositoryActivityListActivity.class;
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchfield = (EditText) findViewById(R.id.searchfield);
        searchfieldLable = (TextView) findViewById(R.id.searchfieldLable);
        searchbutton = (Button) findViewById(R.id.searchbutton);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        searchbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!NetworkStateHelper.hasNetwork(getBaseContext())){
                    AlertHelper.showInfoAlert(v.getContext(), "Keine Internetverbindung", "Es können keine Suchabfragen ohne Internet durchgeführt werden", "Ok", null);
                    return;
                }
                else if (searchfield.getText().toString().isEmpty()){
                    AlertHelper.showInfoAlert(v.getContext(), "Kein Suchbegriff eingegeben", "Es können keine Suchabfragen ohne Suchbegriff durchgeführt werden", "Ok", null);
                    return;
                }
                Intent intent = new Intent(getApplicationContext(), activityClass);
                intent.putExtra("keyword", searchfield.getText().toString());
                startActivity(intent);
            }
        });
    }

}
