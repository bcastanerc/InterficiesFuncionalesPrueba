import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class personaPrueba {
    public static void main(String[] args) {
        PersonaDAO dao = new PersonaDAOImplTest();
        dao.delete((new Persona(1, "Bill")));
    }
}

interface PersonaDAO{
    List<Persona> all();
    void update(Persona p);
    void delete (Persona p);
    void create(Persona p);
}

class PersonaDAOImplTest implements PersonaDAO{

    private List<Persona> list = new ArrayList<>();

    PersonaDAOImplTest(){
        list.add(new Persona(1, "Bill"));
        list.add(new Persona(2, "Marc"));
        list.add(new Persona(3, "Pere"));
        list.add(new Persona(4, "Dani"));
    }

    @Override
    public List<Persona> all() {
        List<Persona> resultat = new ArrayList<>();
        for (Persona p : this.list){
            resultat.add(new Persona((p.getId()),p.getName()));
        }
        return resultat;
    }

    @Override
    public void update(Persona p) {
        Iterator <Persona> it = list.iterator();
        while(it.hasNext()){
            Persona pe = it.next();
            if (pe.getId() == p.getId()){
                pe.setId(p.getId());
                pe.setName(p.getName());
            }
        }
    }

    @Override
    public void delete(Persona p) {
        list.removeIf(pe -> pe.getId() == p.getId());
    }

    @Override
    public void create(Persona p) {
        this.list.add(p);
    }
}

class Persona{

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    Persona(int id, String name){
        this.id = id;
        this.name = name;
    }
}
