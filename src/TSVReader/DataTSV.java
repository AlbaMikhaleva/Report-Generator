package TSVReader;

import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.nio.charset.StandardCharsets.*;


public class DataTSV {
    private String number;
    private String date;
    private String name;

    public DataTSV(String number, String date, String name) {
        this.number = number;
        this.date = date;
        this.name = name;
    }

    public DataTSV() {
    }

    public String getNumber() {
        return number;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    ArrayList<DataTSV> dataTSV = new ArrayList<>();

    public ArrayList<DataTSV> readTSV (String fileName) {
        // открываем файл
        String nameFile = fileName;
        StringTokenizer st;
        try {
            FileInputStream fileInput = new FileInputStream(nameFile);
            InputStreamReader fileReader = new InputStreamReader(fileInput, UTF_16);
            BufferedReader TSVFile = new BufferedReader(fileReader);

            // считывание первой строчки данных
            String dataLine = TSVFile.readLine();
            while (dataLine != null) {
                st = new StringTokenizer(dataLine, "\t");
                List<String> dataList = new ArrayList<String>();
                while (st.hasMoreElements()) {
                    dataList.add(st.nextElement().toString());
                }
                dataTSV.add(new DataTSV(dataList.get(0), dataList.get(1), dataList.get(2)));

                //считываем следующую строчку данных
                dataLine = TSVFile.readLine();
            }

            TSVFile.close();

        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return dataTSV;
    }
}

