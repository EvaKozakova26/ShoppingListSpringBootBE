package shopping_list.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopping_list.Model.Item;
import shopping_list.Model.ShoppingList;
import shopping_list.Repository.ItemRepository;

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
    public Optional<Item> findItem(Item item) {
        return itemRepository.findItem(item);
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
