package org.project4.back_end.repository;

import org.project4.back_end.Entity.RoleEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    // tìm kiếm quyền hạn theo tên quyền
    @Query("SELECT r FROM RoleEntity r WHERE r.roleName like :roleName")
    List<RoleEntity> findByRoleName(@Param("roleName") String roleName, Pageable pageable);
    RoleEntity findByRoleId(Integer roleId);
    void deleteByRoleId(Integer roleId);
}
