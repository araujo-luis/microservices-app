package uv.airlines.app.service.impl;

import uv.airlines.app.service.AirportsService;
import uv.airlines.app.domain.Airports;
import uv.airlines.app.repository.AirportsRepository;
import uv.airlines.app.service.dto.AirportsDTO;
import uv.airlines.app.service.mapper.AirportsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Airports}.
 */
@Service
@Transactional
public class AirportsServiceImpl implements AirportsService {

    private final Logger log = LoggerFactory.getLogger(AirportsServiceImpl.class);

    private final AirportsRepository airportsRepository;
    
    private final AirportsMapper airportsMapper;

    public AirportsServiceImpl(AirportsRepository airportsRepository, AirportsMapper airportsMapper) {
        this.airportsRepository = airportsRepository;
        this.airportsMapper = airportsMapper;
    }

    @Override
    public AirportsDTO save(AirportsDTO airportsDTO) {
        Airports airports = airportsMapper.toEntity(airportsDTO);
        airports = airportsRepository.save(airports);
        return airportsMapper.toDto(airports);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AirportsDTO> findAll() {
        return airportsRepository.findAll().stream().map(airportsMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AirportsDTO> findOne(Long id) {
        return airportsRepository.findById(id).map(airportsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        airportsRepository.deleteById(id);
    }

	@Override
	public void saveAll(List<AirportsDTO> airportsDTO) {
		List<Airports> airports = airportsMapper.toList(airportsDTO);
		airportsRepository.saveAll(airports);
		
	}

	@Override
	public String getRandomAirport() {
		return airportsRepository.getRandomAirport();
	}
}
