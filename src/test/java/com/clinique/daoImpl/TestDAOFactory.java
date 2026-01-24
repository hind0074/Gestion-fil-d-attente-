package com.clinique.daoImpl;

import com.clinique.dao.DAOFactory;

public class TestDAOFactory extends DAOFactory {
    public TestDAOFactory() {
        super("jdbc:h2:mem:testdb", "sa", "");
    }
}
