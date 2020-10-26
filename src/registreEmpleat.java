public class registreEmpleat {
    public static void main(String[] args)throws Exception {
        RegisteEmpleat re = new RegisteEmpleat(1,"Bill", "New York",1_000_000);
        RegisteEmpleat re2 = (RegisteEmpleat) re.clone();
    }
}
class RegisteEmpleat implements Cloneable{
    private int id;
    private String name,adreca;
    private double salari;

    RegisteEmpleat(int id, String name, String adreca, double salari){
        this.id = id;
        this.name = name;
        this.adreca =adreca;
        this.salari = salari;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAdreca() {
        return adreca;
    }

    public double getSalari() {
        return salari;
    }

    public void setId(String name) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    public void setSalari(double salari) {
        this.salari = salari;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new RegisteEmpleat(this.id,this.name,this.adreca,this.salari);
    }

}
