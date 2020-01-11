SET SQL_SAFE_UPDATES = 0;

/*Q1*/
/*IDA*/
SELECT * FROM flight_schedule FS 
JOIN airports A
ON FS.airport_takeoff_id=A.id
JOIN aircrafts AIR
ON FS.aircraft_id = AIR.id
WHERE takeoff_date >= '2019-06-13 00:00:00' and  takeoff_date< '2019-06-14 00:00:00' AND airport_takeoff_id='LAX' AND airport_arrival_id='TGU'
AND (AIR.capacity - AIR.seats_taken) >= 1;

/*VUELTA*/
SELECT * FROM flight_schedule FS 
JOIN AIRPORTS A
ON FS.airport_takeoff_id=A.id
JOIN aircrafts AIR
ON FS.aircraft_id = AIR.id
WHERE takeoff_date >= '2019-06-18 00:00:00' and  takeoff_date< '2019-06-19 00:00:00' AND airport_takeoff_id='TGU' AND airport_arrival_id='LAX'
AND (AIR.capacity - AIR.seats_taken) >= 1;

/*Q2*/

SELECT * FROM flight_schedule FS 
JOIN airports A
ON FS.airport_takeoff_id=A.id
JOIN aircrafts AIR
ON FS.aircraft_id = AIR.id
WHERE takeoff_date >= '2019-06-10 00:00:00' and  takeoff_date< '2019-06-17 00:00:00' AND airport_takeoff_id='LAX' AND airport_arrival_id='TGU'
AND (AIR.capacity - AIR.seats_taken) >= 1;

/*Q3 Part 1: Vuelos pendientes de la agencia 4*/

SELECT * FROM flight_schedule FS
INNER JOIN reservations R
ON FS.id = R.flight_schedule_id
where arrival_date > now() AND
r.agencies_id = 4;

/*Q3 Part 2: Vuelos realizados por la agencia 4*/
SELECT * FROM flight_schedule FS
INNER JOIN reservations R
ON FS.id = R.flight_schedule_id
where arrival_date < now() AND
r.agencies_id = 4;

/*Q4 BOARDING PASS*/

SELECT * FROM reservation_passengers 
WHERE reservation_id = 5 and seat_number is null;

/*Q5*/
/*Able to cancel*/
SELECT * FROM reservations R INNER JOIN flight_schedule FS
ON R.flight_schedule_id = FS.id 
WHERE FS.takeoff_date > '2019-06-09 00:00:00' and FS.takeoff_date < '2019-06-16 00:00:00' AND R.status = 1;

/*Able to changue*/
SELECT * FROM reservations R INNER JOIN flight_schedule FS
ON R.flight_schedule_id = FS.id
INNER JOIN reservation_passengers RP
ON RP.reservation_id = R.id
WHERE FS.takeoff_date > '2019-06-09 00:00:00' and FS.takeoff_date < '2019-06-16 00:00:00' AND R.status = 1 AND R.id=19 AND seat_number is not null ;

/*QUERY 6*/
SELECT p.id, lastname, name, COUNT(*) AS times_priority FROM reservation_passengers RP 
JOIN passenger P 
ON P.id = RP.passenger_id
where RP.priority = 1
GROUP BY P.id
HAVING times_priority>2;

/*QUERY 7*/
SELECT A.munipality, A.continent, A.id, SUM(RP.flight_rate) as payment FROM flight_schedule FS 
join reservations R 
ON FS.id = R.flight_schedule_id
JOIN reservation_passengers RP
ON R.id = RP.reservation_id 
JOIN airports A 
on A.id = FS.airport_arrival_id
WHERE R.reservation_date BETWEEN (CURRENT_DATE() - INTERVAL 1 MONTH) AND CURRENT_DATE() 
GROUP BY FS.airport_arrival_id
ORDER BY payment DESC LIMIT 10;

/*QUERY 8*/
SELECT YEAR(R.reservation_date), MONTH(R.reservation_date), sum(RP.flight_rate) 
FROM reservations R JOIN reservation_passengers RP
ON R.id = RP.reservation_id
GROUP BY YEAR(R.reservation_date), MONTH(R.reservation_date)
ORDER BY YEAR(R.reservation_date), MONTH(R.reservation_date) DESC;
