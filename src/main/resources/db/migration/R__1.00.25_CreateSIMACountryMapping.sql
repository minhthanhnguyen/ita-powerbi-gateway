DROP TABLE IF EXISTS [dbo].[SIMA_COUNTRY_MAPPINGS]
GO

CREATE TABLE [dbo].[SIMA_COUNTRY_MAPPINGS]
(
    [Correct Country Name]  VARCHAR(MAX),
    [Incorrect Country Name] VARCHAR(MAX)
)
GO

CREATE OR ALTER VIEW [sima_steel_reader].[SIMA_COUNTRY_MAPPINGS]
AS
SELECT * FROM [dbo].[SIMA_COUNTRY_MAPPINGS]
GO