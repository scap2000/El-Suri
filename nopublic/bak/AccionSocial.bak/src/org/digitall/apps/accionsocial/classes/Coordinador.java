package org.digitall.apps.accionsocial.classes;

import org.digitall.apps.accionsocial.interfaces.AccionSocialPrincipal;
import org.digitall.apps.accionsocial.interfaces.PanelAgregarBeneficiario;
import org.digitall.apps.accionsocial.interfaces.PanelBusquedaPersona;
import org.digitall.apps.accionsocial.interfaces.PanelRegistrarEntregaRecurso;
import org.digitall.apps.accionsocial.interfaces.PersonasMgmt;
import org.digitall.lib.components.Advisor;
import org.digitall.lib.components.basic.BasicPanel;
import org.digitall.lib.components.basic.ExtendedInternalFrame;

public class Coordinador {
    
    private int paso = 0;
    private int opcion = 0;
    private int subOpcion = 0;
    public int planSocialSeleccionado = 0;// (Matias): Esto creo que no se utiliza y no debería utilizarse
    private boolean preguntarDireccion = true;
    private boolean continuar = true;
    
    /**
     * Paneles que se utilizarán para la administración de planes sociales
     */
    private AccionSocialPrincipal accionSocialMgmt;
    private PanelBusquedaPersona pBusquedaPersona = new PanelBusquedaPersona(this);
    private PanelRegistrarEntregaRecurso pRegistrarEntregaRecurso = new PanelRegistrarEntregaRecurso(this); 
    private PanelAgregarBeneficiario pAgregarBeneficiario = new PanelAgregarBeneficiario(this); 
    private PersonasMgmt personasMgmt;
    
    /**
     * Clases que se utilizarán para la administración de planes sociales
     */
    
    private Persona persona = new Persona();
    private Entrega entrega = new Entrega();
    private PlanSocial planSocial = new PlanSocial();
    private Prestacion prestacion = new Prestacion();
    private Beneficiario beneficiario = new Beneficiario();
    private Persona tutor = new Persona();
    
    public Coordinador() {
        super();
    }
    
    public void start() {
        paso = 1;
        addPaneles();
        //pBusquedaPersona.setPersona(Persona);
        setPanelActivo(pBusquedaPersona);
        pBusquedaPersona.iniciarPanel();
        permitirAvance(false);
        permitirRetroceso(false);
        permitirInicio(false);
    }
    
    public void setPanelActivo(BasicPanel _panel) {
        setVisibilidadPaneles(false);
        accionSocialMgmt.setPanelCental(_panel);
        setCabecera();
    }
    
    /**
     * Todos los paneles que maneje el coordinador son agregados al panel central
     */
    private void addPaneles() {
        setVisibilidadPaneles(false);
        accionSocialMgmt.addPanelCental(pBusquedaPersona);
        accionSocialMgmt.addPanelCental(pRegistrarEntregaRecurso);
        accionSocialMgmt.addPanelCental(pAgregarBeneficiario);
        accionSocialMgmt.repaint();
    }
    
    /**
     * A todos los paneles que maneje el coordinador se los setea visible o no según el parámetro
     * @param _visibles
     */
    private void setVisibilidadPaneles(boolean _visibles) {
        pBusquedaPersona.setVisible(_visibles);
        pRegistrarEntregaRecurso.setVisible(_visibles);
        pAgregarBeneficiario.setVisible(_visibles);
    }
    
    public void siguiente() {
        //System.out.println("PASO " + paso + " - OPCION " +opcion );
        switch (paso) {
            case 1: {
                    paso = 2;
                    switch (opcion) {
                        case 0:{// consulta si la persona tiene o no planes sociales
                            paso = 1;
                            if ( persona.tienePlanesSociales() ) {
                                opcion = 1;
                            } else {
                                opcion = 2;
                            }
                            siguiente();
                        }
                        break;
                        case 1:{ // tiene plan social
                                if (Advisor.question("Mensaje", "La dirección actual es:\n " + persona.getDomicilio() + ".\n ¿Desea cambiarla?")) {
                                    continuar = false;
                                    paso = 1;
                                    opcion = 0;
                                    ExtendedInternalFrame personasMgmtContainer = new ExtendedInternalFrame("Agregar/Modificar");
                                    personasMgmt = new PersonasMgmt(this);
                                    personasMgmtContainer.setCentralPanel(personasMgmt);
                                    personasMgmtContainer.show();
                                    personasMgmt.setPersona(persona);
                                } else {
                                    opcion = 1;
                                    permitirAvance(false);
                                    permitirRetroceso(true);
                                    permitirInicio(true);
                                    setPanelActivo(pRegistrarEntregaRecurso);
                                    pRegistrarEntregaRecurso.iniciarPanel();    
                                }
                        }
                        break;
                        case 2:{//no tiene plan social
                            if (preguntarDireccion) {
                                if (Advisor.question("Mensaje", "La dirección actual es:\n " + persona.getDomicilio() + ".\n ¿Desea cambiarla?")) {
                                    continuar = false;
                                    paso = 1;
                                    opcion = 0;
                                    ExtendedInternalFrame personasMgmtContainer = new ExtendedInternalFrame("Agregar/Modificar");
                                    personasMgmt = new PersonasMgmt(this);
                                    personasMgmt.setPersona(persona);
                                    personasMgmtContainer.setCentralPanel(personasMgmt);
                                    personasMgmtContainer.show();
                                } else {
                                    continuar = true;
                                }
                            } else {
                                continuar = true;
                            }
                            if (continuar) {
                                opcion = 2;
                                permitirAvance(false);
                                permitirRetroceso(true);
                                permitirInicio(true);
                                setPanelActivo(pAgregarBeneficiario);
                                pAgregarBeneficiario.iniciarPanel();    
                            }
                        }
                        break;
                default:
                }
            }
                break;
            case 2: {// PanelRegistrarEntregaRecurso
                paso = 3;
                opcion = 2;
                permitirAvance(false);
                permitirRetroceso(true);
                permitirInicio(true);
                setPanelActivo(pAgregarBeneficiario);
                pAgregarBeneficiario.iniciarPanel();
            }
                break;
        default:
        }
    }

    
    public void atras() {
        //System.out.println("PASO " + paso + " - OPCION " +opcion );
        switch (paso) {
            case 2:{
                opcion = 0;
                paso = 1;
                clearBeneficiario();
                clearPersona();
                permitirAvance(false);
                permitirRetroceso(false);
                permitirInicio(false);
                preguntarDireccion = true;
                setPanelActivo(pBusquedaPersona);
                pBusquedaPersona.iniciarPanel();
            }break;
            case 3:{
                paso = 2;
                opcion = 1;
                permitirAvance(false);
                permitirRetroceso(true);
                permitirInicio(true);
                setPanelActivo(pRegistrarEntregaRecurso);
                pRegistrarEntregaRecurso.iniciarPanel();   
            }break;
        }
    }
    
    public void inicio() {
        paso = 1;
        opcion = 0;
        subOpcion = 0;
        preguntarDireccion = true;
        persona.cargarse(persona.getIdPersona());//Esto lo hago por las dudas despues lo veo con mas detalle
        setPanelActivo(pBusquedaPersona);
        pBusquedaPersona.iniciarPanel();
        permitirAvance(true);
        permitirRetroceso(false);
        permitirInicio(false);
    }
    
    public void clearPersona() {
        persona = new Persona();
        setCabecera();
    }
    
    public void clearBeneficiario() {
        beneficiario = new Beneficiario();
    }
    
    public void setCabecera() {
        accionSocialMgmt.gettfBeneficiario().setValue(persona.getApellidos() + " " + persona.getNombres());
        accionSocialMgmt.gettfCuil().setValue(persona.getCuil());
        //accionSocialMgmt.getTfDniBeneficiario().setValue(persona.getNroDocumento());
        //accionSocialMgmt.gettfDniTutor().setValue(beneficiario.getNumeroDocumentoTutor());
        //accionSocialMgmt.gettfTutor().setValue(beneficiario.getTutor());
    }
    
    public void setSubOpcion(int subOpcion) {
        this.subOpcion = subOpcion;
    }

    public int getSubOpcion() {
        return subOpcion;
    }

    public void setPaso(int paso) {
        this.paso = paso;
    }

    public int getPaso() {
        return paso;
    }
    
    public void permitirAvance(boolean _permiso) {
        accionSocialMgmt.getBtnNext().setEnabled(_permiso);
    }
    
    public void permitirRetroceso(boolean _permiso) {
        accionSocialMgmt.getBtnBack().setEnabled(_permiso);
    }
    
    public void permitirInicio(boolean _permiso) {
        accionSocialMgmt.getBtnFirst().setEnabled(_permiso);
    }
    
    public void setAccionSocialMgmt(AccionSocialPrincipal _accionSocialMgmt) {
        accionSocialMgmt = _accionSocialMgmt;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }

    public int getOpcion() {
        return opcion;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setPlanSocial(PlanSocial planSocial) {
        this.planSocial = planSocial;
    }

    public PlanSocial getPlanSocial() {
        return planSocial;
    }

    public void setPrestacion(Prestacion prestacion) {
        this.prestacion = prestacion;
    }

    public Prestacion getPrestacion() {
        return prestacion;
    }

    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public PersonasMgmt getPersonasMgmt() {
        return personasMgmt;
    }

    public void setTutor(Persona tutor) {
        this.tutor = tutor;
    }

    public Persona getTutor() {
        return tutor;
    }

    public void setPreguntarDireccion(boolean preguntarDireccion) {
        this.preguntarDireccion = preguntarDireccion;
    }

    public boolean isPreguntarDireccion() {
        return preguntarDireccion;
    }

    public void setContinuar(boolean continuar) {
        this.continuar = continuar;
    }

    public boolean isContinuar() {
        return continuar;
    }
}
