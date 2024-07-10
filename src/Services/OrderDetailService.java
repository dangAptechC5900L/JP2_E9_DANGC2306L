package Services;

import Entity.Customer;
import Entity.Order;
import Entity.OrderDetail;
import Entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDetailService {
    public static List<Customer> customers;
    public static List<Product> products;
    public static List<Order> orders;
    public static List<OrderDetail> orderDetails;

    public OrderDetailService(List<OrderDetail> orderDetails){
        this.orderDetails=orderDetails;
    }

    public Optional<OrderDetail> getOrderDetailById(int ordId){
        return orderDetails.stream()
                .filter(ord->ord.getId()==ordId)
                .findFirst();
    }


}
