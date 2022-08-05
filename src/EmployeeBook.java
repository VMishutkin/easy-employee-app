import java.util.ArrayList;

public class EmployeeBook {
    private Employee[] employees;// массив сотрудников
    private int bookSize; // переменная нужна для реализации динамического размера массива
    public EmployeeBook(){
        //Реализовал возможность динамически менять размер массива, чтобы не использовать коллекции а воспользоваться только пройденным материалом
        employees = new Employee[0];
    }
    private EmployeeBook(Employee[] department){
        //Приватный конструктор для создания отдельного эксемпляра класса из массива сотрудников для работы с подмассивами, например с отделом
        this.employees = department;
        this.bookSize = department.length;
    }
    public void addEmployee(String name, int department, int salary){
        if(name==null){
            System.out.println("Введите имя сотрудника");
            return;
        }
        Employee newEmployee= new Employee(name, department, salary);// создаем нового сотрудника
        Employee[] bookBeforExpansion = employees; // сохраняем указатель на старый массив до изменения размера
        employees = new Employee[++bookSize];// создаем новый массив с размером на 1 больше
        //цикл копирования сотрудников из старого массива
        for (int i = 0; i < bookBeforExpansion.length; i++) {
            employees[i] = bookBeforExpansion[i];
        }
        employees[bookSize-1] = newEmployee; // добавляем нового сотрудника в последнюю ячейку

    }
    public boolean deleteEmployee(String deletingEmployeeName){
        boolean isDeleted = false;//переменная которая указывает получилось ли найти сотрудника с соответствующим именем для удаления
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getName().equals(deletingEmployeeName)){
                System.out.println("Сотрудник " + employees[i].getName() + " удален");
                employees[i]=null;// если нашли сотрудника обнуляем указатель на него
                isDeleted= true;
            }
        }

        if(isDeleted){
            // создаем новый массив размером на 1 меньше
            Employee[] bookBeforDeleting = employees;
            employees = new Employee[--bookSize];
            int counter = 0;
            //копируем в цикле все элементы предыдущего массива, кроме того что равен null
            for (int i = 0; i < bookBeforDeleting.length; i++) {
                if(bookBeforDeleting[i]!=null){
                    employees[counter++] = bookBeforDeleting[i];
                }
            }
            return true;
        }else {
            System.out.println("Сотрудник с таким именем не найден");
            return false;
        }
    }

    public boolean deleteEmployee(int deletingId){
        //принцип тот же что и с удалением по имени.
        boolean isDeleted = false;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getId()==deletingId){
                System.out.println("Сотрудник " + employees[i].getName() + " удален");
                employees[i]=null;
                isDeleted= true;
            }
        }
        if(isDeleted){
            Employee[] bookBeforDeleting = employees;
            employees = new Employee[--bookSize];
            int counter = 0;
            for (int i = 0; i < bookBeforDeleting.length; i++) {
                if(bookBeforDeleting[i]!=null){
                    employees[counter++] = bookBeforDeleting[i];
                }
            }
            return true;
        }else {
            System.out.println("Сотрудник с таким именем не найден");
            return false;
        }
    }

    public int getSalarySummorize() {
        //Суммируем все зарплаты.
        int sumSalary = 0;
        for(int i = 0 ;i< employees.length; i++){
            sumSalary+=employees[i].getSalary();
        }
        //System.out.println("Суммарная зарплата = " + sumSalary);
        // Не делаю вывод в консоль чтобы при вызове метода из другого не получать лишнюю строку
        return sumSalary;
    }

    public void printAllEmployeesInfo() {
        System.out.println("Список сотрудников");
        for(int i = 0 ;i< employees.length; i++){
            if (employees[i]!=null){
                System.out.println(employees[i]);
            }
        }
    }
    public void printEmployeesNames() {
        System.out.println("Все сотрудники: ");
        for (int i = 0; i < employees.length; i++) {
            System.out.println((i + 1) + ". " + employees[i].getName());
        }
    }

    public int getAverageSalary() {
        //Использую метод из пункта про сумму, но приходится не делать вывод чтобы не получать строку про сумму зарплат
        return getSalarySummorize()/ employees.length;
    }

    public Employee findMaximalSalary() {
        Employee lucky;// временная переменная.
        if(employees==null){
            System.out.println("Список сотрудников пустой");
            return null;
        }else {
            //По классике обозначаем первого самым богатым, сравниваем с каждым по очереди и если у кого то зп больше то меняем указатель на него;
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

    public Employee findMinimalSalary() {
        Employee loser;
        if(employees==null){
            System.out.println("Список сотрудников пустой");
            return null;
        }else {
            //По классике обозначаем первого самым бедным, сравниваем с каждым по очереди и если у кого то зп меньше то меняем указатель на него;
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
    public Employee[] printSalariesLowerThreshold(int upperThreshold) {
        System.out.println("Сотрудники у которых зарплата меньше "+ upperThreshold + " рублей:");
        int employeesMassiveSize=0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getSalary() < upperThreshold){
                employeesMassiveSize++;
            }
        }
        //System.out.println(" Размер отдела " + departmentSize + " Человек");
        Employee[] emplyyesWithLessSalaries = new Employee[employeesMassiveSize];
        int counter = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getSalary() < upperThreshold){
                System.out.println(employees[i]);
                emplyyesWithLessSalaries[counter++] = employees[i];
            }
        }
        return emplyyesWithLessSalaries;
    }

    public Employee[] printSalariesUpperThreshold(int lowerThreshold) {
        System.out.println("Сотрудники у которых зарплата больше "+ lowerThreshold + " рублей:");

        int employeesMassiveSize=0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getSalary() > lowerThreshold){
                employeesMassiveSize++;
            }
        }
        //System.out.println(" Размер отдела " + departmentSize + " Человек");
        Employee[] emplyeesWithMoreSalaries = new Employee[employeesMassiveSize];
        int counter = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getSalary() > lowerThreshold){
                System.out.println(employees[i]);
                emplyeesWithMoreSalaries[counter++] = employees[i];
            }
        }
        return emplyeesWithMoreSalaries;

    }

    public boolean getDepartmentInfoAndIndexing(int departmentID, int indexingSize) {
        //создаем новый Employeebook из массива сотрудников отдела, проводим требуемые в задаче операции.
        System.out.println("Находим информацию по отделу " + departmentID);
        if(getDepartmentsEmployees(departmentID)==null){
            System.out.println("Нет информации по отделу" + departmentID);
            return false;
        }else {
            EmployeeBook department = new EmployeeBook(getDepartmentsEmployees(departmentID));
            department.findMinimalSalary();
            department.findMaximalSalary();
            department.getSalarySummorize();
            department.getAverageSalary();
            department.indexingSalaries(indexingSize);
            department.printAllEmployeesInfo();
            return true;
        }
    }

    private Employee[] getDepartmentsEmployees(int departmentID) {
        //Определяем сколько сотрудников в отделе
        int departmentSize=0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getDepartment() == departmentID){
                departmentSize++;
            }
        }
        if (departmentSize>0) {
            //Создаем новый массив сотрудников, только из одного отдела.
            Employee[] departmentEmployees = new Employee[departmentSize];
            int departmentCounter = 0;
            for (int i = 0; i < employees.length; i++) {
                if (employees[i].getDepartment() == departmentID) {
                    departmentEmployees[departmentCounter++] = employees[i];
                }
            }
            return departmentEmployees;
        } else {
            System.out.println("Нет сотрудников в отделе");
            return null;
        }
    }

    public void indexingSalaries(int procentIncrease) {
        for (int i = 0; i < employees.length; i++) {
            int newSalary = employees[i].getSalary() * (100 + procentIncrease) /100;
            employees[i].setSalary(newSalary);
        }
        System.out.println("Зарплаты проиндексированы на " + procentIncrease +" процентов");
    }
//
    public boolean changeSalary(String employeeName, int newSalary) {
        //находим сотрудника, вызываем его сеттер зарплаты, возвращаем true если сотрудник найден, false если не удалось найти.
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getName().equals(employeeName)){
                employees[i].setSalary(newSalary);
                System.out.println("Зарплата сотрудника " + employees[i].getName() + " изменена на " + newSalary + " рублей");

                return true;
            }
        }
        System.out.println("Сотрудник " + employeeName + " не найден");
        return false;
    }

    public boolean changeDepartment(String employeeName, int newDepartmentId) {
        //находим сотрудника, вызываем его сеттер зарплаты, возвращаем true если сотрудник найден, false если не удалось найти.
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getName().equals(employeeName)){
                employees[i].setDepartment(newDepartmentId);
                System.out.println("Сотрудник " + employees[i].getName() + " переведен в " + newDepartmentId + " отдел");
                return true;
            }
        }
        System.out.println("Сотрудник " + employeeName + " не найден");
        return false;
    }

    public void printEmployeesByDepartments(){
        ArrayList<Integer> departments = new ArrayList<>(); //Для разнообразия воспользуюсь наконец готовым решением
        for (int i = 0; i < employees.length; i++) {
            if (!departments.contains(employees[i].getDepartment())){
                departments.add(employees[i].getDepartment());
            }
        }
        for (int i = 0; i < departments.size(); i++) {
            System.out.println("Сотрудники отдела " + departments.get(i) + ":");
            for (int j = 0; j < employees.length; j++) {
                if (employees[j].getDepartment()==departments.get(i)){
                    System.out.println(employees[j].getName());
                }
            }
        }
    }
}
