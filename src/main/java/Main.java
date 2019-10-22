import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.println("Введите адрес сайта: пример - > https://yandex.ru ");
    String url = sc.nextLine();

    System.out
        .println("Введите количество создаваемых потоков: - > 0 установит значение по умолчанию.");
    int numThreads = sc.nextInt();

    System.out.println("Сканируем сайт...");
    long start = System.currentTimeMillis();
    LinkExecutor linkExecutor = new LinkExecutor(url, url);
    String siteMap = numThreads == 0 ? new ForkJoinPool().invoke(linkExecutor)
        : new ForkJoinPool(numThreads).invoke(linkExecutor);

    System.out.println("Сканирование завершено...");
    System.out
        .println("Время сканирования " + ((System.currentTimeMillis() - start) / 1000) + " сек.");
    writeFiles(siteMap);
  }

  private static void writeFiles(String map) {
    System.out.println("Пишем файл...");
    String filePath = "siteMap.txt";

    File file = new File(filePath);
    try (PrintWriter writer = new PrintWriter(file)) {
      writer.write(map);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    System.out.println("Карта создана!");
  }
}
