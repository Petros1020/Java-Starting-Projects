/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.ProjectBoot_Jnpep.controllers;

import com.bootcamp.ProjectBoot_Jnpep.websockets.SessionRegistry;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat/{activityId}")
public class WsChatEndPoint {

    private SessionRegistry sessionRegistry;

    @OnMessage
    public void onMessage(String message, Session sender) throws IOException {
        System.out.println(message);
        final ObjectMapper mapper = new ObjectMapper();
        final JsonNode messagejson = mapper.readTree(message);
        String name = messagejson.get("name").asText();
        String text = messagejson.get("text").asText();
        String date = messagejson.get("date").asText();
        Set<Session> sessions = sessionRegistry.getAll();
        for (Session s : sessions) {
            s.getBasicRemote().sendText(date+": " + name + "--->" + text);
        }
    }

    @OnOpen
    public void open(Session session, EndpointConfig conf,@PathParam("activityId")Integer activityId) throws IOException {
//       String activityString=(String) request.getAttribute("activityId");
//       Integer activityId=Integer.parseInt(activityString);
//         System.out.println(activityId);
//        ActivityController activityController=new  ActivityController();
//        activityController.getChat(activityId);
        sessionRegistry = new SessionRegistry();
        Map<String, String> params = session.getPathParameters();
        sessionRegistry.add(session);
        session.getBasicRemote().sendText("People in chat " + sessionRegistry.getPeople());
    }

    @OnClose
    public void close(Session session) {
        sessionRegistry.remove(session);
    }

}
