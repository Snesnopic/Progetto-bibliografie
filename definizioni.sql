CREATE TYPE tipo_enum AS ENUM ('Conferenza', 'Libro', 'Rivista','Fascicolo', 'Articolo');

CREATE TABLE Associativa_Riferimenti_Categoria(
    ID_Riferimento integer NOT NULL,
    ID_Categoria integer NOT NULL
);

CREATE TABLE Associazione_Riferimenti(
    ID_Riferimento integer NOT NULL,
    ID_Riferimento_Associato integer NOT NULL
);

CREATE TABLE Autore_Riferimento(
    ID_Utente integer NOT NULL,
    Descr_Utente character varying(500),
    ID_Riferimento integer NOT NULL,
	Ordine integer NOT NULL
);

CREATE TABLE Categoria (
    ID_Categoria integer PRIMARY KEY,
    Descr_Categoria character varying(500) NOT NULL,
	ID_Super_Categoria integer,
	ID_Utente integer
);

CREATE TABLE Riferimenti_Biblio (
    ID_Riferimento integer PRIMARY KEY,
    Titolo_Riferimento character varying(200) NOT NULL,
    Data_Riferimento date,
    On_line boolean NOT NULL,
	Tipo tipo_enum NOT NULL,
    URL character varying(500) UNIQUE,
    DOI integer UNIQUE,
    Descr_Riferimento character varying(1000),
	Editore character varying(1000),
	ISBN character varying(20) UNIQUE,
	ISNN character varying(20) UNIQUE,
	Luogo character varying(100),
	Pag_Inizio integer,
	Pag_Fine integer,
	Edizione integer
);

CREATE TABLE Utente (
    ID_Utente integer PRIMARY KEY,
    Nome_Utente character varying(200) NOT NULL,
    Cognome_Utente character varying(200) NOT NULL,
    Data_Inizio_Validita date DEFAULT CURRENT_DATE,
    Data_Fine_Validita date
);

ALTER TABLE Associativa_Riferimenti_Categoria 
ADD CONSTRAINT fk_ID_Riferimento FOREIGN KEY (ID_Riferimento) REFERENCES Riferimenti_Biblio(ID_Riferimento) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE Associativa_Riferimenti_Categoria 
ADD CONSTRAINT fk_ID_Categoria FOREIGN KEY (ID_Categoria) REFERENCES Categoria(ID_Categoria) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE Associazione_Riferimenti
ADD CONSTRAINT fk_ID_Riferimento_Citante FOREIGN KEY (ID_Riferimento) REFERENCES Riferimenti_Biblio(ID_Riferimento) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE Associazione_Riferimenti
ADD CONSTRAINT fk_ID_Riferimento_Citato FOREIGN KEY (ID_Riferimento_Associato) REFERENCES Riferimenti_Biblio(ID_Riferimento) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE Autore_Riferimento
ADD CONSTRAINT fk_ID_Riferimento_Autore FOREIGN KEY (ID_Riferimento) REFERENCES Riferimenti_Biblio(ID_Riferimento) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE Autore_Riferimento
ADD CONSTRAINT fk_ID_Utente FOREIGN KEY (ID_Utente) REFERENCES Utente(ID_Utente) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE Categoria
ADD CONSTRAINT fk_ID_Super_Categoria FOREIGN KEY (ID_Super_Categoria) REFERENCES Categoria(ID_Categoria) ON UPDATE CASCADE ON DELETE SET NULL;

ALTER TABLE Categoria
ADD CONSTRAINT fk_ID_Categoria_Utente FOREIGN KEY (ID_Utente) REFERENCES Utente(ID_Utente) ON UPDATE CASCADE ON DELETE SET NULL;

-- Popolazione tabella Utente
INSERT INTO Utente VALUES (1, 'Agata', 'Esposito', '2021-03-12', NULL);
INSERT INTO Utente VALUES (2, 'Mario', 'Finizio', '2021-08-17', '2031-12-31');
INSERT INTO Utente VALUES (3, 'Alberto', 'Maurizi', '2021-09-16', '2031-12-31');
INSERT INTO Utente VALUES (4, 'Sara','Benerecetti', '2022-01-26', NULL);
INSERT INTO Utente VALUES (5, 'Manuel','Bavaro', '2022-02-23',NULL);
INSERT INTO Utente VALUES (6, 'Vismaro', 'Tussi', '2021-03-12', NULL);
INSERT INTO Utente VALUES (7, 'Adriana ', 'Panatta', '2021-08-17', '2031-12-31');
INSERT INTO Utente VALUES (8, 'Adamo', 'Sraffa', '2021-09-16', '2031-12-31');
INSERT INTO Utente VALUES (9, 'Valentina ','Bonolis', '2022-01-26', NULL);
INSERT INTO Utente VALUES (10, 'Gustavo ','Foconi', '2022-02-23',NULL);
INSERT INTO Utente VALUES (11, 'Serafina', 'Chigi', '2021-03-12', NULL);
INSERT INTO Utente VALUES (12, 'Lazzaro', 'Acerbi', '2021-08-17', '2031-12-31');
INSERT INTO Utente VALUES (13, 'Nina', 'Dossetti', '2021-09-16', '2031-12-31');
INSERT INTO Utente VALUES (14, 'Dallapé','Dallapé', '2022-01-26', NULL);
INSERT INTO Utente VALUES (15, 'Viola','Alessi', '2022-02-23',NULL);
INSERT INTO Utente VALUES (16, 'Annunziata','Errani','2022-03-20',NULL);
INSERT INTO Utente VALUES (17, 'Alessio','Donarelli','2022-03-23',NULL);
INSERT INTO Utente VALUES (18, 'Ramona','Mezzetta','2022-03-20',NULL);
INSERT INTO Utente VALUES (19, 'Marcella','Esposito','2022-03-23',NULL);
INSERT INTO Utente VALUES (20, 'Maria','Bernini','2022-03-20',NULL);

-- Popolazione tabella Riferimenti_Biblio

INSERT INTO Riferimenti_Biblio VALUES (1,'Test 01', CURRENT_DATE ,'true','Libro','www.google.com01',1234,'Libro 01','Academic Press','73941',NULL,NULL,NULL,NULL,3);
INSERT INTO Riferimenti_Biblio VALUES (2,'Test 02', CURRENT_DATE ,'false','Conferenza',NULL,9791,'Conferenza 02',NULL,NULL,'Roma',NULL,NULL,NULL,NULL);
INSERT INTO Riferimenti_Biblio VALUES (3,'Test 03', CURRENT_DATE ,'true','Articolo','www.google.com02',8973,'Articolo 03',NULL,NULL,'74526',NULL,0,25,3);
INSERT INTO Riferimenti_Biblio VALUES (4,'Test 04', CURRENT_DATE ,'true','Rivista','www.google.com03',1274,'Rivista 04',NULL,NULL,'74524',NULL,0,50,3);
INSERT INTO Riferimenti_Biblio VALUES (5,'Test 05', CURRENT_DATE ,'true','Articolo','www.google.com04',5151,'Articolo 03',NULL,NULL,'74525',NULL,0,25,3);
INSERT INTO Riferimenti_Biblio VALUES (6,'Test 06', CURRENT_DATE ,'true','Fascicolo','www.google.com05',3597,'fascicolo 03',NULL,NULL,'74536',NULL,0,25,3);
INSERT INTO Riferimenti_Biblio VALUES (7,'Test 07', CURRENT_DATE ,'true','Libro','www.google.com06',7906,'Libro 03','McGraw-Hill Education','15963',NULL,NULL,NULL,NULL,8); 
INSERT INTO Riferimenti_Biblio VALUES (8,'Test 08', CURRENT_DATE ,'false','Conferenza',NULL,2455,'CONFERENZA 08',NULL,NULL,'Londra',NULL,NULL,NULL,NULL);
INSERT INTO Riferimenti_Biblio VALUES (9,'Test 09', CURRENT_DATE ,'true','Articolo','www.google.com07',7566,'Articolo 09',NULL,NULL,'18982',NULL,0,30,7);
INSERT INTO Riferimenti_Biblio VALUES (10,'Test 10', CURRENT_DATE ,'true','Rivista','www.google.com08',6102,'Rivista 10',NULL,NULL,'48504',NULL,0,65,9);
INSERT INTO Riferimenti_Biblio VALUES (11,'Test 11', CURRENT_DATE ,'true','Libro','www.google.com09',1235,'Libro 11','Nature Publishing Group','848422',NULL,NULL,NULL,NULL,4);
INSERT INTO Riferimenti_Biblio VALUES (12,'Test 12', CURRENT_DATE ,'false','Conferenza',NULL,8484,'Conferenza 12',NULL,NULL,'Napoli',NULL,NULL,NULL,NULL);
INSERT INTO Riferimenti_Biblio VALUES (13,'Test 13', CURRENT_DATE ,'true','Articolo','www.google.com10',8974,'Articolo 13',NULL,NULL,'431845',NULL,0,57,6);
INSERT INTO Riferimenti_Biblio VALUES (14,'Test 14', CURRENT_DATE ,'true','Rivista','www.google.com11',3255,'Rivista 14',NULL,NULL,'76620',NULL,0,41,3);
INSERT INTO Riferimenti_Biblio VALUES (15,'Test 15', CURRENT_DATE ,'true','Articolo','www.google.com12',7859,'Articolo 15',NULL,NULL,'50245',NULL,0,27,6);
INSERT INTO Riferimenti_Biblio VALUES (16,'Test 16', CURRENT_DATE ,'true','Fascicolo','www.google.com13',1055,'fascicolo 16',NULL,NULL,'81804',NULL,0,15,2);
INSERT INTO Riferimenti_Biblio VALUES (17,'Test 17', CURRENT_DATE ,'true','Libro','www.google.com14',7907,'Libro 17','Universitas Studiorum','42774',NULL,NULL,NULL,NULL,9); 
INSERT INTO Riferimenti_Biblio VALUES (18,'Test 18', CURRENT_DATE ,'false','Conferenza',NULL,6226,'CONFERENZA 18',NULL,NULL,'Milano',NULL,NULL,NULL,NULL);
INSERT INTO Riferimenti_Biblio VALUES (19,'Test 19', CURRENT_DATE ,'true','Articolo','www.google.com15',8248,'Articolo 19',NULL,NULL,'58054',NULL,0,37,7);
INSERT INTO Riferimenti_Biblio VALUES (20,'Test 20', CURRENT_DATE ,'true','Rivista','www.google.com16',5780,'Rivista 20',NULL,NULL,'78779',NULL,0,20,9);

-- Popolazione tabella Autore_Riferimento 
INSERT INTO Autore_Riferimento VALUES (1,'Descr Utente1',3,1);
INSERT INTO Autore_Riferimento VALUES (2,'Descr Utente2',4,2);
INSERT INTO Autore_Riferimento VALUES (3,'Descr Utente3',5,3);
INSERT INTO Autore_Riferimento VALUES (4,'Descr Utente4',1,4);
INSERT INTO Autore_Riferimento VALUES (5,'Descr Utente5',2,5);
INSERT INTO Autore_Riferimento VALUES (6,'Descr Utente6',20,6);
INSERT INTO Autore_Riferimento VALUES (7,'Descr Utente7',16,7);
INSERT INTO Autore_Riferimento VALUES (8,'Descr Utente8',10,8);
INSERT INTO Autore_Riferimento VALUES (9,'Descr Utente9',13,9);
INSERT INTO Autore_Riferimento VALUES (10,'Descr Utente10',19,10);
INSERT INTO Autore_Riferimento VALUES (11,'Descr Utente11',7,11);
INSERT INTO Autore_Riferimento VALUES (12,'Descr Utente12',20,12);
INSERT INTO Autore_Riferimento VALUES (13,'Descr Utente13',11,13);
INSERT INTO Autore_Riferimento VALUES (14,'Descr Utente14',18,14);
INSERT INTO Autore_Riferimento VALUES (15,'Descr Utente15',2,15);
INSERT INTO Autore_Riferimento VALUES (16,'Descr Utente16',6,16);
INSERT INTO Autore_Riferimento VALUES (17,'Descr Utente17',1,17 );
INSERT INTO Autore_Riferimento VALUES (18,'Descr Utente18',16,18);
INSERT INTO Autore_Riferimento VALUES (19,'Descr Utente19',17,19);
INSERT INTO Autore_Riferimento VALUES (20,'Descr Utente20',20,20);
-- Popolazione tabella Associazione_Riferimenti

INSERT INTO Associazione_Riferimenti VALUES (1,10);
INSERT INTO Associazione_Riferimenti VALUES (2,16);
INSERT INTO Associazione_Riferimenti VALUES (3,19);
INSERT INTO Associazione_Riferimenti VALUES (4,16);
INSERT INTO Associazione_Riferimenti VALUES (5,14);
INSERT INTO Associazione_Riferimenti VALUES (6,12);
INSERT INTO Associazione_Riferimenti VALUES (7,5);
INSERT INTO Associazione_Riferimenti VALUES (8,17);
INSERT INTO Associazione_Riferimenti VALUES (9,18);
INSERT INTO Associazione_Riferimenti VALUES (10,3);
INSERT INTO Associazione_Riferimenti VALUES (11,18);
INSERT INTO Associazione_Riferimenti VALUES (12,20);
INSERT INTO Associazione_Riferimenti VALUES (13,6);
INSERT INTO Associazione_Riferimenti VALUES (14,10);
INSERT INTO Associazione_Riferimenti VALUES (15,9);
INSERT INTO Associazione_Riferimenti VALUES (16,2);
INSERT INTO Associazione_Riferimenti VALUES (17,12);
INSERT INTO Associazione_Riferimenti VALUES (18,19);
INSERT INTO Associazione_Riferimenti VALUES (19,7);
INSERT INTO Associazione_Riferimenti VALUES (20,4);

--Popolazione tabella Categoria 

INSERT INTO Categoria VALUES (1,'Informatica',NULL, 2);
INSERT INTO Categoria VALUES (2,'Storia',NULL, 1);
INSERT INTO Categoria VALUES (3,'Matematica',NULL, 3);
INSERT INTO Categoria VALUES (4,'Letteratura Italiana',NULL,20);
INSERT INTO Categoria VALUES (5,'Latino',NULL,16);
INSERT INTO Categoria VALUES (6,'Chimica',NULL,20);
INSERT INTO Categoria VALUES (7,'Arte',NULL,20);
INSERT INTO Categoria VALUES (8,'Filosofia',NULL,17);
INSERT INTO Categoria VALUES (9,'Letteratura Inglese',NULL,15);
INSERT INTO Categoria VALUES (10,'Fisica', NULL, 11);
INSERT INTO Categoria VALUES (11,'Database',1,2);
INSERT INTO Categoria VALUES (12,'Linguaggi di Programmazione',1,2);
INSERT INTO Categoria VALUES (13,'Reti Informatiche',1,2);
INSERT INTO Categoria VALUES (20,'PAN',13,2);
INSERT INTO Categoria VALUES (21,'GAN',13,2);
INSERT INTO Categoria VALUES (22,'LAN',13,2);
INSERT INTO Categoria VALUES (14,'Database parallelo',11,2);
INSERT INTO Categoria VALUES (15,'Database distribuito',11,2);
INSERT INTO Categoria VALUES (16,'Database multimediale',11,2);
INSERT INTO Categoria VALUES (17,'C++',12,2);
INSERT INTO Categoria VALUES (18,'Java',12,2);
INSERT INTO Categoria VALUES (19,'Python',12,2);
INSERT INTO Categoria VALUES (23,'Età Antica',2, 1);
INSERT INTO Categoria VALUES (24,'Medioevo',2, 1);
INSERT INTO Categoria VALUES (25,'Età moderna',2, 1);
INSERT INTO Categoria VALUES (26,'Periodo Ellenistico',23,1);
INSERT INTO Categoria VALUES (27,'Guerra Pelopponneso',23,1);
INSERT INTO Categoria VALUES (28,'Magna Grecia',23,1);
INSERT INTO Categoria VALUES (29,'Medioevo Alto',24, 1);
INSERT INTO Categoria VALUES (30,'Medioevo Basso',24, 1);
INSERT INTO Categoria VALUES (31,'Naturalismo',4,20);
INSERT INTO Categoria VALUES (32,'Verismo',4,20);
INSERT INTO Categoria VALUES (33,'Verga',32,20);
INSERT INTO Categoria VALUES (34,'Zola',31,20);
INSERT INTO Categoria VALUES (35,'Geometria Euclidea',3, 3);
INSERT INTO Categoria VALUES (36,'Algebra',3, 3);
INSERT INTO Categoria VALUES (37,'Trigonometria',3, 3);

--Popolazione tabella Associativa_Riferimenti_Categoria

INSERT INTO Associativa_Riferimenti_Categoria VALUES (1,6);
INSERT INTO Associativa_Riferimenti_Categoria VALUES (2,10);
INSERT INTO Associativa_Riferimenti_Categoria VALUES (3,5);
INSERT INTO Associativa_Riferimenti_Categoria VALUES (5,28);
INSERT INTO Associativa_Riferimenti_Categoria VALUES (6,27);
INSERT INTO Associativa_Riferimenti_Categoria VALUES (7,23);
INSERT INTO Associativa_Riferimenti_Categoria VALUES (8,26);
INSERT INTO Associativa_Riferimenti_Categoria VALUES (9,22);
INSERT INTO Associativa_Riferimenti_Categoria VALUES (10,2);
INSERT INTO Associativa_Riferimenti_Categoria VALUES (11,1);
INSERT INTO Associativa_Riferimenti_Categoria VALUES (12,3);
INSERT INTO Associativa_Riferimenti_Categoria VALUES (13,4);
INSERT INTO Associativa_Riferimenti_Categoria VALUES (14,5);
INSERT INTO Associativa_Riferimenti_Categoria VALUES (15,2);
INSERT INTO Associativa_Riferimenti_Categoria VALUES (16,2);
INSERT INTO Associativa_Riferimenti_Categoria VALUES (17,2);
INSERT INTO Associativa_Riferimenti_Categoria VALUES (18,2);
INSERT INTO Associativa_Riferimenti_Categoria VALUES (19,2);
INSERT INTO Associativa_Riferimenti_Categoria VALUES (20,1);

--vincoli 
ALTER TABLE Associazione_Riferimenti ADD UNIQUE (ID_Riferimento, ID_Riferimento_Associato);
ALTER TABLE Categoria ADD UNIQUE (ID_Categoria, ID_Super_Categoria);
ALTER TABLE Autore_Riferimento ADD UNIQUE (ID_Utente, ID_Riferimento, Ordine);


--trigger per impedire che un riferimento non digitale abbia un URL
CREATE FUNCTION online_check() RETURNS trigger LANGUAGE plpgsql AS $$
BEGIN
IF NEW.On_line = false AND NEW.URL IS NOT NULL THEN
	RAISE EXCEPTION 'Non puoi inserire un URL se il riferimento è offline';
END IF;
RETURN NEW;
END;
$$;

CREATE TRIGGER online_check
AFTER INSERT OR UPDATE OF On_line ON Riferimenti_Biblio
FOR EACH ROW EXECUTE FUNCTION online_check();

--trigger di tipo libro
CREATE FUNCTION libro_check() RETURNS trigger LANGUAGE plpgsql AS $$
BEGIN
IF NEW.Tipo = 'Libro' AND (NEW.ISNN IS NOT NULL OR NEW.Luogo IS NOT NULL OR NEW.Pag_Inizio IS NOT NULL OR NEW.Pag_Fine IS NOT NULL OR NEW.DOI IS NOT NULL) THEN
	RAISE EXCEPTION 'Inserimento non valido';
END IF;
RETURN NEW;
END;
$$;

CREATE TRIGGER libro_check
AFTER INSERT OR UPDATE OF Tipo ON Riferimenti_Biblio
FOR EACH ROW EXECUTE FUNCTION libro_check();

--trigger di tipo conferenza
CREATE FUNCTION conferenza_check() RETURNS trigger LANGUAGE plpgsql AS $$
BEGIN
IF NEW.Tipo = 'Conferenza' AND (NEW.ISNN IS NOT NULL OR NEW.ISBN IS NOT NULL OR NEW.Pag_Inizio IS NOT NULL OR NEW.Pag_Fine IS NOT NULL OR NEW.DOI IS NOT NULL
OR NEW.Edizione IS NOT NULL OR NEW.Editore IS NOT NULL) THEN
	RAISE EXCEPTION 'Inserimento non valido';
END IF;
RETURN NEW;
END;
$$;

CREATE TRIGGER conferenza_check
AFTER INSERT OR UPDATE OF Tipo ON Riferimenti_Biblio
FOR EACH ROW EXECUTE FUNCTION conferenza_check();

--trigger di tipo articolo
CREATE FUNCTION articolo_check() RETURNS trigger LANGUAGE plpgsql AS $$
BEGIN
IF NEW.Tipo = 'Articolo' AND (NEW.ISBN IS NOT NULL OR NEW.Luogo IS NOT NULL OR NEW.Editore IS NOT NULL OR NEW.Edizione IS NOT NULL OR NEW.DOI IS NOT NULL) THEN
	RAISE EXCEPTION 'Inserimento non valido';
END IF;
RETURN NEW;
END;
$$;

CREATE TRIGGER articolo_check
AFTER INSERT OR UPDATE OF Tipo ON Riferimenti_Biblio
FOR EACH ROW EXECUTE FUNCTION articolo_check();

--trigger di tipo rivista
CREATE FUNCTION rivista_check() RETURNS trigger LANGUAGE plpgsql AS $$
BEGIN
IF NEW.Tipo = 'Rivista' AND (NEW.ISBN IS NOT NULL OR NEW.Luogo IS NOT NULL OR NEW.Editore IS NOT NULL OR NEW.Edizione IS NOT NULL OR NEW.DOI IS NOT NULL
	OR NEW.Pag_Fine IS NOT NULL OR NEW.Pag_Inizio IS NOT NULL) THEN
	RAISE EXCEPTION 'Inserimento non valido';
END IF;
RETURN NEW;
END;
$$;

CREATE TRIGGER rivista_check
AFTER INSERT OR UPDATE OF Tipo ON Riferimenti_Biblio
FOR EACH ROW EXECUTE FUNCTION rivista_check();

--trigger di tipo fascicolo
CREATE FUNCTION fascicolo_check() RETURNS trigger LANGUAGE plpgsql AS $$
BEGIN
IF NEW.Tipo = 'Fascicolo' AND (NEW.ISBN IS NOT NULL OR NEW.Luogo IS NOT NULL OR NEW.Editore IS NOT NULL OR NEW.Edizione IS NOT NULL OR NEW.ISNN IS NOT NULL
	OR NEW.Pag_Fine IS NOT NULL OR NEW.Pag_Inizio IS NOT NULL) THEN
	RAISE EXCEPTION 'Inserimento non valido';
END IF;
RETURN NEW;
END;
$$;
CREATE TRIGGER fascicolo_check
AFTER INSERT OR UPDATE OF Tipo ON Riferimenti_Biblio
FOR EACH ROW EXECUTE FUNCTION fascicolo_check();

--funzione per ricavare sopra categorie da categoria
CREATE FUNCTION super_cat(catIn integer) RETURNS VARCHAR(100) LANGUAGE plpgsql AS $$
DECLARE
codiciString VARCHAR(100) := '';
catTmp integer := catIn;
supercatTmp integer := (SELECT ID_Super_Categoria FROM Categoria WHERE ID_Categoria = catTmp);
BEGIN
WHILE supercatTmp IS NOT NULL
BEGIN LOOP
	codiciString := codiciString||supercatTmp||',';
	catTmp := supercatTmp;
	supercatTmp := (SELECT ID_Super_Categoria FROM Categoria WHERE ID_Categoria = catTmp);
END LOOP;
codiciString := RTRIM(codiciString,',');
RETURN codiciString;
END;
$$;

--funzione per ricavare sotto categorie da categoria
CREATE FUNCTION sub_cat(catIn integer) RETURNS VARCHAR(100) LANGUAGE plpgsql AS $$
DECLARE
codiciString VARCHAR(100) := '';
catTmp integer := catIn;
i record;
BEGIN
FOR i IN (SELECT ID_Categoria FROM Categoria WHERE ID_Super_Categoria = catTmp)
LOOP
	codiciString := codiciString||i.ID_Categoria||',';
	codiciString := codiciString||sub_cat(i.ID_Categoria);
END LOOP;
codiciString := RTRIM(codiciString,',');
RETURN codiciString;
END;
$$;
--funzione che dato un utente in input, restituisce tutti i riferimenti che citano i riferimenti di questo utente
--funzione che data una categoria in input, restituisce tutti i riferimenti che appartengono a quella categoria o una sua sottocategoria