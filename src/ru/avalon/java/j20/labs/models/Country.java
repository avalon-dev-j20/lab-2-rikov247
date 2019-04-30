package ru.avalon.java.j20.labs.models;

import java.io.IOException;
import java.text.ParseException;
import java.util.Objects;

/**
 * Модель представления о стране. Реализована.
 */
public class Country {

    /**
     * Код страны.
     */
    private final String code;
    /**
     * Название страны.
     */
    private final String name;

    /**
     * Основной конструктор класса.
     *
     * @param code код страны
     * @param name название страны
     */
    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * Возвращает код страны.
     *
     * @return строковый код страны
     */
    public String getCode() {
        return code;
    }

    /**
     * Возвращает название страны.
     *
     * @return название страны
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object otherObject) { // проверка на полную идентичность (если идентичны - то true)
        if (otherObject instanceof Country) { // проверка на то, является ли Object Country?
            Country otherCountry = (Country) otherObject; // (Country) - приведение к типу
            return name.equals(otherCountry.name)
                    && code == otherCountry.code; // возвращается результат лексикографического сравнения имени двух стран И кода двух стран между собой
        }
        return false;
    }

    // переопределяем hashCode, если переопределяем equals
    public int hashCode() {
        return Objects.hash(name, code); // hashCode комбинации имени и возраста
    }

    private static boolean isValid(String line) throws IOException { // проверка на корректность строки: Вернет true, если все выполняется.
        if (line != null) { // если строка есть, то проверяем её:
            return !line.isEmpty() // если не пустая строка
                    && !line.startsWith(":") // не начинается с ":"
                    && line.contains(":"); // содержит ":"
        } else {
            return false;
        }
    }

    /**
     * Возвращает экземпляр страны созданный из переданного текста в формате
     * 'Код:Название'.
     *
     * @param text текст в формате 'Код:Название'
     * @return новый экземпляр типа {@Link Country}.
     * @throws ParseException в случае, если переданная строка имеет неверный
     * формат.
     */
    public static Country valueOf(String text) throws ParseException, IOException { // преобразуем text в объект типа Country (выделяем из него код и название)
        if (isValid(text) == true) { // если строка подходит, то выделяем из нее код и название 
            int index = text.indexOf(":"); // метод indexOf класса String (его экземпляр text) - который возвращает индекс (типа int) символа (указанного в скобках) в строке  
            String key = text.substring(0, index).trim(); // получение подстроки от 0 до индекс (до знака равно). trim() - подрезает строку (удаляет пробелы)
            String value = text.substring(index + 1).trim(); // получение подстроки начиная со следующего символа после индекса (после :) и до конца строки
            return new Country(key, value); // возвращаем новый объект типа Country
        } 
        return null; 
    }
}
