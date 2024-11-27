package ru.example.articles.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.example.articles.model.Article;
import ru.example.articles.model.Word;
import ru.example.articles.service.generator.ArticleGenerator;
import ru.example.articles.store.Store;

import java.util.List;

public class SimpleArticleService implements ArticleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleArticleService.class.getSimpleName());

    private final ArticleGenerator articleGenerator;

    public SimpleArticleService(ArticleGenerator articleGenerator) {
        this.articleGenerator = articleGenerator;
    }

    @Override
    public void generate(List<Word> words, int count, Store<Article> articleStore) {
        LOGGER.info("Геренация статей в количестве {}", count);
        for (int i = 1; i <= count; i++) {
            LOGGER.info("Сгенерирована статья № {}", i);
            articleStore.save(articleGenerator.generate(words));
        }
    }
}
