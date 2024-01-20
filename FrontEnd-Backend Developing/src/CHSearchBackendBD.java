import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class CHSearchBackendBD implements CHSearchBackendInterface {
  private HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable;
  private PostReaderInterface postReader;
  private int postCount;

  public CHSearchBackendBD(HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable,
      PostReaderInterface postReader) {
    this.hashtable = hashtable;
    this.postReader = postReader;
    this.postCount = 0;
  }

  public void loadData(String filename) throws FileNotFoundException {
    List<PostInterface> posts = postReader.readPostsFromFile(filename);
    if (posts == null) {
        System.out.println("No posts found in file");
        return;
    }
    for (PostInterface post : posts) {
        addPostToHashtable(post);
    }
}

  /**
   * Helper method extracts individual words
   */
  private void addPostToHashtable(PostInterface post) {
    List<String> titleWords = getWords(post.getTitle());
    List<String> bodyWords = getWords(post.getBody());
    for (String word : titleWords)
      hashtable.putOne("T:" + word, post);
    for (String word : bodyWords)
      hashtable.putOne("B:" + word, post);
    postCount++;
  }

  /**
   * Helper method to extract words from a string of text, and return a single list. This method
   * also strips out punctuation and converts any upper case letter to lowercase to help find
   * variations in punctuation and casing later.
   */
  private List<String> getWords(String text) {
    // strip out all punctuation
    // make all letters to lower case
    text = text.replaceAll("[^a-zA-Z ]", "").toLowerCase();

    // break remaining letters into words around spaces
    String[] words = text.split(" ");
    return Arrays.asList(words);
  }

  /**
   * Finds all posts with title words
   */
  public List<String> findPostsByTitleWords(String words) {
    List<String> wordList = getWords(words);
    List<String> postStrings = new ArrayList<>();
    findPostsHelper(wordList, postStrings, "T:");
    return postStrings;
  }

  /**
   * Finds all posts with body words
   */
  public List<String> findPostsByBodyWords(String words) {
    List<String> wordList = getWords(words);
    List<String> postStrings = new ArrayList<>();
    findPostsHelper(wordList, postStrings, "B:");
    return postStrings;
  }

  /**
   * Finds all posts with title/body words
   */

  public List<String> findPostsByTitleOrBodyWords(String words) {
    List<String> wordList = getWords(words);
    List<String> postStrings = new ArrayList<>();
    findPostsHelper(wordList, postStrings, "T:");
    findPostsHelper(wordList, postStrings, "B:");
    return postStrings;
  }

  /**
   * Helper method that searches for posts by title/body word word, adds to a list, and removes any
   * duplicates
   */
  private void findPostsHelper(List<String> words, List<String> postStrings, String typeString) {
    for (String word : words) {
      try {
        // search for each word in the list by title/body
        List<PostInterface> posts = hashtable.get(typeString + word);
        // then add each of those posts as title - url strings into list
        for (PostInterface post : posts) {
          String postString = post.getTitle() + " - " + post.getUrl();
          postStrings.add(postString);
        }
      } catch (NoSuchElementException e) {
      } // when one key is not found, try another
    }
    // sort string to make it easier to find and replace duplicates
    postStrings.sort(null);
    // remove duplicates strings from list
    for (int i = 1; i < postStrings.size(); i++)
      if (postStrings.get(i).equals(postStrings.get(i - 1)))
        postStrings.remove(i--);
  }

  /**
   * Displays statistics regarding the number of posts, words, and total pairs that are a part of
   * the current dataset being searched
   */
  public String getStatisticsString() {
    return "Dataset contains:\n" + "    " + postCount + " posts\n" + "    " + hashtable.getSize()
        + " unique words\n" + "    " + hashtable.getNumberOfValues() + " total word-post pairs\n"
        + "    note that words in titles vs bodies are counted separately";
  }

}

