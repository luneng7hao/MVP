package com.mvp.view.custom;

import android.graphics.Color;
import android.util.Log;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义BarChart管理类（柱形图）
 * Created by Asion on 2018/3/23.
 */

public class BarChartManager {

    private BarChart mBarChart;
    private YAxis leftAxis;
    private YAxis rightAxis;
    private XAxis xAxis;

    public BarChartManager(BarChart mBarChart) {
        this.mBarChart = mBarChart;
        leftAxis = mBarChart.getAxisLeft();
        rightAxis = mBarChart.getAxisRight();
        xAxis = mBarChart.getXAxis();
    }

    /**
     * 初始化BarChart
     */
    private void initBarChart() {
        //背景颜色
        mBarChart.setBackgroundColor(Color.WHITE);
        //网格
        mBarChart.setDrawGridBackground(false);
        //背景阴影
        mBarChart.setDrawBarShadow(false);
        mBarChart.setHighlightFullBarEnabled(false);
        //显示边界
        mBarChart.setDrawBorders(true);
        //设置动画效果
        mBarChart.animateY(1000, Easing.EasingOption.Linear);
        mBarChart.animateX(1000, Easing.EasingOption.Linear);
        //折线图例 标签 设置
        Legend legend = mBarChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(11f);
        //显示位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        //XY轴的设置
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        //保证Y轴从0开始，不然会上移一点
        leftAxis.setAxisMinimum(0f);
        rightAxis.setAxisMinimum(0f);
        //设置不绘制网格线
        xAxis.setDrawGridLines(false);
        leftAxis.setDrawGridLines(false);
        rightAxis.setDrawGridLines(false);

    }

    /**
     * 展示柱状图(一条)
     *
     * @param xAxisValues
     * @param yAxisValues
     * @param label
     * @param color
     */
    public void showBarChart(List<Float> xAxisValues, List<Float> yAxisValues, String label, int color) {
        initBarChart();
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < xAxisValues.size(); i++) {
            entries.add(new BarEntry(xAxisValues.get(i), yAxisValues.get(i)));
        }
        // 每一个BarDataSet代表一类柱状图
        BarDataSet barDataSet = new BarDataSet(entries, label);
        //柱形图颜色
        barDataSet.setColor(color);
        //柱形图上数据的格式大小
        barDataSet.setValueTextSize(9f);
        barDataSet.setFormLineWidth(1f);
        barDataSet.setFormSize(15.f);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet);
        BarData data = new BarData(dataSets);
        //设置X轴的刻度数
        //第一个参数是X轴坐标的个数，第二个参数是 是否不均匀分布，true是不均匀分布
        xAxis.setLabelCount(xAxisValues.size() - 1, false);

        mBarChart.setData(data);
    }

    /**
     * 展示柱状图(多条)
     *
     * @param xAxisValues
     * @param yAxisValues
     * @param labels
     * @param colours
     */
    public void showBarChart(List<Float> xAxisValues, List<List<Float>> yAxisValues, List<String> labels, List<Integer> colours) {
        initBarChart();
        BarData data = new BarData();
        for (int i = 0; i < yAxisValues.size(); i++) {
            ArrayList<BarEntry> entries = new ArrayList<>();
            for (int j = 0; j < xAxisValues.size(); j++) {

                entries.add(new BarEntry(xAxisValues.get(j), yAxisValues.get(i).get(j)));
            }
            BarDataSet barDataSet = new BarDataSet(entries, labels.get(i));

            barDataSet.setColor(colours.get(i));
            barDataSet.setValueTextColor(colours.get(i));
            barDataSet.setValueTextSize(10f);
            barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
            data.addDataSet(barDataSet);
        }
        //获知有几组柱形图
        int amount = yAxisValues.size();
       //柱状图组之间的间距
        float groupSpace = 0.12f;
      //组内柱形图的间距
        float barSpace = 0.02f;
       //计算单个柱形图的宽度
        //计算时要把柱形图两侧的空格算上
        float barWidth = (float) ((1f - 0.12-0.02) / amount/(xAxisValues.size() +1)*xAxisValues.size());
        xAxis.setLabelCount(xAxisValues.size() - 1, false);

        data.setBarWidth(barWidth); ;
        mBarChart.setData(data);
        //设置柱形图的启示X坐标值、柱形图组间的值、单个柱形图间的值
        mBarChart.groupBars(0, groupSpace, barSpace);
        mBarChart.invalidate();

    }

    /**
     * 设置Y轴值
     *
     * @param max
     * @param min
     * @param labelCount
     */
    public void setYAxis(float max, float min, int labelCount) {
        if (max < min) {
            return;
        }
        leftAxis.setAxisMaximum(max);
        leftAxis.setAxisMinimum(min);
        leftAxis.setLabelCount(labelCount, false);

        rightAxis.setAxisMaximum(max);
        rightAxis.setAxisMinimum(min);
        rightAxis.setLabelCount(labelCount, false);
        mBarChart.invalidate();
    }

    /**
     * 设置X轴的值
     *
     * @param max
     * @param min
     */
    public void setXAxis(float max, float min, int labelCount) {
        xAxis.setAxisMaximum(max);
        xAxis.setAxisMinimum(min);
        xAxis.setLabelCount(labelCount, false);
        mBarChart.invalidate();
    }

    /**
     * 设置高限制线
     *
     * @param high
     * @param name
     */
    public void setHightLimitLine(float high, String name, int color) {
        if (name == null) {
            name = "高限制线";
        }
        LimitLine hightLimit = new LimitLine(high, name);
        hightLimit.setLineWidth(4f);
        hightLimit.setTextSize(10f);
        hightLimit.setLineColor(color);
        hightLimit.setTextColor(color);
        leftAxis.addLimitLine(hightLimit);
        mBarChart.invalidate();
    }

    /**
     * 设置低限制线
     *
     * @param low
     * @param name
     */
    public void setLowLimitLine(int low, String name) {
        if (name == null) {
            name = "低限制线";
        }
        LimitLine hightLimit = new LimitLine(low, name);
        hightLimit.setLineWidth(4f);
        hightLimit.setTextSize(10f);
        leftAxis.addLimitLine(hightLimit);
        mBarChart.invalidate();
    }

    /**
     * 设置描述信息
     *
     * @param str
     */
    public void setDescription(String str) {
        Description description = new Description();
        description.setText(str);
        description.setTextColor(Color.RED);
        mBarChart.setDescription(description);
        mBarChart.invalidate();
    }
}
