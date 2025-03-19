package com.example.InstaPay_Travel_Tours.controller;

import com.example.InstaPay_Travel_Tours.model.Entry;
import com.example.InstaPay_Travel_Tours.model.EntryType;
import com.example.InstaPay_Travel_Tours.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/income")
@CrossOrigin(origins = "http://localhost:63342")
public class EntryController {

    @Autowired
    private EntryService entryService;

    // Save an income or expense entry
    @PostMapping("/{type}/save")
    public Entry saveEntry(@PathVariable("type") String type, @RequestBody Entry entry) {
        entry.setType(EntryType.valueOf(type.toUpperCase()));
        return entryService.saveEntry(entry);
    }

    // Get all entries (both incomes and expenses)
    @GetMapping("/entries")
    public List<Entry> getAllEntries() {
        return entryService.getAllEntries();
    }

    // Update an existing entry
    @PutMapping("/{type}/update/{id}")
    public Entry updateEntry(@PathVariable("id") Long id, @PathVariable("type") String type, @RequestBody Entry updatedEntry) {
        updatedEntry.setType(EntryType.valueOf(type.toUpperCase()));
        return entryService.updateEntry(id, updatedEntry);
    }

    // Delete an entry
    @DeleteMapping("/entries/delete/{id}")
    public void deleteEntry(@PathVariable("id") Long id) {
        entryService.deleteEntry(id);
    }
}
