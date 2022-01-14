package org.digitall.projects.kratos.salta;
import com.sun.j3d.utils.behaviors.interpolators.KBKeyFrame;
import com.sun.j3d.utils.behaviors.interpolators.KBRotPosScaleSplinePathInterpolator;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.geometry.Stripifier;
import com.sun.j3d.utils.geometry.Triangulator;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;
import java.applet.Applet;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.Panel;
import java.awt.Polygon;
import java.awt.geom.GeneralPath;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.HiResCoord;
import javax.media.j3d.LineArray;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.LineStripArray;
import javax.media.j3d.Locale;
import javax.media.j3d.Material;
import javax.media.j3d.Node;
import javax.media.j3d.PointArray;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.VirtualUniverse;

import javax.swing.JDialog;

import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.sql.LibSQL;

public class ThreeDeeDrawEngine extends JDialog
{
//  private Polygon[] polys; 
  private Polygon polys; 
  private Point3d[] polygon;
  private GeometryInfo gi;
  private NormalGenerator ng;
  private Stripifier st;
  private Appearance appearance;
  private Material material;
  private PolygonAttributes polyatt;
  private Shape3D part; 
  private int puntos;
//  private int[] stripCounts = new int[1];
  private int numPaths;
  private int[] selectedPath;
  private GeneralPath[] paths; 
  private double xmin=0;
  private double xmax=0;
  private double ymin=0;
  private double ymax=0;
  private double extX=0;
  private double extY=0;
  private double Factor = 1;
  private double xOffset = 0;
  private double yOffset = 0;
  private int fWidth=800,fHeight=600;
  private int fNumPoints;


  private int duration = 15000;
/*  private Alpha animAlpha;
  private Transform3D yAxis;
  private KBKeyFrame[] linearKeyFrames;
  private KBRotPosScaleSplinePathInterpolator  linearInterpolator;
  private TransformGroup objTransformGroup;
*/

    
//  public GeomInfo(Polygon[] _polys) 
//    polys = _polys;
  public ThreeDeeDrawEngine()
  {

    GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
    Canvas3D canvas = new Canvas3D(config);
    this.setSize(800,600);

    add(canvas, BorderLayout.CENTER);

    Color3f light1Color = new Color3f(0.1f, 0.1f, 1.8f);
    BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
  

    BranchGroup scene = new BranchGroup();

    // Set up the background
    Color3f bgColor = new Color3f(0.05f, 2.05f, 0.2f);
    Background bg = new Background(bgColor);
    scene.addChild(bg);

    Sphere sphere = new Sphere(0.5f);
    scene.addChild(sphere);

    addManzanas(scene);
//    addParcelas(scene);
//    addCalles(scene, bounds);

    Vector3f light1Direction  = new Vector3f(4.0f, -7.0f, -12.0f);
    DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
    light1.setInfluencingBounds(bounds);
    scene.addChild(light1);
    scene.setBounds(bounds);

    // Add viewing platform  
    SimpleUniverse u = new SimpleUniverse(canvas);
    Locale loc = new Locale(u);
    // add mouse behaviors to ViewingPlatform
    ViewingPlatform viewingPlatform = u.getViewingPlatform();
    viewingPlatform.setNominalViewingTransform();
    
    // add orbit behavior to the ViewingPlatform
    OrbitBehavior orbit = new OrbitBehavior(canvas,OrbitBehavior.REVERSE_ALL);
    orbit.setSchedulingBounds(bounds);
    viewingPlatform.setViewPlatformBehavior(orbit);
//    scene.compile();
//    loc.addBranchGraph(scene);
//    u.addBranchGraph(scene);


    Locale locale = createLocaleCoords(u);
    //create the BranchGroup containing the Geometry for the scene
    scene.compile();
    //add the scene BranchGroup to the Locale
    locale.addBranchGraph(scene);
  }

  public Polygon getPolyFromPoints(double[] xp, double[] yp, int cantidad) 
  {
    int[] x = new int[cantidad];
    int[] y = new int[cantidad];
    for (int i=0; i < cantidad; i++) 
    {
//      x[i] = (int)( (xp[i] - xOffset) * Factor);
//      y[i] = (int)( fHeight - ((yp[i] - yOffset) * Factor));
      x[i] = (int)( (xp[i] - xOffset));
      y[i] = (int)( fHeight - ((yp[i] - yOffset)));
    }
    Polygon poly = new Polygon(x, y, cantidad);
    return poly;
  } 

  protected Locale createLocaleCoords( VirtualUniverse u )
  {
    int[] xPos = { 0, 0, 0, 0, 0, 0, 0, 0 };
    int[] yPos = { 0, 0, 0, 0, 0, 0, 0, 0 };
    int[] zPos = { 0, 0, 0, 0, 0, 0, 0, 0 };
    HiResCoord hiResCoord = new HiResCoord( xPos, yPos, zPos );
    return new Locale( u, hiResCoord );
  }

  public void addParcelas(BranchGroup scene) 
  {
    try {
      String Data = "the_geom from (select the_geom, parcela, oid from gis.parcelas_su";
/*      Data += " union select the_geom, parcela, oid from gis.parcelas_sb";
      Data += " union select the_geom, parcela, oid from gis.parcelas_sc"; 
      Data += " union select the_geom, parcela, oid from gis.parcelas_sd"; 
      Data += " union select the_geom, parcela, oid from gis.parcelas_se"; 
      Data += " union select the_geom, parcela, oid from gis.parcelas_sf"; 
      Data += " union select the_geom, parcela, oid from gis.parcelas_sg"; 
      Data += " union select the_geom, parcela, oid from gis.parcelas_sh"; 
      Data += " union select the_geom, parcela, oid from gis.parcelas_sj"; 
      Data += " union select the_geom, parcela, oid from gis.parcelas_sk";
      Data += " union select the_geom, parcela, oid from gis.parcelas_sl"; 
      Data += " union select the_geom, parcela, oid from gis.parcelas_sm"; 
      Data += " union select the_geom, parcela, oid from gis.parcelas_sn"; 
      Data += " union select the_geom, parcela, oid from gis.parcelas_so"; 
      Data += " union select the_geom, parcela, oid from gis.parcelas_sp"; 
      Data += " union select the_geom, parcela, oid from gis.parcelas_sq"; 
      Data += " union select the_geom, parcela, oid from gis.parcelas_sr"; 
//Error GEOS!!      Data += " union select the_geom, parcela, oid from gis.parcelas_ss"; 
      Data += " union select the_geom, parcela, oid from gis.parcelas_st"; 
      Data += " union select the_geom, parcela, oid from gis.parcelas_su) as foo"; */
      Data += " ) as foo";
      Data = Data.trim();
      String geometry = Data.substring(0,Data.indexOf(" ")).trim();
      Data = Data.substring(Data.indexOf(" ")).trim();
      String LabelItem = "parcela as label, ";
      String Qwhere = "";
      String Query = "select " + LabelItem + " AsText(centroid(" + geometry + ")) as centroid, AsText(" + geometry + ") as the_geom,npoints(" + geometry + ") " + Data + Qwhere;
      System.out.println(Query);
      ResultSet count = LibSQL.exQuery("select count(*) from (" + Query + ") as foo");
      count.next();
      paths = new GeneralPath[count.getInt(1)];
      selectedPath = new int[count.getInt(1)];
      ResultSet Polygons = LibSQL.exQuery(Query);
      if (Qwhere == Qwhere) 
      {
        ResultSet Extent = LibSQL.exQuery("select xmin(extent(" + geometry + ")), " +
                            "xmax(extent(" + geometry + ")), " +
                            "ymin(extent(" + geometry + ")), " +
                            "ymax(extent(" + geometry + ")) " + Data );
  
        Extent.next();
        
        xmin = Extent.getDouble("xmin");
        xmax = Extent.getDouble("xmax");
        ymin = Extent.getDouble("ymin");
        ymax = Extent.getDouble("ymax");
        extX = xmax - xmin;
        extY = ymax - ymin;
      }
      if (extX > extY) 
      {
        Factor = (fWidth-20) / extX;
      } else 
      {
        Factor = (fHeight-20) / extY;
      }
      xOffset = xmin;
      yOffset = ymin;
      System.out.println("ExtX: " + extX + " ExtY: " + extY);
      System.out.println("xFactor: " + Factor + " yFactor: " + Factor);
      System.out.println("xOffset: " + xOffset + " yOffset: " + yOffset);
      numPaths = 0;
      double [] xd;
      double [] yd;
      while (Polygons.next()) 
      {
        /**Polï¿½gono*/  
        numPaths++;
        int fNumPoints = Polygons.getInt("npoints");
        String Poly = Polygons.getString("the_geom");
        Poly = Poly.substring(Poly.indexOf("(((")+3,Poly.length()-3); //Si es polï¿½gono cerrado, por ejemplo Manzana o Parcela
//        Poly = Poly.substring(Poly.indexOf("((")+2,Poly.length()-3);  //Si es una polilï¿½nea, por ejemplo Calle o Rï¿½o
        xd = new double[fNumPoints];
        yd = new double[fNumPoints];
        for (int i=0; i < fNumPoints-1; i++) 
        {
          xd[i] = Double.parseDouble(Poly.substring(0,Poly.indexOf(" ")).trim());
          yd[i] = Double.parseDouble(Poly.substring(Poly.indexOf(" "), Poly.indexOf(",")).trim());
          Poly = Poly.substring(Poly.indexOf(",")+1,Poly.length());
        }
        xd[fNumPoints-1] = Double.parseDouble(Poly.substring(0,Poly.indexOf(" ")).trim());
        yd[fNumPoints-1] = Double.parseDouble(Poly.substring(Poly.indexOf(" "), Poly.length()).trim());

        polys = getPolyFromPoints(xd, yd, fNumPoints);
        double rnd = Math.random();
        polygon = new Point3d[fNumPoints];
        for (int j=0;j<fNumPoints;j++)
        {
          polygon[j] = new Point3d(polys.xpoints[j]/100.0,polys.ypoints[j]/100.0,0.0);
//          polygon[j] = new Point3d(xd[j]/100.0,yd[j]/100.0,0.0);
//          polygon[j] = new Point3d(polys.xpoints[j],polys.ypoints[j],0);
        }
        int[] stripCounts= { fNumPoints };

        gi = new GeometryInfo(GeometryInfo.POLYGON_ARRAY); 
        gi.setCoordinates(polygon); 
        gi.setStripCounts(stripCounts); 
        ng = new NormalGenerator(); 
        ng.generateNormals(gi); 
        st = new Stripifier(); 
        st.stripify(gi); 
        appearance = new Appearance();

/*
        ColoringAttributes ca = new ColoringAttributes();
        ca.setColor((float)Math.random(), (float)Math.random(), (float)Math.random());
        appearance.setColoringAttributes(ca);
*/        
        appearance.setMaterial(new Material());

        polyatt = new PolygonAttributes();
        polyatt.setBackFaceNormalFlip(false);
        polyatt.setCullFace(0);
        polyatt.setPolygonMode(PolygonAttributes.POLYGON_FILL); //wireframe o poligono relleno
        appearance.setPolygonAttributes(polyatt);
        part = new Shape3D(gi.getGeometryArray(),appearance); 
        scene.addChild(part);

        //Polilï¿½nea
        for (int j=0;j<fNumPoints;j++)
        {
          polygon[j] = new Point3d(polys.xpoints[j]/100.0,polys.ypoints[j]/100.0,-0.001f);
        }
        LineStripArray calle = new LineStripArray(fNumPoints,LineStripArray.COORDINATES|LineStripArray.NORMALS, stripCounts);
        calle.setCoordinates(0,polygon);
        Appearance ap = new Appearance();
//        LineAttributes la = new LineAttributes();
//        la.setLineWidth(20.1f);
//        ap.setLineAttributes(la);
        ColoringAttributes c = new ColoringAttributes();
        c.setColor(2.1f, 2.1f, 2.1f);
        ap.setColoringAttributes(c);
        Shape3D shape = new Shape3D(calle, ap);
        scene.addChild(shape);

      }
    } catch (SQLException x) 
    {
      Advisor.printException(x);
    }

    Point3d[] linea = new Point3d[2];
    linea[0] = new Point3d(0,0,0);
    linea[1] = polygon[1];
    LineArray guia = new LineArray(2,LineArray.COORDINATES|LineArray.NORMALS);
    guia.setCoordinates(0,linea);
    Shape3D guiash = new Shape3D(guia);
    scene.addChild(guiash);
  }

  public void addManzanas(BranchGroup scene)
  {
    try {
      String Data = "the_geom from (select the_geom, oid from gis_salta.manzanas_vinculadas WHERE area(the_geom) > 0 order by oid asc /*limit 10*/";
/*      Data += " union select the_geom, manzana, oid from gis.manzanas_sb";
      Data += " union select the_geom, manzana, oid from gis.manzanas_sc"; 
      Data += " union select the_geom, manzana, oid from gis.manzanas_sd";
      Data += " union select the_geom, manzana, oid from gis.manzanas_se";
      Data += " union select the_geom, manzana, oid from gis.manzanas_sf";
      Data += " union select the_geom, manzana, oid from gis.manzanas_sg";
      Data += " union select the_geom, manzana, oid from gis.manzanas_sh";
      Data += " union select the_geom, manzana, oid from gis.manzanas_sj";
      Data += " union select the_geom, manzana, oid from gis.manzanas_sk";
      Data += " union select the_geom, manzana, oid from gis.manzanas_sl";
      Data += " union select the_geom, manzana, oid from gis.manzanas_sm";
      Data += " union select the_geom, manzana, oid from gis.manzanas_sn";
      Data += " union select the_geom, manzana, oid from gis.manzanas_so";
      Data += " union select the_geom, manzana, oid from gis.manzanas_sp";
      Data += " union select the_geom, manzana, oid from gis.manzanas_sq";
      Data += " union select the_geom, manzana, oid from gis.manzanas_sr";
      Data += " union select the_geom, manzana, oid from gis.manzanas_ss";
      Data += " union select the_geom, manzana, oid from gis.manzanas_st";
      Data += " union select the_geom, manzana, oid from gis.manzanas_su";*/  
      Data += " ) as foo";
      Data = Data.trim();
      String geometry = Data.substring(0,Data.indexOf(" ")).trim();
      Data = Data.substring(Data.indexOf(" ")).trim();
      String LabelItem = "oid as label, ";
      String Qwhere = "";
      String Query = "select " + LabelItem + " /*AsText(centroid(" + geometry + ")) as centroid,*/ AsText(" + geometry + ") as the_geom,npoints(" + geometry + ") " + Data + Qwhere;
      System.out.println(Query);
      ResultSet count = LibSQL.exQuery("select count(*) from (" + Query + ") as foo");
      count.next();
      paths = new GeneralPath[count.getInt(1)];
      selectedPath = new int[count.getInt(1)];
      ResultSet Polygons = LibSQL.exQuery(Query);
      if (Qwhere == Qwhere) 
      {
        ResultSet Extent = LibSQL.exQuery("select xmin(extent(" + geometry + ")), " +
                            "xmax(extent(" + geometry + ")), " +
                            "ymin(extent(" + geometry + ")), " +
                            "ymax(extent(" + geometry + ")) " + Data );
  
        Extent.next();
        xmin = Extent.getDouble("xmin");
        xmax = Extent.getDouble("xmax");
        ymin = Extent.getDouble("ymin");
        ymax = Extent.getDouble("ymax");
        extX = xmax - xmin;
        extY = ymax - ymin;
      }
      if (extX > extY) 
      {
        Factor = (fWidth-20) / extX;
      } else 
      {
        Factor = (fHeight-20) / extY;
      }
      xOffset = xmin;
      yOffset = ymin;
      System.out.println("ExtX: " + extX + " ExtY: " + extY);
      System.out.println("xFactor: " + Factor + " yFactor: " + Factor);
      System.out.println("xOffset: " + xOffset + " yOffset: " + yOffset);
      numPaths = 0;
      while (Polygons.next()) 
      {
        /**Polï¿½gono*/  
        numPaths++;
        String _polygonString = Polygons.getString("the_geom");
        //_polygonString = _polygonString.substring(_polygonString.indexOf("(((")+3,_polygonString.length()-3); //Si es polï¿½gono cerrado, por ejemplo Manzana o Parcela
//        Poly = Poly.substring(Poly.indexOf("((")+2,Poly.length()-3);  //Si es una polilï¿½nea, por ejemplo Calle o Rï¿½o

	 _polygonString = _polygonString.replaceAll("POLYGON", "");
	 _polygonString = _polygonString.substring(_polygonString.indexOf("((") + 2, _polygonString.length() - 2);
	 String[] points = _polygonString.split(",");

	  double[] xd = new double[points.length];
	  double[] yd = new double[points.length];
	  for (int i = 0; i < points.length; i++) {
	      String[] xy = points[i].split(" ");
	      xd[i] = Double.parseDouble(xy[0]);
	      yd[i] = Double.parseDouble(xy[1]);
	  }

        polys = getPolyFromPoints(xd, yd, points.length);
        double rnd = Math.random();
        polygon = new Point3d[points.length];
        for (int j=0;j<points.length;j++)
        {
          polygon[j] = new Point3d(polys.xpoints[j]/100.0,polys.ypoints[j]/100.0,0.0);
//          polygon[j] = new Point3d(xd[j]/100.0,yd[j]/100.0,0.0);
//          polygon[j] = new Point3d(polys.xpoints[j],polys.ypoints[j],0);
        }
        int[] stripCounts= { points.length };

        /*PointArray manzana = new PointArray(points.length,PointArray.COORDINATES|PointArray.NORMALS|PointArray.TEXTURE_COORDINATE_2);
        manzana.setCoordinates(0,polygon);
        Shape3D shape = new Shape3D(manzana);
        scene.addChild(shape);*/

        gi = new GeometryInfo(GeometryInfo.POLYGON_ARRAY); 
        gi.setCoordinates(polygon); 
        gi.setStripCounts(stripCounts); 
        ng = new NormalGenerator(); 
        ng.generateNormals(gi); 
        st = new Stripifier(); 
        st.stripify(gi); 
        appearance = new Appearance();
        polyatt = new PolygonAttributes();
        polyatt.setBackFaceNormalFlip(false);
        polyatt.setCullFace(0);
        polyatt.setPolygonMode(PolygonAttributes.POLYGON_FILL); //wireframe o poligono relleno
        ColoringAttributes ca = new ColoringAttributes();
        ca.setColor(1.8f, 0.1f, 0.1f);
        appearance.setColoringAttributes(ca);
        appearance.setPolygonAttributes(polyatt);
        part = new Shape3D(gi.getGeometryArray(),appearance); 
        scene.addChild(part);

        //Polilï¿½nea
        /*for (int j=0;j<points.length;j++)
        {
          polygon[j] = new Point3d(polys.xpoints[j]/100.0,polys.ypoints[j]/100.0,-0.001f);
        }
        LineStripArray calle = new LineStripArray(points.length,LineStripArray.COORDINATES|LineStripArray.NORMALS, stripCounts);
        calle.setCoordinates(0,polygon);
        Appearance ap = new Appearance();
        LineAttributes la = new LineAttributes();
        la.setLineWidth(1.1f);
        ap.setLineAttributes(la);
        ColoringAttributes c = new ColoringAttributes();
        c.setColor(2.1f, 2.1f, 2.1f);
        ap.setColoringAttributes(c);
        Shape3D shape = new Shape3D(calle, ap);
        scene.addChild(shape);*/

      }
    } catch (SQLException x) 
    {
      Advisor.printException(x);
    }

    Point3d[] linea = new Point3d[2];
    linea[0] = new Point3d(0,0,0);
    linea[1] = polygon[1];
    LineArray guia = new LineArray(2,LineArray.COORDINATES|LineArray.NORMALS);
    guia.setCoordinates(0,linea);
    Shape3D guiash = new Shape3D(guia);
    scene.addChild(guiash);
  }
 
  public void addCalles(BranchGroup scene, BoundingSphere bounds)
  {
    try {
      String Data = "the_geom from (select the_geom, idcalle, oid from gis.calles where idcalle = 408 limit 1";
      Data += " ) as foo";
      Data = Data.trim();
      String geometry = Data.substring(0,Data.indexOf(" ")).trim();
      Data = Data.substring(Data.indexOf(" ")).trim();
      String LabelItem = "idcalle as label, ";
      String Qwhere = "";
      String Query = "select " + LabelItem + " AsText(centroid(" + geometry + ")) as centroid, AsText(" + geometry + ") as the_geom,npoints(" + geometry + ") " + Data + Qwhere;
      System.out.println(Query);
      ResultSet count = LibSQL.exQuery("select count(*) from (" + Query + ") as foo");
      count.next();
      paths = new GeneralPath[count.getInt(1)];
      selectedPath = new int[count.getInt(1)];
      ResultSet Polygons = LibSQL.exQuery(Query);
     /* if (Qwhere == Qwhere) 
      {
        ResultSet Extent = Proc.exQuery(Con,"select xmin(extent(" + geometry + ")), " +
                            "xmax(extent(" + geometry + ")), " +
                            "ymin(extent(" + geometry + ")), " +
                            "ymax(extent(" + geometry + ")) " + Data );
  
        Extent.next();
        xmin = Extent.getDouble("xmin");
        xmax = Extent.getDouble("xmax");
        ymin = Extent.getDouble("ymin");
        ymax = Extent.getDouble("ymax");
        extX = xmax - xmin;
        extY = ymax - ymin;
      }
      if (extX > extY) 
      {
        Factor = (fWidth-20) / extX;
      } else 
      {
        Factor = (fHeight-20) / extY;
      }
      xOffset = xmin;
      yOffset = ymin;
      System.out.println("ExtX: " + extX + " ExtY: " + extY);
      System.out.println("xFactor: " + Factor + " yFactor: " + Factor);
      System.out.println("xOffset: " + xOffset + " yOffset: " + yOffset);*/
      numPaths = 0;
      double [] xd;
      double [] yd;
      KBKeyFrame[] linearKeyFrames = new KBKeyFrame[6];
      while (Polygons.next()) 
      {
        /**Polï¿½gono*/  
        numPaths++;
        int fNumPoints = Polygons.getInt("npoints");
        String Poly = Polygons.getString("the_geom");
        Poly = Poly.substring(Poly.indexOf("((")+2,Poly.length()-3);  //Si es una polilï¿½nea, por ejemplo Calle o Rï¿½o
        xd = new double[fNumPoints];
        yd = new double[fNumPoints];
        for (int i=0; i < fNumPoints-1; i++) 
        {
          xd[i] = Double.parseDouble(Poly.substring(0,Poly.indexOf(" ")).trim());
          yd[i] = Double.parseDouble(Poly.substring(Poly.indexOf(" "), Poly.indexOf(",")).trim());
          Poly = Poly.substring(Poly.indexOf(",")+1,Poly.length());
        }
        xd[fNumPoints-1] = Double.parseDouble(Poly.substring(0,Poly.indexOf(" ")).trim());
        yd[fNumPoints-1] = Double.parseDouble(Poly.substring(Poly.indexOf(" "), Poly.length()).trim());

        polys = getPolyFromPoints(xd, yd, fNumPoints);
        double rnd = Math.random();
        polygon = new Point3d[fNumPoints];
        linearKeyFrames = new KBKeyFrame[fNumPoints];
        for (int j=0;j<fNumPoints;j++)
        {
          polygon[j] = new Point3d(polys.xpoints[j]/100.0,polys.ypoints[j]/100.0,0.0);

          // Prepare linear keyframe data
          float knot = ((float)j/(float)(fNumPoints-1));
          linearKeyFrames[j] = new KBKeyFrame(knot, 1, new Point3f(polygon[j]), 0.0f, 0.0f, 0.0f, new Point3f(1.0f, 1.0f, 1.0f), 0.0f, 0.0f, 0.0f); 
        }

        int[] stripCounts= { fNumPoints };

        LineStripArray calle = new LineStripArray(fNumPoints,LineStripArray.COORDINATES, stripCounts);
        calle.setCoordinates(0,polygon);
        Shape3D shape = new Shape3D(calle);
        scene.addChild(shape);
      }
          // Interpolator

        // Create transforms such that all objects appears in the scene
        Transform3D sceneTransform = new Transform3D();
        //sceneTransform.setScale(0.14f);
        //Transform3D yrot = new Transform3D(); 
        //yrot.rotY(-Math.PI/5.0d);
        //sceneTransform.mul(yrot);
        TransformGroup sceneTransformGroup = new TransformGroup(sceneTransform);
        sceneTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        sceneTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    
        Transform3D objTransform = new Transform3D();
        TransformGroup objTransformGroup = new TransformGroup(objTransform);
        objTransformGroup.setCapability(TransformGroup.ALLOW_PICKABLE_WRITE);

        Transform3D yAxis = new Transform3D();
        Alpha animAlpha = new Alpha (-1,Alpha.INCREASING_ENABLE,0,0,duration,0,0,0,0,0);
        KBRotPosScaleSplinePathInterpolator linearInterpolator = new KBRotPosScaleSplinePathInterpolator(animAlpha, objTransformGroup, yAxis, linearKeyFrames);
        linearInterpolator.setSchedulingBounds(bounds);

        Color3f coneColor  = new Color3f(0.9f, 0.1f, 0.1f);
        Color3f eColor     = new Color3f(0.0f, 0.0f, 0.0f);
        Color3f sColor     = new Color3f(1.0f, 1.0f, 1.0f);
        Material m = new Material(coneColor, eColor, coneColor, sColor, 100.0f);
        Appearance a = new Appearance();
        m.setLightingEnable(true);
        a.setMaterial(m);
        Sphere bondi = new Sphere(0.1f); 
        bondi.setAppearance(a);
        objTransformGroup.addChild(bondi);
        sceneTransformGroup.addChild(objTransformGroup);
        sceneTransformGroup.addChild(linearInterpolator);

        scene.addChild(sceneTransformGroup);
        // Fin Interpolator

    } catch (SQLException x) 
    {
      Advisor.printException(x);
    }
  }

}