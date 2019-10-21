import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class Main {

  public static void main(String[] args) {
    System.out.println("Введите адрес сайта: пример - > https://yandex.ru ");
    Scanner sc = new Scanner(System.in);
    String url = sc.nextLine();

    System.out.println("Сканируем сайт...");
    LinkExecutor linkExecutor = new LinkExecutor(url, url);
    String siteMap = new ForkJoinPool().invoke(linkExecutor);

    System.out.println("Сканирование завершено...");
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
