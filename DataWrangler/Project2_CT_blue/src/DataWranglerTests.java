
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataWranglerTests {

    private CSVReader reader;

    @BeforeEach
    public void setUp() {
        reader = new CSVReader();
    }


    @Test
    public void testReadCSV() throws IOException {
        List<StudentRecords> students = reader.readCSV("/Users/riasharma/Library/Mobile Documents/com~apple~CloudDocs/Desktop/Comp Sci 400/Project2_CT_blue/src/students.csv");
        assertNotNull(students);
        assertEquals("John Smith", students.get(0).getName());
        assertEquals(12345, students.get(0).getStudentID());
        assertEquals("Computer Science", students.get(0).getMajor());
    }

    @Test
    public void testIsCSVValid() throws IOException {
        assertTrue(reader.isCSVValid("/Users/riasharma/Library/Mobile Documents/com~apple~CloudDocs/Desktop/Comp Sci 400/Project2_CT_blue/src/students.csv"));
        assertFalse(reader.isCSVValid("invalid.csv"));
    }

    @Test
    public void testReadInvalidCSV() {
        assertThrows(IOException.class, () -> {
            reader.readCSV("invalid.csv");
        });
    }

    @Test
    public void testSetSeparator() {
        reader.setSeparator(';');
        assertEquals(';', reader.separator);
    }

    @Test
    public void testCompareTo() {
        // Create a student object with ID = 12345
        StudentRecords student = new StudentRecords("John Smith", 12345, "Computer Science");

        // Test when the input ID is greater than the student ID
        assertEquals(-1, student.compareTo(54321));

        // Test when the input ID is less than the student ID
        assertEquals(1, student.compareTo(11111));

        // Test when the input ID is equal to the student ID
        assertEquals(0, student.compareTo(12345));
    }

}
