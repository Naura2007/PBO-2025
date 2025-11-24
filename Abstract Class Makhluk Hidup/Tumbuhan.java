public class Tumbuhan extends MakhlukHidup {
    private String Akar;

    public Tumbuhan(String Akar) {
        this.Akar = Akar;
    }

    @Override
    public void berdiri() {
        System.out.println("Tumbuhan berdiri dengan     :  " + Akar);
    }
}
