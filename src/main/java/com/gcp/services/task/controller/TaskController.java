package com.gcp.services.task.controller;

import com.gcp.services.task.dao.ITaskDAO;
import com.gcp.services.task.dao.TaskDAOFactory;
import com.gcp.services.task.model.Task;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {
    private static Logger log = Logger.getLogger(TaskController.class.getName());

    private ITaskDAO taskDAO;

    public TaskController(){
        try {
            TaskDAOFactory taskDAOFactory = new TaskDAOFactory();
            taskDAO = taskDAOFactory.getDataBase();

        } catch (Exception e) {
            log.debug(e.getMessage());
        }
    }

    @RequestMapping(value = "/tasks", method=RequestMethod.GET)
    public List<Task> filterTasks(@RequestParam(value="query", defaultValue="", required=false) String query, @RequestParam(value="status", defaultValue="-1", required=false) int status) throws ParseException, SQLException {
        return this.taskDAO.filter(query, status);
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET)
    public Task fetchTaskByID(@PathVariable("id") int ID) throws ParseException, SQLException{
        return this.taskDAO.fetchByID(ID);
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    public Task createTask(@RequestBody Task task) throws SQLException{
        taskDAO.save(task);
        return task;
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.PUT)
    public Task updateTask(@RequestBody Task task) throws SQLException{
        taskDAO.update(task);
        return task;
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.DELETE)
    public Task deleteTask(@RequestBody Task task) throws SQLException {
        this.taskDAO.remove(task);
        return new Task();
    }
}
