package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import model.Avion;
import model.Roba;

import java.io.IOException;
import java.util.List;

public class Zadatak3IzmenaVrednosti {
    static Dao<Avion,Integer> avionDao;
    static Dao<Roba,Integer> robaDao;

    public static void main(String[] args) {
        ConnectionSource connectionSource = null;
        try {
            // create our data-source for the database
            connectionSource = new JdbcConnectionSource(Konstante.DATABASE_URL);
            avionDao = DaoManager.createDao(connectionSource, Avion.class);
            robaDao = DaoManager.createDao(connectionSource, Roba.class);

            List<Roba> robe=robaDao.queryForAll();
            for(Roba r:robe) {
                System.out.println("Roba = " + r);
            }

            List<Roba> pronadjenaRoba=robaDao.queryForEq(Roba.POLJE_OPIS,"plasticna stolica");
            Roba robaZaIzmenu=pronadjenaRoba.get(0);//Preuzimamo prvi pronadjeni
            //Menjamo vrednost atributa opis na Drvena stolica
            robaZaIzmenu.setOpis("Drvena stolica");
            //Cuvamo izmene u bazi, menja se vrednost kolone opis
            robaDao.update(robaZaIzmenu);

            /*Prikaz vrednosti tabele Roba
               da potvrdimo da je vrednost izmenjena
             */
            robe=robaDao.queryForAll();
            for(Roba r:robe)
                System.out.println("Roba = " + r);

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (connectionSource != null) {
                try {
                    connectionSource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

