package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class TrelloMapperTestSuite {

    private TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void mapToBoardsTest() {
        // Given
        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        ArrayList<TrelloListDto> lists = new ArrayList<>();
        lists.add(new TrelloListDto("2", "bb", true));
        trelloBoardDto.add(new TrelloBoardDto("1", "a", lists));

        // When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDto);

        // Then
        assertEquals(1, trelloBoards.size());
        TrelloBoard trelloBoard = trelloBoards.get(0);
        assertTrue(trelloBoard.getId() == "1");
        assertTrue(trelloBoard.getName() == "a");
        TrelloList trelloList = trelloBoard.getLists().get(0);
        assertTrue(trelloList.getId() == "2");
        assertTrue(trelloList.getName() == "bb");
        assertTrue(trelloList.isClosed() == true);
    }

    @Test
    public void mapToBoardsDtoTest() {
        //given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        List<TrelloList> lists = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "a", lists));
        lists.add(new TrelloList("2", "b", true));

        //when
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);

        //then
        assertTrue(trelloBoardDtos.size() == 1);
        TrelloBoardDto trelloBoardDto = trelloBoardDtos.get(0);
        assertTrue(trelloBoardDto.getId() == "1");
        assertTrue(trelloBoardDto.getName() == "a");
        TrelloListDto trelloListDto = trelloBoardDto.getLists().get(0);
        assertTrue(trelloListDto.isClosed() == true);
        assertTrue(trelloListDto.getName() == "b");
        assertTrue(trelloListDto.getId() == "2");
    }

    @Test
    public void mapToList() {
        //given
        List<TrelloListDto> listDto = new ArrayList<>();
        listDto.add(new TrelloListDto("2", "b", true));

        //when
        List<TrelloList> trelloLists = trelloMapper.mapToList(listDto);
        //then
        assertTrue(trelloLists.size() == 1);
        TrelloList trelloList = trelloLists.get(0);
        assertTrue(trelloList.getName() == "b");
        assertTrue(trelloList.getId() == "2");
        assertTrue(trelloList.isClosed() == true);

    }

    @Test
    public void mapToListDto() {
        //given
        List<TrelloList> lists = new ArrayList<>();
        lists.add(new TrelloList("4", "d", true));

        //when
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(lists);

        //then
        assertTrue(trelloListDtos.size() == 1);
        TrelloListDto trelloListDto = trelloListDtos.get(0);
        assertTrue(trelloListDto.getId() == "4");
        assertTrue(trelloListDto.getName() == "d");
        assertTrue(trelloListDto.isClosed() == true);
    }

    @Test
    public void mapToCardDto() {
        //given
        TrelloCard trelloCard = new TrelloCard("1", "bla bla", "g", "1");
        //when
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //then
        assertTrue(trelloCardDto.getName() == "1");
        assertTrue(trelloCardDto.getDescription() == "bla bla");
        assertTrue(trelloCardDto.getPos() == "g");
        assertTrue(trelloCardDto.getListId() == "1");

    }

    @Test
    public void mapToCard() {
        //given
        TrelloCardDto trelloCardDto = new TrelloCardDto("5", "xx", "v", "2");

        //when
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //then
        assertTrue(trelloCard.getName() == "5");
        assertTrue(trelloCard.getDescription() == "xx");
        assertTrue(trelloCard.getPos() == "v");
        assertTrue(trelloCard.getListId() == "2");
    }


}