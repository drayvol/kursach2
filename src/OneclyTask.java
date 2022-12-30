import java.time.LocalDateTime;

public class OneclyTask extends Task implements Periodicity{
    public OneclyTask(String name, String info, LocalDateTime DateTime, TypeOfTask typeOfTask){
        super(name,info,DateTime,typeOfTask);
    }
    @Override
    public boolean checkDate(LocalDateTime dateTime){
        return getDateTime().toLocalDate().equals(dateTime.toLocalDate());
    }


}
