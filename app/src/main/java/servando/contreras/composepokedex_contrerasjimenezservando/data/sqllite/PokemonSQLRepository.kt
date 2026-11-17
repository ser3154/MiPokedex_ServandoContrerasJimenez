package servando.contreras.composepokedex_contrerasjimenezservando.data.sqllite

import servando.contreras.composepokedex_contrerasjimenezservando.data.interfaces.IPokemonDAO
import servando.contreras.composepokedex_contrerasjimenezservando.data.interfaces.IPokemonRepository
import servando.contreras.composepokedex_contrerasjimenezservando.domain.Pokemon

class PokemonSQLRepository(private val dao: IPokemonDAO) : IPokemonRepository {

    override suspend fun addPokemon(pokemon: Pokemon) {
        dao.insertPokemon(pokemon)
    }

    override suspend fun getPokemons(): List<Pokemon> {
        return dao.getAllPokemons()
    }

    override suspend fun getFavoritePokemons(): List<Pokemon> {
        return dao.getFavoritePokemons()
    }

    override suspend fun getPokemonsByType(type: String): List<Pokemon> {
        return dao.getPokemonsByType(type)
    }

    override suspend fun updatePokemon(pokemon: Pokemon) {
        dao.updatePokemon(pokemon)
    }

    override suspend fun deletePokemon(id: Int) {
        val pokemon = dao.getPokemonById(id) ?: return
        dao.deletePokemon(pokemon)
    }
}