package br.dev.henry.dailytek.data.database
import android.content.Context

// cria um id unico e aletorio pensando na privacidade do usuário ao responder os formularios, sem nenhuma forma de identifica-lo.
class HiddenID {
    companion object {
        fun getOrCreateUserId(context: Context): String {
            val prefs = context.getSharedPreferences("dailytek_prefs", Context.MODE_PRIVATE)
            val existingId = prefs.getString("user_id", null)
            return if (existingId != null) {
                existingId
            } else {
                val newId = java.util.UUID.randomUUID().toString()
                prefs.edit().putString("user_id", newId).apply()
                newId
            }
        }
    }
}