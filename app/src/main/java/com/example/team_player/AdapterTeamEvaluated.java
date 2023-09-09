package com.example.team_player;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Ignore;

import com.example.team_player.databinding.RecyclerTeamEvaluatedBinding;

import java.util.List;

public class AdapterTeamEvaluated extends RecyclerView.Adapter<AdapterTeamEvaluated.ViewHolderTeamE> {

    List<Team> teams;
    Show show;

    @Ignore
    public AdapterTeamEvaluated(List<Team> teams, Show show) {
        this.teams = teams;
        this.show = show;
    }

    public AdapterTeamEvaluated(List<Team> teams) {
        this.teams = teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderTeamE onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerTeamEvaluatedBinding binding=RecyclerTeamEvaluatedBinding.inflate
                (LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolderTeamE(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTeamE holder, int position) {
      int pos=position;
        holder.tvName.setText(teams.get(pos).getName());
        holder.starBT.setOnClickListener(v ->
        {show.showNumberOfCups(teams.get(pos).getId());});
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    class ViewHolderTeamE extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageButton starBT;
        public ViewHolderTeamE(@NonNull RecyclerTeamEvaluatedBinding binding) {
            super(binding.getRoot());
                this.tvName=binding.tvNameT;
                this.starBT=binding.starBT;
        }
    }
    interface Show{
        void showNumberOfCups(int id);
    }
}
