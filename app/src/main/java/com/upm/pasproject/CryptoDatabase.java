package com.upm.pasproject;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.upm.pasproject.CryptoDao;

@Database(entities = {Crypto.class}, version = 1)

public abstract class CryptoDatabase extends RoomDatabase {
    public abstract CryptoDao cryptoDao();
}
