import agents.Animal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import tools.AnimalRepository;

@ComponentScan("tools")
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        AnimalRepository animalRepository = context.getBean(AnimalRepository.class);

        animalRepository.printDuplicate();
        System.out.println("\nЖивотные, которые родились в високостный год:\n");
        for (String name :
                animalRepository.findLeapYearNames()) {
            System.out.println(name);
        }
        System.out.println("\nЖивотные, которые старше 5 лет\n");
        for (Animal animal :
                animalRepository.findOlderAnimal(5)) {
            System.out.println(animal);
        }
    }
}
