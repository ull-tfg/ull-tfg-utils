package es.ull.utils.random;

import java.util.Random;

public class UllRandom {
    
    public static int random(int rangeMin, int rangeMax) {
        return new Random().nextInt((rangeMax - rangeMin)) + rangeMin;
    }
}
