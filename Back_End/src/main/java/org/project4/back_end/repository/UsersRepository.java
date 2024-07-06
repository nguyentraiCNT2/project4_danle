package org.project4.back_end.repository;

import org.project4.back_end.Entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Integer> {
    // tìm kiếm người dùng theo username
    @Query("SELECT u FROM UserEntity u WHERE u.userName like :userName")
    List<UserEntity> findByUserName(@Param("userName") String userName, Pageable pageable);

    // tìm kiếm người dùng theo first name
    @Query("SELECT u FROM UserEntity u WHERE u.firstName like :firstName")
    List<UserEntity> findByFirstName(@Param("firstName") String firstName, Pageable pageable);

    // tìm kiếm người dùng theo last name
    @Query("SELECT u FROM UserEntity u WHERE u.lastName like :lastName")
    List<UserEntity> findByLastName(@Param("lastName") String lastName, Pageable pageable);

    // tìm kiếm người dùng theo email
    @Query("SELECT u FROM UserEntity u WHERE u.email like :email")
    List<UserEntity> findByEmail(@Param("email") String email, Pageable pageable);

    // tìm kiếm người dùng theo phone
    @Query("SELECT u FROM UserEntity u WHERE u.phone like :phone")
    List<UserEntity> findByPhone(@Param("phone") String phone, Pageable pageable);

    //  tìm kiếm người dùng theo status
    List<UserEntity> findByStatus(Boolean status, Pageable pageable);

    //  tìm kiếm người dùng theo gender
    List<UserEntity> findByGender(Boolean gender, Pageable pageable);

    UserEntity findByUserId(Integer userId);
    void deleteByUserId(Integer userId);

}
