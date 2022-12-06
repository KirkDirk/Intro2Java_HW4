package date221121.HW4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// Написать приложение для ввода ФИО, возраст и пол пользователей. 
// Данные хранить в разных списках. Сортировать пользователей по возрасту. 
// Не вывести в порядке возрастания возрастов а именно сортировать!)

public class hw4_main {
    public static void main(String[] args) {
        ArrayList<String> list_fname = new ArrayList<>(); // инициируем список имен
        ArrayList<String> list_lname = new ArrayList<>(); // инициируем список фамилий
        ArrayList<String> list_tname = new ArrayList<>(); // инициируем список отчеств
        ArrayList<Integer> list_age = new ArrayList<>(); // инициируем список возрастов 
        ArrayList<String> list_gender = new ArrayList<>(); // инициируем список полов
        List<Integer> linkedlist = new LinkedList<>(); // инициируем список индексов-связей

        boolean flag = true; // начальное значение флажка выхода
        while (flag) { // запускаем цикл считывания персональных данных
            Scanner scanner = new Scanner(System.in);
            Scanner scanner_age = new Scanner(System.in);
            Scanner scanner_gender = new Scanner(System.in);

            System.out.println("Введите ФИО: ");
            String fio = scanner.nextLine(); // считываем строку с ФИО
            System.out.println("Введите возраст: ");
            Integer age = scanner_age.nextInt(); // считываем Int значение возраста
            list_age.add(age); // записываем возраст в список
            String[] fio1 = fio.split(" "); // считываем ФИО и разбиваем по пробелам
            // предполагаем, что ввод осуществляется в порядке Ф-И-О 
            // и записываем данные при наличии
            if (fio1.length == 3) {
                list_fname.add(fio1[1]);
                list_lname.add(fio1[0]);
                list_tname.add(fio1[2]);
            } else if (fio1.length == 2) {
                list_lname.add(fio1[0]);
                list_fname.add(fio1[1]);
                list_tname.add("");
            } else {
                list_lname.add(fio1[0]);
                list_fname.add("");
                list_tname.add("");
            }
            
            System.out.println("Введите пол (M/F): ");
            String gender = scanner_gender.nextLine(); // считываем пол в латинице
            if (gender.contains("M")) { // записываем значение пола в логическом типе
                list_gender.add("true");
            }
            else  {
                list_gender.add("false");
            }
            linkedlist.add(list_age.size() - 1); // записываем значение текущего индекса-связи 
            // (при этом зацепиться можно за любой список, не только возраста)
            System.out.println("Хотите ввести еще одну персону? Y/N"); // проверяем будет ли еще записи
            Scanner scanner1 = new Scanner(System.in);
            String yn = scanner1.nextLine();
            if(yn.toUpperCase().equals("N")) { // запуск (или незапуск) ветки цикла
                flag = false;
            }
        }
        // сортировать пользователей по возрасту

        int cnt = list_age.size()-1; // определяем размер счетчика
        while (cnt > -1) { // запускаем перебор
            int max_age = list_age.get(linkedlist.get(cnt)); // задаем начальное значение мах возраста
            int index = cnt; // получаем начальний индекс мах возраста
            for (int i = 0; i < cnt; i++){ // ищем реальный мах возраста
                if (max_age < list_age.get(linkedlist.get(i))){
                    max_age = list_age.get(linkedlist.get(i)); // находим текущий мах возраста
                    index = i; // и его индекс
                }
            }
            int tmp = linkedlist.get(cnt);
            linkedlist.set(cnt, linkedlist.get(index)); // передвигаем значение мах в конец списка
            linkedlist.set(index, tmp);

            cnt--;
        }
        linkedlist.forEach(i -> // выводим списки 
        System.out.println(list_fname.get(i)+" "+
                            list_lname.get(i)+" " +
                            list_tname.get(i)+" " +
                            list_age.get(i)+" "+
                            list_gender.get(i)));
        
    }
}
