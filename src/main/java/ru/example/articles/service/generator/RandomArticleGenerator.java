package ru.example.articles.service.generator;

import ru.example.articles.model.Article;
import ru.example.articles.model.Word;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomArticleGenerator implements ArticleGenerator {
    @Override
    public Article generate(List<Word> words) {
        Collections.shuffle(words);
        return new Article(words.stream()
                .map(Word::getValue)
                .collect(Collectors.joining(" ")));
    }
}
