package servando.contreras.composepokedex_contrerasjimenezservando.data.sqllite

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import servando.contreras.composepokedex_contrerasjimenezservando.data.interfaces.IPokemonDAO
import servando.contreras.composepokedex_contrerasjimenezservando.domain.Pokemon

class PokemonDAO(private val helper: DatabaseHelper) : IPokemonDAO {

    private val mapper = PokemonMapper()

    override suspend fun insertPokemon(pokemon: Pokemon) {
        withContext(Dispatchers.IO) {
            helper.writableDatabase.use { db ->
                db.insert(Contract.Pokemons.TABLE_NAME, null, mapper.getContentValues(pokemon))
            }
        }
    }

    override suspend fun getAllPokemons(): List<Pokemon> {
        return withContext(Dispatchers.IO) {
            val db = helper.readableDatabase
            val query = "SELECT * FROM ${Contract.Pokemons.TABLE_NAME}"
            db.rawQuery(query, null).use { c ->
                buildList {
                    while (c.moveToNext()) {
                        add(mapper.getPokemon(c))
                    }
                }
            }
        }
    }

    override suspend fun getPokemonById(id: Int): Pokemon? {
        return withContext(Dispatchers.IO) {
            val db = helper.readableDatabase
            val query = "SELECT * FROM ${Contract.Pokemons.TABLE_NAME} " +
                    "WHERE ${Contract.Pokemons.COLUMN_NAME_ID} = ?"
            db.rawQuery(query, arrayOf(id.toString())).use { c ->
                if (c.moveToFirst()) mapper.getPokemon(c) else null
            }
        }
    }

    override suspend fun getFavoritePokemons(): List<Pokemon> {
        return emptyList()
    }

    override suspend fun getPokemonsByType(type: String): List<Pokemon> {
        return withContext(Dispatchers.IO) {
            val db = helper.readableDatabase
            val query = "SELECT * FROM ${Contract.Pokemons.TABLE_NAME} " +
                    "WHERE ${Contract.Pokemons.COLUMN_NAME_TYPE} = ?"
            db.rawQuery(query, arrayOf(type)).use { c ->
                buildList {
                    while (c.moveToNext()) {
                        add(mapper.getPokemon(c))
                    }
                }
            }
        }
    }

    override suspend fun updatePokemon(pokemon: Pokemon) {
        withContext(Dispatchers.IO) {
            helper.writableDatabase.use { db ->
                db.update(
                    Contract.Pokemons.TABLE_NAME,
                    mapper.getContentValues(pokemon),
                    "${Contract.Pokemons.COLUMN_NAME_NUMBER} = ?",
                    arrayOf(pokemon.number.toString())
                )
            }
        }
    }

    override suspend fun deletePokemon(pokemon: Pokemon) {
        withContext(Dispatchers.IO) {
            helper.writableDatabase.use { db ->
                db.delete(
                    Contract.Pokemons.TABLE_NAME,
                    "${Contract.Pokemons.COLUMN_NAME_NUMBER} = ?",
                    arrayOf(pokemon.number.toString())
                )
            }
        }
    }
}