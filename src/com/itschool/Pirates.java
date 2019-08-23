package com.itschool;

import java.io.*;
import java.util.ArrayList;

class Pirates {
    private ArrayList<Pirate> pirates;

    public Pirates()
    {
        pirates = new ArrayList<>();
    }

    public void Add(Pirate pirate)
    {
        this.pirates.add(pirate);
    }

    public void Save(String filename)
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(pirates);
            out.flush();
            out.close();
            fileOut.flush();
            fileOut.close();
            System.out.print("Serialized data is saved in file: " + filename);
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public void Load(String filename)
    {
        try
        {
            File file;
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.pirates = (ArrayList<Pirate>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException ex)
        {
            ex.printStackTrace();
            return;
        } catch (ClassNotFoundException c)
        {
            System.out.println("Pirate class not found");
            c.printStackTrace();
            return;
        }
    }

    public void Clear()
    {
        this.pirates.clear();
    }

    @Override
    public String toString()
    {
        String result = "";
        for (Pirate p : this.pirates)
        {
            result += p.toString();
        }

        return result;
    }


}
