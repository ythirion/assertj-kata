import io.vavr.control.Try;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.assertj.vavr.api.VavrAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.ythirion.tolkien.AllCharacters;
import org.ythirion.tolkien.Character;

import static org.assertj.core.api.Assertions.*;
import static org.ythirion.tolkien.AllCharacters.*;
import static org.ythirion.tolkien.Race.*;

@ExtendWith(SoftAssertionsExtension.class)
class TolkienCharactersTest {
    @Test
    void basics() {
        // string 'The Lord of the Rings' is not null, starts with 'The', contains 'Lord' and ends with Rings
        assertThat("The Lord of the Rings")
                .isNotNull()
                .startsWith("The")
                .contains("Lord")
                .endsWith("Rings");

        // Frodo name is equal to 'Frodo'
        assertThat(frodo.getName()).isEqualTo("Frodo");

        // Frodo is not Sauron
        assertThat(frodo).isNotEqualTo(sauron);

        // Frodo age is 33
        assertThat(frodo.getAge()).isEqualTo(33);

        // Frodo is part of the fellowship of the Ring
        assertThat(frodo).isIn(fellowshipOfTheRing);

        // Frodo age is greater than 30 and is a hobbit
        assertThat(frodo).matches(p -> p.getAge() > 30 && p.getRace() == HOBBIT);

        // Frodo age is 33, add a description
        assertThat(frodo.getAge())
                .as("Frodo should not be %s", frodo.getAge())
                .isEqualTo(33);
    }

    @Test
    void soft_assertions(SoftAssertions assertions) {
        // Assert that George Martin as great authors equals Tolkien
        assertions.assertThat("George Martin")
                .as("great authors")
                .isEqualTo("JRR Tolkien");

        // Assert that 42 as universal answer is greater than 100
        assertions.assertThat(42)
                .as("universal answer")
                .isGreaterThan(100);

        // Assert that Gandalf equals Sauron
        assertions.assertThat("Gandalf").isEqualTo("Sauron");
    }

    @Test
    void exceptions() {
        // assert that calling throwSomething will throw an exception with a message containing not safe
        assertThatThrownBy(AllCharacters::throwSomething).hasMessageContaining("not safe");
    }

    @Test
    void collections() {
        // all elements must satisfy the given assertions : every hobbits have race hobbit and name is not Sauron
        assertThat(hobbits()).allSatisfy(character -> {
            assertThat(character.getRace()).isEqualTo(HOBBIT);
            assertThat(character.getName()).isNotEqualTo("Sauron");
        });

        // navigating : sam is the second hobbits
        assertThat(hobbits()).element(1).isEqualTo(sam);

        // filtering : filter race that are not HOBBIT nor MAN
        assertThat(fellowshipOfTheRing)
                .filteredOn("race", notIn(HOBBIT, MAN))
                .containsOnly(gandalf, gimli, legolas);

        // extraction : extract name, age and race, should contains data of Boromir, Sam and Legolas
        assertThat(fellowshipOfTheRing)
                .extracting("name", "age", "race")
                .contains(
                        tuple("Boromir", 37, MAN),
                        tuple("Sam", 38, HOBBIT),
                        tuple("Legolas", 1000, ELF)
                );
    }

    @Test
    void vavr() {
        Try<Character> sauron = find(c -> c.getName().equals("Sauron"));
        // assert you retrieve Sauron successfully
        VavrAssertions.assertThat(sauron).isSuccess();
    }
}
