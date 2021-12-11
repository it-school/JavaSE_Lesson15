package com.itschool.classwork;

import java.io.*;
import java.util.Calendar;

public class Pet implements Serializable, Externalizable {
   private PetType type;
   private String nickname;
   private Calendar birthday;
   private String master;

   public Pet() {
   }

   public Pet(PetType type, String nickname, Calendar birthday, String master) {
      this.type = type;
      this.nickname = nickname;
      this.birthday = birthday;
      this.master = master;
   }

   public String getMaster() {
      return master;
   }

   public void setMaster(String master) {
      this.master = master;
   }

   public PetType getType() {
      return type;
   }

   public void setType(PetType type) {
      this.type = type;
   }

   @Override
   public String toString() {
      return "\n\t" + nickname + " (" + type + ')' + " - " + birthday.getTime().toLocaleString() + " (master: " + this.master + ")";
   }

   public String getNickname() {
      return nickname;
   }

   public void setNickname(String nickname) {
      this.nickname = nickname;
   }

   public Calendar getBirthday() {
      return birthday;
   }

   public void setBirthday(Calendar birthday) {
      this.birthday = birthday;
   }

   @Override
   public void writeExternal(ObjectOutput out) throws IOException {
      out.writeObject(type);
      out.writeUTF(nickname);
      out.writeObject(birthday);
   }

   @Override
   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
      this.type = (PetType) in.readObject();
      this.nickname = in.readUTF();
      this.birthday = (Calendar) in.readObject();
   }
}
