package com.gcp.services.task.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.sql.SQLException;

@Component
public class TaskDAOFactory {
    private static Logger log = Logger.getLogger(TaskDAOFactory.class.getName());

    @Autowired
    private DAOUtil daoUtil;

    private ITaskDAO taskDAO;

    public ITaskDAO getDataBase() throws Exception {
        log.debug("TASK LIST DBMS: " + daoUtil.getDbms());
        if(taskDAO == null) {
            if(daoUtil.getDbms().toLowerCase().equals("mysql")) {
                taskDAO = createMySQLTaskDAO();
            }

            if(daoUtil.getDbms().toLowerCase().equals("sqlite")) {
                taskDAO = createSQLiteTaskDAO();
            }
        }

        return taskDAO;
    }

    private TaskDAO createMySQLTaskDAO() throws SQLException, ClassNotFoundException, IOException {
        return new TaskDAO(daoUtil.connectToMysql());
    }

    private TaskDAO createSQLiteTaskDAO() throws SQLException, ClassNotFoundException, IOException {
        TaskDAO taskDAO = new TaskDAO(daoUtil.connectToSQLite());
        taskDAO.createDatabase();

        return taskDAO;
    }

    public DAOUtil getDaoUtil(){
        return daoUtil;
    }
}
