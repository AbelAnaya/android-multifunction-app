package com.upm.pasproject;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Crypto {

    @PrimaryKey(autoGenerate = true)
    int Id;
    String crypto;
    String value;
    String icon_url;
}
