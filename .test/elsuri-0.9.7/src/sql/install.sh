#!/bin/bash
echo "
 LIMITACI�N DE RESPONSABILIDAD - COPYRIGHT - [Espa�ol]
 ================================================================================
 El Suri - Entorno JAVA de Trabajo y Desarrollo para Gobierno Electr�nico
 ================================================================================

 Informaci�n del Proyecto:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 por D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO es propiedad de
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) y
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los t�rminos de la Licencia P�blica General GNU publicada
 por la Fundaci�n para el Software Libre, ya sea la versi�n 3
 de la Licencia, o (a su elecci�n) cualquier versi�n posterior.

 Este programa se distribuye con la esperanza de que sea �til, pero
 SIN GARANT�A ALGUNA; ni siquiera la garant�a impl�cita
 MERCANTIL o de APTITUD PARA UN PROP�SITO DETERMINADO.
 Consulte los detalles de la Licencia P�blica General GNU para obtener
 una informaci�n m�s detallada.

 Deber�a haber recibido una copia de la Licencia P�blica General GNU
 junto a este programa.
 En caso contrario, consulte <http://www.gnu.org/licenses/>.

 DISCLAIMER - COPYRIGHT - [English]
 =====================================================================================
 El Suri - JAVA Management & Development Environment for Electronic Government
 =====================================================================================

 Project Info:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 by D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO is owned by
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) and
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
"
echo "******************************************************************

Notas de Instalaci�n
====================

    * Debe tener instalado PostgreSQL (versi�n 8.2 o superior)
    * Debe tener instalado PostGIS integrado a PostgreSQL
    * Debe estar iniciado el motor de bases de datos PostgreSQL
    * El usuario postgres debe tener acceso irrestricto al motor

    Si cumple estos requisitos entonces puede continuar,
    si no presione Control-C ahora
"
echo "Presione ENTER para continuar o Control-C para cancelar..."
read

echo "Este script intentar� instalar la base de datos elsuridemo"
echo "Presione ENTER para continuar o Control-C para cancelar (en cualquier momento)"
read

echo "Presione ENTER para borrar la base de datos \"elsuridemo\""
read
echo "Borrando (si existe) la base de datos \"elsuridemo\""
dropdb elsuridemo -U postgres
echo "Presione ENTER para crear la base de datos \"elsuridemo\""
echo "Si ocurre un error por favor no contin�e con la ejecuci�n del script"
read
echo "Creando la base de datos \"elsuridemo\""
createdb elsuridemo -U postgres -T template1
echo "Si ha ocurrido un error por favor presione Control-C, si no presione ENTER"
echo "Presione ENTER para insertar los roles necesarios"
read
echo "Insertando roles"
psql -U postgres -d elsuridemo -f pgdump_roles.sql
echo "Si han ocurrido errores ahora puede ignorarlos"
echo "Presione ENTER para insertar la base de datos \"elsuridemo\""
echo "En este paso seguramente van a ocurrir errores, puede ignorarlos tranquilamente"
read
echo "Levantando la base de datos"
gunzip -c pgdump-elsuridemo.tar.gz | pg_restore -U postgres -d elsuridemo -C -Ft -v

echo "Listo!"
