package Services;

import Entity.Customer;
import Entity.Order;
import Entity.OrderDetail;
import Entity.Product;

import java.util.List;
import java.util.Optional;

public class CustomerService {
    public static List<Customer> customers;
    public static List<Product> products;
    public static List<Order> orders;
    public static List<OrderDetail> orderDetails;

    public CustomerService(List<Customer> customers){
        this.customers=customers;
    }

    public Optional<Customer> getCustomerByID(int cusId){
        return customers.stream()
                .filter(cus->cus.getId()==cusId)
                .findFirst();
    }
}
