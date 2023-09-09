package com.example.team_player;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.team_player.databinding.ActivityTeamsBinding;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Date;

public class ActivityTeams extends AppCompatActivity implements FragmentTeam_Team.InsertTeam , FragmentTeam_Player.InsertPlayer , FragmentDialogAddAction.Done , AdapterTeamEvaluated.Show {

    ActivityTeamsBinding binding;
    FragmentManager manager;
    Team t;
    int numberOfCups;
    ViewModel vm;
    FragmentAdd add = new FragmentAdd();
    FragmentDialogAddAction fragmentDialogAddAction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeamsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FragmentHome_Tab fragmentHome = new FragmentHome_Tab();
        getSupportFragmentManager().beginTransaction().
                add(R.id.fragment, fragmentHome).commit();
        //هنا ما بدي أضيفها على ال back stace

        manager = getSupportFragmentManager();

        vm = new ViewModelProvider(this).get(ViewModel.class);


        binding.bottomBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    FragmentTransaction transaction1 = manager.beginTransaction();
                    transaction1.replace(R.id.fragment, new FragmentHome_Tab()).commit();
                    transaction1.addToBackStack("home");
                } else if (item.getItemId() == R.id.search) {
                    //هنا صار جاهز
                    FragmentTransaction transaction2 = manager.beginTransaction();
                    transaction2.replace(R.id.fragment, new FragmentSearch()).commit();
                    transaction2.addToBackStack("search");
                } else if (item.getItemId() == R.id.teamsOrder) {
                    FragmentTransaction transaction3 = manager.beginTransaction();
                    transaction3.replace(R.id.fragment,new FragmentTeamOrder()).commit();

                } else if (item.getItemId() == R.id.plus) {
                    //هنا جهزت الأكواد
                    FragmentTransaction transaction4 = manager.beginTransaction();
                    transaction4.replace(R.id.fragment, add).commit();
                    transaction4.addToBackStack("add");
                }
                return true;
            }
        });
    }
    @Override
    public void insertTeam(String name, int numberOfCups) {
        vm.insertTeam(new Team(name, numberOfCups));
    }

    @Override
    public void insertPlayer(String name, Bitmap bitmap, Date date, int idTeam, int shirtNumber) {
        vm.insertPlayer(new Player(name, shirtNumber, bitmap, date, idTeam));
    }

    @Override
    public void getTheDialog(String title) {
        fragmentDialogAddAction=FragmentDialogAddAction.newInstance(title);
        fragmentDialogAddAction.show(getSupportFragmentManager(),"dialog");
    }

    @Override
    public void done(boolean ok) {
        if(ok) {
            FragmentTransaction transaction1 = manager.beginTransaction();
            transaction1.replace(R.id.fragment, new FragmentHome_Tab()).commit();
            transaction1.addToBackStack("home");
            binding.bottomBar.setSelectedItemId(R.id.home);
            fragmentDialogAddAction.dismiss();

        }



    }

    @Override
    public void showNumberOfCups(int id) {
        vm.getTeam(id).observe(this,team -> {
            t=team;
            if(t!=null){
                numberOfCups=t.getNumberOfCups();
            }
        });
        Toast.makeText(this, numberOfCups+"", Toast.LENGTH_SHORT).show();

    }
}