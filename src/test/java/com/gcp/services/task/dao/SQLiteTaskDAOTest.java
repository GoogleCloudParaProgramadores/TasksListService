package com.gcp.services.task.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

public class SQLiteTaskDAOTest extends TaskDAOTest {

    @Autowired
    private TaskDAOFactory taskDAOFactory;

    @Before
    public void setup() throws Exception {
        taskDAO = taskDAOFactory.getDataBase();
    }

    @After
    public void tearDown() throws SQLException {
        taskDAOFactory.getDaoUtil().clean();
    }
}
