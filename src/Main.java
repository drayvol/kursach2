import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            Calendar.addTask(scanner);
                            break;
                        case 2:
                            Calendar.editTask(scanner);
                            break;
                        case 3:
                            Calendar.deleteTask(scanner);
                            break;
                        case 4:
                            Calendar.printActualTasks();
                            break;
                        case 5:
                            Calendar.printArchivedTasks();
                            break;
                        case 6:
                            Calendar.getTaskByDay(scanner);
                            break;
                        case 7:
                            Calendar.getGroupedByDate();
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println(
                """
                        1. Добавить задачу
                        2. Редактировать задачу
                        3. Удалить задачу
                        4. Получить актуальные задачи
                        5. Получить удаленные задачи
                        6. Получить задачи на определенный день
                        7. Получить задачи, сгруппированные по датам
                        0. Выход
                        """
        );
    }
}
