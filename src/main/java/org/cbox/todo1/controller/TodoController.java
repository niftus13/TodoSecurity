package org.cbox.todo1.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.cbox.todo1.dto.PageRequestDTO;
import org.cbox.todo1.dto.PageResponceDTO;
import org.cbox.todo1.dto.TodoDTO;
import org.cbox.todo1.service.TodoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/todo/")
public class TodoController {

    private final TodoService service;


    @GetMapping("{tno}")
    public TodoDTO getOne(@PathVariable(name = "tno") Long tno){

        return service.getOne(tno);
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/list")
    public PageResponceDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO){

        return service.getList(pageRequestDTO);
    }

    @PostMapping("/")
    public Map<String, Long> register(@RequestBody TodoDTO todoDTO){

        Long tno = service.register(todoDTO);

        return Map.of("TNO",tno);
        
    }

        @PutMapping("/{tno}")
    public Map<String, String> modify(
            @PathVariable(name="tno") Long tno,
            @RequestBody TodoDTO todoDTO) {

        todoDTO.setTno(tno);

        log.info("Modify: " + todoDTO);

        service.modify(todoDTO);

        return Map.of("RESULT", "SUCCESS");
    }

    
    @DeleteMapping("/{tno}")
    public Map<String, String> remove( @PathVariable(name="tno") Long tno ){

        log.info("Remove:  " + tno);

        service.remove(tno);

        return Map.of("RESULT", "SUCCESS");
    }

    
}
