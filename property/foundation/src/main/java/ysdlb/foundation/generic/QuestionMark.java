package ysdlb.foundation.generic;

import ysdlb.foundation.component.Animal;
import ysdlb.foundation.component.Dog;

import java.util.Arrays;
import java.util.List;


public class QuestionMark {
    public static int countLegs(List<Animal> animals) {
        int num = 0;
        for (Animal animal: animals) {
            num += animal.getLegNum();
        }
        return num;
    }
    public static int countLegs1(List<? extends Animal> animals) {
        int num = 0;
        for (Animal animal: animals) {
            num += animal.getLegNum();
        }
        return num;
    }

    public static void main(String[] args) {
        List<Animal> animals = Arrays.asList(new Dog());
        countLegs(animals);
        countLegs1(animals);

        List<Dog> dogs = Arrays.asList(new Dog());
        // countLegs(dogs);
        countLegs1(dogs);
    }

}
