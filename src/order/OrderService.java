package order;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class OrderService {
    private List<Order> orders = new ArrayList<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public List<Order> findAll() {
        try {
            lock.readLock().lock();
            return orders;
        } finally {
            lock.readLock().unlock();
        }
    }

    public List<Order> findLast100() {

        try {
            lock.readLock().lock();
            return orders.stream()
                    .sorted(Comparator.comparingLong(Order::getId))
                    .limit(100)
                    .toList();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void add(Order order) {
        try {
            lock.readLock().lock();
            orders.add(order);
        } finally {
            lock.readLock().unlock();
        }

    }

    public void remove(Order order) {
        try {
            lock.readLock().lock();
            orders.remove(order);
        } finally {
            lock.readLock().unlock();
        }
    }
}
