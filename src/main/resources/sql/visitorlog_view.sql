/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : SQL Server
 Source Server Version : 14001000
 Source Host           : localhost:1433
 Source Catalog        : xingda
 Source Schema         : dbo

 Target Server Type    : SQL Server
 Target Server Version : 14001000
 File Encoding         : 65001

 Date: 23/04/2019 18:01:50
*/


-- ----------------------------
-- Table structure for visitorlog_view
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[visitorlog_view]') AND type IN ('U'))
	DROP TABLE [dbo].[visitorlog_view]
GO

CREATE TABLE [dbo].[visitorlog_view] (
  [visitor_pin] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [visitor_mobile] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [visitor_company] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [name] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [pin] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [visitor_statu] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [in_address] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [in_time] datetime2(7)  NULL,
  [out_time] datetime2(7)  NULL,
  [visitor_type] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[visitorlog_view] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of visitorlog_view
-- ----------------------------
INSERT INTO [dbo].[visitorlog_view]  VALUES (N'1', N'123', N'2', N'3', N'2', N'2', N'1', N'2019-04-23 17:15:29.0000000', N'2019-04-17 17:15:34.0000000', N'vip')
GO


-- ----------------------------
-- Primary Key structure for table visitorlog_view
-- ----------------------------
ALTER TABLE [dbo].[visitorlog_view] ADD CONSTRAINT [PK__visitorl__0F5909C121693006] PRIMARY KEY CLUSTERED ([visitor_pin])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO

