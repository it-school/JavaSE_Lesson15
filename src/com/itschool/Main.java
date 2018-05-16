package com.itschool;

import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        /*
        byte[] bytesToWrite = {1, 2, 3}; //что записываем
        byte[] bytesReaded = new byte[10]; //куда считываем
        String fileName = "d:\\test.txt", fileName1 = "d:\\test1.txt";
        try {
            FileOutputStream outFile = new FileOutputStream(fileName);
            outFile.write(bytesToWrite); //запись в файл
            outFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Невозможно произвести запись в файл:" + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода:" + e.toString());
        }

        try {
            FileInputStream inFile = new FileInputStream(fileName1);
            int bytesAvailable = inFile.available(); //сколько можно считать
            int count = inFile.read(bytesReaded, 0, bytesAvailable);

            System.out.println(Arrays.toString(bytesReaded));

            inFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Невозможно произвести чтение из файла:" + fileName1);
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода:" + e.toString());
        }
*/

        /*
        FileInputStream inFile1 = null;
        FileInputStream inFile2 = null;
        SequenceInputStream sequenceStream = null;
        FileOutputStream outFile = null;
        try {
            inFile1 = new FileInputStream("file1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            inFile2 = new FileInputStream("file2.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sequenceStream = new SequenceInputStream(inFile1, inFile2);
        try {
            outFile = new FileOutputStream("file3.txt");
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

        byte[] bytesReaded = new byte[10];
        String fileName ="file1.txt";
        InputStream inStream =null;
        OutputStream outStream =null;
//Записать в файл некоторое количество байт
        long timeStart =System.currentTimeMillis();
        try {
            outStream =new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //outStream =new BufferedOutputStream(outStream);
        for(int i=100000000;--i>=0;)
        {
            try {
                outStream.write(bytesReaded);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        outStream.flush();
        outStream.close();

        try {
            inStream =new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        inStream =new BufferedInputStream(inStream);
        int error = 0;
        do{
            error = inStream.read();
          //  if (error >= 0)
                //System.out.println(error);
        }  while(error!=-1);
        inStream.close();
        System.out.println(System.currentTimeMillis()-timeStart);



        String fileName = "d:\\file6.txt";
        FileWriter fw = null;
        BufferedWriter bw = null;
        FileReader fr = null;
        BufferedReader br = null;
        // Строка, которая будет записана в файл
        String data = "Some data to be written and readed \n";
        try {
            fw = new FileWriter(fileName);
            bw = new BufferedWriter(fw);
            System.out.println("Write some data to file: " + fileName);
            // Несколько раз записать строку
            for (int i = (int) (Math.random() * 10); --i >= 0; )
                bw.write(data);
            bw.close();
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);
            String s = null;
            int count = 0;
            System.out.println("Read  data from file: " + fileName);
            // Считывать данные, отображая на экран
            while ((s = br.readLine()) != null)
                System.out.println("row  " + ++count + " read:" + s);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        PrintWriter pw = new PrintWriter(System.out, true);
        pw.println("Это строка:");
        int i = -7;
        pw.println(i);
        double d = 4.5;
        pw.println(d);


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
        pw.println("--------------------------");
        pw.println(objRead.toString());
*/

        // ----------serialize---------------
        Employee e = new Employee();
        e.name = "John Silver";
        e.address = "Treasure Island";
        e.number = 151;

        try {
            FileOutputStream fileOut = new FileOutputStream("./employee.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(e);
            out.flush(); out.close();
            fileOut.flush(); fileOut.close();
            System.out.printf("Serialized data is saved in ./employee.ser");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // ---------deserialize -----------
        Employee e1 = null;
        try {
            FileInputStream fileIn = new FileInputStream("./employee.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            e1 = (Employee) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }

        System.out.println("Deserialized Employee...");
        System.out.println("Name: " + e1.name);
        System.out.println("Address: " + e1.address);
        System.out.println("Number: " + e1.number);

    }
}

