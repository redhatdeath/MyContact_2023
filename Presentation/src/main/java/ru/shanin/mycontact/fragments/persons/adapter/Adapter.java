package ru.shanin.mycontact.fragments.persons.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import java.util.Calendar;
import java.util.Date;

import ru.shanin.domain.entity.Person;
import ru.shanin.mycontact.R;

public class Adapter extends ListAdapter<Person, ViewHolder> {
    public static final int MAX_POOL_SIZE = 10;
    public static final int VIEW_TYPE_PEOPLE_AGE_1 = 100;
    public static final int VIEW_TYPE_PEOPLE_AGE_2 = 200;
    public static final int VIEW_TYPE_PEOPLE_AGE_3 = 300;
    public static final int VIEW_TYPE_PEOPLE_AGE_4 = 400;
    public static final int VIEW_TYPE_PEOPLE_AGE_5 = 500;
    private final ViewHolder.OnPersonClickListener onPersonClickListener;
    private final ViewHolder.OnPersonLongClickListener onPersonLongClickListener;

    public Adapter(
            ViewHolder.OnPersonClickListener onPersonClickListener,
            ViewHolder.OnPersonLongClickListener onPersonLongClickListener
    ) {
        super(new DiffCallback());
        this.onPersonClickListener = onPersonClickListener;
        this.onPersonLongClickListener = onPersonLongClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        int age = getYear(getItem(position).getPersonInfo().getDate()) - 2000;
        switch (age / 5) {
            case 0:
                return VIEW_TYPE_PEOPLE_AGE_1;
            case 1:
                return VIEW_TYPE_PEOPLE_AGE_2;
            case 2:
                return VIEW_TYPE_PEOPLE_AGE_3;
            case 3:
                return VIEW_TYPE_PEOPLE_AGE_4;
            default:
                return VIEW_TYPE_PEOPLE_AGE_5;
        }
    }

    private int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout;
        switch (viewType) {
            case VIEW_TYPE_PEOPLE_AGE_1:
                layout = R.layout.person_age_1;
                break;
            case VIEW_TYPE_PEOPLE_AGE_2:
                layout = R.layout.person_age_2;
                break;
            case VIEW_TYPE_PEOPLE_AGE_3:
                layout = R.layout.person_age_3;
                break;
            case VIEW_TYPE_PEOPLE_AGE_4:
                layout = R.layout.person_age_4;
                break;
            case VIEW_TYPE_PEOPLE_AGE_5:
                layout = R.layout.person_age_5;
                break;
            default:
                throw new RuntimeException("Unknown view type " + viewType);
        }
        View view = LayoutInflater
                .from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(view, onPersonClickListener, onPersonLongClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.setPersonData(getItem(position));
    }

    private static void showLog(String s) {
        Log.d("showLog", s);
    }

}