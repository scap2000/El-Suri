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
 * IconTypes.java
 *
 * */
package org.digitall.lib.icons;

import java.awt.Image;

import javax.swing.ImageIcon;

import org.digitall.lib.components.Advisor;
import org.digitall.lib.resources.ResourcesManager;

//

public class IconTypes {

    /************************************************ MAIN ICONS *******************************************/
    /**
    private static final String SPLASH_BACK_IMAGE = "iconos/fondo-sermaq-dig.jpg";
    public static final ImageIcon SPLASH_BACK_ICON = getIcon(SPLASH_BACK_IMAGE);
    private static final String HEADER_IMAGE = "iconos/sermaq-logo.jpg";
    public static final ImageIcon HEADER_ICON = getIcon(HEADER_IMAGE);
    */
    
    private static final String ORGANIZATION_LEFT_LOGO = "iconos/org/leftlogo.png";
    public static final ImageIcon organization_left_logo = getIcon(ORGANIZATION_LEFT_LOGO);
    
    private static final String ORGANIZATION_RIGHT_LOGO = "iconos/org/rightlogo.png";
    public static final ImageIcon organization_right_logo = getIcon(ORGANIZATION_RIGHT_LOGO);
    
    /*private static final String ORGANIZATION_TRANSPARENCY_LOGO = "iconos/org/transparencylogo.png";
    public static final ImageIcon organization_transparency_logo = getIcon(ORGANIZATION_TRANSPARENCY_LOGO);
*/
    private static final String SPLASH_BACKGROUND = "iconos/ui/splash/background.png";
    public static final ImageIcon splash_background = getIcon(SPLASH_BACKGROUND);
    private static final String LOGIN_BACKGROUND = "iconos/ui/login/background.png";
    public static final ImageIcon login_background = getIcon(LOGIN_BACKGROUND);
    private static final String LOGIN_ACCEPT = "iconos/ui/login/accept.png";
    public static final ImageIcon login_accept = getIcon(LOGIN_ACCEPT);
    private static final String LOGIN_ACCEPT_RO = "iconos/ui/login/accept_ro.png";
    public static final ImageIcon login_accept_ro = getIcon(LOGIN_ACCEPT_RO);
    private static final String LOGIN_ACCEPT_PR = "iconos/ui/login/accept_pr.png";
    public static final ImageIcon login_accept_pr = getIcon(LOGIN_ACCEPT_PR);
    private static final String LOGIN_CANCEL = "iconos/ui/login/cancel.png";
    public static final ImageIcon login_cancel = getIcon(LOGIN_CANCEL);
    private static final String LOGIN_CANCEL_RO = "iconos/ui/login/cancel_ro.png";
    public static final ImageIcon login_cancel_ro = getIcon(LOGIN_CANCEL_RO);
    private static final String LOGIN_CANCEL_PR = "iconos/ui/login/cancel_pr.png";
    public static final ImageIcon login_cancel_pr = getIcon(LOGIN_CANCEL_PR);
    private static final String LOGIN_LANGUAGE = "iconos/ui/login/language.png";
    public static final ImageIcon login_language = getIcon(LOGIN_LANGUAGE);
    private static final String LOGIN_OPTIONS = "iconos/ui/login/options.png";
    public static final ImageIcon login_options = getIcon(LOGIN_OPTIONS);
    private static final String DESKTOP_TOP_LEFT = "iconos/ui/basicdesktop/top_left.png";
    public static final ImageIcon desktop_top_left = getIcon(DESKTOP_TOP_LEFT);
    private static final String DESKTOP_BOTTOM_LEFT = "iconos/ui/basicdesktop/bottom_left.png";
    public static final ImageIcon desktop_bottom_left = getIcon(DESKTOP_BOTTOM_LEFT);
    private static final String DESKTOP_TOP_RIGHT = "iconos/ui/basicdesktop/top_right.png";
    public static final ImageIcon desktop_top_right = getIcon(DESKTOP_TOP_RIGHT);
    private static final String LOGIN_USER_IMAGE = "iconos/22x22/sesion01.gif";
    public static final ImageIcon LOGIN_USER_ICON = getIcon(LOGIN_USER_IMAGE);
    private static final String LOGIN_PWD_IMAGE = "iconos/22x22/sesion02.gif";
    public static final ImageIcon LOGIN_PWD_ICON = getIcon(LOGIN_PWD_IMAGE);
    private static final String DIGITALL_LOGO_IMAGE = "iconos/digitall-logo4.jpg";
    public static final ImageIcon DIGITALL_LOGO_ICON = getIcon(DIGITALL_LOGO_IMAGE);
    private static final String RDBMS_IMAGE = "iconos/32x32/server.png";
    public static final ImageIcon RDBMS_ICON = getIcon(RDBMS_IMAGE);
    /*************************************************************************************************************/
    /************************************************ PATH ICONS 16x16 *******************************************/
    private static final String STATUSBAR16x16 = "iconos/gears.gif";
    private static final String CANCELBUTTON16x16 = "iconos/16x16/save_cancel.png";
    private static final String EDITBUTTON16x16 = "iconos/16x16/mod.gif";
    private static final String ADDMOREBUTTON16x16 = "iconos/16x16/addmore.png";
    private static final String APPLICATIONBUTTON16x16 = "iconos/16x16/application.png";
    private static final String GOBUTTON16x16 = "iconos/16x16/go.png";
    private static final String NEXTWIZARD16x16 = "iconos/16x16/next.png";
    private static final String PREVIOUSWIZARD16x16 = "iconos/16x16/previous.png";
    private static final String LANGUAGE16x16 = "iconos/16x16/Language.png";
    private static final String CALENDAR_LOGO16x16 = "iconos/16x16/calendar.png";
    private static final String SYSTEM16x16 = "iconos/16x16/system.png";
    private static final String FILES16x16 = "iconos/16x16/Files.png";
    private static final String FILESCONFIG16x16 = "iconos/16x16/FileConfig.png";
    private static final String STEPOFF16x16 = "iconos/16x16/step-off.png";
    private static final String STEPON16x16 = "iconos/16x16/step-on.png";
    private static final String STEPINCOMPLETE16x16 = "iconos/16x16/incomplete.png";
    private static final String STEPREJECTED16x16 = "iconos/16x16/rejected.png";
    private static final String STEPNOMADE16x16 = "iconos/16x16/no-made.png";
    private static final String STEPAPPLY16x16 = "iconos/22x22/apply.png";
    private static final String PRINT_IMAGE_16x16 = "iconos/16x16/imprimir.gif";
    public static final ImageIcon PRINT_ICON_16x16 = getIcon(PRINT_IMAGE_16x16);
    /*************************************************************************************************************/
    /************************************************ PATH ICONS 22x22 *******************************************/
    private static final String REPORT22x22 = "iconos/22x22/report.png";
    private static final String LANGUAGE22x22 = "iconos/22x22/Language.png";
    private static final String FILES22x22 = "iconos/22x22/Files.png";
    private static final String FILESCONFIG_OFF22x22 = "iconos/22x22/fileconfig-off.png";
    private static final String FILESCONFIG_ON22x22 = "iconos/22x22/fileconfig-on.png";
    private static final String FILESNEWFAST_OFF22x22 = "iconos/22x22/filenewfast-off.png";
    private static final String FILESWIZARD_OFF22x22 = "iconos/22x22/filewizard-off.png";
    private static final String FILESWIZARD_ON22x22 = "iconos/22x22/filewizard-on.png";
    private static final String FILESNEWFAST_ON22x22 = "iconos/22x22/filenewfast-on.png";
    private static final String CALENDAR_LOGO22x22 = "iconos/22x22/calendar.png";
    /*************************************************************************************************************/
    /************************************************ PATH ICONS 32x32 *******************************************/
    private static final String REPORT32x32 = "iconos/32x32/report.png";
    private static final String LANGUAGE32x32 = "iconos/32x32/Language2.png";
    private static final String CALENDAR_LOGO32x32 = "iconos/32x32/calendar.png";
    /*************************************************************************************************************/
    private static final String ACCEPT_16x16 = "iconos/16x16/accept.png";
    public static final ImageIcon accept_16x16 = getIcon(ACCEPT_16x16);
    private static final String ACCEPT_RO_16x16 = "iconos/16x16/accept_ro.png";
    public static final ImageIcon accept_ro_16x16 = getIcon(ACCEPT_RO_16x16);
    private static final String ACCEPT_PR_16x16 = "iconos/16x16/accept_pr.png";
    public static final ImageIcon accept_pr_16x16 = getIcon(ACCEPT_PR_16x16);
    private static final String ACCEPT_NE_16x16 = "iconos/16x16/accept_ne.png";
    public static final ImageIcon accept_ne_16x16 = getIcon(ACCEPT_NE_16x16);
    private static final String ADD_ITEM_16x16 = "iconos/16x16/additem.png";
    public static final ImageIcon add_item_16x16 = getIcon(ADD_ITEM_16x16);
    private static final String ADD_ITEM_RO_16x16 = "iconos/16x16/additem_ro.png";
    public static final ImageIcon add_item_ro_16x16 = getIcon(ADD_ITEM_RO_16x16);
    private static final String ADD_ITEM_PR_16x16 = "iconos/16x16/additem_pr.png";
    public static final ImageIcon add_item_pr_16x16 = getIcon(ADD_ITEM_PR_16x16);
    private static final String ADD_ITEM_NE_16x16 = "iconos/16x16/additem.png";
    public static final ImageIcon add_item_ne_16x16 = getIcon(ADD_ITEM_NE_16x16);
    private static final String ASSIGN_DOWN_16x16 = "iconos/16x16/assign_dn.png";
    public static final ImageIcon assignDown_16x16 = getIcon(ASSIGN_DOWN_16x16);
    private static final String ASSIGN_DOWN_RO_16x16 = "iconos/16x16/assign_dn_ro.png";
    public static final ImageIcon assignDown_ro_16x16 = getIcon(ASSIGN_DOWN_RO_16x16);
    private static final String ASSIGN_DOWN_PR_16x16 = "iconos/16x16/assign_dn_pr.png";
    public static final ImageIcon assignDown_pr_16x16 = getIcon(ASSIGN_DOWN_PR_16x16);
    private static final String ASSIGN_DOWN_NE_16x16 = "iconos/16x16/assign_dn_ne.png";
    public static final ImageIcon assignDown_ne_16x16 = getIcon(ASSIGN_DOWN_NE_16x16);
    private static final String ASSIGN_RIGHT_16x16 = "iconos/16x16/assign_rt.png";
    public static final ImageIcon assignRight_16x16 = getIcon(ASSIGN_RIGHT_16x16);
    private static final String ASSIGN_RIGHT_RO_16x16 = "iconos/16x16/assign_rt_ro.png";
    public static final ImageIcon assignRight_ro_16x16 = getIcon(ASSIGN_RIGHT_RO_16x16);
    private static final String ASSIGN_RIGHT_PR_16x16 = "iconos/16x16/assign_rt_pr.png";
    public static final ImageIcon assignRight_pr_16x16 = getIcon(ASSIGN_RIGHT_PR_16x16);
    private static final String ASSIGN_RIGHT_NE_16x16 = "iconos/16x16/assign_rt_ne.png";
    public static final ImageIcon assignRight_ne_16x16 = getIcon(ASSIGN_RIGHT_NE_16x16);
    private static final String CANCEL_16x16 = "iconos/16x16/cancel.png";
    public static final ImageIcon cancel_16x16 = getIcon(CANCEL_16x16);
    private static final String CANCEL_RO_16x16 = "iconos/16x16/cancel_ro.png";
    public static final ImageIcon cancel_ro_16x16 = getIcon(CANCEL_RO_16x16);
    private static final String CANCEL_PR_16x16 = "iconos/16x16/cancel_pr.png";
    public static final ImageIcon cancel_pr_16x16 = getIcon(CANCEL_PR_16x16);
    private static final String CANCEL_NE_16x16 = "iconos/16x16/cancel_ne.png";
    public static final ImageIcon cancel_ne_16x16 = getIcon(CANCEL_NE_16x16);
    private static final String CLOSE_16x16 = "iconos/16x16/close.png";
    public static final ImageIcon close_16x16 = getIcon(CLOSE_16x16);
    private static final String CLOSE_RO_16x16 = "iconos/16x16/close_ro.png";
    public static final ImageIcon close_ro_16x16 = getIcon(CLOSE_RO_16x16);
    private static final String CLOSE_PR_16x16 = "iconos/16x16/close_pr.png";
    public static final ImageIcon close_pr_16x16 = getIcon(CLOSE_PR_16x16);
    private static final String CLOSE_NE_16x16 = "iconos/16x16/close_ne.png";
    public static final ImageIcon close_ne_16x16 = getIcon(CLOSE_NE_16x16);
    private static final String DELETE_16x16 = "iconos/16x16/delete.png";
    public static final ImageIcon delete_16x16 = getIcon(DELETE_16x16);
    private static final String DELETE_RO_16x16 = "iconos/16x16/delete_ro.png";
    public static final ImageIcon delete_ro_16x16 = getIcon(DELETE_RO_16x16);
    private static final String DELETE_PR_16x16 = "iconos/16x16/delete_pr.png";
    public static final ImageIcon delete_pr_16x16 = getIcon(DELETE_PR_16x16);
    private static final String DELETE_NE_16x16 = "iconos/16x16/delete_ne.png";
    public static final ImageIcon delete_ne_16x16 = getIcon(DELETE_NE_16x16);
    private static final String FIND_16x16 = "iconos/16x16/find.png";
    public static final ImageIcon find_16x16 = getIcon(FIND_16x16);
    private static final String FIND_RO_16x16 = "iconos/16x16/find_ro.png";
    public static final ImageIcon find_ro_16x16 = getIcon(FIND_RO_16x16);
    private static final String FIND_PR_16x16 = "iconos/16x16/find_pr.png";
    public static final ImageIcon find_pr_16x16 = getIcon(FIND_PR_16x16);
    private static final String FIND_NE_16x16 = "iconos/16x16/find_ne.png";
    public static final ImageIcon find_ne_16x16 = getIcon(FIND_NE_16x16);
    private static final String LOCK_16x16 = "iconos/16x16/lock.png";
    public static final ImageIcon lock_16x16 = getIcon(LOCK_16x16);
    private static final String LOCK_RO_16x16 = "iconos/16x16/lock_ro.png";
    public static final ImageIcon lock_ro_16x16 = getIcon(LOCK_RO_16x16);
    private static final String LOCK_PR_16x16 = "iconos/16x16/lock_pr.png";
    public static final ImageIcon lock_pr_16x16 = getIcon(LOCK_PR_16x16);
    private static final String LOCK_NE_16x16 = "iconos/16x16/lock_ne.png";
    public static final ImageIcon lock_ne_16x16 = getIcon(LOCK_NE_16x16);
    private static final String NEXT_16x16 = "iconos/16x16/next.png";
    public static final ImageIcon next_16x16 = getIcon(NEXT_16x16);
    private static final String NEXT_RO_16x16 = "iconos/16x16/next_ro.png";
    public static final ImageIcon next_ro_16x16 = getIcon(NEXT_RO_16x16);
    private static final String NEXT_PR_16x16 = "iconos/16x16/next_pr.png";
    public static final ImageIcon next_pr_16x16 = getIcon(NEXT_PR_16x16);
    private static final String NEXT_NE_16x16 = "iconos/16x16/next_ne.png";
    public static final ImageIcon next_ne_16x16 = getIcon(NEXT_NE_16x16);
    private static final String PREVIOUS_16x16 = "iconos/16x16/previous.png";
    public static final ImageIcon back_16x16 = getIcon(PREVIOUS_16x16);
    private static final String PREVIOUS_RO_16x16 = "iconos/16x16/previous_ro.png";
    public static final ImageIcon back_ro_16x16 = getIcon(PREVIOUS_RO_16x16);
    private static final String PREVIOUS_PR_16x16 = "iconos/16x16/previous_pr.png";
    public static final ImageIcon back_pr_16x16 = getIcon(PREVIOUS_PR_16x16);
    private static final String PREVIOUS_NE_16x16 = "iconos/16x16/previous_ne.png";
    public static final ImageIcon back_ne_16x16 = getIcon(PREVIOUS_NE_16x16);
    private static final String LAST_16x16 = "iconos/16x16/last.png";
    public static final ImageIcon last_16x16 = getIcon(LAST_16x16);
    private static final String LAST_RO_16x16 = "iconos/16x16/last_ro.png";
    public static final ImageIcon last_ro_16x16 = getIcon(LAST_RO_16x16);
    private static final String LAST_PR_16x16 = "iconos/16x16/last_pr.png";
    public static final ImageIcon last_pr_16x16 = getIcon(LAST_PR_16x16);
    private static final String LAST_NE_16x16 = "iconos/16x16/last_ne.png";
    public static final ImageIcon last_ne_16x16 = getIcon(LAST_NE_16x16);
    private static final String FIRST_16x16 = "iconos/16x16/first.png";
    public static final ImageIcon first_16x16 = getIcon(FIRST_16x16);
    private static final String FIRST_RO_16x16 = "iconos/16x16/first_ro.png";
    public static final ImageIcon first_ro_16x16 = getIcon(FIRST_RO_16x16);
    private static final String FIRST_PR_16x16 = "iconos/16x16/first_pr.png";
    public static final ImageIcon first_pr_16x16 = getIcon(FIRST_PR_16x16);
    private static final String FIRST_NE_16x16 = "iconos/16x16/first_ne.png";
    public static final ImageIcon first_ne_16x16 = getIcon(FIRST_NE_16x16);
    private static final String REFRESH_16x16 = "iconos/16x16/refresh.png";
    public static final ImageIcon refresh_16x16 = getIcon(REFRESH_16x16);
    private static final String REFRESH_RO_16x16 = "iconos/16x16/refresh_ro.png";
    public static final ImageIcon refresh_ro_16x16 = getIcon(REFRESH_RO_16x16);
    private static final String REFRESH_PR_16x16 = "iconos/16x16/refresh_pr.png";
    public static final ImageIcon refresh_pr_16x16 = getIcon(REFRESH_PR_16x16);
    private static final String REFRESH_NE_16x16 = "iconos/16x16/refresh_ne.png";
    public static final ImageIcon refresh_ne_16x16 = getIcon(REFRESH_NE_16x16);
    private static final String RELOAD_16x16 = "iconos/16x16/reload.png";
    public static final ImageIcon reload_16x16 = getIcon(RELOAD_16x16);
    private static final String RELOAD_RO_16x16 = "iconos/16x16/reload_ro.png";
    public static final ImageIcon reload_ro_16x16 = getIcon(RELOAD_RO_16x16);
    private static final String RELOAD_PR_16x16 = "iconos/16x16/reload_pr.png";
    public static final ImageIcon reload_pr_16x16 = getIcon(RELOAD_PR_16x16);
    private static final String RELOAD_NE_16x16 = "iconos/16x16/reload_ne.png";
    public static final ImageIcon reload_ne_16x16 = getIcon(RELOAD_NE_16x16);
    private static final String MODIFY_16x16 = "iconos/16x16/modify.png";
    public static final ImageIcon modify_16x16 = getIcon(MODIFY_16x16);
    private static final String MODIFY_RO_16x16 = "iconos/16x16/modify_ro.png";
    public static final ImageIcon modify_ro_16x16 = getIcon(MODIFY_RO_16x16);
    private static final String MODIFY_PR_16x16 = "iconos/16x16/modify_pr.png";
    public static final ImageIcon modify_pr_16x16 = getIcon(MODIFY_PR_16x16);
    private static final String MODIFY_NE_16x16 = "iconos/16x16/modify_ne.png";
    public static final ImageIcon modify_ne_16x16 = getIcon(MODIFY_NE_16x16);
    private static final String NEW_16x16 = "iconos/16x16/new.png";
    public static final ImageIcon new_16x16 = getIcon(NEW_16x16);
    private static final String NEW_RO_16x16 = "iconos/16x16/new_ro.png";
    public static final ImageIcon new_ro_16x16 = getIcon(NEW_RO_16x16);
    private static final String NEW_PR_16x16 = "iconos/16x16/new_pr.png";
    public static final ImageIcon new_pr_16x16 = getIcon(NEW_PR_16x16);
    private static final String NEW_NE_16x16 = "iconos/16x16/new_ne.png";
    public static final ImageIcon new_ne_16x16 = getIcon(NEW_NE_16x16);
    private static final String OK_16x16 = "iconos/16x16/ok.png";
    public static final ImageIcon ok_16x16 = getIcon(OK_16x16);
    private static final String OK_RO_16x16 = "iconos/16x16/ok_ro.png";
    public static final ImageIcon ok_ro_16x16 = getIcon(OK_RO_16x16);
    private static final String OK_PR_16x16 = "iconos/16x16/ok_pr.png";
    public static final ImageIcon ok_pr_16x16 = getIcon(OK_PR_16x16);
    private static final String OK_NE_16x16 = "iconos/16x16/ok_ne.png";
    public static final ImageIcon ok_ne_16x16 = getIcon(OK_NE_16x16);
    private static final String REPORT_16x16 = "iconos/16x16/report.png";
    public static final ImageIcon report_16x16 = getIcon(REPORT_16x16);
    private static final String REPORT_RO_16x16 = "iconos/16x16/report_ro.png";
    public static final ImageIcon report_ro_16x16 = getIcon(REPORT_RO_16x16);
    private static final String REPORT_PR_16x16 = "iconos/16x16/report_pr.png";
    public static final ImageIcon report_pr_16x16 = getIcon(REPORT_PR_16x16);
    private static final String REPORT_NE_16x16 = "iconos/16x16/report_ne.png";
    public static final ImageIcon report_ne_16x16 = getIcon(REPORT_NE_16x16);
    private static final String PRINT_16x16 = "iconos/16x16/print.png";
    public static final ImageIcon print_16x16 = getIcon(PRINT_16x16);
    private static final String PRINT_RO_16x16 = "iconos/16x16/print_ro.png";
    public static final ImageIcon print_ro_16x16 = getIcon(PRINT_RO_16x16);
    private static final String PRINT_PR_16x16 = "iconos/16x16/print_pr.png";
    public static final ImageIcon print_pr_16x16 = getIcon(PRINT_PR_16x16);
    private static final String PRINT_NE_16x16 = "iconos/16x16/print_ne.png";
    public static final ImageIcon print_ne_16x16 = getIcon(PRINT_NE_16x16);
    private static final String PRINT_SAVE_16x16 = "iconos/16x16/print_save.png";
    public static final ImageIcon print_save_16x16 = getIcon(PRINT_SAVE_16x16);
    private static final String PRINT_SAVE_RO_16x16 = "iconos/16x16/print_save_ro.png";
    public static final ImageIcon print_save_ro_16x16 = getIcon(PRINT_SAVE_RO_16x16);
    private static final String PRINT_SAVE_PR_16x16 = "iconos/16x16/print_save_pr.png";
    public static final ImageIcon print_save_pr_16x16 = getIcon(PRINT_SAVE_PR_16x16);
    private static final String PRINT_SAVE_NE_16x16 = "iconos/16x16/print_save_ne.png";
    public static final ImageIcon print_save_ne_16x16 = getIcon(PRINT_SAVE_NE_16x16);
    private static final String UNASSIGN_LEFT_16x16 = "iconos/16x16/unassign_lt.png";
    public static final ImageIcon unAssign_left_16x16 = getIcon(UNASSIGN_LEFT_16x16);
    private static final String UNASSIGN_LEFT_RO_16x16 = "iconos/16x16/unassign_lt_ro.png";
    public static final ImageIcon unAssign_left_ro_16x16 = getIcon(UNASSIGN_LEFT_RO_16x16);
    private static final String UNASSIGN_LEFT_PR_16x16 = "iconos/16x16/unassign_lt_pr.png";
    public static final ImageIcon unAssign_left_pr_16x16 = getIcon(UNASSIGN_LEFT_PR_16x16);
    private static final String UNASSIGN_LEFT_NE_16x16 = "iconos/16x16/unassign_lt_ne.png";
    public static final ImageIcon unAssign_left_ne_16x16 = getIcon(UNASSIGN_LEFT_NE_16x16);
    private static final String UNASSIGN_UP_16x16 = "iconos/16x16/unassign_up.png";
    public static final ImageIcon unAssign_up_16x16 = getIcon(UNASSIGN_UP_16x16);
    private static final String UNASSIGN_UP_RO_16x16 = "iconos/16x16/unassign_up_ro.png";
    public static final ImageIcon unAssign_up_ro_16x16 = getIcon(UNASSIGN_UP_RO_16x16);
    private static final String UNASSIGN_UP_PR_16x16 = "iconos/16x16/unassign_up_pr.png";
    public static final ImageIcon unAssign_up_pr_16x16 = getIcon(UNASSIGN_UP_PR_16x16);
    private static final String UNASSIGN_UP_NE_16x16 = "iconos/16x16/unassign_up_ne.png";
    public static final ImageIcon unAssign_up_ne_16x16 = getIcon(UNASSIGN_UP_NE_16x16);
    private static final String SAVE_16x16 = "iconos/16x16/save.png";
    public static final ImageIcon save_16x16 = getIcon(SAVE_16x16);
    private static final String SAVE_RO_16x16 = "iconos/16x16/save_ro.png";
    public static final ImageIcon save_ro_16x16 = getIcon(SAVE_RO_16x16);
    private static final String SAVE_PR_16x16 = "iconos/16x16/save_pr.png";
    public static final ImageIcon save_pr_16x16 = getIcon(SAVE_PR_16x16);
    private static final String SAVE_NE_16x16 = "iconos/16x16/save_ne.png";
    public static final ImageIcon save_ne_16x16 = getIcon(SAVE_NE_16x16);
    private static final String DONTSAVE_16x16 = "iconos/16x16/dontsave.png";
    public static final ImageIcon dontsave_16x16 = getIcon(DONTSAVE_16x16);
    private static final String DONTSAVE_RO_16x16 = "iconos/16x16/dontsave_ro.png";
    public static final ImageIcon dontsave_ro_16x16 = getIcon(DONTSAVE_RO_16x16);
    private static final String DONTSAVE_PR_16x16 = "iconos/16x16/dontsave_pr.png";
    public static final ImageIcon dontsave_pr_16x16 = getIcon(DONTSAVE_PR_16x16);
    private static final String DONTSAVE_NE_16x16 = "iconos/16x16/dontsave_ne.png";
    public static final ImageIcon dontsave_ne_16x16 = getIcon(DONTSAVE_NE_16x16);
    /**UI Icons*/
    private static final String RADIO_16x16 = "iconos/ui/radiobutton/rbtn.png";
    public static final ImageIcon radio_16x16 = getIcon(RADIO_16x16);
    private static final String RADIO_RO_16x16 = "iconos/ui/radiobutton/rbtn_ro.png";
    public static final ImageIcon radio_ro_16x16 = getIcon(RADIO_RO_16x16);
    private static final String RADIO_NE_16x16 = "iconos/ui/radiobutton/rbtn_ne.png";
    public static final ImageIcon radio_ne_16x16 = getIcon(RADIO_NE_16x16);
    private static final String RADIO_SEL_16x16 = "iconos/ui/radiobutton/rbtn_sel.png";
    public static final ImageIcon radio_sel_16x16 = getIcon(RADIO_SEL_16x16);
    private static final String RADIO_SEL_NE_16x16 = "iconos/ui/radiobutton/rbtn_sel_ne.png";
    public static final ImageIcon radio_sel_ne_16x16 = getIcon(RADIO_SEL_NE_16x16);
    private static final String RADIO_SEL_PR_16x16 = "iconos/ui/radiobutton/rbtn_sel_pr.png";
    public static final ImageIcon radio_sel_pr_16x16 = getIcon(RADIO_SEL_PR_16x16);
    private static final String RADIO_SEL_RO_16x16 = "iconos/ui/radiobutton/rbtn_sel_ro.png";
    public static final ImageIcon radio_sel_ro_16x16 = getIcon(RADIO_SEL_RO_16x16);
    private static final String CHECK_16x16 = "iconos/ui/chkbutton/chkbtn.png";
    public static final ImageIcon check_16x16 = getIcon(CHECK_16x16);
    private static final String CHECK_RO_16x16 = "iconos/ui/chkbutton/chkbtn_ro.png";
    public static final ImageIcon check_ro_16x16 = getIcon(CHECK_RO_16x16);
    private static final String CHECK_NE_16x16 = "iconos/ui/chkbutton/chkbtn_ne.png";
    public static final ImageIcon check_ne_16x16 = getIcon(CHECK_NE_16x16);
    private static final String CHECK_SEL_16x16 = "iconos/ui/chkbutton/chkbtn_sel.png";
    public static final ImageIcon check_sel_16x16 = getIcon(CHECK_SEL_16x16);
    private static final String CHECK_SEL_NE_16x16 = "iconos/ui/chkbutton/chkbtn_sel_ne.png";
    public static final ImageIcon check_sel_ne_16x16 = getIcon(CHECK_SEL_NE_16x16);
    private static final String CHECK_SEL_PR_16x16 = "iconos/ui/chkbutton/chkbtn_sel_pr.png";
    public static final ImageIcon check_sel_pr_16x16 = getIcon(CHECK_SEL_PR_16x16);
    private static final String CHECK_SEL_RO_16x16 = "iconos/ui/chkbutton/chkbtn_sel_ro.png";
    public static final ImageIcon check_sel_ro_16x16 = getIcon(CHECK_SEL_RO_16x16);
    private static final String EXT_IFRAME_CLOSE_22x22 = "iconos/ui/extendedinternalframe/close.png";
    public static final ImageIcon ext_iframe_close_22x22 = getIcon(EXT_IFRAME_CLOSE_22x22);
    private static final String EXT_IFRAME_CLOSE_RO_22x22 = "iconos/ui/extendedinternalframe/close.png";
    public static final ImageIcon ext_iframe_close_ro_22x22 = getIcon(EXT_IFRAME_CLOSE_RO_22x22);
    private static final String EXT_IFRAME_CLOSE_PR_22x22 = "iconos/ui/extendedinternalframe/close.png";
    public static final ImageIcon ext_iframe_close_pr_22x22 = getIcon(EXT_IFRAME_CLOSE_PR_22x22);
    private static final String EXT_IFRAME_ICONIFY_22x22 = "iconos/ui/extendedinternalframe/iconify.png";
    public static final ImageIcon ext_iframe_iconify_22x22 = getIcon(EXT_IFRAME_ICONIFY_22x22);
    private static final String EXT_IFRAME_ICONIFY_RO_22x22 = "iconos/ui/extendedinternalframe/iconify.png";
    public static final ImageIcon ext_iframe_iconify_ro_22x22 = getIcon(EXT_IFRAME_ICONIFY_RO_22x22);
    private static final String EXT_IFRAME_ICONIFY_PR_22x22 = "iconos/ui/extendedinternalframe/iconify.png";
    public static final ImageIcon ext_iframe_iconify_pr_22x22 = getIcon(EXT_IFRAME_ICONIFY_PR_22x22);
    private static final String EXT_IFRAME_DEPLOY_22x22 = "iconos/ui/extendedinternalframe/deploy.png";
    public static final ImageIcon ext_iframe_deploy_22x22 = getIcon(EXT_IFRAME_DEPLOY_22x22);
    private static final String EXT_IFRAME_DEPLOY_RO_22x22 = "iconos/ui/extendedinternalframe/deploy.png";
    public static final ImageIcon ext_iframe_deploy_ro_22x22 = getIcon(EXT_IFRAME_DEPLOY_RO_22x22);
    private static final String EXT_IFRAME_TOP_RIGHT = "iconos/ui/extendedinternalframe/topright.png";
    public static final ImageIcon ext_iframe_top_right = getIcon(EXT_IFRAME_TOP_RIGHT);
    private static final String EXIT_32x32 = "iconos/ui/general/exit.png";
    public static final ImageIcon exit_32x32 = getIcon(EXIT_32x32);
    private static final String EXIT_RO_32x32 = "iconos/ui/general/exit.png";
    public static final ImageIcon exit_ro_32x32 = getIcon(EXIT_RO_32x32);
    private static final String CLOSE_SESSION_32x32 = "iconos/ui/general/close_session.png";
    public static final ImageIcon close_session_32x32 = getIcon(CLOSE_SESSION_32x32);
    private static final String CLOSE_SESSION_RO_32x32 = "iconos/ui/general/close_session.png";
    public static final ImageIcon close_session_ro_32x32 = getIcon(CLOSE_SESSION_RO_32x32);
    private static final String WEBMAIL_32x32 = "iconos/ui/general/webmail.png";
    public static final ImageIcon webmail_32x32 = getIcon(WEBMAIL_32x32);
    private static final String WEBMAIL_RO_32x32 = "iconos/ui/general/webmail.png";
    public static final ImageIcon webmail_ro_32x32 = getIcon(WEBMAIL_RO_32x32);
    private static final String CHAT_32x32 = "iconos/ui/modules/main/chat.png";
    public static final ImageIcon chat_32x32 = getIcon(CHAT_32x32);
    private static final String CHAT_RO_32x32 = "iconos/ui/modules/main/chat.png";
    public static final ImageIcon chat_ro_32x32 = getIcon(CHAT_RO_32x32);
    private static final String INBOX_32x32 = "iconos/ui/modules/main/inbox.png";
    public static final ImageIcon inbox_32x32 = getIcon(INBOX_32x32);
    private static final String INBOX_RO_32x32 = "iconos/ui/modules/main/inbox.png";
    public static final ImageIcon inbox_ro_32x32 = getIcon(INBOX_RO_32x32);
    private static final String STICKYNOTES_BACKGROUND = "iconos/ui/modules/stickynotes/background.png";
    public static final ImageIcon stickynotes_background = getIcon(STICKYNOTES_BACKGROUND);
    private static final String STICKYNOTES_DELETE = "iconos/ui/modules/stickynotes/delete.png";
    public static final ImageIcon stickynotes_delete = getIcon(STICKYNOTES_DELETE);
    private static final String STICKYNOTES_MINIMIZE = "iconos/ui/modules/stickynotes/minimize.png";
    public static final ImageIcon stickynotes_minimize = getIcon(STICKYNOTES_MINIMIZE);
    private static final String GEOCALC_32x32 = "iconos/ui/general/geocalc.png";
    public static final ImageIcon geocalc_32x32 = getIcon(GEOCALC_32x32);
    private static final String GEOCALC_RO_32x32 = "iconos/ui/general/geocalc.png";
    public static final ImageIcon geocalc_ro_32x32 = getIcon(GEOCALC_RO_32x32);
    private static final String CALENDAR_BG = "iconos/ui/general/calendarbg.png";
    public static final ImageIcon calendar_bg = getIcon(CALENDAR_BG);
    private static final String CALENDAR_HIDE = "iconos/ui/general/calendarhide.png";
    public static final ImageIcon calendar_hide = getIcon(CALENDAR_HIDE);
    private static final String CALENDAR_SHOW = "iconos/ui/general/calendarshow.png";
    public static final ImageIcon calendar_show = getIcon(CALENDAR_SHOW);
    private static final String BUSY = "iconos/ui/general/busy.gif";
    public static final ImageIcon busy = getIcon(BUSY);
    private static final String DICCIONARIO = "iconos/ui/modules/main/diccionario.png";
    public static final ImageIcon diccionario = getIcon(DICCIONARIO);
    private static final String INFO_DEL_SISTEMA = "iconos/ui/modules/main/info_del_sistema.png";
    public static final ImageIcon info_del_sistema = getIcon(INFO_DEL_SISTEMA);
    private static final String SISTEMA_EXPEDIENTES = "iconos/ui/modules/main/sistema_expedientes.png";
    public static final ImageIcon sistema_expedientes = getIcon(SISTEMA_EXPEDIENTES);


    /**MODULES*/
    private static final String ASSETS_32x32 = "iconos/ui/modules/mainpanel/assets.png";
    public static final ImageIcon assets_32x32 = getIcon(ASSETS_32x32);
    private static final String ASSETS_RO_32x32 = "iconos/ui/modules/mainpanel/assets_ro.png";
    public static final ImageIcon assets_ro_32x32 = getIcon(ASSETS_RO_32x32);

    private static final String CASHFLOW_32x32 = "iconos/ui/modules/mainpanel/cashflow.png";
    public static final ImageIcon cashflow_32x32 = getIcon(CASHFLOW_32x32);
    private static final String CASHFLOW_RO_32x32 = "iconos/ui/modules/mainpanel/cashflow_ro.png";
    public static final ImageIcon cashflow_ro_32x32 = getIcon(CASHFLOW_RO_32x32);

    private static final String GAIA_32x32 = "iconos/ui/modules/mainpanel/gaia.png";
    public static final ImageIcon gaia_32x32 = getIcon(GAIA_32x32);
    private static final String GAIA_RO_32x32 = "iconos/ui/modules/mainpanel/gaia_ro.png";
    public static final ImageIcon gaia_ro_32x32 = getIcon(GAIA_RO_32x32);

    private static final String MAIN_32x32 = "iconos/ui/modules/mainpanel/main.png";
    public static final ImageIcon main_32x32 = getIcon(MAIN_32x32);
    private static final String MAIN_RO_32x32 = "iconos/ui/modules/mainpanel/main_ro.png";
    public static final ImageIcon main_ro_32x32 = getIcon(MAIN_RO_32x32);

    private static final String REPORTS_32x32 = "iconos/ui/modules/mainpanel/reports.png";
    public static final ImageIcon reports_32x32 = getIcon(REPORTS_32x32);
    private static final String REPORTS_RO_32x32 = "iconos/ui/modules/mainpanel/reports_ro.png";
    public static final ImageIcon reports_ro_32x32 = getIcon(REPORTS_RO_32x32);

    private static final String RESOURCES_32x32 = "iconos/ui/modules/mainpanel/resources.png";
    public static final ImageIcon resources_32x32 = getIcon(RESOURCES_32x32);
    private static final String RESOURCES_RO_32x32 = "iconos/ui/modules/mainpanel/resources_ro.png";
    public static final ImageIcon resources_ro_32x32 = getIcon(RESOURCES_RO_32x32);

    private static final String STICKYNOTES_32x32 = "iconos/ui/modules/mainpanel/stickynotes.png";
    public static final ImageIcon stickynotes_32x32 = getIcon(STICKYNOTES_32x32);
    private static final String STICKYNOTES_RO_32x32 = "iconos/ui/modules/mainpanel/stickynotes_ro.png";
    public static final ImageIcon stickynotes_ro_32x32 = getIcon(STICKYNOTES_RO_32x32);

    private static final String TAXES_32x32 = "iconos/ui/modules/mainpanel/taxes.png";
    public static final ImageIcon taxes_32x32 = getIcon(TAXES_32x32);
    private static final String TAXES_RO_32x32 = "iconos/ui/modules/mainpanel/taxes_ro.png";
    public static final ImageIcon taxes_ro_32x32 = getIcon(TAXES_RO_32x32);

    /** MAPPER MODULO */
    private static final String MAPPER_ZOOM_EXTENTS = "iconos/ui/modules/mapper/zoomextents.png";
    public static final ImageIcon mapper_zoom_extents = getIcon(MAPPER_ZOOM_EXTENTS);
    private static final String MAPPER_ZOOM_IN = "iconos/ui/modules/mapper/zoomin.png";
    public static final ImageIcon mapper_zoom_in = getIcon(MAPPER_ZOOM_IN);
    private static final String MAPPER_ZOOM_OUT = "iconos/ui/modules/mapper/zoomout.png";
    public static final ImageIcon mapper_zoom_out = getIcon(MAPPER_ZOOM_OUT);
    private static final String MAPPER_RULE = "iconos/ui/modules/mapper/rule.png";
    public static final ImageIcon mapper_rule = getIcon(MAPPER_RULE);
    private static final String MAPPER_QUERY = "iconos/ui/modules/mapper/query.png";
    public static final ImageIcon mapper_query = getIcon(MAPPER_QUERY);
    private static final String MAPPER_LAYER_EDITION = "iconos/ui/modules/mapper/layeredition.png";
    public static final ImageIcon mapper_layer_edition = getIcon(MAPPER_LAYER_EDITION);

    /** TAXES MODULE */
    private static final String ADMINISTRACION_DE_IMPUESTOS = "iconos/ui/modules/taxes/administracion_de_impuestos.png";
    public static final ImageIcon administracion_de_impuestos = getIcon(ADMINISTRACION_DE_IMPUESTOS);
    private static final String ADMINISTRAR_ALQUILERES = "iconos/ui/modules/taxes/administrar_alquileres.png";
    public static final ImageIcon administrar_alquileres = getIcon(ADMINISTRAR_ALQUILERES);
    private static final String ADMINISTRAR_AUTOMOTORES = "iconos/ui/modules/taxes/administrar_automotores.png";
    public static final ImageIcon administrar_automotores = getIcon(ADMINISTRAR_AUTOMOTORES);
    private static final String ADMINISTRAR_CATASTROS = "iconos/ui/modules/taxes/administrar_catastros.png";
    public static final ImageIcon administrar_catastros = getIcon(ADMINISTRAR_CATASTROS);
    private static final String ADMINISTRAR_COMERCIOS = "iconos/ui/modules/taxes/administrar_comercios.png";
    public static final ImageIcon administrar_comercios = getIcon(ADMINISTRAR_COMERCIOS);
    private static final String CAJERO = "iconos/ui/modules/taxes/cajero.png";
    public static final ImageIcon cajero = getIcon(CAJERO);
    private static final String LIMPIAR_ANTICIPOS_ACT_VARIAS = "iconos/ui/modules/taxes/limpiar_anticipos_act_varias.png";
    public static final ImageIcon limpiar_anticipos_act_varias = getIcon(LIMPIAR_ANTICIPOS_ACT_VARIAS);
    private static final String LIMPIAR_ANTICIPOS_AUTOMOTORES = "iconos/ui/modules/taxes/limpiar_anticipos_automotores.png";
    public static final ImageIcon limpiar_anticipos_automotores = getIcon(LIMPIAR_ANTICIPOS_AUTOMOTORES);
    private static final String LIMPIAR_ANTICIPOS_DE_ALQUILERES = "iconos/ui/modules/taxes/limpiar_anticipos_de_alquileres.png";
    public static final ImageIcon limpiar_anticipos_de_alquileres = getIcon(LIMPIAR_ANTICIPOS_DE_ALQUILERES);
    private static final String LIMPIAR_ANTICIPOS_TGS = "iconos/ui/modules/taxes/limpiar_anticipos_tgs.png";
    public static final ImageIcon limpiar_anticipos_tgs = getIcon(LIMPIAR_ANTICIPOS_TGS);
    private static final String REGISTRAR_PAGO_DE_IMPUESTOS  = "iconos/ui/modules/taxes/registrar_pago_de_impuestos.png";
    public static final ImageIcon registrar_pago_de_impuestos = getIcon(REGISTRAR_PAGO_DE_IMPUESTOS);

    /** DRIVING LICENSE */
    private static final String CONDUCTORES  = "iconos/ui/modules/drivinglicenses/conductores.png";
    public static final ImageIcon conductores = getIcon(CONDUCTORES);
    private static final String CONFIGURACION_DE_LICENCIAS  = "iconos/ui/modules/drivinglicenses/configuracion_de_licencias.png";
    public static final ImageIcon configuracion_de_licencias = getIcon(CONFIGURACION_DE_LICENCIAS);
    private static final String RADIOGRAMAS  = "iconos/ui/modules/drivinglicenses/radiogramas.png";
    public static final ImageIcon radiogramas = getIcon(RADIOGRAMAS);

    /**
     * FUNCTION ICONS
     * */
    /**
       * CASHFLOW
       * */
    private static final String ADMINISTRACION_DE_PARTIDAS_PRESUPUESTARIAS = "iconos/ui/modules/cashflow/administracion_de_partidas_presupuestarias.png";
    public static final ImageIcon administracion_de_partidas_presupuestarias = getIcon(ADMINISTRACION_DE_PARTIDAS_PRESUPUESTARIAS);
    private static final String  ASIENTOS = "iconos/ui/modules/cashflow/asientos.png";
    public static final ImageIcon asientos = getIcon(ASIENTOS);
    private static final String  BALANCE_DE_SUMAS_Y_SALDOS = "iconos/ui/modules/cashflow/balance_de_sumas_y_saldos.png";
    public static final ImageIcon balance_de_sumas_y_saldos = getIcon(BALANCE_DE_SUMAS_Y_SALDOS);
    private static final String CENTROS_DE_COSTOS = "iconos/ui/modules/cashflow/centros_de_costos.png";
    public static final ImageIcon centros_de_costos = getIcon(CENTROS_DE_COSTOS);
    private static final String COMPROBANTES = "iconos/ui/modules/cashflow/comprobantes.png";
    public static final ImageIcon comprobantes = getIcon(COMPROBANTES);
    private static final String CUENTAS_BANCARIAS = "iconos/ui/modules/cashflow/cuentas_bancarias.png";
    public static final ImageIcon cuentas_bancarias = getIcon(CUENTAS_BANCARIAS);
    private static final String FACTURAR_NOTAS_DE_RECEPCION = "iconos/ui/modules/cashflow/facturar_notas_de_recepcion.png";
    public static final ImageIcon facturar_notas_de_recepcion = getIcon(FACTURAR_NOTAS_DE_RECEPCION);
    private static final String FACTURAR_ORDEN_DE_PROVISION = "iconos/ui/modules/cashflow/facturar_orden_de_provision.png";
    public static final ImageIcon facturar_orden_de_provision = getIcon(FACTURAR_ORDEN_DE_PROVISION);
    private static final String LIBRO_BANCO = "iconos/ui/modules/cashflow/libro_banco.png";
    public static final ImageIcon libro_banco = getIcon(LIBRO_BANCO);
    private static final String LIBRO_MAYOR = "iconos/ui/modules/cashflow/libro_mayor.png";
    public static final ImageIcon libro_mayor = getIcon(LIBRO_MAYOR);
    private static final String MODELOS_DE_ASIENTOS = "iconos/ui/modules/cashflow/modelos_de_asientos.png";
    public static final ImageIcon modelos_de_asientos = getIcon(MODELOS_DE_ASIENTOS);
    private static final String NOTA_DE_EGRESO = "iconos/ui/modules/cashflow/nota_de_egreso.png";
    public static final ImageIcon nota_de_egreso = getIcon(NOTA_DE_EGRESO);
    private static final String NOTA_DE_INGRESO = "iconos/ui/modules/cashflow/nota_de_ingreso.png";
    public static final ImageIcon nota_de_ingreso = getIcon(NOTA_DE_INGRESO);
    private static final String NUEVA_ORDEN_DE_PAGO = "iconos/ui/modules/cashflow/nueva_orden_de_pago.png";
    public static final ImageIcon nueva_orden_de_pago = getIcon(NUEVA_ORDEN_DE_PAGO);
    private static final String NUEVO_ASIENTO_MEDIANTE_MODELO = "iconos/ui/modules/cashflow/nuevo_asiento_mediante_modelo.png";
    public static final ImageIcon nuevo_asiento_mediante_modelo = getIcon(NUEVO_ASIENTO_MEDIANTE_MODELO);
    private static final String ORDEN_DE_PAGO = "iconos/ui/modules/cashflow/orden_de_pago.png";
    public static final ImageIcon orden_de_pago = getIcon(ORDEN_DE_PAGO);
    private static final String PAGOS_DE_TERCEROS = "iconos/ui/modules/cashflow/pagos_de_terceros.png";
    public static final ImageIcon pagos_de_terceros = getIcon(PAGOS_DE_TERCEROS);
    private static final String PLAN_DE_CUENTAS = "iconos/ui/modules/cashflow/plan_de_cuentas.png";
    public static final ImageIcon plan_de_cuentas = getIcon(PLAN_DE_CUENTAS);
    private static final String REGISTRAR_COMPROBANTES_A_PAGAR = "iconos/ui/modules/cashflow/registrar_comprobantes_a_pagar.png";
    public static final ImageIcon registrar_comprobantes_a_pagar = getIcon(REGISTRAR_COMPROBANTES_A_PAGAR);

    /** RESOURCES */
    private static final String APROBAR_PEDIDOS_DE_MATERIALES = "iconos/ui/modules/resources/aprobar_pedidos_de_materiales.png";
    public static final ImageIcon aprobar_pedidos_de_materiales = getIcon(APROBAR_PEDIDOS_DE_MATERIALES);
    private static final String AUTORIZAR_PEDIDOS_DE_MATERIALES = "iconos/ui/modules/resources/autorizar_pedidos_de_materiales.png";
    public static final ImageIcon autorizar_pedidos_de_materiales = getIcon(AUTORIZAR_PEDIDOS_DE_MATERIALES);
    private static final String BIENES_DE_USO = "iconos/ui/modules/resources/bienes_de_uso.png";
    public static final ImageIcon bienes_de_uso = getIcon(BIENES_DE_USO);
    private static final String ENTREGA_DE_RECURSOS = "iconos/ui/modules/resources/entrega_de_recursos.png";
    public static final ImageIcon entrega_de_recursos = getIcon(ENTREGA_DE_RECURSOS);
    private static final String GENERAR_ORDEN_DE_COMPRA = "iconos/ui/modules/resources/generar_orden_de_compra.png";
    public static final ImageIcon generar_orden_de_compra = getIcon(GENERAR_ORDEN_DE_COMPRA);
    private static final String GENERAR_ORDEN_DE_PROVISION = "iconos/ui/modules/resources/generar_orden_de_provision.png";
    public static final ImageIcon generar_orden_de_provision = getIcon(GENERAR_ORDEN_DE_PROVISION);
    private static final String INGRESO_DE_RECURSOS_POR_COMPRAS = "iconos/ui/modules/resources/ingreso_de_recursos_por_compras.png";
    public static final ImageIcon ingreso_de_recursos_por_compras = getIcon(INGRESO_DE_RECURSOS_POR_COMPRAS);
    private static final String LISTADO_DE_REMITOS_EXTERNOS = "iconos/ui/modules/resources/listado_de_remitos_externos.png";
    public static final ImageIcon listado_de_remitos_externos = getIcon(LISTADO_DE_REMITOS_EXTERNOS);
    private static final String ORDENES_DE_COMPRA_EXISTENTES = "iconos/ui/modules/resources/ordenes_de_compra_existentes.png";
    public static final ImageIcon ordenes_de_compra_existentes = getIcon(ORDENES_DE_COMPRA_EXISTENTES);
    private static final String PROVEEDORES = "iconos/ui/modules/resources/proveedores.png";
    public static final ImageIcon proveedores = getIcon(PROVEEDORES);
    private static final String RECURSOS_MATERIALES = "iconos/ui/modules/resources/recursos_materiales.png";
    public static final ImageIcon recursos_materiales = getIcon(RECURSOS_MATERIALES);
    private static final String VER_CREAR_PEDIDOS_DE_MATERIALES = "iconos/ui/modules/resources/ver_crear_pedidos_de_materiales.png";
    public static final ImageIcon ver_crear_pedidos_de_materiales = getIcon(VER_CREAR_PEDIDOS_DE_MATERIALES);


    /**
     * ASSETS
     * */
     
    private static final String CONFIGURACION_BASE_DE_SUELDOS = "iconos/ui/modules/assets/configuracion_base_de_sueldos.png";
    public static final ImageIcon configuracion_base_de_sueldos = getIcon(CONFIGURACION_BASE_DE_SUELDOS);
    private static final String DEPENDENCIAS = "iconos/ui/modules/assets/dependencias.png";
    public static final ImageIcon dependencias = getIcon(DEPENDENCIAS);
    private static final String HABILIDADES = "iconos/ui/modules/assets/habilidades.png";
    public static final ImageIcon habilidades = getIcon(HABILIDADES);
    private static final String LEGAJOS = "iconos/ui/modules/assets/legajos.png";
    public static final ImageIcon legajos = getIcon(LEGAJOS);
    private static final String LIQUIDACION_DE_SUELDOS = "iconos/ui/modules/assets/liquidacion_de_sueldos.png";
    public static final ImageIcon liquidacion_de_sueldos = getIcon(LIQUIDACION_DE_SUELDOS);
    private static final String  RECURSOS_HUMANOS= "iconos/ui/modules/assets/recursos_humanos.png";
    public static final ImageIcon recursos_humanos = getIcon(RECURSOS_HUMANOS);

    /**
      * RESOURCES_REQUESTS
      * */
    /*private static final String REQUEST_AUTH = "iconos/ui/modules/resourcesrequests/requestauth.png";
    public static final ImageIcon requestauth = getIcon(REQUEST_AUTH);*/
    /**
      * RESOURCES_CONTROL
      * */
    /*private static final String PROVIDERS = "iconos/ui/modules/resourcescontrol/providers.png";
    public static final ImageIcon providers = getIcon(PROVIDERS);
    private static final String MATERIALRESOURCES = "iconos/ui/modules/resourcescontrol/materialresources.png";
    public static final ImageIcon materialresources = getIcon(MATERIALRESOURCES);
    private static final String HUMANRESOURCES = "iconos/ui/modules/resourcescontrol/humanresources.png";
    public static final ImageIcon humanresources = getIcon(HUMANRESOURCES);
    private static final String SKILLS = "iconos/ui/modules/resourcescontrol/skills.png";
    pulic static final ImageIcon skills = getIcon(SKILLS);*/
    /**
      * STICKYNOTES
      * */
    private static final String STICKYNOTE = "iconos/ui/general/stickynote.png";
    public static final ImageIcon stickynote = getIcon(STICKYNOTE);

    private static final String GAIA = "iconos/ui/modules/gaia/gaia.png";
    public static final ImageIcon gaia = getIcon(GAIA);

    /**
    * END FUNCTION ICONS
    * */
    private static final String UPLOADPICTUREICON = "iconos/other/uploadPicture.jpg";
    private static final String SHOWPICTUREICON = "iconos/other/showPicture.jpg";
    private static final String ADDGROUPICON = "iconos/22x22/group-add.png";
    private static final String ADDUSERICON = "iconos/22x22/user-add.png";
    private static final String FILES_LOGO_ON = "iconos/32x32/files-logoOn.png";
    private static final String FILES_LOGO_OFF = "iconos/32x32/files-logoOff.png";
    private static final String DRILLING_LOGO_ON = "iconos/other/drilling-logo.png";
    private static final String DRILLING_LOGO_OFF = "iconos/other/drilling-logo.png";
    private static final String MAPPER_LOGO_ON = "iconos/other/mapper-logo.png";
    private static final String MAPPER_LOGO_OFF = "iconos/other/mapper-logo.png";
    private static final String ADMIN_LOGO_ON = "iconos/other/admin-logo.png";
    private static final String ADMIN_LOGO_OFF = "iconos/other/admin-logo.png";
    private static final String LOGISTIC_LOGO_ON = "iconos/other/logistic-logo.png";
    private static final String LOGISTIC_LOGO_OFF = "iconos/other/logistic-logo.png";
    private static final String CALCULATOR_LOGO_ON = "iconos/32x32/calc.png";
    private static final String CALCULATOR_LOGO_OFF = "iconos/32x32/calc.png";
    private static final String BLUETOOTH_LOGO_ON = "iconos/other/bluetooth-logo.png";
    private static final String BLUETOOTH_LOGO_OFF = "iconos/other/bluetooth-logo.png";
    private static final String COMPYS_LOGO_ON = "iconos/other/compys-logoOn.png";
    private static final String COMPYS_LOGO_OFF = "iconos/other/compys-logoOff.png";
    private static final String CHWY_LOGO_ON = "iconos/other/chwy-logo.png";
    private static final String CHWY_LOGO_OFF = "iconos/other/chwy-logo.png";
    private static final String LOGOUT_ICON_ON = "iconos/32x32/logout-On.png";
    private static final String LOGOUT_ICON_OFF = "iconos/32x32/logout-Off.png";
    private static final String EXIT_ICON_ON = "iconos/32x32/exit-On.png";
    private static final String EXIT_ICON_OFF = "iconos/32x32/exit-Off.png";
    private static final String GEOCALCULATOR_LOGO_ON = "iconos/other/calculator-logo.png";
    private static final String GEOCALCULATOR_LOGO_OFF = "iconos/other/calculator-logo.png";
    private static final String WEBMAIL_LOGO_ON = "iconos/32x32/email.png";
    private static final String WEBMAIL_LOGO_OFF = "iconos/32x32/email.png";
    
    
    /**
     * CR_DRILLING ICONS
     */
    private static final String CR_DRILLING_HEADER_LOGO = "iconos/22x22/CRDrilling_IconLogo.png";
    public static final ImageIcon CRDrilling_IconHeaderLogo = getIcon(CR_DRILLING_HEADER_LOGO);
    /**
     * CR GENERAL ICONS
     */
    private static final String CR_HEADER_LOGO = "iconos/22x22/CRIcon.gif";
    public static final ImageIcon CR_IconHeaderLogo = getIcon(CR_HEADER_LOGO);
    /**
     * CR_ADMIN ICONS
     */
    private static final String CR_ADMIN_HEADER_LOGO = "iconos/22x22/admin-logo.gif";
    public static final ImageIcon CRAdmin_IconHeaderLogo = getIcon(CR_ADMIN_HEADER_LOGO);
    private static final String SYSTEM_IMAGE_22x22 = "iconos/22x22/system-logo.gif";
    public static final ImageIcon SYSTEM_ICON_22x22 = getIcon(SYSTEM_IMAGE_22x22);
    private static final String USER_IMAGE_22x22 = "iconos/22x22/user-logo.gif";
    public static final ImageIcon USER_ICON_22x22 = getIcon(USER_IMAGE_22x22);
    private static final String FILE_IMAGE_22x22 = "iconos/22x22/archivist-logo.gif";
    public static final ImageIcon FILE_ICON_22x22 = getIcon(FILE_IMAGE_22x22);
    /**
     * COMMON ICONS
     */
    /**
     * CR_FILES ICONS
     */
    private static final String CR_FILES_HEADER_LOGO = "iconos/22x22/CRFiles_IconLogo.png";
    public static final ImageIcon CRFiles_IconHeaderLogo = getIcon(CR_FILES_HEADER_LOGO);
    private static final String CR_FILES_BLACK_ICON = "iconos/32x32/FileBlack.png";
    public static ImageIcon CRFileBlack = getIcon(CR_FILES_BLACK_ICON);
    private static final String CR_FILES_RED_ICON = "iconos/32x32/FileRed.png";
    public static final ImageIcon CRFileRed = getIcon(CR_FILES_RED_ICON);
    private static final String CR_FILES_YELLOW_ICON = "iconos/32x32/FileYellow.png";
    public static final ImageIcon CRFileYellow = getIcon(CR_FILES_YELLOW_ICON);
    private static final String CR_FILES_GREEN_ICON = "iconos/32x32/FileGreen.png";
    public static final ImageIcon CRFileGreen = getIcon(CR_FILES_GREEN_ICON);
    private static final String CR_FILES_BLUE_ICON = "iconos/32x32/FileBlue.png";
    public static final ImageIcon CRFileBlue = getIcon(CR_FILES_BLUE_ICON);
    private static final String CR_FILES_TOTAL_ICON = "iconos/32x32/FileTotal.png";
    public static final ImageIcon CRFileTotal = getIcon(CR_FILES_TOTAL_ICON);
    private static final String CR_FILES_BLACK_16x16_ICON = "iconos/16x16/FileBlack.png";
    public static final ImageIcon CRFileBlack_16x16 = getIcon(CR_FILES_BLACK_16x16_ICON);
    private static final String CR_FILES_RED_16x16_ICON = "iconos/16x16/FileRed.png";
    public static final ImageIcon CRFileRed_16x16 = getIcon(CR_FILES_RED_16x16_ICON);
    private static final String CR_FILES_YELLOW_16x16_ICON = "iconos/16x16/FileYellow.png";
    public static final ImageIcon CRFileYellow_16x16 = getIcon(CR_FILES_YELLOW_16x16_ICON);
    private static final String CR_FILES_GREEN_16x16_ICON = "iconos/16x16/FileGreen.png";
    public static final ImageIcon CRFileGreen_16x16 = getIcon(CR_FILES_GREEN_16x16_ICON);
    private static final String CR_FILES_BLUE_16x16_ICON = "iconos/16x16/FileBlue.png";
    public static final ImageIcon CRFileBlue_16x16 = getIcon(CR_FILES_BLUE_16x16_ICON);
    private static final String CR_FILES_TOTAL_16x16_ICON = "iconos/16x16/FileTotal.png";
    public static final ImageIcon CRFileTotal_16x16 = getIcon(CR_FILES_TOTAL_16x16_ICON);
    private static final String CR_FILES_BLACK_22x22_ICON = "iconos/22x22/FileBlack.png";
    public static final ImageIcon CRFileBlack_22x22 = getIcon(CR_FILES_BLACK_22x22_ICON);
    private static final String CR_FILES_RED_22x22_ICON = "iconos/22x22/FileRed.png";
    public static final ImageIcon CRFileRed_22x22 = getIcon(CR_FILES_RED_22x22_ICON);
    private static final String CR_FILES_YELLOW_22x22_ICON = "iconos/22x22/FileYellow.png";
    public static final ImageIcon CRFileYellow_22x22 = getIcon(CR_FILES_YELLOW_22x22_ICON);
    private static final String CR_FILES_GREEN_22x22_ICON = "iconos/22x22/FileGreen.png";
    public static final ImageIcon CRFileGreen_22x22 = getIcon(CR_FILES_GREEN_22x22_ICON);
    private static final String CR_FILES_BLUE_22x22_ICON = "iconos/22x22/FileBlue.png";
    public static final ImageIcon CRFileBlue_22x22 = getIcon(CR_FILES_BLUE_22x22_ICON);
    private static final String CR_FILES_TOTAL_22x22_ICON = "iconos/22x22/FileTotal.png";
    public static final ImageIcon CRFileTotal_22x22 = getIcon(CR_FILES_TOTAL_22x22_ICON);
    /**
     * DIGITALL DESKTOP ICONS
     */
    private static final String SLOW_CONNECTION_ICON = "iconos/16x16/messagebox_warning.png";
    public static final ImageIcon slowConnection = getIcon(SLOW_CONNECTION_ICON);
    private static final String FAST_CONNECTION_ICON = "iconos/16x16/ok2.png";
    public static final ImageIcon fastConnection = getIcon(FAST_CONNECTION_ICON);
    public static final ImageIcon imgDrillingOn = getIcon(DRILLING_LOGO_ON);
    public static final ImageIcon imgDrillingOff = getIcon(DRILLING_LOGO_OFF);
    public static final ImageIcon imgMapperOn = getIcon(MAPPER_LOGO_ON);
    public static final ImageIcon imgMapperOff = getIcon(MAPPER_LOGO_OFF);
    public static final ImageIcon imgAdminOn = getIcon(ADMIN_LOGO_ON);
    public static final ImageIcon imgAdminOff = getIcon(ADMIN_LOGO_OFF);
    public static final ImageIcon imgLogisticOn = getIcon(LOGISTIC_LOGO_ON);
    public static final ImageIcon imgLogisticOff = getIcon(LOGISTIC_LOGO_OFF);
    public static final ImageIcon imgGeoCalculatorOn = getIcon(GEOCALCULATOR_LOGO_ON);
    public static final ImageIcon imgGeoCalculatorOff = getIcon(GEOCALCULATOR_LOGO_OFF);
    public static final ImageIcon imgCalculatorOn = getIcon(CALCULATOR_LOGO_ON);
    public static final ImageIcon imgCalculatorOff = getIcon(CALCULATOR_LOGO_OFF);
    public static final ImageIcon imgBluetoothOn = getIcon(BLUETOOTH_LOGO_ON);
    public static final ImageIcon imgBluetoothOff = getIcon(BLUETOOTH_LOGO_OFF);
    public static final ImageIcon imgCOMPySOn = getIcon(COMPYS_LOGO_ON);
    public static final ImageIcon imgCOMPySOff = getIcon(COMPYS_LOGO_OFF);
    public static final ImageIcon imgFilesOn = getIcon(FILES_LOGO_ON);
    public static final ImageIcon imgFilesOff = getIcon(FILES_LOGO_OFF);
    public static final ImageIcon imgChWYOn = getIcon(CHWY_LOGO_ON);
    public static final ImageIcon imgChWYOff = getIcon(CHWY_LOGO_OFF);
    public static final ImageIcon imgSessionOn = getIcon(LOGOUT_ICON_ON);
    public static final ImageIcon imgSessionOff = getIcon(LOGOUT_ICON_OFF);
    public static final ImageIcon imgExitOn = getIcon(EXIT_ICON_ON);
    public static final ImageIcon imgExitOff = getIcon(EXIT_ICON_OFF);
    public static final ImageIcon imgWebMailOn = getIcon(WEBMAIL_LOGO_ON);
    public static final ImageIcon imgWebMailOff = getIcon(WEBMAIL_LOGO_OFF);
    public static final ImageIcon uploadPictureIcon = getIcon(UPLOADPICTUREICON);
    public static final ImageIcon showPictureIcon = getIcon(SHOWPICTUREICON);
    public static final ImageIcon addGroupIcon = getIcon(ADDGROUPICON);
    public static final ImageIcon addUserIcon = getIcon(ADDUSERICON);
    /************************************************ ICONS 16x16 *******************************************/
    public static final ImageIcon addMoreButtonIcon_16x16 = getIcon(ADDMOREBUTTON16x16);
    public static final ImageIcon cancelDataIcon_16x16 = getIcon(CANCELBUTTON16x16);
    public static final ImageIcon editIcon_16x16 = getIcon(EDITBUTTON16x16);
    public static final ImageIcon applicationIcon_16x16 = getIcon(APPLICATIONBUTTON16x16);
    public static final ImageIcon goButtonIcon_16x16 = getIcon(GOBUTTON16x16);
    public static final ImageIcon statusBarIcon_16x16 = getIcon(STATUSBAR16x16);
    public static final ImageIcon nextWizardIcon_16x16 = getIcon(NEXTWIZARD16x16);
    public static final ImageIcon previousWizardIcon_16x16 = getIcon(PREVIOUSWIZARD16x16);
    public static final ImageIcon systemIcon_16x16 = getIcon(SYSTEM16x16);
    public static final ImageIcon calendarIcon_16x16 = getIcon(CALENDAR_LOGO16x16);
    public static final ImageIcon filesIcon_16x16 = getIcon(FILES16x16);
    public static final ImageIcon fileConfigIcon_16x16 = getIcon(FILESCONFIG16x16);
    public static final ImageIcon stepIcon_Off_16x16 = getIcon(STEPOFF16x16);
    public static final ImageIcon stepIcon_On_16x16 = getIcon(STEPON16x16);
    public static final ImageIcon stepApplyIcon_16x16 = getIcon(STEPAPPLY16x16);
    public static final ImageIcon stepIncompleteIcon_16x16 = getIcon(STEPINCOMPLETE16x16);
    public static final ImageIcon stepRejectedIcon_16x16 = getIcon(STEPREJECTED16x16);
    public static final ImageIcon stepNoMadeIcon_16x16 = getIcon(STEPNOMADE16x16);
    /********************************************************************************************************/
    /************************************************ ICONS 22x22 *******************************************/
    public static final ImageIcon reportIcon_22x22 = getIcon(REPORT22x22);
    public static final ImageIcon languageIcon_22x22 = getIcon(LANGUAGE22x22);
    public static final ImageIcon filesIcon_22x22 = getIcon(FILES22x22);
    public static final ImageIcon fileConfigIcon_Off_22x22 = getIcon(FILESCONFIG_OFF22x22);
    public static final ImageIcon fileConfigIcon_On_22x22 = getIcon(FILESCONFIG_ON22x22);
    public static final ImageIcon fileNewFast_Off_22x22 = getIcon(FILESNEWFAST_OFF22x22);
    public static final ImageIcon fileWizard_Off_22x22 = getIcon(FILESWIZARD_OFF22x22);
    public static final ImageIcon fileWizard_On_22x22 = getIcon(FILESWIZARD_ON22x22);
    public static final ImageIcon fileNewFast_On_22x22 = getIcon(FILESNEWFAST_ON22x22);
    public static final ImageIcon calendarIcon_22x22 = getIcon(CALENDAR_LOGO22x22);
    /********************************************************************************************************/
    /************************************************ ICONS 32x32 *******************************************/
    public static final ImageIcon reportIcon_32x32 = getIcon(REPORT32x32);
    public static final ImageIcon languageIcon_32x32 = getIcon(LANGUAGE32x32);
    public static final ImageIcon calendarIcon_32x32 = getIcon(CALENDAR_LOGO32x32);

    /********************************************************************************************************/
    public static ImageIcon getIcon(String _nombre) {
	try {
	    return new ImageIcon(ResourcesManager.class.getResource(_nombre));
	} catch (Exception x) {
	    Advisor.messageBox("Error Fatal: Falta el icono o imagen " + _nombre, "Error");
	    System.exit(-1);
	    return null;
	}
    } 

    public static ImageIcon getScaledIcon(ImageIcon _icon, int _width, int _height) {
	if (_icon != null && _icon.getImage() != null) {
	    return new ImageIcon(_icon.getImage().getScaledInstance(_width, _height, Image.SCALE_REPLICATE));
	} else {
	    return null;
	}
    }

}
