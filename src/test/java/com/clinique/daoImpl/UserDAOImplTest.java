package com.clinique.daoImpl;

import com.clinique.beans.User;
import com.clinique.dao.DAOFactory;
import com.clinique.dao.DAOException;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOImplTest {

    private static DAOFactory daoFactory;
    private static UserDAOImpl userDAO;

    @BeforeAll
    static void setupDatabase() throws Exception {
        // Connexion à une base H2 en mémoire
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");
        Statement stmt = conn.createStatement();

        // Créer la table "users" (⚠️ pas "user")
        stmt.execute("CREATE TABLE utilisateurs (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nom VARCHAR(50), prenom VARCHAR(50), email VARCHAR(100), tel VARCHAR(20), adresse VARCHAR(100)," +
                "login VARCHAR(50), password VARCHAR(50), role VARCHAR(20), date_de_naissance DATE, cin VARCHAR(20))");

        conn.close();

        // Simuler DAOFactory
        daoFactory = new TestDAOFactory();
        userDAO = new UserDAOImpl(daoFactory);
    }

    @Test
    void testCreateAndFindById() throws DAOException {
        User user = new User();
        user.setNom("Ali");
        user.setPrenom("Ben");
        user.setEmail("ali@example.com");
        user.setTel("0600000000");
        user.setAdresse("Khouribga");
        user.setLogin("ali");
        user.setPassword("pass");
        user.setRole(User.Role.MEDECIN);
        user.setDateDeNaissance(Date.valueOf("1990-01-01"));
        user.setCin("AB123456");

        userDAO.create(user);
        assertTrue(user.getId() > 0);

        User fetched = userDAO.findById(user.getId());
        assertNotNull(fetched);
        assertEquals("Ali", fetched.getNom());
    }

    @Test
    void testFindByLogin() throws DAOException {
        User user = userDAO.findByLogin("ali", "pass");
        assertNotNull(user);
        assertEquals("Ali", user.getNom());
    }

    @Test
    void testUpdate() throws DAOException {
        User user = userDAO.findByLogin("ali", "pass");
        user.setNom("Ali Updated");
        userDAO.update(user);

        User updated = userDAO.findById(user.getId());
        assertEquals("Ali Updated", updated.getNom());
    }

    @Test
    void testGetAll() throws DAOException {
        List<User> users = userDAO.getAll();
        assertFalse(users.isEmpty());
        assertEquals("Ali Updated", users.get(0).getNom());
    }

    @Test
    void testDelete() throws DAOException {
        User user = userDAO.findByLogin("ali", "pass");
        userDAO.delete(user.getId());

        User deleted = userDAO.findById(user.getId());
        assertNull(deleted);
    }
}
