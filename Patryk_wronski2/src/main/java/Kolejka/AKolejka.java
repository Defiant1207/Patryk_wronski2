package Kolejka;

import Procesy.IProcesy;

import java.util.ArrayList;
import java.util.List;

public abstract class AKolejka implements IKolejka {
    public final int x;
    IProcesy[] procesy1;
    IProcesy[] procesy2;
    List<IProcesy> kolejka;
    int kwant_czasu;

    public AKolejka(int x, int kwant_czasu) {

        this.x = x;
        procesy1 = new IProcesy[x];
        procesy2 = new IProcesy[x];
        kolejka = new ArrayList<>();
        this.kwant_czasu = kwant_czasu;

    }

    @Override
    public double getProcessA(int x) {
        return procesy1[x].getA();
    }

    @Override
    public double getProcessP(int x) {

        return procesy1[x].getP();
    }

    @Override
    public int getProcessPosition(IProcesy process) {
        Integer processX = null;
        for (int i = 0; i < procesy1.length; i++) {
            if (process == procesy1[i]) {
                processX = i;
            }

        }
        if (processX == null) {
            return -1;
        } else {
            return processX.intValue();
        }
    }

    @Override
    public boolean settleProcess(IProcesy process, int x) {

        int settled = getProcessPosition(process);

        if (procesy1[x] != null) {
            return false;
        }
        if (settled >= 0) {
            procesy1[settled] = null;
        }
        procesy1[x] = process;
        return true;

    }

    @Override
    public void sort() {
        for (int i = 0; i < procesy1.length; i++) {
            for (int j = 0; j < procesy1.length - 1; j++) {
                if (getProcessA(j + 1) < getProcessA(j)) {
                    IProcesy[] proc = new IProcesy[procesy1.length];
                    proc[j] = procesy1[j];
                    procesy1[j] = procesy1[j + 1];
                    procesy1[j + 1] = proc[j];
                } else if (getProcessA(j + 1) == getProcessA(j) && getProcessP(j + 1) < getProcessP(j)) {
                    IProcesy[] proc = new IProcesy[procesy1.length];
                    proc[j] = procesy1[j];
                    procesy1[j] = procesy1[j + 1];
                    procesy1[j + 1] = proc[j];
                }
            }
        }
    }

    @Override
    public void fn() {
        double t = 0;
        for (int i = 0; i < procesy1.length; i++) {
            prioSort(t, i);
            int prio = fn2();
            procesy2[i] = kolejka.get(prio);
            kolejka.remove(prio);
            if (procesy2[i].getA() < t) {
                t += procesy2[i].getP();
            } else {
                t = procesy2[i].getA() + procesy2[i].getP();
            }
            for (int j = 0; j < kolejka.size(); j++) {
                if (kolejka.get(j).getP() > kwant_czasu) {
                    kolejka.get(j).increasePrio((1));
                }
            }
        }
    }

    @Override
    public void prioSort(double t, int x) {
        for (int i = x; i < procesy1.length; i++) {
            if (procesy1[i] != null) {
                if (procesy1[i].getA() <= t) {
                    kolejka.add(procesy1[i]);
                    procesy1[i] = null;
                }

            }
        }
        if (procesy1[x] != null && procesy1[x].getA() > t) {
            kolejka.add(procesy1[x]);
            procesy1[x] = null;
        }
    }

    @Override
    public int fn2() {
        double max = kolejka.get(0).getPrio();
        int position = 0;
        if (kolejka.size() > 1) {
            for (int i = 1; i < kolejka.size(); i++) {
                if (kolejka.get(i).getPrio() > max) {
                    max = kolejka.get(i).getPrio();
                    position = i;
                }
            }
        }
        return position;
    }


    @Override
    public double[] waitingTime() {
        double a = 0;
        double P = procesy2[0].getP();
        double t = procesy2[0].getA() + procesy2[0].getP();
        for (int i = 1; i < procesy2.length; i++) {
            double a2;
            a2 = procesy2[i].getA();
            double P2;
            P2 = procesy2[i].getP();
            if (t >= a2) {
                a += t - a2;
                P = P + P2 + t - a2;
                t += P2;


            } else if (t < a2) {
                t = a2 + P2;
                P += P2;
            }
        }
        double tab[] = new double[2];
        tab[0] = a;
        tab[1] = P;
        return tab;
    }
}
