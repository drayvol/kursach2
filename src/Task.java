import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import Validate.Validate;
import Validate.*;

public abstract class Task {
    public static int counter = 0;
    private String name;
    private String info;
    private LocalDateTime dateTime;
    private TypeOfTask typeOfTask;
    public Integer id;
    private boolean archived;
    public Task(String name, String info, LocalDateTime dateTime, TypeOfTask typeOfTask){
        this.name = Validate.validateString(name);
        this.info = Validate.validateString(info);
        this.dateTime = dateTime;
        id = counter++;
        this.typeOfTask = typeOfTask;
        this.archived = false;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Validate.validateString(name);
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = Validate.validateString(info);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getId() {
        return id;
    }

    public static int getCounter() {
        return counter;
    }

    public TypeOfTask getTypeOfTask() {
        return typeOfTask;
    }

    public void setTypeOfTask(TypeOfTask typeOfTask) {
        this.typeOfTask = typeOfTask;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return id == task.id && archived == task.archived && Objects.equals(name, task.name) && Objects.equals(info, task.info) && Objects.equals(dateTime, task.dateTime) && Objects.equals(typeOfTask, task.typeOfTask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, info, dateTime, typeOfTask, id, archived);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", dateTime=" + dateTime +
                ", typeOfTask=" + typeOfTask +
                ", id=" + id +
                ", archived=" + archived +
                '}';
    }
}
