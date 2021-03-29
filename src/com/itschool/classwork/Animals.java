package com.itschool.classwork;

import java.io.*;
import java.util.ArrayList;

public class Animals {
   ArrayList<Pet> pets;

   public Animals(ArrayList<Pet> pets) {
      this.pets = pets;
   }

   public Animals() {
      this.pets = new ArrayList<>();
   }

   public void add(Pet... pet) {
      for (Pet item : pet) {
         this.pets.add(item);
      }
   }

   public void save(String filename) {
      try {
         FileOutputStream fileOut = new FileOutputStream(filename);
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(pets);
         out.close();
         fileOut.close();
      } catch (IOException ex) {
         ex.printStackTrace();
      }
   }

   public void load(String filename) {
      try {
         FileInputStream fileIn = new FileInputStream(filename);
         ObjectInputStream in = new ObjectInputStream(fileIn);
         this.pets = (ArrayList<Pet>) in.readObject();
         in.close();
         fileIn.close();
      } catch (IOException ex) {
         ex.printStackTrace();
         return;
      } catch (ClassNotFoundException c) {
         c.printStackTrace();
         return;
      }
   }

   @Override
   public String toString() {
      return "Animals{" + pets + '}';
   }
}
