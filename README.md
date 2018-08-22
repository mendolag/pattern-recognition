# Pattern Recognition
With this application, we want to solve the following problem:
Given a set of P feature points in the bidimensional plane, we want to determine every line that contains at least N or more collinear points.

### Prerequisites
Maven needs to be installed on your machine in order to run the application. 

### How to run

The application is developed using Spring Boot.
In order to start it, go into the project root folder in the terminal, and type the following command mvn spring-boot:run 

The Rest API can be reached after starting the application at http://localhost:8080/

## API

Add a point to the space
```
POST /point with body { "x": ..., "y": ... }
```

Get all points in the space
```
GET /space
```

Get all line segments passing through at least N points, where all points are collinear. 
```
GET /lines/{n}
```

Remove all points from the space
```
DELETE /space
```

## Implementation and Known Limitations 

Probably the algorithm is not one of the most perfect mathematically speaking, as there are probably some more efficient mathematical approaches to this problem.


My implementation consists in grouping all the segments according to their slope. These 2 points segments are created when some new point is inserted in the space.
When the user wants to retrieve the segments with N or more collinear points, the algorithm creates all the combinations of segments which are in the same group of slope. 
 
 
The solution I developed has a time complexity of O(n<sup>3</sup>) 

Points are not sorted on any of the axis.


## Alternatives
I thought about a different solution in which we could decrease the time complexity, while incrementing the space complexity. 
In order to achieve that, we would need to create all the different combinations right when inserting a point, and also to group these combinations by the starting point.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Author

* Giuseppe Mendola