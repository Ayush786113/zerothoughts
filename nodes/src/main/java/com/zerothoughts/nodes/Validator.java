package com.zerothoughts.nodes;

import org.springframework.stereotype.Component;

@Component
public class Validator {
    public boolean validateTask(String task){
        return task != null && task.length() < 1024;
    }
    public boolean validateParent(String parent){
        return parent != null;
    }
    public boolean validateLevel(int level){
        return level > 0;
    }
}
