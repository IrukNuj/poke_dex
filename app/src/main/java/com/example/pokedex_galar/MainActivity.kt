package com.example.pokedex_galar

import android.content.Context
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        show()
    }

    private fun show() {
        val texts = getData(this)
        val listView = findViewById<ListView>(R.id.pokemons_list)
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, texts)
        listView.adapter = adapter
        Log.d(null, "ほげほげほげほげ")
        Log.d(null,texts.toString())
//        val a = PokedexDatabaseHelper(this).readableDatabase.rawQuery("SELECT * FROM " + DBContract.PokemonEntry.TABLE_NAME, null)
//        Log.d(null, a.getString().toString())
    }

}
