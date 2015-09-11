package com.erikwrede.astar;

import lombok.Getter;

/**
 * @author Erik
 */
@Getter
public class CartesianSystem {
    private int lengthX;
    private int lengthY;
    private Node[][] system;

    //1 = obstacle; 0 = ok
    public CartesianSystem(int[][] nodes) {
        parseNodes(nodes);
    }

    public void parseNodes(int[][] obstacleMap) {
        system = new Node[obstacleMap.length][];
        System.out.println(obstacleMap.length);
        for (int x = 0; x < obstacleMap.length; x++) {
            Node[] systemX = new Node[obstacleMap[x].length];
            for (int y = 0; y < obstacleMap[x].length; y++) {
                systemX[y] = new Node(x, y, (obstacleMap[x][y] == 1));
            }
            system[x] = systemX;
        }
        for (Node[] narray : system) {
            for (Node node : narray) {
                node.setEast(get(node.getX() + 1, node.getY()));
                node.setWest(get(node.getX() - 1, node.getY()));
                node.setNorth(get(node.getX(), node.getY() + 1));
                node.setSouth(get(node.getX() - 1, node.getY() - 1));
            }
        }
    }

    public Node get(int x, int y) {
        try {
            if (x <= 0 || y <= 0) {
                return new Node(x, y, true);
            } else if (x >= system.length) {
                return new Node(x, y, true);
            } else if (y >= system[x].length) {
                return new Node(x, y, true);
            } else {
                return system[x][y];
            }
        } catch (ArrayIndexOutOfBoundsException never) {
            System.err.println("OutOfBoundsException while getting System Node, should never ever occur");
            never.printStackTrace();
            return new Node(x,y,true);
        }
    }
}
