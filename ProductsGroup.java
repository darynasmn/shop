

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class ProductsGroup implements Serializable {
    private String name;
    private String description;
    public ArrayList<Product> products;

    public ProductsGroup(String name, String description) {
        this.name = name;
        this.description = description;
        products = new ArrayList<>();
    }

    /**
     * adds new product to the group
     * @param product
     * @return
     */
    public int addProduct(Product product){
        if(find(product)==null){
            product.setGroupName(this.name);
            products.add(product);

            return 1;
        }
        return 0;
    }

    /**
     * removes a certain product
     * @param product
     * @return
     */
    public int removeProduct(Product product){
        if(find(product)==null){
            products.remove(product);
            return 1;
        }
        return 0;

    }

    /**
     * changes the name and description of the group
     * @param newName
     * @param newDescription
     */
    public void editProductsGroup(String newName,String newDescription){
        this.name = newName;
        this.description=newDescription;
    }

    /**
     * the method checks whether product is already added to the group and if is returns it
     * @param product
     * @return
     */
    private Product find(Product product){
        for(Product product1:products){
            if(product1.getName().equals(product.getName()))
                return product1;
        }
        return null;
    }
    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        for(Product product:getProducts())
            product.setGroupName(name);
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return name;
    }
}
