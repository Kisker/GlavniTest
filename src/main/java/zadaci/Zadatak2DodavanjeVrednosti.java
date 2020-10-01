package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import model.Avion;
import model.Roba;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class Zadatak2DodavanjeVrednosti {
    static Dao<Avion,Integer> avionDao;
    static Dao<Roba,Integer> robaDao;

    public static void main(String[] args) {
        ConnectionSource connectionSource = null;
        try {
            // create our data-source for the database
            connectionSource = new JdbcConnectionSource(Konstante.DATABASE_URL);
            avionDao = DaoManager.createDao(connectionSource, Avion.class);
            robaDao = DaoManager.createDao(connectionSource, Roba.class);

            TableUtils.clearTable(connectionSource, Avion.class);
            TableUtils.clearTable(connectionSource, Roba.class);

            Avion as1=new Avion("Avion 1",34);
            avionDao.create(as1);

            Avion as2=new Avion("Avion 1",21);
            avionDao.create(as2);

            Roba r1=new Roba("patike", "duboke patike", 1);
            r1.setAvion(as1);
            robaDao.create(r1);

            Roba r2=new Roba("kosulja","na duge rukave", 0.4);
            r2.setAvion(as1);
            robaDao.create(r2);

            Roba r3=new Roba("voda","voda za pice", 1.4);
            r3.setAvion(as1);
            robaDao.create(r3);

            Roba r4=new Roba("ploce","drvene ploce", 3.4);
            r4.setAvion(as2);
            robaDao.create(r4);

            Roba r5=new Roba("stolica","plasticna stolica", 2.4);
            r5.setAvion(as2);
            robaDao.create(r5);

            List<Roba> robe=robaDao.queryForAll();
            for(Roba r:robe) {
                System.out.println("Roba = " + r);
            }

            List<Avion> avioni=avionDao.queryForAll();
            for(Avion as:avioni)
                System.out.println("Avion = " + as);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
