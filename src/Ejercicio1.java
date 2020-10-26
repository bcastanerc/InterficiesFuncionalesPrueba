import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio1 {
    public static void main(String[] args) {

        // Cramos Salarios
        Salary salary = new Salary(300000);
        Salary salary2 = new Salary(200000);
        Salary salary3 = new Salary(100000);

        // Creamos empleados
        Employee employee = new Employee(1, "Pere", salary);
        Employee employee2 = new Employee(2, "Joan", salary2);
        Employee employee3 = new Employee(3, "Tolo", salary3);

        // Creamos el Disk y añadimos a los empleados
        Disk disk = new Disk();
        disk.writeToDisk(employee);
        disk.writeToDisk(employee2);
        disk.writeToDisk(employee3);

        // Calculamos el % de impuestos de cada sueldo.
        for (Employee em : disk.getEmployees()){
            em.getSalary().calculateTaxes(em.getSalary());
        }

        // Reports de Cada Empleado y cada tipo
        for (Employee em : disk.getEmployees()){
            EmployeeReport er = new EmployeeReport(em);
            er.displayReport();

            PayRollReport pr = new PayRollReport(em);
            pr.displayReport();

            TaxReport tr = new TaxReport(em);
            tr.displayReport();
            System.out.println();
        }

        // Busca al empleado con id 1
        System.out.println(disk.readFromDisk(1));

        // Creamos el XML con todos los Empleados y sus atributos, despues lo parseamos.
        XML xml = new XML();
        xml.parseXML(xml.createXML(disk));
        System.out.println(xml.getParsedXML());
    }
}

class Employee{
    private int id;
    private String name;
    private Salary salary;

    Employee(int id, String name, Salary salary){
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // Getter
    public String getName() {
        return name;
    }
    public Salary getSalary() {
        return salary;
    }
    public int getId() {
        return id;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }
    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary.toString() +
                '}'+ "\n";
    }
}

class Salary{
    // Cantidad en centimos.
    private int quantity;
    private int taxPercent;

    Salary(int quantity){
        this.quantity = quantity;
    }

    // Supongo que este metodo es para calcular cuanto es el porcentaje de impuestos que se aplicara
    // al salarrio, esto dependerá de la cantidad que gana.
    public void calculateTaxes(@NotNull Salary salary){
        if (salary.quantity >150000){
            salary.setTaxPercent(20);
        }else{
            salary.setTaxPercent(10);
        }
    }

    // Calcula cuanto es la suma de dinero que tiene que pagar de impuestos, ejemplo (1000€*10%)/100 esto puede servir para calcular el sueldo neto.
    public int calculatePay(@NotNull Salary salary){
        return (salary.getTaxPercent()*salary.getQuantity())/100;
    }

    // Getters
    // Cuantity /100 ya que el sueldo esta almacenado en centimos.
    public int getQuantity() {
        return quantity /100;
    }
    public int getTaxPercent() {
        return taxPercent;
    }

    // Setters
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setTaxPercent(int taxPercent) {
        this.taxPercent = taxPercent;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "cuantity=" + quantity +
                ", taxPercent=" + taxPercent +
                '}';
    }
}

class Disk{
    private List<Employee> employees = new ArrayList<>();
    // También podriamos crear una lista de salarios y implementar los mismos metodos para ella
    // (tendriamos que añadir id al salario, esto puede ser util si tenemos salarios por secciones, Programadores con id de salario 1 cobran 20000, Medicos id2 cobran 25000)

    // Añade empleados alDisk
    public void writeToDisk(Employee employee){
        this.employees.add(employee);
    }

    // Busca a un Empleado en el Disk
    public Employee readFromDisk(int id){
        for (Employee em : employees){
            if (em.getId() == id){
                return em;
            }
        }
        return null;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public String toString() {
        StringBuilder employeesString = new StringBuilder();
        for (Employee em : employees){
            employeesString.append(em.toString());
        }
        return employeesString.toString();
    }
}

class XML{
    private String parsedXML;

    public String createXML(@NotNull Disk disk){
        return "XML{" +
                "parsedXML='\n" + disk.toString() + '\'' +
                '}';
    }

    // Técnicamente el parse deberias ser usado para comprobar que el String és un XML
    public String getParsedXML() {
        return parsedXML;
    }

    // Antes de parsearlo deberiamos comprobar si es correcto en teoria.
    public void parseXML(String parsedXML) {
        this.parsedXML = parsedXML;
    }

}

interface DisplayReports {
    abstract public void displayReport();
    abstract public String getMsg();
}

class EmployeeReport implements DisplayReports {
    private String msg;

    // Genera el mensaje para un Employee Report
    EmployeeReport(@NotNull Employee emp) {
       this.msg = emp.getName() + " gana " + (emp.getSalary().getQuantity()) + "€, sus impuestos són del " + emp.getSalary().getTaxPercent() + "%.";;
    }

    @Override
    public void displayReport() {
        System.out.println("Employee Report: " + this.msg);
    }

    @Override
    public String getMsg() {
        return msg;
    }
}

class PayRollReport implements DisplayReports {
    private String msg;

    // Genera el mensaje para un PayRoll Report
    PayRollReport(@NotNull Employee emp) {
        this.msg = emp.getName() + " gana " + (emp.getSalary().getQuantity()) + "€, su gasto en impuestos es de " + emp.getSalary().calculatePay(emp.getSalary()) + "€." ;
    }

    @Override
    public void displayReport() {
        System.out.println("Pay Roll Report: " + this.msg);
    }

    @Override
    public String getMsg() {
        return msg;
    }
}

class TaxReport implements DisplayReports {
    private String msg;

    // Genera el mensaje para un TaxReport Report
    TaxReport(Employee emp) {
        this.msg = emp.getName() + " tiene " + emp.getSalary().getTaxPercent() + "% de impuestos en el salario.";
    }

    @Override
    public void displayReport() {
        System.out.println("Tax Report: " + this.msg);
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
