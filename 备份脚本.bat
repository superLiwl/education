@echo off
set "Ymd=%date:~,4%%date:~5,2%%date:~8,2%%time:~0,2%%time:~3,2%%time:~6,2%"
cd D:\work\mysql-5.7.26-winx64\bin
mysqldump --opt --single-transaction=TRUE --user=root --password=123456 --host=127.0.0.1 --protocol=tcp --port=3306 --default-character-set=utf8 --single-transaction=TRUE --routines --events "jeesite" > D:/sql/jeesite_%Ymd%.sql
@echo on
