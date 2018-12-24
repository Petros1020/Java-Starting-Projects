/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.ProjectBoot_Jnpep.websockets;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.Session;
import org.springframework.stereotype.Component;

/**
 *
 * @author karag
 */

public class SessionRegistry {

    private Set<Session> sessions = new HashSet<>();
//Concurrency lock type for singleton beans with container-managed concurrency. 
//Annotate a singleton’s business or timeout method with @Lock(READ) if the method
//can be concurrently accessed, or shared, with many clients. 
//Annotate the business or timeout method with @Lock(WRITE) 
//if the singleton session bean should be locked to other clients while a client is calling that method. 
    //Typically,
//the @Lock(WRITE) annotation is used when clients are 
//modifying the state of the singleton.

    
    public Set<Session> getAll() {
        return Collections.unmodifiableSet(sessions);
    }


    public void add(Session session) {
        sessions.add(session);
    }


    public void remove(Session session) {
        sessions.remove(session);
    }

    public int getPeople() {
        return sessions.size();
    }
}
