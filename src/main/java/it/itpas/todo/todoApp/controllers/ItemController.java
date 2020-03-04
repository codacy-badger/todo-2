package it.itpas.todo.todoApp.controllers;


import antlr.collections.List;
import it.itpas.todo.todoApp.models.Item;
import it.itpas.todo.todoApp.viewModels.ItemViewModel;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@val
@RestController
@RequestMapping("/api/items")
public class ItemController {

    ArrayList<Item> itens = new ArrayList<Item>();
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
    public void SeedItems() {
        val item = new Item("t1","ciao");
        val item2 = new Item("t2","ciaociao");
        val item3 =new Item("t3","ciao3");

        items.add(item);
        items.add(item2);
        items.add(item3);

    }
}
