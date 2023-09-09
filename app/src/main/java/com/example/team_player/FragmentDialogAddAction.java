package com.example.team_player;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.team_player.databinding.FragmentDialogAddActionBinding;

public class FragmentDialogAddAction extends DialogFragment {


    private static final String TITLE_FOR_MESSAGE = "message";
    private String title;
    Done done;

    public FragmentDialogAddAction() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout
                (ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public static FragmentDialogAddAction newInstance(String title) {
        FragmentDialogAddAction fragment = new FragmentDialogAddAction();
        Bundle args = new Bundle();
        args.putString(TITLE_FOR_MESSAGE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Done)
            done=(Done) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(TITLE_FOR_MESSAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentDialogAddActionBinding binding=FragmentDialogAddActionBinding.
                inflate(inflater, container, false);
        binding.tvTitle.setText(title);
        binding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                done.done(true);
            }
        });

        return binding.getRoot();
    }
    interface Done{
        void done(boolean ok);
    }
}