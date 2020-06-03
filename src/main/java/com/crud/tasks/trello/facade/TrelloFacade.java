package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.service.TrelloService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

import static java.util.stream.Collectors.toList;

@Component
public class TrelloFacade {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TrelloFacade.class);
    @Autowired
    private TrelloService trelloService;

    @Autowired
    TrelloMapper trelloMapper;

    public List<TrelloBoard> fetchTrelloBoards() {
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloService.fetchTrelloBoards());
        LOGGER.info("Starting filtering boards...");
        List<TrelloBoard> filteredBoards = trelloBoards.stream()
                .filter(trelloBoard -> trelloBoard.getName().equalsIgnoreCase("test"))
                .collect(toList());
        LOGGER.info("Boards have been filtered. Current list size: " + filteredBoards.size());
        return trelloMapper.mapToBoardsDto(filteredBoards);
    }

    public CreatedTrelloCardDto createCard(final TrelloCardDto trelloCardDto) {
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        if (trelloCard.getName().contains("test")) {
            LOGGER.info("Someone is testing my application!");
        } else {
            LOGGER.info("Seems that my application is used in proper way.");
        }
        return trelloService.createdTrelloCard(trelloMapper.mapToCardDto(trelloCard));
    }
}
