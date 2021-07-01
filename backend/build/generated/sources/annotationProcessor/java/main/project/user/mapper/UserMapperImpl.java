package project.user.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import project.user.dto.UserDTO;
import project.user.model.Role;
import project.user.model.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-01T15:31:07+0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.8.3.jar, environment: Java 11.0.6 (JetBrains s.r.o)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserDTO user) {
        if ( user == null ) {
            return null;
        }

        User user1 = new User();

        user1.setRole( userDTOToRole( user ) );
        user1.setId( user.getId() );
        user1.setName( user.getName() );
        user1.setUsername( user.getUsername() );
        user1.setEmail( user.getEmail() );
        user1.setPassword( user.getPassword() );

        return user1;
    }

    @Override
    public UserDTO fromUser(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setName( user.getName() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setPassword( user.getPassword() );

        return userDTO;
    }

    protected Role userDTOToRole(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        Role role = new Role();

        return role;
    }
}
