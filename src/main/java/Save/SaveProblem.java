package Save;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author alero
 */
public class SaveProblem {

    public static void main(String problem, String result) throws IOException {
        write(problem, result);
    }

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

    public static List<List<String>> readFile() throws FileNotFoundException, IOException {
        List<List<String>> records = new ArrayList<>();
        String[] values = null;
        try (BufferedReader br = new BufferedReader(new FileReader("Table.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String COMMA_DELIMITER = ",";
                values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }
        }
        return records;
    }
}