package br.dev.henry.dailytek.ui.advancedchart
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.dev.henry.dailytek.R
import br.dev.henry.dailytek.databinding.FragmentAdvancedChartBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

// tela responsável por exibir gráficos avançados
class AdvancedChartFragment : Fragment() {

    private var _binding: FragmentAdvancedChartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val chartViewModel = ViewModelProvider(this).get(AdvancedChartViewModel::class.java)

        // infla o layout usando ViewBinding
        _binding = FragmentAdvancedChartBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // configura os gráficos
        setupLineChart(requireContext(), binding.lineChart1)
        setupBarChart(requireContext(), binding.barChart2)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // evita memory leaks
        _binding = null
    }

    // configura o gráfico de linha com dados e estilo visual
    private fun setupLineChart(context: Context, lineChart: LineChart) {
        val entries = arrayListOf(
            Entry(0f, 5f),
            Entry(1f, 8f),
            Entry(2f, 7f),
            Entry(3f, 5f),
            Entry(4f, 1f),
            Entry(5f, 2f)
        )

        val dataSet = LineDataSet(entries, "").apply {
            color = ContextCompat.getColor(context, R.color.chart_blue)
            lineWidth = 3f
            circleRadius = 5f
            valueTextSize = 12f
            setDrawValues(false)
            mode = LineDataSet.Mode.CUBIC_BEZIER
            setDrawCircles(false)
            form = Legend.LegendForm.NONE
        }

        // remove eixo Y esquerdo e direito
        lineChart.axisLeft.isEnabled = false
        lineChart.axisRight.isEnabled = false

        // aplica dados ao gráfico
        lineChart.data = LineData(dataSet)

        // rótulos do eixo X
        val xLabels = listOf("01 mar", "02 mar", "03 mar", "04 mar", "05 mar", "06 mar")
        lineChart.xAxis.apply {
            valueFormatter = IndexAxisValueFormatter(xLabels)
            position = XAxis.XAxisPosition.BOTTOM
            labelRotationAngle = 0f
            setDrawGridLines(false)
            setDrawAxisLine(false)
            textColor = Color.BLACK
            textSize = 12f
            granularity = 1f
        }

        // Estilização adicional
        lineChart.description.isEnabled = false
        lineChart.legend.isEnabled = true
        lineChart.setTouchEnabled(true)
        lineChart.setPinchZoom(true)
        lineChart.setDrawGridBackground(false)
        lineChart.setDrawBorders(false)
        lineChart.animateX(800)
        lineChart.invalidate()
    }

    // configura o gráfico de barras com dados e estilo visual
    private fun setupBarChart(context: Context, barChart: BarChart) {
        val entries = arrayListOf(
            BarEntry(0f, 30f),
            BarEntry(1f, 80f),
            BarEntry(2f, 60f),
            BarEntry(3f, 50f),
            BarEntry(4f, 70f),
            BarEntry(5f, 40f)
        )

        val dataSet = BarDataSet(entries, "").apply {
            color = ContextCompat.getColor(context, R.color.chart_blue)
            setDrawValues(false)
        }

        // aplica dados ao gráfico
        barChart.data = BarData(dataSet)

        // rótulos do eixo X
        val labels = listOf("Dez", "Jan", "Fev", "Mar", "Abr", "Mai")
        barChart.xAxis.apply {
            valueFormatter = IndexAxisValueFormatter(labels)
            position = XAxis.XAxisPosition.BOTTOM
            setDrawGridLines(false)
            setDrawAxisLine(false)
            textColor = Color.BLACK
            textSize = 12f
            labelRotationAngle = 0f
            granularity = 1f
        }

        // remove eixos Y
        barChart.axisLeft.isEnabled = false
        barChart.axisRight.isEnabled = false

        // estilização adicional
        barChart.setDrawGridBackground(false)
        barChart.setDrawBorders(false)
        barChart.description.isEnabled = false
        barChart.legend.isEnabled = false
        barChart.animateY(800)
        barChart.invalidate()

        // botão de voltar
        binding.imageButton4.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
