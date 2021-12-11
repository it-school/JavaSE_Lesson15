package com.itschool;

class Pirate implements java.io.Serializable {
   String name;
   String address;
   int age; // transient отменяет сериализацию значения свойства

   public Pirate() {
   }

   public Pirate(String name, String address, int age) {
      this.name = name;
      this.address = address;
      this.age = age;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public int getAge() {
      return age;
   }

   public void setAge(int age) {
      this.age = age;
   }

   @Override
   public String toString() {
      return "\nName: " + this.name + ", address: " + this.address + ", age: " + this.age;
   }
}
