package ru.shanin.mycontact.fragments.persons;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import ru.shanin.app.AppStart;
import ru.shanin.domain.entity.Person;
import ru.shanin.mycontact.R;
import ru.shanin.mycontact.databinding.FragmentPersonsBinding;
import ru.shanin.mycontact.fragments.about_person.AboutPerson;
import ru.shanin.mycontact.fragments.persons.adapter.Adapter;

public class Persons extends Fragment {
    private FragmentPersonsBinding binding;
    private static final String ARGUMENT_IS_ONE_PANE_STATE = "is one pane";
    private Boolean isOnePane;
    private PersonsViewModel viewModel;
    private ItemTouchHelper peopleTouchHelper;
    private Adapter adapter;

    public static Persons newInstance(Boolean isOnePane) {
        Bundle args = new Bundle();
        args.putBoolean(ARGUMENT_IS_ONE_PANE_STATE, isOnePane);
        Persons fragment = new Persons();
        fragment.setArguments(args);
        return fragment;
    }

    private void parseParams() {
        Bundle args = requireArguments();
        if (!args.containsKey(ARGUMENT_IS_ONE_PANE_STATE))
            throw new RuntimeException("Arguments are absent");
        isOnePane = args.getBoolean(ARGUMENT_IS_ONE_PANE_STATE);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseParams();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = FragmentPersonsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        initViewModel();
        initView(view);
    }

    private void initAdapter() {
        adapter = new Adapter(
                this::startAboutPeople,
                this::newShare
        );
        initSwipe();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(
                this,
                new PersonsViewModelFactory(
                        AppStart.getInstance().getAddItem(),
                        AppStart.getInstance().getRemoveItem(),
                        AppStart.getInstance().getGetListItems()
                ))
                .get(PersonsViewModel.class);
        viewModel
                .getAll()
                .observe(
                        getViewLifecycleOwner(),
                        peoples -> adapter.submitList(peoples)
                );
    }

    private void initSwipe() {
        peopleTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(
                        0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
                ) {
                    @Override
                    public boolean onMove(
                            @NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder,
                            @NonNull RecyclerView.ViewHolder target
                    ) {
                        return false;
                    }

                    @Override
                    public void onSwiped(
                            @NonNull RecyclerView.ViewHolder viewHolder,
                            int direction
                    ) {
                        Person person = adapter.getCurrentList().get(viewHolder.getAdapterPosition());
                        viewModel.deletePeople(person.getId());
                    }
                });
    }

    private void setupRecyclerView() {
        binding.rvPersons.getRecycledViewPool().setMaxRecycledViews(
                Adapter.VIEW_TYPE_PEOPLE_AGE_1, Adapter.MAX_POOL_SIZE);
        binding.rvPersons.getRecycledViewPool().setMaxRecycledViews(
                Adapter.VIEW_TYPE_PEOPLE_AGE_2, Adapter.MAX_POOL_SIZE);
        binding.rvPersons.getRecycledViewPool().setMaxRecycledViews(
                Adapter.VIEW_TYPE_PEOPLE_AGE_3, Adapter.MAX_POOL_SIZE);
        binding.rvPersons.getRecycledViewPool().setMaxRecycledViews(
                Adapter.VIEW_TYPE_PEOPLE_AGE_4, Adapter.MAX_POOL_SIZE);
        binding.rvPersons.getRecycledViewPool().setMaxRecycledViews(
                Adapter.VIEW_TYPE_PEOPLE_AGE_5, Adapter.MAX_POOL_SIZE);
    }

    private void initView(View view) {
        binding.fab.setOnClickListener(v -> viewModel.addNew());
        binding.rvPersons.setAdapter(adapter);
        setupRecyclerView();
        peopleTouchHelper.attachToRecyclerView(binding.rvPersons);

    }

    private void startAboutPeople(Person person) {
        Fragment fragment = AboutPerson.newInstance(
                person.getId());
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.popBackStack();
        if (isOnePane)
            fragmentManager
                    .beginTransaction()
                    .addToBackStack("null")
                    .replace(R.id.container_persons, fragment, null)
                    .commit();
        else
            fragmentManager
                    .beginTransaction()
                    .addToBackStack("null")
                    .replace(R.id.container_about_person, fragment, null)
                    .commit();
    }

    private void newShare(Person person) {
        Intent intent = ShareIntent.share(person);
        startActivity(Intent.createChooser(intent, "Поделится ^-^"));
    }
}