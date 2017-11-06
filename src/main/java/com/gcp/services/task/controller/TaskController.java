package com.gcp.services.task.controller;

import com.gcp.services.task.dao.ITaskDAO;
import com.gcp.services.task.dao.TaskDAOFactory;
import com.gcp.services.task.model.Task;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {
    private static Logger log = Logger.getLogger(TaskController.class.getName());

    private ITaskDAO taskDAO;

    @Autowired
    private TaskDAOFactory taskDAOFactory;

    @RequestMapping(value = "/tasks", method=RequestMethod.GET)
    public List<Task> filterTasks(
            @RequestParam(value="query", defaultValue="", required=false) String query,
            @RequestParam(value="status", defaultValue="-1", required=false) int status) throws Exception {
        taskDAO = taskDAOFactory.getDataBase();
        return taskDAO.filter(query, status);
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET)
    public Task fetchTaskByID(@PathVariable("id") int ID) throws Exception {
        taskDAO = taskDAOFactory.getDataBase();
        return this.taskDAO.fetchByID(ID);
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    public Task createTask(@RequestBody Task task) throws Exception {
        taskDAO = taskDAOFactory.getDataBase();
        taskDAO.save(task);
        return task;
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.PUT)
    public Task updateTask(@RequestBody Task task) throws Exception {
        taskDAO = taskDAOFactory.getDataBase();
        taskDAO.update(task);
        return task;
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.DELETE)
    public Task deleteTask(@RequestBody Task task) throws Exception {
        taskDAO = taskDAOFactory.getDataBase();
        taskDAO.remove(task);
        return new Task();
    }

    @RequestMapping(value = "/ishealth", method = RequestMethod.GET)
    public String isHealth() {
        log.debug("TASKS LIST SERVICE IS HEALTH");
        return "ok";
    }
}
