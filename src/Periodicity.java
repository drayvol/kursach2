import java.time.LocalDateTime;

public interface Periodicity {
    boolean checkDate(LocalDateTime dateTime);
    void setName(String name);
    LocalDateTime getDateTime();
    void setArchived(boolean archived);
    void setInfo(String Info);
}
