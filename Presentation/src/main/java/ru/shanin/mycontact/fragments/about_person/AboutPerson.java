package ru.shanin.mycontact.fragments.about_person;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ru.shanin.app.AppStart;
import ru.shanin.domain.entity.Person;
import ru.shanin.mycontact.R;
import ru.shanin.mycontact.databinding.FragmentAboutPersonBinding;

public class AboutPerson extends Fragment {

    private FragmentAboutPersonBinding binding;
    private AboutPersonViewModel viewModel;
    private static final String ARGUMENT_PEOPLE_ID = "people id";
    private String personId;
    private Person person;

    private final int[] color = {0xAA55FF00, 0xAA550033, 0xAA550077, 0xAA5500AA, 0xAA5500FF};

    public static AboutPerson newInstance(
            String peopleId
    ) {
        Bundle args = new Bundle();
        args.putString(ARGUMENT_PEOPLE_ID, peopleId);
        AboutPerson fragment = new AboutPerson();
        fragment.setArguments(args);
        return fragment;
    }

    private void parseParams() {
        Bundle args = requireArguments();
        if (!args.containsKey(ARGUMENT_PEOPLE_ID))
            throw new RuntimeException("Argument '''People Id''' is absent");
        personId = args.getString(ARGUMENT_PEOPLE_ID);
    }

    @Override
    public void onCreate(
            @Nullable Bundle savedInstanceState
    ) {
        super.onCreate(savedInstanceState);
        parseParams();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = FragmentAboutPersonBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(
                this,
                new AboutPersonViewModelFactory(
                        AppStart.getInstance().getGetOneItem()
                ))
                .get(AboutPersonViewModel.class);
        viewModel.getPerson(personId).observe(
                getViewLifecycleOwner(),
                _person -> {
                    person = _person;
                    initView();
                }
        );
    }

    private int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    private void initView() {
        int colorId = (getYear(person.getPersonInfo().getDate()) - 2000) / 5;
        if (colorId > 0 && colorId < 5)
            binding.getRoot().setBackgroundColor(color[colorId]);
        else
            binding.getRoot().setBackgroundColor(color[0]);
        // Person Info
        binding.photo.setImageResource(R.drawable.ic_launcher_ca);
        binding.tvLn.setText(person.getPersonInfo().getLastName());
        binding.tvFn.setText(person.getPersonInfo().getFirstName());
        binding.tvSn.setText(person.getPersonInfo().getSecondName());
        binding.tvEmail.setText(person.getPersonInfo().getEmail());
        binding.tvPhone.setText(person.getPersonInfo().getPhone());
        binding.tvListOfKnowledge.setText(person.getPersonInfo().getListOfKnowledge().toString());
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String date = simpleDateFormat.format(person.getPersonInfo().getDate());
        binding.tvDate.setText(date);
    }
}