public class faccade {
    public static void main(String[] args) {
    InstrumentMaker im =new InstrumentMaker();
    im.playViolin();
    }
}

class InstrumentMaker{
    private Violin  violin = new Violin();
    private Drums  drums = new Drums();
    private Guitar  guitar = new Guitar();

    void playViolin(){
        this.violin.play();
    }
    void playDrums(){
        this.drums.play();
    }
    void playGuitar(){
        this.guitar.play();
    }
}

interface Instrument{
    void play();
}

class Violin implements Instrument{

    @Override
    public void play() {
        System.out.println("Playing violin");
    }
}

class Drums implements Instrument{

    @Override
    public void play() {
        System.out.println("Playing violin");
    }
}

class Guitar implements Instrument{

    @Override
    public void play() {
        System.out.println("Playing violin");
    }
}