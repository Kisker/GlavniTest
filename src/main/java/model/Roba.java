package model;

import com.j256.ormlite.field.DatabaseField;

public class Roba {
    //Staticki atributi
    public static final String POLJE_NAZIV="naziv";
    public static final String POLJE_OPIS="opis";
    public static final String POLJE_TEZINA="tezina";

    //atributi za upisu u bazu
    @DatabaseField(generatedId = true)//primarni kljuc koji se automatski generise
    private int id;
    @DatabaseField(columnName = POLJE_NAZIV,canBeNull = false)
    private String naziv;
    @DatabaseField(columnName = POLJE_OPIS,canBeNull = false)
    private String opis;
    @DatabaseField(columnName = POLJE_TEZINA,canBeNull = false)
    private double tezina;

    //Atribut za jedinicni kraj veze izmedju klasa Avion i Roba
    @DatabaseField(foreign = true,foreignAutoRefresh = true, canBeNull = true)
    private Avion avion;

    /**
     * Konstruktor bez parametara, potreban za ORMLite
     */
    public Roba() {
        //Obavezan za potrebe ORMLite-a
    }

    /**
     * Konstrutkor sa parametrima naziv, opis, tezina, visina, duzina i sirina
     * @param naziv naziv robe
     * @param opis opis robe
     * @param tezina tezina robe
     * @param visina visina robe
     * @param duzina duzina robe
     * @param sirina sirina robe
     */
    public Roba(String naziv, String opis, double tezina, double visina, double duzina, double sirina) {
        this.naziv = naziv;
        this.opis = opis;
        this.tezina = tezina;
//        this.visina = visina;
//        this.duzina = duzina;
//        this.sirina = sirina;
    }

    // get i set metode
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getTezina() {
        return tezina;
    }

    public void setTezina(double tezina) {
        this.tezina = tezina;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    //Redefinisana toString metoda
    @Override
    public String toString() {
        return "Roba{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", opis='" + opis + '\'' +
                ", tezina=" + tezina +
                '}';
    }
}
