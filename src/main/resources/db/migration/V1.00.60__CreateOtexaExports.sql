DROP TABLE IF EXISTS [dbo].[OTEXA_EXPORTS]
GO

CREATE TABLE [dbo].[OTEXA_EXPORTS](
	[SCHEDB]        [varchar](15) NULL,
	[REPORT_YEAR]   [varchar](4) NULL,
	[REPORT_MONTH]  [varchar](2) NULL,
	[GROUP]         [varchar](25) NULL,
	[COUNTRY]       [varchar](255) NULL,
	[HEADER_ID]     [varchar](25) NULL,
	[VAL]           [decimal](30, 10) NULL
)
GO


DROP TABLE IF EXISTS [dbo].[OTEXA_HEADER_EXPORTS_REF]
GO

CREATE TABLE [dbo].[OTEXA_HEADER_EXPORTS_REF](
	[HEADER_ID]           [varchar](15) NULL,
	[YR]                  [integer] NULL,
	[HEADER_DESCRIPTION]  [varchar](255) NULL,
	[HEADER_TYPE]         [varchar](255) NULL
)
GO
