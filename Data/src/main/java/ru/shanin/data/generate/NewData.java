package ru.shanin.data.generate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;

import ru.shanin.domain.entity.Knowledge;
import ru.shanin.domain.entity.Person;
import ru.shanin.domain.entity.PersonInfo;

public class NewData {
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

    private static ArrayList<Knowledge> genListOfKnowledge() {
        String[] knowledge = {
                " C++ ", " CLI ", " C ", " Object_Pascal ", " Groovy ",
                " Java ", " JavaScript ", " Objective_C ", " Perl ",
                " PHP ", " Python ", " Ruby ", " Swift "," GO ",
                " Visual_Basic ", " Ada ", " Erlang ", " Gentee ",
                " Haskell ", " Scheme ", " Лисп ", " Kotlin ",
                " Curry ", " Delphi ", " Rust ", " Scala "
        };
        ArrayList<Knowledge> listOfKnowledge = new ArrayList<>();
        for (int i = 0; i < (int) (Math.random() * 3 + 1); i++) {
            int id = (int) (Math.random() * knowledge.length);
            listOfKnowledge.add(new Knowledge(id, knowledge[id]));
        }
        listOfKnowledge.sort(Comparator.comparing(Knowledge::getNameKnowledge));
        return listOfKnowledge;
    }

    private static Date genDate() {
        Random rnd = new Random();
        long live_ms = 946690000000L + Math.abs(rnd.nextLong()) % (20L * 365 * 24 * 60 * 60 * 1000);
        return new Date(live_ms);
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
}