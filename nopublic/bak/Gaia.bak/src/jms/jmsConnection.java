package jms;

/** Conexión a PostGIS para JavaMapServer */

public class jmsConnection 
{
  private String user=""; /** Usuario */
  private String password=""; /** Clave */
  private String dbname=""; /** Nombre de la BD */
  private String host=""; /** Host: IP o localhost */
  private String port=""; /** Puerto de conexión, en PostgreSQL generalmente es 5432 */

  public jmsConnection()
  {
  
  }
  
  public void setUser(String u) 
  {
    this.user = u;
  }
  
  public void setPassword(String p) 
  {
    this.password = p;
  }
  
  public void setDbName(String d) 
  {
    this.dbname = d;
  }
  
  public void setHost(String h) 
  {
    this.host = h;
  }
  
  public void setPort(String p) 
  {
    this.port = p;
  }
  
  public String getUser() 
  {
    return this.user;
  }
  
  public String getPassword() 
  {
    return this.password;
  }
  
  public String getDbName() 
  {
    return this.dbname;
  }
  
  public String getHost() 
  {
    return this.host;
  }
  
  public String getPort() 
  {
    return this.port;
  }
  
  public String getStrConnection() 
  {
    return "user=" + this.user + " password=" + this.password + " dbname=" + this.dbname + " host=" + this.host + " port=" + this.port;
  }

  public void setStrConnection(String c) 
  {
    user = c.substring(0,c.indexOf(" ")).trim();
    c = c.substring(c.indexOf(" ")).trim();
    password = c.substring(0,c.indexOf(" ")).trim();
    c = c.substring(c.indexOf(" ")).trim();
    dbname = c.substring(0,c.indexOf(" ")).trim();
    c = c.substring(c.indexOf(" ")).trim();
    host = c.substring(0,c.indexOf(" ")).trim();
    c = c.substring(c.indexOf(" ")).trim();
    port = c;
  }
}