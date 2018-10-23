INSERT INTO online_movie_store.genre_types(genre_type,genre_description) values ("Horror","Horror genre, scary movies that might not be appropriate for children");
INSERT INTO online_movie_store.genre_types(genre_type,genre_description) values ("Romance","Romance films or romance movies are romantic love stories recorded in visual media for broadcast in theaters and on TV that focus on passion, emotion, and the affectionate romantic involvement of the main characters.");
INSERT INTO online_movie_store.genre_types(genre_type,genre_description) values ("Comedy Horror","Comedy horror is a literary and film genre that combines elements of comedy and horror fiction.");
INSERT INTO online_movie_store.genre_types(genre_type,genre_description) values ("Drama","Drama is the specific mode of fiction represented in performance: a play, opera, mime, ballet, etc, performed in a theatre, or on radio or television.");
INSERT INTO online_movie_store.genre_types(genre_type,genre_description) values ("Comedy","Comedy is a genre of film in which the main emphasis is on humour.");
INSERT INTO online_movie_store.genre_types(genre_type,genre_description) values ("Animation","Animation is a dynamic medium in which images or objects are manipulated to appear as moving images.");
INSERT INTO online_movie_store.genre_types(genre_type,genre_description) values ("Action","Action film is a film genre in which the protagonist or protagonists are thrust into a series of challenges that typically include violence, extended fighting, physical feats, and frantic chases.");
INSERT INTO online_movie_store.genre_types(genre_type,genre_description) values ("Documentary","A documentary film is a nonfictional motion picture intended to document some aspect of reality, primarily for the purposes of instruction, education, or maintaining a historical record.");
INSERT INTO online_movie_store.genre_types(genre_type,genre_description) values ("Adventure","Adventure films are a genre of film that typically use their action scenes to display and explore exotic locations in an energetic way.");
INSERT INTO online_movie_store.genre_types(genre_type,genre_description) values ("Thriller","Thrillers are characterized and defined by the moods they elicit, giving viewers heightened feelings of suspense, excitement, surprise, anticipation and anxiety.");
INSERT INTO online_movie_store1.genre_types(genre_type,genre_description) values ("Fiction","Fiction is any story or setting that is derived from imagination in other words, not based strictly on history or fact.");

INSERT INTO online_movie_store.actors(actor_first_name,actor_last_name,actor_details,actor_gender) values ("Robert","De Niro","One of the greatest actors of all time, Robert De Niro was born on August 17, 1943 in Manhattan, New York City","Male");
INSERT INTO online_movie_store.actors(actor_first_name,actor_last_name,actor_details,actor_gender) values ("Jack","Nicholson","Jack Nicholson, an American actor, producer, director and screenwriter, is a three-time Academy Award winner and twelve-time nominee.","Male");
INSERT INTO online_movie_store.actors(actor_first_name,actor_last_name,actor_details,actor_gender) values ("Tom","Hanks","Thomas Jeffrey Hanks was born in Concord, California, to Janet Marylyn (Frager), a hospital worker, and Amos Mefford Hanks, an itinerant cook.","Male");
INSERT INTO online_movie_store1.actors(actor_first_name,actor_last_name,actor_details,actor_gender) values ("Leonardo","DiCaprio","Leonardo Wilhelm DiCaprio was born November 11, 1974 in Los Angeles, California, the only child of Irmelin DiCaprio ( Indenbirken) and former comic book artist George DiCaprio.","Male");
INSERT INTO online_movie_store.actors(actor_first_name,actor_last_name,actor_details,actor_gender) values ("Naomi","Watts","Naomi Ellen Watts was born on September 28, 1968 in Shoreham, England, to Myfanwy Edwards Miv (Roberts), an antiques dealer and costume/set designer, and Peter Watts (Peter Anthony Watts), the road manager to Pink Floyd.","Female");

INSERT INTO online_movie_store.directors(director_first_name,director_last_name,director_details,director_citinenship) values ("Steven","Spielberg","Steven Allan Spielberg KBE OMRI is an American filmmaker. He is considered one of the founding pioneers of the New Hollywood era and one of the most popular directors and producers in film history.","American");

INSERT INTO online_movie_store.video_format_types(video_format_type_description) values ("HDV");
INSERT INTO online_movie_store.video_format_types(video_format_type_description) values ("AVI");
INSERT INTO online_movie_store.video_format_types(video_format_type_description) values ("WMV");
INSERT INTO online_movie_store.video_format_types(video_format_type_description) values ("MP4");
INSERT INTO online_movie_store.video_format_types(video_format_type_description) values ("MOV");

INSERT INTO online_movie_store.rental_packages(rental_economic_price,rental_additional_price,rental_package_name) values (1.0,0.5,"normal");
INSERT INTO online_movie_store.rental_packages(rental_economic_price,rental_additional_price,rental_package_name) values (1.2,0.7,"middle");
INSERT INTO online_movie_store.rental_packages(rental_economic_price,rental_additional_price,rental_package_name) values (1.4,0.9,"expensive");

INSERT INTO online_movie_store.payment_methods(payment_method_description) values ("Credit card");
INSERT INTO online_movie_store.payment_methods(payment_method_description) values ("Debit card");

INSERT INTO online_movie_store.movies(rental_package_id,genre_type_id,video_format_type_id,release_year,movie_title,movie_description) values (1,3,1,2016,"The Comedian","A look at the life of an aging insult comic named Jack Burke.");
INSERT INTO online_movie_store.movies(rental_package_id,genre_type_id,video_format_type_id,release_year,movie_title,movie_description) values (2,2,1,2015,"The Romance Movie","Test romance description");
INSERT INTO online_movie_store.movies(rental_package_id,genre_type_id,video_format_type_id,release_year,movie_title,movie_description) values (3,3,1,2015,"The Comedy Horror movie","Test Comedy Horror description");
INSERT INTO online_movie_store.movies(rental_package_id,genre_type_id,video_format_type_id,release_year,movie_title,movie_description) values (3,4,1,2015,"The Drama  movie","Test Drama description");

INSERT into online_movie_store.customers(customer_first_name,customer_last_name,customer_email,customer_phone,customer_age) values("test_username","test_password","test@test.com","12345678",25)




