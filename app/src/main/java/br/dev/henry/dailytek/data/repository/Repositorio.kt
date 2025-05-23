package br.dev.henry.dailytek.data.repository
import br.dev.henry.dailytek.data.database.dao.RespostaDao
import br.dev.henry.dailytek.data.database.dao.ResultadoFinalDao
import br.dev.henry.dailytek.data.database.entity.RespostaEntity
import br.dev.henry.dailytek.data.database.entity.ResultadoFinalEntity

// regras de negocio
class Repositorio(private val respostaDao: RespostaDao, private val resultadoFinalDao: ResultadoFinalDao) {

    suspend fun salvarResposta(resposta: RespostaEntity) = respostaDao.inserirResposta(resposta)

    suspend fun calcularResultado(usuarioId: String): ResultadoFinalEntity {
        val respostas = respostaDao.buscarRespostasPorUsuario(usuarioId)

        val ansiedadeRespostas = respostas.filter { it.categoria == "Ansiedade/Burnout" }
        val cargaRespostas = respostas.filter { it.categoria == "Carga de Trabalho" }

        val riscoAnsiedade = when {
            ansiedadeRespostas.count { it.resposta.contains("Ansioso", true) || it.resposta.contains("Estressado", true) } >= 2 -> "Agudo"
            else -> "Leve"
        }

        val riscoCarga = when {
            cargaRespostas.count { it.resposta.contains("Muito Alta", true) || it.resposta.contains("Sempre", true) } >= 2 -> "Alto"
            cargaRespostas.count { it.resposta.contains("Alta", true) } >= 2 -> "Moderado"
            else -> "Leve"
        }

        val resultado = ResultadoFinalEntity(
            riscoAnsiedade = riscoAnsiedade,
            riscoCarga = riscoCarga,
            usuarioId = usuarioId
        )

        resultadoFinalDao.inserirResultado(resultado)
        return resultado
    }
}