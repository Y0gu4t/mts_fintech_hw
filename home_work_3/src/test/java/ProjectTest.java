import agents.Animal;
import agents.Bear;
import agents.Cat;
import agents.Fox;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tools.SearchService;
import tools.SearchServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProjectTest {
    @Nested
    public class AnimalTest {
        private Animal bear;

        public AnimalTest() {
            bear = new Bear("breed#1", "name#1", BigDecimal.valueOf(1.0), "character#1", LocalDate.of(2023, 1, 1));
        }

        @Test
        @DisplayName("Animal and clone equality test")
        public void testAnimalCloneEqualsMethod() {
            Animal cloneBear = bear;
            assertEquals(cloneBear, bear, "Bear clone is not equal to bear");
        }

        @Test
        @DisplayName("Similar animals equality test")
        public void testSimilarAnimalsEqualsMethod() {
            Bear similarAnimal = new Bear("breed#1", "name#1", BigDecimal.valueOf(2.0), "character#1", LocalDate.of(2022, 2, 2));
            assertEquals(similarAnimal, bear, "Similar bears are equal");
        }

        @Test
        @DisplayName("Not Similar animals equality test")
        public void testNotSimilarAnimalsEqualsMethod() {
            Bear notSimilarBear = new Bear("breed#1", "name#2", BigDecimal.valueOf(2.0), "character#1", LocalDate.of(2022, 2, 2));
            assertNotEquals(notSimilarBear, bear, "Not Similar bears are not equal");
        }

        @Test
        @DisplayName("Other animals equality test")
        public void testOtherAnimalEqualsMethod() {
            Bear bear1 = new Bear("breed#1", "name#1", BigDecimal.valueOf(1.0), "character#1", LocalDate.of(2023, 1, 1));
            Cat cat1 = new Cat("breed#1", "name#1", BigDecimal.valueOf(1.0), "character#1", LocalDate.of(2023, 1, 1));
            assertNotEquals(bear1, cat1, "Bear and other animal are equal");
        }

    }

    @Nested
    public class SearchServiceTest {
        private SearchService searchService;

        public SearchServiceTest() {
            searchService = new SearchServiceImpl();
        }

        @Test
        @DisplayName("Leap year % 100 != 0 test")
        public void testLeapYearNotEqual100() {
            Animal[] animals = new Animal[1];
            animals[0] = new Bear("breed#1", "name#1", BigDecimal.valueOf(1.0), "character#1", LocalDate.of(4, 1, 1));
            String[] names = searchService.findLeapYearNames(animals);
            assertEquals(names.length, 1, "Incorrect array of names length");
            assertEquals(names[0], animals[0].getName(), "Incorrect animal's name");
        }

        @Test
        @DisplayName("Leap year % 100 == 0 test")
        public void testLeapYearEqual100() {
            Animal[] animals = new Animal[1];
            animals[0] = new Bear("breed#1", "name#1", BigDecimal.valueOf(1.0), "character#1", LocalDate.of(100, 1, 1));
            String[] names = searchService.findLeapYearNames(animals);
            assertEquals(names.length, 0, "Incorrect array of names length");
        }

        @Test
        @DisplayName("Leap year % 400 == 0 test")
        public void testLeapYearEqual400() {
            Animal[] animals = new Animal[1];
            animals[0] = new Bear("breed#1", "name#1", BigDecimal.valueOf(1.0), "character#1", LocalDate.of(400, 1, 1));
            String[] names = searchService.findLeapYearNames(animals);
            assertEquals(names.length, 1, "Incorrect array of names length");
            assertEquals(names[0], animals[0].getName(), "Incorrect animal's name");
        }

        @Test
        @DisplayName("Leap year % 400 != 0 test")
        public void testLeapYearNotEqual400() {
            Animal[] animals = new Animal[1];
            animals[0] = new Bear("breed#1", "name#1", BigDecimal.valueOf(1.0), "character#1", LocalDate.of(300, 1, 1));
            String[] names = searchService.findLeapYearNames(animals);
            assertEquals(names.length, 0, "Incorrect array of names length");
        }

        @Test
        @DisplayName("Leap and not leap years test")
        public void testLeapAndNotLeapYears() {
            Animal[] animals = new Animal[2];
            animals[0] = new Bear("breed#1", "name#1", BigDecimal.valueOf(1.0), "character#1", LocalDate.of(1, 1, 1));
            animals[1] = new Fox("breed#1", "name#2", BigDecimal.valueOf(1.0), "character#1", LocalDate.of(4, 1, 1));
            String[] names = searchService.findLeapYearNames(animals);
            assertEquals(names.length, 1, "Incorrect array of names length");
            assertEquals(names[0], animals[1].getName(), "Incorrect animal's name");
        }

        @Test
        @DisplayName("Two leap years test")
        public void testTwoLeapYears() {
            Animal[] animals = new Animal[2];
            animals[0] = new Bear("breed#1", "name#1", BigDecimal.valueOf(1.0), "character#1", LocalDate.of(4, 1, 1));
            animals[1] = new Fox("breed#1", "name#2", BigDecimal.valueOf(1.0), "character#1", LocalDate.of(4, 1, 1));
            String[] names = searchService.findLeapYearNames(animals);
            assertEquals(names.length, 2, "Incorrect array of names length");
        }

        @ParameterizedTest(name = "Try to test with year {arguments}")
        @ValueSource(ints = {1, 2, 5})
        @DisplayName("Correct identification of old animals test")
        public void testFindOlderAnimalsMethod(int year) {
            Animal[] animals = new Animal[2];
            animals[0] = new Bear("breed#1", "name#1", BigDecimal.valueOf(1.0), "character#1", LocalDate.of(2019, 1, 1));
            animals[1] = new Fox("breed#1", "name#2", BigDecimal.valueOf(1.0), "character#1", LocalDate.of(2024, 1, 1));
            Animal[] olderAnimals = searchService.findOlderAnimal(animals, year);
            assertEquals(olderAnimals.length, 1, "Incorrect array of animals length");
            assertEquals(animals[0], olderAnimals[0], "Incorrect animal");
        }

        @Test
        @DisplayName("Bear and Fox duplicate test")
        public void testNotDuplicateOtherAnimals() {
            Animal[] animals = new Animal[2];
            Animal fox = new Fox("breed#1", "name#1", BigDecimal.valueOf(1.0), "character#1", LocalDate.of(2024, 1, 1));
            Animal bear = new Bear("breed#1", "name#1", BigDecimal.valueOf(1.0), "character#1", LocalDate.of(2023, 1, 1));
            animals[0] = bear;
            animals[1] = fox;
            List<Animal> duplicatesAnimals = searchService.findDuplicate(animals);
            assertEquals(duplicatesAnimals.size(), 0, "Incorrect array of duplicate animals size");
        }

        @ParameterizedTest(name = "Try with {arguments} duplicates")
        @ValueSource(ints = {2, 3, 4})
        @DisplayName("Similar animals duplicate test")
        public void testTwoDuplicateOtherAnimals(int value) {
            Animal[] animals = new Animal[value];
            Animal bear = new Bear("breed#1", "name#1", BigDecimal.valueOf(1.0), "character#1", LocalDate.of(2023, 1, 1));
            Arrays.fill(animals, bear);
            List<Animal> duplicatesAnimals = searchService.findDuplicate(animals);
            assertEquals(duplicatesAnimals.size(), value * (value - 1) / 2, "Incorrect array of duplicate animals size");
        }
    }
}
