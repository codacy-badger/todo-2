package it.itpas.todo.todoapp.controllers;


import it.itpas.todo.todoapp.models.Item;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@val
@RestController
@RequestMapping("/api/items")
public class ItemController {

    ArrayList<Item> items = new ArrayList<Item>();

    public ItemController() {
        super();
        this.seedItems();
    }


    @GetMapping("/")
    public ResponseEntity<?> getAllItems() {
        try {

            return new ResponseEntity<>(items,HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItem(@PathVariable int id) {

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
     public void seedItems() {
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
    public ResponseEntity<?> createItem (@RequestBody Item item) {

        try {
            System.out.printf("Hai creato l'item: \nId: %d\nNome: %s\nDescrizione: %s\nCompletato: %b\n", item.getId(), item.getName(), item.getDescription(), item.getCompleted());

            items.add(item);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateItem(@PathVariable int id, @RequestBody Item item)
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
