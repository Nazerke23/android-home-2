package com.example.secondhomework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterHolder> {

    private Context mContext;
    private List<java.lang.Character> characters;
    private OnItemNoteListener mOnItemNoteListener;

    public CharacterAdapter(List<java.lang.Character> charactersList, Context mContext, OnItemNoteListener mOnItemNoteListener) {
        this.mContext = mContext;
        this.characters = charactersList;
        this.mOnItemNoteListener = mOnItemNoteListener;
    }


    @NonNull
    @Override
    public CharacterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.character_record_item, parent, false);
        return new CharacterHolder(view, mOnItemNoteListener);

    }

    @Override
    public void onBindViewHolder(@NonNull CharacterHolder holder, int position) {

        holder.name.setText(characters.get(position).getName());
        holder.birthday.setText(characters.get(position).getBirthday());
        holder.status.setText(characters.get(position).getStatus());


        System.out.println(characters.get(position).getImg());
        Picasso.with(mContext)
                .load(characters.get(position).getImg())
                .placeholder(R.drawable.ic_baseline_person_24)
                .fit()
                .into(holder.image);


    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public static class CharacterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView birthday;
        TextView status;
        ImageView image;
        OnItemNoteListener noteListener;

        public CharacterHolder(View itemView, OnItemNoteListener noteListener) {
            super(itemView);
            name = itemView.findViewById(R.id.charName);
            birthday = itemView.findViewById(R.id.charbirthDay);
            status = itemView.findViewById(R.id.charStatus);
            image = itemView.findViewById(R.id.imageViewUser);
            this.noteListener = noteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            noteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnItemNoteListener{
        void onNoteClick(int position);
    }
}
