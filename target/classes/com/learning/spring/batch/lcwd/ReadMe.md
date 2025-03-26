# ReadMe - About this exercise

This exercise is a reproduction of the example by Durgesh on his YouTube Channel
(LCWD - Learn Code With Durgesh).

> File: _🔥Batch Processing in one shot ｜ Spring Boot Batch  Processing in  Hindi [Ab_-ZsD-zV8]_

## Objective

* An example to demonstrate Spring Batch with Spring boot with the following steps
	* Reader - reads the Product details in a data.csv file
	* Processor - calculate the discount and apply the discounted price to a new column in DTO/Model
	* Writer - writes to the MySQL database using JDBC

## Known Caveats

There are a few known caveats in this example

* He has not skipped he header
* He has used `varchar` for all the column values for the errors while running the example
