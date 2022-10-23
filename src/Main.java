import order.Order;
import order.OrderService;

public class Main {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();

        for (int i = 1; i <= 150; i++) {
            orderService.add(new Order(i));
        }
        System.out.println(orderService.findAll());
        System.out.println(orderService.findLast100());
    }

}