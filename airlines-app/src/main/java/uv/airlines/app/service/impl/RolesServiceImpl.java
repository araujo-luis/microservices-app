package uv.airlines.app.service.impl;

import uv.airlines.app.service.RolesService;
import uv.airlines.app.domain.Roles;
import uv.airlines.app.repository.RolesRepository;
import uv.airlines.app.service.dto.RolesDTO;
import uv.airlines.app.service.mapper.RolesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link Roles}.
 */
@Service
@Transactional
public class RolesServiceImpl implements RolesService {

    private final Logger log = LoggerFactory.getLogger(RolesServiceImpl.class);

    private final RolesRepository rolesRepository;

    private final RolesMapper rolesMapper;

    public RolesServiceImpl(RolesRepository rolesRepository, RolesMapper rolesMapper) {
        this.rolesRepository = rolesRepository;
        this.rolesMapper = rolesMapper;
    }

   
    @Override
    public RolesDTO save(RolesDTO rolesDTO) {
        log.debug("Request to save Roles : {}", rolesDTO);
        Roles roles = rolesMapper.toEntity(rolesDTO);
        roles = rolesRepository.save(roles);
        return rolesMapper.toDto(roles);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RolesDTO> findAll() {
        log.debug("Request to get all Roles");
        return rolesRepository.findAll().stream().map(rolesMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

   
    @Override
    @Transactional(readOnly = true)
    public Optional<RolesDTO> findOne(String id) {
        log.debug("Request to get Roles : {}", id);
        return rolesRepository.findById(id).map(rolesMapper::toDto);
    }

 
    @Override
    public void delete(String id) {
        log.debug("Request to delete Roles : {}", id);
        rolesRepository.deleteById(id);
    }

	@Override
	public List<RolesDTO> findAllWhereUserIsNull() {
		// TODO Auto-generated method stub
		return null;
	}
}
