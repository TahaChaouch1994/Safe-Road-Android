package com.example.projetandroidbinome;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetandroidbinome.entities.post;

import java.util.ArrayList;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.ExempleViewHolder> {

    private ArrayList<post> mExampleList;

    public static class ExempleViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView1;
        public ImageView mImageView2;

        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;


        public ExempleViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageView1 = itemView.findViewById(R.id.imagemappub);
            mImageView2 = itemView.findViewById(R.id.imagemappubface);

            mTextView1=itemView.findViewById(R.id.titrepub);
            mTextView2=itemView.findViewById(R.id.contenupub);
            mTextView3=itemView.findViewById(R.id.titrepubname);

        }
    }

    public PostListAdapter(ArrayList<post> exampleList) {
        mExampleList=exampleList;
    }

    @NonNull
    @Override
    public ExempleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.acceuil_list_item,parent,false);
        ExempleViewHolder evh = new ExempleViewHolder(v);
        return evh;

    }

    @Override
    public void onBindViewHolder(@NonNull ExempleViewHolder holder, int position) {

        post currentItem = mExampleList.get(position);
        holder.mImageView1.setImageResource(currentItem.getUrlMap());
        holder.mImageView2.setImageResource(currentItem.getUrlImage());
        holder.mTextView1.setText(currentItem.getTitre());
        holder.mTextView2.setText(currentItem.getContenu());
        holder.mTextView3.setText(currentItem.getNom());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
