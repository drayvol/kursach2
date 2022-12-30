import java.time.LocalDateTime;

public class AnnualTask  extends Task implements Periodicity{
    public AnnualTask(String name, String info, LocalDateTime DateTime, TypeOfTask typeOfTask){
        super(name,info,DateTime,typeOfTask);
    }
    @Override
    public boolean checkDate(LocalDateTime dateTime){
        return getDateTime().getYear()==dateTime.getYear();
    }



}