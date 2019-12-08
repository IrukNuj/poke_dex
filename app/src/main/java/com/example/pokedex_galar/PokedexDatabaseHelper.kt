package com.example.pokedex_galar

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class PokedexDatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    @Throws(SQLiteException::class)

    fun insertData(db: SQLiteDatabase, data: PokedexModel): Boolean {
        val values = ContentValues()
        values.put(DBContract.PokemonEntry.COLUMN_NAME_POKEMON_NAME, data.name)
        values.put(DBContract.PokemonEntry.COLUMN_NAME_TYPE1, data.type1)

        db.insert(DBContract.PokemonEntry.TABLE_NAME, null, values)

        return true
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
        db.execSQL("INSERT INTO " + DBContract.PokemonEntry.TABLE_NAME + " VALUES(1,'ピカチュウ','でんき')")
//        insertData(db, PokedexModel("ピカチュウ", "でんき"))
//        insertData(db, PokedexModel("ピカチュウ", "でんき"))
//        insertData(db, PokedexModel("ピカチュウ", "でんき"))
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        db?.execSQL(SQL_CREATE_ENTRIES)
    }


    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "POKEMONS.db"

        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBContract.PokemonEntry.TABLE_NAME + "(" +
                DBContract.PokemonEntry.COLUMN_NAME_POKEMON_NAME + "TEXT ," +
                DBContract.PokemonEntry.COLUMN_NAME_TYPE1 + "TEXT)"
        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBContract.PokemonEntry.TABLE_NAME
    }
}

fun getData(context: Context) : List<String> {
    val db = PokedexDatabaseHelper(context).readableDatabase
    val cursor : Cursor = db.rawQuery("SELECT * FROM " + DBContract.PokemonEntry.TABLE_NAME, null)

    val texts = mutableListOf<String>()
    Log.d("DBの中身","")

    cursor.use {
        while (cursor.moveToNext()) {
            val text = cursor.getString((cursor.getColumnIndex(("name"))))
            Log.d("DBの中身",text)
            texts.add(text)
        }
    }

    texts.add("hogehoge")
    db.close()
    return texts
}
