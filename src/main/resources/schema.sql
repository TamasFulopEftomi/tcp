DROP ALL OBJECTS;             
CREATE USER IF NOT EXISTS "SA" SALT '8af2870bccf3b219' HASH '04cc36ab307b0516ec72d31f17bf0302e64edd4fdc3cc13d36ad0498d7748ad6' ADMIN;         
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_5BB26A16_FD90_4184_B4C3_E73DC560DDE3" START WITH 1 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_8801E03A_67F9_4378_9B04_92515DDF77A9" START WITH 1 BELONGS_TO_TABLE;             
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_890B9289_8192_4887_A310_84B1627763D4" START WITH 6 BELONGS_TO_TABLE;
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_D1FF34B7_6904_42B7_8F64_27D71AD19035" START WITH 73 BELONGS_TO_TABLE;               
CREATE SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_78903A0E_C58B_49F2_AFC9_54C9717E008C" START WITH 15 BELONGS_TO_TABLE;               
CREATE CACHED TABLE "PUBLIC"."BOX"(
    "ID" INTEGER DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_78903A0E_C58B_49F2_AFC9_54C9717E008C" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_78903A0E_C58B_49F2_AFC9_54C9717E008C",
    "BOX_TYPE" VARCHAR(255),
    "BOX_WEIGHT" DOUBLE NOT NULL,
    "BOXES_IN_ROW" INTEGER NOT NULL,
    "ROWS_ON_PALLET" INTEGER NOT NULL
);             
ALTER TABLE "PUBLIC"."BOX" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_1" PRIMARY KEY("ID");          
-- 14 +/- SELECT COUNT(*) FROM PUBLIC.BOX;    
CREATE CACHED TABLE "PUBLIC"."CARGO"(
    "ID" INTEGER DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_5BB26A16_FD90_4184_B4C3_E73DC560DDE3" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_5BB26A16_FD90_4184_B4C3_E73DC560DDE3",
    "ITEM_NO" VARCHAR(255),
    "STOCK" INTEGER NOT NULL
);              
ALTER TABLE "PUBLIC"."CARGO" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_3" PRIMARY KEY("ID");        
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.CARGO;   
CREATE CACHED TABLE "PUBLIC"."ITEM"(
    "ID" INTEGER DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_D1FF34B7_6904_42B7_8F64_27D71AD19035" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_D1FF34B7_6904_42B7_8F64_27D71AD19035",
    "ITEM_NO" VARCHAR(255),
    "ITEM_WEIGHT" DOUBLE NOT NULL,
    "PCS_IN_BOX" INTEGER NOT NULL,
    "BOX_ID" INTEGER,
    "PALLET_ID" INTEGER
);     
ALTER TABLE "PUBLIC"."ITEM" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_2" PRIMARY KEY("ID");         
-- 72 +/- SELECT COUNT(*) FROM PUBLIC.ITEM;   
CREATE CACHED TABLE "PUBLIC"."PALLET"(
    "ID" INTEGER DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_890B9289_8192_4887_A310_84B1627763D4" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_890B9289_8192_4887_A310_84B1627763D4",
    "PALLET_TYPE" VARCHAR(255),
    "PALLET_WEIGHT" DOUBLE NOT NULL,
    "ROOF_WEIGHT" DOUBLE NOT NULL,
    "STACKABLE" BOOLEAN NOT NULL
);           
ALTER TABLE "PUBLIC"."PALLET" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_8" PRIMARY KEY("ID");       
-- 5 +/- SELECT COUNT(*) FROM PUBLIC.PALLET;  
CREATE CACHED TABLE "PUBLIC"."USER"(
    "ID" INTEGER DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_8801E03A_67F9_4378_9B04_92515DDF77A9" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_8801E03A_67F9_4378_9B04_92515DDF77A9",
    "EMAIL" VARCHAR(255),
    "NAME" VARCHAR(255),
    "PASSWORD" VARCHAR(255)
);        
ALTER TABLE "PUBLIC"."USER" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_27" PRIMARY KEY("ID");        
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.USER;                
ALTER TABLE "PUBLIC"."ITEM" ADD CONSTRAINT "PUBLIC"."FKHNWA2HM5743SAOIFEBAEHWT8I" FOREIGN KEY("PALLET_ID") REFERENCES "PUBLIC"."PALLET"("ID") NOCHECK;        
ALTER TABLE "PUBLIC"."ITEM" ADD CONSTRAINT "PUBLIC"."FKOPXUIAAV40QV8RS7CK0PIDWX1" FOREIGN KEY("BOX_ID") REFERENCES "PUBLIC"."BOX"("ID") NOCHECK;              
