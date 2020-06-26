package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private DbService service;

    @Test
    public void shouldGetTasks() throws Exception {
        // given
        List<TaskDto> taskDtos = new ArrayList();
        taskDtos.add(new TaskDto(1L, "A", "Aaa"));
        taskDtos.add(new TaskDto(2L, "B", "Bbb"));
        when(taskMapper.mapToTaskDtoList(any(List.class))).thenReturn(taskDtos);

        // when
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldGetTask() throws Exception {
        // given
        Task task = new Task(1L, "A", "Aaa");
        when(service.getTask(1L)).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(task))
                .thenReturn(new TaskDto(task.getId(), task.getTitle(), task.getContent()));

        // when
        mockMvc.perform(get("/v1/tasks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(task.getId().intValue())))
                .andExpect(jsonPath("$.title", is(task.getTitle())))
                .andExpect(jsonPath("$.content", is(task.getContent())));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        // when
        mockMvc.perform(delete("/v1/tasks/1"))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        // given
        TaskDto dto = new TaskDto(1L, "A", "Aaa");

        String dtoAsJson = new Gson().toJson(dto);

        // when
        mockMvc.perform(post("/v1/tasks")
                .content(dtoAsJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        // given
        TaskDto dto = new TaskDto(1L, "A", "Aaa");

        String dtoAsJson = new Gson().toJson(dto);
        Task task = new Task();
        when(taskMapper.mapToTask(dto)).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(dto);

        // when
        mockMvc.perform(put("/v1/tasks")
                .content(dtoAsJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(dto.getId().intValue())))
                .andExpect(jsonPath("$.title", is(dto.getTitle())))
                .andExpect(jsonPath("$.content", is(dto.getContent())));
    }
}