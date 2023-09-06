import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {
    private Map<String, String> parents;

    public static void main(String[] args) {
        Main main = new Main();
        main.init();
        main.getParent("hi");
    }

    private void init() {
        parents = new HashMap<>();
        parents.put("john", "-");
        parents.put("mary", "-");
        parents.put("edward", "john");
        parents.put("hi", "edward");
    }

    private void getParent(String child) {
        if("-".equals(child)) {
            return;
        }
        System.out.println(child);
        getParent(parents.get(child));
    }
}