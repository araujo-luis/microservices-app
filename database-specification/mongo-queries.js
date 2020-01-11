db.airportControl.insert({ "passport": "1234", "name": "Luis"});
db.airportControl.insert({ "passport": "asd3", "name": "Juan"});
db.airportControl.insert({ "passport": "21c3", "name": "Camilla"});
db.airportControl.insert({ "passport": "asd44", "name": "Marta"});
db.airportControl.insert({ "passport": "ad423", "name": "Alejandra"});
db.airportControl.insert({ "passport": "ce345f", "name": "Leyre"});
db.airportControl.insert({"identification": "21c3", "securityGateDate": new Date(), "boardingDate": new Date("2019-06-03T23:00:00Z"), "gate": "A21", "airportDestiny": "LAX" });
db.airportControl.insert({"identification": "21c3", "securityGateDate": new Date("2019-06-03T23:00:00Z"), "boardingDate": new Date("2019-06-03T23:40:00Z"), "gate": "A21", "airportDestiny": "LAX" });
db.airportControl.insert({"identification": "21c3", "securityGateDate": new Date("2019-06-04T05:00:00Z"), "boardingDate": new Date("2019-06-04T05:21:00Z"), "gate": "A21", "airportDestiny": "LAX" });
db.airportControl.insert({"identification": "21c3", "securityGateDate": new Date("2019-06-04T07:00:00Z"), "boardingDate": new Date("2019-06-04T07:41:00Z"), "gate": "A21", "airportDestiny": "LAX" });
db.airportControl.insert({"identification": "21c3", "securityGateDate": new Date("2018-06-04T07:00:00Z"), "boardingDate": new Date("2018-06-04T07:41:00Z"), "gate": "A21", "airportDestiny": "LAX" });
db.airportControl.insert({"identification": "21c3", "securityGateDate": new Date("2019-06-04T07:00:00Z"), "boardingDate": new Date("2019-06-04T07:41:00Z"), "gate": "A21", "airportDestiny": "TGU" });
db.airportControl.insert({"identification": "21c3", "securityGateDate": new Date("2019-06-04T06:00:00Z"), "boardingDate": new Date("2019-06-04T07:01:00Z"), "gate": "A22", "airportDestiny": "TGU" });
db.airportControl.insert({"identification": "21c3", "securityGateDate": new Date("2019-06-04T06:00:00Z"), "boardingDate": new Date("2019-06-04T06:21:00Z"), "gate": "A22", "airportDestiny": "MAD" });
db.airportControl.insert({"identification": "21c3", "securityGateDate": new Date("2018-06-04T06:00:00Z"), "boardingDate": new Date("2018-06-04T06:21:00Z"), "gate": "A22", "airportDestiny": "MAD" });
db.airportControl.insert({"identification": "21c3", "securityGateDate": new Date("2018-06-04T06:00:00Z"), "boardingDate": new Date("2018-06-04T06:11:00Z"), "gate": "A22", "airportDestiny": "MAD" });
db.airportControl.insert({"identification": "21c3", "securityGateDate": new Date("2017-06-04T06:00:00Z"), "boardingDate": new Date("2017-06-04T06:21:00Z"), "gate": "A21", "airportDestiny": "VLC" });

//QUERY 9
db.airportControl.aggregate([
  { "$match": {
    "$and": [
      { "$expr": { "$eq": [{ "$year": "$boardingDate" }, 2019] }},
      { "airportDestiny": "MAD"}
    ]
  }},
  { "$group": {
    "_id": null,
    "average": {
      "$avg": { "$subtract": ["$boardingDate", "$securityGateDate"] }
    }
  }}
]);


// QUERY 10
db.airportControl.aggregate([
  { "$match": {
    "$and": [
      { "$expr": { "$eq": [{ "$year": "$boardingDate" }, 2018] }},
      { "gate": "A22"}
    ]
  }},
  { "$group": {
    "_id": null,
    "average": {
      "$avg": { "$subtract": ["$boardingDate", "$securityGateDate"] }
    }
  }}
]);

//SHOPS COLLECTION
db.shops.insert({
  "customer": "21c3",
  "shopDate": new Date("2019-06-03T23:00:00Z"),
  "airportDestiny": "LAX",
  "shopName" : "Supermarket",
  "items" : [
    {
      "productName": "Water",
      "price": 3,
      "quantity": 2
    },
    {
      "productName": "Candies",
      "price": 1,
      "quantity": 5
    }
  ]
});


db.shops.insert({
  "customer": "21c3",
  "shopDate": new Date("2019-06-05T13:00:00Z"),
  "airportDestiny": "TGU",
  "shopName" : "Supermarket",
  "items" : [
    {
      "productName": "Water",
      "price": 5,
      "quantity": 4
    },
    {
      "productName": "Candies",
      "price": 2,
      "quantity": 5
    }
  ]
});

db.shops.insert({
  "customer": "21c3",
  "shopDate": new Date("2019-06-05T13:00:00Z"),
  "airportDestiny": "TGU",
  "shopName" : "CandyShop",
  "items" : [
    {
      "productName": "Water",
      "price": 50,
      "quantity": 4
    },
    {
      "productName": "Candies",
      "price": 2,
      "quantity": 5
    }
  ]
});

db.shops.insert({
  "customer": "21c3",
  "shopDate": new Date("2018-06-05T13:00:00Z"),
  "airportDestiny": "TGU",
  "shopName" : "CandyShop",
  "items" : [
    {
      "productName": "Water",
      "price": 50,
      "quantity": 2
    },
    {
      "productName": "Candies",
      "price": 32,
      "quantity": 1
    }
  ]
});

//QUERY 11 Part 1
db.shops.aggregate(
   [
   {$unwind: "$items"},
   { "$match": {
     "$and": [
       { "$expr": { "$eq": [{ "$year": "$shopDate" }, 2018] }},
       { "airportDestiny": "TGU"}
     ]
   }},

      {
        $group : {
           _id : "$shopName",
          totalSold: { $sum: { $multiply: [ "$items.price", "$items.quantity" ] } },
          totalCustumers:{$sum:1}
        }
      }
   ]
);

//QUERY 11 Part 2
db.airportControl.createIndex( { "airportDestiny": 1 } );
db.shops.createIndex( { "shopName": 1 } );

db.shops.aggregate(
   [
   {$unwind: "$items"},
   { "$match": {
        "$expr": { "$eq": [{ "$year": "$shopDate" }, 2019] }
   }},

      {
        $group : {
           _id : "$shopName",
          totalSold: { $sum: { $multiply: [ "$items.price", "$items.quantity" ] } },
          totalCustumers:{$sum:1}
        }
      }
   ]
);
