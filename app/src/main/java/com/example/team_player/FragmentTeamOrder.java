package com.example.team_player;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.team_player.databinding.FragmentTeamOrderBinding;

import java.util.ArrayList;
import java.util.List;

public class FragmentTeamOrder extends Fragment implements AdapterTeamEvaluated.Show {
    List<Team> teams;
    ViewModel vm;
    Team t;
    public FragmentTeamOrder() {
        // Required empty public constructor
    }



    public static FragmentTeamOrder newInstance(String param1, String param2) {
        FragmentTeamOrder fragment = new FragmentTeamOrder();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentTeamOrderBinding binding=FragmentTeamOrderBinding.inflate(
                inflater, container, false);
         vm=new ViewModelProvider(this).get(ViewModel.class);
         teams=new ArrayList<>();
         AdapterTeamEvaluated adapter=new AdapterTeamEvaluated(teams,this);
        binding.toggleButton.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
        if(checkedId==R.id.ascBT){
            vm.getOrderTeamASC().observe(getActivity(), new Observer<List<Team>>() {
                @Override
                public void onChanged(List<Team> teams1) {
                    teams=teams1;
                    adapter.setTeams(teams);
                    binding.recycler.setAdapter(adapter);
                    binding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));
                }
            });
        } else if (checkedId==R.id.descBT) {
            vm.getOrderTeamDESC().observe(getActivity(), new Observer<List<Team>>() {
                @Override
                public void onChanged(List<Team> teams1) {
                    teams=teams1;
                    adapter.setTeams(teams);
                    binding.recycler.setAdapter(adapter);
                    binding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));
                }
            });
        }
        });
        return binding.getRoot();
    }

    @Override
    public void showNumberOfCups(int id) {

        vm.getTeam(id).observe(getActivity(),team -> {
            t=team;
            if(t!=null){
               Toast.makeText(getActivity(), t.getNumberOfCups()+""
                       , Toast.LENGTH_SHORT).show();
            }
        });

    }
}