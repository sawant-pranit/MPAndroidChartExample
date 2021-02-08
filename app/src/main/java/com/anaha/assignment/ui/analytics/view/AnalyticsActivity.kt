package com.anaha.assignment.ui.analytics.view

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.anaha.assignment.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.Utils
import kotlinx.android.synthetic.main.activity_analytics.*
import java.util.*

class AnalyticsActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context) = Intent(context, AnalyticsActivity::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.WHITE
        setContentView(R.layout.activity_analytics)



        setupBloodPressureData()
        setupHeartRate()
        setupTemperature()
    }

    private fun setupTemperature() {
        setData(45, 108f, chartTemperature, false)
    }

    private fun setupHeartRate() {
        setData(45, 180f, chartHeartRate)
    }



    private fun setData(count: Int, range: Float, lineChart: LineChart, isHeartRate: Boolean = true) {
        val values = ArrayList<Entry>()
        for (i in 0 until count) {
            val value = (Math.random() * range).toFloat() - 30
            values.add(Entry(i.toFloat(), value, resources.getDrawable(R.drawable.star)))
        }
        val set1: LineDataSet
        if (lineChart.getData() != null &&
            lineChart.getData().getDataSetCount() > 0
        ) {
            set1 = lineChart.getData().getDataSetByIndex(0) as LineDataSet
            set1.values = values
            set1.notifyDataSetChanged()
            lineChart.getData().notifyDataChanged()
            lineChart.notifyDataSetChanged()
        } else {
            // create a dataset and give it a type
            set1 = LineDataSet(values, "DataSet 1")
            set1.setDrawIcons(false)

            // draw dashed line
            set1.enableDashedLine(10f, 5f, 0f)

            // black lines and points
            set1.color = Color.BLACK
            set1.setCircleColor(Color.BLACK)

            // line thickness and point size
            set1.lineWidth = 1f
            set1.circleRadius = 3f

            // draw points as solid circles
            set1.setDrawCircleHole(false)

            // customize legend entry
            set1.formLineWidth = 1f
            set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
            set1.formSize = 15f

            // text size of values
            set1.valueTextSize = 9f

            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f)

            // set the filled area
            set1.setDrawFilled(true)
            set1.fillFormatter =
                IFillFormatter { dataSet, dataProvider -> lineChart.getAxisLeft().getAxisMinimum() }

            // set color of filled area
            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
                val drawable = if(isHeartRate) ContextCompat.getDrawable(this, R.drawable.fade_red) else ContextCompat.getDrawable(this, R.drawable.fade_temperature)
                set1.fillDrawable = drawable
            } else {
                set1.fillColor = Color.BLACK
            }
            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(set1) // add the data sets

            // create a data object with the data sets
            val data = LineData(dataSets)

            // set data
            lineChart.setData(data)
        }
    }

    private fun setupBloodPressureData() {
        chartBloodPressure.description.isEnabled = false
        chartBloodPressure.setMaxVisibleValueCount(60)
        chartBloodPressure.setPinchZoom(false)
        chartBloodPressure.setDrawGridBackground(false)

        // x axis
        val xAxis =chartBloodPressure.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)

        // y axis
        val yAxisLeft = chartBloodPressure.axisLeft
        yAxisLeft.setLabelCount(7, false)
        yAxisLeft.setDrawGridLines(false)
        yAxisLeft.setDrawAxisLine(false)

        val yAxisRight = chartBloodPressure.axisRight
        yAxisRight.isEnabled = false

        // set data
        val progress = 50
        chartBloodPressure.resetTracking()

        val values = ArrayList<CandleEntry>()

        for (i in 0 until progress) {
            val multi: Float = (progress + 1).toFloat()
            val `val` = (Math.random() * 40).toFloat() + multi
            val high = (Math.random() * 9).toFloat() + 8f
            val low = (Math.random() * 9).toFloat() + 8f
            val open = (Math.random() * 6).toFloat() + 1f
            val close = (Math.random() * 6).toFloat() + 1f
            val even = i % 2 == 0
            values.add(
                CandleEntry(
                    i.toFloat(), `val` + high,
                    `val` - low,
                    if (even) `val` + open else `val` - open,
                    if (even) `val` - close else `val` + close,
                    resources.getDrawable(R.drawable.star)
                )
            )
        }

        val set1 = CandleDataSet(values, "Blood Pressure")

        set1.setDrawIcons(false)
        set1.axisDependency = AxisDependency.LEFT
//        set1.setColor(Color.rgb(80, 80, 80));
        //        set1.setColor(Color.rgb(80, 80, 80));
        set1.shadowColor = Color.DKGRAY
        set1.shadowWidth = 0f
        set1.decreasingColor = Color.RED
        set1.decreasingPaintStyle = Paint.Style.FILL
        set1.increasingColor = Color.rgb(122, 242, 84)
        set1.increasingPaintStyle = Paint.Style.STROKE
        set1.neutralColor = Color.BLUE
        //set1.setHighlightLineWidth(1f);

        //set1.setHighlightLineWidth(1f);
        val data = CandleData(set1)

        chartBloodPressure.setData(data)
        chartBloodPressure.invalidate()
    }
}