package com.gitwatch.gitwatch.app.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.gitwatch.gitwatch.R;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText searchfield;
    private TextView searchfieldLable;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_user:
                    searchfield.setHint("Benutzersuche");
                    searchfieldLable.setText("Nach Benutzer suchen");
                    return true;
                case R.id.navigation_repository:
                    searchfield.setHint("Repositorysuche");
                    searchfieldLable.setText("Nach Repository suchen");
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
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
