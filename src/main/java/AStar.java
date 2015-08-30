/**
 * @author Erik
 */
public class AStar {
    private CartesianSystem system;
    private Node beginning;
    private Node goal;
    public static void main(String[] args){
        CartesianSystem system = new CartesianSystem(new int[][]{
                new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1},
                new int[]{1,0,0,0,0,0,1,0,0,0,0,0,1},
                new int[]{1,0,0,0,0,0,1,0,0,0,0,0,1},
                new int[]{1,0,0,0,0,0,0,0,0,0,0,0,1},
                new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1}
        });
        Node goal = system.getSystem()[1][9];
        Node beginning = system.getSystem()[1][1];
        new AStar().startAStar(system,beginning,goal);
    }

    public void startAStar(CartesianSystem system, Node beginning, Node goal){
        this.system = system;
        this.beginning = beginning;
        this.goal = goal;
        PathContainer container = new PathContainer(goal);
        Path path = new Path(goal);
        path.add(beginning);

        container.add(path);

        while (true){
            if(path.getCurrent().equals(goal)){
                container.getPaths().remove(path);
                printPretty(path);

            }
            if(!(path.getCurrent().getEast().isObstacle() || path.getCurrent().getEast().equals(path.getBefore())) && path.getCurrent().getEast() != null){
                container.add(new Path(path).add(path.getCurrent().getEast()));
            }
            if(!(path.getCurrent().getWest().isObstacle() || path.getCurrent().getWest().equals(path.getBefore())) && path.getCurrent().getWest() != null){
                container.add(new Path(path).add(path.getCurrent().getWest()));
            }
            if(!(path.getCurrent().getNorth().isObstacle() || path.getCurrent().getNorth().equals(path.getBefore())) && path.getCurrent().getNorth() != null){
                container.add(new Path(path).add(path.getCurrent().getNorth()));
            }
            if(!(path.getCurrent().getSouth().isObstacle() || path.getCurrent().getSouth().equals(path.getBefore())) && path.getCurrent().getSouth() != null){
                container.add(new Path(path).add(path.getCurrent().getSouth()));
            }
            container.getPaths().remove(path);
            path = container.getMostPromising();
        }

    }

    public void printPretty(Path path) {
        System.out.println("Goal path found!!!1^111Eleven: " + path);
        char[][] chars = new char[system.getSystem().length][];
        Node[][] cartsystem = system.getSystem();
        for (int x = 0; x < cartsystem.length; x++) {
            char[] systemX = new char[cartsystem[x].length];
            for (int y = 0; y < cartsystem[x].length; y++) {
                Node curr = cartsystem[x][y];
                if (curr.isObstacle()) {
                    systemX[y] = '#';
                } else if(curr.equals(goal)){
                    systemX[y] = 'G';
                } else if(curr.equals(beginning)){
                    systemX[y] = 'S';
                } else {
                    systemX[y] = 'O';
                }
            }
            chars[x] = systemX;
        }
        for(Node node : path.getNodes()){
            chars[node.getX()][node.getY()] = 'X';
        }

        for(char[] nodes : chars){
            for(char charac : nodes){
                System.out.print(charac + " ");
            }
            System.out.print("\n");
        }
    }
}
