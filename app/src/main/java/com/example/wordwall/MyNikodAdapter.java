package com.example.wordwall;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyNikodAdapter extends RecyclerView.Adapter<MyNikodAdapter.MyViewHolder> {
    private ArrayList<Char> characters;
    private OnItemClickListener listener;

    public MyNikodAdapter(ArrayList<Char> characters, OnItemClickListener listener) {
        this.characters = characters;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.xml_view, parent, false);
        return new MyViewHolder(view);
    }

    public void isvisible() {
//        cha.setTextColor(000000);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(characters.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Character item);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView cha;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cha = itemView.findViewById(R.id.cha);
        }

        public void bind(final Char item, final OnItemClickListener listener) {
            cha.setText(item.getCha());

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    listener.onItemClick(item);
//                }
//            });
        }
    }

}


