package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DbServiceTest {

    @Autowired
    private DbService dbService;

    @Test
    void getAllTasks() {
        // given
        Task savedTask = dbService.saveTask(new Task(null, "Test", "Test"));

        // when
        List<Task> tasks = dbService.getAllTasks();

        // then
        assertTrue(tasks.size() > 0);
    }

    @Test
    void getTask() {
        // given
        Task savedTask = dbService.saveTask(new Task(null, "Test", "Test"));
        Long id = savedTask.getId();

        // when
        boolean result = dbService.getTask(id).isPresent();

        // then
        assertTrue(result);
    }

    @Test
    void saveTask() {
        // given
        Task taskToSave = new Task(null, "Test", "Test");

        // when
        Task savedTask = dbService.saveTask(taskToSave);

        // then
        assertEquals(taskToSave.getId(), savedTask.getId());
        assertEquals(taskToSave.getTitle(), savedTask.getTitle());
        assertEquals(taskToSave.getContent(), savedTask.getContent());
    }

    @Test
    void deleteTask() {
        // given
        Task savedTask = dbService.saveTask(new Task(null, "Test", "Test"));
        Long id = savedTask.getId();

        // when
        dbService.deleteTask(id);

        // then
        assertFalse(dbService.getTask(id).isPresent());
    }
}