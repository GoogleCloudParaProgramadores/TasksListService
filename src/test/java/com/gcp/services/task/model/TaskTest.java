package com.gcp.services.task.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaskTest {
    public TaskTest(){

    }

    @Test
    public void getName() throws Exception {
        Task task = new Task();
        task.setTitle("GCP Course");
        assertEquals("GCP Course", task.getTitle());
    }
}