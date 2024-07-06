package org.project4.back_end.service.IMPL;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.project4.back_end.Entity.RoleEntity;
import org.project4.back_end.Entity.UserEntity;
import org.project4.back_end.dto.UserDTO;
import org.project4.back_end.repository.RoleRepository;
import org.project4.back_end.repository.UsersRepository;
import org.project4.back_end.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceIMPL implements UserService {
    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public int totalItem() {
        return (int) userRepository.count();
    }

    @Override
    public List<UserDTO> getAll(Pageable pageable) {
        List<UserDTO> results = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findAll(pageable).getContent();
        for (UserEntity item: userEntities
        ) {
            UserDTO DTO = modelMapper.map(item, UserDTO.class);
            results.add(DTO);
        }
        return results;
    }

    @Override
    public UserDTO getUserById(Integer id) {
        try {
            UserEntity user = userRepository.findByUserId(id);
            return modelMapper.map(user,UserDTO.class);
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching data by ID", e);
        }
    }

    @Override
    public List<UserDTO> getUserByUserName(String userName, Pageable pageable) {
        List<UserDTO> results = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findByUserName(userName,pageable);
        for (UserEntity item: userEntities
        ) {
            UserDTO DTO = modelMapper.map(item, UserDTO.class);
            results.add(DTO);
        }
        return results;
    }

    @Override
    public List<UserDTO> getUserByFirstName(String firstName, Pageable pageable) {
        List<UserDTO> results = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findByFirstName(firstName,pageable);
        for (UserEntity item: userEntities
        ) {
            UserDTO DTO = modelMapper.map(item, UserDTO.class);
            results.add(DTO);
        }
        return results;
    }

    @Override
    public List<UserDTO> getUserByLastName(String lastName, Pageable pageable) {
        List<UserDTO> results = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findByLastName(lastName,pageable);
        for (UserEntity item: userEntities
        ) {
            UserDTO DTO = modelMapper.map(item, UserDTO.class);
            results.add(DTO);
        }
        return results;
    }

    @Override
    public List<UserDTO> getUserByEmail(String email, Pageable pageable) {
        List<UserDTO> results = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findByEmail(email,pageable);
        for (UserEntity item: userEntities
        ) {
            UserDTO DTO = modelMapper.map(item, UserDTO.class);
            results.add(DTO);
        }
        return results;
    }

    @Override
    public List<UserDTO> getUserByPhone(String phone, Pageable pageable) {
        List<UserDTO> results = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findByPhone(phone,pageable);
        for (UserEntity item: userEntities
        ) {
            UserDTO DTO = modelMapper.map(item, UserDTO.class);
            results.add(DTO);
        }
        return results;
    }

    @Override
    public List<UserDTO> getUserByStatus(Boolean status, Pageable pageable) {
        List<UserDTO> results = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findByStatus(status,pageable);
        for (UserEntity item: userEntities
        ) {
            UserDTO DTO = modelMapper.map(item, UserDTO.class);
            results.add(DTO);
        }
        return results;
    }

    @Override
    public List<UserDTO> getUserByGender(Boolean gender, Pageable pageable) {
        List<UserDTO> results = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findByGender(gender,pageable);
        for (UserEntity item: userEntities
        ) {
            UserDTO DTO = modelMapper.map(item, UserDTO.class);
            results.add(DTO);
        }
        return results;
    }

    @Override
    public void createUser(UserDTO user) {
        if(user != null){
            UserEntity userEntity = modelMapper.map(user, UserEntity.class);
            RoleEntity roleEntity = roleRepository.findByRoleId(user.getRole().getRoleId());
            userEntity.setRole(roleEntity);
            userRepository.save(userEntity);
        }
    }

    @Override
    public void updateUser(UserDTO user) {

            UserEntity userEntity = userRepository.findByUserId(user.getUserId());
            if (userEntity == null) {
            throw new EntityNotFoundException("User not found");
        }
        RoleEntity roleEntity = roleRepository.findByRoleId(userEntity.getRole().getRoleId());
        userEntity.setRole(roleEntity);
            modelMapper.map(user, userEntity);
            userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteByUserId(id);
    }
}
