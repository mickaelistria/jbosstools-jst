package org.jboss.tools.jst.web.kb;

import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.jboss.tools.jst.web.kb.taglib.INameSpace;

/**
 * The interface defines the methods to collect
 * contexts for the included pages.
 * 
 * @author Victor Rubezhny
 *
 */
public interface IIncludedContextSupport {

	/**
	 * Adds the context created for the included page
	 * 
	 * @param includedContext
	 */
	void addIncludedContext(IPageContext includedContext);
	
	/**
	 * Returns the list of all the collected contexts
	 * 
	 * @return
	 */
	List<IPageContext> getIncludedContexts();
	
	/**
	 * Returns Resource of the page
	 * @return
	 */
	IFile getResource();
	
	/**
	 * Returns map of name spaces which are set in particular offset.
	 * Key is URI of name space.
	 * @return
	 */
	Map<String, List<INameSpace>> getNameSpaces(int offset);
}