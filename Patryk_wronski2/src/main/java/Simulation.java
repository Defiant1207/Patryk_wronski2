import Kreator_procesow.Kreator;

import java.io.File;
import java.io.FileNotFoundException;

public class Simulation {

    public static void main(String[] args) throws FileNotFoundException {
        File dane = new File("./../dane.txt");
        Kreator procesy = new Kreator(dane);
        procesy.load();
        procesy.sort();
        procesy.answers();

    }
}
