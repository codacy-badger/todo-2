package it.itpas.todo.todoApp.controllers;


import it.itpas.todo.todoApp.models.Item;
import it.itpas.todo.todoApp.viewModels.ItemViewModel;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@val
@RestController
@RequestMapping("/api/items")
public class ItemController {

    @GetMapping("/")
    public ResponseEntity<?> GetAllItems() {
        try {
            val item = new Item("t1","ciao");
            val item2 = new Item("t2","ciaociao");
            val item3 =new Item("t3","ciao3");

            val items = new ArrayList<Item>();
            items.add(item);
            items.add(item2);
            items.add(item3);

            return new ResponseEntity<>(items,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> GetItem(@PathVariable int id) {

        try {
            if (id < 1) throw new Exception();
            val item = new Item("ciao", "cicocioais");
            item.setId(id);

            return new ResponseEntity<>(item,HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> UpdateItem(@PathVariable int id, @RequestBody Item item)
    {
        try
        {
            val itemUpdated = getItemById(id);
            //val mock = new Item();
            itemUpdated.setId(item.getId());
            itemUpdated.setDescription(item.getDescription());
            itemUpdated.setName(item.getName());
            return new ResponseEntity<>(itemUpdated,HttpStatus.OK);
        }
        catch (NullPointerException exNull)
        {
            return new ResponseEntity<>(exNull.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Item getItemById(int id)
    {
        val item = new Item();

        item.setId(id);
        item.setName("hello");
        item.setDescription("saluto");
        item.setCompleted(false);

        return item;
    }
}
