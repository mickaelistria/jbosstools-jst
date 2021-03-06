/******************************************************************************* 
 * Copyright (c) 2013 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/ 
package org.jboss.tools.jst.web.ui.palette.html.jquery.wizard;

import java.io.File;

import org.jboss.tools.jst.web.WebModelPlugin;
import org.jboss.tools.jst.web.ui.palette.html.wizard.AbstractNewHTMLWidgetWizard;

/**
 * 
 * @author Viacheslav Kabanovich
 *
 */
public class NewJQueryWidgetWizard extends AbstractNewHTMLWidgetWizard implements JQueryConstants {

	public NewJQueryWidgetWizard() {
	}

	@Override
	protected String getTextForBrowser() {
		ElementNode html = new ElementNode(TAG_HTML, false);
		createHead(html);
		createBodyForBrowser(html.addChild(TAG_BODY));

		NodeWriter w = new NodeWriter(false);
		html.flush(w, 0);

		StringBuilder sb = new StringBuilder();
		sb.append(DOCTYPE).append("\n").append(w.getText());
		return sb.toString();
	}

	/**
	 * Override to wrap content.
	 * @param body
	 */
	protected void createBodyForBrowser(ElementNode body) {
		addContent(body);
	}

	protected ElementNode getPageContentNode(ElementNode parent) {
		ElementNode page = parent.addChild(TAG_DIV);
		page.addAttribute(ATTR_DATA_ROLE, ROLE_PAGE);
		page.addAttribute(ATTR_ID, "jbt");
		ElementNode content = page.addChild(TAG_DIV);
		page.addAttribute(ATTR_DATA_ROLE, ROLE_CONTENT);
		return content;
	}

	protected ElementNode getFormNode(ElementNode parent) {
		ElementNode form = parent.addChild(TAG_FORM);
		form.addAttribute(ATTR_ACTION, "#");
		form.addAttribute(ATTR_METHOD, METHOD_GET);
		return form;
	}
	
	private void createHead(ElementNode html) {
		ResourceConstants c = new ResourceConstants120();
		
		String styleSheetURI = c.getCSSPath();
		String jQueryScriptURI = c.getScriptPath();
		String jQueryMobileScriptURI = c.getMobileScriptPath();
		
		File root = WebModelPlugin.getJSStateRoot(); //new File("/home/slava/Downloads/jquery.mobile-1.2.0.zip"); 

		if(root != null) {
			String prefix = "js/";
			if(root.isDirectory()) {
				styleSheetURI = new File(root, prefix + c.getCSSName()).toURI().toString();
				jQueryScriptURI = new File(root, prefix + c.getScriptName()).toURI().toString();
				jQueryMobileScriptURI = new File(root, prefix + c.getMobileScriptName()).toURI().toString();
			} else if(root.isFile()) {
				String jar = "jar:" + root.toURI().toString() + "!/";
				styleSheetURI = jar + prefix + c.getCSSName();
				jQueryScriptURI = jar + prefix + c.getScriptName();
				jQueryMobileScriptURI = jar + prefix + c.getMobileScriptName();
			}
		}

		ElementNode head = html.addChild(TAG_HEAD);
		head.addChild(TAG_TITLE, "Page Title");
		ElementNode meta = head.addChild(TAG_META);
		meta.addAttribute(ATTR_NAME, "viewport");
		meta.addAttribute(ATTR_CONTENT, "width=device-width, initial-scale=1");
		ElementNode link = head.addChild(TAG_LINK);
		link.addAttribute(ATTR_REL, "stylesheet");
		link.addAttribute(ATTR_HREF, styleSheetURI);
		ElementNode script = head.addChild(TAG_SCRIPT, "");
		script.addAttribute(ATTR_SRC, jQueryScriptURI);
		script = head.addChild(TAG_SCRIPT, "");
		script.addAttribute(ATTR_SRC, jQueryMobileScriptURI);
	}

	abstract class ResourceConstants {
		public String getSite() {
			return "http://code.jquery.com/";
		}
		public String getMobileSite() {
			return "http://code.jquery.com/mobile/";
		}
		public abstract String getVerionFolder();
		public abstract String getCSSName();
		public abstract String getScriptName();
		public abstract String getMobileScriptName();
		
		public String getCSSPath() {
			return getMobileSite() + getVerionFolder() + getCSSName();
		}
			
		public String getScriptPath() {
			return getSite() + getScriptName();
		}
			
		public String getMobileScriptPath() {
			return getMobileSite() + getVerionFolder() + getMobileScriptName();
		}			

	}

	class ResourceConstants120 extends ResourceConstants {
		@Override
		public String getVerionFolder() {
			return "1.2.0/";
		}
		@Override
		public String getCSSName() {
			return "jquery.mobile-1.2.0.min.css";
		}
		@Override
		public String getScriptName() {
			return "jquery-1.8.2.min.js";
		}
		@Override
		public String getMobileScriptName() {
			return "jquery.mobile-1.2.0.min.js";
		}
	}

	class ResourceConstants130 extends ResourceConstants {
		@Override
		public String getVerionFolder() {
			return "1.3.0-rc.1/";
		}
		@Override
		public String getCSSName() {
			return "jquery.mobile-1.3.0-rc.1.min.css";
		}
		@Override
		public String getScriptName() {
			return "jquery-1.9.0.min.js";
		}
		@Override
		public String getMobileScriptName() {
			return "jquery.mobile-1.3.0-rc.1.min.js";
		}
	}
}
