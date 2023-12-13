package com.example.PVSSpringBoot.ControllerPackage;


import com.example.PVSSpringBoot.Entities.ProductFront;
import com.example.PVSSpringBoot.Entities.ProductFrontBuilder;
import com.example.PVSSpringBoot.Entities.UserFront;


import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import com.example.PVSSpringBoot.repositories.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Map;




@RestController
@CrossOrigin
@RequestMapping("/api")
public class Controller {
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private RequestService requestService;

    @GetMapping("/hello")
    UserFront hello(){
        return new UserFront(-1, "Hello", "", false);
    }

    @PostMapping("/setAdmin")
    public String setAdmin(@RequestBody Map<String, Long> body){
        return requestService.setAdmin(body.get("admin"), body.get("user"));
    }
    @PostMapping("/removeAdmin")
    public String removeAdminAccess(@RequestBody Map<String, Long> body){
        return requestService.removeAdminAccess(body.get("admin"), body.get("user"));
    }
    @PostMapping("/getUser")
    public UserFront getUser(@RequestBody Map<String, Integer> body){
        return requestService.getUser(body.get("id"));
    }
    @PostMapping("/getUserByEmail")
    public UserFront getUserByEmail(@RequestBody Map<String, String> body){
        return requestService.getUserByEmail(body.get("email"));
    }
    @GetMapping("/getJoinDate/{id}")
    public ResponseEntity<String> getJoinDate(@PathVariable long id){
        return ResponseEntity.ok(requestService.getJoinDate(id));
    }
    @PostMapping("/changeUserName")
    public ResponseEntity<String> changeUserName(@RequestBody Map<String, String> body){
        return ResponseEntity.ok(requestService.changeUserName(Long.parseLong(body.get("id")), body.get("newName")));
    }
    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestBody Map<String, Long> body){
        return requestService.deleteUser(body.get("admin"), body.get("user"));
    }

    @PostMapping("/addNewProduct")
    public String addNewProduct(@RequestBody Map<String, String> body){System.out.println("111");
        //in RequestService
        //public String addNewProduct(ProductFront)
        return requestService.addNewProduct(
                new ProductFrontBuilder()
                        .setProductName(body.get("name"))
                        .setApproved(false)
                        .setBrandName(body.get("brandName"))
                        .setDescription(body.get("description"))
                        .setPrice(Float.parseFloat(body.get("price")))
                        .setTargetAnimal(body.get("targetAnimal"))
                        .setCategoryName(body.get("categoryName"))
                        .setUserEmail(body.get("userEmail"))
                        .setProductPhoto(body.get("photo"))
                        .get()
        );
    }

    @PostMapping("/getProductByUser")
    public List<ProductFront> getRequestProductByUserEmail(@RequestBody Map<String, String> body){
        return requestService.getProductByUserEmail(body.get("email"));
    }

    @PostMapping("/deleteRequestProduct")
    public String deleteRequestProductById(@RequestBody Map<String, Long> body){
        return requestService.deleteProductById(body.get("id"));
    }
}
