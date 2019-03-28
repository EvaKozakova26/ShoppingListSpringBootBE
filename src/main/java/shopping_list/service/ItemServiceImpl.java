package shopping_list.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopping_list.model.Item;
import shopping_list.model.ShoppingList;
import shopping_list.repository.ItemRepository;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ItemServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    public Item saveItem(Item item) {
        logger.debug("getting items");
        item.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        itemRepository.save(item);
        return item;
    }

    @Transactional
    public List<Item> findItems(ShoppingList shoppingList) {
        return itemRepository.findAllByShoppingListId(shoppingList.getId());
    }

    @Transactional
    public void removeItem(Item item) {
        itemRepository.delete(item);
    }

    @Transactional
    public Item findById(Item item) {
        return itemRepository.findById(item.getId());
    }

    @Transactional
    public void changeState(Item item) {
        Item editedItem = itemRepository.findById(item.getId());
        if (editedItem != null) {
            editedItem.setState(item.getState());
            itemRepository.save(editedItem);
        }
    }

}
