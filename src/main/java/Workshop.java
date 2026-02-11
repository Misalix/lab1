import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Workshop <T extends Car> {

    private final int capacity;
    private final List<T> storedCars;

    public Workshop(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be greater than or equal to 0");
        }
        this.capacity = capacity;
        this.storedCars = new ArrayList<>();

    }

    public int getCapacity() {
        return capacity;
    }

    public int size() {
        return storedCars.size();
    }

    public boolean isFull() {
        return storedCars.size() >= capacity;
    }

    public boolean isEmpty() {
        return storedCars.isEmpty();
    }

    public void load(T car) {
        if (car == null) {
            throw new NoSuchElementException("Car must not be null");
        }
        if ((isFull())) {
            throw new NoSuchElementException("Workshop is full!");
        }
        storedCars.add(car);
    }

    public T unload(int index) {
        if (isEmpty()) {
            throw new NoSuchElementException("Workshop is empty!");
        }
        if ( index < 0 || index >= storedCars.size()) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return storedCars.remove(index);

    }
}
