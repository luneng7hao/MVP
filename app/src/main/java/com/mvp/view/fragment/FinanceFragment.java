package com.mvp.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.mvp.view.R;
import com.mvp.view.custom.BarChartManager;
import com.mvp.view.custom.CombinedChartManager;
import com.mvp.view.custom.LineChartMarkerView;
import com.mvp.view.custom.LineChartViewTwo;
import com.mvp.view.fragment.base.AsionBaseFragment;
import com.mvp.view.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Asion on 2018/2/1.
 */

public class FinanceFragment extends AsionBaseFragment {

    @BindView(R.id.line_chart)
    LineChart line_chart;
    @BindView(R.id.line_chart02)
    LineChart line_chart02;
    @BindView(R.id.bar_hart)
    BarChart bar_hart;
    @BindView(R.id.combined_chart)
    CombinedChart combined_chart;
    //自定义某日温度
    private int[] temperature = {3, 3, 4, 4, 4, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 14, 15, 15, 14, 11, 9, 8, 7, 7};
    //自定义十二个月份
    private String[] monthList = {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
    //自定义收益信息
    private int[] gains = {6000, 500, 2346, 7605, 8907, 1003, 789, 2000, 3000, 1600, 1800, 3765};
    private int[] gains02 = {3000, 2300, 2346, 8705, 8307, 11003, 1289, 3000, 5000, 1200, 3600, 6765};
    //自定义消费信息
    private int[] consume = {1000, 2000, 1500, 3000, 800, 3200, 1200, 3200, 1200, 1100, 2000, 800};
    //自定义省份
    private String[] provinces = {"山东", "北京", "天津", "河北", "江苏", "浙江", "杭州", "辽宁", "福建", "广东", "山西", "河南"};

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_finance;
    }

    @Override
    protected void initView() {
        setLine01();
        setLine02();
        setBar();
        setCombinedChart();
    }

    /**
     * 柱状图加+线形图
     */
    private void setCombinedChart() {
//x轴数据
        List<String> xData = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            xData.add(String.valueOf(i));
        }
        //y轴数据集合
        List<List<Float>> yBarDatas = new ArrayList<>();
        //4种直方图
        for (int i = 0; i < 2; i++) {
            //y轴数
            List<Float> yData = new ArrayList<>();
            for (int j = 0; j <= 10; j++) {
                yData.add((float) (Math.random() * 100));
            }
            yBarDatas.add(yData);
        }
        //y轴数据集合
        List<List<Float>> yLineDatas = new ArrayList<>();
        //4种直方图
        for (int i = 0; i < 2; i++) {
            //y轴数
            List<Float> yData = new ArrayList<>();
            for (int j = 0; j <= 10; j++) {
                yData.add((float) (Math.random() * 100));
            }
            yLineDatas.add(yData);
        }
        //名字集合
        List<String> barNames = new ArrayList<>();
        barNames.add("直方图一");
        barNames.add("直方图二");
//        barNames.add("直方图三");
//        barNames.add("直方图四");
        //颜色集合
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.RED);
//        colors.add(Color.YELLOW);
//        colors.add(Color.CYAN);
        //竖状图管理类

        List<String> lineNames = new ArrayList<>();
        lineNames.add("折线图一");
        lineNames.add("折线图二");
//        lineNames.add("折线图三");
//        lineNames.add("折线图四");


        //单个图 管理类
//        CombinedChartManager combineChartManager1 = new CombinedChartManager(combined_chart);
//        combineChartManager1.showCombinedChart(xData, yBarDatas.get(0), yLineDatas.get(0),
//                "直方图", "线性图", colors.get(0), colors.get(1));
        //多个图
        CombinedChartManager combineChartManager2 = new CombinedChartManager(combined_chart);
        combineChartManager2.showCombinedChart(xData, yBarDatas, yLineDatas, barNames, lineNames,
                colors, colors);
        //Y轴动画
        combined_chart.animateY(3000);
    }

    /**
     * 消费柱状图
     */
    private void setBar() {
        BarChartManager barChartManager1 = new BarChartManager(bar_hart);
        //设置x轴的数据
        ArrayList<Float> xValues = new ArrayList<>();
        for (int i = 0; i < provinces.length; i++) {
            xValues.add((float) i);
        }
        //设置柱形图显示的数据()
        List<List<Float>> yValues = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            List<Float> yValue = new ArrayList<>();
            for (int j = 0; j < provinces.length; j++) {
                yValue.add((float) (Math.random() * 5000));
            }
            yValues.add(yValue);
        }
        //设置X轴坐标的显示，为省份坐标
        bar_hart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return provinces[((int) value)];
            }
        });
        //颜色集合
        List<Integer> colours = new ArrayList<>();
        colours.add(Color.GREEN);
        colours.add(Color.BLUE);

        //线的名字集合
        List<String> names = new ArrayList<>();
        names.add("人均收益");
        names.add("人均消费");

        //创建多条折线的图表
//        barChartManager1.showBarChart(xValues,yValues.get(0),"人均收益",Color.GREEN);
        barChartManager1.showBarChart(xValues, yValues, names, colours);
        barChartManager1.setDescription("省份");
    }

    /**
     * 温度图
     */
    private void setLine01() {
        //显示线形图边界（默认不显示）
//        line_chart.setDrawBorders(true);

        //MarkerView可自定义，用于点击图标值时显示想要的内容
        LineChartMarkerView mv = new LineChartMarkerView(mActivity);
        line_chart.setMarkerView(mv);
        //设置所要显示的数据
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            entries.add(new Entry(i, temperature[i]));
        }
        LineDataSet lineDataSet = new LineDataSet(entries, "温度");
        LineData data = new LineData(lineDataSet);
        //去除Y坐标的小数点（float型转为int型数据）
        data.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return "" + (int) value;
            }
        });
        line_chart.setData(data);

        //设置显示描述文本
        line_chart.getDescription().setEnabled(true);
        Description description = new Description();
        description.setText("时间");
        description.setTextColor(Color.RED);
        line_chart.setDescription(description);

        //X轴
        XAxis xAxis = line_chart.getXAxis();
        //X轴位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//值：BOTTOM,BOTH_SIDED,BOTTOM_INSIDE,TOP,TOP_INSIDE
        //X轴坐标间距
        xAxis.setGranularity(1f);
        //X轴刻度数量，并平均分配
        xAxis.setLabelCount(12, true);
        //X轴最小值与最大值
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(23f);

        //Y轴
        //Y轴为左右两个
        YAxis leftYAxis = line_chart.getAxisLeft();
        YAxis rightYAxis = line_chart.getAxisRight();
//        leftYAxis.setAxisMinimum(0f);
//        leftYAxis.setAxisMaximum(40f);
//
//        rightYAxis.setAxisMinimum(0f);
//        rightYAxis.setAxisMaximum(40f);
        //设置右侧Y轴不可见
        rightYAxis.setEnabled(false);

        //4.X轴和Y轴类似，都具有相同的属性方法
//        rightYAxis.setGranularity(1f);
//        rightYAxis.setLabelCount(11,false);
//        rightYAxis.setTextColor(Color.BLUE); //文字颜色
//        rightYAxis.setGridColor(Color.RED); //网格线颜色
//        rightYAxis.setAxisLineColor(Color.GREEN); //Y轴颜色

        //5.限制线LimitLine
//        LimitLine limitLine = new LimitLine(37,"高温预警"); //得到限制线
//        limitLine.setLineWidth(4f); //宽度
//        limitLine.setTextSize(10f);
//        limitLine.setTextColor(Color.RED);  //颜色
//        limitLine.setLineColor(Color.BLUE);
//        rightYAxis.addLimitLine(limitLine); //Y轴添加限制线

        //设置线条动画
        line_chart.animateX(3000);
    }

    /**
     * 收益图
     */
    private void setLine02() {
        //显示线形图边界（默认不显示）
//        line_chart.setDrawBorders(true);

        //MarkerView可自定义，用于点击图标值时显示想要的内容
        LineChartViewTwo mv = new LineChartViewTwo(mActivity);
        line_chart02.setMarkerView(mv);
        //设置所要显示的数据
        //显示两条数据
        //在这里可以设置线条以及各顶点的样式
        List<Entry> entries01 = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            entries01.add(new Entry(i, gains[i]));
        }
        LineDataSet lineDataSet01 = new LineDataSet(entries01, "我的收益");

        List<Entry> entries02 = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            entries02.add(new Entry(i, gains02[i]));
        }
        LineDataSet lineDataSet02 = new LineDataSet(entries02, "她的收益");
        lineDataSet02.setColor(Color.RED);

        LineData data = new LineData(lineDataSet01, lineDataSet02);
        //去除Y坐标的小数点（float型转为int型数据）
        line_chart02.setData(data);

        //设置显示描述文本
        line_chart02.getDescription().setEnabled(true);
        Description description = new Description();
        description.setText("月份");
        description.setTextColor(Color.RED);
        line_chart02.setDescription(description);

        //X轴
        XAxis xAxis = line_chart02.getXAxis();
        //X轴位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//值：BOTTOM,BOTH_SIDED,BOTTOM_INSIDE,TOP,TOP_INSIDE
        //X轴坐标间距
        xAxis.setGranularity(1f);
        //X轴刻度数量，并平均分配
        xAxis.setLabelCount(12, true);
        //X轴最小值与最大值
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(11f);

        //设置X轴坐标的显示，为月份坐标
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return monthList[((int) value)];
            }
        });

        //Y轴
        //Y轴为左右两个
        YAxis leftYAxis = line_chart02.getAxisLeft();
        YAxis rightYAxis = line_chart02.getAxisRight();
//        leftYAxis.setAxisMinimum(0f);
//        leftYAxis.setAxisMaximum(40f);
//
//        rightYAxis.setAxisMinimum(0f);
//        rightYAxis.setAxisMaximum(40f);
        //设置右侧Y轴不可见
        rightYAxis.setEnabled(false);

        //4.X轴和Y轴类似，都具有相同的属性方法
//        rightYAxis.setGranularity(1f);
//        rightYAxis.setLabelCount(11,false);
//        rightYAxis.setTextColor(Color.BLUE); //文字颜色
//        rightYAxis.setGridColor(Color.RED); //网格线颜色
//        rightYAxis.setAxisLineColor(Color.GREEN); //Y轴颜色

        //5.限制线LimitLine
//        LimitLine limitLine = new LimitLine(37,"高温预警"); //得到限制线
//        limitLine.setLineWidth(4f); //宽度
//        limitLine.setTextSize(10f);
//        limitLine.setTextColor(Color.RED);  //颜色
//        limitLine.setLineColor(Color.BLUE);
//        rightYAxis.addLimitLine(limitLine); //Y轴添加限制线
    }

    @Override
    protected void initData() {
    }

}