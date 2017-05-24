package com.gitwatch.gitwatch.app.ui;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gitwatch.gitwatch.R;
import com.gitwatch.gitwatch.core.Domain.Model.Branch;
import com.gitwatch.gitwatch.core.Domain.Model.Repository;
import com.gitwatch.gitwatch.infrastructure.github.Services.BrancheService;
import com.gitwatch.gitwatch.infrastructure.github.Services.RepositoryService;

import org.json.JSONException;

import java.util.List;

/**
 * A fragment representing a single RepositoryActivity detail screen.
 * This fragment is either contained in a {@link RepositoryActivityListActivity}
 * in two-pane mode (on tablets) or a {@link RepositoryActivityDetailActivity}
 * on handsets.
 */
public class RepositoryActivityDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Repository mItem;
    private List<Branch> mItemBranche;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RepositoryActivityDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.

            String id = getArguments().getString(ARG_ITEM_ID);

            try {
                mItem = (Repository) new RepositoryService().getById(Long.parseLong(id));
                mItemBranche = new BrancheService().getByRepository(id);
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
        View rootView = inflater.inflate(R.layout.repositoryactivity_detail, container, false);


        ((TextView) rootView.findViewById(R.id.owner)).setText(mItem.getOwner());
        ((TextView) rootView.findViewById(R.id.description)).setText(mItem.getDescription());

        return rootView;
    }
}
