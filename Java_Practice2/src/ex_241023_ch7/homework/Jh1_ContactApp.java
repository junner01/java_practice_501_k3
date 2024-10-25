package ex_241023_ch7.homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public final class Jh1_ContactApp {
  private final ArrayList<Jh1_Person> contacts = new ArrayList<>();

  private Jh1_ContactApp() {}

  public static void main(String[] args) {
    new Jh1_ContactApp().loopMainMenu();
  }

  /**
   * Try parse given string into Whole number(>=0 integer), Returns -1 on parse error.
   * */
  int toWhole(String s) {
    try {
      return Integer.parseUnsignedInt(s);
    } catch (NumberFormatException e) {
      return -1;
    }
  }

  void loopMainMenu() {

    final String[] mainOptions = {"1", "2", "3", "4", "5", "6"};
    String choice = "";
    do {
      if (contacts.isEmpty()) {
        loopAddContact(true);
      } else {
        String sb = "=== [ MainMenu ] ===\n" +
            String.format(" %3d Contacts right here.\n", contacts.size()) +
            "[1] Add Contact\n" +
            "[2] List\n" +
            "[3] Search by name\n" +
            "[4] Search by contact\n" +
            "[5] Pick random\n" +
            "[6] Quit\n";

        choice = Jh1_Prompt.option(sb, "Select menu (1~6)", mainOptions);
        switch (choice) {
          case "1":
            loopAddContact(false);
            break;
          case "2":
            loopList();
            break;
          case "3":
            loopNameSearch();
            break;
          case "4":
            loopContactSearch();
            break;
          case "5":
            loopPick();
            break;
          case "6":
          default:
            break;
        }
      }
    } while (!choice.equals("6"));

  }

  void loopAddContact(boolean useMain) {
    System.out.println("\n\n=== [ Add Contact ] ===");
    if (useMain) System.out.println("** You have no people in your Contacts. Let's get started from adding one.");
    String name = Jh1_Prompt.text("Enter the name of person.");
    String contact = Jh1_Prompt.text("Enter the contact.");
    String contactType = Jh1_Prompt.textDefault("Contact description", "phone");
    contacts.add(new Jh1_Person(name, contactType, contact));
  }

  void loopList() {
    while (true) {
      StringBuilder sb = new StringBuilder();
      sb.append("\n\n=== [ Contact List ] ===\n");
      int i = 1;
      for (Jh1_Person person : contacts) sb.append(String.format("[ %d ] %s\n", i++, person.toString()));

      String choice = Jh1_Prompt.option(sb.toString(),
          "Select an index of person, or type \"//\" to quit",
          s -> {
            if (s.equals("//")) return true;
            int e = toWhole(s);
            return e >= 1 && e <= contacts.size();
          });
      if (choice.equals("//")) return;
      int choiceIndex = toWhole(choice) - 1;

      inspectPerson(contacts.get(choiceIndex));
    }
  }

  void loopNameSearch() {
    while (true) {
      String q = Jh1_Prompt.text("\n\n=== [ Search by name ] ===\n"
        + String.format("%d Contacts out there.\n", contacts.size()),
        "Input name to find. Type \"//\" for quit.");

      if (q.equals("//")) return;

      List<Jh1_Person> result = searchByName(q);
      if (result.isEmpty()) {
        System.out.printf("No contacts found by name contains \"%s\"\n", q);
        continue;
      }
      System.out.printf("%d contact(s) found.\n", result.size());
      for (Jh1_Person p : result) {
        System.out.println(p);
      }
    }
  }

  void loopContactSearch() {
    while (true) {
      String q = Jh1_Prompt.text("\n\n=== [ Search by contact ] ===\n"
          + String.format("%d Contacts out there.\n", contacts.size()),
        "Input contact \"value\" to find. Type \"//\" for quit.");

      if (q.equals("//")) return;

      List<Jh1_ContactSearchResult> result = searchByContactValue(q);
      if (result.isEmpty()) {
        System.out.printf("No contacts found by \"%s\"\n", q);
        continue;
      }
      System.out.printf("%d contact(s) found.\n", result.size());
      for (Jh1_ContactSearchResult p : result) {
        System.out.println(p);
      }
    }
  }

  void loopPick() {
    System.out.println("\n\n=== [ Pick random ] ===\n");
    Random random = new Random();
    Jh1_Person pick = contacts.get(random.nextInt(contacts.size()));
    System.out.println(pick);
  }

  void inspectPerson(Jh1_Person person) {
    System.out.println("\n=== [ Person Info ] ===");
    System.out.println(person.verbose());
  }

  List<Jh1_Person> searchByName(String q) {
//    return contacts.stream().filter(p -> p.getName().contains(q)).toList();
    ArrayList<Jh1_Person> found = new ArrayList<>();
    for (Jh1_Person person : contacts) {
      if (person.getName().contains(q)) {
        found.add(person);
      }
    }
    return found;
  }

  List<Jh1_ContactSearchResult> searchByContactValue(String q) {
    ArrayList<Jh1_ContactSearchResult> found = new ArrayList<>();
    for (Jh1_Person person : contacts) {
      Set<Jh1_ContactSearchResult> m = person.findContact(q);
      found.addAll(m);
    }
    return found;
  }

}
