package Services;

import Entity.Product;
import java.util.List;
import java.util.Optional;

public class ProductService {
    public static List<Product> products;

    public ProductService(List<Product> products){
        this.products=products;
    }

    public Optional<Product> getProductById(String pid){
        return products.stream()
               .filter(p->p.getId().equals(pid))
               .findFirst();
    }
}
