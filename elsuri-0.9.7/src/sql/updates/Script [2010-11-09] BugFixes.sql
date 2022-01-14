UPDATE taxes.alicuotascontribuciones SET idcuentadeduccion = -1 WHERE idcuentadeduccion = 0;
UPDATE taxes.alicuotascontribuciones SET idcuentainteres = -1 WHERE idcuentainteres = 0;

CREATE OR REPLACE FUNCTION taxes.addcertificado(_idtipoimpuesto integer, _idbien integer, _idalicuotacontribucion integer, _iddetalleboletacontribucion integer) RETURNS integer AS $BODY$
DECLARE
	_reg record;
	_anio integer;
	_numero integer;
	_contribuyente character varying = '';
	_dni character varying = '';
	_nrobien character varying = '';
	_idcertificado integer;
	_meshasta integer;
	query character varying;
	_titulo1 character varying;
	_titulo2 character varying;
	_tipoautomotor character varying :='';
	_marca character varying := '';
	_modelo character varying := '';
	_categoria character varying := '';
	_leyenda1 character varying := '';
	_leyenda2 character varying := '';
	_domicilio character varying := '';
BEGIN 

	SELECT INTO _anio EXTRACT('year' FROM now()::date);
	SELECT INTO _meshasta EXTRACT('month' FROM now()::date);
	SELECT INTO _numero PUBLIC.ISNULL(max(numero),0)::integer + 1 FROM taxes.certificados WHERE idalicuotacontribucion = _idalicuotacontribucion;

	IF (_idtipoimpuesto = 1 OR _idtipoimpuesto = 2) THEN 

		SELECT INTO _reg * FROM taxes.padroncatastral WHERE catastro = (SELECT numero FROM taxes.catastros WHERE idcatastro = _idbien);

		IF (_reg is not null) THEN

			_contribuyente := _reg.domape;
			_dni := _reg.domnudoc;
			_nrobien := _reg.catastro;
			_titulo1 := 'IMPUESTO A LOS CATASTROS';
			_titulo2 := 'CERTIFICADO DE LIBRE DEUDA';	
		ELSE
			RAISE EXCEPTION 'OCURRIO UN ERROR, LOS DATOS NO SE GRABARON';
		END IF;

	ELSIF (_idtipoimpuesto = 3) THEN

		SELECT INTO _reg * FROM taxes.padronautomotor WHERE iddominio = (SELECT iddominio FROM taxes.automotores WHERE idautomotor = _idbien);

		IF ( NOT (_reg is null)) THEN
			_contribuyente := _reg.titular;
			_dni := _reg.dni;
			_nrobien := _reg.dominio;
			_titulo1 := 'IMPUESTO A LOS AUTOMOTORES';
			IF (_idalicuotacontribucion = 1) THEN 
				_titulo2 := 'CERTIFICADO DE LIBRE DEUDA';	
			ELSE 
				_titulo2 := 'CERTIFICADO DE LIBRE DEUDA CON BAJA';
			END IF;

			_tipoautomotor := _reg.tipo;
			_marca := _reg.marca;
			_modelo := _reg.modelo;
			_categoria := _reg.idtipocategoria;
			_domicilio := _reg.domicilio;
			_leyenda1 := _reg.nromotor;
		ELSE
			RAISE EXCEPTION 'OCURRIO UN ERROR, LOS DATOS NO SE GRABARON';
		END IF;
	ELSE
		RAISE EXCEPTION 'FUNCIONALIDAD NO DISPONIBLE';
	END IF;

	SELECT INTO _idcertificado PUBLIC.ISNULL(MAX(idcertificado),0)::integer + 1 FROM taxes.certificados;

	query := 'INSERT INTO taxes.certificados (
			idcertificado,
			idalicuotacontribucion,
			anio,
			numero,
			titulo1,
			titulo2,
			idcontribuyente,
			contribuyente,
			dni,
			fechaemision,
			idbien,
			nrobien,
			tipoautomotor,
			marca,
			modelo,
			categoria,
			meshasta,
			aniohasta,
			usuario,
			leyenda1,
			leyenda2,
			estado,
			iddetalleboletacontribucion,
			domicilio
			) VALUES( 
			'|| _idcertificado ||',
			'|| _idalicuotacontribucion ||',
			'|| _anio ||',
			'|| _numero ||',
			'''|| _titulo1 ||''',
			'''|| _titulo2 ||''',
			-1,
			'''|| _contribuyente ||''',
			'''|| _dni ||''',
			now(),
			'|| _idbien ||',
			'''|| _nrobien ||''',
			'''|| _tipoautomotor ||''',
			'''|| _marca ||''',
			'''|| _modelo ||''',
			'''|| _categoria ||''',
			'|| _meshasta ||',
			'|| _anio ||',
			'''|| "session_user"() ||''',
			'''|| _leyenda1 ||''',
			'''|| _leyenda2 ||''',
			'''',
			'|| _iddetalleboletacontribucion ||',
			'''|| _domicilio ||'''
			)';
--	raise exception 'query: %',query;

	EXECUTE query;	
	RETURN _idcertificado;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION taxes.addcertificado(_idtipoimpuesto integer, _idbien integer, _idalicuotacontribucion integer, _iddetalleboletacontribucion integer) IS '2010-11-05: (santiago)';
CREATE OR REPLACE FUNCTION taxes.adddetalleboletacontribucion(_idboletacontribucion integer, _monto double precision, _interes double precision, _deducciones double precision, _idtipoimpuesto integer, _idalicuotacontribucion integer, _idbien integer, _concepto character varying, _contribuyente character varying) RETURNS integer AS $BODY$
DECLARE

	_iddetalleboletacontribucion integer;
	query character varying;
	_registro record;
BEGIN 

	SELECT INTO _iddetalleboletacontribucion PUBLIC.ISNULL(MAX(iddetalleboletacontribucion),0)::integer + 1 FROM taxes.detalleboletascontribuciones;

	query := 'INSERT INTO taxes.detalleboletascontribuciones (
				iddetalleboletacontribucion,
				idboletacontribucion,
				fechavto,
				monto,
				interes,
				deducciones,
				montototal,
				idtipoimpuesto,
				idalicuotacontribucion,
				estado,
				fechaimpresion,
				fechapago
				) VALUES( 
				'|| _iddetalleboletacontribucion ||',
				'|| _idboletacontribucion ||',
				now()::date,
				PUBLIC.TOMONEY('|| _monto ||'),
				PUBLIC.TOMONEY('|| _interes ||'),
				PUBLIC.TOMONEY('|| _deducciones ||'),
				PUBLIC.TOMONEY('|| (_monto + _interes) - _deducciones||'),
				'|| _idtipoimpuesto ||',
				'|| _idalicuotacontribucion ||',
				'''',
				now()::date,
				NULL
				)';

		EXECUTE query;

	SELECT INTO _registro * FROM taxes.alicuotascontribuciones WHERE idalicuotacontribucion = _idalicuotacontribucion;

	IF (_registro is not null) THEN 
		IF (_registro.libredeuda AND NOT _registro.multa) THEN -- SE TRATA DE UN LIBRE DEUDA
			PERFORM taxes.addCertificado(_idtipoimpuesto, _idbien, _idalicuotacontribucion, _iddetalleboletacontribucion);
		ELSIF  (NOT _registro.libredeuda AND NOT _registro.baja AND NOT _registro.multa) THEN -- SE TRATA DE UN RECIBO DE CONTRIBUCIÓN
			PERFORM taxes.addReciboContribucion(_iddetalleboletacontribucion, _idalicuotacontribucion, _concepto, _contribuyente, ((_monto + _interes) - _deducciones), _registro.nombre,'');
		ELSIF  (_registro.multa) THEN -- SE TRATA DE UNA MULTA
			-- desarrollar el registro de la multa
		ELSE	-- SI SE TRATA OTRO CONCEPTO

		END IF;
	END IF;

	/* Actualizar el monto total de la tabla boletascontribuciones */
	
	RETURN _iddetalleboletacontribucion;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION taxes.adddetalleboletacontribucion(_idboletacontribucion integer, _monto double precision, _interes double precision, _deducciones double precision, _idtipoimpuesto integer, _idalicuotacontribucion integer, _idbien integer, _concepto character varying, _contribuyente character varying) IS '2010-11-05: (santiago)';
CREATE OR REPLACE FUNCTION taxes.getalicuotacontribucion(_idalicuotacontribucion integer) RETURNS double precision AS $BODY$
DECLARE
        _alicuota double precision = 0.0;
BEGIN 

        /*IF (_idalicuotacontribucion < 5) THEN*/ 
                SELECT INTO _alicuota (valormodulo * multiplicador) FROM taxes.alicuotascontribuciones
                                WHERE   idalicuotacontribucion = _idalicuotacontribucion
                                AND     estado <> '*';
        /*ELSE 
                RAISE EXCEPTION'FUNCIONALIDAD NO IMPLEMENTADA';
        END IF;*/

        RETURN _alicuota::double precision;

END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION taxes.getalicuotacontribucion(_idalicuotacontribucion integer) IS '2010-11-05: (santiago)';


CREATE TABLE taxes.modeloscontribuciones (
    idmodelo SERIAL,
    nombre character varying NOT NULL DEFAULT '',
    estado character NOT NULL DEFAULT '',
    PRIMARY KEY (idmodelo)
);
COMMENT ON TABLE taxes.modeloscontribuciones IS 'Modelos preconfigurados para Boletas de Contribuciones';;
COMMENT ON COLUMN taxes.modeloscontribuciones.idmodelo IS 'ID de Modelo';
COMMENT ON COLUMN taxes.modeloscontribuciones.nombre IS 'Nombre del Modelo';
COMMENT ON COLUMN taxes.modeloscontribuciones.estado IS '*: Borrado'; 

CREATE TABLE taxes.detallemodeloscontribuciones (
    idmodelo integer,
    idalicuotacontribucion integer,
    descripcion character varying NOT NULL DEFAULT ''
);


COMMENT ON TABLE taxes.detallemodeloscontribuciones IS 'Detalle de los Modelos para Boletas de Contribuciones';;
COMMENT ON COLUMN taxes.detallemodeloscontribuciones.idmodelo IS 'ID de Modelo';
COMMENT ON COLUMN taxes.detallemodeloscontribuciones.idalicuotacontribucion IS 'ID de Alícuota Contribución'; 
COMMENT ON COLUMN taxes.detallemodeloscontribuciones.descripcion IS 'Descripción de la Contribución'; 

ALTER TABLE taxes.detallemodeloscontribuciones ADD FOREIGN KEY (idmodelo) REFERENCES taxes.modeloscontribuciones(idmodelo) ON UPDATE CASCADE ON DELETE RESTRICT;
ALTER TABLE taxes.detallemodeloscontribuciones ADD FOREIGN KEY (idalicuotacontribucion) REFERENCES taxes.alicuotascontribuciones(idalicuotacontribucion) ON UPDATE CASCADE ON DELETE RESTRICT;

INSERT INTO taxes.modeloscontribuciones (idmodelo, nombre, estado) VALUES (1, 'Modelo vacío'::character varying, ''::bpchar);
INSERT INTO taxes.modeloscontribuciones (idmodelo, nombre, estado) VALUES (2, 'Terrenos Cementerio (Terreno para Covacha)'::character varying, ''::bpchar);
INSERT INTO taxes.modeloscontribuciones (idmodelo, nombre, estado) VALUES (3, 'Terrenos Cementerio (Covacha Superpuesta)'::character varying, ''::bpchar);
INSERT INTO taxes.modeloscontribuciones (idmodelo, nombre, estado) VALUES (4, 'Terrenos Cementerio (Mausoleos construidos)'::character varying, ''::bpchar);
INSERT INTO taxes.modeloscontribuciones (idmodelo, nombre, estado) VALUES (5, 'Terrenos Cementerio (Nichos)'::character varying, ''::bpchar);

INSERT INTO taxes.detallemodeloscontribuciones VALUES (2, 80, 'Concesión Terreno para Covacha x5 años');
INSERT INTO taxes.detallemodeloscontribuciones VALUES (2, 82, 'Por Inhumación');
INSERT INTO taxes.detallemodeloscontribuciones VALUES (2, 87, 'Por Derecho de Sepelio');
INSERT INTO taxes.detallemodeloscontribuciones VALUES (2, 90, 'Por Disposiciones Varias');
INSERT INTO taxes.detallemodeloscontribuciones VALUES (2, 88, 'Por Derechos Varios');
INSERT INTO taxes.detallemodeloscontribuciones VALUES (2, 87, 'Por Carrozas porta coronas');
INSERT INTO taxes.detallemodeloscontribuciones VALUES (2, 90, 'Por Limpieza y Mantenimiento');

INSERT INTO taxes.detallemodeloscontribuciones VALUES (3, 80, 'Covacha Superpuesta x5 años');
INSERT INTO taxes.detallemodeloscontribuciones VALUES (3, 82, 'Por Inhumación');
INSERT INTO taxes.detallemodeloscontribuciones VALUES (3, 87, 'Por Derecho de Sepelio');
INSERT INTO taxes.detallemodeloscontribuciones VALUES (3, 90, 'Por Disposiciones Varias');
INSERT INTO taxes.detallemodeloscontribuciones VALUES (3, 88, 'Por Derechos Varios');
INSERT INTO taxes.detallemodeloscontribuciones VALUES (3, 87, 'Por Carrozas porta coronas');
INSERT INTO taxes.detallemodeloscontribuciones VALUES (3, 90, 'Por Limpieza y Mantenimiento');
INSERT INTO taxes.detallemodeloscontribuciones VALUES (3, 80, 'Renovación Covacha');

INSERT INTO taxes.detallemodeloscontribuciones VALUES (4, 80, 'Mausoleos construidos');
INSERT INTO taxes.detallemodeloscontribuciones VALUES (4, 82, 'Por Inhumación');
INSERT INTO taxes.detallemodeloscontribuciones VALUES (4, 87, 'Por Derecho de Sepelio');
INSERT INTO taxes.detallemodeloscontribuciones VALUES (4, 90, 'Por Disposiciones Varias');
INSERT INTO taxes.detallemodeloscontribuciones VALUES (4, 88, 'Por Derechos Varios');
INSERT INTO taxes.detallemodeloscontribuciones VALUES (4, 87, 'Por Carrozas porta coronas');
INSERT INTO taxes.detallemodeloscontribuciones VALUES (4, 90, 'Por Limpieza y Mantenimiento');

INSERT INTO taxes.detallemodeloscontribuciones VALUES (5, 80, 'Nichos');
INSERT INTO taxes.detallemodeloscontribuciones VALUES (5, 82, 'Por Inhumación');
INSERT INTO taxes.detallemodeloscontribuciones VALUES (5, 87, 'Por Derecho de Sepelio');
INSERT INTO taxes.detallemodeloscontribuciones VALUES (5, 90, 'Por Disposiciones Varias');
INSERT INTO taxes.detallemodeloscontribuciones VALUES (5, 88, 'Por Derechos Varios');
INSERT INTO taxes.detallemodeloscontribuciones VALUES (5, 87, 'Por Carrozas porta coronas');
INSERT INTO taxes.detallemodeloscontribuciones VALUES (5, 90, 'Por Limpieza y Mantenimiento');

CREATE OR REPLACE FUNCTION taxes.getallmodeloscontribuciones() RETURNS refcursor AS $BODY$
DECLARE
	returnCursor REFCURSOR;
	query TEXT;
BEGIN

	query := 'SELECT	*
		FROM
			taxes.modeloscontribuciones
		ORDER BY
			modeloscontribuciones.nombre';

	OPEN returnCursor FOR EXECUTE query;
	RETURN returnCursor;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION taxes.getallmodeloscontribuciones() IS '2010-11-08: (santiago)';
CREATE OR REPLACE FUNCTION taxes.setdetalleboletacontribucion(_iddetalleboletacontribucion integer, _idboletacontribucion integer, _anio integer, _numero integer, _monto double precision, _interes double precision, _deducciones double precision, _montototal double precision, _idtipoimpuesto integer, _idalicuotacontribucion integer, _estado character varying, _concepto character varying, _idcontribucion integer, _nombrecontribucion character varying, _nombrealicuota character varying, _calculo integer, _multiplicador double precision, _valormodulo double precision, _montobase double precision, _porcentaje double precision, _montofijo double precision) RETURNS integer AS $BODY$
BEGIN 

	UPDATE taxes.detalleboletascontribuciones SET
			fechavto = now()::date,
			monto = PUBLIC.TOMONEY(_monto),
			interes = PUBLIC.TOMONEY(_interes),
			deducciones = PUBLIC.TOMONEY(_deducciones),
			montototal = PUBLIC.TOMONEY((_monto + _interes) - _deducciones),
			idtipoimpuesto = _idtipoimpuesto,
			idalicuotacontribucion = _idalicuotacontribucion,
			fechaimpresion = now()::date,
			concepto = UPPER(_concepto),
			idcontribucion = _idcontribucion,
			nombrecontribucion = _nombrecontribucion,
			nombrealicuota  = _nombrealicuota,
			calculo = _calculo,
			multiplicador = _multiplicador,
			valormodulo = _valormodulo,
			montobase = _montobase,
			porcentaje = _porcentaje,
			montofijo = _montofijo
		WHERE
			iddetalleboletacontribucion = _iddetalleboletacontribucion;

	RETURN _iddetalleboletacontribucion;

END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION taxes.setdetalleboletacontribucion(_iddetalleboletacontribucion integer, _idboletacontribucion integer, _anio integer, _numero integer, _monto double precision, _interes double precision, _deducciones double precision, _montototal double precision, _idtipoimpuesto integer, _idalicuotacontribucion integer, _estado character varying, _concepto character varying, _idcontribucion integer, _nombrecontribucion character varying, _nombrealicuota character varying, _calculo integer, _multiplicador double precision, _valormodulo double precision, _montobase double precision, _porcentaje double precision, _montofijo double precision) IS '2010-11-08: (santiago)';
CREATE OR REPLACE FUNCTION taxes.setmodelocontribucion(_idboletacontribucion integer, _idmodelo integer) RETURNS integer AS $BODY$
DECLARE
	detalle record;
	boletacontribucion record;
BEGIN

	DELETE FROM taxes.detalleboletascontribuciones WHERE idboletacontribucion = _idboletacontribucion;
	SELECT INTO boletacontribucion * FROM taxes.boletascontribuciones WHERE idboletacontribucion = _idboletacontribucion;
	FOR detalle IN SELECT detallemodeloscontribuciones.idalicuotacontribucion,
				detallemodeloscontribuciones.descripcion,
				contribuciones.idcontribucion,
				contribuciones.nombre AS contribucion,
				alicuotascontribuciones.nombre AS alicuotacontribucion,
				alicuotascontribuciones.valorxmil,
				alicuotascontribuciones.valormodulo,
				alicuotascontribuciones.porcentaje,
				alicuotascontribuciones.multiplicador,
				alicuotascontribuciones.montofijo
			FROM taxes.detallemodeloscontribuciones
				INNER JOIN taxes.alicuotascontribuciones ON detallemodeloscontribuciones.idalicuotacontribucion = alicuotascontribuciones.idalicuotacontribucion
				INNER JOIN taxes.contribuciones ON alicuotascontribuciones.idcontribucion = contribuciones.idcontribucion
			WHERE idmodelo = _idmodelo LOOP
		PERFORM taxes.adddetalleboletacontribucion(_idboletacontribucion, 
				boletacontribucion.anio,
				boletacontribucion.numero,
				0,
				0,
				0,
				0,
				6, /* Contribuciones */
				detalle.idalicuotacontribucion,
				'',
				'' || detalle.descripcion || '',
				detalle.idcontribucion,
				'' || detalle.contribucion || '',
				'' || detalle.alicuotacontribucion || '',
				0, /* Cálculo */
				detalle.multiplicador,
				detalle.valormodulo,
				detalle.valorxmil,
				detalle.porcentaje,
				detalle.montofijo);
	END LOOP;
	RETURN _idboletacontribucion;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION taxes.setmodelocontribucion(_idboletacontribucion integer, _idmodelo integer) IS '2010-11-08: (santiago)';

UPDATE organigrama.dependencias SET misiones = '' WHERE misiones IS NULL;
ALTER TABLE organigrama.dependencias ALTER COLUMN misiones SET NOT NULL, ALTER COLUMN misiones SET DEFAULT '';
UPDATE organigrama.dependencias SET funciones = '' WHERE funciones IS NULL;
ALTER TABLE organigrama.dependencias ALTER COLUMN funciones SET NOT NULL, ALTER COLUMN funciones SET DEFAULT '';

