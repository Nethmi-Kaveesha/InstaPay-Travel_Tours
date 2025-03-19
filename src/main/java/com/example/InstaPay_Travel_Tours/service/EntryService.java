package com.example.InstaPay_Travel_Tours.service;

import com.example.InstaPay_Travel_Tours.model.Entry;
import com.example.InstaPay_Travel_Tours.repo.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EntryService {

    @Autowired
    private EntryRepository entryRepository;

    // Save a new income or expense
    public Entry saveEntry(Entry entry) {
        return entryRepository.save(entry);
    }

    // Get all entries (both income and expense)
    public List<Entry> getAllEntries() {
        return entryRepository.findAll();
    }

    // Update an existing entry
    public Entry updateEntry(Long id, Entry updatedEntry) {
        Entry entry = entryRepository.findById(id).orElseThrow(() -> new RuntimeException("Entry not found"));
        entry.setDescription(updatedEntry.getDescription());
        entry.setAmount(updatedEntry.getAmount());
        entry.setType(updatedEntry.getType());
        return entryRepository.save(entry);
    }

    // Delete an entry
    public void deleteEntry(Long id) {
        entryRepository.deleteById(id);
    }
}
