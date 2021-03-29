package com.itschool.classwork;

import java.io.Serializable;
import java.util.Calendar;

public class Pet implements Serializable {
   private PetType type;
   private String nickname;
   private Calendar birthday;

   public Pet(PetType type, String nickname, Calendar birthday) {
      this.type = type;
      this.nickname = nickname;
      this.birthday = birthday;
   }

   public PetType getType() {
      return type;
   }

   public void setType(PetType type) {
      this.type = type;
   }

   @Override
   public String toString() {
      return "\n\t" + nickname + " (" + type + ')' + " - " + birthday.getTime().toLocaleString();
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
}
