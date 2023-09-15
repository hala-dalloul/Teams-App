package com.example.team_player;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.team_player.databinding.FragmentTeamPlayerBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FragmentTeam_Player extends Fragment implements AdapterSelectedTeam.ClickedTeam  {

    Bitmap bitmap;
    FragmentTeamPlayerBinding binding;
    InsertPlayer insertPlayer;
    ViewModel vm;
    AdapterSelectedTeam adapterSelectedTeam;
    List<Team> teams=new ArrayList<>();
    Date date;
    int idTeam;
    FragmentDialogAddAction fragmentDialogAddAction;


    ActivityResultLauncher<Intent> launcher= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK
                            && result.getData() != null ){
                        bitmap = (Bitmap) result.getData().getExtras().get("data");
                        binding.imagePlayer.setBackground(null);
                        binding.imagePlayer.setImageBitmap(bitmap);
                    }
                }

            });
    public FragmentTeam_Player() {
        // Required empty public constructor
    }


    public static FragmentTeam_Player newInstance(String param1, String param2) {
        FragmentTeam_Player fragment = new FragmentTeam_Player();
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof InsertPlayer)
            insertPlayer=(InsertPlayer) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         binding=FragmentTeamPlayerBinding.inflate(
                inflater, container, false);
        vm = new ViewModelProvider(this).get(ViewModel.class);


        adapterSelectedTeam=new AdapterSelectedTeam(teams,this::clickedTeam);
        binding.recycler.setAdapter(adapterSelectedTeam);
        binding.recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        vm.getAllTeams().observe(getActivity(),teams1 -> {
            teams=teams1;
            adapterSelectedTeam.setTeams(teams);

        });
        binding.imagePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                launcher.launch(intent);
            }
        });

        binding.calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                int y = calendar.get(Calendar.YEAR);
                int m = calendar.get(Calendar.MONTH) + 1;
                int d = calendar.get(Calendar.DAY_OF_MONTH);
                if (year >= y || month >= m || dayOfMonth >= d) {
                    date = new Date(year, month, dayOfMonth);
                } else {
                    date = null;
                }
            }
        });

        binding.saveBT.setOnClickListener(v -> {
            String name=binding.edName.getText().toString();
            String shirt=binding.edShirt.getText().toString();
            if(name!=null&& shirt !=null&&bitmap!=null && idTeam >0 && date!=null) {
              insertPlayer.insertPlayer(name, bitmap, date, idTeam,
                      Integer.parseInt(shirt));
                insertPlayer.getTheDialog("Insert player is completed");
                binding.edName.setText(null);
                binding.edShirt.setText(null);
                bitmap=null;
                binding.imagePlayer.setBackgroundResource(R.drawable.photo);
                idTeam=0;
                date=null;
            }
        });
        return binding.getRoot();
    }


    @Override
    public void clickedTeam(int id) {
        idTeam= teams.get(id-1).getId();
        Toast.makeText(getActivity(), "your team is:"
                +teams.get(id-1).getName(), Toast.LENGTH_SHORT).show();
    }



    interface  InsertPlayer{
        void insertPlayer(String name, Bitmap bitmap, Date date, int idTeam, int shirtNumber);
        void getTheDialog(String title);
    }
}