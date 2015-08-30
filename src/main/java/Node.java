import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author Erik
 */
@Data
@ToString(exclude = {"east","west","north","south"})
@RequiredArgsConstructor
public class Node {
    @NonNull private int x;
    @NonNull private int y;
    @NonNull private boolean obstacle;

    private Node east;
    private Node west;
    private Node north;
    private Node south;

    public double distance(Node other){
        if(this.x == other.getX() || this.getY() == other.getY()){
            return 1;
        }else{
            return Math.sqrt((other.getX()-this.x)*(other.getX()-this.x)+ (other.getY()-this.y)*(other.getY()-this.y));
        }
    }
}
