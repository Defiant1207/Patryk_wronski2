package Procesy;

public interface IProcesy {
    /**
     *
     * @return czas przetwarzania procesu
     */
    public double getP();

    /**
     *
     * @return czas przyjscia procesu
     */
    public double getA();

    public double getPrio();

    public void increasePrio(double x);
}
