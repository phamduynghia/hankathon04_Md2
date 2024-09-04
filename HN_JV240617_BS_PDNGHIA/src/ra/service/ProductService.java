package ra.service;

import ra.model.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductService implements IGenericService<Product, String> {
    private List<Product> productList = new ArrayList<>();

    @Override
    public List<Product> getAll() {
        return productList;
    }

    @Override
    public void save(Product product) {
        productList.add(product);
    }

    @Override
    public Product findById(String productId) {
        return productList.stream().filter(p -> p.getProductId().equals(productId)).findFirst().orElse(null);
    }

    @Override
    public void delete(String productId) {
        productList.removeIf(p -> p.getProductId().equals(productId));
    }

    public void sortProductsByPriceDesc() {
        productList.sort(Comparator.comparingDouble(Product::getProductPrice).reversed());
    }

    public List<Product> findByName(String productName) {
        List<Product> result = new ArrayList<>();
        for (Product p : productList) {
            if (p.getProductName().toLowerCase().contains(productName.toLowerCase())) {
                result.add(p);
            }
        }
        return result;
    }
}
