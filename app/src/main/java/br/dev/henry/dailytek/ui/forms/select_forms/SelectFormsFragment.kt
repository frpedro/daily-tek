package br.dev.henry.dailytek.ui.forms.select_forms
import br.dev.henry.dailytek.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.dev.henry.dailytek.databinding.FragmentSelectFormsBinding

class SelectFormsFragment : Fragment() {

    private var _binding: FragmentSelectFormsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectFormsBinding.inflate(inflater, container, false)

        // configura as arrows para enviar para novas telas
        binding.imageView.setOnClickListener {
            findNavController().navigate(R.id.navigation_dashboard) // para voltar ao dashboard
        }
        binding.button13.setOnClickListener {
            findNavController().navigate(R.id.navigation_forms) // para ir ao forms: carga de trabalho
        }
        binding.button12.setOnClickListener {
            findNavController().navigate(R.id.navigation_forms4)// para ir ao forms: clima emocional
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
