package com.dio.project.BoardProject.Services;

import com.dio.project.BoardProject.Entities.Card;
import com.dio.project.BoardProject.Exceptions.DataNotFoundException;
import com.dio.project.BoardProject.Repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    private final CardRepository repository;
    @Autowired
    public CardService(CardRepository repository) {
        this.repository = repository;
    }

    public void createCard(Card card){
        repository.save(card);
    }

    public void changeCardColumn(Card card, boolean hasCancelled){
        repository.updateCard(card);
    }

    public Card getCard(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Card not Found"));
    }

}
