import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {

        Employee[] employees = new Employee[10];
        employees[0] = new Employee("Петров Иван Васильевич", 3, 43345);
        employees[1] = new Employee("Васильев Петр Иванович", 4, 54211);
        employees[2] = new Employee("Петров Василий Иванович", 3, 25678);
        employees[3] = new Employee("Иванов Петр Васильевич", 1, 67783);
        employees[4] = new Employee("Петров Василий Иванович", 2, 34578);
        employees[5] = new Employee("Иванов Василий Петрович", 2, 15633);
        employees[6] = new Employee("Петров Петр Петрович", 5, 37733);
        employees[7] = new Employee("Васильев Василий Иванович", 2, 24677);
        employees[8] = new Employee("Иванов Иван Иванович", 4, 75899);
        employees[9] = new Employee("Капитан Джек Воробей", 1, 37348);

        printAllEmployyes(employees);
        System.out.println("Суммарная зарплата сотрудников =  " + getSalarySummorize(employees));
        Employee loserEmployer = findMinimalSalary(employees);
        Employee luckyEmployer = findMaximalSalary(employees);
        System.out.println("Средняя зарплата сотрудников = " + getAverageSalary(employees));
        printEmployeesNames(employees);
    }

    private static void printEmployeesNames(Employee[] employees) {
        for (int i = 0; i < employees.length; i++) {
            System.out.println(++i + ". " + employees[i].getName());
        }
    }

    private static int getAverageSalary(Employee[] employees) {
        return getSalarySummorize(employees)/ employees.length;
    }

    private static Employee findMaximalSalary(Employee[] employees) {
        Employee lucky;
        if(employees==null){
            System.out.println("Список сотрудников пустой");
            return null;
        }else {
            lucky=employees[0];
            for (int i = 1; i < employees.length; i++) {
                if (employees[i].getSalary()>lucky.getSalary()){
                    lucky=employees[i];
                }
            }
        }
        System.out.println("Самый счастливый сотрудник - " +lucky.getName() );
        return lucky;
    }

    private static Employee findMinimalSalary(Employee[] employees) {
        Employee loser;
        if(employees==null){
            System.out.println("Список сотрудников пустой");
            return null;
        }else {
            loser=employees[0];
            for (int i = 1; i < employees.length; i++) {
                if (employees[i].getSalary()<loser.getSalary()){
                    loser=employees[i];
                }
            }
        }
        System.out.println("Самый большой неудачник- " +loser.getName() );
        return loser;
    }

    private static int getSalarySummorize(Employee[] employees) {
        int sumSalary = 0;
        for(int i = 0 ;i< employees.length; i++){
            sumSalary+=employees[i].getSalary();
        }
        //System.out.println("Суммарная зарплата = " + sumSalary);
        return sumSalary;
    }

    private static void printAllEmployyes(Employee[] employees) {
        System.out.println("Список сотрудников");
        for(int i = 0 ;i< employees.length; i++){
            if (employees[i]!=null){
                System.out.println(employees[i]);
            }
        }
    }
}