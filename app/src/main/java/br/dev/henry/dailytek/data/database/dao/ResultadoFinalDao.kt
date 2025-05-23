package br.dev.henry.dailytek.data.database.dao;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import br.dev.henry.dailytek.data.database.entity.ResultadoFinalEntity;

// busca os resultados no banco
@Dao
interface ResultadoFinalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirResultado(resultado:ResultadoFinalEntity)

    @Query("SELECT * FROM resultados WHERE usuarioId = :usuarioId")
    suspend fun buscarResultadoPorUsuario(usuarioId: String): ResultadoFinalEntity?
}
