package Kolejka;

import Procesy.IProcesy;

public interface IKolejka {
    /**
     *
     * @param x - pozycja w tabeli
     * @return czas przyjscia procesu
     */
    double getProcessA(int x);
    /**
     *
     * @param x - pozycja w tabeli
     * @return czas przetwarzania procesu
     */
    double getProcessP(int x);

    /**
     * Sortuje procesy zaczynajac od tych, ktore najwczesniej przyszly
     */
    void sort();

    int getProcessPosition(IProcesy process);

    /**
     * umieszcza proces na podanej pozycji
     * @param process
     * @param x - pozycja na ktorej chcemy umiescic proces
     * @return prawda- jezeli mozna umiescic proces na danej pozycji, falsz- nie mozna
     */
    boolean settleProcess(IProcesy process, int x);

    void prioSort(double t, int x);

    int fn2();

    /**
     * zlicza czasy oczekiwania i przetwarzania procesow
     * @return laczny czas oczekiwania i przetwarzania procesow
     */
    double[] waitingTime();

    void fn();

    void wyswietl(int i);
}
