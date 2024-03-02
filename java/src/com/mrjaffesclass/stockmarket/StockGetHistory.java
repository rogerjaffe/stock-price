package com.mrjaffesclass.stockmarket;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * StockGetHistory class
 *
 * This class is used by AP Computer Science A as a replacement to the
 * YahooFinance stock price API that was rendered useless when Yahoo shut down
 * their service
 *
 * The class was inspired by
 * https://mkyong.com/java/java-https-client-httpsurlconnection-example/ which
 * has a wonderful example of how to make an HTTPS call
 *
 * @author Roger Jaffe
 * @version 1.0
 */
public class StockGetHistory {

  private final String URL = "https://us-west1-stock-market-api-415819.cloudfunctions.net/stock-market-api?";
  private final String API_KEY;
  private final String ticker;
  private final ArrayList<String> lines;
  private final ArrayList<StockPrice> stockPrices;

  /**
   * Constructor for the StockAPI class
   *
   * Get an AlphaVantage API key from https://www.alphavantage.co/support
   *
   * @param apiKey An AlphaVantage API key
   * @param ticker Stock ticker symbol
   */
  public StockGetHistory(String apiKey, String ticker) {
    this.ticker = ticker;
    this.API_KEY = apiKey;
    this.lines = new ArrayList<>();
    this.stockPrices = new ArrayList<>();
    HttpsURLConnection con = this.getConnection(ticker);
    getContent(con);
    this.parseLines();
  }

  /**
   * getConnection makes a connection to the URL
   *
   * @param ticker The ticker symbol of the requested stock
   * @return A string of CSV data with a header line
   */
  private HttpsURLConnection getConnection(String ticker) {
    URL url;
    HttpsURLConnection con = null;
    String https_url = URL + "ticker=" + ticker + "&fcn=TIME_SERIES_DAILY&apikey=" + API_KEY;
    try {
      url = new URL(https_url);
      con = (HttpsURLConnection) url.openConnection();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return con;
  }

  /**
   * getContent gets the content from the HTTPS connection
   *
   * @param con HTTPS connection
   */
  private void getContent(HttpsURLConnection con) {
    if (con != null) {
      try {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream())
        );
        String input;
        while ((input = br.readLine()) != null) {
          this.lines.add(input);
        }
        br.close();

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Parse the CSV lines from the HTTPS call
   */
  private void parseLines() {
    boolean first = true;
    for (String line : this.lines) {
      if (first) {
        first = false;
      } else {
        Scanner s = new Scanner(line).useDelimiter(",");
        String date = s.next();
        double open = s.nextDouble();
        double high = s.nextDouble();
        double low = s.nextDouble();
        double close = s.nextDouble();
        long volume = s.nextLong();
        StockPrice sp = new StockPrice(date, open, high, low, close, volume);
        stockPrices.add(sp);
      }
    }
  }

  /**
   * Get stock ticker
   *
   * @return Stock ticker
   */
  public String getTicker() {
    return ticker;
  }

  /**
   * Get the ArrayList of stock price data
   *
   * @return Stock price data
   */
  public ArrayList<StockPrice> getStockPrices() {
    return stockPrices;
  }
}
