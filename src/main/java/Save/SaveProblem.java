package Save;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SaveProblem {

    /**
     * Metodo main
     * @param problem string del problema en notacion sufija
     * @param result string del resultado del problema
     * @throws IOException
     */
    public static void main(String problem, String result) throws IOException {
        write(problem, result);
    }

    /**
     * Escribe en un archivo csv
     * @param problem string del problema en notacion sufija
     * @param result string del resultado del problema
     * @throws IOException
     */
    public static void write(String problem, String result) throws IOException {
        List<List<String>> records = readFile();
        try (PrintWriter writer = new PrintWriter("Table.csv")) {
            StringBuilder sb = new StringBuilder();
            sb.append("DATE:");
            sb.append(',');
            sb.append("PROBLEM:");
            sb.append(',');
            sb.append("RESULT:");
            for (int j = 1; j < records.size(); j++) {
                sb.append("\n");
                for (int i = 0; i < records.get(j).size(); i++) {
                    sb.append(records.get(j).get(i));
                    sb.append(',');
                }
            }

            String date = new Date().toString();
            sb.append("\n");
            sb.append(date);
            sb.append(',');
            sb.append(problem);
            sb.append(',');
            sb.append(result);

            writer.write(sb.toString());
            System.out.println("done!");
        }
    }

    /**
     * Lee el contenido de un archivo csv y lo almacena en una matriz
     * @return matriz con los datos del archivo csv
     * @throws IOException
     */
    public static List<List<String>> readFile() throws IOException {
        List<List<String>> records = new ArrayList<>();
        String[] values = null;
        try (BufferedReader br = new BufferedReader(new FileReader("Table.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String COMMA_DELIMITER = ",";
                values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }
        } catch (FileNotFoundException ex) {
            generateCsvFile();
        }
        return records;
    }

    /**
     * Crea un archivo csv
     */
    private static void generateCsvFile() {

        FileWriter writer = null;

        try {

            writer = new FileWriter("Table");
            writer.append("DATE:");
            writer.append(',');
            writer.append("PROBLEM:");
            writer.append(',');
            writer.append("RESULT:");

            System.out.println("CSV file is created...");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}