/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  com.mycompany.myapp;

import Entities.rideanimal;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author elloumiaymen
 */
public class stat {
     /**
     * Creates a renderer for the specified colors.
     */
   
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
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    ArrayList<rideanimal> listePro = new ArrayList<>();

    protected CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);

//         for ( Projets obj : listePro) {
//            System.out.println("mehdi");
//  
//            series.add("hg",85);
//
//            }
        int k = 0;
        for (double value : values) {
            series.add("Project" + ++k, value);
        }

        return series;

    }
//

    public Form createPieChartForm() {
        // Generate the values
        double[] values = new double[100];
        CategorySeries series = new CategorySeries("");
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PidevMobile/scripts/aymen/selectRideAnimal.php?idUser=2");

        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                int i = 0;
                for (rideanimal obj : (ArrayList<rideanimal>) getListProjets(new String(con.getResponseData()))) {
                    i++;
                     series.add(obj.getCitySource(), 0.0);
                }

            }
        });
        NetworkManager.getInstance().addToQueue(con);

        // Set up the renderer
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.rgb(12, 45, 82), ColorUtil.rgb(44, 90, 10), ColorUtil.YELLOW, ColorUtil.rgb(80, 30, 7)};
        double[] value = new double[]{20, 4, 50, 9};
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
        PieChart chart = new PieChart(series, renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        // Create a form and show it.
        Form f = new Form(new BorderLayout());
        f.add(BorderLayout.CENTER, c);
        return f;
    }

    public ArrayList<rideanimal> getListProjets(String json) {
        ArrayList<rideanimal> listeProjets = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> projets = j.parseJSON(new CharArrayReader(json.toCharArray()));

            System.out.println();
            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) projets.get("rideanimal");

            for (Map<String, Object> obj : list) {
                rideanimal p = new rideanimal();
                p.setCityDestination(obj.get("citySource").toString());
                p.setSpeciesAnimal(obj.get("speciesAnimal").toString());

                listeProjets.add(p);

            }

        } catch (IOException ex) {
        }
        return listeProjets;
    }

}

