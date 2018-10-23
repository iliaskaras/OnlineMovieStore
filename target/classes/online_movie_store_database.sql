CREATE DATABASE  IF NOT EXISTS `online_movie_store`;
USE `online_movie_store`;


DROP TABLE IF EXISTS `movies`;
DROP TABLE IF EXISTS `genre_types`;
DROP TABLE IF EXISTS `video_format_types`;
DROP TABLE IF EXISTS `movie_cast`;
DROP TABLE IF EXISTS `actors`;
DROP TABLE IF EXISTS `directors`;
DROP TABLE IF EXISTS `movie_reviews`;
DROP TABLE IF EXISTS `customers`;
DROP TABLE IF EXISTS `accounts`;
DROP TABLE IF EXISTS `payment_methods`;
DROP TABLE IF EXISTS `transactions`;
DROP TABLE IF EXISTS `rental_packages`;

CREATE TABLE `rental_packages` (
  `rental_package_id` int(11) NOT NULL AUTO_INCREMENT,
  `rental_economic_price` FLOAT(7,4) DEFAULT NULL,
  `rental_additional_price` FLOAT(7,4) DEFAULT NULL,
  `rental_package_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`rental_package_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `genre_types` (
  `genre_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `genre_type` varchar(30) DEFAULT NULL,
  `genre_description` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`genre_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `video_format_types` (
  `video_format_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `video_format_type_description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`video_format_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `movies` (
  `movie_id` int(11) NOT NULL AUTO_INCREMENT,
  `rental_package_id` int(11) DEFAULT '1',
  `genre_type_id` int(11) DEFAULT NULL,
  `video_format_type_id` int(11) DEFAULT NULL,
  `release_year` int(11) DEFAULT NULL,
  `movie_title` varchar(45) DEFAULT NULL,
  `movie_description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`movie_id`),
  FOREIGN KEY (`genre_type_id`) REFERENCES genre_types(genre_type_id),
  FOREIGN KEY (`video_format_type_id`) REFERENCES video_format_types(video_format_type_id),
  FOREIGN KEY (`rental_package_id`) REFERENCES rental_packages(rental_package_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `actors` (
  `actor_id` int(11) NOT NULL AUTO_INCREMENT,
  `actor_first_name` varchar(45) DEFAULT NULL,
  `actor_last_name` varchar(45) DEFAULT NULL,
  `actor_details` varchar(250) DEFAULT NULL,
  `actor_gender` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`actor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `directors` (
  `director_id` int(11) NOT NULL AUTO_INCREMENT,
  `director_first_name` varchar(45) DEFAULT NULL,
  `director_last_name` varchar(45) DEFAULT NULL,
  `director_details` varchar(250) DEFAULT NULL,
  `director_citinenship` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`director_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `movie_cast` (
  `movie_id` int(11) DEFAULT NULL,
  `actor_id` int(11) DEFAULT NULL,
  `director_id` int(11) DEFAULT NULL,
  FOREIGN KEY (`movie_id`) REFERENCES movies(movie_id),
  FOREIGN KEY (`actor_id`) REFERENCES actors(actor_id),
  FOREIGN KEY (`director_id`) REFERENCES directors(director_id)
);

CREATE TABLE `movie_reviews` (
  `movie_review_id` int(11) NOT NULL AUTO_INCREMENT,
  `movie_id` int(11) DEFAULT NULL,
  `movie_review` varchar(200) DEFAULT NULL,
  `movie_raiting` smallint(11) DEFAULT NULL,
  PRIMARY KEY (`movie_review_id`),
  FOREIGN KEY (`movie_id`) REFERENCES movies(movie_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `customers` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_first_name` varchar(45) DEFAULT NULL,
  `customer_last_name` varchar(45) DEFAULT NULL,
  `customer_email` varchar(45) DEFAULT NULL,
  `customer_phone` varchar(45) DEFAULT NULL,
  `customer_age` smallint(11) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `payment_methods` (
  `payment_method_id` int(11) NOT NULL AUTO_INCREMENT,
  `payment_method_description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`payment_method_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `accounts` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) DEFAULT NULL,
  `payment_method_id` int(11) DEFAULT NULL,
  `account_username` varchar(45) DEFAULT NULL,
  `account_password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`account_id`),
  FOREIGN KEY (`customer_id`) REFERENCES customers(customer_id),
  FOREIGN KEY (`payment_method_id`) REFERENCES payment_methods(payment_method_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `transactions` (
  `transaction_id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) DEFAULT NULL,
  `movie_id` int(11) DEFAULT NULL,
  `transaction_date` DATE DEFAULT NULL,
  `number_of_order_days` int(11) DEFAULT NULL,
  `rental_end_date` DATE DEFAULT NULL,
  `transaction_amount` FLOAT(7,4) DEFAULT NULL,
  `transaction_comment` varchar(200) DEFAULT NULL,
  `paid` TinyInt(1) DEFAULT 0,
  PRIMARY KEY (`transaction_id`),
  FOREIGN KEY (`account_id`) REFERENCES accounts(account_id),
  FOREIGN KEY (`movie_id`) REFERENCES movies(movie_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



