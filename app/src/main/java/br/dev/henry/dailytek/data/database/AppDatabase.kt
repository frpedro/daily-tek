package br.dev.henry.dailytek.data.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.dev.henry.dailytek.data.database.entity.RespostaEntity
import br.dev.henry.dailytek.data.database.entity.ResultadoFinalEntity
import br.dev.henry.dailytek.data.database.dao.RespostaDao
import br.dev.henry.dailytek.data.database.dao.ResultadoFinalDao

// cria e inicia o banco
@Database(entities = [RespostaEntity::class, ResultadoFinalEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun respostaDao(): RespostaDao
    abstract fun resultadoFinalDao(): ResultadoFinalDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "dailytek_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}