package model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

public class Avion {


        //Staticki atributi
        public static final String POLJE_OZNAKA="oznaka";
        public static final String POLJE_RASPON_KRILA="raspon_krila";

        //atributi za upisu u bazu
        @DatabaseField(generatedId = true)//primarni kljuc koji se automatski generise
        private int id;
        @DatabaseField(columnName = POLJE_OZNAKA,canBeNull = false)
        private String oznaka;
        @DatabaseField(columnName = POLJE_RASPON_KRILA,canBeNull = false)
        private int rasponKrila;

        //Atribut za vise kraj veze izmendju klasa Roba i Avion
        @ForeignCollectionField(foreignFieldName = "avion")
        private ForeignCollection<Roba> roba;


        public Avion() {
            //Obavezan za potrebe ORMLite-a
        }

        public Avion(String oznaka, int rasponKrila) {
            this.oznaka = oznaka;
            this.rasponKrila = rasponKrila;
        }

        // get i set metode
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOznaka() {
            return oznaka;
        }

        public void setOznaka(String oznaka) {
            this.oznaka = oznaka;
        }

        public int getRasponKrila() {
            return rasponKrila;
        }

        public void setRasponKrila(int rasponKrila) {
            this.rasponKrila = rasponKrila;
        }

        public ForeignCollection<Roba> getRoba() {
            return roba;
        }

        public void setRoba(ForeignCollection<Roba> roba) {
            this.roba = roba;
        }

        //Redefinisana metoda toString
        @Override
        public String toString() {
            return "Avion{" +
                    "id=" + id +
                    ", oznaka='" + oznaka + '\'' +
                    ", rasponKrila=" + rasponKrila +
                    '}';
        }
    }


