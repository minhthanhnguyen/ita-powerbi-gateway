DROP PROCEDURE IF EXISTS sp_OTEXA_UPDATE_COUNTRY
GO

DROP PROCEDURE IF EXISTS sp_OTEXA_POST_PROCESSING
GO

DROP TABLE IF EXISTS OTEXA_HTS_CAT_REF
CREATE TABLE OTEXA_HTS_CAT_REF (
    [HTS] varchar(255),
    [CAT_ID] INT
)
GO

CREATE PROCEDURE sp_OTEXA_POST_PROCESSING
AS
BEGIN
    DROP TABLE IF EXISTS [dbo].[OTEXA_COUNTRY_REF]
    CREATE TABLE [dbo].[OTEXA_COUNTRY_REF] (
       [CTRY_ID] INT IDENTITY,
       [CTRY_DESCRIPTION] VARCHAR(255),
       [SOURCE] VARCHAR(255)
    )

   INSERT INTO [OTEXA_COUNTRY_REF] ([CTRY_DESCRIPTION], [SOURCE])
   SELECT DISTINCT Country, 'ANNUAL' FROM [dbo].[OTEXA_ANNUAL] UNION ALL
   SELECT DISTINCT Country, 'ANNUAL_FOOTWEAR' FROM [dbo].[OTEXA_ANNUAL_FOOTWEAR]

   TRUNCATE TABLE OTEXA_HTS_CAT_REF
   INSERT INTO OTEXA_HTS_CAT_REF ([HTS], [CAT_ID])
   SELECT DISTINCT [HTS], [CAT_ID] from OTEXA_ANNUAL union
   SELECT DISTINCT [HTS], [CAT_ID] from OTEXA_ANNUAL_FOOTWEAR
END
GO


DROP VIEW IF EXISTS [dbo].[OTEXA_HTS_REF_VW]
GO

CREATE VIEW [dbo].[OTEXA_HTS_REF_VW]
AS
    SELECT hts.[HTS], hts_cat.[CAT_ID], CONCAT(hts.[HTS], ' - ', hts.[DESCRIPTION]) as 'LONG_HTS'
    FROM [dbo].[OTEXA_HTS_REF] hts
    INNER JOIN [dbo].[OTEXA_HTS_CAT_REF] hts_cat
    ON hts.HTS = hts_cat.HTS
GO
