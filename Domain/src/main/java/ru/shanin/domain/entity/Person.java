package ru.shanin.domain.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;

import ru.shanin.domain.entity.comparators.ComparatorByLFSNames;

public class Person {
    public static final Comparator<Person> byLFN = new ComparatorByLFSNames();
    private final PersonInfo personInfo;
    private final String id;


    // Constructor for exist "room's database entity"
    public Person(String id, PersonInfo personInfo) {
        this.personInfo = personInfo;
        this.id = id;
    }

    // Constructor by default for new objects
    public Person(PersonInfo personInfo) {
        this.personInfo = personInfo;
        this.id = createId();
    }

    private String createId() {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No SHA256 Algorithm");
        }
        byte[] encodedHash = digest.digest(
                personInfo.getToSHA256().getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
        for (byte hash : encodedHash) {
            String hex = Integer.toHexString(0xff & hash);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    @NonNull
    @Override
    public String toString() {
        return personInfo.toString();
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Person guest = (Person) obj;
        return id.equals(guest.getId())
                && (personInfo == guest.getPersonInfo()
                || (personInfo != null && personInfo.equals(guest.getPersonInfo()))
        );
    }
}
