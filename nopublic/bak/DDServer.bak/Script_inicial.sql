CREATE DATABASE logsdatabase ENCODING 'LATIN1';

CONNECT logsdatabase;

CREATE SCHEMA auditorias;
CREATE LANGUAGE plpgsql;

--Tablas
CREATE TABLE auditorias.logssystem (
    oidusuario integer NOT NULL,
    usuario character varying NOT NULL,
    fechahora timestamp without time zone DEFAULT now() NOT NULL,
    nombresistema character varying NOT NULL,
    nombrehost character varying NOT NULL,
    basededatos character varying NOT NULL,
    direccionip character varying NOT NULL,
    direccionmac character varying NOT NULL,
    versionjvm character varying NOT NULL,
    nombreso character varying NOT NULL,
    memoriaram character varying NOT NULL,
    procesador character varying NOT NULL,
    resolucionpantalla character varying NOT NULL,
    estado character(1),
    nombreservidor character varying NOT NULL
);


--Stored

CREATE OR REPLACE FUNCTION auditorias.addlogsystem(_usuario character varying, _fechahora character varying, _nombresistema character varying, _nombrehost character varying, _basededatos character varying, _direccionip character varying, _direccionmac character varying, _versionjvm character varying, _nombreso character varying, _memoriaram character varying, _procesador character varying, _resolucionpantalla character varying, _estado character varying, _nombreservidor character varying) RETURNS integer AS $BODY$
DECLARE
	_idlogsystem integer;
	_oidusuario integer;
	query character varying;
BEGIN
/*
	#version = 1.0
	#groups = 0
	#description = Funcion utilizada para agregar un registro del Sistema
*/
--	SELECT INTO _idlogsystem public.ISNULL(max(idlogsystem),0)::integer + 1 FROM auditorias.logssystem;

	SELECT INTO _oidusuario oid FROM pg_roles WHERE rolname = _usuario;

	INSERT INTO auditorias.logssystem (
--			idlogsystem, 
			oidusuario, 
			usuario,
--			fechahora,
			nombresistema, 
			nombrehost, 
			basededatos, 
			direccionip,
			direccionmac, 
			versionjvm, 
			nombreso, 
			memoriaram, 
			procesador, 
			resolucionpantalla, 
			estado,
			nombreservidor
			) 
--	VALUES( 		_idlogsystem,
	VALUES( 		_oidusuario,
			_usuario,
--			_fechahora,
			_nombresistema,
			_nombrehost,
			_basededatos,
			_direccionip,
			_direccionmac,
			_versionjvm,
			_nombreso,
			_memoriaram,
			_procesador,
			_resolucionpantalla,
			'',
			_nombreservidor
	);
	RETURN 0; 
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION auditorias.addlogsystem(_usuario character varying, _fechahora character varying, _nombresistema character varying, _nombrehost character varying, _basededatos character varying, _direccionip character varying, _direccionmac character varying, _versionjvm character varying, _nombreso character varying, _memoriaram character varying, _procesador character varying, _resolucionpantalla character varying, _estado character varying, _nombreservidor character varying) IS '2010-03-22: (moraless)';

CREATE OR REPLACE FUNCTION auditorias.getultimoregistro(_nombreservidor character varying) RETURNS refcursor AS $BODY$
DECLARE
	_ultimoid integer = 0;
	query TEXT;
	returnCursor refcursor;
BEGIN
/*
	#version = 1.0
	#groups = 0
	#Description = Funcion que devuelve el último registro de Sistema insertado 
*/
	query := 'SELECT 
			* 
		FROM 
			auditorias.logssystem 
		WHERE 
			nombreservidor = ''' || _nombreservidor || '''
		ORDER BY fechahora DESC LIMIT 1';

	OPEN returnCursor FOR EXECUTE query;
	RETURN returnCursor;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION auditorias.getultimoregistro(_nombreservidor character varying) IS '2010-04-06: (santiago)';

-- GRUPOS Y USUARIOS NECESARIOS

CREATE ROLE "logsuser" WITH ENCRYPTED PASSWORD 'md533b3ba7068d19bb45a059970696424d6' NOSUPERUSER NOCREATEDB NOCREATEROLE INHERIT LOGIN CONNECTION LIMIT -1 VALID UNTIL 'infinity';

CREATE GROUP chwy; -- Nombre de grupo sugerido

-- PERMISOS
GRANT chwy TO logsuser;

REVOKE ALL ON FUNCTION auditorias.addlogsystem(_usuario character varying, _fechahora character varying, _nombresistema character varying, _nombrehost character varying, _basededatos character varying, _direccionip character varying, _direccionmac character varying, _versionjvm character varying, _nombreso character varying, _memoriaram character varying, _procesador character varying, _resolucionpantalla character varying, _estado character varying, _nombreservidor character varying) FROM PUBLIC;
REVOKE ALL ON FUNCTION auditorias.getultimoregistro(character varying) FROM PUBLIC;

GRANT SELECT, INSERT ON auditorias.logssystem TO GROUP chwy; 

GRANT EXECUTE ON FUNCTION auditorias.addlogsystem(character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying) TO GROUP chwy;
GRANT EXECUTE ON FUNCTION auditorias.getultimoregistro(character varying) TO GROUP chwy;
