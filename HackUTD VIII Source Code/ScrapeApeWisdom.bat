@echo off
powershell -Command "(New-Object Net.WebClient).DownloadFile('https://apewisdom.io/api/v1.0/filter/all-stocks/', 'top100Stocks.txt')"
for /f "tokens=*" %%A in (top100Stocks) do (echo%%A)
powershell -Command "(New-Object Net.WebClient).DownloadFile('https://apewisdom.io/api/v1.0/filter/all-crypto', 'top100Cryptos.txt')"
for /f "tokens=*" %%A in (top100Cryptos.txt) do (echo%%A)