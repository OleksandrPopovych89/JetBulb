package order;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class OrderService {
    private final Map<Long, Order> orders = new HashMap<>();
    private final Deque<Order> latest = new LinkedList<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();


    public List<Order> findAll() {
        try {
            lock.readLock().lock();
            return new ArrayList<>(orders.values());
        } finally {
            lock.readLock().unlock();
        }
    }

    public List<Order> findLast100() {
        try {
            lock.readLock().lock();
            return new ArrayList<>(latest);
        } finally {
            lock.readLock().unlock();
        }

    }

    public Optional<Order> findById(Long id) {
        try {
            lock.readLock().lock();
            return Optional.ofNullable(orders.get(id));
        } finally {
            lock.readLock().unlock();
        }
    }


    public void add(Order order) {
        try {
            lock.writeLock().lock();
            orders.put(order.getId(), order);
            if (latest.size() == 100) latest.removeFirst();
            latest.add(order);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void remove(Order order) {
        try {
            lock.writeLock().lock();
            orders.remove(order.getId());
            latest.remove(order);
        } finally {
            lock.writeLock().unlock();
        }
    }
}
