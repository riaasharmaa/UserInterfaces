import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader implements CSVReaderInterface{
    char separator = ',';

    public List<StudentRecords> readCSV(String fileName) throws IOException {
        List<StudentRecords> students = new ArrayList<>();
        boolean firstRow = true;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (firstRow) {
                    firstRow = false;
                    continue; // skip the first row
                }
                String[] values = line.split(String.valueOf(separator));
                String name = values[0];
                int studentID = Integer.parseInt(values[1]);
                String major = values[2];
                students.add(new StudentRecords(name, studentID, major));
            }
        }
        return students;
    }


    public boolean isCSVValid(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(String.valueOf(separator));
                if (parts.length != 3) {
                    reader.close();
                    return false;
                }
            }
            reader.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }


    public void setSeparator(char separator) {
        this.separator = separator;
    }
}
