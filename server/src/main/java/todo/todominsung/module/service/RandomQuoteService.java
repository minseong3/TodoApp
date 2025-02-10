package todo.todominsung.module.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import todo.todominsung.module.dto.RandomQuoteDto;

import java.io.InputStream;
import java.util.*;

@Service
public class RandomQuoteService {

    private List<RandomQuoteDto> quotes;

    @PostConstruct // 빈이 초기화된 후 자동으로 실행
    public void loadQuotes() {
        try {
            ObjectMapper objectMapper = new ObjectMapper(); // JSON -> Java 객체 , Java 객체 -> JSON으로 변환할 때 사용
            InputStream inputStream = getClass().getResourceAsStream("/quotes.json");
            quotes = objectMapper.readValue(inputStream, new TypeReference<List<RandomQuoteDto>>() {});
        } catch (Exception error) {
            throw new RuntimeException("Failed to load quotes.json", error);
        }
    }

    public RandomQuoteDto getRandomQuote() {
        int randomIndex = (int) (Math.random() * quotes.size());
        return quotes.get(randomIndex);
    }
}
