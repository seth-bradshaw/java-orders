package com.lambdaschool.sethsorders.services;

import com.lambdaschool.sethsorders.models.Agent;

public interface AgentService
{
    Agent getById(long agentid);

    Agent save(Agent agent);

    void deleteAllAgents();

    void deleteAgent(long agentcode) throws AgentCustomerException;
}
