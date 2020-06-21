package CreateTable;

import Setting.Columns;

import Setting.Page;
import TSVReader.DataTSV;

import java.awt.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

public class ReportCreate extends JFrame {

    public static String[][] createGUI() {
        JFrame frame = new JFrame("Report");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<Columns> columns = Columns.getValueColumns("settings.xml");

        String[] columnNames = new String[columns.size()];
        for (int i = 0; i < columnNames.length; i++) {
            columnNames[i] = columns.get(i).getTitle();
        }

        DataTSV dataTSV = new DataTSV();
        ArrayList<DataTSV> dataTSVList = dataTSV.readTSV("source-data.tsv");
        String[][] data = new String[dataTSVList.size()][3];

        for (int i = 0; i < dataTSVList.size(); i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    data[i][j] = dataTSVList.get(i).getNumber();
                } else if (j == 1) {
                    data[i][j] = dataTSVList.get(i).getDate();
                } else {
                    data[i][j] = dataTSVList.get(i).getName();
                }
            }
        }
        Page page = new Page();
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.getContentPane().add(scrollPane);
        Page p = page.getValuePage("settings.xml");
        frame.setPreferredSize(new Dimension(p.getWidth() * 10, p.getHeight() * 10));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        return data;
    }

    public static void main(String[] args) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("Report.txt");
            PrintWriter pw = new PrintWriter(fw);
            String [][] print = new ReportCreate().createGUI();
            pw.println(print.toString());
            pw.close();

        } catch (IOException ex) {
            Logger.getLogger(ReportCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

