package project.user.mapper;

import org.mapstruct.*;
import project.user.dto.UserDTO;
import project.user.model.ERole;
import project.user.model.Role;
import project.user.model.User;

import java.util.Iterator;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(source = "role", target = "role.name", ignore = true)
    })
    User toUser(UserDTO user);

    @Mappings({
            @Mapping(source = "role.name", target = "role", ignore = true)
    })
    UserDTO fromUser(User user);
}
