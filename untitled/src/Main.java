import java.util.*;

public class Main {
    /*
    1. Пусть дан произвольный список целых чисел, удалить из него четные числа
    2. Задан целочисленный список ArrayList. Найти минимальное, максимальное и среднее арифметическое из этого списка. Collections.max()
    3.*Реализовать алгоритм сортировки слиянием
     */

    public static void main(String[] args) {

        String menu="";
        Boolean runAgain=true;
        do {
            menu=showMenu();
            switch (menu){
                case "1":
                    ex1(getRandomList(10, 100));
                    break;
                case "2":
                    ex2(getRandomList(10, 100));
                    break;
                case "3":
                    ex3(getRandomList(15, 100));
                    break;

                case "0":
                    runAgain=false;
                    break;
                default:
                    System.out.println("Ошибка ввода пункта!");
                    break;
            }

        } while (runAgain);
    }
    static String showMenu(){
        System.out.println("Выберите задачу:");
        System.out.println("1.Пусть дан произвольный список целых чисел, удалить из него четные числа.");
        System.out.println("2.Задан целочисленный список ArrayList. "
                +"Найти минимальное, максимальное и среднее арифметическое из этого списка");
        System.out.println("3.*Реализовать алгоритм сортировки слиянием");
        System.out.println("0.Закончить");
        Scanner scanner = new Scanner(System.in);
        String p = scanner.nextLine();
        return p;
    }
    //Создание случайного списка длиной n, максимальное число Max
    static List<Integer> getRandomList(int n, int max){
        List<Integer> list=new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            list.add(rand.nextInt(max+1));
        }
        return list;
    }
    //1. Пусть дан произвольный список целых чисел, удалить из него четные числа
    static void ex1 (List<Integer> list){
        System.out.println("Список первоначальный:");
        System.out.println(list);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i)%2==0){
                list.remove(i);
                i--;
            }
        }
        System.out.println("Список без четных:");
        System.out.println(list);
    }
    //2. Задан целочисленный список ArrayList. Найти минимальное, максимальное и среднее арифметическое из этого списка. Collections.max()
    static void ex2 (List<Integer> list){
        System.out.println("Список:");
        System.out.println(list);
        System.out.println("Минимальное значение:"+Collections.min(list));
        System.out.println("Максимальное значение:"+Collections.max(list));
        int sum=0;
        for (Integer val : list) {
            sum+=val;
        }
        System.out.println("Средне арифмитическое значение:"+((float) sum/list.size()));
    }
    static List<Integer> mergeSort(List<Integer> list){
        if (list.size()==1 || list.size()==0){ //если одиночный или ваще нечего - нырнули до дна
            return list; // возращаем единичку = выход из рекурсии
        }
        // если что-то есть разделяем попапалам или как получится и властвуем))
        List<Integer> left=new ArrayList<>();
        List<Integer> right=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i>=(list.size()/2)){
                right.add(list.get(i));
            } else {
                left.add(list.get(i));
            }

        }
        //рекурсионно отсортируем левую и правую часть
        left=mergeSort(left);
        right=mergeSort(right);
        //создадим результирующий список
        List<Integer> result=new ArrayList<>();
        int i_left=0;
        int i_right=0;
        // Заполним его с левой части, если там меньше или справо если там меньше
        // пока хоть одна часть не кончиться
        while (i_left<left.size() && i_right<right.size()){
            if (left.get(i_left)<right.get(i_right)){
                result.add(left.get(i_left));
                i_left++;
            } else {
                result.add(right.get(i_right));
                i_right++;
            }

        }
        // Допишем остатки левой и правой части
        // можно было втащить этот цикл в предыдущий с условиями
        // но так мне понятнее
        while (i_left<left.size() ){
            result.add(left.get(i_left));
            i_left++;
        }
        while (i_right<right.size() ){
            result.add(right.get(i_right));
            i_right++;
        }
        return result;
    }
    //3.*Реализовать алгоритм сортировки слиянием
    static void ex3 (List<Integer> list){
        System.out.println("Изначальный список:");
        System.out.println(list);
        System.out.println("Осортированный список:");
        System.out.println(mergeSort(list));

        System.out.println("Сравнение скоростей:");

        List<Integer> sorted_array=new ArrayList<>();
        int num_count=10000;
        System.out.println("Массив длиной "+list.size()+" циклов сортировки "+num_count);

        long startTime = System.nanoTime();
        for (int i = 0; i < num_count; i++) {
            sorted_array=mergeSort(list);
        }
        long endTime = System.nanoTime();
        long mergeTime=endTime-startTime;
        startTime = System.nanoTime();
        for (int i = 0; i < num_count; i++) {
            sorted_array=list;
            Collections.sort(sorted_array);
        }
        endTime = System.nanoTime();
        long CollectionSortTime=endTime-startTime;

        System.out.println("Слияние "+(mergeTime/100000));
        System.out.println("Collection.sort "+(CollectionSortTime/100000));
    }
}