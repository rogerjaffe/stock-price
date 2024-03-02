import functions from '@google-cloud/functions-framework';
import fetch from 'node-fetch';
import { buildUrl } from './buildUrl.js';

const HEADER = "Date,Open,High,Low,Close,Volume";

/**
 * Use the Alpha Vantage API to get stock price history.
 * Return the data as a CSV file for APCSA students to process
 * using a Java Scanner, then plot the data using Flourish
 *
 * AV API functions used for this project: TIME_SERIES_DAILY
 *
 * Sample fetch URL: https://us-west1-stock-market-api-415819.cloudfunctions.net/stock-market-api?ticker=<TICKER_SYMBOL>&fcn=<FUNCTION>&apikey=<API_KEY>
 *
 * Query parameters (all are required):
 *
 * @param {string} ticker - the stock symbol to query
 * @param {string} fcn - the Alpha Vantage function to call
 * @param {string} apikey - the API key to use
 *
 * @type {string}
 */

functions.http('getStockHistory', (req, res) => {
  const {ticker, fcn, apikey} = req.query;
  res.setHeader('Content-Type', 'text/csv');
  if (!ticker || !fcn || !apikey) {
    res.status(200).send('Missing required query parameters');
    return;
  }
  const url = buildUrl(ticker, fcn, apikey);

  // Get the data from AlphaVantage
  fetch(url)
    .then((res) => res.json())
    .then((data) => {
      // Check for AlphaVantage API error
      if (data['Error Message']) {
        res.status(200).send('No data found for ' + ticker);
        return;
      }

      // CSV Header string
      const csvHeader = [HEADER];

      // Retrieve daily values from response object
      const dailyValues = data['Time Series (Daily)'];

      // Get the dates and sort them earliest to latest
      const dates = Object.keys(dailyValues);
      dates.sort((a,b) => a < b ? -1 : 1);

      // Create an array of CSV lines
      const dateHeaders = ['1. open', '2. high', '3. low', '4. close', '5. volume'];
      const csvLines = dates.map((date) => {
        const valueStrs = dateHeaders.map((header) => dailyValues[date][header]);
        const values = valueStrs.map((str) => parseFloat(str));
        return date + ',' + values.join(',');
      })

      // Put it all together and send the CSV
      const csv = csvHeader.concat(csvLines);
      const csvStr = csv.join("\n");
      res.status(200).send(csvStr);
    })
});
