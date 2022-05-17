package com.itschool;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itschool.classwork.Animals;
import com.itschool.classwork.Pet;
import com.itschool.classwork.PetType;
import org.apache.commons.compress.archivers.ArchiveException;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

class Main {
   private static final String fileName = "C:\\Users\\vence\\AppData\\Local\\Temp\\1test.txt";
   private static final String fileName1 = "C:\\Users\\vence\\AppData\\Local\\Temp\\test1.txt";
   private static FileOutputStream outFile;

   public static void main(String[] args) throws IOException {
      println();              // обёртка для вывода на стандартный поток вывода

      arraysReadWrite();      // запись и чтение массивов данных в бинарный файл

      filesConcatenation();   // сцепление файлов

      speedTestBinary();      // тест производительности записи бинарных данных

      speedTestText();        // тест производительности записи символьных данных

      serializationNativeJava();    // сериализация/десериализация объектов

      serializationFasterJackson(); // сериализация/десериализация объектов

      getCurrencyExchangeRates();   // десериализация данных из JSON полученного из API

      classWork();
   }


   /**
    * Как работает System.out.println()
    */
   private static void println() {
      // Запись строк в стандартный поток вывода
      PrintWriter pw = new PrintWriter(System.out, true);
      pw.write("Это строка:");
      int i = -7;
      pw.println(i);
      double d = 4.5;
      pw.println(d);
   }


   /**
    * Чтение/запись байтовых массивов
    *
    * @throws IOException метод потенциально может вызвать исключение IOException
    */
   private static void arraysReadWrite() throws IOException {
      // Запись массива байтов в файл
      byte[] bytesToWrite = {65, 97, 45, 48, 10}; // что записываем
      outFile = null;
      boolean isOpened = false;

      try {
         outFile = new FileOutputStream(fileName, true);
         isOpened = true;
         outFile.write(bytesToWrite); //запись в файл
      } catch (FileNotFoundException e) {
         System.out.println("Невозможно произвести запись в файл:" + fileName);
      } catch (IOException e) {
         System.out.println("Ошибка ввода/вывода:" + e);
      }
      if (isOpened) {
         outFile.close();
      }

      // Чтение массива байтов из файла
      try {
         FileInputStream inFile = new FileInputStream(fileName);
         int bytesAvailable = inFile.available(); //сколько можно считать
         System.out.println("Available: " + bytesAvailable);

         byte[] bytesRead = new byte[bytesAvailable]; //куда считываем
         int count = inFile.read(bytesRead, 0, bytesAvailable);

         System.out.println("Было считано байт: " + count);
         System.out.println(Arrays.toString(bytesRead));

         inFile.close();
      } catch (FileNotFoundException e) {
         System.out.println("Невозможно произвести чтение из файла:" + fileName1);
      } catch (IOException e) {
         System.out.println("Ошибка ввода/вывода:" + e);
      }
   }


   /**
    * Конкатенация файлов (сцепление содержимого)
    */
   private static void filesConcatenation() {
      FileInputStream inFile1 = null;
      FileInputStream inFile2 = null;
      SequenceInputStream sequenceStream;
      outFile = null;
      try {
         inFile1 = new FileInputStream("filecat1.txt");
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }

      try {
         inFile2 = new FileInputStream("filecat2.txt");
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }

      sequenceStream = new SequenceInputStream(inFile1, inFile2);
      try {
         outFile = new FileOutputStream("filecat3.txt", true);
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }

      int readByte = 0;
      try {
         readByte = sequenceStream.read();
      } catch (IOException e) {
         e.printStackTrace();
      }
      while (readByte != -1) {
         try {
            outFile.write(readByte);
         } catch (IOException e) {
            e.printStackTrace();
         }
         try {
            readByte = sequenceStream.read();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }


   /**
    * Оценка производительности чтения/записи больших массивов бинарных данных
    *
    * @throws IOException метод потенциально может вызвать исключение IOException
    */
   private static void speedTestBinary() throws IOException {
      Random random = new Random();
      byte[] bytesArray = new byte[1024 * 1024];
      for (int i = 0; i < bytesArray.length; i++) {
         bytesArray[i] = (byte) random.nextInt();
      }
      String fileName2 = "C:\\Users\\vence\\AppData\\Local\\Temp\\file1.txt";
      InputStream inStream = null;
      OutputStream outStream = null;

      long timeStart = System.currentTimeMillis();

      // Записываем в файл некоторое количество байт данных
      try {
         outStream = new FileOutputStream(fileName2);
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }

      if (outStream != null) {
         for (int i = 1024; --i >= 0; ) {
            try {
               outStream.write(bytesArray);
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
         outStream.flush();
         outStream.close();
      }

      // читаем записанные данные
      try {
         inStream = new FileInputStream(fileName);
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }

      if (inStream != null) {
         inStream = new BufferedInputStream(inStream);
         int readByte;
         do {
            readByte = inStream.read();
            //  if (readByte >= 0)
            //System.out.println(readByte);
         } while (readByte != -1);

         inStream.close();
      }

      System.out.println("Затрачено времени: " + (System.currentTimeMillis() - timeStart));
   }


   /**
    * Оценка производительности буферизированной записи/чтения большого объёма текстовых блоков данных
    */
   private static void speedTestText() {
      long timeStart = System.currentTimeMillis();

      String fileName5 = "d:\\file6.txt";
      FileWriter fw;
      BufferedWriter bw;
      FileReader fr;
      BufferedReader br;
      // Строка, которая будет записана в файл
      String data = "Some data to be written and read \n";
      try {
         fw = new FileWriter(fileName5);
         bw = new BufferedWriter(fw);
         System.out.println("\nWrite some data to file: " + fileName5);
         // Несколько раз записываем строку
         for (int i = 100; --i >= 0; )
            bw.write(data);

         bw.close();

         fr = new FileReader(fileName5);
         br = new BufferedReader(fr);
         String s;
         int count = 0;
         System.out.println("Read  data from file: " + fileName5);
         // Считываем данные
         while ((s = br.readLine()) != null) {
            count++;
            // System.out.println("row  " + ++count + " read:" + s);  // отображая на экране
         }
         System.out.println("Считано строк: " + count);
         br.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      System.out.println("Затрачено времени: " + (System.currentTimeMillis() - timeStart));
   }


   /**
    * Встроенная ("нативная") Java сериализация/десериализация объектов
    *
    * @throws IOException метод потенциально может вызвать исключение IOException
    */
   private static void serializationNativeJava() throws IOException {
// Сериализация простого объекта
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      Object objSave = 1;
      ObjectOutputStream oos = new ObjectOutputStream(os);
      oos.writeObject(objSave);

      byte[] bArray = os.toByteArray(); //получение содержимого массива
      ByteArrayInputStream is = new ByteArrayInputStream(bArray);
      ObjectInputStream ois = new ObjectInputStream(is);
      Object objRead = null;
      try {
         objRead = ois.readObject();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
      System.out.println("--------------------------");
      System.out.println(objRead != null ? objRead.toString() : "object is null");


// Сериализация 1 экземпляра пользовательского класса
      Pirate pirate = new Pirate("Long John Silver", "Treasure Island", 65);
/*
      pirate.name = "Long John Silver";
      pirate.address = "Treasure Island";
      pirate.age = 66;
 */

      try {
         FileOutputStream fileOut = new FileOutputStream("./pirate.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(pirate);
         out.close();
         fileOut.close();

         System.out.println("Serialized data is saved in ./pirate.ser");
      } catch (IOException ex) {
         ex.printStackTrace();
      }
      // ---------deserialize -----------
      Pirate e1 = null;
      try {
         FileInputStream fileIn = new FileInputStream("./pirate.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         e1 = (Pirate) in.readObject();
         in.close();
         fileIn.close();

         System.out.println("\nDeserialized pirate..." + e1);
      } catch (IOException ex) {
         ex.printStackTrace();
         return;
      } catch (ClassNotFoundException c) {
         System.out.println("Pirate class not found");
         c.printStackTrace();
         return;
      }

      System.out.println("\n----------------------------------\n");
// Сериализация экземпляра пользовательского класса содержащего поле-список
      Pirates pirates = new Pirates();
      pirates.Add(pirate);
      pirates.Add(new Pirate("Dr. David Livesey", "Bristol", 100));
      pirates.Add(new Pirate("Captain Alexander Smollett", "Bristol", 1000));
      System.out.println(pirates);

      pirates.Save("./pirates.ser");

      pirates.Clear();

      // ---------deserialize -----------
      pirates.Load("./pirates.ser");
      System.out.println("\n\nDeserialized pirates: ...");
      System.out.println(pirates);
   }


   /**
    * Сериализация/десериализация объектов с использованием библиотеки Faster Jackson
    *
    * @throws IOException метод потенциально может вызвать исключение IOException
    */
   private static void serializationFasterJackson() throws IOException {
      Pirates pirates = new Pirates();
      pirates.Add(new Pirate("Long John Silver", "Treasure Island", 65));
      pirates.Add(new Pirate("Dr. David Livesey", "Bristol", 100));
      pirates.Add(new Pirate("Captain Alexander Smollett", "Bristol", 1000));
      System.out.println(pirates);

// Сериализация объекта класс Pirates с использованием библиотеки Jackson
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
      objectMapper.writeValue(new File("pirates.json"), pirates);

// Десериализация объекта класс Pirates с использованием библиотеки Jackson
      Pirates piratesTeam = null;
      boolean isSuccessful = false;
      try {
         piratesTeam = objectMapper.readValue(new File("pirates1.json"), Pirates.class);
         isSuccessful = true;
      } catch (Exception e) {
         System.out.println("FasterJackson deserialization error!");
      }
      System.out.println("\nDeserialized Class Pirates");
      System.out.println(isSuccessful ? piratesTeam : "can't show empty list");

// Упаковка в ZIP-архив
      System.out.println("\nLet's zip out file");
      try {
         Packer.pack(new File(System.getProperty("user.dir") + "/src/backup/"), new File("pirates.json.zip"));
      } catch (ArchiveException e) {
         e.printStackTrace();
      }
   }


   /**
    * Получение JSON-данных от API по сети с последующей десериализацией объекта
    *
    * @throws IOException метод потенциально может вызвать исключение IOException
    */
   private static void getCurrencyExchangeRates() throws IOException {
      Currency[] rates = null;

      ObjectMapper objectMapper = new ObjectMapper();
      try {
         rates = objectMapper.readValue(new URL("https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5"), Currency[].class);
      } catch (IOException e) {
         //e.printStackTrace();
         System.out.println("Network is inaccessible\nExchage using last loaded rate: ");
         rates = objectMapper.readValue(new File("./rates.json"), Currency[].class);
      }

      for (Currency rate : rates) {
         System.out.println(rate);
      }

      System.out.printf("%d x %5.2f = %5.2f", 200, Double.parseDouble(rates[0].getSale()), 200 * Double.parseDouble(rates[0].getSale()));
   }


   /**
    * Ещё один пример сериализации/десериализации объектов
    */
   private static void classWork() {
      Pet snake = new Pet(PetType.snake, "Kaa", new GregorianCalendar(2005, Calendar.OCTOBER, 20), "John");
      Pet cat = new Pet(PetType.cat, "Tom", new GregorianCalendar(2015, Calendar.NOVEMBER, 20), "David");
      Pet dog = new Pet(PetType.dog, "Barbos", new GregorianCalendar(2017, Calendar.FEBRUARY, 2), "Anonymous");

      System.out.println(cat);

      try {
         FileOutputStream fileOut = new FileOutputStream("./cat.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(cat);
         out.close();
         fileOut.close();
         System.out.println("Serialized data is saved in ./cat.ser");
      } catch (IOException ex) {
         ex.printStackTrace();
      }

      // ---------deserialize -----------
      Pet newCat = null;
      try {
         FileInputStream fileIn = new FileInputStream("./cat.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         newCat = (Pet) in.readObject();
         System.out.println("\nDeserialized animal..." + newCat);
         in.close();
         fileIn.close();
      } catch (IOException ex) {
         ex.printStackTrace();
         return;
      } catch (ClassNotFoundException c) {
         System.out.println("Pet class not found");
         c.printStackTrace();
         return;
      }

      System.out.println("\n----------------------------------");

      Animals zoo = new Animals();
      zoo.add(dog, cat, snake, new Pet(PetType.fish, "Freddy", GregorianCalendar.getInstance(), "Nobody"));
      System.out.println("Serialized animals:\n" + zoo);
      System.out.println("\n----------------------------------");

      zoo.save("zoo.ser");

      zoo = new Animals();
      zoo.load("zoo.ser");
      System.out.println("Deserialized animals:\n" + zoo);
   }
}