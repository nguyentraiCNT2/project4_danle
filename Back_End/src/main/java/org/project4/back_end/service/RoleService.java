package org.project4.back_end.service;

import org.project4.back_end.Entity.RoleEntity;
import org.project4.back_end.dto.RoleDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {
    public int totalItem();
    public List<RoleDTO> getAllRoles(Pageable pageable);
    public List<RoleDTO> getByRoleName(String roleName , Pageable pageable);

    public RoleDTO getRoleById(Integer roleId);

    public void deleteRoleById(Integer roleId);

    public void saveRole(RoleDTO roleEntity);

    public void updateRole(RoleDTO roleEntity);
}
