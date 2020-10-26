public class frontController {
    public static void main(String[] args) {
        // Emplea dispatcher, recive peticiones y las envia donde toca.
        // Objetivo, tener un controlador que se encarga de todas las peticiones.

        FrontController fc = new FrontController();
        fc.dispatchRequest("home");
        fc.dispatchRequest("Internal");

    }
}

class HomePageView {
     void show(){
         System.out.println("Home Page");
     }
}

class InternalPageView {
    void show(){
        System.out.println("Internal Page");
    }
}

class Dispatcher {
    private HomePageView homePageView = new HomePageView();
    private InternalPageView internalPageView = new InternalPageView();

    void dispatch(String request){
        if(request.equalsIgnoreCase("Home")){
            homePageView.show();
        }
        if (request.equalsIgnoreCase("Internal")){
            internalPageView.show();
        }
    }
}

class FrontController {
    private Dispatcher dispatcher = new Dispatcher();

    void dispatchRequest(String request){
        dispatcher.dispatch(request);
    }
}