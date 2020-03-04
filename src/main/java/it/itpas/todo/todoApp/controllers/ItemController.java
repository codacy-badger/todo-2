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

        items.add(item);

    }

    @PostMapping("/")
    public ResponseEntity<?> CreateItem (@RequestBody Item item) {

        try {
            System.out.printf("Hai creato l'item: \nId: %d\nNome: %s\nDescrizione: %s\nCompletato: %b\n", item.getId(), item.getName(), item.getDescription(), item.getCompleted());

            items.add(item);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.I_AM_A_TEAPOT);
        }
        return new ResponseEntity<>(item, HttpStatus.OK);


    }
}
