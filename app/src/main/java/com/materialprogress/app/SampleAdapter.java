package com.materialprogress.app;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.materialprogress.R;
import com.paging.pagingadapter.PagingAdapter;

/*
This file is part of PagedRecyclerView

PagedRecyclerView is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Foobar is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
*/

public class SampleAdapter extends PagingAdapter<String> {

    public SampleAdapter() {
        super();
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return getItemViewType(position) == NORMAL_VIEW_TYPE ? getItem(position).hashCode() : -1;
    }

    @Override
    protected SampleHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.text_view_layout, null);
        return new SampleHolder(view);
    }

    @Override
    protected void onBindHolder(RecyclerView.ViewHolder viewHolder, int position) {
        SampleHolder holder = (SampleHolder) viewHolder;
        holder.textView.setText(mItems.get(position));
    }

    @Override
    protected int getLoadingLayout() {
        return R.layout.view_loading;
    }

    private class SampleHolder extends RecyclerView.ViewHolder {

        TextView textView;

        SampleHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}