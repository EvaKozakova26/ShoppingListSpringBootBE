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
import java.util.Optional;

@Service
public class ItemService {
    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);


    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    public Item saveItem(Item item) {
        logger.debug("getting items");
        item.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        itemRepository.save(item);
        return item;
    }

    @Transactional
    public List<Item> findItems(ShoppingList shoppingList) {

        return itemRepository.findAllByListId(shoppingList);
    }

    @Transactional
    public void removeItem(Item item) {
        itemRepository.removeItem(item);
    }

    @Transactional
    public Optional<Item> findById(Item item) {
        return itemRepository.findById(item.getId());
    }

    @Transactional
    public void changeState(Item item) {
        Optional<Item> editedItem = itemRepository.findById(item.getId());
        if (editedItem.isPresent()) {
            editedItem.get().setState(item.getState());
            itemRepository.changeState(editedItem.get());
        }
    }

}
