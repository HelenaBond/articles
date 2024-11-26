package ru.example.articles.service.generator;

import ru.example.articles.model.Article;
import ru.example.articles.model.Word;

import java.util.List;

public interface ArticleGenerator {
    Article generate(List<Word> words);
}
