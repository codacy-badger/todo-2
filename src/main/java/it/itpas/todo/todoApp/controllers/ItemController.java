package it.itpas.todo.todoApp.controllers;


import com.fasterxml.jackson.annotation.JsonAlias;
import it.itpas.todo.todoApp.models.BaseEntity;
import it.itpas.todo.todoApp.models.Item;
import it.itpas.todo.todoApp.viewModels.ItemViewModel;
import lombok.val;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

@val
@RestController
@RequestMapping("/api/items")
public class ItemController {

    @GetMapping("/")
    public ResponseEntity<?> GetAllItems() {
        try {

            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("item.json"));
            JSONArray jsonArray = (JSONArray) obj;
            val items = new ArrayList<BaseEntity>();

            jsonArray.forEach(array -> {
                JSONObject jsonObj = (JSONObject) array;
                BaseEntity item = new Item(jsonObj.getAsString("nome"),jsonObj.getAsString("description"));
                items.add(item);
            });

            return new ResponseEntity<>(items, HttpStatus.OK);




        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{nome}")
    public ResponseEntity<?> GetItem(@PathVariable String nome) {

        try {

            System.out.println(nome);
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("item.json"));
            JSONArray jsonArray = (JSONArray) obj;
            val items = new ArrayList<BaseEntity>();

            jsonArray.forEach(array -> {
                JSONObject jsonObj = (JSONObject) array;
                if (jsonObj.getAsString("nome").compareTo(nome)==0) {
                    BaseEntity item = new Item(jsonObj.getAsString("nome"), jsonObj.getAsString("description"));
                    items.add(item);
                }

            });

            return  new ResponseEntity<>(items, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }


    }

    @PostMapping("/")
    public ResponseEntity<?> postItem (@RequestBody  JSONObject item) {

        try {
            File file = new File("item.json");
            FileWriter fw = new FileWriter(file, true);
            fw.write(item.toJSONString());

        } catch (IOException e) {
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(item,HttpStatus.OK);


    }
}
