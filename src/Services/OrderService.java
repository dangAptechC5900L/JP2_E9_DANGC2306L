package Services;

import Entity.Customer;
import Entity.Order;
import Entity.OrderDetail;
import Entity.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderService {
    public static List<Customer> customers;
    public static List<Product> products;
    public static List<Order> orders;
    public static List<OrderDetail> orderDetails;

    public OrderService(List<Order> orders){
        this.orders=orders;
    }

    public Optional<Order> getOrderById(String odId){
        return orders.stream()
                .filter(od->od.getId().equals(odId))
                .findFirst();
    }
}
