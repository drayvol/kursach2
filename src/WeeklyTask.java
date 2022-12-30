import java.time.LocalDateTime;

public class WeeklyTask extends Task implements Periodicity{
    public WeeklyTask(String name, String info, LocalDateTime DateTime, TypeOfTask typeOfTask){
        super(name,info,DateTime,typeOfTask);
    }
    @Override
    public boolean checkDate(LocalDateTime dateTime){
        return getDateTime().getDayOfWeek().equals(dateTime.getDayOfWeek());
    }


}
