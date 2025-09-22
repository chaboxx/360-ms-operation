--liquibase formatted sql

--changeset chaboxx159@gmail.com:0.1

USE [db-360-operation];

CREATE TABLE [Money] (
    [money_id] INT PRIMARY KEY IDENTITY(1, 1),
    [balance] DECIMAL(18,2) NOT NULL,
    [account_id] UNIQUEIDENTIFIER NOT NULL,
    [currency_code] VARCHAR(10) NOT NULL,
    [created_at] DATETIME2 DEFAULT SYSUTCDATETIME(),
    [updated_at] DATETIME2 DEFAULT SYSUTCDATETIME()
    )

CREATE TABLE [Currency] (
    [currency_id] INT PRIMARY KEY IDENTITY(1, 1),
    [currency_code] VARCHAR(10) UNIQUE NOT NULL,
    [currency_name] VARCHAR(100) NOT NULL,
    [created_at] DATETIME2 DEFAULT SYSUTCDATETIME(),
    [updated_at] DATETIME2 DEFAULT SYSUTCDATETIME()
    )

CREATE TABLE [OperationType] (
    [operation_id] INT PRIMARY KEY IDENTITY(1, 1),
    [operation_code] VARCHAR(20) NOT NULL,
    [created_at] DATETIME2 DEFAULT SYSUTCDATETIME(),
    [updated_at] DATETIME2 DEFAULT SYSUTCDATETIME()
    )


CREATE TABLE [Operations] (
    [operation_id] INT PRIMARY KEY IDENTITY(1, 1),
    [operation_type] INT NOT NULL,
    [money_master] INT NOT NULL,
    [money_secondary] INT NOT NULL,
    [created_at] DATETIME2 DEFAULT SYSUTCDATETIME(),
    [updated_at] DATETIME2 DEFAULT SYSUTCDATETIME()
    )


ALTER TABLE [Money] ADD FOREIGN KEY ([currency_code]) REFERENCES [Currency] ([currency_code])
ALTER TABLE [Operations] ADD FOREIGN KEY ([operation_type]) REFERENCES [OperationType] ([operation_id])
ALTER TABLE [Operations] ADD FOREIGN KEY ([money_master]) REFERENCES [Money] ([money_id])
ALTER TABLE [Operations] ADD FOREIGN KEY ([money_secondary]) REFERENCES [Money] ([money_id])

--rollback USE [db-360-operation];
--rollback DROP TABLE [Money];
--rollback DROP TABLE [Currency];
--rollback DROP TABLE [OperationType];
--rollback DROP TABLE [Operations];
