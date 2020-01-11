package uv.airlines.app.service.impl;

import uv.airlines.app.service.AgenciesService;
import uv.airlines.app.domain.Agencies;
import uv.airlines.app.repository.AgenciesRepository;
import uv.airlines.app.service.dto.AgenciesDTO;
import uv.airlines.app.service.mapper.AgenciesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Agencies}.
 */
@Service
@Transactional
public class AgenciesServiceImpl implements AgenciesService {

    private final Logger log = LoggerFactory.getLogger(AgenciesServiceImpl.class);

    private final AgenciesRepository agenciesRepository;

    private final AgenciesMapper agenciesMapper;

    public AgenciesServiceImpl(AgenciesRepository agenciesRepository, AgenciesMapper agenciesMapper) {
        this.agenciesRepository = agenciesRepository;
        this.agenciesMapper = agenciesMapper;
    }

    @Override
    public AgenciesDTO save(AgenciesDTO agenciesDTO) {
        Agencies agencies = agenciesMapper.toEntity(agenciesDTO);
        agencies = agenciesRepository.save(agencies);
        return agenciesMapper.toDto(agencies);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AgenciesDTO> findAll() {
        return agenciesRepository.findAll().stream().map(agenciesMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AgenciesDTO> findOne(Long id) {
        return agenciesRepository.findById(id).map(agenciesMapper::toDto);
    }
    
    @Override
    public void delete(Long id) {
        agenciesRepository.deleteById(id);
    }

	@Override
	public Long getRandomAngency() {
		return agenciesRepository.getRandomAgency();
	}
}
