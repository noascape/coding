-- SELECT vorname, `nachname` FROM teilnehmer WHERE NOT nachname = 'Wolfer' ORDER BY nachname, vorname; 
-- SELECT * FROM teilnehmer WHERE (vorname <> 'Noah' ) OR (vorname IS NULL) es gibt auch != für ungleich
-- SELECT * FROM kurs.teilnehmer LIMIT 3;      oder  USE kurs; SELECT * FROM teilnehmer;
-- SELECT * FROM teilnehmer LIMIT 2 OFFSET 4;
-- SELECT * FROM teilnehmer LIMIT 4;
-- SELECT `NAME`, Continent, SurfaceArea FROM world.country;
-- SELECT COUNT(*), Continent, SUM(SurfaceArea) AS Surface FROM world.country WHERE Continent LIKE 'A%' GROUP BY Continent HAVING Surface > 15000000 AND Continent LIKE '_F%' ORDER BY Continent DESC;                     -- ASC 
-- SELECT COUNT(DISTINCT vorname) FROM teilnehmer;
 SELECT * FROM world.country LEFT JOIN countrylanguage ON country.Code = countrylanguage.CountryCode WHERE countrylanguage.CountryCode IS NULL;     -- LEFT JOIN dann kommen auch die NULL Werte