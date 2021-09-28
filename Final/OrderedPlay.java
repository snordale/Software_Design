package sd_final;

public class OrderedPlay implements PlayOrder {

    private Queue queue;
    private int currentIndex;

    public OrderedPlay(Queue queue, int currentIndex) {
        this.queue = queue;
        this.currentIndex = currentIndex;
    }

    @Override
    public int getNext() {
        return ++currentIndex;
    }

    @Override
    public boolean hasNext() {
        if (currentIndex < queue.size() - 1) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public void setIndex(int newIndex) {
    	currentIndex = newIndex;
    }
}