import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Test class for playing
 */
public class Main {
    public static void main(String[] args){
        //System.out.println("Chosen mode is: " + mode);
        Scanner sc = new Scanner(System.in);
        //sc.nextLine();
        List<List<String>> list = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            clearScreen();
            List<String> row = new ArrayList<>();
            System.out.print("Set ID: ");
            String ID = sc.nextLine();
            System.out.print("Set Name: ");
            String name = sc.nextLine();
            System.out.print("Set Phone: ");
            String phone = sc.nextLine();
            row.add(ID + ", " + name + ", " + phone);
            list.add(row);
        }
        int mode;
        mode = getMenuItem();
        switch (mode){
            case 12:
                clearScreen();
                System.out.println();
                System.out.println("  <===          Список клиентов           ===>");
                System.out.println();
                System.out.println("  ID\t| Имя\t\t| Номер телефона");
                System.out.println("  -------------------------------------------");
                //list.foreach(System.out::println);
                for(List<String> line : list){
                    for(String s : line){
                        String[] s2 = s.split(", ");
                        if (s2[1].length() < 5) {
                            s2[1] = s2[1] + "\t";
                        }
                        System.out.print("  " + s2[0] + "\t| " + s2[1] + "\t| " + s2[2] + "\n");
                    }
                }
                System.out.println("  -------------------------------------------");
                System.out.println();
                System.out.println("  Меню: ");
                System.out.println("  1 - Назад | 2 - Добавить | 3 - Изменить | 4 - Удалить ");
                System.out.println("  -----  ");
                System.out.print("  Command => ");
                sc.nextLine();
                break;
            default:
                System.out.println("Другой режим");
                sc.nextLine();
                System.exit(0);
        }
    }
//TODO Создать метод бла-бла
    private static void clearScreen() {
        //Очистка экрана - строка ввода в нижней части экрана
        /*for (int i = 0; i < 100; i++) {
            System.out.println("\b");
        }*/
        //Очистка экрана - строка ввода в верхней части экрана
        final String OS = System.getProperty("os.name");
        if (OS.contains("Windows")) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        } else {
            final String ANSI_CLS = "\u001b[2J";
            final String ANSI_HOME = "\u001b[H";
            System.out.print(ANSI_HOME + ANSI_CLS);
            System.out.flush();
        }
    }

    private static int getMenuItem() {
        Scanner sc = new Scanner(System.in);
        int select = 0;
        showMainMenu();
        try {
            select = sc.nextInt();
        } catch (InputMismatchException exception){
            getMenuItem();
        }
        sc.nextLine();
        select = getSubMenu(select);
        return select;
    }

    private static int getSubMenu(int select){
        int sub = 0;
        Scanner sc = new Scanner(System.in);
        select *= 10;
        switch (select){
            case 10:
                showClientMenu();
                break;
            case 20:
                showOrderMenu();
                break;
            case 30:
                showExitMenu();
                break;
            default:
                getMenuItem();
                break;
        }
        try {
            sub = select + sc.nextInt();
        } catch (InputMismatchException exception){
            getSubMenu(select / 10);
        }
        sc.nextLine();
        while (sub % 10 > 2){
            sub = getSubMenu(select / 10);
        }
        if (sub % 10 == 1) {
            getMenuItem();
        } else if (sub == 32) {
            System.exit(0);
        }
        return sub;
    }

    private static void showMainMenu(){
        clearScreen();
        System.out.println();
        System.out.println("  <=  Меню   =>  ");
        System.out.println("  -------------  ");
        System.out.println("  1 = Клиенты    ");
        System.out.println("  2 = Заказы     ");
        System.out.println("  3 = Выход      ");
        System.out.println("  -------------  ");
        System.out.print("  Command => ");
    }


    private static void showExitMenu(){
        clearScreen();
        System.out.println();
        System.out.println("  <=  Выход  =>  ");
        System.out.println("  -------------  ");
        System.out.println("  1 = Назад      ");
        System.out.println("  2 = Выход      ");
        System.out.println();
        System.out.println("  -------------  ");
        System.out.print("  Command => ");
    }

    private static void showClientMenu(){
        clearScreen();
        System.out.println();
        System.out.println("  <= Клиенты =>  ");
        System.out.println("  -------------  ");
        System.out.println("  1 = Назад      ");
        System.out.println("  2 = Просмотр   ");
        System.out.println();
        System.out.println("  -------------  ");
        System.out.print("  Command => ");
    }

    private static void showOrderMenu(){
        clearScreen();
        System.out.println();
        System.out.println("  <= Заказы  =>  ");
        System.out.println("  -------------  ");
        System.out.println("  1 = Назад      ");
        System.out.println("  2 = Просмотр   ");
        System.out.println();
        System.out.println("  -------------  ");
        System.out.print("  Command => ");
    }
}
