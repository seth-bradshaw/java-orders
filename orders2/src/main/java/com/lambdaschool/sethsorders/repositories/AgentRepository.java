package com.lambdaschool.sethsorders.repositories;

import com.lambdaschool.sethsorders.models.Agent;
import org.springframework.data.repository.CrudRepository;

public interface AgentRepository extends CrudRepository<Agent, Long>
{
}
