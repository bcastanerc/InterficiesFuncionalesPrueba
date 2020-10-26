import java.util.ArrayList;
import java.util.List;

public class filterOrInterceptor {
    public static void main(String[] args) {
        // La idea es tener filtros antes de que el controlador actue,
        // cuando llega una peticion por el front controller la peticion pasara por estos filtros antes de llegar al frontController,
        // por ejemplo autentificacción...

        FilterManager fm = new FilterManager(new Target());
        fm.setFilter(new AuthenticationFilter());
        fm.setFilter(new DebugFilter());
        fm.setFilter(new LogFilter());

        Clien client = new Clien(fm);
        client.sendRequest("HOME");
    }
}

interface Filter {
    void execute(String request);
}

class AuthenticationFilter implements Filter{

    @Override
    public void execute(String request) {
        System.out.println("Authenticating user: " + request);
    }
}

class DebugFilter implements Filter {

    @Override
    public void execute(String request) {
        System.out.println("Debubgging: " + request);
    }
}

class LogFilter implements Filter {

    @Override
    public void execute(String request) {
        System.out.println("LogFilter: " + request);
    }
}

class Target {
    void run(String request){
        System.out.println("Running: " + request);
    }
}

class FilterChain{
    private List<Filter> filters = new ArrayList<>();
    private Target target;

    void addFilter(Filter f){
        filters.add(f);
    }

    void setTarget(Target t){
        this.target = t;
    }

    void execute(String request){
        filters.stream().forEach(f -> f.execute(request));
        target.run(request);
    }
}

class FilterManager {
    FilterChain filterChain;

    FilterManager(Target t){
        filterChain = new FilterChain();
        filterChain.setTarget(t);
    }

    void setFilter(Filter f){
        filterChain.addFilter(f);
    }

    void filterRequest(String request){
        filterChain.execute(request);
    }
}

class Clien {
    FilterManager filterManager;
    Clien(FilterManager fm){
        filterManager = fm;
    }

    void sendRequest(String request){
        filterManager.filterRequest(request);
    }
}