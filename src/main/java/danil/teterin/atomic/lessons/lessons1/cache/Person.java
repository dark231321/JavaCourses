package danil.teterin.atomic.lessons.lessons1.cache;

import java.util.Objects;

public class Person {
    private String firstName;
    private String secondName;

    public Person(String firstName,
                  String secondName){
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getSecondName() {
        return this.secondName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) &&
                Objects.equals(secondName, person.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName);
    }
}
