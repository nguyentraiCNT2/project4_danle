package org.project4.back_end.Controller.admin;

import jakarta.transaction.Transactional;
import org.project4.back_end.dto.UserDTO;
import org.project4.back_end.output.UserOutPut;
import org.project4.back_end.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin/user")
public class UserAdminController {
    @Autowired
    private UserService userService;
    @GetMapping("/getAllUsers")
    public UserOutPut getAllUsers(@RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        UserOutPut result = new UserOutPut();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(userService.getAll(pageable));
        result.setTotalPage((int) Math.ceil((double) (userService.totalItem()) / limit));
        model.addAttribute("userAccountOutput", result);
        return result;
    }
    @GetMapping("/getbyusername/{username}")
    public UserOutPut getUserByUserName(@PathVariable String username, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        UserOutPut result = new UserOutPut();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(userService.getUserByUserName(username,pageable));
        result.setTotalPage((int) Math.ceil((double) (userService.totalItem()) / limit));
        model.addAttribute("userAccountOutput", result);
        return result;
    }
    @GetMapping("/getuserbyid/{userid}")
    public ResponseEntity<?> getByUserid(@PathVariable int userid){
        try {
            UserDTO user = userService.getUserById(userid);
            if (user==null){
                return new ResponseEntity<>( "không có người dùng nào có id là: "+userid, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>( null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/admin/createuser")
    public ResponseEntity<String> createUser(@RequestBody UserDTO dto) {
        try {
            userService.createUser(dto);
            return new ResponseEntity<>("more success " + dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/admin/updateuser/{userid}")
    public ResponseEntity<String> updateRole(@PathVariable int userid, @RequestBody UserDTO dto) {
        try {
            dto.setUserId(userid);
            userService.updateUser(dto);
            return new ResponseEntity<>(dto+"Cập nhật quyền người dùng thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Transactional
    @DeleteMapping("/admin/deleteuser/{userid}")
    public ResponseEntity<String> deleteUserAccount(@PathVariable int userid) {
        try {
            userService.deleteUser(userid);
            return new ResponseEntity<>("Xóa Ảnh thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
