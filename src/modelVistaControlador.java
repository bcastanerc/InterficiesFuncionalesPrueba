public class modelVistaControlador {
    // MVC --> Model Vista Controlador. Me permet separar lo que es com l'usuari reb sa pag. web de com se genera
    public static void main(String[] args) {
        Student tom = new Student();
        tom.setName("Tom");
        tom.setCity("New York");
        StudentView view = new StudentView();
        StudentController controller = new StudentController(tom, view);
        controller.updateView();
    }
}

class StudentView {
    void printStudentDetails(String name, String city) {
        System.out.printf("Name: %s city %s\n", name, city);
    }
}

class StudentController {
    private Student model;
    private StudentView view;

    StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }

    void updateView() {
        view.printStudentDetails(model.getName(), model.getCity());
    }
}

class Student {
    private String name;
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
