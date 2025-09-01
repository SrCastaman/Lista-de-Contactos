package org.example;

import java.util.List;

import org.example.dao.ContactDAO;
import org.example.db.Database;
import org.example.model.Contact;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ContactDAOTest {

    static ContactDAO dao;

    @BeforeAll

    static void setup() {
        Database.initialize();
        dao = new ContactDAO();
    }

    @Test
    void testInsertAndGetAll(){
        Contact c = new Contact("Juan", "1234456", "juan@gmai.com");
        dao.insert(c);
        assertTrue(c.getId() > 0);

        List<Contact> contacts = dao.getAll();
        assertTrue(contacts.stream().anyMatch(ct -> ct.getName().equals("Juan")));
    }


    @Test
    void testGetContact(){
        Contact c = new Contact("Ana", "1234567", "ana@gmail.com");
        dao.insert(c);

        Contact fetched = dao.getContact(c.getId());
        assertNotNull(fetched, "El contacto debe de existir");
        assertEquals("Ana", fetched.getName());
        assertEquals("1234567", fetched.getPhone());
        assertEquals("ana@gmail.com", fetched.getEmail());
    }


    @Test
    void testUpdate(){
        Contact c = new Contact("Luis", "12345", "luis@gmail.com");
        dao.insert(c);

        dao.update(c.getId(), "Luis Actualizado", null, "luis.new@gmail.com");
        Contact update = dao.getContact(c.getId());

        assertEquals("Luis Actualizado", update.getName());
        assertEquals("12345", update.getPhone());
        assertEquals("luis.new@gmail.com", update.getEmail());

    }


    @Test
    void testDelete(){
        Contact c = new Contact("Maria", "12345", "maria@gmail.com");
        dao.insert(c);

        dao.delete(c.getId());

        Contact deleted = dao.getContact(c.getId());
        assertNull(deleted, "El contacto debería de haber sido eliminado");
    }
}
