package com.itschool;

import java.util.Comparator;

public class Currency {
   public static Comparator<Currency> bySale = (o1, o2) -> Double.parseDouble(o1.getSale()) > Double.parseDouble(o2.getSale()) ? 1 : -1;
   public static Comparator<Currency> byBuy = (o1, o2) -> Double.parseDouble(o1.getBuy()) > Double.parseDouble(o2.getBuy()) ? 1 : -1;
   public static Comparator<Currency> byName = (o1, o2) -> o1.ccy.compareTo(o2.ccy) > 0 ? 1 : -1;
   private String ccy;
   private String base_ccy;
   private String buy;
   private String sale;

   public Currency(String ccy, String baseCcy, String buy, String sale) {
      this.ccy = ccy;
      this.base_ccy = baseCcy;
      this.buy = buy;
      this.sale = sale;
   }

   public Currency() {
   }

   public String getCcy() {
      return ccy;
   }

   public void setCcy(String ccy) {
      this.ccy = ccy;
   }

   public String getBase_ccy() {
      return base_ccy;
   }

   public void setBase_ccy(String base_ccy) {
      this.base_ccy = base_ccy;
   }

   public String getBuy() {
      return buy;
   }

   public void setBuy(String buy) {
      this.buy = buy;
   }

   public String getSale() {
      return sale;
   }

   public void setSale(String sale) {
      this.sale = sale;
   }

   @Override
   public String toString() {
      return "Currency{" + "ccy='" + ccy + '\'' + ", baseCcy='" + base_ccy + '\'' + ", buy=" + buy + ", sale=" + sale + '}';
   }
}