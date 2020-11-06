package domain.service;

import domain.db.PersonDB;
import domain.db.PersonDBSQL;
import domain.model.Person;

import java.util.List;

public class UserService {
    private PersonDB personDb = new PersonDBSQL();

    public UserService() {
    }

    public Person getPerson(String personId) {
        return getPersonDb().get(personId);
    }

    public List<Person> getPersons() {
        return getPersonDb().getAll();
    }

    public void addPerson(Person person) {
        getPersonDb().add(person);
    }

/*    public void updatePersons(Person person) {
        getPersonDb().update(person);
    }*/

    public void deletePerson(String id) {
        getPersonDb().delete(id);
    }

    private PersonDB getPersonDb() {
        return personDb;
}

    public Person authenticate(String userid, String password) {
        return getPersonDb().getPersonIfAuthenticated(userid, password);
    }

}
