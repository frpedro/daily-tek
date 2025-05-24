import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import br.dev.henry.dailytek.data.database.entity.RespostaEntity

// cria funcao para salvar resposta mockada
fun mockEnviarResposta(context: Context, resposta: RespostaEntity) {
    Handler(Looper.getMainLooper()).postDelayed({
        Log.d("MOCK_API", "Resposta enviada: ${resposta.pergunta} - ${resposta.resposta}")
        Toast.makeText(context, "Resposta enviada com sucesso!", Toast.LENGTH_SHORT).show()
    }, 1000)
}