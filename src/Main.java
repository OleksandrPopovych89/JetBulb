import order.Order;
import order.OrderService;

public class Main {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();

        for (int i = 1; i <= 1500; i++) {
            orderService.add(new Order(i));
        }
        orderService.remove(new Order(1));
        orderService.remove(new Order(2));
        orderService.remove(new Order(3));
        System.out.println(orderService.findAll());
        System.out.println(orderService.findLast100());
    }
}