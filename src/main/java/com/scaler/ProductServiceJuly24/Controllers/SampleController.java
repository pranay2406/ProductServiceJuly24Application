package com.scaler.ProductServiceJuly24.Controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class SampleController {

     @GetMapping("/say/{name}/{id}/{pwd}")
      public String sayHello(@PathVariable("name") String name,@PathVariable("id") String id,@PathVariable("pwd") String pwd){
           String output="";
              if(id.equalsIgnoreCase("prny2406") && pwd.equalsIgnoreCase("1234567890")){
                  output="Login Successful";
              }
              else{
                  output="Login Failed";
              }

          return output;
      }

      @GetMapping("/bye")
      public String sayBye(){
         return "Bye Everyone!!";
      }
}
