package com.zerothoughts.nodes;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class Node {
    String id, task, parent;
    boolean complete;
    ArrayList<String> children;
    int level;
}
