package ru.example.articles.service;

import ru.example.articles.model.Article;
import ru.example.articles.model.Word;
import ru.example.articles.store.Store;

public interface ArticleService {
    void generate(Store<Word> wordStore, int count, Store<Article> articleStore);
}
