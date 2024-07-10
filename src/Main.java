import Entity.*;
import Services.CustomerService;
import Services.OrderDetailService;
import Services.OrderService;
import Services.ProductService;
import Validate.Validation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        int cusId,ordId,quantity;
        String orderId,cusName,productID;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Customer> customers = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        List<OrderDetail> orderDetails = new ArrayList<>();

        CustomerService cs = new CustomerService(customers);
        OrderDetailService ord = new OrderDetailService(orderDetails);
        OrderService od = new OrderService(orders);
        ProductService ps = new ProductService(products);

        products.add(new Product("MS000001", "Suface 15", 1000));
        products.add(new Product("NE000002", "NokiaX",2000));
        products.add(new Product("SE000003", "Samsumg Galaxy",1200));
        products.add(new Product("SE000004", "Iphone 15promax",2000));

        customers.add(new Customer(1, "Bill Gate"));
        customers.add(new Customer(2, "Mark Zuckerburg"));
        customers.add(new Customer(3, "Elon Musk"));
        customers.add(new Customer(4, "Steve Jobs"));

        orders.add(new Order("ORDPM00000001", customers.get(0), LocalDateTime.now()));
        orders.add(new Order("ORDPM00000002", customers.get(1), LocalDateTime.now()));
        orders.add(new Order("ORDPM00000003", customers.get(2), LocalDateTime.now()));
        orders.add(new Order("ORDPM00000004", customers.get(3), LocalDateTime.now()));

        orderDetails.add(new OrderDetail(1, orders.get(0),products.get(1), 250, EStatus.PE));
        orderDetails.add(new OrderDetail(2, orders.get(3),products.get(0), 100, EStatus.CO));
        orderDetails.add(new OrderDetail(3, orders.get(1),products.get(2), 100, EStatus.PA));
        orderDetails.add(new OrderDetail(4, orders.get(2),products.get(3), 100, EStatus.CA));

        CustomerService.customers=customers;
        ProductService.products=products;
        OrderService.orders=orders;
        OrderDetailService.orderDetails=orderDetails;

          try {
              System.out.println("Enter Order Id: ");
              orderId=br.readLine();
              if(!Validation.isIdOrder(orderId)){
                  System.out.println("Thread 1: OrderId not correct format");
              }else {
                  System.out.println("Enter Customer ID: ");
                  cusId=Integer.parseInt(br.readLine());
                  Optional<Customer> customerOpt = cs.getCustomerByID(cusId);
                  if (!customerOpt.isPresent()) {
                      System.out.println("Cannot find ID");
                  }else{
                      Customer customer = customerOpt.get();
                      System.out.println("Enter Customer name:");
                      cusName=br.readLine();
                      if(!Validation.isNameCustomer(cusName)) {
                          System.out.println("Customer Name not correct format");
                      }else {
                          System.out.println("Enter datetime (yyyy-MM-dd HH:mm): ");
                          String dateTimeStr = br.readLine();
                          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                          LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);

                          // Add the order
                          Order newOrder = new Order(orderId, customer, dateTime);
                          orders.add(newOrder);
                          System.out.println("Order added: " + newOrder);
                          System.out.println(orders);

                          System.out.println("Adding OrderDetail");
                          System.out.println("Enter Product ID: ");
                          productID= br.readLine();
                          Optional<Product> productOpt=ps.getProductById(productID);
                          if(!productOpt.isPresent()){
                              System.out.println("Cannot find ProductId");
                          }else {
                              Product product=productOpt.get();
                              System.out.println("Enter quantity: ");
                              quantity=Integer.parseInt(br.readLine());
                              if(quantity>product.getQuantity()){
                                  System.out.println("Not enough inventory");
                              }else {
                                  OrderDetail newOrderDetail=new OrderDetail(orderDetails.size()+1,newOrder,product,quantity,EStatus.PE);
                                  orderDetails.add(newOrderDetail);
                                  System.out.println("OrderDetail Added: "+newOrderDetail);
                                  System.out.println("==================================");
                                  System.out.println("OrderDetail LIST: "+orderDetails);

                                  //Update Quantity
                                  System.out.println("Enter OrderDetail ID to update: ");
                                  ordId=Integer.parseInt(br.readLine());
                                  Optional<OrderDetail> orderDetailOpt=ord.getOrderDetailById(ordId);
                                  if(!orderDetailOpt.isPresent()){
                                      System.out.println("Cannot find orderDetail ID!");
                                  }else {
                                      OrderDetail orderDetail= orderDetailOpt.get();
                                      //Lấy ra ID tương ứng
                                      productID= orderDetail.getProduct().getId();
                                      Optional<Product> productOptional=ps.getProductById(productID);
                                      if(productOptional.isPresent()){
                                          Product pduct= productOptional.get();
                                          product.setQuantity(product.getQuantity()-newOrderDetail.getQuantity());
                                          System.out.println("Inventory updated for Product: " + pduct.getName() + " Quantity: " + pduct.getQuantity());
                                      }
                                  }
                              }
                          }

//                          System.out.println("Enter OrderDetail: ");
//                          ordId=Integer.parseInt(br.readLine());
//                          if(ord.getOrderDetailById(ordId)==null){
//                              System.out.println();
//                          }

                      }
                  }
              }

          }catch (Exception e){
              System.out.println(e.getMessage());
          }

    }
}
