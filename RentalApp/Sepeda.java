public class Sepeda extends Kendaraan {
    private String jenisSepeda;

    public Sepeda(String merk, String model, int tahunProduksi, String jenisSepeda) {
        super(merk, model, tahunProduksi);
        this.jenisSepeda = jenisSepeda;
    }

    @Override
    public void tampilInfo() {
        super.tampilInfo();
        System.out.println("Jenis Sepeda   : " + jenisSepeda);
        System.out.println("----------------------------");
    }
}
