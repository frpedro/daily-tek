package br.dev.henry.dailytek.ui.forms.carga_de_trabalho.forms2

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
import br.dev.henry.dailytek.databinding.FragmentForms2Binding
import mockEnviarResposta

class FormsFragment2 : Fragment() {

    private val viewModel: FormsViewModel2 by viewModels()

    private var _binding: FragmentForms2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForms2Binding.inflate(inflater, container, false)
        return binding.root
    }

    // api
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // mock
        binding.btnNext2.setOnClickListener {
            val respostaSelecionada = when (binding.radioGroup3.checkedRadioButtonId) {
                R.id.radioButton5 -> "Não"
                R.id.radioButton4 -> "Raramente"
                R.id.radioButton -> "Às vezes"
                R.id.radioButton3 -> "Frequentemente"
                R.id.radioButton2 -> "Sempre"
                else -> "Não respondido"
            }

            val resposta = RespostaEntity(
                pergunta = "Sua carga de trabalho afeta sua qualidade de vida?",
                resposta = respostaSelecionada,
                categoria = "Carga de Trabalho",
                usuarioId = HiddenID.getOrCreateUserId(requireContext())
            )

            // chama o mock
            mockEnviarResposta(requireContext(), resposta)

            // navega para proxima tela
            findNavController().navigate(R.id.navigation_forms3)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
