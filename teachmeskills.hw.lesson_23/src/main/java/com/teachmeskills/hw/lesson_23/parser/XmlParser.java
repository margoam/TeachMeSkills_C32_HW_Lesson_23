package com.teachmeskills.hw.lesson_23.parser;

import com.teachmeskills.hw.lesson_23.exception.XmlFileParseException;
import com.teachmeskills.hw.lesson_23.model.Book;
import com.teachmeskills.hw.lesson_23.model.Library;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class XmlParser {

    public static void showBookWithMaxPagesFromXml(String fileName) throws XmlFileParseException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            Document doc = factory.newDocumentBuilder().parse(new File(fileName));
            doc.getDocumentElement().normalize();

            NodeList allBookNodes = doc.getElementsByTagName("book");
            List<Book> books = new ArrayList<>();

            for (int i = 0; i < allBookNodes.getLength(); i++) {
                Element bookElement = (Element) allBookNodes.item(i);
                Book book = new Book();
                book.setTitle(bookElement.getElementsByTagName("title").item(0).getTextContent());
                book.setAuthor(bookElement.getElementsByTagName("author").item(0).getTextContent());
                book.setYear(Integer.parseInt(bookElement.getElementsByTagName("year").item(0).getTextContent()));
                book.setPages(Integer.parseInt(bookElement.getElementsByTagName("pages").item(0).getTextContent()));
                book.setGenre(bookElement.getElementsByTagName("genre").item(0).getTextContent());
                books.add(book);
            }

            Optional<Book> bookWithMaxPagesAmount = books.stream().max(Comparator.comparingInt(Book::getPages));

            bookWithMaxPagesAmount.ifPresent(book -> System.out.println("A book with max pages is: "
                    + book.getTitle() + " " + book.getPages() + " pages"));
        } catch (Exception e) {
            throw new XmlFileParseException("Error occur during parsing: " + e.getMessage());
        }
    }

    public static File parseToXmlFile(List<Book> list, String toXmlFileName) throws XmlFileParseException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Library.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(new Library(list), new File(toXmlFileName));

            return new File(toXmlFileName);
        } catch (Exception e) {
            throw new XmlFileParseException("Error occurs during file creation: " + e.getMessage());
        }
    }
}
