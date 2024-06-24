package org.cbox.todo1.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.cbox.todo1.domain.Todo;
import org.cbox.todo1.dto.PageRequestDTO;
import org.cbox.todo1.dto.PageResponceDTO;
import org.cbox.todo1.dto.TodoDTO;
import org.cbox.todo1.repository.TodoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    //생성자 주입
    private final TodoRepository todoRepository;

    private final ModelMapper modelMapper;

    @Override
    public Long register(TodoDTO dto) {
    
        Todo todo = modelMapper.map(dto, Todo.class);

        Todo savedTodo = todoRepository.save(todo);

        return savedTodo.getTno();
    }

    @Override
    public TodoDTO getOne(Long tno) {
        Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();

        TodoDTO dto = modelMapper.map(todo, TodoDTO.class);

        return dto;

    }

    @Override
    public void modify(TodoDTO dto) {
        Optional<Todo> result = todoRepository.findById(dto.getTno());

        Todo todo = result.orElseThrow();

        todo.changeTitle(dto.getTitle());
        todo.changeDueDate(dto.getDueDate());
        todo.changeComplete(dto.isComplete());

        todoRepository.save(todo);
    }

    @Override
    public void remove(Long tno) {
       
        todoRepository.deleteById(tno);
    }

    @Override
    public PageResponceDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {

        Pageable pageable = 
        PageRequest.of(
            pageRequestDTO.getPage() -1,
            pageRequestDTO.getSize()
            , Sort.by("tno").descending());

        Page<Todo> result = todoRepository.findAll(pageable);

        List<TodoDTO> dtoList = result.getContent().stream()
        .map(todo -> modelMapper.map(todo, TodoDTO.class))
        .collect(Collectors.toList());

        long totalCount = result.getTotalElements();

        PageResponceDTO<TodoDTO> responceDTO = PageResponceDTO.<TodoDTO>withAll()
            .dtoList(dtoList)
            .pageRequestDTO(pageRequestDTO)
            .totalCount(totalCount)
            .build();

        return responceDTO;



    }

    


}
