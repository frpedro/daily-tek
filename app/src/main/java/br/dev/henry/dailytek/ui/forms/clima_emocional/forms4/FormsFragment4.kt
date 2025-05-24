package br.dev.henry.dailytek.ui.forms.clima_emocional.forms4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.dev.henry.dailytek.R
import br.dev.henry.dailytek.data.database.HiddenID
import br.dev.henry.dailytek.data.database.entity.RespostaEntity
import br.dev.henry.dailytek.databinding.FragmentForms4Binding
import mockEnviarResposta

class FormsFragment4 : Fragment() {

    private val viewModel: FormsViewModel4 by viewModels()

    private var _binding: FragmentForms4Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForms4Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext4.setOnClickListener {
            val respostaSelecionada = when (binding.radioGroup4.checkedRadioButtonId) {
                R.id.radioButton11 -> "Motivado"
                R.id.radioButton12 -> "Cansado"
                R.id.radioButton13 -> "Preocupado"
                R.id.radioButton14 -> "Estressado"
                R.id.radioButton15 -> "Animado"
                R.id.radioButton16 -> "Satisfeito"
                else -> "Não respondido"
            }

            val resposta = RespostaEntity(
                pergunta = "Como você se sente hoje?",
                resposta = respostaSelecionada,
                categoria = "Clima Emocional",
                usuarioId = HiddenID.getOrCreateUserId(requireContext())
            )

            // Envia resposta simulada
            mockEnviarResposta(requireContext(), resposta)

            // Navega para o próximo fragment (ajuste o destino se necessário)
            findNavController().navigate(R.id.navigation_dashboard)


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
