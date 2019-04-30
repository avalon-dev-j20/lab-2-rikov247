package ru.avalon.java.j20.labs.tasks;

import ru.avalon.java.j20.labs.Task;

import java.io.*;

/**
 * Задание №2
 *
 * <p>
 * Тема: "Потоковый ввод-вывод. Чтение и запись данных в текстовом режиме".
 */
public class Task2 implements Task {

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() throws IOException {
        File input = new File("assets/countries.txt"); // файл с исходными данными (входной файл)
        File output = new File("task2_countries_text_mode_output.txt"); // файл, который может не существовать (сам создастся), и в который записывается результат чтения входного файла
        String text = read(input); // переменная, которая хранит результат чтения файла input в виде строки
        write(output, text); // запись результата чтения файла input (переменная text типа String) в файл output
    }

    /**
     * Выполняет чтение указанного файла в текстовом режиме.
     *
     * <p>
     * Весь текст файла возвращается в виде одного экземпляра типа
     * {@link String}.
     *
     * @param file файл
     * @return содержимое файла в виде текста.
     * @throws IOException в случае ошибок ввода-вывода.
     */
    private String read(File file) throws IOException { // метод возвращающий String, принимающий файл типа File и генерирующий исключения 
        try (Reader reader = new FileReader(file)) { // пытаемся поймать исключения при создании экземпляра класса Reader с именем reader на основе файла file
            StringBuilder builder = new StringBuilder(); // накапливать данные в строку и из них получать результат
            char[] buffer = new char[5]; // создается буффер-массив элементов типа char (каждый символ типа String)
            int len; // количество прочитанных данных на каждом шаге чтения (после очередного переполнения буффера - новый шаг чтения)
            while ((len = reader.read(buffer)) > 0) { // пока есть данные для чтения
                builder.append(buffer, 0, len); // добавляем элементы char из буффера с нулевого (0) до последнего прочитанного (len) в переменную builder типа StringBuilder
            }
            return builder.toString(); // возвращаем переменную builder, приведенную к строке
        }
    }

    /**
     * Выполняет запись текстовых данных в файл в текстовом режиме.
     *
     * write(куда передаем, что передаем)
     * 
     * @param file файл
     * @param text текст
     * @throws IOException в случае ошибок ввода-вывода.
     */
    private void write(File file, String text) throws IOException { // метод принимающий: 1. файл, в который будет происходить запись; 2. текст в виде строки - который хранит результат чтения файла input. Генерируются исключения.
        try (Writer writer = new FileWriter(file)) {
            writer.write(text); // запись в поток (write) текста (text), приведенного к типу
        }
    }
}
