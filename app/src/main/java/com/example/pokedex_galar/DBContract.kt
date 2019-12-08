package com.example.pokedex_galar

import android.provider.BaseColumns

object DBContract {
    class PokemonEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "pokemons"
            const val COLUMN_NAME_POKEMON_NAME = "name"
            const val COLUMN_NAME_TYPE1 = "type1"
        }
    }
}