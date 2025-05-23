package br.dev.henry.dailytek.data.database.entity
import androidx.room.Entity
import androidx.room.PrimaryKey

// cria entidade para resultados
@Entity(tableName = "resultados")
data class ResultadoFinalEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val data: Long = System.currentTimeMillis(),
    val riscoAnsiedade: String,
    val riscoCarga: String,
    val usuarioId: String = "default_user"
)
