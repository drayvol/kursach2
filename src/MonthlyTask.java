import java.time.LocalDateTime;

public class MonthlyTask extends Task implements Periodicity{
    public MonthlyTask(String name, String info, LocalDateTime DateTime, TypeOfTask typeOfTask){
        super(name,info,DateTime,typeOfTask);
    }
    @Override
    public boolean checkDate(LocalDateTime dateTime){
        return getDateTime().getMonth().equals(dateTime.getMonth());
    }



}