package com.gitwatch.gitwatch.app.ui.Repository;

import android.app.Activity;
import android.content.Context;
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
import com.gitwatch.gitwatch.app.ui.Commit.CommitActivityListActivity;
import com.gitwatch.gitwatch.core.Domain.Model.Branch;
import com.gitwatch.gitwatch.core.Domain.Model.Repository;
import com.gitwatch.gitwatch.infrastructure.github.Helpers.AlertHelper;
import com.gitwatch.gitwatch.infrastructure.github.Helpers.NetworkStateHelper;
import com.gitwatch.gitwatch.infrastructure.github.Services.BranchService;
import com.gitwatch.gitwatch.infrastructure.github.Services.RepositoryService;

import org.json.JSONException;

import java.util.List;

public class RepositoryActivityDetailFragment extends Fragment {
    public static final String ARG_ITEM_ID = "item_id";

    private Repository mItem;
    private List mItemBranch;

    // Empty Constructor is needed by Fragment initialisation, so do not delete!
    public RepositoryActivityDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {

            String id = getArguments().getString(ARG_ITEM_ID);
            try {
                mItem = (Repository) new RepositoryService().getById(Long.parseLong(id));
                mItemBranch = new BranchService().getByRepository(id);
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


        ((TextView) rootView.findViewById(R.id.commitmessage)).setText(mItem.getOwner());
        if(mItem.getDescription() == "null"){
            ((TextView) rootView.findViewById(R.id.description)).setText("Keine Beschreibung vorhanden");
        } else {
            ((TextView) rootView.findViewById(R.id.description)).setText(mItem.getDescription());
        }

        View recyclerView = rootView.findViewById(R.id.repositoryactivity_detail_branch);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        return rootView;
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(mItemBranch));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Branch> mValues;

        public SimpleItemRecyclerViewAdapter(List<Branch> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.repositoryactivity_detail_branch_content, parent, false);
            return new SimpleItemRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mName.setText(mValues.get(position).getName());
            holder.mItem = mValues.get(position);
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!NetworkStateHelper.hasNetwork(v.getContext())){
                        AlertHelper.showInfoAlert(v.getContext(), "Keine Internetverbindung", "Es können keine Suchabfragen ohne Internet durchgeführt werden", "Ok", null);
                        return;
                    }
                    Context context = v.getContext();
                    Intent intent = new Intent(context, CommitActivityListActivity.class);
                    intent.putExtra("branch", holder.mItem.getName());
                    intent.putExtra("repository", Long.toString(mItem.getId()));
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            if (mValues == null){
                return 0;
            }
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mName;
            public Branch mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mName = (TextView) view.findViewById(R.id.branchName);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mName.getText() + "'";
            }
        }
    }
}
