package com.gitwatch.gitwatch.app.ui.Commit;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gitwatch.gitwatch.R;
import com.gitwatch.gitwatch.core.Domain.Model.Commit;
import com.gitwatch.gitwatch.core.Domain.Model.User;
import com.gitwatch.gitwatch.infrastructure.github.Services.CommitService;

import org.json.JSONException;

import java.util.List;

public class CommitActivityListActivity extends AppCompatActivity {

    private List<Commit> commitList;
    private String branch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_list);

        this.branch = getIntent().getStringExtra("branch");
        String repositoryId = getIntent().getStringExtra("repository");

        try {
            commitList = new CommitService().getByRepoAndBranche(repositoryId,branch);
        } catch (JSONException e) {
            e.printStackTrace();
            finish();
        }

        TextView branchText = (TextView) findViewById(R.id.branch);
        branchText.setText(branch);

        View recyclerView = findViewById(R.id.commitactivity_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(commitList));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Commit> mValues;

        public SimpleItemRecyclerViewAdapter(List<Commit> items) {
            mValues = items;
        }

        @Override
        public SimpleItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.commitactivity_list_content, parent, false);
            return new ViewHolder(view, branch);
        }

        @Override
        public void onBindViewHolder(final SimpleItemRecyclerViewAdapter.ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            //holder.mContentView.setText(mValues.get(position).getName());
            holder.mCommiter.setText(mValues.get(position).getCommitter());
            holder.mCommitMessage.setText(mValues.get(position).getMessage());
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Vielleicht noch eine Funktion commit jemandem senden
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mCommiter;
            public final TextView mCommitMessage;
            public String branch;
            public Commit mItem;

            public ViewHolder(View view, String branch) {
                super(view);
                mView = view;
                mCommiter = (TextView) view.findViewById(R.id.commiter);
                mCommitMessage = (TextView) view.findViewById(R.id.commitmessage);
                this.branch = branch;
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mCommiter.getText() + "'";
            }
        }
    }
}
