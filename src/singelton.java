public class singelton {
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        s1.setValor(1);
        s2.setValor(2);
        System.out.println(s1.getValor() + " " + s2.getValor());
    }
}
class Singleton{
    private int valor;
    private static Singleton singleton;

    private Singleton(){}
    public static Singleton getInstance(){
        if (singleton == null){
            singleton = new Singleton();
        }
        return singleton;
    }
    public int getValor(){
        return valor;
    }

    public void setValor(int valor){
        this.valor = valor;
    }
}
