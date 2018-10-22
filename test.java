import java.io.*;
import java.util.ArrayList;

public class test {
    private static boolean load = false;
    public static void main(String[] args) throws Exception {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Person> list = new ArrayList<>();
        int count = 0;
        try {
            FileInputStream fileInputStream = new FileInputStream("C:/111/test.txt");
            BufferedReader rd = new BufferedReader(new InputStreamReader(fileInputStream));
            String countString = rd.readLine();
            try{
                count = Integer.parseInt(countString);
                if(count>0) {
                    for (int i = 0; i < count; i++) {
                        newRecord(list, rd);
                    }
                    System.out.println("Данные считаны");
                    Thread.sleep(1000);
                }
            }
            catch (Exception e){
                System.out.println("Считать данные не удалось");
                Thread.sleep(1000);
            }
        }
        catch (Exception e){
            System.out.println("Файл не найден");
            Thread.sleep(1000);
        }
        Runtime.getRuntime().exec("cls");
        load = true;
        int s = 100;
            while (s!=0) {
                menu();
                s = Integer.parseInt(reader.readLine());
                switch (s) {
                    case 1:
                        newRecord(list, reader);
                        count++;
                        Runtime.getRuntime().exec("cls");
                        break;
                    case 2: System.out.println("Введите номер записи которую хотите изменить");
                            rewriteRecord(list.get(Integer.parseInt(reader.readLine())-1),reader);
                            System.out.println();
                            Thread.sleep(1000);
                            Runtime.getRuntime().exec("cls");
                            break;
                    case 3: System.out.println("Введите номер записи которую хотите изменить");
                            deleteRecord(Integer.parseInt(reader.readLine())-1,list);
                            System.out.println();
                            Thread.sleep(1000);
                            Runtime.getRuntime().exec("cls");
                            break;
                    case 4: System.out.println("По какому имени искать?");
                            seachName(list, reader.readLine());
                            System.out.println();
                            Thread.sleep(1000);
                            Runtime.getRuntime().exec("cls");
                            break;
                    case 5: System.out.println("По какой должности искать?");
                            seachWork(list, reader.readLine());
                            System.out.println();
                            Thread.sleep(1000);
                            Runtime.getRuntime().exec("cls");
                            break;
                    case 6: System.out.println("По какому возрасту искать?");
                            seachAge(list, Integer.parseInt(reader.readLine()));
                            System.out.println();
                            Thread.sleep(1000);
                            Runtime.getRuntime().exec("cls");
                            break;
                    case 7: System.out.println("По какой зарплате искать?");
                            seachWage(list,Integer.parseInt(reader.readLine()));
                            System.out.println();
                            Thread.sleep(1000);
                            Runtime.getRuntime().exec("cls");
                            break;
                    case 8: System.out.println("По какому полу искать(m/w)?");
                            seachSex(list,reader.readLine().equals("m"));
                            System.out.println();
                            Thread.sleep(1000);
                            Runtime.getRuntime().exec("cls");
                            break;
                    case 9: Runtime.getRuntime().exec("cls");
                            outList(list);
                            System.out.println();
                            Thread.sleep(4000);
                            break;
                    case 10: saveFile(list);
                            System.out.println();
                            Thread.sleep(1000);
                            Runtime.getRuntime().exec("cls");
                            break;

                }
            }

    }

    public static void newRecord(ArrayList list, BufferedReader reader){
        String name;
        String work;
        int age;
        boolean sex;
        int wage;
        if(load) {
            System.out.println("Введите имя, должность, возраст, пол(m/w),зарплату. Каждое с новой строки");
        }
        try {
            name = reader.readLine();
            work = reader.readLine();
            age = Integer.parseInt(reader.readLine());
            if(reader.readLine().equals("m")){
                sex = true;
            } else{
                sex = false;
            }
            wage = Integer.parseInt(reader.readLine());
            list.add(new Person(name,work,age,sex,wage));
        }
        catch (Exception e){
            System.exit(0);
        }
    }
    public static void saveFile(ArrayList<Person> list){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("C:/111/test.txt"));
            writer.write(Integer.toString(list.size()));
            writer.newLine();
            for(int i = 0;i< list.size();i++){
            Person person = list.get(i);
            writer.write(person.name);writer.newLine();
            writer.write(person.work);writer.newLine();
            writer.write(Integer.toString(person.age));writer.newLine();
            if(person.sex){
                writer.write("m");writer.newLine();
            } else{
                writer.write("w");writer.newLine();
            }
            writer.write(Integer.toString(person.wage));writer.newLine();
            }
            writer.flush();
            writer.close();
            }
            catch (Exception e){
            System.out.println("Файл записать не удалось");
        }
    }
    public static void rewriteRecord(Person person, BufferedReader reader){
        System.out.println("1:Изменить должность");
        System.out.println("2:Изменить зарплату");
        System.out.println("3:Изменить возраст");
        System.out.println("4:Ничего не изменять");
        try{
            int s = Integer.parseInt(reader.readLine());
            switch (s){
                case 1: System.out.println("Введите новую должность");
                person.work = reader.readLine();
                break;
                case 2: System.out.println("Введите новую зарплату");
                    person.wage = Integer.parseInt(reader.readLine());
                    break;
                case 3: System.out.println("Введите новый возраст");
                    person.age = Integer.parseInt(reader.readLine());
                    break;
            }
        }
        catch (Exception e){
            System.out.println("Некорректные данные");
        }
    }
    public static void deleteRecord(int i,ArrayList<Person> list){
        list.remove(i);
    }
    public static void outList(ArrayList<Person> list){
        for(int i = 0; i<list.size();i++){
            Person p = list.get(i);
            System.out.println("id записи="+(i+1)+" Имя='"+p.name +"' Должность='"+p.work + "' Возраст='" + p.age + "' Пол='" + (p.sex?"man":"woman") + "' Зарплата='" + p.wage+"'");
        }
    }
    public static void menu(){
        System.out.println("1:Добавить запись");
        System.out.println("2:Изменить запись №_____");
        System.out.println("3:Удалить запись №_____");
        System.out.println("4:Поиск по имени");
        System.out.println("5:Поиск по профессии");
        System.out.println("6:Поиск по возрасту");
        System.out.println("7:Поиск по зарплате");
        System.out.println("8:Поиск по полу");
        System.out.println("9:Вывести список");
        System.out.println("10:Сохранить данные");
        System.out.println("0:Выход");
    }
    public static void seachName(ArrayList<Person> list,String name){
        int count = 0;
        for(int i = 0;i<list.size();i++){
            if(list.get(i).name.equals(name)){
                outRecord(i,list);
                count++;
            }
            if(count==0){
                System.out.println("Записей не найдено");
            }
        }
    }
    public static void seachWork(ArrayList<Person> list,String work){
        int count = 0;
        for(int i = 0;i<list.size();i++){
            if(list.get(i).work.equals(work)){
                outRecord(i,list);
                count++;
            }
            if(count==0){
                System.out.println("Записей не найдено");
            }
        }
    }
    public static void seachAge(ArrayList<Person> list,int age){
        int count = 0;
        for(int i = 0;i<list.size();i++){
            if(list.get(i).age ==age){
                outRecord(i,list);
                count++;
            }
            if(count==0){
                System.out.println("Записей не найдено");
            }
        }
    }
    public static void seachWage(ArrayList<Person> list,int wage){
        int count = 0;
        for(int i = 0;i<list.size();i++){
            if(list.get(i).wage==wage){
                outRecord(i,list);
                count++;
            }
            if(count==0){
                System.out.println("Записей не найдено");
            }
        }
    }
    public static void seachSex(ArrayList<Person> list,boolean sex){
        int count = 0;
        for(int i = 0;i<list.size();i++){
            if(list.get(i).sex ==sex){
                outRecord(i,list);
                count++;
            }
            if(count==0){
                System.out.println("Записей не найдено");
            }
        }
    }
    public static void outRecord(int i, ArrayList<Person> list){
        Person p = list.get(i);
        System.out.println("id записи="+(i+1)+" Имя='"+p.name +"' Должность='"+p.work + "' возраст='" + p.age + "' Пол='" + (p.sex?"man":"woman") + "' Зарплата='" + p.wage+"'");
    }

}