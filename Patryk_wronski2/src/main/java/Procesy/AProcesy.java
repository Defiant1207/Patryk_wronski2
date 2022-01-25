package Procesy;

import Kolejka.IKolejka;

public abstract class AProcesy implements IProcesy {
    protected final double czas_przybycia;
    protected final double czas_przetwarzania;
    protected IKolejka kolejka;
    protected double prio;

    public AProcesy(double czas_przybycia, double czas_przetwarzania, IKolejka kolejka, double prio) {
        this.czas_przybycia = czas_przybycia;
        this.czas_przetwarzania = czas_przetwarzania;
        this.kolejka = kolejka;
        this.prio = prio;

    }

    @Override
    public double getP(){
        return czas_przetwarzania;
    }
    @Override
    public double getA(){
        return czas_przybycia;
    }
    @Override
    public double getPrio(){return prio;}
    @Override
    public void increasePrio(double x){
        prio+=x;
    }
}
