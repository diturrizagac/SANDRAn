package com.example.scoreregisterapp.presentation.custom.adapter;

import android.content.Context;
import android.graphics.Typeface;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.scoreregisterapp.R;
import com.example.scoreregisterapp.presentation.custom.model.dashboard;

import java.util.List;

/**
 * Created byTrending Design on 23/03/19.
 */
public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.MyViewHolder> {

    private Context mContext;
    private List<dashboard> dashboardList;

    Typeface tf;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvtitle;
        ImageView imgMenu;
        CardView cdview;

        public MyViewHolder(View view) {
            super(view);

            tvtitle = (TextView) view.findViewById(R.id.tvmenutitle);

            imgMenu=(ImageView)view.findViewById(R.id.imgmenu);

            cdview = (CardView) view.findViewById(R.id.card_view);
        }
    }

    public DashboardAdapter(Context mContext, List<dashboard> dashboardList) {
        this.mContext = mContext;
        this.dashboardList = dashboardList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.raw_menu, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        //FetchImages();

        //imageserver=imageList;
//        tf = Typeface.createFromAsset(mContext.getAssets(), "fonts/saira_regular.ttf");
//        tf = Typeface.createFromAsset(mContext.getAssets(), "fonts/proximanovaregular.ttf");
//        holder.tvtitle.setTypeface(tf);

        final dashboard dashboard = dashboardList.get(position);
        holder.tvtitle.setText(dashboard.getTitle());

        Glide.with(mContext)
                .load(dashboard.getImg())
                .into(holder.imgMenu);

        holder.cdview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

    }

    @Override
    public int getItemCount() {
        return dashboardList.size();
    }

}