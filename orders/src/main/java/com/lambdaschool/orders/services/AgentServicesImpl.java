package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Agent;
import com.lambdaschool.orders.repositories.AgentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "agentServices")
public class AgentServicesImpl implements AgentServices
{
    @Autowired
    AgentsRepository agentrepos;

    @Transactional
    @Override
    public Agent save(Agent agent)
    {
        return agentrepos.save(agent);
    }
}
