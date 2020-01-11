package uv.airlines.app.service;

import uv.airlines.app.service.dto.AgenciesDTO;

import java.util.List;
import java.util.Optional;

public interface AgenciesService {

    AgenciesDTO save(AgenciesDTO agenciesDTO);

    List<AgenciesDTO> findAll();

    Optional<AgenciesDTO> findOne(Long id);

    void delete(Long id);

	Long getRandomAngency();
}
