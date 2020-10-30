package com.sparta.engineering72.Animal.Fox;

import com.sparta.engineering72.Animal.Animal;
import com.sparta.engineering72.Settings.Settings;
import com.sparta.engineering72.Utility.Randomizer;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FemaleFox extends Fox{
    public static double PREGNANCY_CHANCE = 0.5d;
    private boolean isPregnant;
    private BigInteger count;

    public FemaleFox() {
        super(Animal.Gender.FEMALE);
        age = 0;
        isPregnant = false;
        count = BigInteger.valueOf(1);
    }

    public boolean isPregnant(){
        return isPregnant;
    }

    public static double getPregnancyChance() {
        return PREGNANCY_CHANCE;
    }

    public static void setPregnancyChance(double pregnancyChance) {
        PREGNANCY_CHANCE = pregnancyChance;
    }

    public void getPregnant(){
        isPregnant = true;
    }

    public BigInteger getCount() {
        return count;
    }

    public void setCount(BigInteger count) {
        this.count = count;
    }

    public static List<Animal> breedFoxes(BigInteger count){
        final BigInteger averageOffspringCount = BigInteger.valueOf(5);

        List<Animal> animals = new ArrayList<>();

        MaleFox malefox = new MaleFox();
        FemaleFox femalefox = new FemaleFox();

        BigInteger[] randomGenders;

        if (count.compareTo(Settings.MAX_COUNT_THRESHOLD) > 0){
            BigInteger totalOffspring = BigInteger.valueOf(count.multiply(averageOffspringCount));
            malefox.setCount(totalOffspring.divide(BigInteger.valueOf(2)));
            femalefox.setCount(totalOffspring.divide(BigInteger.valueOf(2)));
        } else {
            BigInteger countMaleOffspring = BigInteger.valueOf(0);
            BigInteger countFemaleOffspring = BigInteger.valueOf(0);

            BigInteger totalOffspring = BigInteger.valueOf(0);

            BigInteger[] childrenArray = Randomizer.getRandomFoxOffspring(count);

            for (BigInteger child : childrenArray) {
                totalOffspring = totalOffspring.add(child);
            }

            randomGenders = Randomizer.getRandomGender(totalOffspring);

            for(int j = 0; j < totalOffspring.intValue(); j++) {
                if (randomGenders[j].compareTo(BigInteger.valueOf(1)) == 0) {
                    countMaleOffspring = countMaleOffspring.add(BigInteger.ONE);
                } else {
                    countFemaleOffspring = countFemaleOffspring.add(BigInteger.ONE);
                }
            }

            malefox.setCount(countMaleOffspring);
            femalefox.setCount(countFemaleOffspring);
        }
        animals.add(malefox);
        animals.add(femalefox);
        return animals;
    }

}
