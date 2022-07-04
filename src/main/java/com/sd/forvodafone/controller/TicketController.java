package com.sd.forvodafone.controller;

import com.sd.forvodafone.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/ticket")
public class TicketController {

    @Autowired
    private ITicketService ticketService;

    @PostMapping
    public ResponseEntity<String> getAction(@RequestBody String input)
    {
        if(input.equals("status"))
        {
            String response=ticketService.getStatus();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else if(input.split("\\s+")[0].equals("park"))
        {
            String response=ticketService.park(input);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }
        else if(input.split("\\s+")[0].equals("leave"))
        {
            String response=ticketService.parkOut(input);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }
        return ResponseEntity.status(HttpStatus.OK).body(input);
    }
}
