package com.upm.pasproject;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import java.util.ArrayList;

@Dao
public interface CryptoDao {

    @Update
    void update(Crypto crypto);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ArrayList<Crypto> crypto);

}
