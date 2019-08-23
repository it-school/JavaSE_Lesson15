package com.itschool;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.Arrays;


class Main {
    private static FileOutputStream outFile;
    private static FileInputStream inFile;
    private static String fileName = "d:\\1test.txt";
    private static String fileName1 = "d:\\test1.txt";

    public static void main(String[] args) throws IOException {
/*
        // Запись строк в стандартный поток вывода
        PrintWriter pw = new PrintWriter(System.out, true);
        pw.write("Это строка:");
        int i = -7;
        pw.println(i);
        double d = 4.5;
        pw.println(d);
*/

//        ArrayRW();

//         SpeedTest();

        Serialization();
    }

    private static void SpeedTest() throws IOException {
        long timeStart = System.currentTimeMillis();
        /*
        // Тестирование производительности буферизированной записи
        byte[] bytesReaded = new byte[100];
        String fileName2 = "file1.txt";
        InputStream inStream = null;
        OutputStream outStream = null;
        //Записать в файл некоторое количество байт

        try {
            outStream = new FileOutputStream(fileName2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 100000; --i >= 0; ) {
            try {
                outStream.write(bytesReaded);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        outStream.flush();
        outStream.close();

        try {
            inStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        inStream = new BufferedInputStream(inStream);
        int error = 0;
        do {
            error = inStream.read();
            //  if (error >= 0)
            //System.out.println(error);
        } while (error != -1);
        inStream.close();
        System.out.println(System.currentTimeMillis() - timeStart);
*/

// Запись строковых данных в файл
        String fileName5 = "d:\\file6.txt";
        FileWriter fw;
        BufferedWriter bw;
        FileReader fr;
        BufferedReader br;
        // Строка, которая будет записана в файл
        String data = "Some data to be written and readed \n";
        try {
            fw = new FileWriter(fileName5);
            bw = new BufferedWriter(fw);
            System.out.println("Write some data to file: " + fileName5);
            // Несколько раз записать строку
            for (int i = (int) (Math.random() * 10); --i >= 0; )
                bw.write(data);
            bw.close();

            fr = new FileReader(fileName5);
            br = new BufferedReader(fr);
            String s;
            int count = 0;
            System.out.println("Read  data from file: " + fileName5);
            // Считывать данные, отображая на экран
            while ((s = br.readLine()) != null)
                System.out.println("row  " + ++count + " read:" + s);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - timeStart);
    }

    private static void ArrayRW() throws IOException {
        // Запись массива байтов
        byte[] bytesToWrite = {65, 97, 45}; //что записываем
        outFile = null;
        boolean isOpened = false;

        try {
            outFile = new FileOutputStream(fileName, false);
            isOpened = true;
            outFile.write(bytesToWrite); //запись в файл
        } catch (FileNotFoundException e) {
            System.out.println("Невозможно произвести запись в файл:" + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода:" + e.toString());
        }
        if (isOpened) {
            outFile.close();
        }

        // Чтение массива байтов
        try {
            inFile = new FileInputStream(fileName);
            int bytesAvailable = inFile.available(); //сколько можно считать
            System.out.println("Available: " + bytesAvailable);

            byte[] bytesReaded = new byte[bytesAvailable]; //куда считываем
            int count = inFile.read(bytesReaded, 0, bytesAvailable);

            System.out.println(Arrays.toString(bytesReaded));

            inFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Невозможно произвести чтение из файла:" + fileName1);
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода:" + e.toString());
        }


        // Сцепление содержимого файлов
        FileInputStream inFile1 = null;
        FileInputStream inFile2 = null;
        SequenceInputStream sequenceStream;
        outFile = null;
        try {
            inFile1 = new FileInputStream("d:\\file1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            inFile2 = new FileInputStream("d:\\file2.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sequenceStream = new SequenceInputStream(inFile1, inFile2);
        try {
            outFile = new FileOutputStream("d:\\file3.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int readedByte = 0;
        try {
            readedByte = sequenceStream.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (readedByte != -1) {
            try {
                outFile.write(readedByte);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                readedByte = sequenceStream.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void Serialization() throws IOException {
        // Сериализация простого объекта
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Object objSave = new Integer(1);
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
        System.out.println(objRead != null ? objRead.toString() : "");


// Сериализация 1 экземпляра пользовательского класса
        Pirate pirate = new Pirate();
        pirate.name = "Long John Silver";
        pirate.address = "Treasure Island";
        pirate.age = 40;

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
        Pirate e1;
        try {
            FileInputStream fileIn = new FileInputStream("./pirate.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            e1 = (Pirate) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Pirate class not found");
            c.printStackTrace();
            return;
        }

        System.out.println("\nDeserialized pirate...");
        System.out.println("Name: " + e1.name);
        System.out.println("Address: " + e1.address);
        System.out.println("Age: " + e1.age);

        System.out.println("\n----------------------------------\n");


// Сериализация списка экземпляров пользовательского класса

        Pirates pirates = new Pirates();
        pirates.Add(pirate);
        pirates.Add(new Pirate("Dr. David Livesey", "Bristol", 100));
        pirates.Add(new Pirate("Captain Alexander Smollett", "Bristol", 100000));
        System.out.println(pirates.toString());

        pirates.Save("./pirates.ser");
        // ---------deserialize -----------
        pirates.Clear();

        pirates.Load("./pirates.ser");
        System.out.println("\n\nDeserialized pirates: ...");
        System.out.println(pirates.toString());

// Jackson library seriaization
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("car.json"), pirate);
        Pirate car = objectMapper.readValue(new File("car.json"), Pirate.class);
        System.out.println(car);
    }
}