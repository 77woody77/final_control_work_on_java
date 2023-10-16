import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ToyRaffle {

    // Внутренний класс, описывающий игрушку.
    static class Toy {
        private int id;
        private String name;
        private int weight;

        public Toy(int id, String name, int weight) {
            this.id = id;
            this.name = name;
            this.weight = weight;
        }

        public int getId() {
            return id;
        }
    }

    // Список игрушек, где игрушки могут встречаться несколько раз в соответствии с их весом.
    private static List<Toy> toyList = new ArrayList<>();
    // Объект для генерации случайных чисел.
    private static Random random = new Random();

    public static void main(String[] args) {
        // Добавляем игрушки в список.
        put("1 2 Конструктор");
        put("2 2 Робот");
        put("3 6 Кукла");

        // Выполняем розыгрыш 10 раз и записываем результаты в файл.
        for (int i = 0; i < 10; i++) {
            int result = get();
            writeToOutputFile(String.valueOf(result));
        }
    }

    /**
     * Метод для добавления игрушки в список на основе строки ввода.
     *
     * @param input Строка в формате "id вес имя".
     */
    public static void put(String input) {
        String[] parts = input.split(" ");
        int id = Integer.parseInt(parts[0]);
        int weight = Integer.parseInt(parts[1]);
        String name = parts[2];

        Toy toy = new Toy(id, name, weight);

        // Добавляем игрушку в список многократно в соответствии с ее весом.
        for (int i = 0; i < weight; i++) {
            toyList.add(toy);
        }
    }

    /**
     * Метод для выбора игрушки на основе ее веса.
     *
     * @return ID выбранной игрушки.
     */
    public static int get() {
        int index = random.nextInt(toyList.size());
        Toy selectedToy = toyList.get(index);
        return selectedToy.getId();
    }

    /**
     * Метод для записи результата розыгрыша в файл.
     *
     * @param output ID выбранной игрушки.
     */
    private static void writeToOutputFile(String output) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true))) {
            writer.write(output);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
