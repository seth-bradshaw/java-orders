package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Agent;
import com.lambdaschool.orders.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "agentService")
public class AgentServiceImpl implements AgentService
{
    @Autowired
    AgentRepository agentrepos;

    @Override
    public Agent getById(long agentid) throws EntityNotFoundException
    {
        return agentrepos.findById(agentid).orElseThrow(() -> new EntityNotFoundException("Agent " + agentid + " not found!"));
    }

    @Transactional
    @Override
    public Agent save(Agent agent)
    {
        return agentrepos.save(agent);
    }

    @Override
    public void deleteAllAgents()
    {
        agentrepos.deleteAll();
    }
}
