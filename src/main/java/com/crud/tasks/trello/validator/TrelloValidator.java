package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloCard;

import java.util.List;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

public class TrelloValidator {

    private void validateCard(final TrelloCard trelloCard) {
        if (trelloCard.getName().contains("test")) {
            LOGGER.info("Someone is testing my application!");
        } else {
            LOGGER.info("Seems that my application is used in proper way.");
        }
    }
}
