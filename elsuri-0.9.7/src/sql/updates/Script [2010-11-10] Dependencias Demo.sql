SET search_path = organigrama, pg_catalog;

DELETE FROM dependencias;
INSERT INTO dependencias VALUES (0, 0, 'SIN ASIGNAR', 0, ' ', '', '', true);
INSERT INTO dependencias VALUES (1, 0, 'ORGANIGRAMA MUNICIPAL PROVISORIO', 0, ' ', '', '', true);
INSERT INTO dependencias VALUES (2, 1, 'INTENDENTE', 1, ' ', 'Establece las bases para la determinacion de las politicas a seguir en la labor interna y externa de la Municipanlidad y evalua la accion ejecuada por la misma.', '1-Planifica, estudia y decide la accion de la Municipanlidad, a traves de las unidades que componen las secretarias.
2-Promulga, reglamenta y pone en vigencia las ordenanas que sancione el Consejo Deliberante.
3-Organiza, coordina y controla los trabajos a realizar en forma conjunta por las secretarias.
4-Estudia la informacion sobre la marca de los planes aprobados, evalua su realizacion y propone medidas correctiva.
5-Atiende las entrevistas, tales que por su importancia e interes, sean de su competencia.
6-Conviene con otros Entes publicos o privados, la frima de tratados especiales.
7-Supervisa la publicacion del Boletin Oficial del Municipio.
8-Administra los bienes del patrimonio municipal de acuerdo a las normas vigentes.
9-Dispone y supervisa la prestacion de servicios municipales.
10-Supervisa el cumplimiento de la politica dispuesta en materia de releaciones dinamicas e integracion del municipio con el medio, sus relaciones institucionales y la gestion de los asuntos relacionados con el bienestar de la Comunidad.
11-Supervisa la actividad adminsitrativa del Municipio.
12-Supervisa el cumplimiento de la politica dispuesta en materia economica, financiera y patronal.
13-Supervisa el cuimplimiento de la politica dispuesta en materia de Obras y Servicios Publicos y Desarrollo Urbano.
', true);
INSERT INTO dependencias VALUES (6, 1, 'ADMINISTRACION Y DESPACHO', 2, ' ', 'Centraliza toda la documentacion que se gestiona en la Municipalidad, asegurando su tramite correcto.', '1-Atiende el despacho del intendente y administra toda la digesta del Departamento Ejecutivo.
2-Controla el movimiento de todas las actuaciones que se tramitan en la municipalidad.
3-Mantiene un fichero de todos los expedientes existentes y tramitados en la municipalidad.
4-Mantiene libros indices de todas las actuaciones tramitadas ente la municipalidad.
5-Redacta y confecciona, resoluciones contratos y toda documentacion inherente a la gestion municipal.
6-Eleva mensualmente al departamento un informe de la labor desarrollada.', true);
INSERT INTO dependencias VALUES (7, 1, 'ASUNTOS JURIDICOS', 2, ' ', 'Promover los juicios de apremio que permitan el cobro de deudas morosas de contribuyentes, cuya via administrativa se encuentra agotada y proponer las medidas que permitan optimizar la cobrabilidad de deudas a favor del municipio. Ejercer en ausencia del asesor letrado, las funciones de este.', '1-Efectuar las tramitaciones tendientes a lograr el cobro por via de jucio de apremio de los aportes adeudados por los contribuyentes morosos, segun liquidacion proporcionada por la Secretaria de Hacienda.
2-Gestionar ante los estratados judiciales el cobro de la deuda municipal y atender toda accion o demanda, a promoverse contra la municipalidad.
3-Informar al Secretario de Hacienda, los planes de pago propuesto por los deudores y requerir su visto bueno.
4-Dictaminar la incobrabilidad de las deudas de los contribuyentes, fundamentado tal decision.
5-Intervenir en la elaboracion de pliegos, contratos y publicacione, apertura de sobres de las licitaciones publicas y privada, emitiendo dictamen al respecto.
6-Supervisa las  tareas relacionadas con la publicacion del Boletin Oficial Municiapal.
7-Proponer las medidas tendientes a lograr el recupero de las tasas y contribuciones municipales.
8-Elevar en forma mensual, un informe al Departamento Ejecutivo sobre las actuaciones desarrolladas.', true);
INSERT INTO dependencias VALUES (8, 1, 'REPRESENTANTE CONSEJO CONSULTIVO', 2, ' ', 'Es la instancia de representacion institucional, de nivel local en cada Municipio, que atendiendo a la realidad social y economica de cada lugar procuran el mayor impacto social y la mejor ejecucion e implementacion del plan Jefes y Jefas de Hogar. El Decreto 565/02, en su Articulo 9º establece la creacion de los Consejos consultivos, los que seran responsables de la ejecucion y efectivizacion del mismo en cada Provincia, ciudad o Localidad de sus respectivas jurisdicciones.
-Los consejos Consultivos estaran conformados por los organismos representativos del ambito de su jurisdiccion, dando cabida a las organizaciones de trabajadores, empresarios, organizaciones sociales y confesionales y autoridades gubernamentales.
-En funcion del tamaño de la jurisdiccion, se determina el numero de miembros garantizando que al menos las DOS TERCERAS (2/3) partes de los mismos correspondan a las instituciones no gubernamentales (sindicatos, organizaciones empresariales, confesionales y sociales).
-El gobierno Municipal tendra el caracter de COORDINADOR, designador entre los demas integrante un (1) Presidente y un(1) Secretario.', '1-Asegurar localmente el control, transpatencia y la efectiva ejecuicion del progrma.
2-Controlar el procedimiento de adjudicacion de los beneficios.
3-Definir, proponer y seleccionar actividades y tareas de contraprestacion que realizaran los beneficiarios.
4-Proponer criterios de seleccion y listados de beneficiarios para las actividades de contraprestacion proyectadas. En este proceso de seleccion de beneficiarios para los proyectos presentados sera de gran utilidad el aporte y la experiencia de las oficians de Empleo y Bosa de Trabajo, tnato Municipales como de Organizaciones Comunitarias.
5-Efectuar el control y seguimiento del desarrollo de las tareas asignadas al os beneficiarios en el ambito muicipal.
6-Verificar que los beneficiarios den cumplimiento a las contraprestaciones establecidas.
7-Autorizar a los organismos ejecutores para la prestacion de los proyectos o activades.
8-Gestionar y/o tramitar RECLAMOS ante el organismo pertinente (GECAL), de bajas producidas en beneficios de la localidad.
9-Gestionar ante la GECAL (Gerencia de Empleo y Capacitacion Laboral) Salta Capital, apoderamiento y rectificacion de documentos de beneficiarios de los programas de empleo.
10-Informar sobre listados de beneficiarios del programa de Jefes y Jefas de Hogar y programa de empleo aptos para recibir el beneficio mensual.
11-Efectuar diversas consultas via Internet de problematicas planteadas por beneficiarios de la localidad.
12-Efectuar entregas a diferentes barrios y comunidades Aborigenes de la localidad de los programas alimentarios que recibe el municipio como: fedral y Focalizados (Crecer Mejor - Pancitas y Caliiacos).
13-Gestionar y tramitar pedidos de bajas provisoras de los beneficiarios del programa Jefes y Jefas de Hogar, logran insercion laboral en empresas, administracion publica, etc.
', true);
INSERT INTO dependencias VALUES (9, 1, 'AUDITOR CONTABLE EXTERNO Y CONTROL DE GESTION', 2, ' ', 'Asesorar al Intendente Municipal en todas las areas que el Intendente considere necesario, enfatizando los Departamentos que dependen de la Secretaria de Hacienda.', '1-Participar en la elaboracion del Presupuesto Anual de Recursos y Calculo de Gastos.
2-Confeccion de proyectos y pliegos licitatorios para la adquisicion de bienes y/o construccion de obras y/o locucion de servicios.
3-Asesoramiento en la formulacion y evaluacion de proyectos de obras y servicios.
4-Control de gestion de Ejecucion Presupuestaria.
5-Colaborar con la Secretaria de Hacienda en cronogramas de pagos a proveedores y terceros.
6-Participar en la elaboracion y analisis de la Ordenanza Tarifaria Municipal.
7-Asesorar al Departamento de Compras en lo referente a la aplicacion de la Ley Nº 6.838 y complementacion.
8-Asesorar al sector de Rentas Municipales en todo lo necerario para mejorar los sistemas de recaudacion.
9-Asesorar a los Departamentos de Contaduria, Tesoreria y Patrimonios.', true);
INSERT INTO dependencias VALUES (10, 1, 'DELEGACIONES MUNICIPALES', 2, ' ', 'Entender en todo lo atinente a la Delegacion adonde correspondiere y procurar el bienestar Social, Cultural y Deportivo de la poblacion.', '1.Atender las demandas, necesidades, problemas e inquietudes de la comunidad.
2-Difundir las normas, regimenes, planes, programas, servicios, ordenanzas, resoluciones, decisiones y actos municipales elevados por el Sr. Intendente y el Sr. Secretario de Gobierno.
3-Actuar como contralor en materia de bromatologia, seguridad, higiene y verificar su cumplimiento por parte de quienes desarrrollen actividades comerciales, de servicio, agropecuarias e industriales.
4-Diagrmar normas relacionadas con el ordenamiento y la seguridad del transito y ocupacion de la via publica.
5-Realiozar las acciones tendientes a promover el desarrollo cultural de poblacion y la consolidacion de los valores Espirutuales, Tradicionales, Historicos y Morales.
6-Promover y estimar la actividad deportiva, tanto recreativa como competitiva.', true);
INSERT INTO dependencias VALUES (11, 1, 'COORDINACION GENERAL', 2, ' ', '', '', true);
INSERT INTO dependencias VALUES (12, 2, 'DE CULTURA, DEPORTE Y TURISMO', 11, ' ', '', '', true);
INSERT INTO dependencias VALUES (13, 2, 'DE GOBIERNO', 11, ' ', '', '', true);
INSERT INTO dependencias VALUES (14, 2, 'DE HACIENDA', 11, ' ', '', '', true);
INSERT INTO dependencias VALUES (15, 2, 'DE OBRAS, SERVICIOS Y MEDIO AMBIENTE', 11, ' ', '', '', true);
INSERT INTO dependencias VALUES (16, 2, 'DE ACCION SOCIAL', 11, ' ', '', '', true);
INSERT INTO dependencias VALUES (18, 3, 'DE PLANEAMIENTO Y DESARROLLO URBANO', 15, ' ', '', '', true);
INSERT INTO dependencias VALUES (24, 4, 'PLANEAMIENTO URBANO', 18, ' ', '', '', true);
INSERT INTO dependencias VALUES (25, 6, 'JEFE DE CATASTRO', 24, ' ', '', '', true);
INSERT INTO dependencias VALUES (27, 6, 'JEFE DE MENSURA', 24, ' ', '', '', true);
INSERT INTO dependencias VALUES (28, 6, 'BOLSA DE TRABAJO', 24, ' ', '', '', true);
INSERT INTO dependencias VALUES (29, 3, 'OBRAS Y SERVICIOS', 15, ' ', '', '', true);
INSERT INTO dependencias VALUES (30, 4, 'OBRAS PUBLICAS', 29, ' ', '', '', true);
INSERT INTO dependencias VALUES (31, 4, 'SERVICIOS PUBLICOS', 29, ' ', '', '', true);
INSERT INTO dependencias VALUES (32, 4, 'MANTENIMIENTOS GENERALES', 29, ' ', '', '', true);
INSERT INTO dependencias VALUES (33, 6, 'DEPOSITO GENERAL', 29, ' ', '', '', true);
INSERT INTO dependencias VALUES (34, 5, 'PROYECTO', 30, ' ', '', '', true);
INSERT INTO dependencias VALUES (35, 5, 'CONSTRUCCION', 30, ' ', '', '', true);
INSERT INTO dependencias VALUES (36, 5, 'VIALIDAD', 30, ' ', '', '', true);
INSERT INTO dependencias VALUES (37, 6, 'RECOLECCION DE RESIDUOS', 31, ' ', '', '', true);
INSERT INTO dependencias VALUES (38, 6, 'BARRIDO Y LIMPIEZA', 31, ' ', '', '', true);
INSERT INTO dependencias VALUES (39, 6, 'ALUMBRADO PUBLICO', 31, ' ', '', '', true);
INSERT INTO dependencias VALUES (40, 5, 'ENCARGADO DE TALLER', 32, ' ', '', '', true);
INSERT INTO dependencias VALUES (42, 3, 'MEDIO AMBIENTE', 15, ' ', '', '', true);
INSERT INTO dependencias VALUES (43, 4, 'ECOLOGIA Y MEDIO AMBIENTE', 42, ' ', '', '', true);
INSERT INTO dependencias VALUES (44, 4, 'SEGURIDAD E HIGIENE', 42, ' ', '', '', true);
INSERT INTO dependencias VALUES (46, 3, 'ASESORIA LEGAL', 13, '*', '', '', true);
INSERT INTO dependencias VALUES (47, 3, 'PRENSA', 13, ' ', '', '', true);
INSERT INTO dependencias VALUES (48, 4, 'SUMARIO', 13, '*', '', '', true);
INSERT INTO dependencias VALUES (49, 4, 'PERSONAL', 13, '*', '', '', true);
INSERT INTO dependencias VALUES (50, 4, 'TRANSITO', 13, '*', '', '', true);
INSERT INTO dependencias VALUES (51, 5, 'JEFE DE CALLE', 50, '*', '', '', true);
INSERT INTO dependencias VALUES (52, 5, 'MAYORDOMIA', 13, '*', '', '', true);
INSERT INTO dependencias VALUES (53, 4, 'ADMINISTRACION DE GOBIERNO', 13, '*', '', '', true);
INSERT INTO dependencias VALUES (54, 6, 'MESA DE ENTRADAS', 53, '*', '', '', true);
INSERT INTO dependencias VALUES (55, 6, 'ADMINISTRACION DE CEMENTERIOS', 53, '*', '', '', true);
INSERT INTO dependencias VALUES (56, 6, 'ARCHIVO GENERAL', 53, '*', '', '', true);
INSERT INTO dependencias VALUES (57, 6, 'BOLETIN MUNICIPAL', 53, '*', '', '', true);
INSERT INTO dependencias VALUES (58, 4, 'ABORIGEN', 13, '*', '', '', true);
INSERT INTO dependencias VALUES (59, 4, 'BROMATOLOGIA', 13, '*', '', '', true);
INSERT INTO dependencias VALUES (60, 5, 'JEFE DE BRAMOTOLOGIA', 59, '*', '', '', true);
INSERT INTO dependencias VALUES (61, 5, 'INSPECTORES', 60, '*', '', '', true);
INSERT INTO dependencias VALUES (62, 5, 'HIGIENE Y SEGURIDAD AMBIENTE', 60, '*', '', '', true);
INSERT INTO dependencias VALUES (63, 3, 'PRODUCCION Y EMPLEO', 13, ' ', '', '', true);
INSERT INTO dependencias VALUES (64, 4, 'TECNICO', 63, '*', '', '', true);
INSERT INTO dependencias VALUES (65, 5, 'AREA OPERATIVA', 64, '*', '', '', true);
INSERT INTO dependencias VALUES (66, 5, 'AREA ADMINISTRATIVA', 64, '*', '', '', true);
INSERT INTO dependencias VALUES (67, 5, 'AREA DE PLANEAMIENTO', 64, '*', '', '', true);
INSERT INTO dependencias VALUES (68, 3, 'FAMILIA MINORIDAD Y CENTRO DE ADICCIONES PELIGRASAS', 16, ' ', '', '', true);
INSERT INTO dependencias VALUES (70, 4, 'COORDINADOR DE PROGRAMAS PREVENTIVOS', 68, ' ', '', '', true);
INSERT INTO dependencias VALUES (71, 4, 'MEDICO LABORAL', 16, ' ', '', '', true);
INSERT INTO dependencias VALUES (72, 3, 'PROMOCION Y ASISTENCIA SOCIAL', 16, ' ', '', '', true);
INSERT INTO dependencias VALUES (73, 4, 'GABINETE PSICOLOGICO', 72, ' ', '', '', true);
INSERT INTO dependencias VALUES (74, 4, 'COORDINADOR DE COMEDORES COMUNITARIOS', 72, ' ', '', '', true);
INSERT INTO dependencias VALUES (75, 4, 'SUPERVISOR GENERAL DE SALAS DE PRIMEROS AUXILIOS', 72, ' ', '', '', true);
INSERT INTO dependencias VALUES (76, 4, 'PRESACION DE BENEFICIO ASISTENCIA SOCIAL', 72, ' ', '', '', true);
INSERT INTO dependencias VALUES (77, 3, 'BROMATOLOGIA', 13, '*', '', '', true);
INSERT INTO dependencias VALUES (78, 4, 'BROMATOLOGIA', 77, '*', '', '', true);
INSERT INTO dependencias VALUES (79, 5, 'JEFE BROMATOLOGIA', 78, '*', '', '', true);
INSERT INTO dependencias VALUES (80, 6, 'INSPECTORES', 79, '*', '', '', true);
INSERT INTO dependencias VALUES (81, 6, 'HIGIENE Y SEGURIDAD AMBIENTE', 79, '*', '', '', true);
INSERT INTO dependencias VALUES (82, 6, 'AREA DE PLANEAMIENTO', 64, '*', '', '', true);
INSERT INTO dependencias VALUES (83, 6, 'AREA OPERATIVA', 64, '*', '', '', true);
INSERT INTO dependencias VALUES (84, 6, 'AREA ADMINISTRATIVA', 64, '*', '', '', true);
INSERT INTO dependencias VALUES (85, 3, 'RENTAS', 14, ' ', '', '', true);
INSERT INTO dependencias VALUES (86, 3, 'CONTABILIDAD', 14, '*', '', '', true);
INSERT INTO dependencias VALUES (87, 4, 'FISCALIZACIÓN TRIBUTARIA', 85, '*', '', '', true);
INSERT INTO dependencias VALUES (88, 4, 'RECAUDACION', 85, '*', '', '', true);
INSERT INTO dependencias VALUES (89, 6, 'INMUEBLES', 87, '*', '', '', true);
INSERT INTO dependencias VALUES (90, 6, 'AUTOMOTORES', 87, '*', '', '', true);
INSERT INTO dependencias VALUES (91, 6, 'ACTIVIDADES VARIAS', 87, '*', '', '', true);
INSERT INTO dependencias VALUES (92, 6, 'TRIBUTOS VARIOS', 87, '*', '', '', true);
INSERT INTO dependencias VALUES (93, 4, 'LIQUIDACION', 86, '*', '', '', true);
INSERT INTO dependencias VALUES (94, 4, 'COMPRAS', 86, '*', '', '', true);
INSERT INTO dependencias VALUES (95, 4, 'PATRIMONIO', 86, '*', '', '', true);
INSERT INTO dependencias VALUES (96, 7, 'COMPRAS', 94, '*', '', '', true);
INSERT INTO dependencias VALUES (97, 7, 'DEPOSITO', 94, '*', '', '', true);
INSERT INTO dependencias VALUES (98, 7, 'RENDICION DE CUENTAS', 86, '*', '', '', true);
INSERT INTO dependencias VALUES (99, 7, 'IMPUTACIONES', 86, '*', '', '', true);
INSERT INTO dependencias VALUES (100, 7, 'PROVEEDORES', 86, '*', '', '', true);
INSERT INTO dependencias VALUES (101, 4, 'TESORERIA', 14, '*', '', '', true);
INSERT INTO dependencias VALUES (102, 4, 'COMPUTOS', 14, '*', '', '', true);
INSERT INTO dependencias VALUES (103, 3, 'DEPORTE CULTURA Y TURISMO', 12, ' ', '', '', true);
INSERT INTO dependencias VALUES (104, 4, 'CULTURA', 103, ' ', '', '', true);
INSERT INTO dependencias VALUES (105, 4, 'DEPORTE', 103, ' ', '', '', true);
INSERT INTO dependencias VALUES (106, 4, 'TURISMO', 103, ' ', '', '', true);
INSERT INTO dependencias VALUES (107, 5, 'COORDINADOR', 106, ' ', '', '', true);
INSERT INTO dependencias VALUES (108, 5, 'COMPLEJO MUNICIPAL', 105, ' ', '', '', true);
INSERT INTO dependencias VALUES (109, 5, 'BIBLIOTECA MUNICIPAL', 104, ' ', '', '', true);
INSERT INTO dependencias VALUES (110, 5, 'ADMINISTRACION', 103, ' ', '', '', true);
INSERT INTO dependencias VALUES (111, 2, 'PRIVADA', 6, '*', '', '', true);
INSERT INTO dependencias VALUES (112, 1, 'CONCEJO DELIBERANTE', 1, ' ', '', '', true);
INSERT INTO dependencias VALUES (113, 8, 'EMPLEADOS CONCEJO', 112, '*', '', '', true);
INSERT INTO dependencias VALUES (114, 7, 'CNEL. CORNEJO', 10, '*', '', '', true);
INSERT INTO dependencias VALUES (115, 7, 'VESPUCIO', 10, ' ', '', '', true);
INSERT INTO dependencias VALUES (116, 8, 'CONCEJO CONSULTIVO', 2, ' ', '', '', true);
INSERT INTO dependencias VALUES (117, 8, 'ASISTENCIA SOCIAL', 16, '*', '', '', true);
INSERT INTO dependencias VALUES (118, 6, 'PRIVADA', 2, '*', '', '', true);
INSERT INTO dependencias VALUES (119, 8, 'PRIVADA', 2, ' ', '', '', true);
INSERT INTO dependencias VALUES (120, 2, 'PRIVADA', 2, '*', '', '', true);
INSERT INTO dependencias VALUES (121, 2, 'PRIVADA', 11, ' ', '', '', true);
INSERT INTO dependencias VALUES (122, 7, 'CORNEJO', 1, ' ', '', '', true);
INSERT INTO dependencias VALUES (123, 4, 'Tesoreria', 14, '*', '', '', true);
INSERT INTO dependencias VALUES (124, 4, 'TESORERIA', 85, '*', '', '', true);
INSERT INTO dependencias VALUES (125, 4, 'CONTADURIA', 85, '*', '', '', true);
INSERT INTO dependencias VALUES (126, 4, 'COMPRAS', 85, '*', '', '', true);
INSERT INTO dependencias VALUES (127, 4, 'LIQUIDACIONES', 85, '*', '', '', true);
INSERT INTO dependencias VALUES (128, 4, 'ADMINISTRACION', 85, '*', '', '', true);
INSERT INTO dependencias VALUES (129, 4, 'PATRIMONIO', 85, '*', '', '', true);
INSERT INTO dependencias VALUES (130, 5, 'RECAUDACIONES', 85, ' ', '', '', true);
INSERT INTO dependencias VALUES (131, 3, 'COMPRAS X', 14, '*', '', '', true);
INSERT INTO dependencias VALUES (132, 4, 'TESORERIA', 14, ' ', '', '', true);
INSERT INTO dependencias VALUES (133, 4, 'CONTADURIA', 14, ' ', '', '', true);
INSERT INTO dependencias VALUES (134, 4, 'COMPRAS', 14, ' ', '', '', true);
INSERT INTO dependencias VALUES (135, 4, 'LIQUIDACIONES', 14, ' ', '', '', true);
INSERT INTO dependencias VALUES (136, 4, 'ADMINISTRACION', 14, ' ', '', '', true);
INSERT INTO dependencias VALUES (137, 4, 'PATRIMONIO', 14, '*', '', '', true);
INSERT INTO dependencias VALUES (138, 5, 'ORGANISMO FISCAL', 85, ' ', '', '', true);
INSERT INTO dependencias VALUES (139, 5, 'AUTOMOTORES', 85, ' ', '', '', true);
INSERT INTO dependencias VALUES (140, 5, 'INMUEBLES', 85, ' ', '', '', true);
INSERT INTO dependencias VALUES (141, 5, 'ACTIVIDADES VARIAS', 85, ' ', '', '', true);
INSERT INTO dependencias VALUES (142, 6, 'PROVEEDORES', 134, ' ', '', '', true);
INSERT INTO dependencias VALUES (143, 5, 'RECURSOS VARIOS', 85, ' ', '', '', true);
INSERT INTO dependencias VALUES (144, 4, 'ASESORIA CONTABLE', 14, ' ', '', '', true);
INSERT INTO dependencias VALUES (145, 6, 'LICENCIA GREMIAL', 14, ' ', '', '', true);
INSERT INTO dependencias VALUES (146, 4, 'MAESTRANZA', 14, ' ', '', '', true);
INSERT INTO dependencias VALUES (147, 7, '', 142, ' ', '', '', true);
INSERT INTO dependencias VALUES (148, 3, 'ADMINISTRACIÓN DE PERSONAL', 13, '*', '', '', true);
INSERT INTO dependencias VALUES (149, 4, 'ADMINISTRACIÓN DE PERSONAL', 13, ' ', '', '', true);
INSERT INTO dependencias VALUES (150, 4, 'TRÁNSITO', 13, ' ', '', '', true);
INSERT INTO dependencias VALUES (151, 4, 'BROMATOLOGÍA', 13, ' ', '', '', true);
INSERT INTO dependencias VALUES (152, 4, 'PATRIMONIO', 13, ' ', '', '', true);
INSERT INTO dependencias VALUES (153, 4, 'ABORIGEN', 13, ' ', '', '', true);
INSERT INTO dependencias VALUES (154, 4, 'ADMINISTRACIÓN PRODUCCIÓN Y EMPLEO', 63, ' ', '', '', true);
INSERT INTO dependencias VALUES (155, 4, 'ADMINISTRACIÓN DE GOBIERNO', 13, ' ', '', '', true);
INSERT INTO dependencias VALUES (156, 4, 'GABINETE PROFESIONAL', 13, ' ', '', '', true);
INSERT INTO dependencias VALUES (157, 6, 'CTC', 13, ' ', '', '', true);
INSERT INTO dependencias VALUES (158, 6, 'HOGARES', 13, ' ', '', '', true);
INSERT INTO dependencias VALUES (159, 6, 'CANAL COMUNITARIO', 13, ' ', '', '', true);
INSERT INTO dependencias VALUES (160, 1, '', 1, ' ', '', '', true);
INSERT INTO dependencias VALUES (161, 7, '', 142, ' ', '', '', true);
