-------------------------------------------------------------------------------
--
-- PATIENT
--
-------------------------------------------------------------------------------
DROP TABLE IF EXISTS PATIENT;
CREATE TABLE PATIENT (
  ID IDENTITY,
  FIRST_NAME VARCHAR(50),
  LAST_NAME VARCHAR(50),
  BIRTHDAY DATE
);
