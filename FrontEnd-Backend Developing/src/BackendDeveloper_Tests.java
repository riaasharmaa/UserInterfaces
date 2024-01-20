
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BackendDeveloper_Tests {
  // test load
  public static boolean test1() {
    CHSearchBackendInterface backend = new CHSearchBackendBD(
        new HashtableWithDuplicateKeysAE<String, PostInterface>(), new PostReaderDW());
    try {
      backend.loadData("/Users/riasharma/eclipse-workspace/CS 400 P1/src/fake.txt");
      return true;
    } catch (FileNotFoundException e) {
      return false;
    }
  }

  // test FindPostsByTitleWords
  public static boolean test2() throws Exception {
    CHSearchBackendInterface backend = new CHSearchBackendBD(
        new HashtableWithDuplicateKeysAE<String, PostInterface>(), new PostReaderDW());
    backend.loadData("/Users/riasharma/eclipse-workspace/CS 400 P1/src/fake.txt");
    List<String> results = backend.findPostsByTitleWords("Madison");
    return !results.isEmpty();
  }

  // test FindPostsByBodyWords
  public static boolean test3() throws Exception {
    CHSearchBackendInterface backend = new CHSearchBackendBD(
        new HashtableWithDuplicateKeysAE<String, PostInterface>(), new PostReaderDW());
    backend.loadData("/Users/riasharma/eclipse-workspace/CS 400 P1/src/fake.txt");
    List<String> results = backend.findPostsByBodyWords("hungry");
    return !results.isEmpty();
  }

  // test FindPostsByTitleOrBodyWords
  public static boolean test4() throws Exception {
    CHSearchBackendInterface backend = new CHSearchBackendBD(
        new HashtableWithDuplicateKeysAE<String, PostInterface>(), new PostReaderDW());
    backend.loadData("/Users/riasharma/eclipse-workspace/CS 400 P1/src/fake.txt");
    List<String> results = backend.findPostsByTitleOrBodyWords("in");
    return !results.isEmpty();
  }

  // test GetStatisticsString
  public static boolean test5() throws Exception {
    CHSearchBackendInterface backend = new CHSearchBackendBD(
        new HashtableWithDuplicateKeysAE<String, PostInterface>(), new PostReaderDW());
    backend.loadData("/Users/riasharma/eclipse-workspace/CS 400 P1/src/fake.txt");
    String stats = backend.getStatisticsString();
    return !stats.isEmpty();
  }
  //test main menu prompt
  public static boolean fetest1() throws Exception{
    String input = "t\n";
    Scanner scanner = new Scanner(input);
    CHSearchFrontendFD frontend = new CHSearchFrontendFD(scanner, null);
    char result = frontend.mainMenuPrompt();
    return (result == 'T');
}

  // test choose search words
  public static boolean fetest2() throws Exception{
    String input = "apple\norange\npear\norange\n\n";
    Scanner scanner = new Scanner(input);
    CHSearchFrontendFD frontend = new CHSearchFrontendFD(scanner, null);
    List<String> result = frontend.chooseSearchWordsPrompt();
    List<String> expected = Arrays.asList("apple", "pear");
    return result.equals(expected);
}
  // test search title command
  public static boolean itest1() throws Exception{
    List<PostDW> searchResults = new ArrayList<>();
    searchResults.add(new PostDW("Title 1", "Body 1", null));
    searchResults.add(new PostDW("Title 2", "Body 2", null));
    CHSearchBackendInterface backend = new CHSearchBackendBD(
        new HashtableWithDuplicateKeysAE<String, PostInterface>(), new PostReaderDW());
    Scanner scanner = new Scanner("madison\nwisco\nuniversity\n\n");
    CHSearchFrontendFD frontend = new CHSearchFrontendFD(scanner, backend);
    frontend.searchTitleCommand(Arrays.asList("madison", "wisco"));
    return true; 
}
// test display stats
  public static boolean itest2() throws Exception{
    CHSearchBackendInterface backend = new CHSearchBackendBD(
        new HashtableWithDuplicateKeysAE<String, PostInterface>(), new PostReaderDW());
    Scanner scanner = new Scanner("/Users/riasharma/eclipse-workspace/CS 400 P1/src/fake.txt\n");
    CHSearchFrontendFD frontend = new CHSearchFrontendFD(scanner, backend);
          backend.loadData("/Users/riasharma/eclipse-workspace/CS 400 P1/src/fake.txt");
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      System.setOut(new PrintStream(outputStream));
      frontend.displayStatsCommand();
      String output = outputStream.toString();

      boolean hasNumPosts = output.contains("posts");
      boolean hasNumWords = output.contains("unique words");
      boolean hasAvgWords = output.contains("total word-post");
      return hasNumPosts && hasNumWords && hasAvgWords;
  }
  //test load data
  public static boolean itest3() throws Exception{
    CHSearchBackendInterface backend = new CHSearchBackendBD(
        new HashtableWithDuplicateKeysAE<String, PostInterface>(), new PostReaderDW());
Scanner scanner = new Scanner("/Users/riasharma/eclipse-workspace/CS 400 P1/src/fake.txt\n");
    CHSearchFrontendFD frontend = new CHSearchFrontendFD(scanner, backend);
    frontend.loadDataCommand();
    return true; // no exception was thrown
}


  public static void main(String[] args) throws Exception {
    System.out.println("Backend Test 1: " + test1());
    System.out.println("Backend Test 2: " + test2());
    System.out.println("Backend Test 3: " + test3());
    System.out.println("Backend Test 4: " + test4());
    System.out.println("Backend Test 5: " + test5());
    System.out.println("Frontend Test 1: " + fetest1());
    System.out.println("Frontend Test 2: " + fetest2());
    System.out.println("Integration Test 1: " + itest1());
    System.out.println("Integration Test 2: " + itest2());
    System.out.println("Integration Test 3: " + itest3());
  }
}


