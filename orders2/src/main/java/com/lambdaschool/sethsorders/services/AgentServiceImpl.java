package com.lambdaschool.sethsorders.services;

import com.lambdaschool.sethsorders.models.Agent;
import com.lambdaschool.sethsorders.repositories.AgentRepository;
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

    @Override
    public void deleteAgent(long agentcode) throws AgentCustomerException
    {
        Agent newAgent = agentrepos.findById(agentcode)
                .orElseThrow(() -> new EntityNotFoundException("Agent " + agentcode  + " not found."));
        if (newAgent.getCustomers().size() < 1)
        {
            agentrepos.deleteById(agentcode);
        }
        else
        {
            throw new AgentCustomerException("This agent has customers silly");
        }
    }
}
