package servando.contreras.composepokedex_contrerasjimenezservando.data.sqllite

import android.content.ContentValues
import android.database.Cursor
import androidx.core.database.getBlobOrNull
import androidx.core.database.getFloatOrNull
import androidx.core.database.getIntOrNull
import androidx.core.database.getStringOrNull
import servando.contreras.composepokedex_contrerasjimenezservando.R
import servando.contreras.composepokedex_contrerasjimenezservando.domain.Pokemon

class PokemonMapper {

    fun getPokemon(cursor: Cursor): Pokemon {
        val num = cursor.getInt(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_NUMBER))
        val name = cursor.getString(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_NAME))
        val description = cursor.getString(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_DESCRIPTION))
        val type = cursor.getString(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_TYPE))
        val height = cursor.getFloatOrNull(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_HEIGHT))
        val weight = cursor.getFloatOrNull(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_WEIGHT))
        val fav = cursor.getIntOrNull(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_FAVORITE))
        val image = cursor.getBlobOrNull(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_IMAGE))
        val ability = cursor.getStringOrNull(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_ABILITY))

        return Pokemon(
            name = name,
            number = num,
            type = type,
            description = description,
            height = height ?: 0f,
            weight = weight ?: 0f,
            favorite = if (fav == null || fav == 0) false else true,
            ability = ability ?: "",
            image = R.drawable.wtp,
            imageSQL = image
        )
    }

    fun getContentValues(pokemon: Pokemon): ContentValues {
        val values = ContentValues().apply {
            put(Contract.Pokemons.COLUMN_NAME_NUMBER, pokemon.number)
            put(Contract.Pokemons.COLUMN_NAME_NAME, pokemon.name)
            put(Contract.Pokemons.COLUMN_NAME_DESCRIPTION, pokemon.description)
            put(Contract.Pokemons.COLUMN_NAME_TYPE, pokemon.type)
            put(Contract.Pokemons.COLUMN_NAME_HEIGHT, pokemon.height)
            put(Contract.Pokemons.COLUMN_NAME_WEIGHT, pokemon.weight)
            put(Contract.Pokemons.COLUMN_NAME_FAVORITE, pokemon.favorite)
            put(Contract.Pokemons.COLUMN_NAME_ABILITY, pokemon.ability)
            put(Contract.Pokemons.COLUMN_NAME_IMAGE, pokemon.imageSQL)
        }
        return values
    }
}