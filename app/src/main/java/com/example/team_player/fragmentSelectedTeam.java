package com.example.team_player;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.team_player.databinding.FragmentSelectedTeamBinding;

import java.util.ArrayList;
import java.util.List;

public class fragmentSelectedTeam extends Fragment {


    private static final String TEAM_ID = "teamId";

    ViewModel vm;

    List<Player>players;
    AdapterPlayer adapterPlayer;
    int id;

    public fragmentSelectedTeam() {
        // Required empty public constructor
    }


    public static fragmentSelectedTeam newInstance(int id) {
        fragmentSelectedTeam fragment = new fragmentSelectedTeam();
        Bundle args = new Bundle();
        args.putInt(TEAM_ID,id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(TEAM_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentSelectedTeamBinding binding=FragmentSelectedTeamBinding.inflate
                (inflater, container, false);
        Log.d("TAG11", "onCreateView: "+id);
        players=new ArrayList<>();
        adapterPlayer=new AdapterPlayer(players);

        vm=new ViewModelProvider(getActivity()).get(ViewModel.class);
        vm.getPlayersByTeamId(id).observe(getActivity(), new Observer<List<Player>>() {
            @Override
            public void onChanged(List<Player> players1) {
                players=players1;
                adapterPlayer.setPlayers(players1);
                binding.recyclerPlayer.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.recyclerPlayer.setAdapter(adapterPlayer);
            }
        });

        return binding.getRoot();
    }
}