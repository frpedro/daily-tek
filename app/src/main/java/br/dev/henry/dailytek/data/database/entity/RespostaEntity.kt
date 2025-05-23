package br.dev.henry.dailytek.data.database.entity
import androidx.room.Entity
import androidx.room.PrimaryKey

// cria entidade para respostas
@Entity(tableName = "respostas")
data class RespostaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val data: Long = System.currentTimeMillis(),
    val pergunta: String,
    val resposta: String,
    val categoria: String,
    val usuarioId: String = "default_user"
)