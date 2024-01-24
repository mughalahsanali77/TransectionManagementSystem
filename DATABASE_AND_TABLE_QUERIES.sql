--database creation query
CREATE DATABASE TRANSACTION_MANAGEMENT_SYSTEM;

USE  TRANSACTION_MANAGEMENT_SYSTEM;

--customer table creation query
CREATE TABLE CUSTOMER(
CUSTOMER_ID INT NOT NULL AUTO_INCREMENT,
FIRST_NAME VARCHAR(100) NOT NULL,
LAST_NAME VARCHAR(100) NOT NULL,
CITY VARCHAR(100) NOT NULL,
STATE VARCHAR (100) NOT NULL,
ADDRESS VARCHAR(250) NOT NULL,
CONTACT_NUMBER  VARCHAR(250) NOT NULL UNIQUE,
PRIMARY KEY (CUSTOMER_ID)
);
--account table creation query
CREATE TABLE ACCOUNT(
ACCOUNT_NO VARCHAR(255) NOT NULL PRIMARY KEY,
PIN_CODE INT NOT NULL,
DATE_OF_CREATE DATE NOT NULL ,
ACCOUNT_TYPE VARCHAR(50) NOT NULL,
AMOUNT BIGINT,
CUSTOMER_ID INT NOT NULL,
FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER(CUSTOMER_ID)
);
--transaction table creation query
CREATE TABLE TRANSACTION(
TRX_ID VARCHAR(255) NOT NULL PRIMARY KEY,
DATE_OF_TRANSFER DATETIME NOT NULL, 
AMOUNT BIGINT NOT NULL,
SENDER_ACCOUNT_NUMBER VARCHAR(255) NOT NULL,
RECEIVER_ACCOUNT_NUMBER  VARCHAR(255) NOT NULL,
FOREIGN KEY (SENDER_ACCOUNT_NUMBER) REFERENCES ACCOUNT(ACCOUNT_NO),
FOREIGN KEY (RECEIVER_ACCOUNT_NUMBER) REFERENCES ACCOUNT(ACCOUNT_NO)
);

-- query to fetch transaction ,sender account ,sender customer,receiver account, receiver customer details
SELECT
    T.TRX_ID,
    T.AMOUNT,
    T.DATE_OF_TRANSFER,
    SENDER.ACCOUNT_NO AS SENDER_ACCOUNT_NUMBER,
    SENDER.ACCOUNT_TYPE AS SENDER_ACCOUNT_TYPE,
    SENDER.CUSTOMER_ID AS SENDER_CUSTOMER_ID,
    SENDER_CUSTOMER.FIRST_NAME AS SENDER_NAME,
    SENDER_CUSTOMER.CONTACT_NUMBER AS SENDER_CONTACT,
    RECEIVER.ACCOUNT_NO AS RECEIVER_ACCOUNT_NO,
    RECEIVER.ACCOUNT_TYPE AS RECEIVER_ACCOUNT_TYPE,
    RECEIVER.CUSTOMER_ID AS RECEIVER_CUSTOMER_ID,
    RECEIVER_CUSTOMER.FIRST_NAME AS RECEIVER_NAME,
    RECEIVER_CUSTOMER.CONTACT_NUMBER AS RECEIVER_CONTACT
FROM
    TRANSACTION T
JOIN
        ACCOUNT AS SENDER ON T.SENDER_ACCOUNT_NUMBER=SENDER.ACCOUNT_NO
JOIN
        CUSTOMER AS SENDER_CUSTOMER ON SENDER.CUSTOMER_ID = SENDER_CUSTOMER.CUSTOMER_ID
JOIN
        ACCOUNT AS RECEIVER ON T.RECEIVER_ACCOUNT_NUMBER=RECEIVER.CUSTOMER_ID
JOIN
        CUSTOMER AS RECEIVER_CUSTOMER ON RECEIVER.CUSTOMER_ID=RECEIVER_CUSTOMER.CUSTOMER_ID
WHERE
    T.TRX_ID=?
