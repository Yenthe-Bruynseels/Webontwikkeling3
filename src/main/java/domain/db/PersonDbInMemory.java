package domain.db;

import domain.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonDbInMemory {
    private Map<String, Person> persons = new HashMap<>();

    public PersonDbInMemory() {
        Person administrator = new Person("admin", "admin@ucll.be", "t", "Ad", "Ministrator");
        Person yenthe = new Person("yenthe", "yenthe@thibault.islove", "jazekers", "Yen", "The");
        Person thibault = new Person("thibault", "thibault@single.forever", "z&g=/=nietokay", "Thi", "Bault");
        add(administrator);
        add(yenthe);
        add(thibault);
    }

    public Person get(String personId) {
        if (personId == null) {
            throw new DbException("No id given");
        }
        return persons.get(personId);
    }

    public List<Person> getAll() {
        return new ArrayList<Person>(persons.values());
    }

    public void add(Person person) {
        if (person == null) {
            throw new DbException("No person given");
        }
        if (persons.containsKey(person.getUserid())) {
            throw new DbException("User already exists");
        }
        persons.put(person.getUserid(), person);
    }

    public void update(Person person) {
        if (person == null) {
            throw new DbException("No person given");
        }
        if (!persons.containsKey(person.getUserid())) {
            throw new DbException("No person found");
        }
        persons.put(person.getUserid(), person);
    }

    public void delete(String personId) {
        if (personId == null) {
            throw new DbException("No id given");
        }
        persons.remove(personId);
    }

    public int getNumberOfPersons() {
        return persons.size();
    }

    public Person getPersonIfAuthenticated(String personId, String password) {
        Person current = get(personId);
        if (current.isCorrectPassword(password)) {
            return current;
        }
        return null;
    }
}
