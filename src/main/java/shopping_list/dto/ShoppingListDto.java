package shopping_list.dto;

import shopping_list.model.Item;
import shopping_list.model.ShoppingList;

import java.util.List;

public class ShoppingListDto {

    private ShoppingList shoppingList;

    private List<Item> items;

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
