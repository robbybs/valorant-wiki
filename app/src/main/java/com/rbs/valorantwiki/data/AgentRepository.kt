package com.rbs.valorantwiki.data

import com.rbs.valorantwiki.model.Agent
import com.rbs.valorantwiki.model.AgentsData
import com.rbs.valorantwiki.model.OrderAgent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class AgentRepository {

//    fun searchAgents(query: String): List<Agent>{
//        return AgentsData.agents.filter {
//            it.name.contains(query, ignoreCase = true)
//        }
//    }
//
//    fun getAgentsById(agentId: String): Agent {
//        return AgentsData.agents.first {
//            it.id == agentId
//        }
//    }

    private val agents = mutableListOf<OrderAgent>()

    init {
        if (agents.isEmpty()) {
            AgentsData.agents.forEach {
                agents.add(OrderAgent(it, 0))
            }
        }
    }

    fun getAllAgents(): Flow<List<OrderAgent>> {
        return flowOf(agents)
    }

    fun getOrderAgentById(agentId: Long): OrderAgent {
        return agents.first {
            it.agent.id == agentId
        }
    }
}