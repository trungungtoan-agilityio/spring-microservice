package com.api.ecommerce.controllers

import com.api.ecommerce.domains.User
import com.api.ecommerce.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    lateinit var userRepository: UserRepository

    @ResponseBody
    @GetMapping("")
    fun getAllUsers(): List<User> {
        return userRepository.findAll().distinct()
    }

    @ResponseBody
    @GetMapping("/{id}")
    fun getUser(@PathVariable("id") userId : Long): User {
        return userRepository.findById(userId).get()
    }

    @ResponseBody
    @PostMapping("/admin")
    fun createAdmin(
        @RequestParam("user_name") userName: String,
        @RequestParam("email") email: String,
        @RequestParam("phone") phone: String
    ): Map<String, Any> {

        // Create admin user and save to db
        val user = User()
        user.userName = userName
        user.email = email
        user.phone = phone
        user.setAdminRole()
        userRepository.save(user)

        val map = LinkedHashMap<String, Any>()
        map["result"] = "Added"
        return map
    }

    @ResponseBody
    @PostMapping("/business")
    fun createBusiness(
        @RequestParam("user_name") userName: String,
        @RequestParam("email") email: String,
        @RequestParam("phone") phone: String
    ): Map<String, Any> {

        // Create admin user and save to db
        val user = User()
        user.userName = userName
        user.email = email
        user.phone = phone
        user.setBusinessRole()
        userRepository.save(user)

        val map = LinkedHashMap<String, Any>()
        map["result"] = "Added"
        return map
    }

    @ResponseBody
    @PostMapping("/customer")
    fun createCustomer(
        @RequestParam("user_name") userName: String,
        @RequestParam("email") email: String,
        @RequestParam("phone") phone: String
    ): Map<String, Any> {

        // Create admin user and save to db
        val user = User()
        user.userName = userName
        user.email = email
        user.phone = phone
        user.setCustomerRole()
        userRepository.save(user)

        val map = LinkedHashMap<String, Any>()
        map["result"] = "Added"
        return map
    }

    @ResponseBody
    @PutMapping("")
    fun updateUser(
        @RequestParam("user_id") userId: Int,
        @RequestParam("user_name") userName: String
    ): Map<String, Any> {
        // userService.updateUser(userId, userName)
        val map = LinkedHashMap<String, Any>()
        map["result"] = "Updated"
        return map
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable("id") userId: Long): Map<String, Any> {
        userRepository.deleteById(userId)
        val map = LinkedHashMap<String, Any>()
        map["result"] = "Deleted"
        return map
    }
}