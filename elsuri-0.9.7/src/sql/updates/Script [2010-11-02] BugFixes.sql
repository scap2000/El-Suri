CREATE OR REPLACE FUNCTION taxes.generarpadronautomotor(_iddominio integer) RETURNS integer AS $BODY$
DECLARE
	_automotor record;
	desde date;
	_idautomotor integer;
BEGIN

	SELECT INTO _automotor iddominio, pagadesde, fechaexencion, cuota FROM taxes.padronautomotor WHERE iddominio = _iddominio;
	
	IF(_automotor.pagadesde is null) THEN
		desde := '2000-07-01';
	ELSE 
		desde := _automotor.pagadesde;
	END IF;

	SELECT INTO _idautomotor public.ISNULL(max(idautomotor),0)::integer + 1 FROM taxes.automotores;

	INSERT INTO taxes.automotores VALUES (
		_idautomotor,
		_automotor.iddominio,
		desde,
		_automotor.fechaexencion::date,
		_automotor.cuota,
		''
	);

	RETURN _idautomotor;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION taxes.generarpadronautomotor(_iddominio integer) IS '2010-11-02: (administrador)';
CREATE OR REPLACE FUNCTION cashflow.getallpagosdeterceros(_fechacobro character varying, _banco character varying, _nombre character varying, _tipocobrado integer, _limit integer, _offset integer) RETURNS refcursor AS $BODY$
DECLARE
	returnCursor REFCURSOR;
	query TEXT;
	filter character varying := '';
BEGIN

	IF (_fechacobro != '') THEN 
		filter := filter || ' AND pagosdeterceros.fechacobro::date = '''|| _fechacobro ||'''::date ';
	END IF;

	IF (_banco != '') THEN 
		filter := filter || ' AND UPPER(pagosdeterceros.banco) LIKE UPPER(''%'|| _banco ||'%'')';
	END IF;

	IF (_nombre != '') THEN 
		filter := filter || ' AND UPPER(pagosdeterceros.nombre) LIKE UPPER(''%'|| _nombre ||'%'')';
	END IF;

	IF (_tipocobrado = 1) THEN 
		filter := filter || ' AND pagosdeterceros.cobrado';
	ELSIF (_tipocobrado = 2) THEN
		filter := filter || ' AND NOT pagosdeterceros.cobrado';
	END IF;


	query := 'SELECT	pagosdeterceros.idpagodetercero,
			pagosdeterceros.fechacobro,
			pagosdeterceros.idtipopago,
			paymenttype_tabs.name,
			pagosdeterceros.numero,
			pagosdeterceros.banco,
			pagosdeterceros.nombre,
			pagosdeterceros.monto,
			pagosdeterceros.cobrado,
			CASE WHEN (pagosdeterceros.cobrado) 
				THEN ''SI''::TEXT
				ELSE ''NO''::TEXT
			END AS escobrado,
			pagosdeterceros.rebotado,
			CASE WHEN (pagosdeterceros.rebotado) 
				THEN ''SI''::TEXT
				ELSE ''NO''::TEXT
			END AS esrebotado,
			pagosdeterceros.observaciones,
			pagosdeterceros.estado,
			pagosdeterceros.fecharecepcion,
			payments.idpayment,
			to_char(ABS(payments.idpayment),''000000'') AS nropago
		FROM 	cashflow.pagosdeterceros
			INNER JOIN tabs.paymenttype_tabs ON paymenttype_tabs.idpaymenttype_tabs = pagosdeterceros.idtipopago
			INNER JOIN cashier.paymentsways ON paymentsways.idpagodetercero = pagosdeterceros.idpagodetercero
			INNER JOIN cashier.payments ON payments.idpayment = paymentsways.idpayment
		WHERE
			pagosdeterceros.estado<>''*'' 
		AND 	pagosdeterceros.idpagodetercero > 0
		'|| filter ||'
		
		ORDER BY
			pagosdeterceros.fechacobro';
--	raise exception'query: %',query;

	If (_limit != 0) THEN
		query := query || ' LIMIT '|| _limit ||' OFFSET '|| _offset;
	END IF;
	
	OPEN returnCursor FOR EXECUTE query;
	RETURN returnCursor;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION cashflow.getallpagosdeterceros(_fechacobro character varying, _banco character varying, _nombre character varying, _tipocobrado integer, _limit integer, _offset integer) IS '2010-10-14: (cesar)';
CREATE OR REPLACE FUNCTION taxes.puedehacerpagocontado(_idbien integer, _idtipoimpuesto integer) RETURNS boolean AS $BODY$
DECLARE
	_anio integer;
	_fechainicio character varying;
	_fechafin character varying;
	resultado boolean;
	_test boolean;
	_cantidadAnticiposAdeudados integer;
BEGIN
	SELECT INTO _anio EXTRACT('year' FROM now()::date);
	_fechainicio = _anio||'-01-01';
	_fechafin = _anio||'-12-20';

	SELECT INTO _test (now()::date BETWEEN _fechainicio::date AND _fechafin::date);
	IF ( _test ) THEN
		resultado = true;
		/*IF (_idtipoimpuesto = 1) THEN --TGS
			SELECT INTO _cantidadAnticiposAdeudados COUNT(*) FROM taxes.cuotastgsmf where year <= (_anio - 1) AND idvoucher = -1 AND idpaymentplans = 0 AND paidPercentage <> 100 AND idcatastro  = _idbien;
		ELSE
			IF (_idtipoimpuesto = 2) THEN --INMOB
							SELECT INTO _cantidadAnticiposAdeudados COUNT(*) FROM taxes.cuotasinmob where year <= (_anio - 1) AND idvoucher = -1 AND idpaymentplans = 0 AND paidPercentage <> 100 AND idcatastro  = _idbien;
			ELSE
				IF (_idtipoimpuesto = 3) THEN --AUTOMOTOR
								SELECT INTO _cantidadAnticiposAdeudados COUNT(*) FROM taxes.cuotasautomotor where year <= (_anio - 1) AND idvoucher = -1 AND idpaymentplans = 0 AND paidPercentage <> 100 AND idautomotor  = _idbien;
				END IF;
			END If;
		END IF;

		IF (_cantidadAnticiposAdeudados > 0) THEN
			resultado = false;
		ELSE
			resultado = true;
		END IF;*/
	ELSE
		resultado = false;
	END IF;
RETURN resultado;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION taxes.puedehacerpagocontado(_idbien integer, _idtipoimpuesto integer) IS '2010-10-12: (barbozam)';
CREATE OR REPLACE FUNCTION resourcescontrol.getallrecursossincuentas(_recurso character varying, _limit integer, _offset integer) RETURNS refcursor AS $BODY$
DECLARE
	returnCursor REFCURSOR;
	query TEXT;
	filter TEXT = '';
BEGIN

	IF (_recurso != '') THEN
		filter := ' AND UPPER(resources.name) LIKE  UPPER(''%'|| _recurso ||'%'')';
	END IF;
	
	query := 'SELECT 	resources.idresource,
                           	resources.name
		FROM  	resourcescontrol.resources
		WHERE 	resources.estado != ''*''		
	      	AND 	resources.idaccount = -1
		'|| filter ||'
		ORDER BY resources.name';

	If (_limit != 0) THEN
		query := query || ' LIMIT '|| _limit ||' OFFSET '|| _offset;
	END IF;

	OPEN returnCursor FOR EXECUTE query;

	RETURN returnCursor;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION resourcescontrol.getallrecursossincuentas(_recurso character varying, _limit integer, _offset integer) IS '2010-10-07: (cesar)';
CREATE OR REPLACE FUNCTION resourcescontrol.setcuentaxrecursos(_idseleccionados character varying, _idaccount integer) RETURNS boolean AS $BODY$
DECLARE 
	query character varying;
BEGIN 	
	query := 'UPDATE 	resourcescontrol.resources 
		SET	idaccount = '|| _idaccount ||'
		WHERE 	idresource IN ('|| _idseleccionados ||')';

	EXECUTE  query;
	RETURN true::boolean;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION resourcescontrol.setcuentaxrecursos(_idseleccionados character varying, _idaccount integer) IS '2010-10-07: (cesar)';
CREATE OR REPLACE FUNCTION resourcescontrol.addresourcebyname(_resourcename character varying) RETURNS boolean AS $BODY$
BEGIN

	PERFORM resourcescontrol.addresource(
					UPPER(_resourcename),
					0,
					0,
					-1,
					'false',
					'',
					0,
					0,
					0,
					false,
					'p');
	
	RETURN true::boolean;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION resourcescontrol.addresourcebyname(_resourcename character varying) IS '2010-10-07: (barbozam)';
CREATE OR REPLACE FUNCTION resourcescontrol.existresource(_resourcename character varying) RETURNS boolean AS $BODY$
BEGIN
	RETURN ( SELECT (COUNT(name)>0)::boolean
		FROM resourcescontrol.resources 
		WHERE UPPER(name) = UPPER(_resourcename)
		AND estado != '*');
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION resourcescontrol.existresource(_resourcename character varying) IS '2010-10-07: (barbozam)';
CREATE OR REPLACE FUNCTION sueldos.getallanticiposdehaberesemitidos(_fechainicio character varying, _fechafin character varying, _nombre character varying, _legajo integer, _limit integer, _offset integer) RETURNS refcursor AS $BODY$
DECLARE
	returnCursor REFCURSOR;
	query TEXT;
	filter character varying;
BEGIN

	IF (_fechainicio != '' AND _fechafin != '') THEN
		filter := 'AND anticiposdehaberes.fechaemision::DATE BETWEEN '''|| _fechainicio ||'''::date and '''|| _fechafin ||'''::date';
	ELSIF (_fechainicio != '' AND _fechafin = '') THEN
		filter := 'AND anticiposdehaberes.fechaemision::DATE > '''|| _fechainicio ||'''::date ';
	ELSIF (_fechainicio = '' AND _fechafin != '') THEN
		filter := 'AND anticiposdehaberes.fechaemision::DATE < '''|| _fechafin ||'''::date ';
	ELSE 
		filter := '';
	END IF;

	IF (_nombre != '') THEN
		filter := filter || ' AND (UPPER(anticiposdehaberes.apellido) LIKE UPPER(''%'|| _nombre ||'%'') OR 
				      UPPER(anticiposdehaberes.nombres) LIKE UPPER(''%'|| _nombre ||'%''))';
	END IF;

	IF (_legajo != 0) THEN
		filter := filter || ' AND anticiposdehaberes.legajo = '|| _legajo ||' ';
	END IF;

	query := 'SELECT 	anticiposdehaberes.idanticipodehaberes,
			anticiposdehaberes.anio,
			anticiposdehaberes.numero,
			anticiposdehaberes.fechaemision,
			anticiposdehaberes.anio ||''-''|| TRIM(TO_CHAR(anticiposdehaberes.numero,''000000'')) AS number,
			anticiposdehaberes.apellido ||'', ''|| anticiposdehaberes.nombres AS empleado,
			anticiposdehaberes.legajo,
			anticiposdehaberes.barcode,
			anticiposdehaberes.fechapago,
			anticiposdehaberes.idpersona,
			anticiposdehaberes.idlegajo,
			anticiposdehaberes.apellido,
			anticiposdehaberes.nombres,
			anticiposdehaberes.fechadescuento,
			anticiposdehaberes.estadoanticipo,
			anticiposdehaberes.monto,
			anticiposdehaberes.idbookkeepingentry,
			anticiposdehaberes.usuario,
			anticiposdehaberes.estado
       	 	FROM 	sueldos.anticiposdehaberes
       		WHERE 	anticiposdehaberes.estado != ''*''
		AND 	anticiposdehaberes.fechapago IS NULL
		AND 	anticiposdehaberes.estadoanticipo = ''EMITIDO''
			'|| filter ||'
		ORDER BY  anticiposdehaberes.anio,anticiposdehaberes.numero, anticiposdehaberes.fechaemision';

--	RAISE EXCEPTION'query: %', query;
	
	If (_limit != 0) THEN
		query := query || ' LIMIT '|| _limit ||' OFFSET '|| _offset;
	END IF;

	OPEN returnCursor FOR EXECUTE query;

	RETURN returnCursor;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION sueldos.getallanticiposdehaberesemitidos(_fechainicio character varying, _fechafin character varying, _nombre character varying, _legajo integer, _limit integer, _offset integer) IS '2010-10-06: (cesar)';
CREATE OR REPLACE FUNCTION cashflow.addbudgetmovementbyinvoice(_idvoucher integer, _idresource integer, _idbrand integer, _finalqty double precision, _price double precision, _amount double precision, _idpurchaseorder integer, _idcostcentre integer, _originalprice double precision) RETURNS integer AS $BODY$
DECLARE 
	id integer;
	idbudgetaux integer;
	idcostcentreaux integer;
	registro record;
	query character varying;
	idaccountaux integer;
	idbudgetmovementaux integer;
	idbudgetcostcentreaux integer;
	idaux integer;
BEGIN 
	/* Obtengo el id de la P.P. de la O.C. a registrarse */  
	SELECT INTO idbudgetaux idbudget
	FROM 	cashflow.budgetcostscentres 
	WHERE idcostcentre = _idcostcentre;

	IF (idbudgetaux > 0) THEN

		/* Obtengo el id de tipo de gasto del Recurso */
		SELECT INTO idaccountaux idaccount 
		FROM 	resourcescontrol.resources 
		WHERE 	idresource = _idresource;
		
		/* Actualizo el campo Invoiceamount del movimiento de la P.P. correspondiente*/
	/*	raise exception 'valores: %, %, %, %, %',idbudgetaux, idaccountaux, _idpurchaseorder, _idvoucher, _amount;*/
		SELECT INTO idbudgetmovementaux cashflow.addbudgetmovements(idbudgetaux, idaccountaux, _idpurchaseorder, 0,_idvoucher, _amount);



		/* Esto ya se hace en repairBudgets();
		--Obtengo el id de la tabla budgetcostscentres (PP x CC) 
		SELECT INTO idbudgetcostcentreaux idbudgetcostcentre FROM cashflow.budgetcostscentres 
			WHERE idcostcentre = _idcostcentre AND idbudget = idbudgetaux;

		--Sumar el Monto Gastado y restar el Monto Preventivo en la tabla Partida Presupuestaria por Centro de Costo y Tipo de Gasto (cashflow.BudgetCostCentreDetail)
		PERFORM cashflow.setbudgetcostcentredetailbyinvoice(idbudgetcostcentreaux, idaccountaux, _amount, idbudgetaux, _finalqty, _originalprice);
		*/
		
		/* Actualiza el Monto Facturado en el Detalle de la Orden de Compras correspondiente (campo realamount de la tabla resourcescontrol.purchaseorderdetail) */
		PERFORM cashflow.setpurchaseorderdetailbyinvoice(_idpurchaseorder, _idresource, _idbrand , _amount);
	
		/* Sumar el Monto de Deuda y restar el Monto Preventivo en la tabla Proveedores */
		EXECUTE 'UPDATE 	org.companies
			SET	predictedamount = ( companies.predictedamount - '|| (_finalqty * _originalprice) ||' )
				/* NO LO HAGO POR QUE LO TENGO QUE GLOBALIZAR DESDE EL VOUCHER */
				/* ,amount = ( companies.amount + '|| _amount ||' ) */
			FROM	resourcescontrol.purchaseorders
			WHERE 	companies.idcompany = purchaseorders.idprovider
				AND purchaseorders.idpurchaseorder = '|| _idpurchaseorder ;

		/* Omitido por cuestiones de rendimiento
		PERFORM cashflow.repairbudgets();
		*/

		RETURN 1;
	ELSE
		RETURN -1;
	END IF;	
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION cashflow.addbudgetmovementbyinvoice(_idvoucher integer, _idresource integer, _idbrand integer, _finalqty double precision, _price double precision, _amount double precision, _idpurchaseorder integer, _idcostcentre integer, _originalprice double precision) IS '2010-10-06: (admin)';
CREATE OR REPLACE FUNCTION cashflow.addbudgetmovementbyinvoicetoprovisionorder(_idvoucher integer, _provisionordersid character varying) RETURNS integer AS $BODY$
DECLARE 
	_voucher record;
	_voucherdetail record;
	id integer;
	idbudgetaux integer;
	idcostcentreaux integer;
	registro record;
	query character varying;
	idaccountaux integer;
	idbudgetmovementaux integer;
	idbudgetcostcentreaux integer;
	idaux integer;
	idpurchaseorderaux integer;
	purchaseorderdetailaux record;
BEGIN 
	/* Recupero en el registro "_voucher" el encabezado de la Factura */  
	SELECT INTO _voucher *, (vouchertype_tabs.name ||' Nº ' || vouchers.number) AS voucher, companiesbyaccounts.idaccount AS idprovideraccount
	FROM 	cashflow.vouchers 
		INNER JOIN tabs.vouchertype_tabs ON vouchertype_tabs.idvouchertype = vouchers.idvouchertype
		INNER JOIN org.companiesbyaccounts ON companiesbyaccounts.idcompany = vouchers.identity
	WHERE idvoucher = _idvoucher;	

	FOR _voucherdetail IN SELECT * FROM cashflow.voucherdetail WHERE idvoucher = _voucher.idvoucher LOOP
		
		/* Obtengo el id de la Ord. Cpra. correspondiente */
		query = 'SELECT 	purchaseorders.idpurchaseorder, purchaseorderdetail.idpurchaseorderdetail, purchaseorderdetail.idresource, 
				purchaseorderdetail.price,purchaseorderdetail.amount, purchaseorderdetail.idbrand,
				budgetcostscentres.idbudget, resources.idaccount
		FROM 	resourcescontrol.purchaseorderdetail
			INNER JOIN resourcescontrol.purchaseorders ON purchaseorders.idpurchaseorder = purchaseorderdetail.idpurchaseorder
			INNER JOIN cashflow.budgetcostscentres ON budgetcostscentres.idcostcentre = purchaseorders.idcostcentre
			INNER JOIN resourcescontrol.resources ON resources.idresource = purchaseorderdetail.idresource
		WHERE	idpurchaseorderdetail IN (
				SELECT  idpurchaseorderdetail
				FROM 	resourcescontrol.purchaseorderdetailxvoucherdetail 
				WHERE 	idvoucherdetailref = ' || _voucherdetail.idvoucherdetail || ') AND purchaseorders.idpurchaseorder IN (' || _provisionordersid || ');';
		EXECUTE query INTO purchaseorderdetailaux;

		IF (purchaseorderdetailaux.idbudget > 0) THEN
	
			/* Actualizo el campo Invoiceamount del movimiento de la P.P. correspondiente*/
			SELECT INTO idbudgetmovementaux cashflow.addbudgetmovements(purchaseorderdetailaux.idbudget, purchaseorderdetailaux.idaccount, purchaseorderdetailaux.idpurchaseorder, 0, _voucherdetail.idvoucher, _voucherdetail.amount);

			/* Obtengo el id de la tabla budgetcostscentres (PP x CC) */
			SELECT INTO idbudgetcostcentreaux idbudgetcostcentre FROM cashflow.budgetcostscentres 
				WHERE idcostcentre = _voucherdetail.idcostcentre AND idbudget = purchaseorderdetailaux.idbudget;
	
			/* Sumar el Monto Gastado y restar el Monto Preventivo en la tabla Partida Presupuestaria por Centro de Costo y Tipo de Gasto (cashflow.BudgetCostCentreDetail) */
			PERFORM cashflow.setbudgetcostcentredetailbyinvoice(idbudgetcostcentreaux, purchaseorderdetailaux.idaccount, _voucherdetail.amount, purchaseorderdetailaux.idbudget, _voucherdetail.finalqty, purchaseorderdetailaux.price);
			
			/* Actualiza el Monto Facturado en el Detalle de la Orden de Compras correspondiente (campo realamount de la tabla resourcescontrol.purchaseorderdetail) */
			PERFORM cashflow.setpurchaseorderdetailbyinvoice(purchaseorderdetailaux.idpurchaseorder, _voucherdetail.idresource, _voucherdetail.idbrand , _voucherdetail.amount);

			EXECUTE 'UPDATE 	org.companies
				SET	predictedamount = ( companies.predictedamount - '|| (_voucherdetail.finalqty * purchaseorderdetailaux.price) ||' )
				WHERE 	companies.idcompany = '|| _voucher.identity ;

		END IF;	

	END LOOP;
	
	PERFORM	cashflow.setVoucherByTotalAmount(_voucher.idvoucher);
	
	/* Actualizo el campo amount de la tabla de proveedores */
	EXECUTE  'UPDATE 	org.companies
		 SET	amount = ( companies.amount + (SELECT amount FROM cashflow.vouchers WHERE idvoucher = '|| _voucher.idvoucher ||') )	
		 WHERE 	companies.idcompany = '|| _voucher.identity ;

	PERFORM	resourcescontrol.setResourceBrandByPrice(_voucherdetail.idresource,_voucherdetail.idbrand,_voucherdetail.price);
	PERFORM	accounting.addBookKeepingEntryFromInvoice(_voucher.idvoucher, _voucher.voucher, _voucher.idprovideraccount);
	/* Actualizo el estado de la Orden de Provision  13 ==> Facturado*/
	EXECUTE  'UPDATE 	resourcescontrol.purchaseorders
		 SET	idrequeststatus = 14	
		 WHERE 	idpurchaseorder IN ('|| _provisionordersid || ')';

	/* Omitido por cuestiones de rendimiento
	PERFORM cashflow.repairbudgets();
	*/

	RETURN 1;

END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION cashflow.addbudgetmovementbyinvoicetoprovisionorder(_idvoucher integer, _provisionordersid character varying) IS '2010-10-06: (admin)';
CREATE OR REPLACE FUNCTION cashflow.addbudgetmovementbyinvoicetoprovisionorder(_idvoucher integer, _idvoucherselected integer) RETURNS integer AS $BODY$
DECLARE 
	_voucher record;
	_voucherdetail record;
	id integer;
	idbudgetaux integer;
	idcostcentreaux integer;
	registro record;
	query character varying;
	idaccountaux integer;
	idbudgetmovementaux integer;
	idbudgetcostcentreaux integer;
	idaux integer;
	idpurchaseorderaux integer;
	purchaseorderdetailaux record;
BEGIN 
	/* Recupero en el registro "_voucher" el encabezado de la Factura */  
	SELECT INTO _voucher *, (vouchertype_tabs.name ||' Nº ' || vouchers.number) AS voucher, companiesbyaccounts.idaccount AS idprovideraccount
	FROM 	cashflow.vouchers 
		INNER JOIN tabs.vouchertype_tabs ON vouchertype_tabs.idvouchertype = vouchers.idvouchertype
		INNER JOIN org.companiesbyaccounts ON companiesbyaccounts.idcompany = vouchers.identity
	WHERE idvoucher = _idvoucher;	

	FOR _voucherdetail IN SELECT * FROM cashflow.voucherdetail WHERE idvoucher = _voucher.idvoucher LOOP
		
		/* Obtengo el id de la Ord. Cpra. correspondiente */  
		SELECT 	INTO purchaseorderdetailaux purchaseorders.idpurchaseorder, purchaseorderdetail.idpurchaseorderdetail, purchaseorderdetail.idresource, 
				purchaseorderdetail.price,purchaseorderdetail.amount, purchaseorderdetail.idbrand,
				budgetcostscentres.idbudget, resources.idaccount
		FROM 	resourcescontrol.purchaseorderdetail
			INNER JOIN resourcescontrol.purchaseorders ON purchaseorders.idpurchaseorder = purchaseorderdetail.idpurchaseorder
			INNER JOIN cashflow.budgetcostscentres ON budgetcostscentres.idcostcentre = purchaseorders.idcostcentre
			INNER JOIN resourcescontrol.resources ON resources.idresource = purchaseorderdetail.idresource
		WHERE	idpurchaseorderdetail = (
		
				SELECT  idpurchaseorderdetail
				FROM 	resourcescontrol.purchaseorderdetailxvoucherdetail 
				WHERE 	idvoucherdetailref = _voucherdetail.idvoucherdetail);


		IF (purchaseorderdetailaux.idbudget > 0) THEN
	
			/* Actualizo el campo Invoiceamount del movimiento de la P.P. correspondiente*/
			SELECT INTO idbudgetmovementaux cashflow.addbudgetmovements(purchaseorderdetailaux.idbudget, purchaseorderdetailaux.idaccount, purchaseorderdetailaux.idpurchaseorder, 0, _voucherdetail.idvoucher, _voucherdetail.amount);

			/* Obtengo el id de la tabla budgetcostscentres (PP x CC) */
			SELECT INTO idbudgetcostcentreaux idbudgetcostcentre FROM cashflow.budgetcostscentres 
				WHERE idcostcentre = _voucher.idcostcentre AND idbudget = purchaseorderdetailaux.idbudget;
	
			/* Sumar el Monto Gastado y restar el Monto Preventivo en la tabla Partida Presupuestaria por Centro de Costo y Tipo de Gasto (cashflow.BudgetCostCentreDetail) */
			PERFORM cashflow.setbudgetcostcentredetailbyinvoice(idbudgetcostcentreaux, purchaseorderdetailaux.idaccount, _voucherdetail.amount, purchaseorderdetailaux.idbudget, _voucherdetail.finalqty, purchaseorderdetailaux.price);
			
			/* Actualiza el Monto Facturado en el Detalle de la Orden de Compras correspondiente (campo realamount de la tabla resourcescontrol.purchaseorderdetail) */
			PERFORM cashflow.setpurchaseorderdetailbyinvoice(purchaseorderdetailaux.idpurchaseorder, _voucherdetail.idresource, _voucherdetail.idbrand , _voucherdetail.amount);

			EXECUTE 'UPDATE 	org.companies
				SET	predictedamount = ( companies.predictedamount - '|| (_voucherdetail.finalqty * purchaseorderdetailaux.price) ||' )
				WHERE 	companies.idcompany = '|| _voucher.identity ;

		END IF;	

	END LOOP;
	
	PERFORM	cashflow.setVoucherByTotalAmount(_voucher.idvoucher);
	
	/* Actualizo el campo amount de la tabla de proveedores */
	EXECUTE  'UPDATE 	org.companies
		 SET	amount = ( companies.amount + (SELECT amount FROM cashflow.vouchers WHERE idvoucher = '|| _voucher.idvoucher ||') )	
		 WHERE 	companies.idcompany = '|| _voucher.identity ;

	PERFORM	resourcescontrol.setResourceBrandByPrice(_voucherdetail.idresource,_voucherdetail.idbrand,_voucherdetail.price);
	PERFORM	accounting.addBookKeepingEntryFromInvoice(_voucher.idvoucher, _voucher.voucher, _voucher.idprovideraccount);
	/* Actualizo el estado de la Orden de Provision  11 ==> Comprado total */
	EXECUTE  'UPDATE 	resourcescontrol.purchaseorders
		 SET	idrequeststatus = 11	
		 WHERE 	idpurchaseorder = '|| _idvoucherselected;

	/* Omitido por cuestiones de rendimiento
	PERFORM cashflow.repairbudgets();
	*/

	RETURN 1;

END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION cashflow.addbudgetmovementbyinvoicetoprovisionorder(_idvoucher integer, _idvoucherselected integer) IS '2010-10-06: (admin)';
CREATE OR REPLACE FUNCTION cashflow.addbudgetmovementbyprovisionorder(_idpurchaseorder integer) RETURNS integer AS $BODY$
DECLARE 
	id integer;
	idbudgetaux integer;
	registro record;
	idaccountaux integer;
	idbudgetmovementaux integer;
	idbudgetcostcentreaux integer;
	idaux integer;
	totalamount double precision = 0;
	rs record;
BEGIN 
	/* Obtengo el id del Centro de Costos relacionado a la Orden de Provision */
	SELECT INTO rs idcostcentre, idprovider
	FROM 	resourcescontrol.purchaseorders 
	WHERE  idpurchaseorder = _idpurchaseorder;

	/* Obtengo el id de la P.P. de la Orden de Provision (que se corresponde con un C.C.) */
	SELECT INTO idbudgetaux idbudget
	FROM 	cashflow.budgetcostscentres 
	WHERE 	idcostcentre = rs.idcostcentre;

	IF (idbudgetaux > 0) THEN
		FOR registro IN SELECT * FROM resourcescontrol.purchaseorderdetail WHERE idpurchaseorder = _idpurchaseorder LOOP

			/* Obtengo el id de tipo de gasto del Recurso */
			SELECT INTO idaccountaux idaccount 
			FROM 	resourcescontrol.resources 
			WHERE 	idresource IN (SELECT	idresource 
						FROM 	resourcescontrol.purchaseorderdetail 
						WHERE 	idpurchaseorderdetail = registro.idpurchaseorderdetail);
			
			/* Registro de un nuevo movimiento de la P.P.*/
			SELECT INTO idbudgetmovementaux cashflow.addbudgetmovements(idbudgetaux, idaccountaux, _idpurchaseorder, registro.amount ,0,0);

			/* Esto ya se hace en repairBudgets()
			--Obtengo el id de la tabla budgetcostscentres (PP x CC) 
			SELECT INTO idbudgetcostcentreaux idbudgetcostcentre FROM cashflow.budgetcostscentres 
				WHERE idcostcentre = rs.idcostcentre AND idbudget = idbudgetaux;
 
			--Sumar el Monto Preventivo en la tabla Partida Presupuestaria por Centro de Costo y Tipo de Gasto 
			SELECT INTO idaux cashflow.setbudgetcostcentredetailbypurchaseorder(idbudgetcostcentreaux, idaccountaux, registro.amount, idbudgetaux);
			*/

			/** Sumo los montos parciales para despues actualizar el monto del encabezado de la Orden de Provision */
			totalamount = totalamount + registro.amount;
		END LOOP;

		/** Actualizo el monto del encabezado de la Orden de provision */ 
		EXECUTE'UPDATE resourcescontrol.purchaseorders SET amount = '|| totalamount ||' WHERE idpurchaseorder = '|| _idpurchaseorder;

		/** Incremento el monto preventivo de la tabla proveedores */ 
		EXECUTE'UPDATE org.companies SET predictedamount = ( predictedamount + '|| totalamount ||' ) WHERE idcompany = '|| rs.idprovider;

		/* Omitido por cuestiones de rendimiento
		PERFORM cashflow.repairbudgets();
		*/

		RETURN 1;
	ELSE
		RETURN -1;
	END IF;	
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION cashflow.addbudgetmovementbyprovisionorder(_idpurchaseorder integer) IS '2010-10-06: (admin)';
CREATE OR REPLACE FUNCTION cashflow.addbudgetmovementbypurchaseorder(_idpurchaseorder integer) RETURNS integer AS $BODY$
DECLARE 
	_rs record;
	_idbudget integer;
	_registro record;
	_idaccount integer;
	_idbudgetcostcentre integer;
	_totalamount double precision = 0;
	_idresourcesrequest integer;
	_regidpedido record;
	_cantitemsauth integer;
	_cantitemsoc integer;
BEGIN 

	/* Obtengo el id del Centro de Costos y el id del proveedor relacionado a la Orden de Compras */
	SELECT INTO _rs idcostcentre, idprovider
	FROM 	resourcescontrol.purchaseorders 
	WHERE  	idpurchaseorder = _idpurchaseorder;

	/* Obtengo el id de la P.P. de la Orden de Compras (que se corresponde con un C.C.) */
	SELECT INTO _idbudget idbudget
	FROM 	cashflow.budgetcostscentres 
	WHERE 	idcostcentre = _rs.idcostcentre;

	/* Obtengo el id del Pedido de Recursos para actualizar sus datos al final del proceso *
	SELECT INTO _idresourcesrequest DISTINCT(idresourcesrequest) 
	FROM 	resourcescontrol.resourcesrequestauthdetail
	WHERE	idresourcesrequestauthdetail IN (
				SELECT 	idresourcesrequestauthdetail
				FROM 	resourcescontrol.purchaseorderdetail 
				WHERE 	idpurchaseorder = _idpurchaseorder);
	*/

	IF (_idbudget > 0) THEN
		FOR _registro IN SELECT * FROM resourcescontrol.purchaseorderdetail WHERE idpurchaseorder = _idpurchaseorder LOOP

			/* Obtengo el id de tipo de gasto del Recurso */
			SELECT INTO _idaccount idaccount 
			FROM 	resourcescontrol.resources 
			WHERE 	idresource IN (SELECT	idresource 
						FROM 	resourcescontrol.purchaseorderdetail 
						WHERE 	idpurchaseorderdetail = _registro.idpurchaseorderdetail);
			
			/* Registro de un nuevo movimiento de la P.P.*/
			PERFORM cashflow.addbudgetmovements(_idbudget, _idaccount, _idpurchaseorder, _registro.amount ,0,0);

			/** Sumo los montos parciales para despues actualizar el monto del encabezado de la Orden de Compras */
			_totalamount = _totalamount + _registro.amount;
		END LOOP;

		/** Actualizo el monto del encabezado de la Orden de Compras */ 
		EXECUTE 'UPDATE resourcescontrol.purchaseorders SET amount = '|| _totalamount ||' WHERE idpurchaseorder = '|| _idpurchaseorder;

		/** Incremento el monto preventivo de la tabla proveedores */ 
		EXECUTE 'UPDATE org.companies SET predictedamount = ( predictedamount + '|| _totalamount ||' ) WHERE idcompany = '|| _rs.idprovider;

		/** Actualizo los estados de las tablas resourcesrequests y resourcesrequestsdetail 
		UPDATE resourcescontrol.resourcesrequests SET idrequeststatus = 4 WHERE idresourcesrequest = _idresourcesrequest;
		UPDATE resourcescontrol.resourcesrequestdetail SET idrequeststatus = 4 WHERE idresourcesrequest = _idresourcesrequest;
		*/

		/** Actualizo la tabla de Recursos por Marcar (si no existe algun Recurso x Marca ==> se agrega a la tabla */ 
		INSERT INTO resourcescontrol.resourcebrands (
			idresourcebrand, 
			idresource, 
			idbrand, 
			minstock,	 
			stock, 
			price, 		
			lifetime, 
			idlifetimetype, 
			estado)
		(SELECT 
			-1,
			idresource,
			idbrand,	
			0,
			0,
			0,	
			0,
			0,
			''
		FROM 
			resourcescontrol.purchaseorderdetail
		WHERE 	
			idpurchaseorder = _idpurchaseorder
		AND (idresource, idbrand) NOT IN (SELECT idresource, idbrand FROM resourcescontrol.resourcebrands));


		/** Actualizo el estado de la tabla resourcesrequestsdetail */ 
		UPDATE resourcescontrol.resourcesrequestdetail
		SET    idrequeststatus = 4
		WHERE  idresourcesrequestdetail IN (
			SELECT idresourcesrequestdetail
			     FROM   resourcescontrol.resourcesrequestauthdetail
			     WHERE  idresourcesrequestauthdetail IN (
					SELECT idresourcesrequestauthdetail 
			                     	FROM   resourcescontrol.purchaseorderdetail
			                    		WHERE  idpurchaseorder = _idpurchaseorder)
						);

		/** Actualizo el estado del Pedido de Materiales -- 4 ==> A Comprar */ 
		PERFORM resourcescontrol.setresourcesrequeststatus(idresourcesrequest, 4, _idpurchaseorder)
		FROM   resourcescontrol.resourcesrequests 
		WHERE  idresourcesrequest IN (
					SELECT DISTINCT(idresourcesrequest)
					FROM     resourcescontrol.resourcesrequestauthdetail
					WHERE    idresourcesrequestauthdetail IN (SELECT 	idresourcesrequestauthdetail
									  FROM 	resourcescontrol.purchaseorderdetail 
									  WHERE 	idpurchaseorder = _idpurchaseorder));

		/* Omitido por cuestiones de rendimiento
		PERFORM cashflow.repairbudgets();
		*/

		RETURN 1;
	ELSE
		RETURN -1;
	END IF;	
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION cashflow.addbudgetmovementbypurchaseorder(_idpurchaseorder integer) IS '2010-10-06: (admin)';
CREATE OR REPLACE FUNCTION cashflow.addbudgetmovementsbyinvoice(_idvoucher integer, _idresource integer, _idbrand integer, _finalqty double precision, _price double precision, _amount double precision, _idpurchaseorder integer, _idcostcentre integer, _originalprice double precision) RETURNS integer AS $BODY$
DECLARE 
	id integer;
	idbudgetaux integer;
	idcostcentreaux integer;
	registro record;
	query character varying;
	idaccountaux integer;
	idbudgetmovementaux integer;
	idbudgetcostcentreaux integer;
	idaux integer;
BEGIN 
	/* Obtengo el id de la P.P. de la O.C. a registrarse */  
	SELECT INTO idbudgetaux idbudget
	FROM 	cashflow.budgetcostscentres 
	WHERE idcostcentre = _idcostcentre;

	IF (idbudgetaux > 0) THEN

		/* Obtengo el id de tipo de gasto del Recurso */
		SELECT INTO idaccountaux idaccount 
		FROM 	resourcescontrol.resources 
		WHERE 	idresource = _idresource;
		
		/* Actualizo el campo Invoiceamount del movimiento de la P.P. correspondiente*/
	/*	raise exception 'valores: %, %, %, %, %',idbudgetaux, idaccountaux, _idpurchaseorder, _idvoucher, _amount;*/
		SELECT INTO idbudgetmovementaux cashflow.addbudgetmovements(idbudgetaux, idaccountaux, _idpurchaseorder, 0,_idvoucher, _amount);

		/* Esto ya se hace en repairBudgets()
		--Obtengo el id de la tabla budgetcostscentres (PP x CC)
		SELECT INTO idbudgetcostcentreaux idbudgetcostcentre FROM cashflow.budgetcostscentres 
			WHERE idcostcentre = _idcostcentre AND idbudget = idbudgetaux;

		--Sumar el Monto Gastado y restar el Monto Preventivo en la tabla Partida Presupuestaria por Centro de Costo y Tipo de Gasto (cashflow.BudgetCostCentreDetail)
		PERFORM cashflow.setbudgetcostcentredetailbyinvoice(idbudgetcostcentreaux, idaccountaux, _amount, idbudgetaux, _finalqty, _originalprice);
		*/
		
		/* Actualiza el Monto Facturado en el Detalle de la Orden de Compras correspondiente (campo realamount de la tabla resourcescontrol.purchaseorderdetail) */
		PERFORM cashflow.setpurchaseorderdetailbyinvoice(_idpurchaseorder, _idresource, _idbrand , _amount);
	
		/* Sumar el Monto de Deuda y restar el Monto Preventivo en la tabla Proveedores */
		EXECUTE 'UPDATE 	org.companies
			SET	predictedamount = ( companies.predictedamount - '|| (_finalqty * _originalprice) ||' )
				/* NO LO HAGO POR QUE LO TENGO QUE GLOBALIZAR DESDE EL VOUCHER */
				/* ,amount = ( companies.amount + '|| _amount ||' ) */
			FROM	resourcescontrol.purchaseorders
			WHERE 	companies.idcompany = purchaseorders.idprovider
				AND purchaseorders.idpurchaseorder = '|| _idpurchaseorder ;

		/* Omitido por cuestiones de rendimiento
		PERFORM cashflow.repairbudgets();
		*/

		RETURN 1;
	ELSE
		RETURN -1;
	END IF;	
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION cashflow.addbudgetmovementsbyinvoice(_idvoucher integer, _idresource integer, _idbrand integer, _finalqty double precision, _price double precision, _amount double precision, _idpurchaseorder integer, _idcostcentre integer, _originalprice double precision) IS '2010-10-06: (admin)';
CREATE OR REPLACE FUNCTION taxes.getmontosdeuda(_tipoimpuesto integer, _idbien integer, _cantanticipos integer, _descuento double precision) RETURNS refcursor AS $BODY$
DECLARE

	query text;
	returnCursor REFCURSOR;
	descuento double precision;
	_fechaUltimoAnticipo character varying := '';
	_fechaPrimerAnticipo character varying := '';
	_montoBasePuro double precision;
	_interesPuro double precision;
	_montoTotalPuro double precision;
	_montoTotalDescontado double precision;
	_montoDescuento double precision;

BEGIN
	_fechaPrimerAnticipo = '2000-06-01';

	IF (_tipoimpuesto = 1) THEN	--Impuesto TGS (calculado por Metros de Frente)
		
		SELECT INTO _fechaPrimerAnticipo date
		FROM taxes.cuotastgsmf 
		WHERE idcatastro = _idbien 
		AND idvoucher = -1
		AND idpaymentplans = 0
		ORDER BY year, number LIMIT 1;

		SELECT INTO _fechaUltimoAnticipo date 
		FROM taxes.cuotastgsmf  
		WHERE date IN (
			SELECT date
			FROM taxes.cuotastgsmf 
			WHERE idcatastro = _idbien 
			AND idvoucher = -1
			AND idpaymentplans = 0       
			ORDER BY year, number LIMIT _cantanticipos
		)
		AND idcatastro = _idbien
		ORDER BY date DESC
		LIMIT 1;
		IF (_fechaUltimoAnticipo IS NULL) THEN
			_fechaUltimoAnticipo = ''||now()::date;
		END IF;

		SELECT INTO _montoBasePuro ROUND(sum((100.0 - paidpercentage) * basicamount/100.0)::numeric,2) FROM taxes.cuotastgsmf WHERE idcatastro = _idbien AND (date BETWEEN _fechaPrimerAnticipo::date AND _fechaUltimoAnticipo::date);
		SELECT INTO _interesPuro ROUND(ROUND(sum((100.0 - paidpercentage) * accruedinterest/100.0)::numeric,2) * ROUND((1 - 0.0)::numeric,2)::numeric,2) FROM taxes.cuotastgsmf WHERE idcatastro =  _idbien AND (cuotastgsmf.date::date BETWEEN  _fechaPrimerAnticipo::date AND _fechaUltimoAnticipo::date);
		SELECT INTO _montoTotalPuro ROUND(sum((100.0 - paidpercentage) * totalamount/100.0)::numeric,2) FROM taxes.cuotastgsmf WHERE idcatastro = _idbien  AND (cuotastgsmf.date::date BETWEEN  _fechaPrimerAnticipo::date AND _fechaUltimoAnticipo::date);
		SELECT INTO _montoTotalDescontado ROUND(ROUND(sum((100.0 - paidpercentage) * totalamount/100.0)::numeric,2) * ROUND((1 - _descuento)::numeric,2)::numeric,2) FROM taxes.cuotastgsmf WHERE idcatastro =  _idbien AND (cuotastgsmf.date::date BETWEEN  _fechaPrimerAnticipo::date AND _fechaUltimoAnticipo::date);
		_montoDescuento = PUBLIC.TOMONEY(_montoTotalPuro - _montoTotalDescontado);

	ELSIF (_tipoimpuesto = 2) THEN	--Impuesto Inmobiliario

		SELECT INTO _fechaPrimerAnticipo date
		FROM taxes.cuotasinmob
		WHERE idcatastro = _idbien 
		AND idvoucher = -1
		AND idpaymentplans = 0
		ORDER BY year, number LIMIT 1;

		SELECT INTO _fechaUltimoAnticipo date 
		FROM taxes.cuotasinmob  
		WHERE date IN (
				SELECT date
				FROM taxes.cuotasinmob 
				WHERE idcatastro = _idbien
				AND idvoucher = -1
				AND idpaymentplans = 0 
				ORDER BY year, number LIMIT _cantanticipos
			)
		AND idcatastro = _idbien
		ORDER BY date DESC
		LIMIT 1;

		IF (_fechaUltimoAnticipo IS NULL) THEN
			_fechaUltimoAnticipo = ''||now()::date;
		END IF;

		SELECT INTO _montoBasePuro ROUND(sum((100.0 - paidpercentage) * basicamount/100.0)::numeric,2) FROM taxes.cuotasinmob WHERE idcatastro = _idbien AND (date BETWEEN _fechaPrimerAnticipo::date AND _fechaUltimoAnticipo::date);
		SELECT INTO _interesPuro ROUND(ROUND(sum((100.0 - paidpercentage) * accruedinterest/100.0)::numeric,2) * ROUND((1 - 0.0)::numeric,2)::numeric,2) FROM taxes.cuotasinmob WHERE idcatastro =  _idbien AND (cuotasinmob.date::date BETWEEN  _fechaPrimerAnticipo::date AND _fechaUltimoAnticipo::date);
		SELECT INTO _montoTotalPuro ROUND(sum((100.0 - paidpercentage) * totalamount/100.0)::numeric,2) FROM taxes.cuotasinmob WHERE idcatastro = _idbien  AND (cuotasinmob.date::date BETWEEN  _fechaPrimerAnticipo::date AND _fechaUltimoAnticipo::date);
		SELECT INTO _montoTotalDescontado ROUND(ROUND(sum((100.0 - paidpercentage) * totalamount/100.0)::numeric,2) * ROUND((1 - _descuento)::numeric,2)::numeric,2) FROM taxes.cuotasinmob WHERE idcatastro =  _idbien AND (cuotasinmob.date::date BETWEEN  _fechaPrimerAnticipo::date AND _fechaUltimoAnticipo::date);
		_montoDescuento = PUBLIC.TOMONEY(_montoTotalPuro - _montoTotalDescontado);

	ELSIF (_tipoimpuesto = 3) THEN	--Impuesto Automotor

		SELECT INTO _fechaPrimerAnticipo date
		FROM taxes.cuotasautomotor 
		WHERE idautomotor = _idbien 
		AND idvoucher = -1
		AND idpaymentplans = 0
		ORDER BY year, number LIMIT 1;

		SELECT INTO _fechaUltimoAnticipo date 
		FROM taxes.cuotasautomotor  
		WHERE date IN (
			SELECT date
			FROM taxes.cuotasautomotor
			WHERE idautomotor = _idbien 
			AND idvoucher = -1
			AND idpaymentplans = 0 
			ORDER BY year, number LIMIT _cantanticipos
		)
		AND idautomotor = _idbien
		ORDER BY date DESC
		LIMIT 1;

		IF (_fechaUltimoAnticipo IS NULL) THEN
			_fechaUltimoAnticipo = ''||now()::date;
		END IF;

		SELECT INTO _montoBasePuro ROUND(sum((100.0 - paidpercentage) * basicamount/100.0)::numeric,2) FROM taxes.cuotasautomotor WHERE idautomotor = _idbien AND (date BETWEEN _fechaPrimerAnticipo::date AND _fechaUltimoAnticipo::date);
		SELECT INTO _interesPuro ROUND(ROUND(sum((100.0 - paidpercentage) * accruedinterest/100.0)::numeric,2) * ROUND((1 - 0.0)::numeric,2)::numeric,2) FROM taxes.cuotasautomotor WHERE idautomotor =  _idbien AND (cuotasautomotor.date::date BETWEEN  _fechaPrimerAnticipo::date AND _fechaUltimoAnticipo::date);
		SELECT INTO _montoTotalPuro ROUND(sum((100.0 - paidpercentage) * totalamount/100.0)::numeric,2) FROM taxes.cuotasautomotor WHERE idautomotor = _idbien  AND (cuotasautomotor.date::date BETWEEN  _fechaPrimerAnticipo::date AND _fechaUltimoAnticipo::date);
		SELECT INTO _montoTotalDescontado ROUND(ROUND(sum((100.0 - paidpercentage)*totalamount/100.0)::numeric,2) * ROUND((1 - _descuento)::numeric,2)::numeric,2) FROM taxes.cuotasautomotor WHERE idautomotor =  _idbien AND (cuotasautomotor.date::date BETWEEN  _fechaPrimerAnticipo::date AND _fechaUltimoAnticipo::date);
		_montoDescuento = PUBLIC.TOMONEY(_montoTotalPuro - _montoTotalDescontado);
	END IF;
	
	query = '	SELECT 	* 
		FROM 	(SELECT 	'|| _montoBasePuro ||' AS  montoPuro,
				'|| _interesPuro ||' AS  interesPuro,
				'|| _montoTotalPuro ||' AS  totalPuro,
				'|| _montoTotalDescontado ||' AS  totaldescontado, 
				'|| _montoDescuento ||' AS  descuento
				) as foo'; 
	IF (query IS NULL) THEN
		query = '	SELECT 	* 
			FROM 	(SELECT 	0.00 AS  montoPuro,
					0.00 AS  interesPuro,
					0.00 AS  totalPuro,
					0.00 AS  totaldescontado, 
					0.00 AS  descuento
				) as foo';
	END IF;

	OPEN returnCursor FOR EXECUTE query;

	RETURN returnCursor;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION taxes.getmontosdeuda(_tipoimpuesto integer, _idbien integer, _cantanticipos integer, _descuento double precision) IS '2010-10-06: (admin)';
CREATE OR REPLACE FUNCTION resourcescontrol.rechazarordenesdeprovision(_ids character varying) RETURNS boolean AS $BODY$
DECLARE
	query character varying;
	rs RECORD;
BEGIN 

	/*Vuelvo a dejar el preventivo de la tabla proveedores en su monto original*/
	query := 'SELECT idpurchaseorder, idprovider, amount FROM resourcescontrol.purchaseorders WHERE idpurchaseorder IN ('||_ids||') AND idrequeststatus = 4';
	FOR rs IN EXECUTE query LOOP
		UPDATE org.companies SET predictedamount = (predictedamount - rs.amount) WHERE idcompany = rs.idprovider;
	END LOOP;
	/*Actualizo el estado de las órdenes de provisión 13 --> Rechazado*/
	query := 'UPDATE 	resourcescontrol.purchaseorders 
		SET 	idrequeststatus = 13 /* Rechazado */
		WHERE 	idpurchaseorder IN ('|| _ids ||')
		AND 	idrequeststatus = 4';

	
	EXECUTE query;

	/* Omitido por cuestiones de rendimiento
	PERFORM cashflow.repairbudgets();
	*/

	RETURN true::boolean;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION resourcescontrol.rechazarordenesdeprovision(_ids character varying) IS '2010-10-06: (admin)';
CREATE OR REPLACE FUNCTION cashflow.addbudgetmovementbyinvoice(_idvoucher integer, _idvoucherselected integer) RETURNS integer AS $BODY$
DECLARE 
		_voucher record;
		_voucherdetail record;
		id integer;
		idbudgetaux integer;
		idcostcentreaux integer;
		registro record;
		query character varying;
		idaccountaux integer;
		idbudgetmovementaux integer;
		idbudgetcostcentreaux integer;
		idaux integer;
		idpurchaseorderaux integer;
		purchaseorderdetailaux record;
		completa boolean;
	BEGIN 
		/* Recupero en el registro "_voucher" el encabezado de la Factura */  
		SELECT INTO _voucher 
				*, 
				(vouchertype_tabs.name ||' Nº ' || (CASE 
								WHEN (vouchertype_tabs.idvouchertype <> 2) 
									THEN vouchers.number::character varying 
									ELSE vouchers.nrofaCtura 
									END)
				) AS voucher, 
				companiesbyaccounts.idaccount AS idprovideraccount
		FROM 	cashflow.vouchers 
			INNER JOIN tabs.vouchertype_tabs ON vouchertype_tabs.idvouchertype = vouchers.idvouchertype
			INNER JOIN org.companiesbyaccounts ON companiesbyaccounts.idcompany = vouchers.identity
		WHERE idvoucher = _idvoucher;	
	
		FOR _voucherdetail IN SELECT * FROM cashflow.voucherdetail WHERE idvoucher = _voucher.idvoucher LOOP
			
			/* Obtengo el id de la Ord. Cpra. correspondiente */  
			SELECT 	INTO purchaseorderdetailaux purchaseorders.idpurchaseorder, purchaseorderdetail.idpurchaseorderdetail, purchaseorderdetail.idresource, 
					purchaseorderdetail.price,purchaseorderdetail.amount, purchaseorderdetail.idbrand,
					budgetcostscentres.idbudget, resources.idaccount
			FROM 	resourcescontrol.purchaseorderdetailxvoucherdetail 
				INNER JOIN resourcescontrol.purchaseorderdetail ON purchaseorderdetail.idpurchaseorderdetail = purchaseorderdetailxvoucherdetail.idpurchaseorderdetail
				INNER JOIN resourcescontrol.purchaseorders ON purchaseorders.idpurchaseorder = purchaseorderdetail.idpurchaseorder
				INNER JOIN cashflow.budgetcostscentres ON budgetcostscentres.idcostcentre = purchaseorders.idcostcentre
				INNER JOIN resourcescontrol.resources ON resources.idresource = purchaseorderdetail.idresource
			WHERE	idvoucherdetailref = (
					SELECT  idvoucherdetailref 
					FROM 	cashflow.voucherdetailxvoucherdetail 
					WHERE 	idvoucherdetail = _voucherdetail.idvoucherdetail);
			
			IF (purchaseorderdetailaux.idbudget > 0) THEN
		
				/* Actualizo el campo Invoiceamount del movimiento de la P.P. correspondiente*/
				SELECT INTO idbudgetmovementaux cashflow.addbudgetmovements(purchaseorderdetailaux.idbudget, purchaseorderdetailaux.idaccount, purchaseorderdetailaux.idpurchaseorder, 0, _voucherdetail.idvoucher, _voucherdetail.amount);
	
				/* Obtengo el id de la tabla budgetcostscentres (PP x CC) */
				SELECT INTO idbudgetcostcentreaux idbudgetcostcentre FROM cashflow.budgetcostscentres 
					WHERE idcostcentre = _voucherdetail.idcostcentre AND idbudget = purchaseorderdetailaux.idbudget;
		
				/* Sumar el Monto Gastado y restar el Monto Preventivo en la tabla Partida Presupuestaria por Centro de Costo y Tipo de Gasto (cashflow.BudgetCostCentreDetail) */
				PERFORM cashflow.setbudgetcostcentredetailbyinvoice(idbudgetcostcentreaux, purchaseorderdetailaux.idaccount, _voucherdetail.amount, purchaseorderdetailaux.idbudget, _voucherdetail.finalqty, purchaseorderdetailaux.price);
				
				/* Actualiza el Monto Facturado y el estado en el Detalle de la Orden de Compras correspondiente (campo realamount de la tabla resourcescontrol.purchaseorderdetail)*/
				PERFORM cashflow.setpurchaseorderdetailbyinvoice(purchaseorderdetailaux.idpurchaseorder, _voucherdetail.idresource, _voucherdetail.idbrand , _voucherdetail.amount);
	
				EXECUTE 'UPDATE 	org.companies
					SET	predictedamount = ( companies.predictedamount - '|| (_voucherdetail.finalqty * purchaseorderdetailaux.price) ||' )
						/* NO LO HAGO POR QUE LO TENGO QUE GLOBALIZAR DESDE EL VOUCHER */
						,amount = ( companies.amount + '|| _voucherdetail.amount ||' ) 
					FROM	resourcescontrol.purchaseorders
					WHERE 	companies.idcompany = purchaseorders.idprovider
					AND 	purchaseorders.idpurchaseorder = '|| purchaseorderdetailaux.idpurchaseorder;
	
				--Aqui cambio el estado de la O.C
				SELECT INTO completa( (SELECT COUNT(*) 
				FROM resourcescontrol.purchaseorderdetail
				WHERE idpurchaseorder = purchaseorderdetailaux.idpurchaseorder
				AND idrequeststatus = 14 ) 
				= 
				(SELECT COUNT(*) 
				FROM resourcescontrol.purchaseorderdetail
				WHERE idpurchaseorder = purchaseorderdetailaux.idpurchaseorder ));
				
				IF ( completa) THEN
					UPDATE resourcescontrol.purchaseorders 
					SET idrequeststatus = 14 
					WHERE idpurchaseorder = purchaseorderdetailaux.idpurchaseorder;
				ELSE 
					UPDATE resourcescontrol.purchaseorders 	
					SET idrequeststatus = 16 
					WHERE idpurchaseorder = purchaseorderdetailaux.idpurchaseorder;
				END IF;
			END IF;	
	
		END LOOP;
		
		PERFORM cashflow.setVoucherByTotalAmount(_voucher.idvoucher);
		PERFORM resourcescontrol.setResourceBrandByPrice(_voucherdetail.idresource,_voucherdetail.idbrand,_voucherdetail.price);
		PERFORM accounting.addBookKeepingEntryFromInvoice(_voucher.idvoucher, _voucher.voucher, _voucher.idprovideraccount);
		PERFORM cashflow.setvouchersbilled('(' || _idvoucherselected || ')');
		PERFORM cashflow.repairbudgets();
	
		RETURN 1;
	
	END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION cashflow.addbudgetmovementbyinvoice(_idvoucher integer, _idvoucherselected integer) IS '2010-10-05: (barbozam)';
CREATE OR REPLACE FUNCTION resourcescontrol.getallproviderswithpurchaseorders(_filter character varying, _idbudget integer) RETURNS refcursor AS $BODY$
DECLARE
	returnCursor REFCURSOR;
	query TEXT;
	filter1 TEXT := '';
BEGIN			

	IF (_idbudget <> -1) THEN
		filter1 := ' AND budgetcostscentres.idbudget = '|| _idbudget;
	END IF;

	query := 'SELECT  DISTINCT
			companies.idcompany,
			companies.name,
			0 AS valueref
		 FROM 
			org.companies
			INNER JOIN resourcescontrol.purchaseorders ON purchaseorders.idprovider = companies.idcompany
				AND 	(purchaseorders.idrequeststatus IN (1,2,3,4,5,6,9,16))
			INNER JOIN cashflow.budgetcostscentres ON budgetcostscentres.idcostcentre = purchaseorders.idcostcentre
				'|| filter1 ||'
		 WHERE 	
			LOWER (companies.name) LIKE LOWER (''%'|| _filter ||'%'')
		AND 	companies.idcompany > 0 
		AND 	companies.estado <> ''*''
		ORDER BY companies.name';
	
	OPEN returnCursor FOR EXECUTE query;
	RETURN returnCursor;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION resourcescontrol.getallproviderswithpurchaseorders(_filter character varying, _idbudget integer) IS '2010-10-05: (barbozam)';
CREATE OR REPLACE FUNCTION resourcescontrol.getallpurchaseordersbyfilter(_purchaseordernumber character varying, _idprovider integer, _idbudget integer) RETURNS refcursor AS $BODY$
DECLARE
	returnCursor REFCURSOR;
	query TEXT;
	Filter1 character varying := '';
	Filter2 character varying := '';

BEGIN	
	IF (_purchaseordernumber <> '') THEN 
		Filter1 = 'AND
				LOWER (purchaseorders.number) LIKE LOWER (''%'|| _purchaseordernumber ||'%'')';
	END IF;

	IF (_idbudget <> -1) THEN 
		Filter2 = ' AND budgetcostscentres.idbudget = '|| _idbudget;
	END IF;

	query := 'SELECT 
			purchaseorders.idpurchaseorder,
			'' Nº ''|| purchaseorders.number AS name,
			(purchaseorders.idcostcentre ||''#''|| budgetcostscentres.idbudget) as valueref
		FROM 
			resourcescontrol.purchaseorders
			INNER JOIN cashflow.budgetcostscentres ON budgetcostscentres.idcostcentre = purchaseorders.idcostcentre
				'|| Filter2 ||'
		 WHERE 	
			purchaseorders.estado <> ''*''
			'|| Filter1 ||'
		AND
			purchaseorders.idprovider = '|| _idprovider ||'
		AND
			(purchaseorders.idrequeststatus IN (1,2,3,4,5,6,9,16))
		ORDER BY 
			purchaseorders.number';

--	Raise exception 'query %', query;
        OPEN returnCursor FOR EXECUTE query;
	RETURN returnCursor;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION resourcescontrol.getallpurchaseordersbyfilter(_purchaseordernumber character varying, _idprovider integer, _idbudget integer) IS '2010-10-05: (barbozam)';
CREATE OR REPLACE FUNCTION cashflow.repairbudgets() RETURNS integer AS $BODY$
DECLARE 
	rs record;
	reg1 record;
	reg2 record;
	result integer;
	_amount double precision := 0;
BEGIN 
	/*Primero restauro las tablas Budgets, BudgetsCostCentres y BudgetCostsCentresDetail */
	UPDATE	cashflow.budgets 
	SET  	availableamount = initialamount + modifiedamount,
		spentamount = 0, spentamountp = 0,
		accountsamount = 0, predictedamount = 0;

	UPDATE	cashflow.budgetcostscentres
	SET  	availableamount = initialamount + modifiedamount,
		spentamount = 0, spentamountp = 0,
		predictedamount = 0;

	UPDATE	cashflow.budgetcostscentredetail
	SET  	availableamount = initialamount + modifiedamount,
		spentamount = 0, spentamountp = 0,
		predictedamount = 0;

	/* Actualizo los montos preventivos para cada partida presupuestaria */
	
	FOR rs IN SELECT
			budgetcostscentres.idbudgetcostcentre,
			--cambio
			SUM(purchaseorderdetail.price*(purchaseorderdetail.receivedqty)) + SUM(purchaseorderdetail.price*(purchaseorderdetail.approvedqty - purchaseorderdetail.receivedqty)) AS predictedamount,
			resources.idaccount
		FROM 
			resourcescontrol.purchaseorders
		INNER JOIN resourcescontrol.purchaseorderdetail ON purchaseorderdetail.idpurchaseorder = purchaseorders.idpurchaseorder
		INNER JOIN resourcescontrol.resources ON purchaseorderdetail.idresource = resources.idresource
		INNER JOIN cashflow.budgetcostscentres ON budgetcostscentres.idcostcentre = purchaseorders.idcostcentre
		WHERE
			purchaseorders.estado <> '*'
			AND approvedqty >= receivedqty
			AND purchaseorders.idrequeststatus IN (1,2,3,4,5,6,7,9,11,16)
		GROUP BY
			budgetcostscentres.idbudgetcostcentre,
			resources.idaccount LOOP
			PERFORM cashflow.setbudgetcostcentrepredictedamount(rs.idbudgetcostcentre, rs.idaccount, rs.predictedamount);
	END LOOP;

	FOR rs IN SELECT
			budgetcostscentres.idbudgetcostcentre,
			--cambio
			SUM(purchaseorderdetail.price*(purchaseorderdetail.receivedqty)) /*+ SUM(purchaseorderdetail.price*(purchaseorderdetail.approvedqty - purchaseorderdetail.receivedqty))*/ AS predictedamount,
			resources.idaccount
		FROM 
			resourcescontrol.purchaseorders
		INNER JOIN resourcescontrol.purchaseorderdetail ON purchaseorderdetail.idpurchaseorder = purchaseorders.idpurchaseorder
		INNER JOIN resourcescontrol.resources ON purchaseorderdetail.idresource = resources.idresource
		INNER JOIN cashflow.budgetcostscentres ON budgetcostscentres.idcostcentre = purchaseorders.idcostcentre
		WHERE
			purchaseorders.estado <> '*'
			AND approvedqty >= receivedqty
			AND purchaseorders.idrequeststatus IN (16)
		GROUP BY
			budgetcostscentres.idbudgetcostcentre,
			resources.idaccount LOOP
			PERFORM cashflow.setbudgetcostcentrepredictedamount(rs.idbudgetcostcentre, rs.idaccount, -1*rs.predictedamount);
	END LOOP;
	/*Luego recorro la tabla BookKeepingEntryDetail para recalcular las P.P. */
	FOR rs IN SELECT 	bookkeepingentrydetail.idbookkeepingentrydetail, bookkeepingentrydetail.idbookkeepingentry,
			bookkeepingentrydetail.idaccount, bookkeepingentrydetail.debitamount,
			bookkeepingentrydetail.creditamount, bookkeepingentrydetail.idbudget, bookkeepingentrydetail.idcostcentre,
			CASE WHEN bookkeepingentries.number > 0
				THEN 	-1
				ELSE 	1
			END AS sign,
			bookkeepingentries.number,
                        budgetcostscentres.idbudgetcostcentre
		FROM 	accounting.bookkeepingentrydetail
			LEFT JOIN accounting.bookkeepingentries ON bookkeepingentries.idbookkeepingentry = bookkeepingentrydetail.idbookkeepingentry
			INNER JOIN cashflow.budgetcostscentres 
				ON budgetcostscentres.idbudget = bookkeepingentrydetail.idbudget
				AND budgetcostscentres.idcostcentre = bookkeepingentrydetail.idcostcentre
		WHERE 	bookkeepingentrydetail.estado <> '*' LOOP
		IF (rs.debitamount > 0) THEN 
			_amount := (rs.debitamount * rs.sign) * (-1);
		ELSE 
			_amount := (rs.creditamount * rs.sign);
		END IF;
		
		PERFORM cashflow.setbudgetcostcentredetailbycashmovement(rs.idbudgetcostcentre, rs.idaccount, _amount);
	END LOOP;

	UPDATE	cashflow.budgets 
	SET  	availableamount = PUBLIC.TOMONEY(availableamount),
		spentamount = PUBLIC.TOMONEY(spentamount),
		accountsamount = PUBLIC.TOMONEY(accountsamount), predictedamount = PUBLIC.TOMONEY(predictedamount);

	UPDATE	cashflow.budgets 
	SET  	spentamountp = PUBLIC.TOMONEY((spentamount / availableamount) *100)
	WHERE 	availableamount != 0;

	UPDATE	cashflow.budgetcostscentres
	SET  	availableamount = PUBLIC.TOMONEY(availableamount),
		spentamount = PUBLIC.TOMONEY(spentamount),
		predictedamount = PUBLIC.TOMONEY(predictedamount);

	UPDATE	cashflow.budgetcostscentres
	SET  	spentamountp = PUBLIC.TOMONEY((spentamount / availableamount) *100)
	WHERE 	availableamount != 0;

	UPDATE	cashflow.budgetcostscentredetail
	SET  	availableamount = PUBLIC.TOMONEY(availableamount),
		spentamount = PUBLIC.TOMONEY(spentamount), 
		predictedamount = PUBLIC.TOMONEY(predictedamount);

	UPDATE	cashflow.budgetcostscentredetail
	SET  	spentamountp = PUBLIC.TOMONEY((spentamount / availableamount) *100)
	WHERE 	availableamount != 0;

	UPDATE	cashflow.budgetcostscentredetail
	SET  	availableamount = PUBLIC.TOMONEY(availableamount),
		spentamount = PUBLIC.TOMONEY(spentamount), 
		predictedamount = PUBLIC.TOMONEY(predictedamount);

	UPDATE	cashflow.budgetcostscentredetail
	SET  	spentamountp = PUBLIC.TOMONEY((spentamount / availableamount) *100)
	WHERE 	availableamount != 0;

	UPDATE	cashflow.budgetcostscentres
	SET	spentamount = PUBLIC.TOMONEY(budgetcostscentredetail.spentamount),
		availableamount = PUBLIC.TOMONEY(budgetcostscentres.initialamount + budgetcostscentres.modifiedamount - budgetcostscentredetail.spentamount)
					FROM 	cashflow.budgetcostscentredetail, accounting.accounts
					WHERE 	budgetcostscentredetail.idaccount = accounts.idaccount
					AND 	accounts.idaccounttype = 5000
					AND	accounts.idparent = 0;
	FOR rs IN SELECT budgets.idbudget, PUBLIC.TOMONEY(SUM(budgetcostscentres.spentamount)) AS spentamount, PUBLIC.TOMONEY(SUM(budgets.initialamount) + SUM(budgets.modifiedamount) - SUM(budgetcostscentres.spentamount)) AS availableamount
	FROM 	cashflow.budgetcostscentres, cashflow.budgets WHERE budgetcostscentres.idbudget = budgets.idbudget GROUP BY budgets.idbudget LOOP

		UPDATE	cashflow.budgets
		SET	spentamount = rs.spentamount,
			availableamount = rs.availableamount
		WHERE 	budgets.idbudget = rs.idbudget;
	END LOOP;
	UPDATE	cashflow.budgets 
	SET	spentamountp = PUBLIC.TOMONEY((spentamount / initialamount)*100)
	WHERE	initialamount != 0;

	UPDATE	cashflow.budgetcostscentres
	SET	spentamountp = PUBLIC.TOMONEY((spentamount / initialamount)*100)
	WHERE	initialamount != 0;

	UPDATE	cashflow.budgetcostscentredetail
	SET  	spentamountp = PUBLIC.TOMONEY((spentamount / initialamount)*100)
	WHERE	initialamount != 0;

	RETURN 0;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION cashflow.repairbudgets() IS '2010-10-05: (barbozam)';
CREATE OR REPLACE FUNCTION cashflow.setpurchaseorderdetailbyinvoice(_idpurchaseorder integer, _idresource integer, _idbrand integer, _amount double precision) RETURNS integer AS $BODY$
DECLARE 
	query character varying;
	idpurchaseorderdetailaux integer;
BEGIN 

	SELECT 	INTO idpurchaseorderdetailaux idpurchaseorderdetail
	FROM 	resourcescontrol.purchaseorderdetail
	WHERE	idpurchaseorder = _idpurchaseorder
	AND 	idresource = _idresource
	AND 	idbrand = _idbrand;
	
	IF ( idpurchaseorderdetailaux is not null ) THEN 
		EXECUTE  'UPDATE 	resourcescontrol.purchaseorderdetail
			SET	realamount = ( realamount + '|| _amount ||' )
			WHERE 	idpurchaseorderdetail = '|| idpurchaseorderdetailaux ;
		EXECUTE  'UPDATE 	resourcescontrol.purchaseorders
			SET	realamount = ( realamount + '|| _amount ||' )
			WHERE 	idpurchaseorder = '|| _idpurchaseorder ;

		IF (	SELECT (approvedqty = receivedqty) 
			FROM resourcescontrol.purchaseorderdetail
			WHERE idpurchaseorderdetail = idpurchaseorderdetailaux) THEN

			UPDATE resourcescontrol.purchaseorderdetail 
			SET idrequeststatus = 14 /*Facturado*/
			WHERE idpurchaseorderdetail = idpurchaseorderdetailaux;
		ELSE

			UPDATE resourcescontrol.purchaseorderdetail 
			SET idrequeststatus = 16 /*Facturado Parcial*/
			WHERE idpurchaseorderdetail = idpurchaseorderdetailaux;
		END IF;

	ELSE 
		/** ACA VA AVISO DE QUE NO SE ENCONTRO EL ID DEL DETALLE DE LA ORDEN DE COMPRAS CORRESPONDIENTE */
		RAISE EXCEPTION 'ERROR: NO SE ENCONTRO EL ID DEL DETALLE DE LA ORDEN DE COMPRAS CORRESPONDIENTE';
	END IF;

	RETURN 0;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION cashflow.setpurchaseorderdetailbyinvoice(_idpurchaseorder integer, _idresource integer, _idbrand integer, _amount double precision) IS '2010-10-05: (barbozam)';
CREATE OR REPLACE FUNCTION accounting.getallbookkeepingentriesavailableamount(_startdate character varying, _enddate character varying, _limit integer, _offset integer) RETURNS refcursor AS $BODY$
DECLARE
	returnCursor REFCURSOR;
	query TEXT;
	filter TEXT:='';
BEGIN
	IF (_startdate != '' AND _enddate != '') THEN
		filter := filter || 'AND bookkeepingentrydetail.idbookkeepingentry IN (SELECT idbookkeepingentry FROM accounting.bookkeepingentries WHERE date BETWEEN '''|| _startdate ||''' AND '''|| _enddate ||''')';
	END IF;
	
	query := 'SELECT
			/*bookkeepingentries.number as number,
			bookkeepingentries.date as date,*/
			bookkeepingentrydetail.idaccount,
			accounts.code,
			accounts.name,
			/*CASE WHEN bookkeepingentrydetail.idaccount <> -1 THEN*/
				(SELECT ''(''|| code ||'') - ''|| name FROM accounting.accounts WHERE accounts.idaccount = bookkeepingentrydetail.idaccount)
			/*ELSE
				(SELECT vouchertype_tabs.name ||'' - Nº ''|| vouchers.number FROM cashflow.vouchers, tabs.vouchertype_tabs
				WHERE vouchers.idvoucher = bookkeepingentrydetail.idvoucher AND vouchertype_tabs.idvouchertype = vouchers.idvouchertype)
			END*/ AS concept,
			PUBLIC.TOMONEY(SUM(bookkeepingentrydetail.debitamount)) AS debitamount,
			PUBLIC.TOMONEY(SUM(bookkeepingentrydetail.creditamount)) AS creditamount,
			CASE WHEN SUM(bookkeepingentrydetail.debitamount) > SUM(bookkeepingentrydetail.creditamount) THEN
				PUBLIC.TOMONEY(SUM(bookkeepingentrydetail.debitamount) - SUM(bookkeepingentrydetail.creditamount)) ELSE 0 
			END AS totaldebitamount,
			CASE WHEN SUM(bookkeepingentrydetail.debitamount) < SUM(bookkeepingentrydetail.creditamount) THEN
				PUBLIC.TOMONEY(SUM(bookkeepingentrydetail.creditamount) - SUM(bookkeepingentrydetail.debitamount)) ELSE 0
			END AS totalcreditamount
		FROM 
			accounting.bookkeepingentrydetail
			INNER JOIN accounting.accounts ON accounts.idaccount = bookkeepingentrydetail.idaccount
			INNER JOIN accounting.bookkeepingentries ON bookkeepingentries.idbookkeepingentry = bookkeepingentrydetail.idbookkeepingentry
		WHERE
			bookkeepingentrydetail.estado<>''*'' '|| filter ||'	
		GROUP BY
			bookkeepingentrydetail.idaccount, accounts.code, accounts.name
		ORDER BY
			code';

	OPEN returnCursor FOR EXECUTE query;
	RETURN returnCursor;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION accounting.getallbookkeepingentriesavailableamount(_startdate character varying, _enddate character varying, _limit integer, _offset integer) IS '2010-10-04: (santiago)';
CREATE OR REPLACE FUNCTION taxes.addcadastral(_dpto character varying, _departamento character varying, _localidad character varying, _tecloc integer, _catastro integer, _domcon character varying, _domtidoc character varying, _domnudoc character varying, _domape character varying, _domfis character varying, _dompor character varying, _tecbavig integer, _vigencia character varying, _plano integer, _tecfeasg character varying, _tecsec character varying, _tecman character varying, _tecmanl character varying, _tecpar character varying, _tecparl character varying, _tecunif character varying, _tecurru integer, _tecorg1 integer, _tecorg2 integer, _tecderd integer, _tecderh integer, _tecobs character varying, _tecsuurb double precision, _terbe character varying, _terreno character varying, _terval character varying, _tervmej character varying, _tervcom character varying, _valfis character varying, _resol character varying, _resolfe character varying, _dcalle character varying, _dnumro character varying, _ddesbarrio character varying, _urbano character varying, _apoderadoname character varying, _apoderadolastname character varying, _apoderadotipodoc integer, _apoderadodoc character varying, _idcategoria integer, _metrosdefrente double precision, _esesquina boolean) RETURNS integer AS $BODY$
DECLARE
	tecfeasg character varying;
	qty integer;
	_idcatastro integer;
	_idparcela integer;
BEGIN 

	IF (_tecfeasg = '') THEN
		tecfeasg := 'null';
	ELSE
		tecfeasg := _tecfeasg;
	END IF;
	
	SELECT INTO qty count(*) FROM taxes.padroncatastral WHERE catastro = _catastro;

	IF (qty = 0) THEN 
		EXECUTE 'INSERT INTO taxes.padroncatastral (
				dpto,
				departamento,
				localidad,
				tecloc,
				catastro,
				domcon,
				domtidoc,
				domnudoc,
				domape,
				domfis,
				dompor,
				tecbavig,
				vigencia,
				plano,
				tecfeasg,
				tecsec,
				tecman,
				tecmanl,
				tecpar,
				tecparl,
				tecunif,
				tecurru,
				tecorg1,
				tecorg2,
				tecderd,
				tecderh,
				tecobs,
				tecsuurb,
				terbe,
				terreno,
				terval,
				tervmej,
				tervcom,
				valfis,
				resol,
				resolfe,
				dcalle,
				dnumro,
				ddesbarrio,
				urbano,
				estado
				) VALUES( 
				'|| _dpto ||',
				'''|| _departamento ||''',
				'''|| _localidad ||''',
				'|| _tecloc ||',
				'|| _catastro ||',
				1,
				--'''|| _domcon ||''',
				'''|| _domtidoc ||''',
				'''|| _domnudoc ||''',
				upper('''|| _domape ||'''),
				upper(substr('''|| _domfis ||''',1,1)),
				'''|| _dompor ||''',
				'|| _tecbavig ||',
				'''|| _vigencia ||''',
				'|| _plano ||',
				'''|| tecfeasg ||''',
				upper(substr('''|| _tecsec ||''',1,1)),
				upper(substr('''|| _tecman ||''',1,5)),
				upper(substr('''|| _tecmanl ||''',1,1)),
				upper(substr('''|| _tecpar ||''',1,5)),
				upper(substr('''|| _tecparl ||''',1,1)),
				'''|| _tecunif ||''',
				'|| _tecurru ||',
				'|| _tecorg1 ||',
				'|| _tecorg2 ||',
				'|| _tecderd ||',
				'|| _tecderh ||',
				'''|| _tecobs ||''',
				'|| _tecsuurb ||',
				'''|| _terbe ||''',
				'''|| _terreno ||''',
				'''|| _terval ||''',
				'''|| _tervmej ||''',
				'''|| _tervcom ||''',
				'''|| _valfis ||''',
				'''|| _resol ||''',
				'''|| _resolfe ||''',
				'''|| _dcalle ||''',
				'''|| _dnumro ||''',
				'''|| _ddesbarrio ||''',
				'''|| _urbano ||''',
				'''')';

		SELECT INTO _idcatastro taxes.generarpadroncatastral(_catastro);

		PERFORM taxes.addvalorfiscalxcatastros(_idcatastro, _catastro, _idcategoria, _metrosdefrente, _esesquina);

		--PERFORM taxes.generarcuotatgs(_idcatastro);
		PERFORM taxes.generarcuotatgsmf(_idcatastro);

		PERFORM taxes.generarcuotainmob(_idcatastro);

		PERFORM taxes.setApoderado(_idcatastro, _apoderadoname, _apoderadolastname, _apoderadotipodoc, _apoderadodoc);

		/* CASO: VALOR FISCAL */
		--PERFORM taxes.setlasttgsinmobfees(_idcatastro);

		/* CASO: METROS DE FRENTE */
		PERFORM taxes.setlasttgsmfinmobfees(_idcatastro);

		--Actualizamos la categoria en el gis

		UPDATE	gis_mosconi.parcelas_vinculadas 
		SET	categoria = valorfiscalxcatastros.idcategoria
		FROM	taxes.valorfiscalxcatastros, taxes.catastros, gis_mosconi.catastros
		WHERE	valorfiscalxcatastros.idcatastro = taxes.catastros.idcatastro 
		AND	taxes.catastros.numero = gis_mosconi.catastros.catastro
		AND	gis_mosconi.catastros.idparcela = parcelas_vinculadas.idparcela
		AND	taxes.catastros.idcatastro = _idcatastro; 

	ELSE
		RAISE EXCEPTION 'El catastro ya esta registrado';
	END IF;

	RETURN qty;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION taxes.addcadastral(_dpto character varying, _departamento character varying, _localidad character varying, _tecloc integer, _catastro integer, _domcon character varying, _domtidoc character varying, _domnudoc character varying, _domape character varying, _domfis character varying, _dompor character varying, _tecbavig integer, _vigencia character varying, _plano integer, _tecfeasg character varying, _tecsec character varying, _tecman character varying, _tecmanl character varying, _tecpar character varying, _tecparl character varying, _tecunif character varying, _tecurru integer, _tecorg1 integer, _tecorg2 integer, _tecderd integer, _tecderh integer, _tecobs character varying, _tecsuurb double precision, _terbe character varying, _terreno character varying, _terval character varying, _tervmej character varying, _tervcom character varying, _valfis character varying, _resol character varying, _resolfe character varying, _dcalle character varying, _dnumro character varying, _ddesbarrio character varying, _urbano character varying, _apoderadoname character varying, _apoderadolastname character varying, _apoderadotipodoc integer, _apoderadodoc character varying, _idcategoria integer, _metrosdefrente double precision, _esesquina boolean) IS '2010-10-04: (barbozam)';
CREATE OR REPLACE FUNCTION taxes.addowner(_dpto character varying, _departamento character varying, _localidad character varying, _tecloc integer, _catastro integer, _domcon character varying, _domtidoc character varying, _domnudoc character varying, _domape character varying, _domfis character varying, _dompor character varying, _tecbavig integer, _vigencia character varying, _plano integer, _tecfeasg character varying, _tecsec character varying, _tecman character varying, _tecmanl character varying, _tecpar character varying, _tecparl character varying, _tecunif character varying, _tecurru integer, _tecorg1 integer, _tecorg2 integer, _tecderd integer, _tecderh integer, _tecobs character varying, _tecsuurb double precision, _terbe character varying, _terreno character varying, _terval character varying, _tervmej character varying, _tervcom character varying, _valfis character varying, _resol character varying, _resolfe character varying, _dcalle character varying, _dnumro character varying, _ddesbarrio character varying, _urbano character varying) RETURNS integer AS $BODY$
DECLARE
	tecfeasg character varying;
	_esNumero boolean = false;
	_regDomCon RECORD;
	_domconmax integer;
BEGIN 

	IF (_tecfeasg = '') THEN
		tecfeasg := 'null';
	ELSE
		tecfeasg := _tecfeasg;
	END IF;

	_esNumero = false;

	FOR _regDomCon IN SELECT domcon FROM taxes.padroncatastral WHERE catastro =  _catastro AND estado != '*' LOOP
		IF (_regDomCon.domcon != '') THEN
			_esNumero = true;
		END IF;
		
	END LOOP;

	IF (_esNumero) THEN
		SELECT INTO _domconmax MAX(PUBLIC.ISNULL(domcon::integer,0)) + 1 FROM taxes.padroncatastral WHERE catastro =  _catastro AND estado != '*';
	ELSE
		_domconmax = 1;
	END IF;

	EXECUTE 'INSERT INTO taxes.padroncatastral (
			dpto,
			departamento,
			localidad,
			tecloc,
			catastro,
			domcon,
			domtidoc,
			domnudoc,
			domape,
			domfis,
			dompor,
			tecbavig,
			vigencia,
			plano,
			tecfeasg,
			tecsec,
			tecman,
			tecmanl,
			tecpar,
			tecparl,
			tecunif,
			tecurru,
			tecorg1,
			tecorg2,
			tecderd,
			tecderh,
			tecobs,
			tecsuurb,
			terbe,
			terreno,
			terval,
			tervmej,
			tervcom,
			valfis,
			resol,
			resolfe,
			dcalle,
			dnumro,
			ddesbarrio,
			urbano,
			estado
			) VALUES( 
			'|| _dpto ||',
			'''|| _departamento ||''',
			'''|| _localidad ||''',
			'|| _tecloc ||',
			'|| _catastro ||',
			'''|| _domconmax ||''',
			'''|| _domtidoc ||''',
			'''|| _domnudoc ||''',
			'''|| _domape ||''',
			'''|| _domfis ||''',
			'''|| _dompor ||''',
			'|| _tecbavig ||',
			'''|| _vigencia ||''',
			'|| _plano ||',
			'''|| tecfeasg ||''',
			'''|| _tecsec ||''',
			'''|| _tecman ||''',
			'''|| _tecmanl ||''',
			'''|| _tecpar ||''',
			'''|| _tecparl ||''',
			'''|| _tecunif ||''',
			'|| _tecurru ||',
			'|| _tecorg1 ||',
			'|| _tecorg2 ||',
			'|| _tecderd ||',
			'|| _tecderh ||',
			'''|| _tecobs ||''',
			'|| _tecsuurb ||',
			'''|| _terbe ||''',
			'''|| _terreno ||''',
			'''|| _terval ||''',
			'''|| _tervmej ||''',
			'''|| _tervcom ||''',
			'''|| _valfis ||''',
			'''|| _resol ||''',
			'''|| _resolfe ||''',
			'''|| _dcalle ||''',
			'''|| _dnumro ||''',
			'''|| _ddesbarrio ||''',
			'''|| _urbano ||''',
			'''')'; 
	
	RETURN _catastro;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION taxes.addowner(_dpto character varying, _departamento character varying, _localidad character varying, _tecloc integer, _catastro integer, _domcon character varying, _domtidoc character varying, _domnudoc character varying, _domape character varying, _domfis character varying, _dompor character varying, _tecbavig integer, _vigencia character varying, _plano integer, _tecfeasg character varying, _tecsec character varying, _tecman character varying, _tecmanl character varying, _tecpar character varying, _tecparl character varying, _tecunif character varying, _tecurru integer, _tecorg1 integer, _tecorg2 integer, _tecderd integer, _tecderh integer, _tecobs character varying, _tecsuurb double precision, _terbe character varying, _terreno character varying, _terval character varying, _tervmej character varying, _tervcom character varying, _valfis character varying, _resol character varying, _resolfe character varying, _dcalle character varying, _dnumro character varying, _ddesbarrio character varying, _urbano character varying) IS '2010-10-04: (barbozam)';
CREATE OR REPLACE FUNCTION taxes.desvinculartitulares(_domape character varying[], _idcatastro integer, _vectorsize integer) RETURNS boolean AS $BODY$
DECLARE
	query character varying;
	i integer;
	_catastro integer := 0;
BEGIN
	SELECT INTO _catastro numero FROM taxes.catastros WHERE idcatastro = _idcatastro;
	
	FOR i IN 1.._vectorsize LOOP
		
		query:=  'UPDATE taxes.padroncatastral
			SET estado = ''*'' 
			WHERE oid IN (	SELECT  oid 
					FROM taxes.padroncatastral 
					WHERE catastro = '|| _catastro ||'
					AND UPPER(domape) = UPPER('''|| _domape[i]||''')
					AND estado != ''*''
					ORDER BY oid DESC LIMIT 1)'; 
		
		EXECUTE query;
	END LOOP;
	RETURN true::boolean;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION taxes.desvinculartitulares(_domape character varying[], _idcatastro integer, _vectorsize integer) IS '2010-10-04: (barbozam)';
CREATE OR REPLACE FUNCTION taxes.getallpagosbybien(_numerobien character varying, _escatastro boolean, _limit integer, _offset integer) RETURNS refcursor AS $BODY$
DECLARE
	returnCursor REFCURSOR; 
	queryTGS character varying:= '';      
	queryInmob character varying:= '';      
	queryAutomotores character varying:= '';      
	queryPlanesDePago character varying:= '';      
	query character varying:= '';      
	_filter character varying:= '';      
BEGIN
	queryTGS = '(SELECT 	idboletatgs as idboleta,
			1 as idtipoimpuesto,
			boletastgs.fechapago::date as fechadepago,
			(''BOLETA T.G.S ''|| anio ||''-''|| to_char(ABS(boletastgs.numero),''FM000000''))::text AS nro,
			''Anticipos Regulares (T.G.S)'' as tipo,
			concepto as concepto,
			boletastgs.importe as importe,
			boletastgs.recargo as recargo,
			(boletastgs.deducciones + boletastgs.dtopagoanual) as descuento,
			boletastgs.total as total

		FROM	taxes.boletastgs
		WHERE 	catastro = '||_numerobien ||'
		AND 	estado <> ''*''
		ORDER BY	idboletatgs)';
      
	queryInmob = '(SELECT idboletainmob as idboleta,
			 2 as idtipoimpuesto,
			 boletasinmob.fechapago::date as fechadepago,
			 (''BOLETA Inmob ''|| anio ||''-''|| to_char(ABS(boletasinmob.numero),''FM000000''))::text AS nro,
			 ''Anticipos Regulares (Inmobiliario)'' as tipo,
			 concepto as concepto,
			 boletasinmob.importe as importe,
			 boletasinmob.recargo as recargo,
			 (boletasinmob.deducciones + boletasinmob.dtopagoanual) as descuento,
			 boletasinmob.total as total

		FROM	taxes.boletasinmob
		WHERE 	catastro = '||_numerobien ||'
		AND 	estado <> ''*''
		ORDER BY	idboletainmob)';

	queryAutomotores = '(SELECT 	idboletaautomotor as idboleta,
				3 as idtipoimpuesto,
				boletasautomotor.fechapago::date as fechadepago,
				(''BOLETA Automotor ''|| anio ||''-''|| to_char(ABS(boletasautomotor.numero),''FM000000''))::text AS nro,
				''Anticipos Regulares (Automotor)'' as tipo,
				concepto as concepto,
				boletasautomotor.importe as importe,
				boletasautomotor.recargo as recargo,
				(boletasautomotor.deducciones + boletasautomotor.dtopagoanual) as descuento,
				boletasautomotor.total as total
			FROM	taxes.boletasautomotor
			WHERE 	UPPER(dominio) = UPPER('''||_numerobien ||''')
			AND 	estado <> ''*''
			ORDER BY	idboletaautomotor)';

	IF (_escatastro) THEN
		_filter = 'catastro = '||_numerobien;
		query := query || queryTGS || ' UNION ' || queryInmob;
	ELSE 
		_filter = 'UPPER(dominio) = UPPER('''||_numerobien||''')';
		query := queryAutomotores;
	END IF;

	queryPlanesDePago = '(SELECT 	idboletaplandepago as idboleta,
				0 as idtipoimpuesto,
				boletasplanesdepago.fechapago::date as fechadepago,
				(''BOLETA Plan de Pago ''|| anio ||''-''|| to_char(ABS(boletasplanesdepago.numero),''FM000000''))::text AS nro,
				''Plan de Pago ('' || (SELECT nombre FROM taxes.tiposimpuesto WHERE idtipoimpuesto = boletasplanesdepago.idtipoimpuesto)|| '')''as tipo,
				(pagadas || '' - ''||pagoysaldo) as concepto,
				boletasplanesdepago.importe as importe,
				boletasplanesdepago.recargo as recargo,
				boletasplanesdepago.deducciones as descuento,
				boletasplanesdepago.total as total

			FROM	taxes.boletasplanesdepago
			WHERE 	'||_filter||'
			AND 	estado <> ''*''
			ORDER BY	idboletaplandepago)';
	
	query :=  query || ' UNION ' || queryPlanesDePago;
	query := 'SELECT * FROM ('|| query ||') AS foo
		ORDER BY fechadepago DESC';
	If (_limit != 0) THEN
		query := query || ' LIMIT '|| _limit ||' OFFSET '|| _offset;
	END IF;

        OPEN returnCursor FOR EXECUTE query;
        RETURN returnCursor;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION taxes.getallpagosbybien(_numerobien character varying, _escatastro boolean, _limit integer, _offset integer) IS '2010-09-29: (barbozam)';
CREATE OR REPLACE FUNCTION taxes.xmlgetallpagosbybien(_numerobien character varying, _escatastro boolean) RETURNS refcursor AS $BODY$
DECLARE
	returnCursor REFCURSOR; 
	queryTGS character varying:= '';      
	queryInmob character varying:= '';      
	queryAutomotores character varying:= '';      
	queryPlanesDePago character varying:= '';      
	query character varying:= '';      
	_filter character varying:= '';      
BEGIN
	queryTGS = '(SELECT 	idboletatgs as idboleta,
			1 as idtipoimpuesto,
			boletastgs.fechapago as fechadepago,
			(''BOLETA T.G.S ''|| anio ||''-''|| to_char(ABS(boletastgs.numero),''FM000000''))::text AS nro,
			''Anticipos Regulares (T.G.S)'' as tipo,
			concepto as concepto,
			boletastgs.importe as importe,
			boletastgs.recargo as recargo,
			(boletastgs.deducciones + boletastgs.dtopagoanual) as descuento,
			boletastgs.total as total

		FROM	taxes.boletastgs
		WHERE 	catastro = '||_numerobien ||'
		AND 	estado <> ''*''
		ORDER BY	idboletatgs)';
      
	queryInmob = '(SELECT idboletainmob as idboleta,
			 2 as idtipoimpuesto,
			 boletasinmob.fechapago as fechadepago,
			 (''BOLETA Inmob ''|| anio ||''-''|| to_char(ABS(boletasinmob.numero),''FM000000''))::text AS nro,
			 ''Anticipos Regulares (Inmobiliario)'' as tipo,
			 concepto as concepto,
			 boletasinmob.importe as importe,
			 boletasinmob.recargo as recargo,
			 (boletasinmob.deducciones + boletasinmob.dtopagoanual) as descuento,
			 boletasinmob.total as total

		FROM	taxes.boletasinmob
		WHERE 	catastro = '||_numerobien ||'
		AND 	estado <> ''*''
		ORDER BY	idboletainmob)';

	queryAutomotores = '(SELECT 	idboletaautomotor as idboleta,
				3 as idtipoimpuesto,
				boletasautomotor.fechapago as fechadepago,
				(''BOLETA Automotor ''|| anio ||''-''|| to_char(ABS(boletasautomotor.numero),''FM000000''))::text AS nro,
				''Anticipos Regulares (Automotor)'' as tipo,
				concepto as concepto,
				boletasautomotor.importe as importe,
				boletasautomotor.recargo as recargo,
				(boletasautomotor.deducciones + boletasautomotor.dtopagoanual) as descuento,
				boletasautomotor.total as total
			FROM	taxes.boletasautomotor
			WHERE 	UPPER(dominio) = UPPER('''||_numerobien ||''')
			AND 	estado <> ''*''
			ORDER BY	idboletaautomotor)';

	IF (_escatastro) THEN
		_filter = 'catastro = '||_numerobien;
		query := query || queryTGS || ' UNION ' || queryInmob;
	ELSE 
		_filter = 'UPPER(dominio) = UPPER('''||_numerobien||''')';
		query := queryAutomotores;
	END IF;

	queryPlanesDePago = '(SELECT 	idboletaplandepago as idboleta,
				0 as idtipoimpuesto,
				boletasplanesdepago.fechapago as fechadepago,
				(''BOLETA Plan de Pago ''|| anio ||''-''|| to_char(ABS(boletasplanesdepago.numero),''FM000000''))::text AS nro,
				''Plan de Pago ('' || (SELECT nombre FROM taxes.tiposimpuesto WHERE idtipoimpuesto = boletasplanesdepago.idtipoimpuesto)|| '')''as tipo,
				(pagadas || '' - ''||pagoysaldo) as concepto,
				boletasplanesdepago.importe as importe,
				boletasplanesdepago.recargo as recargo,
				boletasplanesdepago.deducciones as descuento,
				boletasplanesdepago.total as total

			FROM	taxes.boletasplanesdepago
			WHERE 	'||_filter||'
			AND 	estado <> ''*''
			ORDER BY	idboletaplandepago)';
	
	query :=  query || ' UNION ' || queryPlanesDePago;
	query := 'SELECT * FROM ('|| query ||') AS foo
		ORDER BY fechadepago DESC';

        OPEN returnCursor FOR EXECUTE query;
        RETURN returnCursor;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION taxes.xmlgetallpagosbybien(_numerobien character varying, _escatastro boolean) IS '2010-09-29: (barbozam)';
CREATE OR REPLACE FUNCTION accounting.addaccountbymodel(_idmodel integer, _idaccount integer) RETURNS integer AS $BODY$
DECLARE
	_id integer;
BEGIN 
	SELECT INTO _id PUBLIC.ISNULL(MAX(idmodelbyaccount),0::integer) + 1 FROM accounting.bookkeppingentriesmodelsbyaccounts;

	EXECUTE 'INSERT INTO accounting.bookkeppingentriesmodelsbyaccounts (
			idmodelbyaccount,
			idmodel,
			idaccount,
			estado
			) VALUES( 
			'|| _id ||',
			'|| _idmodel ||',
			'|| _idaccount ||',
			'''')'; 
	RETURN _id;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION accounting.addaccountbymodel(_idmodel integer, _idaccount integer) IS '2010-09-28: (cesar)';
CREATE OR REPLACE FUNCTION accounting.addbookkeepingentrymodel(_name character varying, _idcashmovementtype integer) RETURNS integer AS $BODY$
DECLARE
	_id integer;
BEGIN 
	SELECT INTO _id PUBLIC.ISNULL(MAX(idmodel),0::integer) + 1 FROM accounting.bookkeppingentriesmodels;

	EXECUTE 'INSERT INTO accounting.bookkeppingentriesmodels (
			idmodel,
			name,
			estado,
			idcashmovementtype
			) VALUES( 
			'|| _id ||',
			upper('''|| _name ||'''),
			'''',
			'|| _idcashmovementtype ||')'; 

	RETURN _id;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION accounting.addbookkeepingentrymodel(_name character varying, _idcashmovementtype integer) IS '2010-09-28: (cesar)';
CREATE OR REPLACE FUNCTION cashflow.getallpaymentorders(_date character varying, _number integer, _idprovider integer, _limit integer, _offset integer) RETURNS refcursor AS $BODY$
DECLARE
	returnCursor REFCURSOR;
	query TEXT;
	filter TEXT:= '';
BEGIN
	IF (_date != '') THEN
		filter := filter || ' AND paymentorders.date = '''|| _date ||'''';
	END IF;

	IF (_number != -1) THEN
		filter := filter || ' AND paymentorders.number = '|| _number;
	END IF;

	IF (_idprovider != -1) THEN
		filter := filter || ' AND paymentorders.idprovider = '|| _idprovider;
	END IF;

	query := 'SELECT	
			paymentorders.idpaymentorder,
			paymentorders.number,
			paymentorders.date,
			paymentorders.idprovider,
			companies.name AS provider,
			paymentorders.idcostcentre,
			paymentorders.detailamount,
			paymentorders.deductionamount,
			paymentorders.amount,
			paymentorders.observations,
			paymentorders.estado
		FROM
			cashflow.paymentorders
			INNER JOIN org.companies ON companies.idcompany = paymentorders.idprovider
		WHERE
			paymentorders.estado<>''*'' AND paymentorders.estado<>''p'' AND iscancel=FALSE '|| filter ||'
		ORDER BY
			paymentorders.date';

	If (_limit != 0) THEN
		query := query || ' LIMIT '|| _limit ||' OFFSET '|| _offset;
	END IF;

	OPEN returnCursor FOR EXECUTE query;
	RETURN returnCursor;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION cashflow.getallpaymentorders(_date character varying, _number integer, _idprovider integer, _limit integer, _offset integer) IS '2010-09-27: (cesar)';
CREATE OR REPLACE FUNCTION taxes.gettitularesbycatastro(_idcatastro integer, _limit integer, _offset integer) RETURNS refcursor AS $BODY$
DECLARE
	returnCursor REFCURSOR;	
	query character varying;
	_catastro integer := 0;
BEGIN
	SELECT INTO _catastro numero FROM taxes.catastros WHERE idcatastro = _idcatastro;
	query:='SELECT 	catastro,
			domape,
			domnudoc,
			CASE WHEN (domcon = '''') THEN
                                    0::integer
                               ELSE
                                    domcon::integer
                               END AS domcon,
			dompor
		FROM 	taxes.padroncatastral
		WHERE 	catastro = '||_catastro ||'
		AND 	estado <> ''*''
		ORDER BY 	domcon';
				

	If (_limit != 0) THEN
		query := query || ' LIMIT '|| _limit ||' OFFSET '|| _offset;
	END IF;
	
	OPEN returnCursor FOR EXECUTE query;
	RETURN returnCursor;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION taxes.gettitularesbycatastro(_idcatastro integer, _limit integer, _offset integer) IS '2010-09-24: (cesar)';
CREATE OR REPLACE FUNCTION resourcescontrol.setresourcesrequeststatus(_idresourcesrequest integer, _idrequeststatus integer, _idvoucherref integer) RETURNS boolean AS $BODY$
DECLARE
	_texto text;
	_solicitantes record;
	_materiales record;
BEGIN 
	UPDATE resourcescontrol.resourcesrequests SET idrequeststatus = _idrequeststatus WHERE idresourcesrequest = _idresourcesrequest;

	IF (_idrequeststatus = 3) THEN -- 3: Autorizado
		UPDATE resourcescontrol.resourcesrequestdetail SET idrequeststatus = 3
		FROM   resourcescontrol.resourcesrequestauthdetail
		WHERE  resourcesrequestdetail.idresourcesrequestdetail = resourcesrequestauthdetail.idresourcesrequestdetail
		AND    resourcesrequestdetail.idresourcesrequest = _idresourcesrequest;

		-- Notificacion por Autorización de Pedido
		SELECT INTO _solicitantes resourcesrequests.idsolicitor, 'Se autorizó el Pedido de Materiales Nº ' || resourcesrequests.number AS texto
		FROM   resourcescontrol.resourcesrequests
		WHERE  resourcesrequests.idresourcesrequest = _idresourcesrequest;

		-- 2) Obtengo todos los recursos del solicitante seleccionado
		_texto := '';
		FOR _materiales IN SELECT 'Solicitados: ' || to_char(resourcesrequestdetail.qty,'FM99999999.00') || ', Autorizados: ' || to_char(resourcesrequestauthdetail.qty,'FM99999999.00') || ' ' || resources.name || ' (' || brands.name || ')' AS material
		    		FROM   resourcescontrol.resourcesrequestauthdetail
		    		INNER JOIN resourcescontrol.resourcesrequests ON resourcesrequests.idresourcesrequest = resourcesrequestauthdetail.idresourcesrequest
		    		INNER JOIN resourcescontrol.resources ON resources.idresource = resourcesrequestauthdetail.idresource
		    		INNER JOIN resourcescontrol.resourcesrequestdetail ON resourcesrequestdetail.idresourcesrequestdetail = resourcesrequestauthdetail.idresourcesrequestdetail
		    		INNER JOIN resourcescontrol.brands ON brands.idbrand = resourcesrequestauthdetail.idbrand
		    		WHERE  resourcesrequests.idresourcesrequest = _idresourcesrequest LOOP

				/* 3) Armo el texto del mensaje */
				_texto := _texto || _materiales.material || '\n';

		END LOOP;

		/* 4) Notifico */
		PERFORM calendar.addnews(_solicitantes.idsolicitor, 2, 2, _texto, _solicitantes.texto);

	ELSIF (_idrequeststatus = 15) THEN -- 15: Aprobado

		-- Notificacion por Aprobación de Pedido
		SELECT INTO _solicitantes resourcesrequests.idsolicitor, 'Se aprobó el Pedido de Materiales Nº ' || resourcesrequests.number AS texto
		FROM        resourcescontrol.resourcesrequests
		WHERE       resourcesrequests.idresourcesrequest = _idresourcesrequest;
		PERFORM     calendar.addnews(_solicitantes.idsolicitor, 2, 2, _solicitantes.texto, _solicitantes.texto);

	ELSIF (_idrequeststatus = 4) THEN -- 4: A comprar
		-- Notificacion por Generación de OC
		-- 1) Comienzo un registro con todos los solicitantes que intervienen en la Orden de Compras
		FOR _solicitantes IN SELECT DISTINCT resourcesrequests.idsolicitor, 'Se generó la Orden de Compras Nº ' || purchaseorders.number AS texto
				FROM   resourcescontrol.purchaseorderdetail
				INNER JOIN resourcescontrol.resourcesrequestauthdetail ON resourcesrequestauthdetail.idresourcesrequestauthdetail = purchaseorderdetail.idresourcesrequestauthdetail
				INNER JOIN resourcescontrol.resourcesrequests ON resourcesrequests.idresourcesrequest = resourcesrequestauthdetail.idresourcesrequest
				INNER JOIN resourcescontrol.purchaseorders ON purchaseorderdetail.idpurchaseorder = purchaseorders.idpurchaseorder
				WHERE  purchaseorderdetail.idpurchaseorder = _idvoucherref
				AND resourcesrequests.idresourcesrequest = _idresourcesrequest LOOP
			-- 2) Obtengo todos los recursos del solicitante seleccionado
			_texto := '';
			FOR _materiales IN SELECT to_char(purchaseorderdetail.approvedqty,'FM99999999.00') || ' ' ||  resources.name || ' (' || brands.name || ')' AS material
				  /*purchaseorderdetail.approvedqty,
				  resources.name,
				  brands.name*/
			    		FROM   resourcescontrol.purchaseorderdetail
			    		INNER JOIN resourcescontrol.resourcesrequestauthdetail ON resourcesrequestauthdetail.idresourcesrequestauthdetail = purchaseorderdetail.idresourcesrequestauthdetail
			    		INNER JOIN resourcescontrol.resourcesrequests ON resourcesrequests.idresourcesrequest = resourcesrequestauthdetail.idresourcesrequest
			    		INNER JOIN resourcescontrol.resources ON resources.idresource = purchaseorderdetail.idresource
			    		INNER JOIN resourcescontrol.brands ON brands.idbrand = purchaseorderdetail.idbrand
			    		WHERE  idpurchaseorder = _idvoucherref
					AND resourcesrequests.idresourcesrequest = _idresourcesrequest
			    		AND    idsolicitor = _solicitantes.idsolicitor LOOP

					/* 3) Armo el texto del mensaje */
					_texto := _texto || _materiales.material || '\n';

			END LOOP;
			/* 4) Notifico */
			PERFORM calendar.addnews(_solicitantes.idsolicitor, 2, 2, _texto, _solicitantes.texto);
		END LOOP;

	ELSIF (_idrequeststatus IN (5,11)) THEN -- 5: Comprado (Adquirido)
		-- Notificacion por Recepción de Materiales
		
		-- 1) Comienzo un registro con todos los solicitantes que intervienen la Nota de Recepción
		FOR _solicitantes IN SELECT  DISTINCT  resourcesrequests.idsolicitor, 'LLegada de Materiales, Nota de Recepción Nº ' || resourcesmovements.idmovement AS texto
				FROM   cashflow.voucherdetail
				INNER JOIN resourcescontrol.purchaseorderdetailxvoucherdetail ON purchaseorderdetailxvoucherdetail.idvoucherdetailref = voucherdetail.idvoucherdetail
				INNER JOIN resourcescontrol.purchaseorderdetail ON purchaseorderdetail.idpurchaseorderdetail = purchaseorderdetailxvoucherdetail.idpurchaseorderdetail
				INNER JOIN resourcescontrol.resourcesrequestauthdetail ON resourcesrequestauthdetail.	idresourcesrequestauthdetail = purchaseorderdetail.idresourcesrequestauthdetail
				INNER JOIN resourcescontrol.resourcesrequests ON resourcesrequests.idresourcesrequest = resourcesrequestauthdetail.idresourcesrequest
				INNER JOIN cashflow.vouchers ON vouchers.idvoucher = voucherdetail.idvoucher
				INNER JOIN resourcescontrol.resourcesmovementdetail ON resourcesmovementdetail.idvoucher = vouchers.idvoucher
				INNER JOIN resourcescontrol.resourcesmovements ON resourcesmovements.idmovement = resourcesmovementdetail.idmovement
				WHERE  voucherdetail.idvoucher = _idvoucherref LOOP

			-- 2) Obtengo todos los recursos del solicitante seleccionado
			_texto := '';
			FOR _materiales IN   SELECT  to_char(voucherdetail.finalqty,'FM99999999.00') || ' ' ||  resources.name || ' (' || brands.name || ')' AS material	
					FROM   cashflow.voucherdetail
					INNER JOIN resourcescontrol.purchaseorderdetailxvoucherdetail ON purchaseorderdetailxvoucherdetail.idvoucherdetailref = voucherdetail.idvoucherdetail
					INNER JOIN resourcescontrol.purchaseorderdetail ON purchaseorderdetail.idpurchaseorderdetail = purchaseorderdetailxvoucherdetail.idpurchaseorderdetail
					INNER JOIN resourcescontrol.resourcesrequestauthdetail ON resourcesrequestauthdetail.idresourcesrequestauthdetail = purchaseorderdetail.idresourcesrequestauthdetail
					INNER JOIN resourcescontrol.resourcesrequests ON resourcesrequests.idresourcesrequest = resourcesrequestauthdetail.idresourcesrequest
 						AND idsolicitor = _solicitantes.idsolicitor
					INNER JOIN resourcescontrol.resources ON resources.idresource = purchaseorderdetail.idresource
					INNER JOIN resourcescontrol.brands ON brands.idbrand = purchaseorderdetail.idbrand
					WHERE  voucherdetail.idvoucher = _idvoucherref LOOP
					
					/* 3) Armo el texto del mensaje */
					_texto := _texto || _materiales.material || e'\n';
			END LOOP;

			/* 4) Notifico */
			PERFORM calendar.addnews(_solicitantes.idsolicitor, 2, 2, _texto, _solicitantes.texto);
		END LOOP;
	END IF;

	RETURN true::boolean;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION resourcescontrol.setresourcesrequeststatus(_idresourcesrequest integer, _idrequeststatus integer, _idvoucherref integer) IS '2010-09-24: (cesar)';
CREATE OR REPLACE FUNCTION resourcescontrol.asignedresourcesperson(_idperson integer, _idsrecursos character varying, _idresponsablemovimiento integer, _dateliberate character varying) RETURNS integer AS $BODY$
DECLARE
	dateAsigned character varying;
	idDistResource character varying;
	cadena character varying;
	query character varying;
	posicion integer;
	_idDep integer;
	_id integer;
BEGIN 

	IF (_dateLiberate = '') THEN
		dateAsigned = 'null';
	ELSE
		dateAsigned = ''''|| _dateLiberate ||'''';
	END IF;
	cadena := _idsrecursos;
	WHILE (char_length(cadena) > 0) LOOP
		posicion = position(',' in cadena);
		idDistResource = substring(cadena for (posicion - 1));
		IF ((posicion + 1 ) > char_length(cadena)) THEN
			cadena = '';
		ELSE
			cadena = substring(cadena from (posicion + 1));
		END IF;

		SELECT INTO _id PUBLIC.ISNULL(MAX(idresourcesperson),0)::integer+1 FROM personalfiles.resourcesperson;
		query := 'INSERT INTO personalfiles.resourcesperson
			VALUES('||_id||',
		                 '||idDistResource||',
		                 '||_idperson||',
		                 1,
		                 '''',
		                 '||dateAsigned||',
			       false
			        )';
		EXECUTE query;

		SELECT INTO _id max(iddistinguishableresourcehistory) + 1 FROM resourcescontrol.distinguishableresourceshistory;
		SELECT INTO _idDep iddependency from personalfiles.jobpersons WHERE idperson = _idperson;
		query := 'INSERT INTO resourcescontrol.distinguishableresourceshistory
			VALUES('||_id||',
		                 '||idDistResource||',
		                 '||dateAsigned||',
		                 NULL,
		                 '||_idperson||',
		                 '''',
		                 0,
		                 '''',
		                 '||_idDep||'
			 )';
		EXECUTE query;

		query := 'INSERT INTO personalfiles.resourcesdependency
			VALUES('||_id||',
		                 '||_idDep||',
		                 '||idDistResource||',
		                 1,
		                 '''',
		                 '||dateAsigned||',
			       false
			        )';
		execute query;	
		UPDATE resourcescontrol.distinguishableresources SET idperson = _idperson WHERE iddistinguishableresource = idDistResource::integer;
			
	END LOOP;

--	PERFORM resourcescontrol.asignedresourcesdependency(_idDep,_idsrecursos,_idresponsablemovimiento,_dateLiberate);

	RETURN 1;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION resourcescontrol.asignedresourcesperson(_idperson integer, _idsrecursos character varying, _idresponsablemovimiento integer, _dateliberate character varying) IS '2010-09-24: (barbozam)';
CREATE OR REPLACE FUNCTION resourcescontrol.liberarrecursosdependencia(_iddependencia integer, _idsrecursos character varying, _idresponsablemovimiento integer, _dateliberate character varying) RETURNS integer AS $BODY$
DECLARE
	dateLiberate character varying;
	idDistResource character varying;
	cadena character varying;
	query character varying;
	posicion integer;
	_idPerson integer;
BEGIN 

	IF (_dateLiberate = '') THEN
		dateLiberate = 'null';
	ELSE
		dateLiberate = ''''|| _dateLiberate ||'''';
	END IF;
	cadena := _idsrecursos;
	WHILE (char_length(cadena) > 0) LOOP
		posicion = position(',' in cadena);
		idDistResource = substring(cadena for (posicion - 1));
		IF ((posicion + 1 ) > char_length(cadena)) THEN
			cadena = '';
		ELSE
			cadena = substring(cadena from (posicion + 1));
		END IF;

		query := 'UPDATE personalfiles.resourcesdependency
			SET transferido = true
			WHERE iddistresource = '||idDistResource||'
			AND iddependency = '|| _iddependencia||'
			AND transferido = false
			AND estado <> ''*''';
		execute query;
		query := 'UPDATE resourcescontrol.distinguishableresourceshistory
			SET iddependency = -1,
			idperson = -1,
			enddate = '|| dateLiberate ||'
			WHERE iddistinguishableresource = '|| idDistResource ||'
			AND iddependency = '|| _iddependencia||'
			AND enddate IS NULL
			AND estado <> ''*''';
		execute query;
		query := 'UPDATE resourcescontrol.distinguishableresources
			SET idperson = -1
			WHERE iddistinguishableresource = '||idDistResource||'
			AND estado <> ''*''';
		execute query;
		_idPerson = -1;
		SELECT INTO _idPerson idperson FROM resourcescontrol.distinguishableresourceshistory WHERE iddependency = _iddependencia AND enddate IS NULL AND iddistinguishableresource = idDistResource::integer;
		IF (_idPerson = -1) THEN
			query := 'UPDATE personalfiles.resourcesperson
				SET transferido = true
				WHERE iddistresource = '||idDistResource||'
				AND idperson = '|| _idPerson||'
				AND transferido = false
				AND estado <> ''*''';
			execute query;
		END IF;
	
	END LOOP;

	RETURN 1;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION resourcescontrol.liberarrecursosdependencia(_iddependencia integer, _idsrecursos character varying, _idresponsablemovimiento integer, _dateliberate character varying) IS '2010-09-24: (barbozam)';
CREATE OR REPLACE FUNCTION resourcescontrol.getidvoucherbyresourcesmovement(_idmovement integer) RETURNS integer AS $BODY$
DECLARE
	_idvoucher integer;
BEGIN 
	
	SELECT  INTO _idvoucher PUBLIC.ISNULL(resourcesmovementdetail.idvoucher,0::integer)
	FROM    resourcescontrol.resourcesmovements
	INNER JOIN resourcescontrol.resourcesmovementdetail ON resourcesmovementdetail.idmovement = resourcesmovements.idmovement
	WHERE   resourcesmovements.idmovement = _idmovement
	LIMIT 1;

	RETURN _idvoucher;

END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION resourcescontrol.getidvoucherbyresourcesmovement(_idmovement integer) IS '2010-09-23: (cesar)';
CREATE OR REPLACE FUNCTION taxes.xmlgetdatosbien(_numero character varying, _escatastro boolean) RETURNS refcursor AS $BODY$
DECLARE
		returnCursor REFCURSOR;	
		query character varying;

BEGIN
	IF (_escatastro) THEN
		query:='SELECT
				catastros.idcatastro,
				padroncatastral.domape as titular,
				CASE WHEN ( (apoderados.apellido || '', '' || apoderados.nombre) = '', '') THEN 
					''''
				ELSE
					PUBLIC.ISNULL(((apoderados.apellido || '', '' || apoderados.nombre))::TEXT,''''::TEXT)
				END AS apoderado,
				PUBLIC.ISNULL((padroncatastral.dcalle ||'' ''|| padroncatastral.dnumro)::TEXT,''-''::TEXT) as domicilio,
				padroncatastral.catastro as numero,
				PUBLIC.ISNULL(catastros.barcode,''-'') AS cuentacorriente  
			FROM 
				taxes.padroncatastral
			LEFT JOIN	
				taxes.catastros ON catastros.numero = padroncatastral.catastro
			LEFT JOIN
				taxes.apoderados ON apoderados.idcatastro = catastros.idcatastro
			WHERE	
				padroncatastral.catastro <> 0
			AND 	padroncatastral.estado <> ''*''
			AND 	padroncatastral.catastro = '|| _numero ||'
			ORDER BY domcon
			LIMIT 1';
	ELSE

		query:='SELECT
				automotores.iddominio,
				padronautomotor.titular as titular,
				'''' AS apoderado,
				PUBLIC.ISNULL(padronautomotor.domicilio::TEXT,''-''::TEXT) as domicilio,
				padronautomotor.dominio as numero,
				'''' AS cuentacorriente  
			FROM 
				taxes.padronautomotor
			LEFT JOIN	
				taxes.automotores ON automotores.iddominio = padronautomotor.iddominio
			WHERE	
				padronautomotor.estado <> ''*''
			AND 	UPPER(padronautomotor.dominio) = UPPER('''|| _numero ||''')
			LIMIT 1';
	END IF;

	OPEN returnCursor FOR EXECUTE query;
	RETURN returnCursor;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION taxes.xmlgetdatosbien(_numero character varying, _escatastro boolean) IS '2010-09-22: (barbozam)';

-- Privilegios para la Administración de Centros de costos

REVOKE ALL PRIVILEGES ON FUNCTION cashflow.getallcostscentresbyfilter(character varying, character varying, character varying, boolean, integer, integer) FROM PUBLIC;
REVOKE ALL PRIVILEGES ON FUNCTION cashflow.getallcostscentresbyfilter(character varying, character varying, character varying, boolean, integer, integer) FROM GROUP suiseibutsu;
GRANT EXECUTE ON FUNCTION cashflow.getallcostscentresbyfilter(character varying, character varying, character varying, boolean, integer, integer) TO GROUP suiseibutsu;


REVOKE ALL PRIVILEGES ON FUNCTION cashflow.addcostcentre(character varying, text, character varying) FROM PUBLIC;
REVOKE ALL PRIVILEGES ON FUNCTION cashflow.addcostcentre(character varying, text, character varying) FROM GROUP suiseibutsu;
GRANT EXECUTE ON FUNCTION cashflow.addcostcentre(character varying, text, character varying) TO GROUP suiseibutsu;



REVOKE ALL PRIVILEGES ON FUNCTION cashflow.setcostcentrebyprovisionorder(integer, boolean) FROM PUBLIC;
REVOKE ALL PRIVILEGES ON FUNCTION cashflow.setcostcentrebyprovisionorder(integer, boolean) FROM GROUP suiseibutsu;
GRANT EXECUTE ON FUNCTION cashflow.setcostcentrebyprovisionorder(integer, boolean) TO GROUP suiseibutsu;
 

REVOKE ALL PRIVILEGES ON FUNCTION cashflow.delcostcentre(integer) FROM PUBLIC;
REVOKE ALL PRIVILEGES ON FUNCTION cashflow.delcostcentre(integer) FROM GROUP suiseibutsu;
GRANT EXECUTE ON FUNCTION cashflow.delcostcentre(integer) TO GROUP suiseibutsu;


REVOKE ALL PRIVILEGES ON FUNCTION cashflow.setcostcentre(integer, character varying, text, character varying) FROM PUBLIC;
REVOKE ALL PRIVILEGES ON FUNCTION cashflow.setcostcentre(integer, character varying, text, character varying) FROM GROUP suiseibutsu;
GRANT EXECUTE ON FUNCTION cashflow.setcostcentre(integer, character varying, text, character varying) TO GROUP suiseibutsu;


CREATE OR REPLACE FUNCTION cashflow.addcostcentre(_name character varying, _description text, _code character varying) RETURNS integer AS $BODY$
DECLARE 
	id integer;
BEGIN 

	IF (SELECT (COUNT(*) = 0) FROM cashflow.costscentres WHERE (name = _name OR code = _code) AND estado != '*' ) THEN
		SELECT INTO id public.ISNULL(max(idcostcentre),0)::integer + 1 FROM cashflow.costscentres; 

		EXECUTE 'INSERT INTO cashflow.costscentres (
			idcostcentre,
			name,
			description,
			code,
			estado
			) VALUES( 
			'|| id ||',
			'''|| _name ||''',
			'''|| _description ||''',
			'''|| _code ||''','''')'; 
	ELSE
		RAISE EXCEPTION 'EL NOMBRE Y/O CODIGO YA EXISTE';
	END IF;		
		
	RETURN id; 
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION cashflow.addcostcentre(_name character varying, _description text, _code character varying) IS '2010-11-01: (administrador)';


 REVOKE ALL PRIVILEGES ON FUNCTION cashflow.getallcostscentresbybudget(integer) FROM PUBLIC;
REVOKE ALL PRIVILEGES ON FUNCTION cashflow.getallcostscentresbybudget(integer) FROM GROUP suiseibutsu;
GRANT EXECUTE ON FUNCTION cashflow.getallcostscentresbybudget(integer) TO GROUP suiseibutsu;


CREATE OR REPLACE FUNCTION resourcescontrol.getidvoucherbyresourcesmovement(_idmovement integer) RETURNS integer AS $BODY$
DECLARE
	_idvoucher integer;
BEGIN 
	
	SELECT  INTO _idvoucher PUBLIC.ISNULL(resourcesmovementdetail.idvoucher,0::integer)
	FROM    resourcescontrol.resourcesmovements
	INNER JOIN resourcescontrol.resourcesmovementdetail ON resourcesmovementdetail.idmovement = resourcesmovements.idmovement
	WHERE   resourcesmovements.idmovement = _idmovement
	LIMIT 1;

	RETURN _idvoucher;

END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION resourcescontrol.getidvoucherbyresourcesmovement(_idmovement integer) IS '2010-09-23: (cesar)';


REVOKE ALL PRIVILEGES ON FUNCTION cashflow.getbudgetcostscentresreport(integer) FROM PUBLIC;
REVOKE ALL PRIVILEGES ON FUNCTION cashflow.getbudgetcostscentresreport(integer) FROM GROUP suiseibutsu;
GRANT EXECUTE ON FUNCTION cashflow.getbudgetcostscentresreport(integer) TO GROUP suiseibutsu;

REVOKE ALL PRIVILEGES ON FUNCTION cashflow.setbankaccountbudgetsamount(integer, double precision) FROM PUBLIC;
REVOKE ALL PRIVILEGES ON FUNCTION cashflow.setbankaccountbudgetsamount(integer, double precision) FROM GROUP daikouzui;
GRANT EXECUTE ON FUNCTION cashflow.setbankaccountbudgetsamount(integer, double precision) TO GROUP daikouzui;

REVOKE ALL PRIVILEGES ON FUNCTION org.getallproviderbybilling(character varying) FROM PUBLIC;
REVOKE ALL PRIVILEGES ON FUNCTION org.getallproviderbybilling(character varying) FROM GROUP rieki;
GRANT EXECUTE ON FUNCTION org.getallproviderbybilling(character varying) TO GROUP rieki;

REVOKE ALL PRIVILEGES ON FUNCTION cashflow.getallvoucherdetailtoinvoiceselected(character varying, double precision, integer, integer) FROM PUBLIC;
REVOKE ALL PRIVILEGES ON FUNCTION cashflow.getallvoucherdetailtoinvoiceselected(character varying, double precision, integer, integer) FROM GROUP rieki;
GRANT EXECUTE ON FUNCTION cashflow.getallvoucherdetailtoinvoiceselected(character varying, double precision, integer, integer) TO GROUP rieki;

-- Carga del voucher con estado '*' e id = 0
INSERT INTO cashflow.vouchers (idvoucher, idvouchertype, number, date, identity, identitytype, partialwotaxes, taxes, partialwtaxes, vatamount, vatp, amount, withdetail, description, estado, idcostcentre, closevoucher, paid, idvoucherresourcestatus, idbudget, cancelamount, nrofactura) VALUES ('0', '0', '0', NULL, '0', '0', '0', '0', '0', '0', '0', '0', TRUE, '', '*', '0', FALSE, FALSE, 0, 1, 0.0, '0000-000000'::character varying);

CREATE OR REPLACE FUNCTION taxes.generarpadronautomotor(_iddominio integer) RETURNS integer AS $BODY$
DECLARE
	_automotor record;
	_desde date;
	_idautomotor integer;
BEGIN

	SELECT INTO _automotor iddominio, pagadesde, fechaexencion, cuota FROM taxes.padronautomotor WHERE iddominio = _iddominio;
	
	IF(_automotor.pagadesde IS NULL) THEN
		_desde := '2000-07-01';
	ELSE 
		_desde := _automotor.pagadesde;
	END IF;

	SELECT INTO _idautomotor public.ISNULL(max(idautomotor),0)::integer + 1 FROM taxes.automotores;

	INSERT INTO taxes.automotores VALUES (
		_idautomotor,
		_automotor.iddominio,
		_desde,
		_automotor.fechaexencion::date,
		_automotor.cuota,
		''
	);

	RETURN _idautomotor;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION taxes.generarpadronautomotor(_iddominio integer) IS '2010-11-02: (administrador)';

/* Libre Deuda de Impuestos improvement */

CREATE OR REPLACE FUNCTION taxes.getfechafinlibredeuda(_idtipoimpuesto integer, _idbien integer) RETURNS date AS $BODY$
DECLARE
	_fechafin date;
BEGIN
	IF (_idtipoimpuesto = 3) THEN -- Impuesto Automotor
		SELECT INTO _fechafin (date - (INTERVAL '1 DAY'))::date
			FROM taxes.cuotasautomotor 
			WHERE idautomotor = _idbien
			AND paidpercentage <> 100
			ORDER BY year, number LIMIT 1;
	ELSIF (_idtipoimpuesto = 1) THEN -- TGS
		SELECT INTO _fechafin (date - (INTERVAL '1 DAY'))::date
			FROM taxes.cuotastgsmf 
			WHERE idcatastro = _idbien 
			AND paidpercentage <> 100
			ORDER BY year, number LIMIT 1;
	ELSIF (_idtipoimpuesto = 2) THEN -- Impuesto Inmobiliario
		SELECT INTO _fechafin (date - (INTERVAL '1 DAY'))::date
			FROM taxes.cuotasinmob 
			WHERE idcatastro = _idbien 
			AND paidpercentage <> 100
			ORDER BY year, number LIMIT 1;
	END IF;

	IF (_fechafin IS NULL) THEN 
		SELECT INTO _fechafin date(EXTRACT('YEAR' FROM now()) || '-12-31');
	END IF;

	RETURN _fechafin;
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION taxes.getfechafinlibredeuda(_idtipoimpuesto integer, _idbien integer) IS '2010-11-02: (santiago)';

CREATE OR REPLACE FUNCTION taxes.getlibredeuda(_idtipoimpuesto integer, _idbien integer) RETURNS boolean AS $BODY$
BEGIN
	RETURN taxes.getfechafinlibredeuda(_idtipoimpuesto, _idbien)>now();
END;$BODY$
    LANGUAGE 'plpgsql' VOLATILE SECURITY DEFINER;
COMMENT ON FUNCTION taxes.getlibredeuda(_idtipoimpuesto integer, _idbien integer) IS '2010-11-02: (santiago)';

/* Encuestas comerciales bugfix */

CREATE OR REPLACE FUNCTION "gea_salta"."addencuestacomercial2009seccion2a" ("_insert" boolean, "_idencuestacomercial" integer, "_f2a" character varying, "_f2b" character varying, "_f2c" character varying, "_f2d" character varying, "_f2e" character varying, "_f2f" character varying, "_f2g" character varying, "_f2h" character varying, "_f2i_a" character varying, "_f2i_b" character varying, "_f2j1_a" character varying, "_f2j1_b" character varying, "_f2j2_a" character varying, "_f2j2_b" character varying, "_f2j3_a" character varying, "_f2j3_b" character varying, "_f2j4" character varying, "_f2j5" character varying, "_f2j6" boolean) RETURNS integer AS $BODY$
DECLARE
	_idseccion2 integer;
	query character varying;
	_fchHabilitacion character varying;
	_fchCierre character varying;
BEGIN
	
	IF (_f2j4 = '') THEN
		_fchHabilitacion = 'null';
	ELSE
		_fchHabilitacion =  ''''|| _f2j4 ||''' ';
	END IF;

	IF (_f2j5 = '') THEN
		_fchCierre = 'null';
	ELSE
		_fchCierre =  ''''|| _f2j5 ||''' ';
	END IF;

	IF (_insert) THEN 
		
		query:='INSERT INTO gea_salta.censo_comercial_2009_seccion2_a
				(
				idencuestacomercial,
				f2a,
				f2b,
				f2c,
				f2d,
				f2e,
				f2f,
				f2g,
				f2h,
				f2i_a,
				f2i_b,
				f2j1_a,
				f2j1_b,
				f2j2_a,
				f2j2_b,
				f2j3_a,
				f2j3_b,
				estado,
				f2j4,
				f2j5,
				f2j6
				)
			VALUES (	
				'|| _idencuestacomercial ||',
				TRIM(UPPER('''|| _f2a ||''')),
				TRIM(UPPER('''|| _f2b ||''')),
				TRIM(UPPER('''|| _f2c ||''')),
				TRIM(UPPER('''|| _f2d ||''')),
				TRIM(UPPER('''|| _f2e ||''')),
				TRIM(UPPER('''|| _f2f ||''')),
				TRIM(UPPER('''|| _f2g ||''')),
				TRIM(UPPER('''|| _f2h ||''')),
				TRIM(UPPER('''|| _f2i_a ||''')),
				TRIM(UPPER('''|| _f2i_b ||''')),
				TRIM(UPPER('''|| _f2j1_a ||''')),
				TRIM(UPPER('''|| _f2j1_b ||''')),
				TRIM(UPPER('''|| _f2j2_a ||''')),
				TRIM(UPPER('''|| _f2j2_b ||''')),
				TRIM(UPPER('''|| _f2j3_a ||''')),
				TRIM(UPPER('''|| _f2j3_b ||''')),
				'''',
				'||_fchHabilitacion||',
				'||_fchCierre||',
				'''||_f2j6||'''
				)';
	ELSE 
		query = ' UPDATE 	gea_salta.censo_comercial_2009_seccion2_a
			SET	f2a = TRIM(UPPER('''|| _f2a ||''')),
				f2b = TRIM(UPPER('''|| _f2b ||''')),
				f2c = TRIM(UPPER('''|| _f2c ||''')),
				f2d = TRIM(UPPER('''|| _f2d ||''')),
				f2e = TRIM(UPPER('''|| _f2e ||''')),
				f2f = TRIM(UPPER('''|| _f2f ||''')),
				f2g = TRIM(UPPER('''|| _f2g ||''')),
				f2h = TRIM(UPPER('''|| _f2h ||''')),
				f2i_a = TRIM(UPPER('''|| _f2i_a ||''')),
				f2i_b = TRIM(UPPER('''|| _f2i_b ||''')),
				f2j1_a = TRIM(UPPER('''|| _f2j1_a ||''')),
				f2j1_b = TRIM(UPPER('''|| _f2j1_b ||''')),
				f2j2_a = TRIM(UPPER('''|| _f2j2_a ||''')),
				f2j2_b = TRIM(UPPER('''|| _f2j2_b ||''')),
				f2j3_a = TRIM(UPPER('''|| _f2j3_a ||''')),
				f2j3_b = TRIM(UPPER('''|| _f2j3_b ||''')),
				f2j4 = '|| _fchHabilitacion ||',
				f2j5 = '|| _fchCierre||',
				f2j6 = '''|| _f2j6 ||'''
			WHERE	idencuestacomercial = '|| _idencuestacomercial;
	END IF;	

	EXECUTE  query;
	RETURN _idencuestacomercial;
END;
$BODY$
LANGUAGE "plpgsql" VOLATILE CALLED ON NULL INPUT SECURITY DEFINER;

REVOKE ALL PRIVILEGES ON FUNCTION taxes.getfechafinlibredeuda(integer, integer) FROM PUBLIC;
REVOKE ALL PRIVILEGES ON FUNCTION taxes.getfechafinlibredeuda(integer, integer) FROM GROUP deeta;
GRANT EXECUTE ON FUNCTION taxes.getfechafinlibredeuda(integer, integer) TO GROUP deeta;
