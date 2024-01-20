import java.io.IOException;
import java.util.List;

public interface CSVReaderInterface {
    public List<StudentRecords> readCSV(String filename) throws IOException;
    public boolean isCSVValid(String filepath);
    public void setSeparator(char separator);
}

