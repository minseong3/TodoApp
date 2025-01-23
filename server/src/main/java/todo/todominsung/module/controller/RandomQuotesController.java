package todo.todominsung.module.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import todo.todominsung.module.entity.RandomQuote;
import todo.todominsung.module.service.RandomQuoteService;

@RestController
@RequestMapping("/quotes")
public class RandomQuotesController {
    private final RandomQuoteService randomQuoteService;

    public RandomQuotesController(RandomQuoteService randomQuoteService) {
        this.randomQuoteService = randomQuoteService;
    }

    @GetMapping
    public RandomQuote getRandomQuote() {
        return randomQuoteService.getRandomQuote();
    }
}
