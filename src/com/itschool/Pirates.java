package com.itschool;

import java.io.*;
import java.util.ArrayList;

class Pirates {
   private ArrayList<Pirate> pirates;

   public Pirates() {
      pirates = new ArrayList<>();
   }

   public Pirates(ArrayList<Pirate> pirates) {
      this.pirates = pirates;
   }

   public void Add(Pirate pirate) {
      this.pirates.add(pirate);
   }

   public void Save(String filename) {
      try {
         FileOutputStream fileOut = new FileOutputStream(filename);
         ObjectOutputStream out = new ObjectOutputStream(fileOut);

         out.writeObject(pirates);

         // out.flush();
         out.close();
         //fileOut.flush();
         fileOut.close();
         System.out.print("Serialized data is saved in file: " + filename);
      } catch (IOException ex) {
         ex.printStackTrace();
      }
   }

   public void Load(String filename) {
      try {
         FileInputStream fileIn = new FileInputStream(filename);
         ObjectInputStream in = new ObjectInputStream(fileIn);

         this.pirates = (ArrayList<Pirate>) in.readObject();

         in.close();
         fileIn.close();
      } catch (IOException ex) {
         ex.printStackTrace();
      } catch (ClassNotFoundException c) {
         System.out.println("Pirate class not found");
         c.printStackTrace();
      }
   }

   public void Clear() {
      this.pirates.clear();
   }

   @Override
   public String toString() {
      StringBuilder result = new StringBuilder();
      for (Pirate p : this.pirates)
         result.append(p.toString());

      return result.toString();
   }

   public Pirates filterByName(String name) {
      Pirates filteredPirates = new Pirates();
      for (Pirate p : this.pirates) {
         if (p.getName().toLowerCase().contains(name.strip().toLowerCase())) filteredPirates.pirates.add(p);
      }
      return filteredPirates;
   }
}
