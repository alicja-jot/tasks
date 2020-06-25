package com.crud.tasks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Value("${admin.name}")
    private String adminName;

    @Value("${info.app.company.name}")
    private String companyName;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("welcome", "Hello " + adminName);
        context.setVariable("goodbye", "Goodbye " + adminName);
        context.setVariable("tasks_url", "http://localhost:9999/tasks_frontend");
        context.setVariable("button", "Visit website");
        context.setVariable("company", companyName);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }


}
