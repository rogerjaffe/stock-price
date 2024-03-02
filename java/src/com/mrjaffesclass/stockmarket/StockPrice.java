/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mrjaffesclass.stockmarket;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * The StockPrice class represents the stock prices for a particular day We
 * include the open, close, high and low prices as well as the trading volume
 * for the day
 *
 * @author Roger Jaffe
 */
public class StockPrice {

  private Calendar date;
  private double open;
  private double high;
  private double low;
  private double close;
  private long volume;

  /**
   *
   * @param date The date as a Calendar data type
   * @param open Opening price
   * @param high High price for the day
   * @param low Low price for the day
   * @param close Closing price for the day
   * @param volume Trading volume for the day
   */
  public StockPrice(String date, double open, double high, double low, double close, long volume) {
    int y = Integer.parseInt(date.substring(0, 4));
    int m = Integer.parseInt(date.substring(5, 7)) - 1;
    int d = Integer.parseInt(date.substring(8, 10));
    this.date = new GregorianCalendar(y, m, d);
    this.open = open;
    this.low = low;
    this.high = high;
    this.close = close;
    this.volume = volume;
  }

  /**
   * Get the date
   *
   * @return Trading date
   */
  public Calendar getDate() {
    return date;
  }

  /**
   * Get opening price
   *
   * @return The opening price
   */
  public double getOpen() {
    return open;
  }

  /**
   * Get highest price
   *
   * @return The highest price
   */
  public double getHigh() {
    return high;
  }

  /**
   * Get lowest price
   *
   * @return The lowest price
   */
  public double getLow() {
    return low;
  }

  /**
   * Get closing price
   *
   * @return The closing price
   */
  public double getClose() {
    return close;
  }

  /**
   * Get trading volume
   *
   * @return The trading volume
   */
  public double getVolume() {
    return volume;
  }

}
