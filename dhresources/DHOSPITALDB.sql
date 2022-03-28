USE master;
----------------------------------------
-- DROP DATABASE DHOSPITALDB;
----------------------------------------
CREATE DATABASE DHOSPITALDB;
----------------------------------------

USE DHOSPITALDB;

CREATE TABLE HOSPITAL(
	HOSPITAL_ID INT PRIMARY KEY IDENTITY,
	NOMBRE VARCHAR(255) NOT NULL,
	CREATED_AT DATETIME NOT NULL DEFAULT GETDATE(),
	UPDATED_AT DATETIME,
	CREATED_BY INT NOT NULL,
	UPDATED_BY INT
);

CREATE TABLE PACIENTE(
	PACIENTE_ID INT PRIMARY KEY IDENTITY,
	NOMBRE VARCHAR(255) NOT NULL,
	APELLIDO VARCHAR(255) NOT NULL,
	FECHA_NACIMIENTO DATE NOT NULL,
	DIRECCION VARCHAR(255) NOT NULL,
	FOTO_PERFIL_PATH VARCHAR(255),
	HOSPITAL_ID INT NOT NULL,
	CREATED_AT DATETIME NOT NULL DEFAULT GETDATE(),
	UPDATED_AT DATETIME,
	CREATED_BY INT NOT NULL,
	UPDATED_BY INT,
	FOREIGN KEY(HOSPITAL_ID) REFERENCES HOSPITAL(HOSPITAL_ID)
);

CREATE TABLE DOCTOR(
	DOCTOR_ID INT PRIMARY KEY IDENTITY,
	NOMBRE VARCHAR(255) NOT NULL,
	APELLIDO VARCHAR(255) NOT NULL,
	FECHA_NACIMIENTO DATE NOT NULL,
	DIRECCION VARCHAR(255) NOT NULL,
	FOTO_PERFIL_PATH VARCHAR(255),
	HOSPITAL_ID INT NOT NULL,
	CREATED_AT DATETIME NOT NULL DEFAULT GETDATE(),
	UPDATED_AT DATETIME,
	CREATED_BY INT NOT NULL,
	UPDATED_BY INT,
	FOREIGN KEY(HOSPITAL_ID) REFERENCES HOSPITAL(HOSPITAL_ID)
);

CREATE TABLE ESPECIALIDAD(
	ESPECIALIDAD_ID INT PRIMARY KEY IDENTITY,
	NOMBRE VARCHAR(255) NOT NULL,
	DESCRIPCION VARCHAR(800) NOT NULL,
	AVATAR_PATH VARCHAR(255),
	HOSPITAL_ID INT NOT NULL,
	CREATED_AT DATETIME NOT NULL DEFAULT GETDATE(),
	UPDATED_AT DATETIME,
	CREATED_BY INT NOT NULL,
	UPDATED_BY INT,
	FOREIGN KEY(HOSPITAL_ID) REFERENCES HOSPITAL(HOSPITAL_ID)
);

CREATE TABLE NOTA_VISITA(
	NOTA_VISITA_ID INT PRIMARY KEY IDENTITY,
	PACIENTE_ID INT NOT NULL,
	DESCRIPCION TEXT NOT NULL,
	FECHA_NOTA DATE NOT NULL,
	DOCTOR_ID INT NOT NULL,
	CREATED_AT DATETIME NOT NULL DEFAULT GETDATE(),
	UPDATED_AT DATETIME,
	CREATED_BY INT NOT NULL,
	UPDATED_BY INT,
	FOREIGN KEY(PACIENTE_ID) REFERENCES PACIENTE(PACIENTE_ID),
	FOREIGN KEY(DOCTOR_ID) REFERENCES DOCTOR(DOCTOR_ID)
);

CREATE TABLE DOCTOR_ESPECIALIDAD(
	DOCTOR_ID INT NOT NULL,
	ESPECIALIDAD_ID INT NOT NULL,
	CREATED_AT DATETIME NOT NULL DEFAULT GETDATE(),
	UPDATED_AT DATETIME,
	CREATED_BY INT NOT NULL,
	UPDATED_BY INT,
	PRIMARY KEY(DOCTOR_ID, ESPECIALIDAD_ID),
	FOREIGN KEY(DOCTOR_ID) REFERENCES DOCTOR(DOCTOR_ID),
	FOREIGN KEY(ESPECIALIDAD_ID) REFERENCES ESPECIALIDAD(ESPECIALIDAD_ID)
);

----------------------------------------
SET DATEFORMAT mdy;

SET IDENTITY_INSERT HOSPITAL ON;
INSERT INTO HOSPITAL(HOSPITAL_ID, NOMBRE, CREATED_BY) VALUES(1, 'HOSPITAL AAA', 1);
INSERT INTO HOSPITAL(HOSPITAL_ID, NOMBRE, CREATED_BY) VALUES(2, 'HOSPITAL BBB', 1);
SET IDENTITY_INSERT HOSPITAL OFF;

SET IDENTITY_INSERT PACIENTE ON;
INSERT INTO PACIENTE(PACIENTE_ID, NOMBRE, APELLIDO, FECHA_NACIMIENTO, DIRECCION, HOSPITAL_ID, CREATED_BY)
VALUES
(1, 'JUAN', 'PEREZ', '05/23/1990', 'CALLE UNO, ZONA AAA NRO 100', 2, 1),
(2, 'PEDRO', 'MENDEZ', '07/17/1995', 'CALLE DOS, ZONA BBB NRO 101', 1, 1),
(3, 'ANA', 'FLORES', '01/20/1991', 'CALLE UNO, ZONA AAA NRO 102', 2, 1),
(4, 'LUCAS', 'ROBLES', '08/14/1993', 'CALLE NUEVE, ZONA CCC NRO 103', 1, 1);
SET IDENTITY_INSERT PACIENTE OFF;

SET IDENTITY_INSERT DOCTOR ON;
INSERT INTO DOCTOR(DOCTOR_ID, NOMBRE, APELLIDO, FECHA_NACIMIENTO, DIRECCION, HOSPITAL_ID, CREATED_BY)
VALUES
(1, 'ALEX', 'SMITH', '05/23/1990', 'CALLE UNO, ZONA MMM NRO 200', 1, 1),
(2, 'SARA', 'JACKSON', '07/17/1995', 'CALLE DOS, ZONA NNN NRO 201', 2, 1),
(3, 'JHON', 'CARTER', '01/20/1991', 'CALLE UNO, ZONA PPP NRO 202', 2, 1),
(4, 'SOPHIA', 'CURTIS', '08/14/1993', 'CALLE NUEVE, ZONA OOO NRO 203', 1, 1);
SET IDENTITY_INSERT DOCTOR OFF;

SET IDENTITY_INSERT ESPECIALIDAD ON;
INSERT INTO ESPECIALIDAD(ESPECIALIDAD_ID, NOMBRE, DESCRIPCION, HOSPITAL_ID, CREATED_BY)
VALUES
(1, 'PEDIATRIA', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut rutrum tortor eu fermentum porttitor. Maecenas tempor, neque vitae hendrerit fringilla, nisi mi euismod felis, ut interdum mi ante non urna. Pellentesque feugiat maximus ipsum id sagittis. Sed venenatis condimentum urna vitae mollis. Nulla vel turpis in leo interdum euismod. Morbi volutpat ac urna ut varius. Duis eros urna, varius quis justo in, semper vulputate velit. Suspendisse ornare interdum purus, sit amet vehicula leo tincidunt eget.', 1, 1),
(2, 'CARDIOLOGIA', 'Sed posuere interdum elit, in eleifend ex hendrerit eu. In quam dui, volutpat ut laoreet sit amet, blandit non arcu. Aliquam sem lorem, pulvinar condimentum ultrices a, tempor nec justo. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. In in quam efficitur, rutrum nisi at, vulputate lacus. Morbi accumsan sodales leo sit amet ultrices. Aliquam nec leo sit amet ex lacinia fringilla. Nulla felis elit, dapibus et ullamcorper eu, tincidunt id sapien. Etiam at enim vel quam scelerisque lobortis at quis lorem.', 2, 1),
(3, 'CIRUGIA', 'Aenean at erat vitae urna consectetur ultrices sed eu velit. Proin tristique, ante vitae accumsan vulputate, magna elit consectetur nulla, vitae accumsan eros ligula a mi. Phasellus volutpat urna arcu, vitae fringilla nunc accumsan quis. Etiam vulputate ante ac gravida fermentum. Proin nec felis pretium, porttitor arcu sed, porta orci. Suspendisse eget pharetra nisl. Nulla facilisi. Duis ultrices erat ipsum, ac efficitur magna efficitur eu. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Duis ultricies neque purus, vel facilisis nulla blandit a. Nam dapibus nisl auctor nibh vulputate pretium.', 2, 1),
(4, 'FISIATRIA', 'Aenean auctor in libero vitae rhoncus. Sed semper risus at scelerisque ultrices. Phasellus ac tortor eu libero eleifend luctus. Etiam posuere felis eu lectus fermentum, eget feugiat purus dignissim. Etiam id elementum est. Sed scelerisque, tellus nec imperdiet iaculis, erat magna volutpat leo, ut sollicitudin velit diam vitae diam. Proin arcu lectus, tristique a sollicitudin ac, ullamcorper et ipsum. Mauris viverra congue justo, eget congue tellus consectetur a. Phasellus posuere neque a sapien aliquet, non posuere odio rutrum. Aliquam erat volutpat. In eget lorem non mauris viverra dapibus vitae et metus. Suspendisse potenti. Nulla id iaculis sem. Proin eget mattis diam. Duis feugiat lorem in tellus porttitor accumsan in at dolor. Aenean eleifend erat a tincidunt maximus.', 1, 1);
SET IDENTITY_INSERT ESPECIALIDAD OFF;

SET IDENTITY_INSERT NOTA_VISITA ON;
INSERT INTO NOTA_VISITA(NOTA_VISITA_ID, PACIENTE_ID, FECHA_NOTA, DOCTOR_ID, CREATED_BY, DESCRIPCION)
VALUES
(1, 3, '05/23/2021', 1, 1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut rutrum tortor eu fermentum porttitor. Maecenas tempor, neque vitae hendrerit fringilla, nisi mi euismod felis, ut interdum mi ante non urna. Pellentesque feugiat maximus ipsum id sagittis. Sed venenatis condimentum urna vitae mollis. Nulla vel turpis in leo interdum euismod. Morbi volutpat ac urna ut varius. Duis eros urna, varius quis justo in, semper vulputate velit. Suspendisse ornare interdum purus, sit amet vehicula leo tincidunt eget.'),
(2, 2, '07/17/2021', 2, 1, 'Sed posuere interdum elit, in eleifend ex hendrerit eu. In quam dui, volutpat ut laoreet sit amet, blandit non arcu. Aliquam sem lorem, pulvinar condimentum ultrices a, tempor nec justo. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. In in quam efficitur, rutrum nisi at, vulputate lacus. Morbi accumsan sodales leo sit amet ultrices. Aliquam nec leo sit amet ex lacinia fringilla. Nulla felis elit, dapibus et ullamcorper eu, tincidunt id sapien. Etiam at enim vel quam scelerisque lobortis at quis lorem.'),
(3, 1, '01/20/2021', 3, 1, 'Aenean at erat vitae urna consectetur ultrices sed eu velit. Proin tristique, ante vitae accumsan vulputate, magna elit consectetur nulla, vitae accumsan eros ligula a mi. Phasellus volutpat urna arcu, vitae fringilla nunc accumsan quis. Etiam vulputate ante ac gravida fermentum. Proin nec felis pretium, porttitor arcu sed, porta orci. Suspendisse eget pharetra nisl. Nulla facilisi. Duis ultrices erat ipsum, ac efficitur magna efficitur eu. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Duis ultricies neque purus, vel facilisis nulla blandit a. Nam dapibus nisl auctor nibh vulputate pretium.'),
(4, 4, '08/14/2021', 4, 1, 'Aenean auctor in libero vitae rhoncus. Sed semper risus at scelerisque ultrices. Phasellus ac tortor eu libero eleifend luctus. Etiam posuere felis eu lectus fermentum, eget feugiat purus dignissim. Etiam id elementum est. Sed scelerisque, tellus nec imperdiet iaculis, erat magna volutpat leo, ut sollicitudin velit diam vitae diam. Proin arcu lectus, tristique a sollicitudin ac, ullamcorper et ipsum. Mauris viverra congue justo, eget congue tellus consectetur a. Phasellus posuere neque a sapien aliquet, non posuere odio rutrum. Aliquam erat volutpat. In eget lorem non mauris viverra dapibus vitae et metus. Suspendisse potenti. Nulla id iaculis sem. Proin eget mattis diam. Duis feugiat lorem in tellus porttitor accumsan in at dolor. Aenean eleifend erat a tincidunt maximus.'),
(5, 2, '01/20/2022', 1, 1, 'Sed posuere interdum elit, in eleifend ex hendrerit eu. In quam dui, volutpat ut laoreet sit amet, blandit non arcu. Aliquam sem lorem, pulvinar condimentum ultrices a, tempor nec justo. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. In in quam efficitur, rutrum nisi at, vulputate lacus. Morbi accumsan sodales leo sit amet ultrices. Aliquam nec leo sit amet ex lacinia fringilla. Nulla felis elit, dapibus et ullamcorper eu, tincidunt id sapien. Etiam at enim.');
SET IDENTITY_INSERT NOTA_VISITA OFF;

INSERT INTO DOCTOR_ESPECIALIDAD(DOCTOR_ID, ESPECIALIDAD_ID, CREATED_BY)
VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),
(1, 2, 1);

-- DROP TABLE DOCTOR_ESPECIALIDAD
-- TRUNCATE TABLE DOCTOR_ESPECIALIDAD
-- SELECT * FROM DOCTOR_ESPECIALIDAD