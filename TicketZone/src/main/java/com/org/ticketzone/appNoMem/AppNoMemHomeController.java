package com.org.ticketzone.appNoMem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.ticketzone.domain.OwnerVO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class AppNoMemHomeController {
   
   @ResponseBody
   @RequestMapping(value = "/nomemTicket", method = RequestMethod.POST)
   public String nomemTicket(@RequestBody OwnerVO o ) {
      System.out.println("dddddd");

      return "³Ñ¾î¿Í";
   }
   
}