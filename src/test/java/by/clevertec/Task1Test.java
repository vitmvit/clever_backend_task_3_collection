package by.clevertec;

import by.clevertec.model.Animal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Task1Test {
    @Test
    void testTask1() {
        List<Animal> expectedOrigins = List.of(new Animal(614, "Snake, buttermilk", 10, "Belarusian", "Bigender"),
                new Animal(649, "European stork", 10, "Danish", "Female"),
                new Animal(712, "Flamingo, chilean", 10, "Somali", "Male"),
                new Animal(713, "Red-breasted cockatoo", 10, "Papiamento", "Female"),
                new Animal(744, "Blue-tongued lizard", 10, "Swati", "Male"),
                new Animal(775, "Wolf spider", 10, "Romanian", "Female"),
                new Animal(857, "Jackal, silver-backed", 10, "Kazakh", "Female"));

        List<Animal> actualOrigins = Main.task1();
        Assertions.assertEquals(expectedOrigins, actualOrigins);
    }
}
