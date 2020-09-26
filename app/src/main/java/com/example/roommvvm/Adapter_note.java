package com.example.roommvvm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Adapter_note extends RecyclerView.Adapter <Adapter_note.MyViewHolder> {
    Context context;
   private List<Note> notes=new ArrayList<>();
   private OnItemClickListener listener;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemnote, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Adapter_note.MyViewHolder holder, final int position) {

  holder.txt_title.setText(notes.get(position).getTitle());
  holder.txt_dec.setText(notes.get(position).getDescription());
  holder.priority.setText(String.valueOf(notes.get(position).getPriority()));

  holder.itemview.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          if(listener!=null && position!=RecyclerView.NO_POSITION)
       listener.onItemClick(notes.get(position));
      }
  });
    }

public  void setNotes(List<Note>notes){
  this.notes=notes;
  notifyDataSetChanged();
}
 public Note getNoteAt(int position){
        return notes.get(position);
 }
    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView priority, txt_title,txt_dec;
        CardView itemview;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            priority = itemView.findViewById(R.id.priority);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_dec = itemView.findViewById(R.id.txt_dec);
            itemview = itemView.findViewById(R.id.itemview);
        }
    }

    public  interface OnItemClickListener{
        void onItemClick(Note note);
    }

    public void seOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }
}
