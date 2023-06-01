package ru.shanin.data.generate;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

import ru.shanin.domain.entity.Person;
import ru.shanin.domain.entity.PersonInfo;

public class NewData {

    private static String genNamePhoto() {
        // Field[] drawablesFields = R.drawable.class.getFields();
        ArrayList<String> imageName = new ArrayList<>();
        // for (Field field : drawablesFields)
        //      if (field.getName().length() == 4)
        //      imageName.add(field.getName());
        for (int i = 1; i < 14; i++)
            if (i < 10) imageName.add("a00" + i);
            else imageName.add("a0" + i);
        // Из имени ресурса получить идентификатор
        // String mDrawableName = "name"; // файл name.png в папке drawable
        // int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
        return imageName.get((int) (Math.random() * imageName.size()));
    }

    private static String genNumber() {
        String digits = "0123456789";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 9; i++)
            result.append(digits.charAt((int) (Math.random() * 10)));
        return result.toString();
    }

    private static String genPhoneNumber() {
        return "+79" + genNumber();
    }

    private static String genString(int length) {
        String alphabetInUpperCase = "QWERTYUIOPASDFGHJKLZXCVBNM";
        String alphabetInLowerCase = "qwertyuiopasdfghjklzxcvbnm";
        StringBuilder result = new StringBuilder();
        result.append(alphabetInUpperCase.charAt((int) (Math.random() * 26)));
        for (int i = 0; i < length - 1; i++)
            result.append(alphabetInLowerCase.charAt((int) (Math.random() * 26)));
        return result.toString();
    }

    private static String genLastName() {
        return genString((int) (6 + Math.random() * 5));
    }

    private static String genFirstName() {
        return genString((int) (4 + Math.random() * 5));
    }

    private static String genSecondName() {
        return genString((int) (8 + Math.random() * 5));
    }

    private static ArrayList<String> genListOfKnowledge() {
        String[] knowledge = {
                " C++/CLI ", " C# ", " Object Pascal ", " Groovy ",
                " Java ", " JavaScript ", " Objective-C ", " Perl ",
                " PHP ", " Python ", " Ruby ", " Swift ",
                " Visual Basic ", " Ada ", " Erlang ", " Gentee ",
                " Haskell ", " Scheme ", " Лисп ", " Kotlin ",
                " Curry ", " Delphi ", " Rust ", " Scala "
        };
        ArrayList<String> listOfKnowledge = new ArrayList<>();
        for (int i = 0; i < (int) (Math.random() * 3 + 1); i++)
            listOfKnowledge.add(knowledge[(int) (Math.random() * knowledge.length)]);
        Collections.sort(listOfKnowledge);
        return listOfKnowledge;
    }

    private static Date genDate() {
        Random rnd = new Random();
        // Get an Epoch value roughly between 2000 and 2020
        long live_ms = 946690000000L + Math.abs(rnd.nextLong()) % (20L * 365 * 24 * 60 * 60 * 1000);
        //showLog(String.valueOf(live_ms));
        // Construct a date
        Date date = new Date(live_ms);
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        //String format = simpleDateFormat.format(date);
        //showLog(format);
        return date;
    }


    public static Person newPerson() {
        String ln = genLastName();
        String fn = genFirstName();
        String sn = genSecondName();
        return new Person(new PersonInfo(
                ln, fn, sn,
                genDate(),
                ln + "_" + fn + "@gmail.com",
                genPhoneNumber(), genListOfKnowledge())
        );
    }

    private static void showLog(String s) {
        Log.d("showLog", s);
    }
}