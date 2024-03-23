#### Stock Price History API for APCSA project

##### Google Cloud information

* Hosted in `rogerjaffe@mrjaffesclass.com` account
* Project is stock-market-api
* Project instructions for students are at `https://docs.google.com/document/d/1NIlGaCrOesBGDm5loCndrTImyfETr_05VlI2Jq-gAuM/edit`

##### Google Cloud Function

URL is `https://us-west1-stock-market-api-415819.cloudfunctions.net/stock-market-api?ticker=<TICKER>&fcn=TIME_SERIES_DAILY&apikey=<API_KEY>`

* Replace <TICKER> with the ticker symbol of the stock price you are retrieving
* Replace <API_KEY> with the API Key you get from AlphaVantage

##### Google Cloud Bucket

Bucket `stock-price-api` is used to host the Java documentation for the StockPrice class
