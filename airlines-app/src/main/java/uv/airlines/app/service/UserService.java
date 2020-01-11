package uv.airlines.app.service;

import uv.airlines.app.service.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDTO save(UserDTO UserDTO);
   
    List<UserDTO> findAll();

    Optional<UserDTO> findOne(Long id);

    void delete(Long id);
}
