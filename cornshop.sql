﻿CREATE DATABASE CORNSHOP
USE CORNSHOP

--Bảng sản phẩm
CREATE TABLE PRODUCT
(
	ID INT IDENTITY (1,1),
	PRODUCTID AS ('PRD' + right(replicate('0', 4) 
		+ cast(ID as varchar(4)), 4)) persisted not null,
	PRODUCTNAME NVARCHAR(40) UNIQUE,
	PRICE INT NOT NULL,
	MATERIAL NVARCHAR(30) NULL,
	CATEGORYID INT NOT NULL,
	BRANDID INT NOT NULL
);

--Bảng loại sản phẩm
CREATE TABLE CATEGORY
(
	ID INT IDENTITY (1,1),
	CATEGORYID AS ('CAT' + right(replicate('0', 3) 
		+ cast(ID as varchar(3)), 3)) persisted not null,
	CATEGORYNAME NVARCHAR(30) UNIQUE,
);

--Bảng nhãn hiệu
CREATE TABLE BRAND
(
	ID INT IDENTITY (1,1),
	BRANDID AS ('BRD' + right(replicate('0', 3) 
		+ cast(ID as varchar(3)), 3)) persisted not null,
	BRANDNAME NVARCHAR(30) UNIQUE,
	SUPPLIERID INT NOT NULL
);

--Bảng nhà cung cấp
CREATE TABLE SUPPLIER
(
	ID INT IDENTITY (1,1),
	SUPPLIERID AS ('SPL' + right(replicate('0', 3) 
		+ cast(ID as varchar(3)), 3)) persisted not null,
	SUPPLIERNAME NVARCHAR(40) UNIQUE,
);

--Bảng đơn hàng
CREATE TABLE ORDERS
(
	ID INT IDENTITY (1,1),
	ORDERID AS ('ORD' + right(replicate('0', 7) 
		+ cast(ID as varchar(7)), 7)) persisted not null,
	ORDERDATE DATE,
	TOTALPRICE INT,
	CUSTOMER VARCHAR(10),
	CASHIER INT NOT NULL,
);

--Bảng chi tiết đơn hàng
CREATE TABLE ORDERDETAILS
(
	ID INT IDENTITY (1,1),
	ORDERID INT NOT NULL,
	PRODUCTID INT NOT NULL,
	COLORID INT,
	SIZEID INT,
	QUANTITY INT
);


--Bảng phiếu nhập
CREATE TABLE INCOMEBILL
(
	ID INT IDENTITY (1,1),
	INCOMEBILLID AS ('ICB' + right(replicate('0', 7) 
		+ cast(ID as varchar(7)), 7)) persisted not null,
	CREATEDATE DATE,
	STOREKEEPER INT NOT NULL,
	SUPPLIERID INT NOT NULL
);

--Bảng chi tiết phiếu nhập
CREATE TABLE INCOMEDETAILS
(
	ID INT IDENTITY (1,1),
	INCOMEBILLID INT NOT NULL,
	PRODUCTID INT NOT NULL,
	QUANTITY INT
);

--Bảng màu
CREATE TABLE COLOR
(
	ID INT IDENTITY(1,1),
	COLORNAME NVARCHAR(20),
	RED INT,
	GREEN INT,
	BLUE INT
);

--Bảng kích thươc
CREATE TABLE SIZE
(
	ID INT IDENTITY(1,1),
	SIZE CHAR(6)
);

--Bảng tồn kho
CREATE TABLE STORAGE
(
	ID INT IDENTITY(1,1),
	PRODUCTID INT NOT NULL,
	COLOR INT NOT NULL,
	SIZE INT NOT NULL,
	QUANTITY INT
);

--Bảng nhân viên
CREATE TABLE EMPLOYEE
(
	ID INT IDENTITY (1,1),
	EMPLOYEEID AS ('EMP' + right(replicate('0', 4) 
		+ cast(ID as varchar(4)), 4)) persisted not null,
	EMPLOYEENAME NVARCHAR(40) UNIQUE NOT NULL,
	PHONE VARCHAR(10),
	POSITION NVARCHAR(20),
	GENDER NVARCHAR(3)
);

--Bảng khách hàng
CREATE TABLE CUSTOMER
(
	PHONE VARCHAR(10) UNIQUE NOT NULL,
	NAME NVARCHAR(40) NULL,
	GENDER NVARCHAR(3),
	LASTPURCHASE INT,
	CURRENTPURCHASE INT
);

CREATE TABLE ACCOUNT
(
	ID INT IDENTITY (1,1),
	USERNAME VARCHAR(20) NOT NULL,
	USERPASS VARCHAR(64) NOT NULL,
	EMPLOYEEID INT,
);

-- TẠO KHÓA CHÍNH

--Khóa chính bảng sản phẩm
ALTER TABLE PRODUCT ADD CONSTRAINT PK_PRODUCT PRIMARY KEY (ID)

--Khóa chính bảng loại sản phẩm
ALTER TABLE CATEGORY ADD CONSTRAINT PK_CATEGORY PRIMARY KEY (ID)

--Khóa chính bảng nhãn hiệu
ALTER TABLE BRAND ADD CONSTRAINT PK_BRAND PRIMARY KEY (ID)

--Khóa chính bảng nhà cung cấp
ALTER TABLE SUPPLIER ADD CONSTRAINT PK_SUPPLIER PRIMARY KEY (ID)

--Khóa chính bảng đơn hàng
ALTER TABLE ORDERS ADD CONSTRAINT PK_ORDERS PRIMARY KEY (ID)

--Khóa chính bảng phiếu nhập
ALTER TABLE INCOMEBILL ADD CONSTRAINT PK_INCOMEBILL PRIMARY KEY (ID)

--Khóa chính bảng nhân viên
ALTER TABLE EMPLOYEE ADD CONSTRAINT PK_EMPLOYEE PRIMARY KEY (ID)

--Khóa chính bảng khách hàng
ALTER TABLE CUSTOMER ADD CONSTRAINT PK_CUSTOMER PRIMARY KEY (PHONE)

--Khóa chính bảng tồn kho
ALTER TABLE STORAGE ADD CONSTRAINT PK_STORAGE PRIMARY KEY (ID)

--Khóa chính bảng phiếu nhập
ALTER TABLE INCOMEDETAILS ADD CONSTRAINT PK_INCOMEDETAILS PRIMARY KEY (ID)

--Khóa chính bảng chi tiết phiếu nhập
ALTER TABLE ORDERDETAILS ADD CONSTRAINT PK_ORDERDETAILS PRIMARY KEY (ID)

--Khóa chính bảng màu
ALTER TABLE COLOR ADD CONSTRAINT PK_COLOR PRIMARY KEY (ID)

--Khóa chính bảng kích thước
ALTER TABLE SIZE ADD CONSTRAINT PK_SIZE PRIMARY KEY (ID)

--Khóa chính bảng tài khoản đăng nhập
ALTER TABLE ACCOUNT ADD CONSTRAINT PK_ACCOUNT PRIMARY KEY (ID)


--TẠO KHÓA NGOẠI

--Khóa ngoại bảng sản phẩm
ALTER TABLE PRODUCT ADD 
	CONSTRAINT FK_PRODUCT_CATEGORY FOREIGN KEY (CATEGORYID)
		REFERENCES CATEGORY(ID),
	CONSTRAINT FK_PRODUCT_BRAND FOREIGN KEY (BRANDID)
		REFERENCES BRAND(ID)

--Khóa ngoại bảng nhãn hiệu
ALTER TABLE BRAND ADD 
	CONSTRAINT FK_BRAND_SUPPLIER FOREIGN KEY (SUPPLIERID)
		REFERENCES SUPPLIER(ID)

--Khóa ngoại bảng đơn hàng
ALTER TABLE ORDERS ADD 
	CONSTRAINT FK_ORDERS_EMPLOYEE FOREIGN KEY (CASHIER)
		REFERENCES EMPLOYEE(ID),
	CONSTRAINT FK_ORDERS_CUSTOMER FOREIGN KEY (CUSTOMER)
		REFERENCES CUSTOMER(PHONE)

--Khóa ngoại bảng chi tiết đơn hàng
ALTER TABLE ORDERDETAILS ADD 
	CONSTRAINT FK_ORDERDETAILS_ORDERS FOREIGN KEY (ORDERID)
		REFERENCES ORDERS(ID),
	CONSTRAINT FK_ORDERDETAILS_PRODUCT FOREIGN KEY (PRODUCTID)
		REFERENCES PRODUCT(ID),
	CONSTRAINT FK_ORDERDETAILS_COLOR FOREIGN KEY (COLORID)
		REFERENCES COLOR(ID),
	CONSTRAINT FK_ORDERDETAILS_SIZE FOREIGN KEY (SIZEID)
		REFERENCES SIZE(ID)
--Khóa ngoại bảng phiếu nhập
ALTER TABLE INCOMEBILL ADD 
	CONSTRAINT FK_INCOMEBILL_SUPPLIER FOREIGN KEY (SUPPLIERID)
		REFERENCES SUPPLIER(ID),
	CONSTRAINT FK_INCOMEBILL_EMPLOYEE FOREIGN KEY (STOREKEEPER)
		REFERENCES EMPLOYEE(ID)

--Khóa ngoại bảng chi tiết phiếu nhập
ALTER TABLE INCOMEDETAILS ADD 
	CONSTRAINT FK_INCOMEDETAILS_INCOMEBILL FOREIGN KEY (INCOMEBILLID)
		REFERENCES INCOMEBILL(ID),
	CONSTRAINT FK_INCOMEDETAILS_PRODUCT FOREIGN KEY (PRODUCTID)
		REFERENCES PRODUCT(ID)

--Khóa ngoại bảng tồn kho
ALTER TABLE STORAGE ADD 
	CONSTRAINT FK_STORAGE_PRODUCT FOREIGN KEY (PRODUCTID)
		REFERENCES PRODUCT(ID),
	CONSTRAINT FK_STORAGE_COLOR FOREIGN KEY (COLOR)
		REFERENCES COLOR(ID),
	CONSTRAINT FK_STORAGE_SIZE FOREIGN KEY (SIZE)
		REFERENCES SIZE(ID)

ALTER TABLE ACCOUNT ADD
	CONSTRAINT FK_ACCOUNT_EMPLOYEE FOREIGN KEY (EMPLOYEEID)
		REFERENCES EMPLOYEE(ID)

