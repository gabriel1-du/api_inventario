-- 1. Tabla GENERO_HIST
CREATE TABLE GENERO_HIST (
    id_genero BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_genero VARCHAR(20) NOT NULL
);

-- 2. Tabla AUTOR_HIST
CREATE TABLE AUTOR_HIST (
    id_autor BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_autor VARCHAR(20) NOT NULL
);

-- 3. Tabla TIPO_HIST
CREATE TABLE TIPO_HIST (
    id_tipo_hist BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_tipo VARCHAR(10) NOT NULL
);

-- 4. Tabla SERIE_HIST
CREATE TABLE SERIE_HIST (
    id_serie_hist BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_serie_hist VARCHAR(30) NOT NULL,
    id_genero BIGINT NOT NULL,
    id_autor BIGINT NOT NULL,
    id_tipo_hist BIGINT NOT NULL,
    CONSTRAINT fk_serie_genero FOREIGN KEY (id_genero) REFERENCES GENERO_HIST (id_genero),
    CONSTRAINT fk_serie_autor FOREIGN KEY (id_autor) REFERENCES AUTOR_HIST (id_autor),
    CONSTRAINT fk_serie_tipo FOREIGN KEY (id_tipo_hist) REFERENCES TIPO_HIST (id_tipo_hist)
);

-- 5. Tabla HISTORIETA
CREATE TABLE HISTORIETA (
    id_historieta BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_historieta VARCHAR(30) NOT NULL,
    descripcion_historieta TEXT NOT NULL,
    portada TEXT NOT NULL,
    numero_historieta INT NOT NULL,
    precio DECIMAL(8, 2) NOT NULL,
    id_genero BIGINT NOT NULL,
    id_tipo_hist BIGINT NOT NULL,
    id_autor BIGINT NOT NULL,
    id_serie_hist BIGINT NOT NULL,
    CONSTRAINT fk_historieta_genero FOREIGN KEY (id_genero) REFERENCES GENERO_HIST (id_genero),
    CONSTRAINT fk_historieta_tipo FOREIGN KEY (id_tipo_hist) REFERENCES TIPO_HIST (id_tipo_hist),
    CONSTRAINT fk_historieta_autor FOREIGN KEY (id_autor) REFERENCES AUTOR_HIST (id_autor),
    CONSTRAINT fk_historieta_serie FOREIGN KEY (id_serie_hist) REFERENCES SERIE_HIST (id_serie_hist)
);
