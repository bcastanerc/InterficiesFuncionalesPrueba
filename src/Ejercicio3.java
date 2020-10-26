import java.sql.Time;

public class Ejercicio3 {
    public static void main(String[] args) {

        // Creamos el empleado con salario
        Employee3 se = new SalariedEmployee3(1,"Tolo");
        Salary salary = new Salary(100000);
        ((SalariedEmployee3) se).setSalary(salary);

        // Cramos el empleado que cobra por horas
        Employee3 he = new HourlyEmployee3(2,"Pere");
        Timecard tc = new Timecard();
        ((HourlyEmployee3) he).setTc(tc);
        tc.setPricePerHour(1000);
        tc.setHours(40);

        // Cramos al empleado voluntario
        Employee3 e3 = new VoluntaryEmployee(4,"Pep");
        System.out.println(e3.calcPay());

        VoluntaryEmployee ve = new VoluntaryEmployee(3, "Joan");

        System.out.println(se.calcPay());
        System.out.println(he.calcPay());
        System.out.println(ve.calcPay());
    }
}

abstract class Employee3{
    private int id;
    private String name;

    Employee3(int id, String name){
        this.id = id;
        this.name = name;
    }
    public abstract int calcPay();
}

class SalariedEmployee3 extends Employee3{
    // Podemos usar la clase salary de el otro documento o podemos poner un numero fijo o un int con su setter y getter.
    private Salary salary;

    SalariedEmployee3(int id, String name) {
        super(id, name);
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public Salary getSalary() {
        return salary;
    }

    @Override
    public int calcPay() {
        return this.salary.getQuantity();
    }
}

class HourlyEmployee3 extends Employee3{
    private Timecard tc = new Timecard();

    HourlyEmployee3(int id, String name) {
        super(id, name);
    }

    @Override
    public int calcPay() {
        return this.tc.getHours() * (this.tc.getPricePerHour()/100);
    }

    public void setTc(Timecard tc) {
        this.tc = tc;
    }

    public Timecard getTc() {
        return tc;
    }
}

class VoluntaryEmployee extends Employee3{

    VoluntaryEmployee(int id, String name) {
        super(id, name);
    }

    @Override
    public int calcPay() {
        return 0;
    }
}

class Timecard{
    private int hours;
    private int pricePerHour;

    public int getHours() {
        return hours;
    }

    public int getPricePerHour() {
        return pricePerHour;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setPricePerHour(int pricePerHour) {
        this.pricePerHour = pricePerHour;
    }
}