package todo.todominsung.module.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import todo.todominsung.module.dto.RandomQuoteDto;
import todo.todominsung.module.service.RandomQuoteService;

@RestController
@RequestMapping("/quotes")
public class RandomQuotesController {

    @Autowired
    private RandomQuoteService randomQuoteService;

    @GetMapping
    public RandomQuoteDto getRandomQuote() {
        return randomQuoteService.getRandomQuote();
    }
}
