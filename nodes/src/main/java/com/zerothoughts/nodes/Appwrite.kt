package com.zerothoughts.nodes

import io.appwrite.Client
import io.appwrite.ID
import io.appwrite.services.Databases
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.springframework.stereotype.Component

@OptIn(DelicateCoroutinesApi::class)
@Component
class Appwrite {
    private val client: Client = Client()
        .setKey(Constants.APIKEY)
        .setProject(Constants.PROJECT)
        .setEndpoint(Constants.ENDPOINT)

    private val databases: Databases = Databases(client)

    private val parser: Parser = Parser()

    fun createNode(trigger: Trigger, node: Node){
        GlobalScope.launch {
            try{
                val response = databases.createDocument(
                    Constants.DATABASE,
                    Constants.NODES,
                    ID.unique(),
                    node
                )
                trigger.transferObject(parser.parseNode(response))
            } catch (exception: Exception){
                trigger.transferObject(exception.message)
            }
        }
    }
    fun getNode(trigger: Trigger, id: String){
        GlobalScope.launch {
            try{
                val response = databases.getDocument(
                    Constants.DATABASE,
                    Constants.NODES,
                    id
                )
                trigger.transferObject(parser.parseNode(response))
            } catch (exception: Exception){
                trigger.transferObject(id+": "+exception.message)
            }
        }
    }
    fun updateNode(trigger: Trigger, id: String, update: Map<String, Any>){
        GlobalScope.launch {
            if(update.containsKey("id") || update.containsKey("parent") || update.containsKey("children") || update.containsKey("level"))
                trigger.transferObject("$id: Only task and complete state is mutable!")
            try{
                val response = databases.updateDocument(
                    Constants.DATABASE,
                    Constants.NODES,
                    id,
                    update
                )
                trigger.transferObject(parser.parseNode(response))
            } catch (exception: Exception){
                trigger.transferObject(exception.message)
            }
        }
    }
}