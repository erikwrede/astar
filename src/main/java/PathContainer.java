import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Erik
 */
@Getter
public class PathContainer {
    private List<Path> paths;

    public PathContainer(Node goalNode) {
        this.paths = new ArrayList<>();
    }

    public void add(Path path) {
        paths.add(path);
    }

    public Path getMostPromising(){
        Path mostPromising = null;
        for (Path path : paths) {
            if (mostPromising == null) {
                mostPromising = path;
                continue;
            }
            if (mostPromising.getF() > path.getF()) mostPromising = path;
        }
        return mostPromising;
    }
}
