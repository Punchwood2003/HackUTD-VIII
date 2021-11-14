@echo off
powershell -Command "(New-Object Net.WebClient).DownloadFile('https://apewisdom.io/api/v1.0/filter/all-stocks/', 'top100Stocks.txt')"
for /f "tokens=*" %%A in (top100Stocks.txt) do (echo%%A)
exit