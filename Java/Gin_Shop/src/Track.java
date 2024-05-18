import java.util.LinkedList;
import java.util.Queue;

public class Track {
    Queue<Object> queue = new LinkedList<Object>();

    public Object getNext(){
        if(queue.isEmpty()) return null;
        return queue.remove();
    }

    public void add(Box box) {
        queue.add(box);
    }

    public void add(Bottle bottle) {
        queue.add(bottle);
    }
}
