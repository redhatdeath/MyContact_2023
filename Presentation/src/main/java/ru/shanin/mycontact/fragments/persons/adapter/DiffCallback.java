package ru.shanin.mycontact.fragments.persons.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import ru.shanin.domain.entity.Person;

public class DiffCallback extends DiffUtil.ItemCallback<Person> {
    @Override
    public boolean areItemsTheSame(@NonNull Person oldPerson, @NonNull Person newPerson) {
        return (oldPerson.getId()).equals(newPerson.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Person oldPerson, @NonNull Person newPerson) {
        return oldPerson.equals(newPerson);
    }
}
