package com.itschool.classwork;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Animals implements Serializable {
   ArrayList<Pet> pets;
   String zooTitle;

   public Animals(ArrayList<Pet> pets) {
      this.pets = pets;
      zooTitle = "Best ZOO";
   }

   public Animals(ArrayList<Pet> pets, String zooTitle) {
      this.pets = pets;
      this.zooTitle = zooTitle;
   }

   public Animals() {
      this.pets = new ArrayList<>();
   }

   public void add(Pet... pet) {
      this.pets.addAll(Arrays.asList(pet));
   }

   public void save(String filename) {
      try {
         FileOutputStream fileOut = new FileOutputStream(filename);
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(this);
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
         Animals loaded = (Animals) in.readObject();

         this.zooTitle = loaded.zooTitle;
         this.pets = loaded.pets;

         in.close();
         fileIn.close();
      } catch (IOException | ClassNotFoundException ex) {
         ex.printStackTrace();
      }
   }

   @Override
   public String toString() {
      return "Animals{" + pets + '}';
   }
}