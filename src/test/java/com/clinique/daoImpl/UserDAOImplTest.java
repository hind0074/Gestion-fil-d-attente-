package com.clinique.daoImpl;

import com.clinique.beans.User;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserDAOImplTest {

    @Test
    void testMapFunction() throws Exception {
        // Mock du ResultSet
        ResultSet rs = mock(ResultSet.class);
        when(rs.getInt("id")).thenReturn(1);
        when(rs.getString("nom")).thenReturn("Ali");
        when(rs.getString("prenom")).thenReturn("Ben");
        when(rs.getString("email")).thenReturn("ali@example.com");
        when(rs.getString("tel")).thenReturn("0600000000");
        when(rs.getString("adresse")).thenReturn("Khouribga");
        when(rs.getString("login")).thenReturn("ali");
        when(rs.getString("password")).thenReturn("pass");
        when(rs.getString("role")).thenReturn("MEDECIN");
        when(rs.getDate("date_de_naissance")).thenReturn(Date.valueOf("1990-01-01"));
        when(rs.getString("cin")).thenReturn("AB123456");

        // Instancier le DAO (daoFactory pas nécessaire ici)
        UserDAOImpl dao = new UserDAOImpl(null);

        // Appeler la méthode map via réflexion (car elle est privée)
        var method = UserDAOImpl.class.getDeclaredMethod("map", ResultSet.class);
        method.setAccessible(true);
        User user = (User) method.invoke(dao, rs);

        // Vérifications
        assertEquals(1, user.getId());
        assertEquals("Ali", user.getNom());
        assertEquals("Ben", user.getPrenom());
        assertEquals("ali@example.com", user.getEmail());
        assertEquals(User.Role.MEDECIN, user.getRole());
        assertEquals(Date.valueOf("1990-01-01"), user.getDateDeNaissance());
    }
}
