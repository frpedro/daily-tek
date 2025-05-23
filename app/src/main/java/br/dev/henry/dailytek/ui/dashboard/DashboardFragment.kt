package br.dev.henry.dailytek.ui.dashboard
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        configurarGrafico()

        return root
    }

    private fun configurarGrafico() {
        val entries = listOf(
            Entry(1f, 3f),
            Entry(2f, 2f),
            Entry(3f, 1f),
            Entry(4f, 4f),
            Entry(5f, 1f),
            Entry(6f, 5f),
            Entry(7f, 2f),
            Entry(8f, 1f),
            Entry(9f, 4f),
            Entry(10f, 1f),
            Entry(11f, 5f)
        )

        val dataSet = LineDataSet(entries, "Humor")
        dataSet.color = Color.parseColor("#4F7396")          // Cor da linha
        dataSet.setCircleColor(Color.parseColor("#4F7396")) // Cor dos círculos
        dataSet.lineWidth = 3f
        dataSet.circleRadius = 5f
        dataSet.valueTextSize = 12f
        dataSet.setDrawValues(false)
        dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        dataSet.setDrawCircles(false)

        val lineData = LineData(dataSet)
        binding.lineChart.data = lineData

        // Limpar fundo e grade
        binding.lineChart.setDrawGridBackground(false)      // Remove fundo quadriculado
        binding.lineChart.setDrawBorders(false)             // Remove bordas

        // Configura eixos para não mostrar linhas e marcas
        val xAxis = binding.lineChart.xAxis
        xAxis.setDrawGridLines(false)      // Remove linhas verticais (grade X)
        xAxis.setDrawLabels(false)         // Remove valores (labels) no eixo X
        xAxis.setDrawAxisLine(false)       // Remove linha do eixo X

        val leftAxis = binding.lineChart.axisLeft
        leftAxis.setDrawGridLines(false)   // Remove linhas horizontais (grade Y esquerda)
        leftAxis.setDrawLabels(false)      // Remove valores no eixo Y esquerdo
        leftAxis.setDrawAxisLine(false)    // Remove linha do eixo Y esquerdo

        val rightAxis = binding.lineChart.axisRight
        rightAxis.isEnabled = false         // Desativa eixo Y direito completamente

        binding.lineChart.description.isEnabled = false
        binding.lineChart.legend.isEnabled = true
        dataSet.form = Legend.LegendForm.CIRCLE
        binding.lineChart.setTouchEnabled(true)
        binding.lineChart.setPinchZoom(true)
        binding.lineChart.animateX(800)
        binding.lineChart.invalidate()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lineChart.setOnClickListener {
            findNavController().navigate(R.id.navigation_advanced_chart)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
