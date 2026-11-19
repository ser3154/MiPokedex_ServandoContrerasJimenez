package servando.contreras.composepokedex_contrerasjimenezservando.data.sqllite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATA_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createPokemons = "CREATE TABLE ${Contract.Pokemons.TABLE_NAME}( " +
                "${Contract.Pokemons.COLUMN_NAME_ID} INTEGER PRIMARY KEY," +
                "${Contract.Pokemons.COLUMN_NAME_NUMBER} INTEGER," +
                "${Contract.Pokemons.COLUMN_NAME_NAME} TEXT," +
                "${Contract.Pokemons.COLUMN_NAME_DESCRIPTION} TEXT," +
                "${Contract.Pokemons.COLUMN_NAME_TYPE} TEXT)".trimIndent()

        db?.execSQL(createPokemons)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //val dropTablePokemons = "DROP TABLE IF EXISTS ${Contract.Pokemons.TABLE_NAME}"
        if (oldVersion < 2){
            val addColumnAbility = "ALTER TABLE ${Contract.Pokemons.TABLE_NAME} " +
                    "ADD COLUMN ${Contract.Pokemons.COLUMN_NAME_ABILITY} TEXT "

            val addColumnFav = "ALTER TABLE ${Contract.Pokemons.TABLE_NAME} " +
                    "ADD COLUMN ${Contract.Pokemons.COLUMN_NAME_FAVORITE} BOOLEAN"

            val addColumnWeight = "ALTER TABLE ${Contract.Pokemons.TABLE_NAME} " +
                    "ADD COLUMN ${Contract.Pokemons.COLUMN_NAME_WEIGHT} REAL"

            val addColumnHeight = "ALTER TABLE ${Contract.Pokemons.TABLE_NAME} " +
                    "ADD COLUMN ${Contract.Pokemons.COLUMN_NAME_HEIGHT} REAL"

            val addColumnImage = "ALTER TABLE ${Contract.Pokemons.TABLE_NAME} " +
                    "ADD COLUMN ${Contract.Pokemons.COLUMN_NAME_IMAGE} BLOB"

            db?.beginTransaction()
            db?.execSQL(addColumnAbility)
            db?.execSQL(addColumnFav)
            db?.execSQL(addColumnWeight)
            db?.execSQL(addColumnHeight)
            db?.execSQL(addColumnImage)
            db?.setTransactionSuccessful()
            db?.endTransaction()
        }
    }

    companion object {
        private const val DATA_NAME = "pokedex.db"
        private const val DATABASE_VERSION = 1
    }
}