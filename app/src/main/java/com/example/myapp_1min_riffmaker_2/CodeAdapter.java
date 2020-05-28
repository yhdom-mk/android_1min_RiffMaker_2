package com.example.myapp_1min_riffmaker_2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CodeAdapter extends RecyclerView.Adapter<CodeAdapter.CodeViewHolder> {

    private ArrayList<CodeItem> mCodelist;
    private OnItemClickListener mlistener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mlistener = listener;
    }

    //private View.OnClickListener mlistener;
    //private OnItemClickListener mlistener;   // + 20200415

    public CodeAdapter(ArrayList<CodeItem> codeItems) {
        mCodelist = codeItems;
    }

    public static class CodeViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mCodeview1;    //V to v ?
        public TextView mCodeview2;

        public CodeViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view_1);
            mCodeview1 = itemView.findViewById(R.id.code_view_1);
            mCodeview2 = itemView.findViewById(R.id.code_view_2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public CodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.code_item, parent, false);
        CodeViewHolder cvh = new CodeViewHolder(v, mlistener);
        return cvh;

        /*v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });  +20200417 */
    }

    @Override
    public void onBindViewHolder(@NonNull CodeViewHolder holder, final int position) {
        CodeItem currentItem = mCodelist.get(position);

        holder.mImageView.setImageResource(currentItem.getmImagesource());
        holder.mCodeview1.setText(currentItem.getmCodeview1());
        holder.mCodeview2.setText(currentItem.getmCodeview2());
    }

    @Override
    public int getItemCount() {
        return mCodelist.size();
    }
}
