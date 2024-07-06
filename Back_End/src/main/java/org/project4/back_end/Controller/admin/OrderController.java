//package org.project4.back_end.Controller.admin;
//
//import jakarta.transaction.Transactional;
//import org.project4.back_end.dto.CategoryDTO;
//import org.project4.back_end.dto.OrderDTO;
//import org.project4.back_end.output.CategoryOutPut;
//import org.project4.back_end.service.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@RequestMapping("/admin/order")
//@RestController
//public class OrderController {
//    @Autowired
//    private OrderService orderService;
//
//
//    @GetMapping("/getallrole")
//    public CategoryOutPut getAllUsers(@RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
//        CategoryOutPut result = new CategoryOutPut();
//        result.setPage(page);
//        Pageable pageable =  PageRequest.of(page - 1, limit);
//        result.setListResult(orderService.getAllCategories(pageable));
//        result.setTotalPage((int) Math.ceil((double) (orderService.totalItem()) / limit));
//        model.addAttribute("userAccountOutput", result);
//        return result;
//    }
//
//    @GetMapping("/getbycategoryname/{categoryname}")
//    public CategoryOutPut getRoleByRolename(@PathVariable String categoryname, @RequestParam("page") int page, @RequestParam("limit") int limit, Model model){
//        CategoryOutPut result = new CategoryOutPut();
//        result.setPage(page);
//        Pageable pageable =  PageRequest.of(page - 1, limit);
//        result.setListResult(orderService.getByCategoryName(categoryname,pageable));
//        result.setTotalPage((int) Math.ceil((double) (orderService.totalItem()) / limit));
//        model.addAttribute("userAccountOutput", result);
//        return result;
//    }
//    @GetMapping("/getbyid/{categoryid}")
//    public ResponseEntity<?> getByRoleid(@PathVariable int  categoryid){
//        try {
//            CategoryDTO role = orderService.getCategoryById(categoryid);
//            if (role==null){
//                return new ResponseEntity<>( "không có người dùng nào có id là: "+categoryid, HttpStatus.NOT_FOUND);
//            }
//            return new ResponseEntity<>(role, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>( null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    @PostMapping("/admin/create")
//    public ResponseEntity<String> createrole(@RequestBody CategoryDTO dto) {
//        try {
//            orderService.createCategory(dto);
//            return new ResponseEntity<>("more success " + dto, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    @PutMapping("/admin/update/{categoryid}")
//    public ResponseEntity<String> updateRole(@PathVariable int categoryid, @RequestBody OrderDTO dto) {
//        try {
//            dto.setOrderId(categoryid);
//            orderService.updateOrder(dto);
//            return new ResponseEntity<>(dto+"Cập nhật quyền người dùng thành công", HttpStatus.OK);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    @Transactional
//    @DeleteMapping("/admin/delete/{categoryid}")
//    public ResponseEntity<String> deleteUserAccount(@PathVariable int categoryid) {
//        try {
//            orderService.deleteCategory(categoryid);
//            return new ResponseEntity<>("Xóa  thành công", HttpStatus.OK);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}
