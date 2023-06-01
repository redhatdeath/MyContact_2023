package ru.shanin.mycontact.fragments.persons;

import android.content.Intent;

import ru.shanin.domain.entity.Person;

public class ShareIntent {
    public static Intent share(Person person) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, person.getPersonInfo().getToSHA256());
        intent.setType("plain/text");
        return intent;
    }
}
