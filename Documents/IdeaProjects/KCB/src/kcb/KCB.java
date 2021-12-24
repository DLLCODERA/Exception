package kcb;

import javax.swing.*;

public class KCB {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JTable table = new JTable(new KCB_data()); // JTable 数据和表现分离，table是表现,只管理视图。
        JScrollPane pane = new JScrollPane(table);
        frame.add(pane);
        frame.pack(); //计算自己的大小
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
