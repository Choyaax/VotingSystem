-- Create the voting_system database if it doesn't exist
CREATE DATABASE IF NOT EXISTS voting_system;
-- Use the voting_system database
USE voting_system;

-- Create the candidate table if it doesn't exist
CREATE TABLE IF NOT EXISTS candidate (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    votes INT DEFAULT 0
);

-- Error handling for SQL errors
DELIMITER //

CREATE PROCEDURE `CreateDatabaseAndTable` ()
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION BEGIN
        -- Print error message
        SHOW ERRORS;
        ROLLBACK;
    END;

    START TRANSACTION;

    -- Create or alter database
    CREATE DATABASE IF NOT EXISTS `voting_system`;

    -- Create or alter table
    USE `voting_system`;
    CREATE TABLE IF NOT EXISTS `candidate` (
        `id` INT AUTO_INCREMENT PRIMARY KEY,
        `name` VARCHAR(255) NOT NULL,
        `votes` INT DEFAULT 0
    );

    -- Commit transaction
    COMMIT;
END //
DELIMITER ;

CALL `CreateDatabaseAndTable` ();

-- Insert initial data into the candidate table
INSERT INTO `candidate` (`name`, `votes`) VALUES ('Candidate A', 0);
INSERT INTO `candidate` (`name`, `votes`) VALUES ('Candidate B', 0);

-- Error handling for SQL errors
DELIMITER //

CREATE PROCEDURE `insertAndUpdate` ()
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION BEGIN
        -- Print error message
        SHOW ERRORS;
        ROLLBACK;
    END;

    START TRANSACTION;

    -- Insert initial data into the candidate table
    INSERT INTO `candidate` (`name`, `votes`) VALUES ('Candidate A', 0);
    INSERT INTO `candidate` (`name`, `votes`) VALUES ('Candidate B', 0);

    -- Commit transaction
    COMMIT;
END //
DELIMITER ;
 jspb  ParameterDirection Input
