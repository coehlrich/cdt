/**********************************************************************
 * Copyright (c) 2002,2003 QNX Software Systems and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 * QNX Software Systems - Initial API and implementation
***********************************************************************/
package org.eclipse.cdt.make.internal.ui.editor;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ResourceBundle;

import org.eclipse.cdt.make.core.makefile.IMakefile;
import org.eclipse.cdt.make.internal.core.makefile.NullMakefile;
import org.eclipse.cdt.make.internal.core.makefile.posix.PosixMakefile;
import org.eclipse.cdt.make.internal.ui.MakeUIPlugin;
import org.eclipse.cdt.make.internal.ui.text.MakefileColorManager;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.DefaultRangeIndicator;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.TextOperationAction;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

public class MakefileEditor extends TextEditor {
	public final static String MAKE_COMMENT = "make_comment"; //$NON-NLS-1$
	public final static String MAKE_KEYWORD = "make_keyword"; //$NON-NLS-1$
	public final static String MAKE_MACRO_VAR = "macro_var"; //$NON-NLS-1$
	public final static String MAKE_META_DATA = "meta_data"; //$NON-NLS-1$

	/**
	 * The page that shows the outline.
	 */
	protected MakefileContentOutlinePage page;
	protected IMakefile makefile;

	private MakefileContentOutlinePage getOutlinePage() {
		if (page == null) {
			page = new MakefileContentOutlinePage(getDocumentProvider(), this);
			page.setInput(getEditorInput());
		}
		return page;
	}

	public IMakefile getMakefile() {
		IDocument document = getDocumentProvider().getDocument(getEditorInput());
		return getMakefile(document);
	}

	public IMakefile getMakefile(IDocument document) {
		if (document != null) {
			if (makefile == null || isDirty()) {
				try {
					String content = document.get();
					Reader r = new StringReader(content);
					makefile = new PosixMakefile(r);
				} catch (IOException e) {
					makefile = new NullMakefile();
				}
			}
		}
		return makefile;
	}

	public MakefileEditor() {
		super();
		initializeEditor();
	}

	/**
	 * @see AbstractTextEditor#init(IEditorSite, IEditorInput)
	 */
	protected void initializeEditor() {

		setSourceViewerConfiguration(new MakefileSourceConfiguration(new MakefileColorManager(), this));
		setRangeIndicator(new DefaultRangeIndicator());
		setEditorContextMenuId("#MakefileEditorContext"); //$NON-NLS-1$
		setRulerContextMenuId("#MakefileRulerContext"); //$NON-NLS-1$
		setDocumentProvider(new MakefileDocumentProvider());
	}

	/* (non-Javadoc)
	 * Method declared on IAdaptable
	 */
	public Object getAdapter(Class key) {
		if (key.equals(IContentOutlinePage.class)) {
			return getOutlinePage();
		}
		return super.getAdapter(key);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.ISaveablePart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void doSave(IProgressMonitor monitor) {
		super.doSave(monitor);
		if (page != null) {
			page.update();
		}
	}

	/**
	 * Method to install the editor actions.
	 *
	 * @see org.eclipse.ui.texteditor.AbstractTextEditor#createActions()
	 */
	protected void createActions() {
		super.createActions();

		ResourceBundle bundle = MakeUIPlugin.getDefault().getResourceBundle();

		IAction a = new TextOperationAction(bundle, "ContentAssistProposal.", this, ISourceViewer.CONTENTASSIST_PROPOSALS); //$NON-NLS-1$
		a.setActionDefinitionId(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
		setAction("ContentAssistProposal", a); //$NON-NLS-1$

		a = new TextOperationAction(bundle, "ContentAssistTip.", this, ISourceViewer.CONTENTASSIST_CONTEXT_INFORMATION); //$NON-NLS-1$
		a.setActionDefinitionId(ITextEditorActionDefinitionIds.CONTENT_ASSIST_CONTEXT_INFORMATION);
		setAction("ContentAssistTip", a); //$NON-NLS-1$

	}

}
