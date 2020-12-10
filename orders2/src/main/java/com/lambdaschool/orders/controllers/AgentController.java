package com.lambdaschool.orders.controllers;

import com.lambdaschool.orders.models.Agent;
import com.lambdaschool.orders.services.AgentCustomerException;
import com.lambdaschool.orders.services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agents")
public class AgentController
{
    @Autowired
    AgentService agentService;

    @GetMapping(value = "/agent/{agentid}", produces = "application/json")
    public ResponseEntity<?> getAgentById(@PathVariable long agentid)
    {
        Agent agent = agentService.getById(agentid);

        return new ResponseEntity<>(agent, HttpStatus.OK);
    }

    @DeleteMapping(value = "/unassigned/{agentcode}")
    public ResponseEntity<?> deleteAgentWithNoCustomers(@PathVariable long agentcode) throws AgentCustomerException
    {
        agentService.deleteAgent(agentcode);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
