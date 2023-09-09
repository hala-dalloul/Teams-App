package com.example.team_player;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team_player.databinding.FragmentTeamTeamBinding;


public class FragmentTeam_Team extends Fragment {
    private String name;
    private String numberOfCups;
    int number=0;
    InsertTeam insertTeam;

    public static FragmentTeam_Team newInstance(String param1, String param2) {
        FragmentTeam_Team fragment = new FragmentTeam_Team();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentTeam_Team() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof InsertTeam)
         insertTeam=(InsertTeam) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentTeamTeamBinding binding=FragmentTeamTeamBinding.inflate
                (inflater, container, false);
        binding.edCups.setText(number+"");
        binding.plus.setOnClickListener(v -> { ++number;binding.edCups.setText(number+""); });
        binding.minus.setOnClickListener(v -> { if(number>0) {--number; binding.edCups.setText(number+"");}});

        binding.saveBT.setOnClickListener(v -> {
            name =binding.edName.getText().toString();
            numberOfCups =binding.edCups.getText().toString();
            if(name != null &&numberOfCups != null ) {
                insertTeam.getTheDialog("Insert this team is completed");
                insertTeam.insertTeam(name, Integer.parseInt(numberOfCups));
                binding.edName.setText(null);
                binding.edCups.setText(null);
            }

        });

        return binding.getRoot();
    }
    interface InsertTeam{
        void insertTeam(String name,int numberOfCups);
        void getTheDialog(String title);
    }
}