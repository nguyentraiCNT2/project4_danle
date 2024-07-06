package org.project4.back_end.Controller.admin;

import jakarta.transaction.Transactional;
import org.project4.back_end.dto.RoleDTO;
import org.project4.back_end.dto.UserDTO;
import org.project4.back_end.output.RoleOutPut;
import org.project4.back_end.output.UserOutPut;
import org.project4.back_end.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping("/getallrole")
    public RoleOutPut getAllUsers(@RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        RoleOutPut result = new RoleOutPut();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(roleService.getAllRoles(pageable));
        result.setTotalPage((int) Math.ceil((double) (roleService.totalItem()) / limit));
        model.addAttribute("userAccountOutput", result);
        return result;
    }

    @GetMapping("/getrolename/{rolename}")
    public RoleOutPut getRoleByRolename(@PathVariable String rolename, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
        RoleOutPut result = new RoleOutPut();
        result.setPage(page);
        Pageable pageable =  PageRequest.of(page - 1, limit);
        result.setListResult(roleService.getByRoleName(rolename,pageable));
        result.setTotalPage((int) Math.ceil((double) (roleService.totalItem()) / limit));
        model.addAttribute("userAccountOutput", result);
        return result;
    }
    @GetMapping("/getrolebyid/{roleid}")
    public ResponseEntity<?> getByRoleid(@PathVariable int  roleid){
        try {
            RoleDTO role = roleService.getRoleById(roleid);
            if (role==null){
                return new ResponseEntity<>( "không có người dùng nào có id là: "+roleid, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(role, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>( null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/admin/createrole")
    public ResponseEntity<String> createrole(@RequestBody RoleDTO dto) {
        try {
            roleService.saveRole(dto);
            return new ResponseEntity<>("more success " + dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/admin/updaterole/{roleid}")
    public ResponseEntity<String> updateRole(@PathVariable int roleid, @RequestBody RoleDTO dto) {
        try {
            dto.setRoleId(roleid);
            roleService.updateRole(dto);
            return new ResponseEntity<>(dto+"Cập nhật quyền người dùng thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Transactional
    @DeleteMapping("/admin/deleterole/{roleid}")
    public ResponseEntity<String> deleteUserAccount(@PathVariable int roleid) {
        try {
            roleService.deleteRoleById(roleid);
            return new ResponseEntity<>("Xóa  thành công", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
