package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import model.Avion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AvionNit extends Thread {
    private Avion avion;
    static Dao<Avion, Integer> avionDao;
    public static Boolean dozvoljenoPoletanje = true;
    public static final Object sinhronizacija = new Object();

    public AvionNit(Avion avion) {
        this.avion = avion;
    }

    @Override
    public void run() {
       Random random = new Random();

        System.out.println("Pocinju provere za avion " + avion.getId() + ".");

        try {
           // sleep(2000);
            sleep(random.nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Avion " + avion.getId() + " je spreman za poletanje i ceka dozvolu za poletanje.");

        boolean proveriPonovo = true;
        do {
            synchronized (sinhronizacija) {
                if (dozvoljenoPoletanje) {
                    dozvoljenoPoletanje = false;
                    proveriPonovo = false;
                }
            }
        } while (proveriPonovo);

        System.out.println("Avion " + avion.getId() + " izlazi na pistu i polece.");

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (sinhronizacija) {
            System.out.println("Avion " + avion.getId() + " je poleteo.");
            dozvoljenoPoletanje = true;
        }
    }

    public static void main(String[] args) {
        List<Avion> sviAvioni;
        ConnectionSource connectionSource = null;
        try {
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:avionRoba.db");
            avionDao = DaoManager.createDao(connectionSource, Avion.class);
            sviAvioni = avionDao.queryForAll();

        } catch (Exception e) {
            e.printStackTrace();
            sviAvioni=new ArrayList<Avion>();
        } finally {
            if (connectionSource != null) {
                try {
                    connectionSource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        List<AvionNit> avionNiti=new ArrayList<AvionNit>();
        AvionNit avionNit;
        for(Avion avion:sviAvioni) {
            avionNit = new AvionNit(avion);
            avionNiti.add(avionNit);
        }

        for(AvionNit an:avionNiti){
            an.start();
        }

        for(AvionNit an:avionNiti){
            try {
                an.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Svi avioni su poleteli");
    }
}


