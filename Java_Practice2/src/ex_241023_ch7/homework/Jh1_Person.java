package ex_241023_ch7.homework;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Jh1_Person {
  private String name;
  private final Map<String, String> contacts;
  private final LocalDateTime added;

  public Jh1_Person(String name, String contactType, String contact) {
    this.name = name;
    this.contacts = new HashMap<>();
    this.contacts.put(contactType, contact);
    this.added = LocalDateTime.now();
  }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  @Override
  public String toString() {
    final int contactsSize = contacts.size();
    String usePlural = contactsSize > 1 ? "s" : "";
    return String.format("%16s\t<%d contact%s>", name, contactsSize, usePlural);
  }

  public String verbose() {
    StringBuilder builder = new StringBuilder();
    builder.append(String.format("<< %s >>\n", name));
    builder.append(String.format("Added at %s\n", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(added)));
    int i = 1;
    for (Map.Entry<String, String> entry : contacts.entrySet()) {
      builder.append(String.format("[ %d ] %16s <%s>\n", i, entry.getValue(), entry.getKey()));
      i += 1;
    }
    return builder.toString();
  }

  public Set<Jh1_ContactSearchResult> findContact(String find) {
//    Map<String, String> found = null;
//    Set<Map.Entry<String, String>> found = null;
    Set<Jh1_ContactSearchResult> found = new HashSet<>();
    for (Map.Entry<String, String> entry : contacts.entrySet()) {
      if (entry.getValue().contains(find)) {
//        if (found == null) found = new HashMap<>();
//        if (found == null) found = new HashSet<>();
//        found.put(entry.getKey(), entry.getValue());
        found.add(new Jh1_ContactSearchResult(name, entry.getKey(), entry.getValue()));
      }
    }
    return found;
  }

}
