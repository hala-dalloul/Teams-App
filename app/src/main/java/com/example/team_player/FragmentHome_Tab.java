package com.example.team_player;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.team_player.databinding.FragmentHomeTabBinding;
import com.example.team_player.databinding.FragmentSelectedTeamBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome_Tab extends Fragment {
    public FragmentHome_Tab() {
        // Required empty public constructor
    }
    public static FragmentHome_Tab newInstance(String param1, String param2) {
        FragmentHome_Tab fragment = new FragmentHome_Tab();
        Bundle args = new Bundle();
         fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    ViewModel vm;
    List<String> teamName =new ArrayList<>();
    AdapterViewPagerHome adapterVP;
    List<Fragment> fragments =new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentHomeTabBinding binding=FragmentHomeTabBinding.
                inflate(inflater, container, false);
       vm=new ViewModelProvider(getActivity()).get(ViewModel.class);
       vm.getAllTeamName().observe(getActivity(), new Observer<List<String>>() {
           @Override
           public void onChanged(List<String> strings) {
               teamName=strings;
               vm.getAllTeams().observe(getActivity(), new Observer<List<Team>>() {
                   @Override
                   public void onChanged(List<Team> teams) {
                       for (int i = 0; i < strings.size(); i++) {
                           if(strings.size()>fragments.size())
                                       fragments.add(fragmentSelectedTeam.newInstance
                                                   (teams.get(i).getId()));
                       }
                       adapterVP=new AdapterViewPagerHome(getActivity(),fragments);
                       adapterVP.setFragments(fragments);
                       binding.vpHome.setAdapter(adapterVP);
                       new TabLayoutMediator(binding.tabBarHome, binding.vpHome
                               , new TabLayoutMediator.TabConfigurationStrategy() {
                           @Override
                           public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                               tab.setText(teamName.get(position));
                           }
                       }).attach();
                   }
               });
           }
       });
        return binding.getRoot();
    }

}