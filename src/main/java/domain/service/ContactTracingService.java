package domain.service;

import domain.db.PersonDB;
import domain.db.PersonDBSQL;
import domain.db.PersonDbInMemory;
import domain.model.Person;

import java.util.List;

public class ContactTracingService {
    private PersonDB personDb = new PersonDBSQL();
    //private PersonDbInMemory personDb = new PersonDbInMemory();

    public ContactTracingService() {
    }

/*    public Person getPerson(String personId) {
        return getPersonDb().get(personId);
    }*/

    public List<Person> getPersons() {
        return personDb.getAll();
    }

    public void addPerson(Person person) {
        personDb.add(person);
    }

/*    public void updatePersons(Person person) {
        getPersonDb().update(person);
    }

    public void deletePerson(String id) {
        getPersonDb().delete(id);
    }*/

/*
    private PersonDB getPersonDb() {
        return personDb;
    }
*/

/*    public Person authenticate(String userid, String password) {
        return getPersonDb().getPersonIfAuthenticated(userid, password);
    }*/

}
