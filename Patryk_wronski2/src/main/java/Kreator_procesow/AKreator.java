package Kreator_procesow;

import Kolejka.IKolejka;
import Kolejka.Kolejka;
import Procesy.IProcesy;
import Procesy.Procesy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public abstract class AKreator implements IKreator {

    double a[];
    double P[];
    double Prio[];
    List<IProcesy> procesy;
    Scanner scanner;
    int kwant_czasu;
    IKolejka q1;

    public AKreator(File dane) throws FileNotFoundException {
        this.scanner = new Scanner(dane);
        this.P = new double[15];
        this.a = new double[15];
        this.Prio = new double[15];
        this.procesy = new LinkedList<>();

    }

    @Override
    public void load() {
        while (scanner.hasNext()) {

            for (int j = 0; j < 15; j++) {
                a[j] = scanner.nextInt();
            }
            for (int j = 0; j < 15; j++) {
                P[j] = scanner.nextInt();
            }
            for (int j = 0; j < 15; j++) {
                Prio[j] = scanner.nextInt();
            }
            kwant_czasu = scanner.nextInt();
            q1 = new Kolejka(15,kwant_czasu);

            for (int j = 0; j < 15; j++) {
                IProcesy proces = new Procesy(a[j], P[j], q1, Prio[j]);
                procesy.add(proces);
            }
        }
    }
    @Override
    public void sort() {
            for (int i = 0; i < 15; i++) {
                while (!q1.settleProcess(procesy.get(i), i)) ;
            }
            q1.sort();
            q1.fn();

    }

    @Override
    public void answers() {

            double[] tablica = q1.waitingTime();
            System.out.println( "sredni czas oczekiwania procesu = " + tablica[0]/15);
            System.out.println( "sredni czas przetwarzania procesu = " + tablica[1]/15);

    }


}

