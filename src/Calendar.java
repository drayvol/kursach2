import Validate.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Calendar {
    private static final Map<Integer, Periodicity> actualTasks = new HashMap<>();
    private static final Map<Integer, Periodicity> archivedTasks = new HashMap<>();
    public static void addTask(Scanner scanner){
        scanner.nextLine();
        System.out.println("Введите название задачи: ");
        String name = Validate.validateString(scanner.nextLine());
        System.out.println("Введите описание задачи: ");
        String info = Validate.validateString(scanner.nextLine());
        System.out.println("Выберите тип задачи: 0 - Рабочая, 1 - Личная");
        TypeOfTask typeOfTask = TypeOfTask.values()[Validate.validateTypeOfTask(scanner.nextInt())];
        System.out.println("Выберите повторяемость задачи: 0 - однократная, 1 - ежедневная, 2 - еженедельная, 3 - ежемесячная, 4 - ежегодная");
        int period = Validate.validatePeriodicity(scanner.nextInt());
        System.out.println("Введите дату в формате dd.mm.yyyy hh:mm");
        scanner.nextLine();
        createTask(scanner, name, info, typeOfTask, period);

    }
    public static void createTask(Scanner scanner, String name, String info, TypeOfTask typeOfTask, int period){
       try {
           LocalDateTime taskDate = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
           Periodicity task = null;
           task = create(period, name, info, typeOfTask, taskDate);
       }
       catch (DateTimeParseException e) {
           System.out.println("Неверно введена дата");
       }
    }
    private static Periodicity create(int period, String name, String info, TypeOfTask typeOfTask, LocalDateTime dateTime) {
        switch (period) {
            case 0: {
                OneclyTask oneclyTask = new OneclyTask(name, info, dateTime, typeOfTask);
                actualTasks.put(oneclyTask.getId(), oneclyTask);
                return oneclyTask;
            }
            case 1: {
                DailyTask dailyTask = new DailyTask(name, info, dateTime, typeOfTask);
                actualTasks.put(dailyTask.getId(), dailyTask);
                return dailyTask;
            }
            case 2: {
                WeeklyTask weeklyTask = new WeeklyTask(name, info, dateTime, typeOfTask);
                actualTasks.put(weeklyTask.getId(), weeklyTask);
                return weeklyTask;
            }
            case 3: {
                MonthlyTask monthlyTask = new MonthlyTask(name, info, dateTime, typeOfTask);
                actualTasks.put(monthlyTask.getId(), monthlyTask);
                return monthlyTask;
            }
            case 4: {
                AnnualTask annualTask = new AnnualTask(name, info, dateTime, typeOfTask);
                actualTasks.put(annualTask.getId(), annualTask);
                return annualTask;
            }
            default:
                return null;
        }
    }
        public static void deleteTask(Scanner scanner) {
            System.out.println("Актуальные задачи \n");
            printActualTasks();
            System.out.println("Введите ID задачи, которую хотите удалить");
            int id = scanner.nextInt();
            if(actualTasks.containsKey(id)){
                Periodicity deletedTask = actualTasks.get(id);
                deletedTask.setArchived(true);
                archivedTasks.put(id, deletedTask);
                System.out.println("Задача " + id + " удалена \n");
            }else System.out.println("Такой задачи не существует \n");


        }
        public static void getTaskByDay(Scanner scanner){
            System.out.println("Введите дату в формате dd.mm.yyyy: ");
            try {
                LocalDate requestedDate = LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("dd.MM.yyy"));
                List<Periodicity> foundTasks = findTaskByDate(requestedDate);
                System.out.println("События на " + requestedDate + ":");
                for (Periodicity task : foundTasks) {
                    System.out.println(task);
                }
            }
            catch (DateTimeParseException e){
                System.out.println("Неверно введена дата");
            }
            scanner.nextLine();
            System.out.println("Для выхода нажмите enter \n");

        }
        public static void printArchivedTasks(){
            for (Periodicity task: archivedTasks.values()) {
                System.out.println(task);
            }
        }
        public static void printActualTasks(){
            for (Periodicity task:actualTasks.values()) {
                System.out.println(task);
            }
        }
        public static List<Periodicity> findTaskByDate(LocalDate localDate){
            List<Periodicity> tasks = new ArrayList<>();
            for (Periodicity task:actualTasks.values()) {
                if(task.checkDate(localDate.atStartOfDay())) tasks.add(task);
            }
            return tasks;
        }
        public static void editTask(Scanner scanner){
            System.out.println("Введите ID задачи, которую хотите редактировать");
            printActualTasks();
            int id = scanner.nextInt();
            if(!actualTasks.containsKey(id)) throw new IllegalArgumentException("Невверно веден ID");
            System.out.println("Редактировать: 0 - название, 1 - описание");
            int choice = scanner.nextInt();
            switch (choice){
                case 0:{
                    scanner.nextLine();
                    System.out.println("Введите новое название задачи");
                    String name = Validate.validateString(scanner.nextLine());
                    Periodicity task = actualTasks.get(id);
                    task.setName(name);
                }
                case 1:{
                    scanner.nextLine();
                    System.out.println("Введите новое описание задачи");
                    String info = Validate.validateString(scanner.nextLine());
                    Periodicity task = actualTasks.get(id);
                    task.setInfo(info);
                }

            }

        }


        public static void getGroupedByDate() {
            Map<LocalDate, ArrayList<Periodicity>> taskMap = new HashMap<>();

            for (Map.Entry<Integer, Periodicity> entry : actualTasks.entrySet()) {
                Periodicity task = entry.getValue();
                LocalDate localDate = task.getDateTime().toLocalDate();
                if (taskMap.containsKey(localDate)) {
                    taskMap.get(localDate).add(task);
                } else {
                    taskMap.put(localDate, new ArrayList<>(Collections.singletonList(task)));
                }

            }

            for (Map.Entry<LocalDate, ArrayList<Periodicity>> taskEntry : taskMap.entrySet()) {
                System.out.println(taskEntry.getKey() + " : " + taskEntry.getValue());

            }
        }



}


  /* return switch(period){
            case 0:
            {
                OneclyTask oneclyTask = new OneclyTask(name, info,dateTime, typeOfTask);
                actualTasks.put(oneclyTask.getId(), oneclyTask);
                yield oneclyTask;
            }
            case 1:
            {
                DailyTask dailyTask = new DailyTask(name, info,dateTime, typeOfTask);
                actualTasks.put(dailyTask.getId(), dailyTask);
                yield dailyTask;
            }
            case 2:
            {
                WeeklyTask weeklyTask = new WeeklyTask(name, info,dateTime, typeOfTask);
                actualTasks.put(weeklyTask.getId(), weeklyTask);
                yield weeklyTask;
            }
            case 3:
            {
                MonthlyTask monthlyTask = new MonthlyTask(name, info,dateTime, typeOfTask);
                actualTasks.put(monthlyTask.getId(), monthlyTask);
                yield monthlyTask;
            }
            case 4:
            {
                AnnualTask annualTask = new AnnualTask(name, info,dateTime, typeOfTask);
                actualTasks.put(annualTask.getId(), annualTask);
                yield annualTask;
            }
            default: yield null;
        };*/