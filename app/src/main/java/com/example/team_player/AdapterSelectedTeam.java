package com.example.team_player;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team_player.databinding.RecyclerTeamSlectedBinding;

import java.util.List;

public class AdapterSelectedTeam extends RecyclerView.Adapter<AdapterSelectedTeam.ViewHolderTeam> {
    List<Team> teams;
    ClickedTeam clickedTeam;

    public void setTeams(List<Team> teams) {
        this.teams = teams;
        notifyDataSetChanged();
    }


    public AdapterSelectedTeam(List<Team> teams, ClickedTeam clickedTeam) {
        this.teams = teams;
        this.clickedTeam = clickedTeam;
    }

    @NonNull
    @Override
    public ViewHolderTeam onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       RecyclerTeamSlectedBinding binding=RecyclerTeamSlectedBinding.inflate
               (LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolderTeam(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTeam holder, int position) {
        int pos=position;
        holder.b.getRoot().setOnClickListener(v -> {
            clickedTeam.clickedTeam(teams.get(pos).getId());
        });
        holder.tvName.setText(teams.get(pos).getName());
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }


    class ViewHolderTeam extends RecyclerView.ViewHolder {
        TextView tvName;
        RecyclerTeamSlectedBinding b;
        public ViewHolderTeam(RecyclerTeamSlectedBinding binding) {
            super(binding.getRoot());
            this.b=binding;
            tvName=binding.tvName;
        }
    }
    interface ClickedTeam{
        void clickedTeam(int id);
    }
}
