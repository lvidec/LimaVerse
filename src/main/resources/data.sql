DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS typeOfProduct;
DROP TABLE IF EXISTS product;


CREATE TABLE IF NOT EXISTS Customer(
	id INT NOT NULL AUTO_INCREMENT, 
	username VARCHAR(45) NOT NULL, 
	email VARCHAR(45) NOT NULL, 
	password VARCHAR(45) NOT NULL, 
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS typeOfProduct(
	id INT NOT NULL AUTO_INCREMENT, 
	typeName VARCHAR(45),
	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS Product(
	id INT NOT NULL AUTO_INCREMENT, 
	productName VARCHAR(45) NOT NULL, 
	productDescription VARCHAR(100) NOT NULL, 
	pathOfPic VARCHAR(100) NOT NULL, 
	price FLOAT NOT NULL,
	specs VARCHAR(100),
	resolution VARCHAR(20),
	idTypeOfProduct INT NOT NULL,
	PRIMARY KEY (id), 
	FOREIGN KEY(idTypeOfProduct) REFERENCES typeOfProduct(id)
);

INSERT INTO Customer (username, email, password) VALUES ('Lima', 'lima@gmail.com', 'lima12345');
INSERT INTO Customer (username, email, password) VALUES ('Leonardo', 'leonardo@gmail.com', 'leonardo12345');
INSERT INTO Customer (username, email, password) VALUES ('Videc', 'videc@gmail.com', 'videc12345');

INSERT INTO typeOfProduct (id, typeName) VALUES(DEFAULT, 'Laptop');
INSERT INTO typeOfProduct (id, typeName) VALUES(DEFAULT, 'TV');
INSERT INTO typeOfProduct (id, typeName) VALUES(DEFAULT, 'Headphone');

INSERT INTO product (id, productName, productDescription, pathOfPic, price, specs, resolution, idTypeOfProduct) VALUES(DEFAULT, 'Acer Swift', 'Acer desc', '\images\Laptops\Acer Swift.png', 800, 'Acer specs', NULL, 1);
INSERT INTO product (id, productName, productDescription, pathOfPic, price, specs, resolution, idTypeOfProduct) VALUES(DEFAULT, 'Dell XPS 15', 'Dell desc', '\images\Laptops\Dell XPS 15.png', 1000, 'Dell specs', NULL, 1);
INSERT INTO product (id, productName, productDescription, pathOfPic, price, specs, resolution, idTypeOfProduct) VALUES(DEFAULT, 'MacBook Pro', 'MacBook desc', '\images\Laptops\MacBook Pro.png', 1200, 'MacBook specs', NULL, 1);
INSERT INTO product (id, productName, productDescription, pathOfPic, price, specs, resolution, idTypeOfProduct) VALUES(DEFAULT, 'Razer Blade Pro', 'Razer desc', '\images\Laptops\Razer Blade Pro.png', 1500, 'Razor specs', NULL, 1);
INSERT INTO product (id, productName, productDescription, pathOfPic, price, specs, resolution, idTypeOfProduct) VALUES(DEFAULT, 'ThinkPad X1', 'ThinkPad desc', '\images\Laptops\ThinkPad X1.png', 500, 'ThinkPad specs', NULL, 1);

INSERT INTO product (id, productName, productDescription, pathOfPic, price, specs, resolution, idTypeOfProduct) VALUES(DEFAULT, 'LG', 'LG desc', '\images\TVs\LG.png', 1300, NULL, '4K', 2);
INSERT INTO product (id, productName, productDescription, pathOfPic, price, specs, resolution, idTypeOfProduct) VALUES(DEFAULT, 'Samsung', 'Samsung desc', '\images\TVs\Samsung.png', 3500, NULL, '8K', 2);
INSERT INTO product (id, productName, productDescription, pathOfPic, price, specs, resolution, idTypeOfProduct) VALUES(DEFAULT, 'Sony', 'Sony desc', '\images\TVs\Sony.png', 900, NULL, 'FULL HD', 2);

INSERT INTO product (id, productName, productDescription, pathOfPic, price, specs, resolution, idTypeOfProduct) VALUES(DEFAULT, 'Beats', 'Beats desc', '\images\Headphones\Beats.png', 840, NULL, NULL, 3);
INSERT INTO product (id, productName, productDescription, pathOfPic, price, specs, resolution, idTypeOfProduct) VALUES(DEFAULT, 'Bose', 'Bose desc', '\images\Headphones\Bose.png', 1050, NULL, NULL, 3);
INSERT INTO product (id, productName, productDescription, pathOfPic, price, specs, resolution, idTypeOfProduct) VALUES(DEFAULT, 'Sony WH3', 'Sony desc', '\images\Headphones\Sony WH3.png', 2420, NULL, NULL, 3);
INSERT INTO product (id, productName, productDescription, pathOfPic, price, specs, resolution, idTypeOfProduct) VALUES(DEFAULT, 'Philips', 'Philips desc', '\images\Headphones\Philips.png', 1420, NULL, NULL, 3);


