package domain.service;

import domain.db.*;
import domain.model.Contact;
import domain.model.Person;
import domain.model.Test;

import java.util.List;

public class ContactTracingService {
    private PersonDB personDb = new PersonDBSQL();

    public ContactTracingService() {
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

    public List<Person> allUsersWithManyContacts() {
        return getPersonDb().getusersWithManyContacts();
    }


//-----------------------------------------------------------------------------------------------------------------------------//

    private ContactDB contactDB = new ContactDBSQL();

    public List<Contact> getAllContacts() {
        return getContactDB().getAllContacts();
    }

    public List<Contact> getContactsUser(String userid) {
        return getContactDB().getAllContactsFromUser(userid);
    }

    private ContactDB getContactDB() {
        return contactDB;
    }

    public void addContact(Contact contact) {
        getContactDB().add(contact);
    }

    public void deleteContact(int id) {
        getContactDB().delete(id);
    }

    public List<Contact> getAllContactsSincePositiveTest(String userid) {
        return getContactDB().getAllContactsSinceLatestPositiveTest(userid);
    }


//----------------------------------------------------------------------------------------------------------------------------//

    private TestDB testDB = new TestDBSQL();

    private TestDB getTestDB() {return testDB;}

    public void addTest(Test test) {getTestDB().add(test);}

    public List<Test> getAllTests() {return getTestDB().allTests();}

}
