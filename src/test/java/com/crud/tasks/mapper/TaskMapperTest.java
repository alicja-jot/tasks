package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskMapperTest {

    private TaskMapper taskMapper = new TaskMapper();

    @Test
    void mapToTask() {
        // given
        TaskDto dto = new TaskDto(1L, "A", "Aaa");

        // when
        Task task = taskMapper.mapToTask(dto);

        // then
        assertEquals(dto.getId(), task.getId());
        assertEquals(dto.getTitle(), task.getTitle());
        assertEquals(dto.getContent(), task.getContent());
    }

    @Test
    void mapToTaskDto() {
        // given
        Task task = new Task(1L, "A", "Aaa");

        // when
        TaskDto dto = taskMapper.mapToTaskDto(task);

        // then
        assertEquals(task.getId(), dto.getId());
        assertEquals(task.getTitle(), dto.getTitle());
        assertEquals(task.getContent(), dto.getContent());
    }

    @Test
    void mapToTaskDtoList() {
        // given
        List<Task> tasks = new LinkedList<>();
        tasks.add(new Task(1L, "A", "Aaa"));

        // when
        List<TaskDto> dtos = taskMapper.mapToTaskDtoList(tasks);

        // then
        assertEquals(tasks.size(), dtos.size());
    }
}