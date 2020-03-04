package it.itpas.todo.todoApp.controllers;


import antlr.collections.List;
import it.itpas.todo.todoApp.models.BaseEntity;
import it.itpas.todo.todoApp.models.Item;
import it.itpas.todo.todoApp.viewModels.ItemViewModel;
import lombok.Builder;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@val
@RestController
@RequestMapping("/api/items")
public class ItemController {

    ArrayList<Item> items = new ArrayList<Item>();

    public ItemController() {
        super();
        this.SeedItems();
    }


    @GetMapping("/")
    public ResponseEntity<?> GetAllItems() {
        try {

            return new ResponseEntity<>(items,HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> GetItem(@PathVariable int id) {

        try {

            for (Item item: items) {
                if (item.getId()==id)
                    return new ResponseEntity<>(item,HttpStatus.OK);

            }

            return new ResponseEntity<>("Id non trovato",HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     public void SeedItems() {
        val item= Item.builder()
                .name("TaskSeed1")
                .description("Inizializzazione")
                .completed(true)
                .build();
        item.setId(0);

         val item2= Item.builder()
                 .name("TaskSeed2")
                 .description("Inizializzazione2")
                 .completed(true)
                 .build();
         item2.setId(1);

         val item3= Item.builder()
                 .name("TaskSeed3")
                 .description("Inizializzazione3")
                 .completed(true)
                 .build();
         item3.setId(2);

        items.add(item);
        items.add(item2);
        items.add(item3);

    }

    @PostMapping("/")
    public ResponseEntity<?> CreateItem (@RequestBody Item item) {

        try {
            System.out.printf("Hai creato l'item: \nId: %d\nNome: %s\nDescrizione: %s\nCompletato: %b\n", item.getId(), item.getName(), item.getDescription(), item.getCompleted());

            items.add(item);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> UpdateItem(@PathVariable int id, @RequestBody Item item)
    {
        try
        {
            val itemUpdated = getItemById(id);
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
        Item itemFound = null;

        for (Item item:items)
        {
            if(item.getId().compareTo(id) == 0) {
                itemFound = item;
            }
        }

        return itemFound;
    }

}
