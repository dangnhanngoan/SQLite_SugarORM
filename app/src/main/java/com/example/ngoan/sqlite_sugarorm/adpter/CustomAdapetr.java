package com.example.ngoan.sqlite_sugarorm.adpter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ngoan.sqlite_sugarorm.R;
import com.example.ngoan.sqlite_sugarorm.jobsmodel.Jobs;

import java.util.List;

public class CustomAdapetr extends RecyclerView.Adapter<CustomAdapetr.ViewHolder>{

    private List<Jobs> arrJobs;
    public OnClickItemListener onClickItemListener;

    public CustomAdapetr(List<Jobs> arrJobs) {
        this.arrJobs = arrJobs;
    }

    public interface OnClickItemListener{
        void onItemClick(View v, int position);
    }

    public void removeAt(int position) {
        arrJobs.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, arrJobs.size());
    }

    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvItem.setText(arrJobs.get(position).getJobTitle());
    }

    @Override
    public int getItemCount() {
        return arrJobs == null ? 0 : arrJobs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItem;

        public ViewHolder(View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tv_item);
            itemView.setOnClickListener(view -> {
                onClickItemListener.onItemClick(view,getAdapterPosition());
            });
        }
    }

}
