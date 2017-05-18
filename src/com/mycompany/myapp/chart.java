/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.io.ConnectionRequest;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import java.util.List;

/**
 *
 * @author SALMA
 */
public class chart {
    
// List<Integer> y = new ArrayList<>();
    //public static int y;

 
    public static Form f;
    public double x, y,z ; 

    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * Builds a category series using the provided values.
     *
     * @param values the values
     * @return the category series
     */
    protected CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);
        int k = 0;
        for (double value : values) {
            series.add("User " + ++k, value);
        }

        return series;
    }

    public Form createPieChartForm() {
        // Generate the values
        ConnectionRequest req = new ConnectionRequest();
        ReviewsService re = new ReviewsService();     
        List <Double> le = re.findAll();
        double[] values = new double[le.size()]; // create table same size as liste 
        for(int i = 0; i< le.size();i++) values[i]=le.get(i); //inseri fi table ml liste one  per one 
        // Set up the renderer
        int[] colors = new int[]{ColorUtil.WHITE, ColorUtil.GRAY, ColorUtil.BLUE, ColorUtil.YELLOW, ColorUtil.BLACK};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.BLUE);
        r.setGradientStop(0, ColorUtil.GREEN);
        r.setHighlighted(true);

        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("User", values), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        // Create a form and show it.
        f = new Form("Our best Users", new BorderLayout());
        f.add(BorderLayout.CENTER, c);
        MyApplication a = new MyApplication();
        f.getToolbar().addCommandToRightBar("back", null, e -> a.start());
           
        f.show();
        return f;

    }
}
