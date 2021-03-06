/******************************************************************************* 
 * Copyright (c) 2009 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/ 
package org.jboss.tools.jst.web.kb.internal.taglib;

import org.jboss.tools.jst.web.kb.internal.KbXMLStoreConstants;
import org.jboss.tools.jst.web.kb.taglib.IFaceletTagLibrary;

/**
 * @author Viacheslav Kabanovich
 */
public class FaceletTagLibrary extends FunctionTagLib implements
		IFaceletTagLibrary {

	public FaceletTagLibrary() {		
	}

	@Override
	public FaceletTagLibrary clone() throws CloneNotSupportedException {
		return (FaceletTagLibrary)super.clone();
	}

	@Override
	public String getXMLClass() {
		return KbXMLStoreConstants.CLS_FACELET_LIBRARY;
	}
}