package net.macaronics.springboot.webapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import net.macaronics.springboot.webapp.dto.todo.TodoFormDTO;
import net.macaronics.springboot.webapp.dto.todo.TodoResponseDTO;
import net.macaronics.springboot.webapp.entity.Todo;

/**
 * MapStruct를 사용하면, 인터페이스 기반으로 매핑 메서드를 정의하고, 컴파일 시점에 매핑 구현체가 자동으로 생성됩니다.
 * componentModel = "spring" 설정을 통해 Spring의 빈으로 등록됩니다.
 */
@Mapper(componentModel = "spring")
public interface TodoMapper {

	
    @Mapping(source = "user.username", target = "username")
    TodoResponseDTO toTodoResponseDTO(Todo todo);

    // 필요에 따라 다른 매핑 메서드도 추가
    // Todo toEntity(TodoRequestDTO dto);
    
    
    @Mapping(source = "username", target = "user.username")
    Todo ofTodo(TodoFormDTO todoFormDTO);

    
    
}


		



