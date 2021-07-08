import io.vavr.control.Try;
import org.junit.jupiter.api.Test;
import org.ythirion.tolkien.Character;

import static org.ythirion.tolkien.AllCharacters.find;

class TolkienCharactersTest {
    @Test
    void basics() {
        // string 'The Lord of the Rings' is not null, starts with 'The', contains 'Lord' and ends with Rings

        // Frodo name is equal to 'Frodo'

        // Frodo is not Sauron

        // Frodo age is 33

        // Frodo is part of the fellowship of the Ring

        // Frodo age is greater than 30 and is a hobbit

        // Frodo age is 33, add a description
    }

    @Test
    void soft_assertions() {
        // Assert that George Martin as great authors equals Tolkien

        // Assert that 42 as universal answer is greater than 100

        // Assert that Gandalf equals Sauron
    }

    @Test
    void collections() {
        // all elements must satisfy the given assertions : every hobbits have race hobbit and name is not Sauron

        // navigating : sam is the second hobbits

        // filtering : filter race that are not HOBBIT nor MAN


        // extraction : extract name, age and race, should contains data of Boromir, Sam and Legolas
    }

    @Test
    void vavr() {
        Try<Character> sauron = find(c -> c.getName().equals("Sauron"));
        // assert you retrieve Sauron successfully
    }
}
