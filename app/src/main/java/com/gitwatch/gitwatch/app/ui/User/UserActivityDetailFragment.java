package com.gitwatch.gitwatch.app.ui.User;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.gitwatch.gitwatch.R;
import com.gitwatch.gitwatch.app.ui.Repository.RepositoryRecyclerAdapter;
import com.gitwatch.gitwatch.core.Domain.Model.Repository;
import com.gitwatch.gitwatch.core.Domain.Model.User;
import com.gitwatch.gitwatch.infrastructure.github.Services.RepositoryService;
import com.gitwatch.gitwatch.infrastructure.github.Services.UserService;

import org.json.JSONException;

import java.util.List;

public class UserActivityDetailFragment extends Fragment {
    public static final String ARG_ITEM_ID = "item_id";
    public static final String USERNAME = "user";

    private User mItem;
    private List<Repository> userRepositoryList;


    public UserActivityDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            String id = getArguments().getString(ARG_ITEM_ID);
            String username = getArguments().getString(USERNAME);
            try {
                userRepositoryList = new RepositoryService().getByUser(username);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                mItem = (User) new UserService().getById(Long.parseLong(id));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.useractivity_detail, container, false);

        if (mItem != null) {
            if(mItem.getBio() != "null"){
                ((TextView) rootView.findViewById(R.id.useractivity_detail)).setText(mItem.getBio());
            } else {
                ((TextView) rootView.findViewById(R.id.useractivity_detail)).setText("Keine Bio vorhanden");
            }
        }

        View recyclerView = rootView.findViewById(R.id.repositoryactivity_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        return rootView;
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new RepositoryRecyclerAdapter(userRepositoryList));
    }


}
