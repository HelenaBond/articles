package ru.example.articles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.example.articles.model.Article;
import ru.example.articles.model.Word;
import ru.example.articles.service.SimpleArticleService;
import ru.example.articles.service.generator.RandomArticleGenerator;
import ru.example.articles.store.ArticleStore;
import ru.example.articles.store.WordStore;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class.getSimpleName());

    public static final int TARGET_COUNT = 1_000_000;
    public static final int BATCH_SIZE = 1_000;

    public static void main(String[] args) {
        var properties = loadProperties();
        int id = 0;
        List<Word> words;
        try (var wordStore = new WordStore(properties)) {
            words = wordStore.findAllAfter(id, BATCH_SIZE);
        } catch (Exception e) {
            LOGGER.error("Не удалось получить список слов : { }", e.getCause());
            throw new IllegalStateException();
        }
        try (var articleStore = new ArticleStore(properties)) {
            var articleGenerator = new RandomArticleGenerator();
            var articleService = new SimpleArticleService(articleGenerator);
            articleService.generate(words, TARGET_COUNT, articleStore);
            printAllArticles(articleStore);
        } catch (Exception e) {
            LOGGER.error("Не удалось сгенерировать статьи : { }", e.getCause());
            throw new IllegalStateException();
        }
    }

    private static Properties loadProperties() {
        LOGGER.info("Загрузка настроек приложения");
        var properties = new Properties();
        try (InputStream in = Application.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(in);
        } catch (Exception e) {
            LOGGER.error("Не удалось загрузить настройки. { }", e.getCause());
            throw new IllegalStateException();
        }
        return properties;
    }

    private static void printAllArticles(ArticleStore articleStore) {
        int id = 0;
        List<Article> batchArticles = articleStore.findAllAfter(id, BATCH_SIZE);
        while (!batchArticles.isEmpty()) {
            id = batchArticles.get(batchArticles.size() - 1).getId();
            System.out.println(batchArticles);
            batchArticles = articleStore.findAllAfter(id, BATCH_SIZE);
        }
    }

}
