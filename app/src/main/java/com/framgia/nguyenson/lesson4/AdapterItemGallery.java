package com.framgia.nguyenson.lesson4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class AdapterItemGallery extends RecyclerView.Adapter<AdapterItemGallery.ViewHolder> {
   ArrayList<Bitmap> mList;
    public AdapterItemGallery(ArrayList<Bitmap> list) {
        this.mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View root = layoutInflater.inflate(R.layout.item_gallery,parent,false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterItemGallery.ViewHolder holder, int position) {
        holder.mImageView.setImageBitmap(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_gallery);
        }
    }
}
