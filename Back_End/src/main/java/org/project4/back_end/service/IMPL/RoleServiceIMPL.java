package org.project4.back_end.service.IMPL;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.project4.back_end.Entity.RoleEntity;
import org.project4.back_end.dto.RoleDTO;
import org.project4.back_end.repository.RoleRepository;
import org.project4.back_end.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceIMPL implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public int totalItem() {
        return (int) roleRepository.count();
    }

    @Override
    public List<RoleDTO> getAllRoles(Pageable pageable) {
        List<RoleDTO> results = new ArrayList<>();
        List<RoleEntity> roleEntities = roleRepository.findAll(pageable).getContent();
        for (RoleEntity item : roleEntities
        ) {
            RoleDTO DTO = modelMapper.map(item, RoleDTO.class);
            results.add(DTO);
        }
        return results;
    }

    @Override
    public List<RoleDTO> getByRoleName(String roleName, Pageable pageable) {
        List<RoleDTO> results = new ArrayList<>();
        List<RoleEntity> roleEntities = roleRepository.findByRoleName(roleName, pageable);
        for (RoleEntity item : roleEntities
        ) {
            RoleDTO DTO = modelMapper.map(item, RoleDTO.class);
            results.add(DTO);
        }
        return results;
    }

    @Override
    public RoleDTO getRoleById(Integer roleId) {
        try {
            RoleEntity role = roleRepository.findByRoleId(roleId);
            if (role == null) {
                throw new EntityNotFoundException("Category not found");
            }
            return modelMapper.map(role, RoleDTO.class);
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching data by ID", e);
        }
    }

    @Override
    public void deleteRoleById(Integer roleId) {
        roleRepository.deleteByRoleId(roleId);
    }

    @Override
    public void saveRole(RoleDTO roleEntity) {
        if (roleEntity != null) {
            RoleEntity role = modelMapper.map(roleEntity, RoleEntity.class);
            if (role != null) {
                roleRepository.save(role);
            } else {
                throw new RuntimeException("Không lấy được dữ liệu của Entity");
            }
        }
    }

    @Override
    public void updateRole(RoleDTO roleEntity) {
        if (roleEntity != null) {
            RoleEntity role = roleRepository.findByRoleId(roleEntity.getRoleId());

            modelMapper.map(roleEntity, RoleEntity.class);
            if (role != null) {
                roleRepository.save(role);
            } else {
                throw new RuntimeException("Không lấy được dữ liệu của Entity");
            }
        }
    }
}
