const AV_URL = "https://www.alphavantage.co/query?";

export const buildUrl = (ticker, fcn, apikey) => {
  const parms = [
    `function=${fcn}`,
    `symbol=${ticker}`,
    `apikey=${apikey}`
  ]
  return AV_URL + parms.join('&');
}

