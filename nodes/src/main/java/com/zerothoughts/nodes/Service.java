package com.zerothoughts.nodes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.LinkedList;
import java.util.Map;

@org.springframework.stereotype.Service
public class Service implements Trigger{
    @Autowired
    private Appwrite appwrite;
    @Autowired
    private Validator validator;
    private Object object;
    synchronized public Object createNode(Node node) throws Exception{
        if(!validator.validateTask(node.getTask()))
            throw new IllegalArgumentException("Task cannot be null and should be less than 1024 characters!");
        else if(!validator.validateParent(node.getParent()))
            throw new IllegalArgumentException("Parent cannot be null!");
        else if(!validator.validateLevel(node.getLevel()))
            throw new IllegalArgumentException("Level must be greater than 0!");
        appwrite.createNode(this, node);
        wait();
        return this.object;
    }
    synchronized public Object getNode(String id) throws Exception{
        appwrite.getNode(this, id);
        wait();
        return this.object;
    }
    synchronized public Object getNodes(String [] ids) throws Exception{
        LinkedList<Object> nodesList = new LinkedList<>();
        for(String id : ids){
            try{
                appwrite.getNode(this, id);
            } catch (Exception exception){
                throw new Exception(id+": "+exception.getMessage());
            }
            wait();
            nodesList.add(this.object);
        }
        return nodesList;
    }
    synchronized public Object updateNode(String id, Map<String, Object> update) throws Exception{
        appwrite.updateNode(this, id, update);
        wait();
        return this.object;
    }
    @Override
    synchronized public void transferObject(Object object) {
        this.object = object;
        notify();
    }
}
