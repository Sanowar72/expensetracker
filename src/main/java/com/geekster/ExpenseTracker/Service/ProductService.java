package com.geekster.ExpenseTracker.Service;

import com.geekster.ExpenseTracker.model.Product;
import com.geekster.ExpenseTracker.model.User;
import com.geekster.ExpenseTracker.dao.ProductDao;
import com.geekster.ExpenseTracker.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductDao repository;
    @Autowired
    UserDao userRepository;



    public String createProduct(Product product) { // user must be logged in
        for(User user: UserService.loggedInUsers)
            if(user.getUserId().equals(product.getUser().getUserId()))
                return "Product with title " +
                        repository.save(product).getProductTitle() + " saved!";
        return "user not loggedIn";
    }

    public List<Product> getProducts(Integer userId) {
        List<Product> products = null;
        if(userRepository.findById(userId).isPresent())
            products = repository.getAllProduct(userId);
        return products;
    }

    public String deleteProducts(Integer userId, Integer productId){
        String response =  null;
        if(userRepository.findById(userId).isPresent()){
            response = "";
            List<Product> products = repository.getAllProduct(userId);
            for (Product product : products){
                if(repository.findById(productId).isPresent()){
                    response = "Product with productId " +
                            repository.findById(productId).get().getProductTitle() + " Deleted!";
                    repository.delete(product);
                }
            }
        }
        return response;
    }

    public String updateProducts(Product newProduct, Integer userId, Integer productId) {
        String response =  null;
        if(userRepository.findById(userId).isPresent()){
            response = "";
            List<Product> products = repository.getAllProduct(userId);
            for (Product product : products){
                if(repository.findById(productId).isPresent()){
                    Product oldProduct = repository.findById(productId).get();
                    if(newProduct.getProductTitle() != null) oldProduct.setProductTitle(newProduct.getProductTitle());
                    if(newProduct.getProductDescription() != null) oldProduct.setProductDescription(newProduct.getProductDescription());
                    if(newProduct.getProductPrice() != null) oldProduct.setProductPrice(newProduct.getProductPrice());
                    if(newProduct.getLocalDate() != null) oldProduct.setLocalDate(newProduct.getLocalDate());
                    response = "Product with productId " +
                            repository.findById(productId).get().getProductTitle() + " Updated!";
                    repository.save(oldProduct);
                }
            }
        }
        return response;
    }

}
