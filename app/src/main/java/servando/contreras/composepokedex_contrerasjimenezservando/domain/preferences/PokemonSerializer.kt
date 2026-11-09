package servando.contreras.composepokedex_contrerasjimenezservando.domain.preferences


import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import servando.contreras.composepokedex_contrerasjimenezservando.domain.Pokemon
import java.io.InputStream
import java.io.OutputStream

object PokemonSerializer : Serializer<Pokemon> {
    override val defaultValue: Pokemon = Pokemon(
        name = "",
        number = -1,
        type = "",
        description = "",
        height = 0f,
        weight = 0f,
        favorite = false,
        ability = "",
        image = -1
    )

    override suspend fun readFrom(input: InputStream): Pokemon {
        return try {
            Json.decodeFromString(
                Pokemon.serializer(), input.readBytes().decodeToString()
            )
        }catch (exeption: SerializationException){
            throw CorruptionException("No se pudo leer el JSON", exeption)
        }
    }

    override suspend fun writeTo(
        t: Pokemon,
        output: OutputStream
    ) {
        output.write(Json.encodeToString(Pokemon.serializer(), t).encodeToByteArray())
    }

}