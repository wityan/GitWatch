package com.gitwatch.gitwatch.app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gitwatch.gitwatch.R;
import com.gitwatch.gitwatch.core.Domain.Model.Repository;
import com.gitwatch.gitwatch.infrastructure.github.Helpers.AlertHelper;
import com.gitwatch.gitwatch.infrastructure.github.Helpers.NetworkStateHelper;

import java.util.List;

/**
 * Created by bwitty on 24.05.2017.
 */

public class RepositoryRecyclerAdapter
        extends RecyclerView.Adapter<RepositoryRecyclerAdapter.ViewHolder> {

    private final List<Repository> mValues;

    public RepositoryRecyclerAdapter(List<Repository> items) {
        mValues = items;
    }

    @Override
    public RepositoryRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repositoryactivity_list_content, parent, false);
        return new RepositoryRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RepositoryRecyclerAdapter.ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).getName());
        holder.mOwner.setText(mValues.get(position).getOwner());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!NetworkStateHelper.hasNetwork(v.getContext())){
                    AlertHelper.showInfoAlert(v.getContext(), "Keine Internetverbindung", "Es können keine Suchabfragen ohne Internet durchgeführt werden", "Ok", null);
                    return;
                }
                Context context = v.getContext();
                Intent intent = new Intent(context, RepositoryActivityDetailActivity.class);
                intent.putExtra(RepositoryActivityDetailFragment.ARG_ITEM_ID, Long.toString(holder.mItem.getId()));

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
        public final TextView mContentView;
        public final TextView mOwner;
        public Repository mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.repositoryname);
            mOwner = (TextView) view.findViewById(R.id.commitmessage);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}