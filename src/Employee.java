public class Employee {
   private static int idCounter;
    private String name;
    private int department;
    private int salary;
    private int id;

    public Employee(String name, int department, int salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
        id = ++idCounter;
    }

    @Override
    public String toString() {
        return "ФИО: "+ name +  ", ID=" + id +", Отдел: " + department +
                ", Зарплата: " + salary;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public String getName() {
        return name;
    }

    public int getDepartment() {
        return department;
    }
    //
    public int getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }
}
