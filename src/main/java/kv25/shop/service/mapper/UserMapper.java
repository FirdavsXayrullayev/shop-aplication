package kv25.shop.service.mapper;

import kv25.shop.dto.UserDto;
import kv25.shop.model.Users;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(Users entity){
        return entity == null ? null : new UserDto(
                entity.getId(),
                entity.getFullName(),
                entity.getUsername(),
                entity.getEmail()
        );
    }
    public Users toEntity(UserDto dto){
        return dto == null ? null : new Users(
                dto.getId(),
                dto.getFullName(),
                dto.getUsername(),
                dto.getEmail(),
                null
        );
    }
}
