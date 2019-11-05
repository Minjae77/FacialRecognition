package com.google.android.gms.samples.vision.face.facetracker;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class Analysis extends AppCompatActivity {
    private LineChart lineChart;
    private PieChart pieChart;
    public static List<Float> result = FaceTrackerActivity.result;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.analysis);
        System.out.println(result);

        //Button btn = (Button) findViewById(R.id.button3);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //FaceTrackerActivity.this.complete();
//            }
//        });
        // Check for the camera permission before accessing the camera.  If the
        // permission is not granted yet, request permission.}

        pieChart = (PieChart)findViewById(R.id.piechart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.setEntryLabelColor(Color.WHITE);

        ArrayList<PieEntry> yValues = new ArrayList<PieEntry>();

        yValues.add(new PieEntry(34f,"Japan"));
        yValues.add(new PieEntry(23f,"USA"));
        yValues.add(new PieEntry(14f,"UK"));
        yValues.add(new PieEntry(35f,"India"));
        yValues.add(new PieEntry(40f,"Russia"));
        yValues.add(new PieEntry(40f,"Korea"));

        Description descriptionPie = new Description();
        descriptionPie.setText("20회 동안 표정 분포"); //라벨
        descriptionPie.setTextSize(20);
        descriptionPie.setTextColor(Color.WHITE);
        pieChart.setDescription(descriptionPie);
        pieChart.setCenterTextColor(Color.WHITE);

        pieChart.animateY(1000, Easing.EaseInOutCubic); //애니메이션

        PieDataSet dataSet = new PieDataSet(yValues,"Countries");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        dataSet.setValueTextSize(30f);


        pieChart.setNoDataTextColor(Color.WHITE);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(20f);
        data.setValueTextColor(Color.YELLOW);

        Legend legendpie = pieChart.getLegend();
        legendpie.setTextColor(Color.WHITE);
        legendpie.setTextSize(13f);

        pieChart.setData(data);

        lineChart = (LineChart)findViewById(R.id.line_chart);

        List<Entry> entries = new ArrayList<>();

//        entries.add(new Entry(1, 1));
//        entries.add(new Entry(2, 2));
//        entries.add(new Entry(3, 0));
//        entries.add(new Entry(4, 4));
//        entries.add(new Entry(5, 3));
        for(int i = 0; i < result.size(); i++){
            entries.add(new Entry(i + 1, result.get(i)));
        }

        LineDataSet lineDataSet = new LineDataSet(entries, "이번 표정 분포");
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(6);
        lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setCircleHoleColor(Color.BLUE);
        lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setDrawValues(false);

        lineChart.setGridBackgroundColor(Color.WHITE);
        lineChart.setNoDataTextColor(Color.WHITE);

        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineData.setValueTextColor(Color.WHITE);


        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.WHITE);
        xAxis.enableGridDashedLine(8, 24, 0);
        xAxis.setTextSize(13f);

        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.WHITE);
        yLAxis.setTextSize(13f);

        YAxis yRAxis = lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        Description description = new Description();
        description.setText("");

        Legend legend = lineChart.getLegend();
        legend.setTextColor(Color.WHITE);
        legend.setTextSize(20);

        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setDescription(description);
        lineChart.animateY(2000, Easing.EaseInCubic);
        lineChart.invalidate();

    }



}
