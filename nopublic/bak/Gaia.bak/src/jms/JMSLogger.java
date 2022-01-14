package jms;
import java.util.*;  // for Collection

public interface JMSLogger {
  public void writeEntry(Collection entry); // Write list of lines
  public void writeEntry(String entry);     // Write single line
}