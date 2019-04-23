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

 Date: 23/04/2019 18:01:56
*/


-- ----------------------------
-- Table structure for acclog_view
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[acclog_view]') AND type IN ('U'))
	DROP TABLE [dbo].[acclog_view]
GO

CREATE TABLE [dbo].[acclog_view] (
  [pin] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [name] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [mobile] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [deptname] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [reader_name] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [device_name] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [sn] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [time] datetime2(7)  NULL,
  [event_type] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[acclog_view] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Primary Key structure for table acclog_view
-- ----------------------------
ALTER TABLE [dbo].[acclog_view] ADD CONSTRAINT [PK__acclog_v__DD37D92C65D7EF4E] PRIMARY KEY CLUSTERED ([pin])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO

