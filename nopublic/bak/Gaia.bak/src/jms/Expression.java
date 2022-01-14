package jms;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Expression 
{
/* 
 * 1) >
 * 2) >=
 * 3) <
 * 4) <=
 * 5) !=
 * 6) =
 * */
  private int operador = 0;
  private String operando1, operando2, text;
  
  public Expression(String exp)
  {
    text = exp;
    parseExp(exp);  
  }
  
  public String getText() 
  {
    return text;
  }
  
  public void parseExp(String exp) 
  {
    exp = exp.replace('[',' ').replace(']',' ').replace('(',' ').replace(')',' ');
    exp = exp.replaceAll(" ","");
    if (exp.indexOf(">")>-1) //>
    {
      if (exp.indexOf("=")>exp.indexOf(">")) //>=
        operador = 2;
      else 
        operador = 1;
    } else 
    {
      if (exp.indexOf("<")>-1) //<
      {
        if (exp.indexOf("=")>exp.indexOf("<"))
          operador = 4;
        else
          operador = 3;
      } else 
      {
        if (exp.indexOf("!=")>-1) //!=
          operador = 5;
        else if (exp.indexOf("=")>-1) operador = 6;
      }
    }
    switch (operador) 
    {
      case 1:  //>
          operando1 = exp.substring(0,exp.indexOf(">"));
          operando2 = exp.substring(exp.indexOf(">")+1, exp.length());
        break;
      case 2:  //>=
          operando1 = exp.substring(0,exp.indexOf(">="));
          operando2 = exp.substring(exp.indexOf(">=")+1, exp.length());
        break;
      case 3:  //<
          operando1 = exp.substring(0,exp.indexOf("<"));
          operando2 = exp.substring(exp.indexOf("<")+1, exp.length());
        break;
      case 4:  //<=
          operando1 = exp.substring(0,exp.indexOf("<="));
          operando2 = exp.substring(exp.indexOf("<=")+1, exp.length());
        break;
      case 5:  //!=
          operando1 = exp.substring(0,exp.indexOf("!="));
          operando2 = exp.substring(exp.indexOf("!=")+1, exp.length());
        break;
      case 6:  //=
          operando1 = exp.substring(0,exp.indexOf("="));
          operando2 = exp.substring(exp.indexOf("=")+1, exp.length());
        break;
      case 0:  
          operando1 = "";
          operando2 = "";
        break;
    }
  }
 
  public String getOperando(int index) 
  {
    if (index == 1) return operando1;
      else if (index == 2) return operando2;
        else return "";
  }
  
  public boolean evaluar(ResultSet registro) 
  {
    boolean valor = false;
    if (operador > 0 && operador <= 6) //comparación con números
    {
      try 
      {
        double op1 = Double.parseDouble(registro.getString(operando1));
        double op2 = Double.parseDouble(operando2);
        switch (operador) 
        {
          case 1:  if (op1 > op2)  valor = true; else valor = false; break;
          case 2:  if (op1 < op2)  valor = true; else valor = false; break;
          case 3:  if (op1 >= op2) valor = true; else valor = false; break;
          case 4:  if (op1 <= op2) valor = true; else valor = false; break;
          case 5:  if (op1 != op2) valor = true; else valor = false; break;
          case 6:  if (op1 == op2) valor = true; else valor = false; break;
        }
      } catch (SQLException e) 
      {
        e.printStackTrace();
      }
    }
    return valor;
  }
}