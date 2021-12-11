package com.itschool;

public class Currency {
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