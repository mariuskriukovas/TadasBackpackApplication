INSERT INTO PUBLIC.TRAVELER (ID, NAME)
VALUES (1, 'Jonas'),
       (2, 'Petras');

INSERT INTO PUBLIC.ITEM (ID, NAME, IS_MANDATORY, WEIGHT, PROVIDE_KILOMETERS)
VALUES (1, 'Vandens 1kg buteliukas', false, 1, 10),
       (2, 'Palapinė', true, 6, NULL);

INSERT INTO PUBLIC.TRAVEL (ID, NAME, DISTANCE_KILOMETERS, TRAVELER_ID)
VALUES (1, 'Prie juros', 150, 1),
       (2, 'Prie ežero', 20, 2);