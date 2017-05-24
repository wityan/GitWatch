package com.gitwatch.gitwatch.app.ui;

import android.app.Activity;
import android.content.Intent;
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
import com.gitwatch.gitwatch.core.Domain.Model.Repository;
import com.gitwatch.gitwatch.core.Domain.Model.User;
import com.gitwatch.gitwatch.infrastructure.github.Services.RepositoryService;
import com.gitwatch.gitwatch.infrastructure.github.Helpers.AlertHelper;
import com.gitwatch.gitwatch.infrastructure.github.Helpers.NetworkStateHelper;
import com.gitwatch.gitwatch.infrastructure.github.Services.UserService;

import org.json.JSONException;

import java.util.List;

/**
 * A fragment representing a single UserActivity detail screen.
 * This fragment is either contained in a {@link UserActivityListActivity}
 * in two-pane mode (on tablets) or a {@link UserActivityDetailActivity}
 * on handsets.
 */
public class UserActivityDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    public static final String USERNAME = "user";


    /**
     * The dummy content this fragment is presenting.
     */
    private User mItem;
    private List<Repository> UserRepositoryList;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public UserActivityDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.

            String id = getArguments().getString(ARG_ITEM_ID);
            String username = getArguments().getString(USERNAME);
            try {
                UserRepositoryList = new RepositoryService().getByUser(username);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (!NetworkStateHelper.hasNetwork(this.getContext())){
                AlertHelper.showInfoAlert(this.getContext(), "Keine Internetverbindung", "Es können keine Suchabfragen ohne Internet durchgeführt werden", "Ok");
                startActivity(new Intent(this.getContext(), MainActivity.class));
                return;
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

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            if(mItem.getBio() != "null"){
                ((TextView) rootView.findViewById(R.id.useractivity_detail)).setText(mItem.getBio());
            }
        }

        View recyclerView = rootView.findViewById(R.id.repositoryactivity_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        return rootView;
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new RepositoryRecyclerAdapter(UserRepositoryList));
    }


}
