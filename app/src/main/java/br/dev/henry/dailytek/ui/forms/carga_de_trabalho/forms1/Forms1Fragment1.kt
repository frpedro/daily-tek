package br.dev.henry.dailytek.ui.forms.carga_de_trabalho.forms1
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
import br.dev.henry.dailytek.databinding.FragmentFormsBinding
import mockEnviarResposta

class Forms1Fragment1 : Fragment() {

    companion object {
        fun newInstance() = Forms1Fragment1()
    }

    private val viewModel: FormsViewModel1 by viewModels()

    private var _binding: FragmentFormsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormsBinding.inflate(inflater, container, false)
        return binding.root
    }

    // api
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener {
            val respostaSelecionada = when {
                binding.optionEveryDay.isChecked -> "Muito Leve"
                binding.optionFewTimesWeek.isChecked -> "Leve"
                binding.optionOnceWeek.isChecked -> "Média"
                binding.optionFewTimesMonth.isChecked -> "Alta"
                binding.optionOnceMonth.isChecked -> "Muito Alta"
                else -> "Não respondido"
            }

            val resposta = RespostaEntity(
                pergunta = "Como você avalia sua carga de trabalho?",
                resposta = respostaSelecionada,
                categoria = "Carga de Trabalho",
                usuarioId = HiddenID.getOrCreateUserId(requireContext())
            )

            // aciona o mock
            mockEnviarResposta(requireContext(), resposta)

            // navega para próxima tela
            findNavController().navigate(R.id.navigation_forms2)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
