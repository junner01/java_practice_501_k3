package ex_241023_ch7.homework;

public final class Jh1_ContactSearchResult {
  public final String name;
  public final String contactType;
  public final String contact;

  public Jh1_ContactSearchResult(String name, String contactType, String contact) {
    this.name = name;
    this.contactType = contactType;
    this.contact = contact;
  }

  @Override
  public String toString() {
    return String.format("%16s's %-8s : %s", name, contactType, contact);
  }
}
