package br.dev.henry.dailytek.ui.forms.carga_de_trabalho.forms3

import br.dev.henry.dailytek.data.database.HiddenID
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.dev.henry.dailytek.R
import br.dev.henry.dailytek.data.database.entity.RespostaEntity
import br.dev.henry.dailytek.databinding.FragmentForms3Binding
import mockEnviarResposta

class FormsFragment3 : Fragment() {

    private val viewModel: FormsViewModel3 by viewModels()

    private var _binding: FragmentForms3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForms3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext3.setOnClickListener {
            val respostaSelecionada = when (binding.radioGroup2.checkedRadioButtonId) {
                R.id.radioButton6 -> "Não"
                R.id.radioButton7 -> "Raramente"
                R.id.radioButton8 -> "Às vezes"
                R.id.radioButton9 -> "Frequentemente"
                R.id.radioButton10 -> "Sempre"
                else -> "Não respondido"
            }

            val resposta = RespostaEntity(
                pergunta = "Você trabalha além do seu horário regular?",
                resposta = respostaSelecionada,
                categoria = "Carga de Trabalho",
                usuarioId = HiddenID.getOrCreateUserId(requireContext())
            )

            // chama o mock
            mockEnviarResposta(requireContext(), resposta)

            // volta para dashboard
            findNavController().navigate(R.id.navigation_dashboard)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
