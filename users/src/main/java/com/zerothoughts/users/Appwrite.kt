package com.zerothoughts.users

import io.appwrite.Client
import io.appwrite.ID
import io.appwrite.services.Databases
import io.appwrite.services.Users
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.springframework.aop.framework.adapter.GlobalAdvisorAdapterRegistry
import org.springframework.stereotype.Component

@OptIn(DelicateCoroutinesApi::class)
@Component
class Appwrite {
    private val client: Client = Client()
        .setEndpoint(Constants.ENDPOINT)
        .setKey(Constants.APIKEY)
        .setProject(Constants.PROJECT)

    private val users: Users = Users(client)
    private val databases: Databases = Databases(client)

    private val parser: Parser = Parser()

    fun createUser(trigger: Trigger, user: User){
        GlobalScope.launch {
            try{
                val response = users.create(
                    ID.unique(),
                    user.email,
                    user.phone,
                    user.password,
                    user.name
                )
                trigger.transferObject(parser.parseUser(response))
            } catch (exception: Exception){
                trigger.transferObject(exception.message)
            }
        }
    }
    fun getUser(trigger: Trigger, id: String){
        GlobalScope.launch {
            try{
                val response = databases.getDocument(
                    Constants.DATABASE,
                    Constants.USERS,
                    id
                )
//                val response = users.get(id)
                trigger.transferObject(parser.parseUser(response))
            } catch (exception: Exception){
                trigger.transferObject(exception.message)
            }
        }
    }
    fun updateUser(trigger: Trigger, id: String, user: User){
        GlobalScope.launch {
            try{
                if (user.name != null)
                    users.updateName(id, user.name)
                if (user.email != null)
                    users.updateEmail(id, user.email)
                if (user.phone != null)
                    users.updatePhone(id, user.phone)
                if (user.password != null)
                    users.updatePassword(id, user.password)
                val response = users.get(id)
                trigger.transferObject(parser.parseUser(response))
            } catch (exception: Exception){
                trigger.transferObject(id+": "+exception.message)
            }
        }
    }
}