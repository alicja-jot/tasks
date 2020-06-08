package com.crud.tasks.service;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.LinkedList;
import java.util.List;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class TrelloServiceTest {

    @Autowired
    private TrelloService trelloService;

    @MockBean
    private TrelloClient trelloClient;

    @Test
    void fetchTrelloBoards() {
        // given
        List<TrelloBoardDto> list = new LinkedList<>();
        TrelloBoardDto trelloBoard = new TrelloBoardDto();
        list.add(trelloBoard);
        when(trelloClient.getTrelloBoards()).thenReturn(list);

        // when
        List<TrelloBoardDto> result = trelloService.fetchTrelloBoards();

        // then
        assertEquals(list.size(), result.size());
    }

    @Test
    void createdTrelloCard() {
        // given
        TrelloCardDto dto = new TrelloCardDto();
        CreatedTrelloCardDto card = new CreatedTrelloCardDto("1", "A", "localhost:8080/cards/1", new Badges(1, new AttachmentByType(new Trello(1, 1))));
        when(trelloClient.createNewCard(dto)).thenReturn(card);

        // when
        CreatedTrelloCardDto result = trelloService.createdTrelloCard(dto);

        // then
        assertEquals(card.getId(), result.getId());
        assertEquals(card.getName(), result.getName());
        assertEquals(card.getShortUrl(), result.getShortUrl());
        assertEquals(card.getBadges().getVotes(), result.getBadges().getVotes());
        assertEquals(card.getBadges().getAttachments().getTrello().getCard(), result.getBadges().getAttachments().getTrello().getCard());
        assertEquals(card.getBadges().getAttachments().getTrello().getBoard(), result.getBadges().getAttachments().getTrello().getBoard());
    }
}