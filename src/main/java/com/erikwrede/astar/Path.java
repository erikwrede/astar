package com.erikwrede.astar;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Erik
 */
@Getter
@ToString
public class Path {
    //cost start -> current
    private int g = 0;
    //estimated current -> goal
    private double h = 0;

    private Node goal;

    private List<Node> nodes = new ArrayList<>();


    public Path(Node goal) {
        this.goal = goal;
    }

    public Path(Path from) {
        this.nodes = new ArrayList<>(from.getNodes());
        this.goal = from.getGoal();
        this.g = from.getG();
        this.h = from.getH();
    }

    public Node getCurrent() {
        return nodes.get(nodes.size() - 1);
    }

    public Node getBefore() {
        int index = nodes.size() - 2;
        if (index < 0) return getCurrent();
        return nodes.get(index);
    }

    public boolean add(Node n) {
        if (!nodes.contains(n)) {
            nodes.add(n);
            g++;
            h = n.distance(goal);
            return true;
        }
        return false;
    }

    public double getF() {
        return g + h;
    }
}
