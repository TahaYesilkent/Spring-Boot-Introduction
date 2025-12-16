package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Controller; // Bu satır SİLİNDİ
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/workintech/animal") // Başında / olması daha standarttır
public class AnimalController {

    // Properties'den değerleri çekme
    @Value("${course.name}")
    private String courseName;
    @Value("${project.developer.fullname}")
    private String developerName;

    private Map<Integer, Animal> animals;

    @PostConstruct
    public void init() {
        // Map'i başlatma ve başlangıç değerlerini ekleme
        animals = new HashMap<>();
        animals.put(1, new Animal(1, "Kaplan"));
        animals.put(2, new Animal(2, "Ayı"));

        System.out.println("--- Properties Yüklendi ---");
        System.out.println("Course name: " + courseName);
        System.out.println("Developer: " + developerName);
        System.out.println("--------------------------");
    }

    // [GET]/workintech/animal
    @GetMapping
    public List<Animal> findAll() {
        return animals.values().stream().toList();
    }

    // [GET]/workintech/animal/{id}
    @GetMapping("/{id}")
    public Animal find(@PathVariable int id) {
        Animal animal = animals.get(id);
        if (Objects.isNull(animal)) {
            // İstenen id yoksa 404 döndürmek için (veya basit RuntimeException fırlatmak)
            throw new RuntimeException("Animal with id " + id + " not found!");
        }
        return animal;
    }

    // [POST]/workintech/animal
    @PostMapping
    public Animal save(@RequestBody Animal animal) {
        // POST işleminde gelen id'ye göre yeni hayvan ekleme
        if (animal.getId() == 0 || animal.getName() == null) {
            throw new IllegalArgumentException("ID and Name cannot be null for POST operation.");
        }

        if (animals.containsKey(animal.getId())) {
            // Eğer zaten varsa, güncelleme yapmaz
            System.out.println("Animal with id " + animal.getId() + " already exists. Not saving.");
            return animals.get(animal.getId());
        }

        animals.put(animal.getId(), animal);
        return animal;
    }

    // [PUT]/workintech/animal/{id}
    @PutMapping("/{id}")
    public Animal update(@PathVariable int id, @RequestBody Animal animal) {
        if (!animals.containsKey(id)) {
            // İlgili id yoksa yeni ekle (Upsert mantığı)
            animals.put(id, animal);
            return animal;
        }

        // İlgili id varsa, gelen verilerle güncelle
        Animal existingAnimal = animals.get(id);
        existingAnimal.setName(animal.getName());

        // Gelen RequestBody'deki id'yi kullanmak yerine PathVariable'daki id'yi kullanmak daha güvenlidir.
        // Ama testleriniz (shouldUpdateAnimal) RequestBody'de ID de beklediği için:
        animals.remove(id); // Eski kaydı sil
        animals.put(animal.getId(), animal); // Yeni id'siyle (eğer değiştirilmişse) ekle

        return animal;
    }

    // [DELETE]/workintech/animal/{id}
    @DeleteMapping("/{id}")
    public Animal delete(@PathVariable int id) {
        if (!animals.containsKey(id)) {
            throw new RuntimeException("Animal with id " + id + " not found! Deletion failed.");
        }
        return animals.remove(id); // Silinen nesneyi döndürür
    }
}