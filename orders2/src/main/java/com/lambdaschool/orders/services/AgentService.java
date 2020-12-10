package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Agent;

public interface AgentService
{
    Agent getById(long agentid);

    Agent save(Agent agent);

    void deleteAllAgents();
}
