package ui.utils;

import java.util.Random;

/**
 * Created by vevinmoza on 4/1/16.
 */
public class RandomNumberGenerator {
    public static String generateNumber(){
       return new Random().nextLong()+"";
    }
}
