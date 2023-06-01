package ru.shanin.mycontact.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import ru.shanin.mycontact.databinding.ActivityMainBinding;
import ru.shanin.mycontact.fragments.persons.Persons;

public class ActivityMain extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        startFragmentListOfPeople(isOnePaneMode());
    }

    private boolean isOnePaneMode() {
        return binding.containerAboutPerson == null;
    }

    private void startFragmentListOfPeople(Boolean isOnePane) {
        Fragment fragment = Persons.newInstance(isOnePane);
        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager()
                .beginTransaction()
                .add(binding.containerPersons.getId(), fragment)
                .commit();
    }
}