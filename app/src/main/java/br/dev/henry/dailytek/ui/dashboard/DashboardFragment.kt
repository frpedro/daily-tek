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
        // dados de exemplo para o grafico
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

        val dataSet = LineDataSet(entries, "Humor").apply {
            color = Color.parseColor("#4F7396")                // cor da linha
            setCircleColor(Color.parseColor("#4F7396"))       // cor dos pontos
            lineWidth = 3f
            circleRadius = 5f
            valueTextSize = 12f
            setDrawValues(false)                               // oculta os valores sobre os pontos
            mode = LineDataSet.Mode.CUBIC_BEZIER               // curva
            setDrawCircles(false)                              // oculta o circulo
        }

        binding.lineChart.data = LineData(dataSet)

        // configurações visuais do gráfico
        binding.lineChart.apply {
            setDrawGridBackground(false)                       // remove fundo quadriculado
            setDrawBorders(false)                              // remove bordas
            description.isEnabled = false                      // remove descrição

            // eixo X
            xAxis.apply {
                setDrawGridLines(false)
                setDrawLabels(false)
                setDrawAxisLine(false)
            }

            // eixo Y esquerdo
            axisLeft.apply {
                setDrawGridLines(false)
                setDrawLabels(false)
                setDrawAxisLine(false)
            }

            // eixo Y direito desativado
            axisRight.isEnabled = false

            legend.isEnabled = true                            // mostra legenda
            dataSet.form = Legend.LegendForm.CIRCLE            // forma da legenda
            setTouchEnabled(true)                              // permite interação
            setPinchZoom(true)                                 // zoom com dois dedos
            animateX(800)                          // animação de entrada
            invalidate() // força redesenho para atualizar dados
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // navega para outro gráfico ao clicar no gráfico atual
        binding.lineChart.setOnClickListener {
            findNavController().navigate(R.id.navigation_advanced_chart)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // evita memory leak
    }
}