package br.dev.henry.dailytek.ui.dashboard

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.dev.henry.dailytek.R
import br.dev.henry.dailytek.databinding.FragmentDashboardBinding
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    // Armazena botões selecionados
    private val selectedButtons = mutableSetOf<Button>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        configurarGrafico()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navega para outro gráfico ao clicar no gráfico atual
        binding.lineChart.setOnClickListener {
            findNavController().navigate(R.id.navigation_advanced_chart)
        }

        // Navega para tela de questionários
        binding.imageButton2.setOnClickListener {
            findNavController().navigate(R.id.navigation_select_forms)
        }

        // Referências para todos os botões
        val happy = view.findViewById<Button>(R.id.button)
        val emotional = view.findViewById<Button>(R.id.button8)
        val blueheart = view.findViewById<Button>(R.id.button7)
        val sleep = view.findViewById<Button>(R.id.button10)
        val positive = view.findViewById<Button>(R.id.button9)
        val buttonSad = view.findViewById<Button>(R.id.button2)
        val hangloose = view.findViewById<Button>(R.id.button6)
        val negative = view.findViewById<Button>(R.id.button12)

        // Lista com todos os botões
        val emojiButtons = listOf(happy, emotional, blueheart, sleep, positive, buttonSad, hangloose, negative)

        // Configura o clique para ativar/desativar seleção em múltiplos
        emojiButtons.forEach { button ->
            button.setOnClickListener {
                toggleEmojiSelection(button)
            }
        }
    }

    private fun toggleEmojiSelection(button: Button) {
        if (selectedButtons.contains(button)) {
            // Deseleciona
            selectedButtons.remove(button)
            button.setTextColor(Color.BLACK)
            button.elevation = 8f
            button.setBackgroundColor(Color.WHITE)
        } else {
            // Seleciona
            selectedButtons.add(button)
            button.setBackgroundColor(Color.parseColor("#CCE5FF")) // Azul clarinho
            button.setTextColor(Color.BLACK)
            button.elevation = 2f
        }
    }

    private fun configurarGrafico() {
        val entries = listOf(
            Entry(1f, 3f), Entry(2f, 2f), Entry(3f, 1f), Entry(4f, 4f),
            Entry(5f, 1f), Entry(6f, 5f), Entry(7f, 2f), Entry(8f, 1f),
            Entry(9f, 4f), Entry(10f, 1f), Entry(11f, 5f)
        )

        val dataSet = LineDataSet(entries, "Humor").apply {
            color = Color.parseColor("#4F7396")
            setCircleColor(Color.parseColor("#4F7396"))
            lineWidth = 3f
            circleRadius = 5f
            valueTextSize = 12f
            setDrawValues(false)
            mode = LineDataSet.Mode.CUBIC_BEZIER
            setDrawCircles(false)
        }

        binding.lineChart.data = LineData(dataSet)

        binding.lineChart.apply {
            setDrawGridBackground(false)
            setDrawBorders(false)
            description.isEnabled = false

            xAxis.apply {
                setDrawGridLines(false)
                setDrawLabels(false)
                setDrawAxisLine(false)
            }

            axisLeft.apply {
                setDrawGridLines(false)
                setDrawLabels(false)
                setDrawAxisLine(false)
            }

            axisRight.isEnabled = false

            legend.isEnabled = true
            dataSet.form = Legend.LegendForm.CIRCLE
            setTouchEnabled(true)
            setPinchZoom(true)
            animateX(800)
            invalidate()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
