import java.util.ArrayList;

public class Main {
    static Store store = new Store("STORE");

    public static void main(String[] args) {

        Product cosmetics1 = new Product("Помада", "Maybelline ", 15, 300, "Червона ", "Косметика");
        Product cosmetics2 = new Product("Пудра", "Catrice", 30, 500, "Для найсвітлішої шкіри", "Косметика");
        Product cosmetics3 = new Product("Тіні", "Dior", 54, 1500, "Блакитні", "Косметика");


        Product goodsForHome1 = new Product("Рушники", "Auera", 40, 140, "З бавовни", "Товари для дому ");
        Product goodsForHome2 = new Product("Аромо свічки", "Woodwick", 18, 198, "Аромат ванілі", "Товари для дому");
        Product goodsForHome3 = new Product("Постільна білизна", "Ярослав", 35, 850, "Для двухспального ліжка", "Товари для дому");

        Product householdChemicals1 = new Product("Порошок", "Delamark", 78, 642, "Для дитячого одягу", "Побутова хімія");
        Product householdChemicals2 = new Product("Зубна щітка", "Oral-B", 62, 80, "Рожева", "Побутова хімія");
        Product householdChemicals3 = new Product("Серветки", "Ecolo", 12, 15,"Паперові", "Побутова хімія");


        ProductsGroup cosmetics = new ProductsGroup("Косметика", "");
        ProductsGroup goodsForHome = new ProductsGroup("Товари для дому", "");
        ProductsGroup householdChemicals = new ProductsGroup("Побутова хімія", "");
        cosmetics.addProduct(cosmetics1);
        cosmetics.addProduct(cosmetics2);
        cosmetics.addProduct(cosmetics3);
        goodsForHome.addProduct(goodsForHome1);
        goodsForHome.addProduct(goodsForHome2);
        goodsForHome.addProduct(goodsForHome3);
        householdChemicals.addProduct(householdChemicals1);
        householdChemicals.addProduct(householdChemicals2);
        householdChemicals.addProduct(householdChemicals3);

        store.addProductGroup(cosmetics);
        store.addProductGroup(goodsForHome);
        store.addProductGroup(householdChemicals);
        store.saveToFile();
        store.exportFromFile();
        System.out.println(store.name);
        String res = "";
        for(Product product1 : store.getAllProducts())
        {
            res+=product1.getName()+"\n";
        }
        System.out.println(res);
 //       new MainForm(store);
    }}

