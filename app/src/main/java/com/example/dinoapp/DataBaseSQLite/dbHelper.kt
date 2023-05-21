package com.example.dinoapp.DataBaseSQLite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns


class dbHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_LOCAL)

    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_LOCAL)
        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "localDb.db"

        const val SQL_CREATE_LOCAL = "CREATE TABLE ${DbContract.FeedEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${DbContract.FeedEntry.COLUMN_NAME_DINO_ID} INTEGER)"
        const val SQL_DELETE_LOCAL = "DROP TABLE IF EXISTS ${DbContract.FeedEntry.TABLE_NAME}"
    }

}