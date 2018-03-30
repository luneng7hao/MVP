package com.mvp.view.custom;

import android.content.Context;
import android.text.format.DateFormat;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.mvp.view.R;

import java.text.DecimalFormat;

import static android.R.attr.format;


/**
 * Created by Asion on 2018/3/22.
 */

public class LineChartMarkerView extends MarkerView {
    private TextView markerview_textview;
    private DecimalFormat format = new DecimalFormat();

    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     */
    public LineChartMarkerView(Context context) {
        super(context, R.layout.line_chart_marker_view);
        markerview_textview = (TextView) findViewById(R.id.markerview_textview);
    }

    //显示的内容
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        markerview_textview.setText(format(e.getX())+(int)e.getX()+"时" + "\n" + format.format(e.getY()) + "度");
        super.refreshContent(e, highlight);
    }

    //标记相对于折线图的偏移量
    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }

    //时间格式化（显示今日日期）
    public String format(float x) {
        CharSequence format = DateFormat.format("MM月dd日", System.currentTimeMillis() );
        return format.toString();
    }


}
