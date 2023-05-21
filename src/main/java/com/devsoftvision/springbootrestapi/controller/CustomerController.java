package com.devsoftvision.springbootrestapi.controller;

import com.devsoftvision.springbootrestapi.bean.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//common url
@RequestMapping("customers")
public class CustomerController {
//    @GetMapping("/customer")
//    public Customer getCustomer(){
//
//        Customer customer =new Customer(
//                1,"dbn","shakya"
//        );
//        return customer;
//    }
    //best way to handle status in real world project
    @GetMapping("/customer")
    public ResponseEntity<Customer> getCustomer(){

        Customer customer =new Customer(
                1,"dbn","shakya"
        );
//        return new ResponseEntity<>(customer,HttpStatus.OK);
//        return ResponseEntity.ok(customer);
        return ResponseEntity.ok().header("custom-header","dbn").body(customer);
    }

    //http://localhost:8080/customers
    @GetMapping("/customers")
    public List<Customer> getCustomers(){
        List<Customer> customers =new ArrayList<>();
        customers.add(new Customer(1,"dbn","Shakya"));
        customers.add(new Customer(2,"debn","raj"));
        customers.add(new Customer(1,"dben","sha"));
        return customers;

    }

    //spring boot rest api with path variable
    //http://localhost:8080/customers/1/dbn/shakya
    @GetMapping("/{id}/{first-name}/{last-name}")
    public ResponseEntity<Customer> customerPathVariable(@PathVariable("id")  int customerId, @PathVariable("first-name") String firstName, @PathVariable("last-name") String lastName){
        Customer customer=new Customer(customerId,firstName,lastName);
        return ResponseEntity.ok(customer);
    }


//spring boot REST API with Request Param
    //http://localhost:8080/customers/query?id=1
    @GetMapping("/query")
    public Customer customerRequestVariable(@RequestParam int id, @RequestParam("first-name") String firstName,@RequestParam String lastName){
        return new Customer(id,firstName,lastName);
    }


    //spring boot REST API that handles HTTP POST Request
    //@PostMapping and @RequestBody
    //code not complete still need to save into database
    @PostMapping("/create")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        System.out.println(customer.getId());
        System.out.println(customer.getFirstName());
        System.out.println(customer.getLastName());
        return new ResponseEntity<>(customer,HttpStatus.CREATED);

    }

    //spring boot REST API that handles HTTP PUT Request
    @PutMapping("/{id}/update")
    public Customer updateCustomer(@RequestBody Customer customer,@PathVariable int id){
        System.out.println(customer.getId());
        System.out.println(customer.getFirstName());
        System.out.println(customer.getLastName());
        return customer;
    }

    //spring boot REST API that handles HTTP DELETE Request
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteCustomer ( @PathVariable int id){
        System.out.println(id);
        return ResponseEntity.ok("customer deleted successfully");

    }

}

//public List<Student>
