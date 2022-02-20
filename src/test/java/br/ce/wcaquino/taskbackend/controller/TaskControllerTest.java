package br.ce.wcaquino.taskbackend.controller;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;
import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {
  
  @Mock
  private TaskRepo todoRepo;
  
  @InjectMocks
  private TaskController taskController;
  
  @Test
  void naoDeveSalvarTarefaSemDescricao() {
    try {
      Task t = getTask();
      t.setTask(null);
      taskController.save(t);
      Assertions.fail("");
    } catch (ValidationException ex) {
      Assertions.assertEquals("Fill the task description", ex.getMessage());
    }
  }

  @Test
  void naoDeveSalvarTarefaSemData() {
    try {
      Task t = getTask();
      t.setDueDate(null);
      t.setTask("Descrição");
      taskController.save(t);
      Assertions.fail("");
    } catch (ValidationException ex) {
      Assertions.assertEquals("Fill the due date", ex.getMessage());
    }
  }

  @Test
  void naoDeveSalvarTarefaComDataPassada() {
    try {
      Task t = getTask();
      t.setDueDate(LocalDate.of(2010, Month.MARCH, 20));
      t.setTask("Descrição");
      taskController.save(t);
      Assertions.fail("");
    } catch (ValidationException ex) {
      Assertions.assertEquals("Due date must not be in past", ex.getMessage());
    }
  }

  @Test
  void deveSalvarTarefaComSucesso() throws ValidationException {
    Task t = getTask();
    t.setDueDate(LocalDate.now());
    taskController.save(t);
    Mockito.verify(todoRepo).save(t);
  }

  private Task getTask() {
    var task = new Task();
    task.setId(Long.MAX_VALUE);
    task.setDueDate(LocalDate.now());
    task.setTask("Teste");
    return task;
  }
}
