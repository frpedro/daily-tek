package br.dev.henry.dailytek.ui.advancedchart

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.dev.henry.dailytek.R
import br.dev.henry.dailytek.databinding.FragmentAdvancedChartBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class AdvancedChartFragment : Fragment() {

    private var _binding: FragmentAdvancedChartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val chartViewModel = ViewModelProvider(this).get(AdvancedChartViewModel::class.java)

        _binding = FragmentAdvancedChartBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Chama as funções para configurar os gráficos
        setupLineChart(requireContext(), binding.lineChart1)
        setupBarChart(requireContext(), binding.barChart2)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupLineChart(context: Context, lineChart: LineChart) {
        val entries = arrayListOf(
            Entry(0f, 20f),
            Entry(1f, 24f),
            Entry(2f, 2f),
            Entry(3f, 10f),
            Entry(4f, 28f),
            Entry(5f, 26f)
        )
        val dataSet = LineDataSet(entries, "Histórico").apply {
            color = ContextCompat.getColor(context, R.color.purple_500)
            valueTextColor = ContextCompat.getColor(context, R.color.black)
            lineWidth = 2f
            circleRadius = 5f
            setDrawValues(true)
        }

        lineChart.data = LineData(dataSet)

        val labels = listOf("Seg", "Ter", "Qua", "Qui", "Sex", "Sáb")
        lineChart.xAxis.apply {
            valueFormatter = IndexAxisValueFormatter(labels)
            position = XAxis.XAxisPosition.BOTTOM
            setDrawGridLines(false)
        }
        lineChart.axisRight.isEnabled = false
        lineChart.description.isEnabled = false
        lineChart.invalidate()
    }

    private fun setupBarChart(context: Context, barChart: BarChart) {
        val entries = arrayListOf(
            BarEntry(0f, 30f),
            BarEntry(1f, 80f),
            BarEntry(2f, 60f),
            BarEntry(3f, 50f),
            BarEntry(4f, 70f),
            BarEntry(5f, 60f)
        )
        val dataSet = BarDataSet(entries, "Comparativo").apply {
            color = ContextCompat.getColor(context, R.color.teal_700)
            valueTextColor = ContextCompat.getColor(context, R.color.black)
        }

        barChart.data = BarData(dataSet)

        val labels = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun")
        barChart.xAxis.apply {
            valueFormatter = IndexAxisValueFormatter(labels)
            position = XAxis.XAxisPosition.BOTTOM
            setDrawGridLines(false)
        }
        barChart.axisRight.isEnabled = false
        barChart.description.isEnabled = false
        barChart.invalidate()
    }
}
