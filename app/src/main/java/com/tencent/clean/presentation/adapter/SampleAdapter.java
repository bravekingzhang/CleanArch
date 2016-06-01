package com.tencent.clean.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tencent.clean.R;
import com.tencent.clean.data.model.SampleModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hoollyzhang on 16/5/30.
 * Description :
 */
public class SampleAdapter extends RecyclerView.Adapter {

    public void setImages(List<SampleModel> images) {
        this.images = images;
        notifyDataSetChanged();
    }

    List<SampleModel> images;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_item,parent,false);
        return new SampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SampleViewHolder viewholder = (SampleViewHolder) holder;
        SampleModel simpleModel = images.get(position);
        Glide.with(viewholder.imagevi.getContext()).load(simpleModel.getUrl()).into(viewholder.imagevi);
        viewholder.descriptionTv.setText(simpleModel.getDesc());
    }

    @Override
    public int getItemCount() {
        return images == null ? 0 : images.size();
    }

    static class SampleViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.imageIv)
        ImageView imagevi;
        @Bind(R.id.descriptionTv)
        TextView descriptionTv;

        public SampleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
