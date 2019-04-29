package ru.avalon.java.j20.labs.tasks;

import java.io.BufferedReader;
import ru.avalon.java.j20.labs.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Задание №3
 *
 * <p>
 * Тема: "Потоковый ввод-вывод. Чтение и запись данных с использованием
 * буферизованных типов данных".
 */
public class Task3 implements Task {

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() throws IOException {
        File input = new File("assets/countries.txt"); // файл с исходными данными (входной файл)
        File output = new File("task3_countries_buffered_mode_output.txt"); // файл, который может не существовать (сам создастся), и в который записывается результат чтения входного файла
        Collection<String> lines = read(input); // коллекция линий типа String, которая хранит результат чтения файла input в виде строки
        write(output, lines); // запись результата чтения файла input (переменная text типа String) в файл output
    }

    /**
     * Выполняет чтение указанного файла в коллекцию строк.
     * <p>
     * Каждый элемент коллекции представляет собой отдельную строку файла.
     *
     * @param file файл
     * @return содержимое файла в виде текста.
     * @throws IOException в случае ошибок ввода-вывода.
     */
    private Collection<String> read(File file) throws IOException { // метод возвращающий коллекцию со строками из прочитанного файла, принимающий файл типа File и генерирующий исключения 
        // внутри try с ресурсами произойдет автоматическое освобождение памяти благодаря наследованию AutoCloseable
        try (Reader reader = new FileReader(file); // исходный ридер = открыли файл для чтения
                BufferedReader input = new BufferedReader(reader)) { // BufferedReader (буфферизованный ридер) создается из какого-то ридера: reader
            Collection<String> result = new ArrayList<>(); // создаем коллекцию в которую будем заносить прочитанный текст (List - список, можно заносить повторяющиеся элементы, сохраняется последовательность внесения элементов в коллекцию)
            String line; // переменная line типа String
            while ((line = input.readLine()) != null) { // условие проверки наличия информации для чтения. Метод readLine САМ СОЗДАЕТ СВОЙ БУФФЕР. Если в строке есть символы, то она не null
                line = line.trim(); // подрезает пробельные символы в начале и в конце строки
                if (!line.isEmpty()) { // если строка не пустая,
                    result.add(line); // то добавляем строку (line) в результат (коллекцию)
                }
            }
            return result;
        }
    }

    /**
     * TODO(Преподаватель): Не смог разобраться, почему формирование каждой line
     * происходит таким образом, что как то автоматически разделяет текст файла
     * на строки типа: AU:Австралия. То есть строка обрезается по каким то
     * правилам? Как будто там есть между каждой такой страной enter, но его
     * ведь нет?
     */
    
    /**
     * Выполняет запись коллекции строковых элементов в файл.
     * <p>
     * Каждый элемент коллекции должен быть записан в файл отдельной строкой.
     *
     * write(куда передаем, что передаем)
     *
     * @param file файл
     * @param collection коллекция строк
     * @throws IOException в случае ошибок ввода-вывода.
     */
    private void write(File file, Collection<String> collection) throws IOException { // метод принимающий: 1. файл, в который будет происходить запись; 2. коллекцию-список строк - которая хранит результат чтения файла input в виде отдельных строк. Генерируются исключения.
        try (PrintWriter writer = new PrintWriter(file)) {
            for (String line : collection) { // итерируем (перебираем) каждую линию в коллекции
                writer.println(line); // записываем каждую линию (с переходом на новую строку - println) в файл (file)
            }
        }
    }
}
