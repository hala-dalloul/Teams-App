package com.example.team_player;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team_player.databinding.FragmentAddBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class FragmentAdd extends Fragment {
    AdapterViePagerAdd adapter;
    public FragmentAdd() {
        // Required empty public constructor
    }


    public static FragmentAdd newInstance(String param1, String param2) {
        FragmentAdd fragment = new FragmentAdd();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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
        FragmentAddBinding binding =FragmentAddBinding.inflate
                (inflater, container, false);

        ArrayList<String> tabs=new ArrayList<>();
        tabs.add("Player");
        tabs.add("Team");

        ArrayList<Fragment> fragments=new ArrayList<>();
        fragments.add(new FragmentTeam_Player());
        fragments.add(new FragmentTeam_Team());
        adapter=new AdapterViePagerAdd(getActivity(),fragments);

        binding.viewPagerAdd.setAdapter(adapter);
        new TabLayoutMediator(binding.tabBarAdd, binding.viewPagerAdd,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(tabs.get(position));
                    }
                }).attach();

        return binding.getRoot();
    }
}