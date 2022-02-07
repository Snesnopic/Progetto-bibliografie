CREATE TYPE tipo_enum AS ENUM ('Articolo', 'Libro', 'Risorsa on-line','Dataset');

CREATE TABLE Associativa_Riferimenti_Categoria(
    ID_Riferimento integer NOT NULL,
    ID_Categoria integer NOT NULL
);

CREATE TABLE Associazione_Riferimenti(
    ID_Riferimento integer NOT NULL,
    ID_Riferimento_Associato integer NOT NULL
);

CREATE TABLE Autore_Riferimento(
    ID_Utente integer PRIMARY KEY,
    Descr_Utente character varying(500),
    ID_Riferimento integer NOT NULL
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
    Descr_Autore character varying(200)
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

--INSERT riferiti agli utenti
INSERT INTO Utente VALUES (1, 'Agata', 'Esposito', '2021-03-12', NULL);
INSERT INTO Utente VALUES (2, 'Mario', 'Finizio', '2021-08-17', '2031-12-31');
INSERT INTO Utente VALUES (3, 'Alberto', 'Maurizi', '2021-09-16', '2031-12-31');
INSERT INTO Utente VALUES (4, 'Sara','Benerecetti', '2022-01-26', NULL);
INSERT INTO Utente VALUES (5, 'Manuel','Bavaro', '2022-02-23',NULL);

--INSERT riferiti ai riferimenti biblio
INSERT INTO Riferimenti_Biblio VALUES (1,'Test 01', CURRENT_DATE ,'true','Articolo','www.google.com',1234,'Testi 01', 'Autore 01');
INSERT INTO Riferimenti_Biblio VALUES (2,'Test 02', CURRENT_DATE ,'false','Risorsa on-line',NULL,7894,'Testi 02', 'Autore 02');
INSERT INTO Riferimenti_Biblio VALUES (3,'Test 03', CURRENT_DATE ,'true','Libro','www.google03.com',9632,'Testi 03', 'Autore 03');
INSERT INTO Riferimenti_Biblio VALUES (4,'Test 04', CURRENT_DATE ,'false','Articolo',NULL,4561,'Testi 04', 'Autore 04');
INSERT INTO Riferimenti_Biblio VALUES (5,'Test 05', CURRENT_DATE ,'true','Dataset','www.google05.com',5289,'Testi 05', 'Autore 05');

--INSERT riferiti agli autori riferimento
INSERT INTO Autore_Riferimento VALUES (1,'Descr Utente1',3);
INSERT INTO Autore_Riferimento VALUES (2,'Descr Utente2',4);
INSERT INTO Autore_Riferimento VALUES (3,'Descr Utente3',5);
INSERT INTO Autore_Riferimento VALUES (4,'Descr Utente4',1);
INSERT INTO Autore_Riferimento VALUES (5,'Descr Utente5',2);

--INSERT riferiti alle categorie
INSERT INTO Categoria VALUES (1,'Informatica',NULL, 2);
INSERT INTO Categoria VALUES (2,'Storia',NULL, 1);
INSERT INTO Categoria VALUES (3,'Matematica',NULL, 3);
INSERT INTO Categoria VALUES (4,'Database',1,2);
INSERT INTO Categoria VALUES (5,'UML',4,2);

--INSERT riferiti alle associazione_riferimenti
INSERT INTO Associazione_Riferimenti VALUES (1,5);
INSERT INTO Associazione_Riferimenti VALUES (2,3);
INSERT INTO Associazione_Riferimenti VALUES (4,1);
INSERT INTO Associazione_Riferimenti VALUES (3,5);

--INSERT riferiti alle associativa_riferimenti_categoria
INSERT INTO Associativa_Riferimenti_Categoria VALUES (1,1);
INSERT INTO Associativa_Riferimenti_Categoria VALUES (2,3);
INSERT INTO Associativa_Riferimenti_Categoria VALUES (3,4);
INSERT INTO Associativa_Riferimenti_Categoria VALUES (5,5);
INSERT INTO Associativa_Riferimenti_Categoria VALUES (4,2);

--vincoli 
ALTER TABLE Associazione_Riferimenti ADD UNIQUE (ID_Riferimento, ID_Riferimento_Associato);
ALTER TABLE Categoria ADD UNIQUE (ID_Categoria, ID_Super_Categoria);


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