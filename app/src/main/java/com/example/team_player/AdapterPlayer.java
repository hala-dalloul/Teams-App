package com.example.team_player;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team_player.databinding.RecyclerPlayerBinding;

import java.util.List;

public class AdapterPlayer extends RecyclerView.Adapter<AdapterPlayer.ViewHolderPlayer> {
    List<Player> players;



    public AdapterPlayer(List<Player> players) {
        this.players = players;
        notifyDataSetChanged();
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @NonNull
    @Override
    public ViewHolderPlayer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerPlayerBinding binding=RecyclerPlayerBinding.inflate(
                LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolderPlayer(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPlayer holder, int position) {
        int pos=position;
        holder.imageView.setImageBitmap(players.get(pos).getPhoto());
        holder.tvName.setText(players.get(pos).getName());
        holder.tvShirtNumber.setText(String.valueOf(players.get(pos).getShirtNumber()));
        holder.tvDOB.setText(String.valueOf(players.get(pos).getDateOfBirth()));
        holder.tvTeam.setText(String.valueOf(players.get(pos).getIdTeam()));
    }

    @Override
    public int getItemCount() {
        return players.size();
    }


    class ViewHolderPlayer extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvName,tvDOB,tvShirtNumber,tvTeam;
        public ViewHolderPlayer(@NonNull RecyclerPlayerBinding binding) {
            super(binding.getRoot());
            this.imageView=binding.image;
            this.tvName=binding.tvName;
            this.tvDOB=binding.tvDOB;
            this.tvShirtNumber=binding.tvShirtNumber;
            this.tvTeam=binding.tvTeam;
        }
    }
}
