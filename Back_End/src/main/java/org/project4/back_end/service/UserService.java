package org.project4.back_end.service;

import org.project4.back_end.dto.UserDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    int totalItem();
    //hiển thị tất cả các người dùng
    List<UserDTO> getAll(Pageable pageable);

    // hiển thị người dùng theo id
    UserDTO getUserById(Integer id);

    // hiển thị người dùng theo username
    List<UserDTO> getUserByUserName(String userName, Pageable pageable);

    // hiển thị người dùng theo first name
    List<UserDTO> getUserByFirstName(String firstName, Pageable pageable);

    // hiển thị người dùng theo last name
    List<UserDTO> getUserByLastName(String lastName, Pageable pageable);

    // hiển thị người dùng theo email
    List<UserDTO> getUserByEmail(String email, Pageable pageable);

    // hiển thị người dùng theo phone
    List<UserDTO> getUserByPhone(String phone, Pageable pageable);

    // hiển thị người dùng theo status
    List<UserDTO> getUserByStatus(Boolean status, Pageable pageable);

    // hiển thị người dùng theo gender
    List<UserDTO> getUserByGender(Boolean gender, Pageable pageable);

    // tạo người dùng
    void createUser(UserDTO user);

    // cập nhật người dùng
    void updateUser(UserDTO user);

    // xóa người dùng
    void deleteUser(Integer id);
}
