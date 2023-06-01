package ru.shanin.data.db_room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ru.shanin.data.db_room.entity.PersonRoom;

@Dao
public interface PersonDao {
    // INSERT NEW RECORD
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void _dbAddNew(PersonRoom personRoom);

    // DELETE RECORD BY ID
    @Query("DELETE FROM Persons WHERE Person_id = :id")
    void _dbRemove(String id);

    // SELECT ONE BY ID
    @Query("SELECT * FROM Persons WHERE Person_id = :id")
    PersonRoom _dbGetOne(String id);

    // SELECT ALL
    @Query("SELECT * FROM Persons ORDER BY Last_name,First_name,Second_name ASC")
    List<PersonRoom> _dbGetAll();
}