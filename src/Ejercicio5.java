import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio5 {
    public static void main(String[] args) {

        // Creamos Registro y usuarios
        CLientsRegistry cR= new CLientsRegistry();
        Client c1 = new Client(1,"Tolo Castañer", 100000);
        Client c2 = new Client(2,"Joan Galmés", 200000);
        Client c3 = new Client(3,"Pere Negre", 300000);

        // Introducimos a los usuarios en el registro
        cR.addClient(c1);
        cR.addClient(c2);
        cR.addClient(c3);

        // Eliminamos un usuario
        cR.deleteClient(c2);

        // Creamos el mensaje en español
        MessageLanguage sm = new SpanishMessage();
        // Hacemos una transferencia de c1 a c2 por valor de 555 y le agregamos el registro de la trasnferencia
        cR.addRegistry(c1,sm.transferMoney(c1,555,c3));
        cR.addRegistry(c1,sm.transferMoney(c1,1111,c3));

        // Hacemos print del registro de Tolo
        System.out.println(c1.printRegistry());

        System.out.println(c1.getMoney());
    }
}
interface MessageLanguage{
    String transferMoney(Client c1, int money, Client c2);
    String takeMoneyOut(Client c, int money);
    String depositMoney(Client c, int money);
}

class CatalanMessage implements MessageLanguage{

    @Override
    public String transferMoney(Client c1, int money, Client c2) {
        if(c1.getMoney() >= money){
            c1.setMoney(c1.getMoney()-money);
            c1.setMoney(c2.getMoney()+money);
            return c1.getFullName() + "con ID:" + c1.getId() + " ha transferido " + money + " a " + c2.getFullName() + "con ID:" +c2.getId();
        }
        return "No se ha podido realizar la transferencia de " + c1.getFullName() + "con ID:" + c1.getId() + " con valor de" + money + " a " + c2.getFullName() + "con ID:" +c2.getId();
    }

    @Override
    public String takeMoneyOut(Client c, int money) {
        return null;
    }

    @Override
    public String depositMoney(Client c, int money) {
        return null;
    }
}

class SpanishMessage implements MessageLanguage{

    @Override
    public String transferMoney(Client c1, int money, Client c2) {
        if(c1.getMoney() >= money){
            c1.setMoney(c1.getMoney()-money);
            c2.setMoney(c2.getMoney()+money);
            return c1.getFullName() + " con ID:" + c1.getId() + " ha transferido " + money + " a " + c2.getFullName() + " con ID:" +c2.getId();
        }
        return "No se ha podido realizar la transferencia de " + c1.getFullName() + " con ID:" + c1.getId() + " con valor de " + money + " a " + c2.getFullName() + " con ID:" +c2.getId();
    }

    @Override
    public String takeMoneyOut(Client c, int money) {
        return null;
    }

    @Override
    public String depositMoney(Client c, int money) {
        return null;
    }
}

class EnglishMessage implements MessageLanguage{

    @Override
    public String transferMoney(Client c1, int money, Client c2) {
        return null;
    }

    @Override
    public String takeMoneyOut(Client c, int money) {
        return null;
    }

    @Override
    public String depositMoney(Client c, int money) {
        return null;
    }
}



interface InDevice{
    void write();
}

interface OutDevice{
    void display();
}

class Keyboard implements InDevice{

    @Override
    public void write() {
        System.out.println("Keyboard input");
    }
}
class TouchScreenIn implements InDevice{

    @Override
    public void write() {
        System.out.println("TouchScreen input");
    }
}
class CardReader implements InDevice{

    @Override
    public void write() {
        System.out.println("CardReader input");
    }
}
class PassBook implements InDevice{

    @Override
    public void write() {
        System.out.println("PassBook input");
    }
}


class TouchScreenOut implements OutDevice{

    @Override
    public void display() {
        System.out.println("TouchScreen Display");
    }
}
class Screen implements OutDevice{

    @Override
    public void display() {
        System.out.println("Screen Display");
    }
}

class Client{
    private int id;
    private String fullName;
    private int money;
    private List<String> registry = new ArrayList<>();

    Client (int id, String fullName, int money){
        this.id = id;
        this.fullName = fullName;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public int getMoney() {
        return money;
    }

    public List<String> getRegistry() {
        return registry;
    }

    public String printRegistry() {
        StringBuilder actualRegistry = new StringBuilder("Registry:\n");
        for (String s : registry){
            actualRegistry.append(s).append("\n");
        }
        return actualRegistry.toString();
    }

    public String getFullName() {
        return fullName;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", money=" + money +
                '}';
    }
}

class CLientsRegistry {
    private List<Client> clients = new ArrayList<>();

    void addClient(Client client){
        clients.add(client);
    }

    void deleteClient(Client c){
        // Elimina el cliente de la lista.
        this.clients = clients
                .stream()
                .filter((client) -> client.getId() != c.getId())
                .collect(Collectors.toList());
    }

    public void addRegistry(Client c, String newRegistry){
        c.getRegistry().add(newRegistry);
    }

    @Override
    public String toString() {
        return "CLientsRegistry{" +
                "clients=" + clients +
                '}';
    }
}