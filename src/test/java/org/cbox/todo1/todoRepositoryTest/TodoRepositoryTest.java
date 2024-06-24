package org.cbox.todo1.todoRepositoryTest;

import java.time.LocalDate;
import java.util.Optional;

import org.cbox.todo1.domain.Todo;
import org.cbox.todo1.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2

public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;


    @Test
    public void testInsert(){

    for (int i = 1; i <= 100; i++) {

      Todo todo = Todo.builder()
              .title("Title..." + i)
              .dueDate(LocalDate.of(2023, 12, 31))
              .writer("user00")
              .complete(i%2 == 0)
              .build();

      todoRepository.save(todo);
    }

    }
    @Test
    public void testRead() {
  
      //존재하는 번호로 확인 
      Long tno = 33L;
  
      Optional<Todo> result = todoRepository.findById(tno);
  
      Todo todo = result.orElseThrow();
  
      log.info(todo);
    }


    
}
