/*
ESUP-portail is a french academic project developed under the GPL (General Public License) augmented according to the following :
A binary or source file developped by ESUP-portail can be used or compiled with products under Apache license.
The official english text of the GPL can be found here : http://www.gnu.org/licenses/gpl.html .
A non official french translation can be found here : http://www.linux-France.org/article/these/gpl.html .
The different kinds of licenses governing the products developed by the Apache foundation can be found here : http://www.apache.org/licenses .
It follows that you can as well as use download contents as well modify and redistribute them provided you respect the GPL terms.
Downloading and using such contents do not provide any guaranty.
Be sure that you have well understood the terms of the license before using the contents it covers.
The ESUP-portail distribution includes the following distributions :
    * UPortal :
      software developed by JA-SIG (Java Architecture - Special Interest Group)
      You can find the license page here : http://mis105.udel.edu/ja-sig/uportal/license.html
    * CAS :
      SSO solution developed by Yale University
      You can find the project page here : http://www.yale.edu/tp/auth
    * Cocoon :
      XML framework distributed by the Apache foundation under Apache license;
      Please find the full text here : http://cocoon.apache.org/2.1/license.html
    * Mod_dav:
      A DAV module for Apache web server
      You can find the project page here : http://www.webdav.org/mod_dav
    * IMP :
      webmail from Horde application framework
      You can find the project page here : http://www.horde.org
    * . To be completed
*/
package org.esupportail.portal.ws.groups;


import java.util.Iterator;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.portal.EntityIdentifier;
import org.jasig.portal.groups.GroupsException;
import org.jasig.portal.groups.IEntityGroup;
import org.jasig.portal.groups.IGroupConstants;
import org.jasig.portal.groups.IGroupMember;
import org.jasig.portal.security.IPerson;
import org.jasig.portal.services.GroupService;

/**
 * Id: PortalGroups.java,v 1.0 8 sept. 2004
 * Copyright (c) 2004 Esup Portail (www.esup-portail.org)
 * Classes: PortalGroups
 * Original Author: Yohan Colmant
 * Webservice used to access the portal groups hierarchy
 */
public class PortalGroups {
 

	/**
	 * Logger object
	 */
	protected static final Log log = LogFactory.getLog(PortalGroups.class);
	
	
	
	 /**
     * Recupere le nom d'un groupe donne
     * @param groupKey
     * @return null si le groupe n'est pas retrouve. Une String representant le nom du groupe
     * @throws Exception
     */
    public String getGroupName(String groupKey) throws Exception {
    	
    	// je recupere le groupe dont la clef a ete donnee en parametre
		IEntityGroup myGroup = GroupService.findGroup(groupKey);    		
		
		// si le groupe n'est pas retrouve, on renvoie null
		if (myGroup==null) {
			if (log.isDebugEnabled())
				log.debug("getGroupName"+" :: can't find group "+groupKey);
			return null;
		}
    	else {
    		String name = myGroup.getName();
    		if (log.isDebugEnabled())
				log.debug("getGroupName"+" :: found group name \""+name+"\" for the key "+groupKey);
    		return name;
    	}
    }
	
    
	/**
     * Permet de recuperer le groupe racine du portail
     * @return la clef du groupe racine
     * @throws Exception
     */
	public String getRoot() throws Exception {		
		try {
			IEntityGroup everyone = GroupService.getDistinguishedGroup(GroupService.EVERYONE);
			String key = everyone.getKey();
			if (log.isDebugEnabled())
				log.debug("getRoot"+" :: key = "+key);
			return key;			
		}
		catch(GroupsException e) {
			log.error("getRoot"+" :: "+e);
			throw new Exception(e.getMessage());
		}
	}
	
	
	
	/**
     * Permet de recuperer le groupe racine du portail, et recursivement les sous-groupes de celui-ci
     * @return un tableau de 2 elements: le premier est le nom de ce groupe, le second est un vecteur d'autres sous-noeuds (ou sous-groupes).
     * @throws Exception
     */
	public Object[] getGroupsHierarchy() throws Exception {		
		try {
			IEntityGroup everyone = GroupService.getDistinguishedGroup(GroupService.EVERYONE);	    	
			Object[] result = getSubGroups(everyone);
			if (log.isDebugEnabled())
				log.debug("getGroupsHierarchy"+" :: found");
			return result;
		}
		catch(GroupsException e) {
			log.error("getGroupsHierarchy"+" :: "+e);
			throw new Exception(e.getMessage());
		}
	}
	
	
		
    
    /**
     * Permet de recuperer le nom du groupe passe en parametre, et recursivement les sous-groupes de celui-ci
     * @param igm un groupe donne de type IGroupMember
     * @return un tableau de 2 elements: le premier est le nom de ce groupe, le second est un vecteur d'autres sous-noeuds (ou sous-groupes). Renvoie null si le parametre n'est pas un groupe
     * @throws GroupsException
     */
    private Object[] getSubGroups(IGroupMember igm) throws GroupsException {
    	
    	// on verifie que igm est bien un groupe
    	if (igm.isGroup()) {
    		
    		// on caste en IEntityGroup pour avoir acces a la methode getKey()
			IEntityGroup egi = (IEntityGroup)igm;
			
			// on recupere la clef
			String key = egi.getKey();		
			
	    	// un vecteur qui va contenir tous les noeuds fils de ce noeud
	    	Vector v = new Vector();	    	
			
			// on recupere tous les sous-groupes de "name"
			Iterator allgroups = igm.getMembers();
	    	
			// on parcourt chacun de ces groupes
			while (allgroups.hasNext()) {
	    		
				// un fils de l'arbre
				IGroupMember g = (IGroupMember)allgroups.next();    		
	    	
				// pour chaque groupe fils, on va chercher recursivement ses sous-groupes
				Object[] subs = getSubGroups(g);
	    	
				// on ajoute ceci au vecteur
				if (subs != null)
					v.addElement(subs);
			}			
	    	
	    	// on construit un tableau d'objets [N,V] ou
	    	// * N est le nom d'un noeud
	    	// * V est un vecteur d'autres noeuds
	    	Object[] res = new Object[2];
	    	res[0] = key;
	    	res[1] = v;
	    	return res;
		}
    	
    	// si igm n'est pas un groupe, on retourne null
    	else {
    		if (log.isDebugEnabled())
				log.debug("getSubGroups"+" :: "+igm.getKey()+" is not a valid group");
    		return null;
    	}    	    	
    }

    
    
    

    /**
     * Recupere les sous-groupes d'un groupe donne
     * @param groupKey
     * @return null si le groupe n'est pas retrouve. Un vecteur de clefs de groupes si le groupe a ete retrouve
     * @throws Exception
     */
    public Vector getNestedGroupsOfGroup(String groupKey) throws Exception {
    	
    	try {
    		
    		// je recupere le groupe dont la clef a ete donnee en parametre
    		IEntityGroup myGroup = GroupService.findGroup(groupKey);    		
    		
    		// si le groupe n'est pas retrouve, on renvoie null
    		if (myGroup==null) {
    			if (log.isDebugEnabled())
    				log.debug("getNestedGroupsOfGroup"+" :: can't find group "+groupKey);
    			return null;    
    		}
    		
    		// le vecteur qui va contenir les fils
    		Vector groups = new Vector();
    		
    		// je parcours les fils
    		Iterator iter = myGroup.getMembers();
    		while (iter.hasNext()) {
    			
    			Object object = iter.next();
    			if (object instanceof IEntityGroup) {
    				IEntityGroup g = (IEntityGroup)object;
        			groups.addElement(g.getKey());
    			}// if (object instanceof IEntityGroup)
    		}
    		
    		// renvoie le vecteur
    		if (log.isDebugEnabled())
				log.debug("getNestedGroupsOfGroup"+" :: for the key "+groupKey+", the nested groups are "+groups);
    		return groups;    	
    	}
    	catch(GroupsException e) {
    		log.error("getNestedGroupsOfGroup"+" :: "+e);
    		throw new Exception(e.getMessage());
    	}
    }
	
    
    
    
    
    
    
    
    /**
     * Permet de recuperer tous les groupes auquels appartient un utilisateur donne
     * @param user l'identifiant de l'utilisateur
     * @return un vecteur contenant un ensemble de vecteurs. chacun de ces vecteurs contient la liste des groupes en partant de la racine de l'arbre jusque la feuille<br/>
     * Par exemple: [racine, groupeA, groupeA1, groupe A1b], [racine, groupeB, groupeB3]
     * @throws Exception
     */
    public Vector getGroupsOfUser(String user) throws Exception {
    	
    	// on recherche la personne donnee
    	try {
    		
    		// on recherche ce user
    		EntityIdentifier[] ents = GroupService.searchForEntities(user,IGroupConstants.IS,IPerson.class);
    		
    		// si on ne recupere aucun element, on renvoit null
    		if (ents.length!=0) {
    	
    			// on ne doit recevoir qu'un seul resultat pour ce type de requete
    			EntityIdentifier ei = ents[0];
    		
    			// le IGroupMember associe a cet identifiant
    			IGroupMember gm = GroupService.getGroupMember(ei);						
    		
    			// on recupere tous les groupes auxquels appartient l'utilisateur
    			Iterator iter = gm.getContainingGroups();    			

    	    	// le vecteur qui va contenir tous les groupes
    	    	Vector groups = new Vector();
    	    	//System.out.println("");
    	    	
    			// pour chaque groupe, on va remonter la hierarchie jusqu'a la racine de l'arbre des groupes du portail
    			while(iter.hasNext()) {
    				
    				// pour chaque element
    				IEntityGroup egi = (IEntityGroup)iter.next();
    				
    				// on construit un vecteur qui va contenir toute la hierarchie
    				Vector v = new Vector();			    				

    				// on ajoute le noeud sur lequel on se trouve
    				v.addElement(egi.getKey());
    				//System.out.println("Ajoute "+egi.getName());
    				
    				// on remplit ce vecteur en remontant la hierarchie recursivement
    				getRecurContainingGroups(egi, groups, v);
    				
    				// on ajoute le vecteur trouve au vecteur resultat    				
    				groups.addElement(v);
    				//System.out.println("AjouteAll "+v);
    			}    		
    	
    			// on retourne le resultat
    			if (log.isDebugEnabled())
    				log.debug("getGroupsOfUser"+" :: for the "+user+", the groups are "+groups);  
    			return groups;
    		}
    	
    		// si on ne recupere aucun element, on renvoie null
    		else {
    			if (log.isDebugEnabled())
    				log.debug("getGroupsOfUser"+" :: "+user+" not found");    		
    			return null;
    		}
    	}
    	catch(GroupsException e) {
    		log.error("getGroupsOfUser"+" :: "+e);
    		throw new Exception(e.getMessage());
    	}
    	
    }

    
    
    /**
     * Recupere recursivement les groupes contenant un groupe donne, jusque la racine
     * @param igm un groupe sous la forme IGroupMember
     * @param allGroups vecteur contenant la liste de tous les groupes de l'utilisateur donne. Il peut etre agrandi au cours de la recursivite au cas ou un groupe est rattache a plusieurs groupes en meme temps
     * @param v le vecteur a remplir a chaque etape de la recursivite
     * @throws GroupsException
     */
    private void getRecurContainingGroups(IGroupMember igm, Vector allGroups, Vector v) throws GroupsException {				

    	// recupere les groupes contenant le groupe passe en parametre
		Iterator iter = igm.getContainingGroups();
		
		// pour savoir combien on a d'elements, on recopie cette iteration dans un vecteur
		Vector containingGroups = new Vector();
		while(iter.hasNext()) {
			containingGroups.addElement(iter.next());
		}
				
		
		// pour chacun, on l'ajoute dans la liste
		for (int i=0; i<containingGroups.size(); i++) {
			
			// on recupere le groupe courant dans l'iteration		
			IEntityGroup egi = (IEntityGroup)containingGroups.elementAt(i);
			
			// clone du vecteur passe en parametre, ou le vecteur lui meme
			Vector v2 = null;
			if (i==containingGroups.size()-1) {
				v2=v;
			}
			else {
				v2 = (Vector)v.clone();
				//System.out.println(v2+" clone de "+v);
			}	
			
			// on ajoute la clef de ce groupe
			v2.add(0, egi.getKey());
			//System.out.println("Add "+egi.getName());
			
			// appel a la recursivite
			getRecurContainingGroups(egi, allGroups, v2);
			
			// on ajoute cette hierarchie a tous les groupes
			if (i<containingGroups.size()-1) {
				allGroups.addElement(v2);
				//System.out.println("AddAll "+v2);
			}
		}
		
	}
    
    
}

