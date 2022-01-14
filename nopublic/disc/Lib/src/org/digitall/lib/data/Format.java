/**
 LIMITACIÓN DE RESPONSABILIDAD - COPYRIGHT - [Español]
 ================================================================================
 El Suri - Entorno JAVA de Trabajo y Desarrollo para Gobierno Electrónico
 ================================================================================

 Información del Proyecto:  http://elsuri.sourceforge.net

 Copyright (C) 2007-2010 por D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO.
 D'AMBROSIO MARCELO E CASSINA SANTIAGO SOC DE HECHO es propiedad de
 Lic. Santiago Cassina (santiago@digitallsh.com.ar - scap2000@yahoo.com) y
 Marcelo D'Ambrosio (marcelo@digitallsh.com.ar - marcelodambrosio@gmail.com)
 http://www.digitallsh.com.ar

 Este programa es software libre: usted puede redistribuirlo y/o modificarlo
 bajo los términos de la Licencia Pública General GNU publicada
 por la Fundación para el Software Libre, ya sea la versión 3
 de la Licencia, o (a su elección) cualquier versión posterior.

 Este programa se distribuye con la esperanza de que sea útil, pero
 SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita
 MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO.
 Consulte los detalles de la Licencia Pública General GNU para obtener
 una información más detallada.

 Debería haber recibido una copia de la Licencia Pública General GNU
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

*/
/**
 * Format.java
 *
 * */
package org.digitall.lib.data;

import java.awt.Color;

import java.math.BigDecimal;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.digitall.lib.environment.Environment;

public abstract class Format {

    public static Locale argentina = new Locale("es", "AR");

    public static String color2Hex(Color _color) {
	if (_color == null) {
	    _color = Color.black;
	}
	String hexaColor = Integer.toHexString(_color.getRGB());
	return hexaColor.substring(2, hexaColor.length());
    }

    public static Color hex2Color(String _hexa) {
	if (_hexa.trim().length() == 0) {
	    return Color.BLACK;
	} else {
	    return new Color(Integer.parseInt(_hexa, 16));
	}
    }

    public static String money(double _number) {
	return NumberFormat.getCurrencyInstance(Environment.DEFAULT_LOCALE).format(_number);
    }

    public static String moneyWithOutSign(double _number) {
	String result = NumberFormat.getCurrencyInstance(Environment.DEFAULT_LOCALE).format(_number);
	result = result.replace(Environment.DEFAULT_MONETARY_SIGN, ' ').trim();
	return result;
    }

    /**
     * Recibe del tipo 100,50 --> 100.50
     * @param _number
     * @return
     */
    public static BigDecimal toDouble(String _number) {
	try {
	    return toDouble(Double.parseDouble(_number));
	} catch (NumberFormatException e) {
	    e.printStackTrace();
	    return new BigDecimal("0.00");
	}
    }

    public static BigDecimal toDouble(double _number) {
	try {
	    NumberFormat nf = NumberFormat.getNumberInstance(new Locale("en", "US"));
	    nf.setMaximumFractionDigits(2);
	    nf.setMinimumFractionDigits(2);
	    nf.setMinimumIntegerDigits(1);
	    String value = nf.format(_number);
	    Number result = nf.parse(value);
	    return new BigDecimal(result.toString());
	} catch (ParseException e) {
	    e.printStackTrace();
	    return new BigDecimal("0.00");
	}
    }

    public static BigDecimal toDoubleWithMonetarySign(String _number) {
	try {
	    _number = _number.replace(Environment.DEFAULT_MONETARY_SIGN, ' ').trim();
	    NumberFormat nf = NumberFormat.getNumberInstance(Environment.DEFAULT_LOCALE);
	    nf.setMaximumFractionDigits(2);
	    nf.setMinimumFractionDigits(2);
	    nf.setMinimumIntegerDigits(1);
	    Number result = nf.parse(_number);
	    return new BigDecimal(result.toString());
	} catch (ParseException e) {
	    e.printStackTrace();
	    return new BigDecimal("0");
	}
    }

    public static String toHtmlCentered(String _string) {
	return "<html><p align=center>" + _string.replaceAll("\\n", "<br>") + "</html>";
    }

    public static String toHtmlCenteredUnderline(String _string) {
	return "<html><p align=center><u>" + _string.replaceAll("\\n", "<br>") + "</u></font></html>";
    }

    /**
     *Convierte la fecha dada (formato americano) al formato extendido
     * Ejemplo: 2010-12-31 devuelve "Viernes 31 de Diciembre de 2010"
     * @param _date
     * @return
     */
    public static String dateToText(String _date) {
        String _returns = "N/A";
        try {
            if (_date.length() >= 8) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Calendar _fecha = Calendar.getInstance();
                _fecha.setTime(formatter.parse(_date));
                String day = Environment.daysArray[_fecha.get(Calendar.DAY_OF_WEEK) - 1];
                String month = Environment.monthsArray[_fecha.get(Calendar.MONTH)];
                _returns = Environment.lang.getProperty("DateMask").replaceFirst("day", day).replaceFirst("month", month).replaceFirst("dd", String.valueOf(_fecha.get(Calendar.DAY_OF_MONTH))).replaceFirst("yyyy", String.valueOf(_fecha.get(Calendar.YEAR)));
            }
        } catch (ParseException x) {
            x.printStackTrace();
        }
        return _returns;
    }

    /**
     *Convierte la fecha dada (formato americano) en formato extendido
     * Ejemplo: 2010-12-31 devuelve "Viernes 31 de Diciembre de 2010"
     * @param _calendar
     * @return
     */
    public static String dateToText(Calendar _calendar) {
        return dateToText(_calendar.get(Calendar.YEAR) + "-" + (_calendar.get(Calendar.MONTH)+1) + "-" + _calendar.get(Calendar.DAY_OF_MONTH));
    }

    public static class NumberToText {

	private static int flag = 0;
	private static String unit;
	private static String ten;
	private static String number;
	private static String thousand;
	private static String thousandTen;
	private static String thousandHundred;
	private static String million;
	private static String millionTen;

	private static String units(int _number) {
	    switch (_number) {
		case 9 :
		    unit = "nueve";
		    break;
		case 8 :
		    unit = "ocho";
		    break;
		case 7 :
		    unit = "siete";
		    break;
		case 6 :
		    unit = "seis";
		    break;
		case 5 :
		    unit = "cinco";
		    break;
		case 4 :
		    unit = "cuatro";
		    break;
		case 3 :
		    unit = "tres";
		    break;
		case 2 :
		    unit = "dos";
		    break;
		case 1 :
		    if (flag == 0)
			unit = "un";
		    else
			unit = "un";
		    break;
		case 0 :
		    unit = "cero";
		    break;
	    }
	    return unit;
	}

	private static String tens(int _number) {
	    if (_number >= 90 && _number <= 99) {
		ten = "noventa ";
		if (_number > 90)
		    ten = ten.concat("y ").concat(units(_number - 90));
	    } else if (_number >= 80 && _number <= 89) {
		ten = "ochenta ";
		if (_number > 80)
		    ten = ten.concat("y ").concat(units(_number - 80));
	    } else if (_number >= 70 && _number <= 79) {
		ten = "setenta ";
		if (_number > 70)
		    ten = ten.concat("y ").concat(units(_number - 70));
	    } else if (_number >= 60 && _number <= 69) {
		ten = "sesenta ";
		if (_number > 60)
		    ten = ten.concat("y ").concat(units(_number - 60));
	    } else if (_number >= 50 && _number <= 59) {
		ten = "cincuenta ";
		if (_number > 50)
		    ten = ten.concat("y ").concat(units(_number - 50));
	    } else if (_number >= 40 && _number <= 49) {
		ten = "cuarenta ";
		if (_number > 40)
		    ten = ten.concat("y ").concat(units(_number - 40));
	    } else if (_number >= 30 && _number <= 39) {
		ten = "treinta ";
		if (_number > 30)
		    ten = ten.concat("y ").concat(units(_number - 30));
	    } else if (_number >= 20 && _number <= 29) {
		if (_number == 20)
		    ten = "veinte ";
		else
		    ten = "veinti".concat(units(_number - 20));
	    } else if (_number >= 10 && _number <= 19) {
		switch (_number) {
		    case 10 :
			ten = "diez ";
			break;
		    case 11 :
			ten = "once ";
			break;
		    case 12 :
			ten = "doce ";
			break;
		    case 13 :
			ten = "trece ";
			break;
		    case 14 :
			ten = "catorce ";
			break;
		    case 15 :
			ten = "quince ";
			break;
		    case 16 :
			ten = "dieciseis ";
			break;
		    case 17 :
			ten = "diecisiete ";
			break;
		    case 18 :
			ten = "dieciocho ";
			break;
		    case 19 :
			ten = "diecinueve ";
			break;
		}
	    } else
		ten = units(_number);
	    return ten;
	}

	private static String hundreds(int _number) {
	    if (_number >= 100) {
		if (_number >= 900 && _number <= 999) {
		    ten = "novecientos ";
		    if (_number > 900)
			ten = ten.concat(tens(_number - 900));
		} else if (_number >= 800 && _number <= 899) {
		    ten = "ochocientos ";
		    if (_number > 800)
			ten = ten.concat(tens(_number - 800));
		} else if (_number >= 700 && _number <= 799) {
		    ten = "setecientos ";
		    if (_number > 700)
			ten = ten.concat(tens(_number - 700));
		} else if (_number >= 600 && _number <= 699) {
		    ten = "seiscientos ";
		    if (_number > 600)
			ten = ten.concat(tens(_number - 600));
		} else if (_number >= 500 && _number <= 599) {
		    ten = "quinientos ";
		    if (_number > 500)
			ten = ten.concat(tens(_number - 500));
		} else if (_number >= 400 && _number <= 499) {
		    ten = "cuatrocientos ";
		    if (_number > 400)
			ten = ten.concat(tens(_number - 400));
		} else if (_number >= 300 && _number <= 399) {
		    ten = "trescientos ";
		    if (_number > 300)
			ten = ten.concat(tens(_number - 300));
		} else if (_number >= 200 && _number <= 299) {
		    ten = "doscientos ";
		    if (_number > 200)
			ten = ten.concat(tens(_number - 200));
		} else if (_number >= 100 && _number <= 199) {
		    if (_number == 100)
			ten = "cien ";
		    else
			ten = "ciento ".concat(tens(_number - 100));
		}
	    } else
		ten = tens(_number);
	    return ten;
	}

	private static String thousands(int _number) {
	    if (_number >= 1000 && _number < 2000) {
		thousand = ("un mil ").concat(hundreds(_number % 1000));
	    }
	    if (_number >= 2000 && _number < 10000) {
		flag = 1;
		thousand = units(_number / 1000).concat(" mil ").concat(hundreds(_number % 1000));
	    }
	    if (_number < 1000)
		thousand = hundreds(_number);
	    return thousand;
	}

	private static String thousandTens(int _number) {
	    if (_number == 10000)
		thousandTen = "diez mil";
	    if (_number > 10000 && _number < 20000) {
		flag = 1;
		thousandTen = tens(_number / 1000).concat(" mil ").concat(hundreds(_number % 1000));
	    }
	    if (_number >= 20000 && _number < 100000) {
		flag = 1;
		thousandTen = tens(_number / 1000).concat(" mil ").concat(thousands(_number % 1000));
	    }
	    if (_number < 10000)
		thousandTen = thousands(_number);
	    return thousandTen;
	}

	private static String thousandHundreds(int _number) {
	    if (_number == 100000)
		thousandHundred = "cien mil";
	    if (_number >= 100000 && _number < 1000000) {
		flag = 1;
		thousandHundred = hundreds(_number / 1000).concat(" mil ").concat(hundreds(_number % 1000));
	    }
	    if (_number < 100000)
		thousandHundred = thousandTens(_number);
	    return thousandHundred;
	}

	private static String millon(int _number) {
	    if (_number >= 1000000 && _number < 2000000) {
		flag = 1;
		million = ("un millon ").concat(thousandHundreds(_number % 1000000));
	    }
	    if (_number >= 2000000 && _number < 10000000) {
		flag = 1;
		million = units(_number / 1000000).concat(" millones ").concat(thousandHundreds(_number % 1000000));
	    }
	    if (_number < 1000000)
		million = thousandHundreds(_number);
	    return million;
	}

	private static String millionTens(int _number) {
	    if (_number == 10000000)
		millionTen = "diez millones";
	    if (_number > 10000000 && _number < 20000000) {
		flag = 1;
		millionTen = tens(_number / 1000000).concat("millones ").concat(thousandHundreds(_number % 1000000));
	    }
	    if (_number >= 20000000 && _number < 100000000) {
		flag = 1;
		millionTen = tens(_number / 1000000).concat(" milllones ").concat(millon(_number % 1000000));
	    }
	    if (_number < 10000000)
		millionTen = millon(_number);
	    return millionTen;
	}

	public static String numberToText(int _number) {
	    number = millionTens(_number);
	    return number;
	}

	public static String numberToText(double _number) {
	    number = millionTens((int)_number);
	    double _rounded = roundTo(toDouble(_number).doubleValue() - toDouble((int)_number).doubleValue(), 2);
	    return (number + " con " + (((int)(roundTo(_rounded * 100,2))) - (((int)_rounded) * 100)) + "/100").replaceAll("  ", " ");
	}

	private static double roundTo(double _number, int _depth) {
	    return Math.round(_number * Math.pow(10, _depth)) / Math.pow(10, _depth);
	}

    }

}
