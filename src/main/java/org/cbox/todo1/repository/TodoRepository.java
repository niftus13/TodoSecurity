package org.cbox.todo1.repository;

import org.cbox.todo1.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long>{
    
}
