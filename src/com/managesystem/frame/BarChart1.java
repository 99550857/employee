package com.managesystem.frame;

import com.managesystem.dao.EnergyBiaoDAO;
import com.managesystem.dao.impl.EnergyDAOImpl;
import com.managesystem.model.EnergyBiao;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 柱形图表
 * Created by lenovo on 2017/12/26.
 */
public class BarChart1 {
    ChartPanel chartPanel;
    String[] name;
    List<EnergyBiao> list = new ArrayList<>();
    private EnergyBiaoDAO energyBiaoDAO;
    int i;
    int lenth;

    public BarChart1() {
        try {
            energyBiaoDAO=new EnergyDAOImpl();
            list = energyBiaoDAO.getAll();
            lenth = list.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        name = new String[lenth];
        for (i = 0; i < lenth; i++) {
            name[i] = list.get(i).getDepartment();
        }

        CategoryDataset dataset = getDataSet();
        JFreeChart chart = ChartFactory.createBarChart3D("按部门的消耗统计", // 图表标题
                "部门", // 目录轴的显示标签
                "费用", // 数值轴的显示标签
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                true, // 是否显示图例(对于简单的柱状图必须是false)
                false, // 是否生成工具
                false // 是否生成URL链接
        );

        // 从这里开始
        CategoryPlot plot = chart.getCategoryPlot();// 获取图表区域对象
        CategoryAxis domainAxis = plot.getDomainAxis(); // 水平底部列表
        domainAxis.setLabelFont(new Font("黑体", Font.BOLD, 14)); // 水平底部标题
        domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // 垂直标题
        ValueAxis rangeAxis = plot.getRangeAxis();// 获取柱状
        rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体

        // 到这里结束，虽然代码有点多，但只为一个目的，解决汉字乱码问题

        chartPanel = new ChartPanel(chart, true); // 这里也可以用chartFrame,可以直接生成一个独立的Frame

    }

    public ChartPanel getChartPanel() {
        return chartPanel;
    }

    public CategoryDataset getDataSet() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (i = 0; i < lenth; i++) {
            dataset.addValue(list.get(i).getDepartmentEnergy(), list.get(i).getDepartment(),
                    list.get(i).getDepartment());
        }
        return dataset;
    }

}
