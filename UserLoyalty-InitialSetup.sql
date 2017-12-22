delimiter $$

create schema `userloyalty`$$

use `userloyalty`$$

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  `active` bit(1) DEFAULT b'1',
  `deleted` bit(1) DEFAULT b'0',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_UNIQUE` (`firstName`,`lastName`)
) ENGINE=InnoDB $$

CREATE TABLE `transfer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userId` int NOT NULL,
  `amount` int NOT NULL,
  `active` bit(1) DEFAULT b'1',
  `deleted` bit(1) DEFAULT b'0',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB $$

DROP PROCEDURE IF EXISTS `Insert_User`$$
CREATE PROCEDURE `Insert_User`(
     
      IN inFirstName VARCHAR(50) 
     ,IN inLastName VARCHAR(50)  
     ,IN inEmail VARCHAR(255) 
     ,OUT status varchar(250)
)
SPROC: BEGIN

    Declare user_exists INT;
    Declare userId INT;
    
	SET user_exists = NULL;
    SET userId = 0;
    
    if(inFirstName IS NULL OR inFirstName = '' OR inLastName IS NULL OR inLastName = "")
    then
		SET status = 'REQUIRED_KEYS_MISSING';
		LEAVE SPROC;
	END IF;

	Select id INTO user_exists
	From `userloyalty`.`user`
	Where user.firstName = inFirstName and user.lastName = inLastName and user.deleted = false 
	LIMIT 1;

	IF(user_exists IS NOT NULL AND user_exists != "")
	THEN 
		SET status = 'EXISTS_CHOOSE_ANOTHER';
		LEAVE SPROC;
	END IF;

	INSERT INTO `userloyalty`.`user`
	(
	`firstName`,
	`lastName`,
	`email`,
	`active`,
	`deleted`,
	`created`,
	`updated`)
	VALUES
	(
	inFirstName,
	inLastName,
	inEmail,
	1,
	0,
	now(),
	null);

	select LAST_INSERT_ID() INTO userId;
    
    SET status = 'CREATE_SUCCESS';
    
    SELECT 
		`user`.`id`,
		`user`.`firstName`,
		`user`.`lastName`,
		`user`.`email`
	FROM `userloyalty`.`user`
    WHERE `user`.`id` = userId and `user`.`active` = true and `user`.`deleted` = false;


END$$

DROP PROCEDURE IF EXISTS `Insert_Transfer`$$
CREATE PROCEDURE `Insert_Transfer`(
     
      IN inUserId INT
     ,IN inAmount INT  
     ,OUT status varchar(250)
)
SPROC: BEGIN

    Declare user_Total INT;
    Declare user_exists INT;
    Declare transferId INT;
    
	SET user_exists = 0;
    SET user_Total = 0;
    SET transferId = 0;
    
    if(inUserId IS NULL OR inUserId = 0)
    then
		SET status = 'REQUIRED_KEYS_MISSING';
		LEAVE SPROC;
	END IF;

	Select id INTO user_exists
	From `userloyalty`.`user`
	Where user.id = inUserId and user.active=true and user.deleted = false;

	IF(user_exists <= 0)
	THEN 
		SET status = 'NO_MATCH_FOUND';
		LEAVE SPROC;
	END IF;
    
    Select sum(amount) INTO user_Total
    from transfer
    where transfer.userId = inUserId and transfer.deleted = false
    group by transfer.userId;
    
    IF(inAmount < 0 AND (user_Total + inAmount ) < 0)
	THEN 
		SET status = 'NOT_SUFFICIENT_BALANCE';
		LEAVE SPROC;
	END IF;

	INSERT INTO `userloyalty`.`transfer`
	(
	`userId`,
	`amount`,
	`active`,
	`deleted`,
	`created`,
	`updated`)
	VALUES
	(
	inUserId,
	inAmount,
	1,
	0,
	now(),
	null);

	select LAST_INSERT_ID() INTO transferId;
    
    SET status = 'CREATE_SUCCESS';
    
    SELECT `transfer`.`id`,
    `transfer`.`userId`,
    `transfer`.`amount`
	FROM `userloyalty`.`transfer`
    WHERE `transfer`.`id` = transferId AND transfer.active = true and transfer.deleted = false;



END$$

DROP PROCEDURE IF EXISTS `Get_Transfers`$$
CREATE PROCEDURE `Get_Transfers`(
     
      IN inTransferId INT
     ,IN inUserId INT  
     ,OUT status varchar(250)
)
SPROC: BEGIN

    Declare user_exists INT;
    
	SET user_exists = 0;
    
    if(inUserId IS NULL OR inUserId = 0)
    then
		SET status = 'REQUIRED_KEYS_MISSING';
		LEAVE SPROC;
	END IF;

	Select id INTO user_exists
	From `userloyalty`.`user`
	Where user.id = inUserId and user.active=true and user.deleted = false;

	IF(user_exists <= 0)
	THEN 
		SET status = 'NO_MATCH_FOUND';
		LEAVE SPROC;
	END IF;
    
    SET status = 'FETCH_SUCCESS';
    
    SELECT 
    `transfer`.`id`,
    `transfer`.`userId`,
    `transfer`.`amount`
	FROM `userloyalty`.`transfer`
    WHERE case when (inTransferId is not null and inTransferId!="") then `transfer`.`id` = inTransferId else 1=1 end
    and `transfer`.userId = inUserId and `transfer`.deleted = false;



END$$

delimiter ;
