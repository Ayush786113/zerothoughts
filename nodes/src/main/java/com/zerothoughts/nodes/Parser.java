package com.zerothoughts.nodes;

import io.appwrite.models.Document;

import java.util.ArrayList;
import java.util.Map;

public class Parser {
    public Node parseNode(Document<Map<String, Object>> response){
        Node node = new Node();
        Map<String, Object> data = response.getData();
        node.setId(response.getId());
        node.setTask(String.valueOf(data.get("task")));
        node.setParent(String.valueOf(data.get("parent")));
        node.setChildren((ArrayList<String>) data.get("children"));
        node.setComplete(Boolean.parseBoolean(String.valueOf(data.get("complete"))));
        node.setLevel((int) Double.parseDouble(String.valueOf(data.get("level"))));
        return node;
    }
}
