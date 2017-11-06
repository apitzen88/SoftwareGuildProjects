use SuperHeroSightings;

select * from sightings;
select * from Hero;
select * from organization;
select * from location;
select * from heroorganization;

select SightingDate, HeroName, h.Description,
LocationName, l.Description, City,
Latitude, longitude 
 from sightings s
 join hero h on s.HeroID = h.heroID
 join location l on s.locationID = l.LocationID
where s.SightingDate = '2017-10-19';

select SightingDate, HeroName, h.Description,
LocationName, l.Description, City,
Latitude, longitude 
 from sightings s
 join hero h on s.HeroID = h.heroID
 join location l on s.locationID = l.LocationID
where s.SightingDate = '2017-10-19' and l.LocationName = 'Central City';

select locationName, SightingDate
from sightings s
join hero h on s.HeroID = h.heroId
join location l on s.LocationID = l.LocationName
where h.HeroID = 1;

