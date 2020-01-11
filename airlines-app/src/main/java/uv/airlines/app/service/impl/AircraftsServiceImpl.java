package uv.airlines.app.service.impl;

import uv.airlines.app.service.AircraftsService;
import uv.airlines.app.domain.Aircrafts;
import uv.airlines.app.repository.AircraftsRepository;
import uv.airlines.app.service.dto.AircraftsDTO;
import uv.airlines.app.service.mapper.AircraftsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Aircrafts}.
 */
@Service
@Transactional
public class AircraftsServiceImpl implements AircraftsService {

    private final Logger log = LoggerFactory.getLogger(AircraftsServiceImpl.class);

    private final AircraftsRepository aircraftsRepository;

    private final AircraftsMapper aircraftsMapper;

    public AircraftsServiceImpl(AircraftsRepository aircraftsRepository, AircraftsMapper aircraftsMapper) {
        this.aircraftsRepository = aircraftsRepository;
        this.aircraftsMapper = aircraftsMapper;
    }

    /**
     * Save a aircrafts.
     *
     * @param aircraftsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AircraftsDTO save(AircraftsDTO aircraftsDTO) {
        log.debug("Request to save Aircrafts : {}", aircraftsDTO);
        Aircrafts aircrafts = aircraftsMapper.toEntity(aircraftsDTO);
        aircrafts = aircraftsRepository.save(aircrafts);
        return aircraftsMapper.toDto(aircrafts);
    }

    /**
     * Get all the aircrafts.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AircraftsDTO> findAll() {
        log.debug("Request to get all Aircrafts");
        return aircraftsRepository.findAll().stream().map(aircraftsMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one aircrafts by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AircraftsDTO> findOne(Long id) {
        log.debug("Request to get Aircrafts : {}", id);
        return aircraftsRepository.findById(id).map(aircraftsMapper::toDto);
    }

    /**
     * Delete the aircrafts by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Aircrafts : {}", id);
        aircraftsRepository.deleteById(id);
    }
}
