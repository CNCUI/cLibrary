# mysql
mysql补全日期
天：
```
SELECT
	@num := @num + 1 AS num,
	DATE_FORMAT( ADDDATE( DATE_SUB( '2019-08-13', INTERVAL 1 DAY ), INTERVAL @num DAY ), '%Y-%m-%d' ) AS date 
FROM
	project,
	( SELECT @num := 0 ) t 
WHERE
	DATE_FORMAT( ADDDATE( DATE_SUB( '2019-08-13', INTERVAL 1 DAY ), INTERVAL @num DAY ), '%Y-%m-%d' ) < DATE_FORMAT( NOW( ), '%Y-%m-%d' );
```
小时：
```
SELECT
	@num := @num + 1 AS num,
	DATE_FORMAT( ADDDATE( DATE_SUB( '2019-08-13 08', INTERVAL 1 HOUR ), INTERVAL @num HOUR ), '%Y-%m-%d %H' ) AS date 
FROM
	project,
	( SELECT @num := 0 ) t 
WHERE
	DATE_FORMAT( ADDDATE( DATE_SUB( '2019-08-13 08', INTERVAL 1 HOUR ), INTERVAL @num HOUR ), '%Y-%m-%d %H' ) < DATE_FORMAT( NOW( ), '%Y-%m-%d %H' );
```
