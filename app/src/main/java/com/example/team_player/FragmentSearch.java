package com.example.team_player;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.team_player.databinding.FragmentSearchBinding;

import java.util.Date;
import java.util.List;

public class FragmentSearch extends Fragment {
    Player player;
    public FragmentSearch() {
        // Required empty public constructor
    }

    public static FragmentSearch newInstance(String param1, String param2) {
        FragmentSearch fragment = new FragmentSearch();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         FragmentSearchBinding binding= FragmentSearchBinding.inflate(
                 inflater, container, false);
         ViewModel vm=new ViewModelProvider(this).get(ViewModel.class);
         binding.textField.setEndIconOnClickListener(v -> {
             String name=binding.edNameSearch.getText().toString();

                 vm.getPlayerByName(name).observe(getActivity(), player1 -> {
                     if(player1!=null)
                     {
                         player = player1;
                         Date d = player.getDateOfBirth();
                         String date = d.getYear() + "/" + d.getMonth() + "/" + d.getDay();
                         binding.playerName.setText(player.getName());
                         binding.playerBOD.setText(date);


                         binding.playerShirtNum.setText(String.valueOf(player.getShirtNumber()));
                         int id = player.getIdTeam();
                         vm.getTeamName(id).observe(getActivity(), new Observer<String>() {
                             @Override
                             public void onChanged(String s) {
                                 binding.playerTeam.setText(s);
                             }
                         });
                     }
                     else{
                         Toast.makeText(getActivity(),
                                 "Not found this name", Toast.LENGTH_SHORT).show();
                     }
                 });
         });
        return binding.getRoot();
    }

}