

import java.io.*;
import java.util.ArrayList;

public class Store implements Serializable{
    public ArrayList<ProductsGroup> productsGroups;
    public String name;
    private String pathWhereToSave = "D:\\OneDrive - National University of Kyiv Mohyla Аcademy\\Рабочий стол\\Наукма\\proga";
    public Store(String name) {
        this.name = name;
        this.productsGroups = new ArrayList<>();
    }
    public Store(String name,ArrayList<ProductsGroup> productsGroups) {
        this.name = name;
        this.productsGroups = productsGroups;
    }

    /**
     * creates files with data ab store
     */
    public void saveToFile(){
        try {
            FileOutputStream out = new FileOutputStream("SavedStore");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
            objectOutputStream.writeObject(new Store(this.name,this.productsGroups));
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void exportFromFile(){
        try {
            FileInputStream in = new FileInputStream("SavedStore");
            ObjectInputStream objectOutputStream = new ObjectInputStream(in);
            Store exportedStore = (Store) objectOutputStream.readObject();
            this.name = exportedStore.getName();
            this.productsGroups = exportedStore.getProductsGroups();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveToFile1(){
        createFilesWithProducts();
        try (FileWriter writer = new FileWriter(pathWhereToSave+"/listOfGroups.txt", false)) {
            for (int i = 0; i < productsGroups.size(); i++) {
                String text = productsGroups.get(i).getName();
                writer.write(text);
                writer.append('\n');
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * creates files with products
     */
    private void createFilesWithProducts() {
        for (int i = 0; i < productsGroups.size(); i++) {
            try (FileWriter writer = new FileWriter(pathWhereToSave + "/" + productsGroups.get(i).getName() + ".txt", false)) {
                for (int j = 0; j < productsGroups.get(i).getProducts().size(); j++) {
                    String text = (productsGroups.get(i).getProducts().get(j)).getName();
                    writer.write(text);
                    writer.append('\n');
                }
                writer.flush();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


    /**
     * adds a new product group to the list
     * @param productsGroup
     * @return
     */
    public int addProductGroup(ProductsGroup productsGroup){
        if(find(productsGroup)==null){
            productsGroups.add(productsGroup);
            return 1;
        }
        return 0;
    }

    /**
     * removes an existing product group
     * @param productsGroup
     * @return
     */
    public int remove(ProductsGroup productsGroup){
        if(find(productsGroup)==null){
            productsGroups.remove(productsGroup);
            return 1;
        }
        return 0;
    }
    /**
     * the method checks whether productGroup is already added to the ArrayList of groups and if is returns it
     * @param productsGroup
     * @return
     */
    private ProductsGroup find(ProductsGroup productsGroup){
        for(ProductsGroup productsGroup1:productsGroups){
            if(productsGroup1.getName().equals(productsGroup.getName()))
                return productsGroup1;
        }
        return null;
    }

    public ArrayList<Product> getAllProducts()
    {
        ArrayList<Product>res=new ArrayList<Product>();
        for(ProductsGroup productsGroup:productsGroups)
            for(Product pr:productsGroup.getProducts())
                res.add(pr);
        return res;
    }

    public Product findProduct(String name) {
        Product res = null;
        for (ProductsGroup productsGroup : productsGroups)
            for (Product pr : productsGroup.getProducts()) {
                if (pr.getName().equals(name)) {
                    res = pr;
                    break;
                }
            }
        return res;
    }

    public String[][] getTableData(ArrayList<Product> products)
    {
        String[][] data=new String[products.size()][5];
        for(int i=0;i<products.size();i++)
        {
            data[i]=new String[]{products.get(i).getName(),products.get(i).getProducer(),""+products.get(i).getAmount(),
                    ""+products.get(i).getPrice(),products.get(i).getGroupName()};
        }

        return data;
    }

    public void removeProduct(String name)
    {
        for(ProductsGroup productsGroup:productsGroups)
            for(Product pr:productsGroup.getProducts())
            {
                if(pr.getName().equals(name)) {
                    productsGroup.getProducts().remove(pr);
                    break;
                }
            }
    }

    //getters and setters
    public ArrayList<ProductsGroup> getProductsGroups() {
        return productsGroups;
    }

    public void setProductsGroups(ArrayList<ProductsGroup> productsGroups) {
        this.productsGroups = productsGroups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}


