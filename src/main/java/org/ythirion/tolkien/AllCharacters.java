package org.ythirion.tolkien;

import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Try;
import lombok.experimental.UtilityClass;

import java.util.function.Predicate;

import static org.ythirion.tolkien.Race.*;

@UtilityClass
public class AllCharacters {
    public static final Character frodo = new Character("Frodo", 33, HOBBIT);
    public static final Character sam = new Character("Sam", 38, HOBBIT);
    public static final Character pippin = new Character("Pippin", 28, HOBBIT);
    public static final Character merry = new Character("Merin", 36, HOBBIT);
    public static final Character boromir = new Character("Boromir", 37, MAN);
    public static final Character aragorn = new Character("Aragorn", 37, MAN);
    public static final Character legolas = new Character("Legolas", 1000, ELF);
    public static final Character elrond = new Character("Elrond", 10000, ELF);
    public static final Character sauron = new Character("Sauron", 1000, ELF);
    public static final Character gandalf = new Character("Gandalf", 10000, MAIA);
    public static final Character gimli = new Character("Gimly", 10000, DWARF);

    public static final Seq<Character> fellowshipOfTheRing = List.of(frodo, sam, pippin, merry, boromir, aragorn, legolas, gandalf, gimli);

    public static Try<Character> find(Predicate<Character> filter) {
        return Try.of(() -> fellowshipOfTheRing
                .filter(filter)
                .singleOption()
                .getOrElseThrow(IllegalStateException::new));
    }

    public static Seq<Character> hobbits() {
        return fellowshipOfTheRing.filter(c -> c.getRace().equals(HOBBIT));
    }

    public static void throwSomething() throws Exception {
        throw new Exception("It's not safe to call me");
    }
}
