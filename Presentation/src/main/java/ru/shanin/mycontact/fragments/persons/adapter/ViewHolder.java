package ru.shanin.mycontact.fragments.persons.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import ru.shanin.domain.entity.Person;
import ru.shanin.mycontact.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    private final OnPersonClickListener onClickListener;
    private final OnPersonLongClickListener onLongClickListener;

    public ViewHolder(
            View itemView,
            OnPersonClickListener onClickListener,
            OnPersonLongClickListener onLongClickListener
    ) {
        super(itemView);
        this.onClickListener = onClickListener;
        this.onLongClickListener = onLongClickListener;
    }

    public void setPersonData(Person person) {
        itemView.setOnClickListener(v -> onClickListener.onClick(person));
        itemView.setOnLongClickListener(v -> {
            onLongClickListener.onLongClick(person);
            return true;
        });
        String lastName = person.getPersonInfo().getLastName();
        String fsName = person.getPersonInfo().getFirstName() + " " + person.getPersonInfo().getSecondName();
        ((TextView) itemView.findViewById(R.id.tv_LastName)).setText(lastName);
        ((TextView) itemView.findViewById(R.id.tv_FSName)).setText(fsName);
        ((ImageView) itemView.findViewById(R.id.iv_photo)).setImageResource(R.drawable.ic_launcher_ca);
    }

    public interface OnPersonClickListener {
        void onClick(Person person);
    }

    public interface OnPersonLongClickListener {
        void onLongClick(Person person);
    }
}