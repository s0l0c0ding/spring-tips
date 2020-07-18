CREATE TABLE POST(
   ID INT PRIMARY KEY     NOT NULL,
   TITLE VARCHAR,
   BODY VARCHAR 
);
-- Insert rows into table 'POST'
INSERT INTO POST
( -- columns to insert data into
 ID, TITLE, BODY
)
VALUES
( -- first row: values for the columns in the list above
 1, 'Column2_Value', 'Column3_Value'
),
( -- second row: values for the columns in the list above
 2, 'Column2_Value', 'Column3_Value'
);
-- add more rows here
