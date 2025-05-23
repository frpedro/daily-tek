package br.dev.henry.dailytek.data.database.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.dev.henry.dailytek.data.database.entity.RespostaEntity

// busca as respostas no banco
@Dao
interface RespostaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirResposta(resposta: RespostaEntity)

    @Query("SELECT * FROM respostas WHERE usuarioId = :usuarioId")
    suspend fun buscarRespostasPorUsuario(usuarioId: String): List<RespostaEntity>

    @Query("DELETE FROM respostas")
    suspend fun limparRespostas()
}
