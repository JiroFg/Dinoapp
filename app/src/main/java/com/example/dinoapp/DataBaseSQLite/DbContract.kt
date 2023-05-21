package com.example.dinoapp.DataBaseSQLite

import android.provider.BaseColumns

object DbContract {

    object FeedEntry : BaseColumns {
        const val TABLE_NAME = "localDb"
        const val COLUMN_NAME_DINO_ID = "DinoID"
    }
}