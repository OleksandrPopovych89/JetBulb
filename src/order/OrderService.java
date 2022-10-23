package order;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class OrderService {
    private final Map<Long, Order> orders = new ConcurrentHashMap<>();
    private final Deque<Order> latest = new ConcurrentLinkedDeque<>();


    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }

    public List<Order> findLast100() {
        return new ArrayList<>(latest);
    }

    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(orders.get(id));
    }


    public void add(Order order) {
        orders.put(order.getId(), order);
        if (latest.size() == 100) latest.removeLast();
        latest.add(order);
    }

    public void remove(Order order) {
        orders.remove(order.getId());
        latest.remove(order);
    }
}
