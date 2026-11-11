package servando.contreras.composepokedex_contrerasjimenezservando.data.sqllite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATA_NAME,null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        TODO("Not yet implemented")
    }

    companion object{
        private const val DATA_NAME = "pokedex.db"
        private const val DATABASE_VERSION = 1
    }
}