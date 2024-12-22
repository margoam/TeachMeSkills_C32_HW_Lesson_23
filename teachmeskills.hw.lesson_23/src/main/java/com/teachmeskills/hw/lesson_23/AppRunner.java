package com.teachmeskills.hw.lesson_23;

import com.teachmeskills.hw.lesson_23.model.Book;
import com.teachmeskills.hw.lesson_23.parser.JsonParser;
import com.teachmeskills.hw.lesson_23.parser.XmlParser;

import java.io.File;
import java.util.List;

/**
 * 1. Используя библиотеку Jackson, напишите Java-программу, которая вычитывает JSON-файл(books.json) и сохранит данные в коллекцию Java.
 * 2. Конвертируйте данные из этой коллекции в XML-формат с помощью библиотеки JAXB. Сохраните полученный XML результат в файл.
 * 3. Используя любой парсер(DOM,SAX,StAX) распарсите данные в Java приложении и выведите в консоль информацию о книге с наибольшим количеством страниц.
 */
public class AppRunner {
    public static void main(String[] args) {

        try {
            List<Book> books = JsonParser.parseJsonFile("books.json");
            File xmlFile = XmlParser.parseToXmlFile(books, "books.xml");
            XmlParser.showBookWithMaxPagesFromXml(xmlFile.getName());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}