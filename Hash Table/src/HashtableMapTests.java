import java.util.NoSuchElementException;

// --== CS400 Project One File Header ==--
// Name: Ria Sharma
// CSL Username: ria
// Email: rsharma78@wisc.edu
// Lecture #: Florian Heimerl - MWF 1:20-2:10PM
// Notes to Grader: N/A
public class HashtableMapTests {

  public static boolean test1() {
    HashtableMap<String, Integer> map = new HashtableMap<>(4);
    try {
      map.put("Key1", 1);
      map.put("Key2", 2);
      map.put("Key3", 3);
      map.put("Key4", 4);
      return map.getSize() == 4;
    } catch (Exception e) {
      return false;
    }
  }

  public static boolean test2() {
    HashtableMap<String, Integer> map = new HashtableMap<>(4);
    try {
      map.put("Key1", 1);
      map.put("Key2", 2);
      map.put("Key3", 3);
      map.put("Key4", 4);
      return map.get("Key1") == 1 && map.get("Key2") == 2 && map.get("Key3") == 3
          && map.get("Key4") == 4;
    } catch (Exception e) {
      return false;
    }
  }

  public static boolean test3() {
    HashtableMap<String, Integer> map = new HashtableMap<>(4);
    try {
      map.put("Key1", 1);
      map.put("Key2", 2);
      map.put("Key3", 3);
      map.put("Key4", 4);
      Integer removed = map.remove("Key3");
      return removed == 3 && map.getSize() == 3;
    } catch (Exception e) {
      return false;
    }
  }

  public static boolean test4() {
    HashtableMap<String, Integer> map = new HashtableMap<>(4);
    try {
      map.put("Key1", 1);
      map.put("Key2", 2);
      map.put("Key3", 3);
      map.put("Key4", 4);
      map.put("Key1", 5);
      return false;
    } catch (IllegalArgumentException e) {
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public static boolean test5() {
    HashtableMap<String, Integer> map = new HashtableMap<>(4);
    try {
      map.put("Key1", 1);
      map.put("Key2", 2);
      map.put("Key3", 3);
      map.put("Key4", 4);
      map.get("Key5");
      return false;
    } catch (NoSuchElementException e) {
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public static void main(String[] args) {
    System.out.println("Test 1: " + (test1() ? "true" : "false"));
    System.out.println("Test 2: " + (test2() ? "true" : "false"));
    System.out.println("Test 3: " + (test3() ? "true" : "false"));
  }
}
