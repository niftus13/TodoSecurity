package org.cbox.todo1.service;

import org.cbox.todo1.dto.PageRequestDTO;
import org.cbox.todo1.dto.PageResponceDTO;
import org.cbox.todo1.dto.TodoDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface TodoService {

    Long register(TodoDTO dto);

    TodoDTO getOne(Long tno);

    void modify(TodoDTO dto);

    void remove(Long tno);

    PageResponceDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);

    
}
