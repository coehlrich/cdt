/*******************************************************************************
 * Copyright (c) 2001 Rational Software Corp. and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v0.5 
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v05.html
 * 
 * Contributors:
 *     Rational Software - initial implementation
 ******************************************************************************/
package org.eclipse.cdt.internal.core.parser.token;

import org.eclipse.cdt.core.parser.IScanner;
import org.eclipse.cdt.core.parser.IToken;
import org.eclipse.cdt.core.parser.Keywords;
import org.eclipse.cdt.internal.core.parser.scanner.ContextStack;
import org.eclipse.cdt.internal.core.parser.scanner.IScannerContext;

public class SimpleToken extends AbstractToken implements IToken {
	
	public SimpleToken(int t, ContextStack contextStack ) {
		super(t,contextStack.getCurrentLineNumber());
		setOffsetAndLength(contextStack.getCurrentContext());
	}
	
	public SimpleToken( int t, int endOffset )
	{
		super( t );
		setOffsetAndLength( endOffset );
	}

	protected int offset;

    // All the tokens generated by the macro expansion 
    // will have dimensions (offset and length) equal to the expanding symbol.
	public int getOffset() { 
		return offset; 
	}
	
	public int getLength() {
		return getImage().length();
	}
	
	/**
	 * @param context
	 */
	protected void setOffsetAndLength(IScannerContext context) {
		offset = context.getOffset() - getLength();		
	}
	
	protected void setOffsetAndLength( int endOffset )
	{
		this.offset = endOffset - getLength();
	}
	
	public String getImage() { 
		switch ( getType() ) {
				case IToken.tCOLONCOLON :
					return "::" ; //$NON-NLS-1$
				case IToken.tCOLON :
					return ":" ; //$NON-NLS-1$
				case IToken.tSEMI :
					return ";" ; //$NON-NLS-1$
				case IToken.tCOMMA :
					return "," ; //$NON-NLS-1$
				case IToken.tQUESTION :
					return "?" ; //$NON-NLS-1$
				case IToken.tLPAREN  :
					return "(" ; //$NON-NLS-1$
				case IToken.tRPAREN  :
					return ")" ; //$NON-NLS-1$
				case IToken.tLBRACKET :
					return "[" ; //$NON-NLS-1$
				case IToken.tRBRACKET :
					return "]" ; //$NON-NLS-1$
				case IToken.tLBRACE :
					return "{" ; //$NON-NLS-1$
				case IToken.tRBRACE :
					return "}"; //$NON-NLS-1$
				case IToken.tPLUSASSIGN :
					return "+="; //$NON-NLS-1$
				case IToken.tINCR :
					return "++" ; //$NON-NLS-1$
				case IToken.tPLUS :
					return "+"; //$NON-NLS-1$
				case IToken.tMINUSASSIGN :
					return "-=" ; //$NON-NLS-1$
				case IToken.tDECR :
					return "--" ; //$NON-NLS-1$
				case IToken.tARROWSTAR :
					return "->*" ; //$NON-NLS-1$
				case IToken.tARROW :
					return "->" ; //$NON-NLS-1$
				case IToken.tMINUS :
					return "-" ; //$NON-NLS-1$
				case IToken.tSTARASSIGN :
					return "*=" ; //$NON-NLS-1$
				case IToken.tSTAR :
					return "*" ; //$NON-NLS-1$
				case IToken.tMODASSIGN :
					return "%=" ; //$NON-NLS-1$
				case IToken.tMOD :
					return "%" ; //$NON-NLS-1$
				case IToken.tXORASSIGN :
					return "^=" ; //$NON-NLS-1$
				case IToken.tXOR :
					return "^" ; //$NON-NLS-1$
				case IToken.tAMPERASSIGN :
					return "&=" ; //$NON-NLS-1$
				case IToken.tAND :
					return "&&" ; //$NON-NLS-1$
				case IToken.tAMPER :
					return "&" ; //$NON-NLS-1$
				case IToken.tBITORASSIGN :
					return "|=" ; //$NON-NLS-1$
				case IToken.tOR :
					return "||" ; //$NON-NLS-1$
				case IToken.tBITOR :
					return "|" ; //$NON-NLS-1$
				case IToken.tCOMPL :
					return "~" ; //$NON-NLS-1$
				case IToken.tNOTEQUAL :
					return "!=" ; //$NON-NLS-1$
				case IToken.tNOT :
					return "!" ; //$NON-NLS-1$
				case IToken.tEQUAL :
					return "==" ; //$NON-NLS-1$
				case IToken.tASSIGN :
					return "=" ; //$NON-NLS-1$
				case IToken.tSHIFTL :
					return "<<" ; //$NON-NLS-1$
				case IToken.tLTEQUAL :
					return "<=" ; //$NON-NLS-1$
				case IToken.tLT :
					return "<"; //$NON-NLS-1$
				case IToken.tSHIFTRASSIGN :
					return ">>=" ; //$NON-NLS-1$
				case IToken.tSHIFTR :
					return ">>" ; //$NON-NLS-1$
				case IToken.tGTEQUAL :
					return ">=" ; //$NON-NLS-1$
				case IToken.tGT :
					return ">" ; //$NON-NLS-1$
				case IToken.tSHIFTLASSIGN :
					return "<<=" ; //$NON-NLS-1$
				case IToken.tELLIPSIS :
					return "..." ; //$NON-NLS-1$
				case IToken.tDOTSTAR :
					return ".*" ; //$NON-NLS-1$
				case IToken.tDOT :
					return "." ; //$NON-NLS-1$
				case IToken.tDIVASSIGN :
					return "/=" ; //$NON-NLS-1$
				case IToken.tDIV :
					return "/" ; //$NON-NLS-1$
				case IToken.t_and :
					return Keywords.AND;
				case IToken.t_and_eq :
					return Keywords.AND_EQ ;
				case IToken.t_asm :
					return Keywords.ASM ;
				case IToken.t_auto :
					return Keywords.AUTO ;
				case IToken.t_bitand :
					return Keywords.BITAND ;
				case IToken.t_bitor :
					return Keywords.BITOR ;
				case IToken.t_bool :
					return Keywords.BOOL ;
				case IToken.t_break :
					return Keywords.BREAK ;
				case IToken.t_case :
					return Keywords.CASE ;
				case IToken.t_catch :
					return Keywords.CATCH ;
				case IToken.t_char :
					return Keywords.CHAR ;
				case IToken.t_class :
					return Keywords.CLASS ;
				case IToken.t_compl :
					return Keywords.COMPL ;
				case IToken.t_const :
					return Keywords.CONST ;
				case IToken.t_const_cast :
					return Keywords.CONST_CAST ;
				case IToken.t_continue :
					return Keywords.CONTINUE ;
				case IToken.t_default :
					return Keywords.DEFAULT ;
				case IToken.t_delete :
					return Keywords.DELETE ;
				case IToken.t_do :
					return Keywords.DO;
				case IToken.t_double :
					return Keywords.DOUBLE ;
				case IToken.t_dynamic_cast :
					return Keywords.DYNAMIC_CAST ;
				case IToken.t_else :
					return Keywords.ELSE;
				case IToken.t_enum :
					return Keywords.ENUM ;
				case IToken.t_explicit :
					return Keywords.EXPLICIT ;
				case IToken.t_export :
					return Keywords.EXPORT ;
				case IToken.t_extern :
					return Keywords.EXTERN;
				case IToken.t_false :
					return Keywords.FALSE;
				case IToken.t_float :
					return Keywords.FLOAT;
				case IToken.t_for :
					return Keywords.FOR;
				case IToken.t_friend :
					return Keywords.FRIEND;
				case IToken.t_goto :
					return Keywords.GOTO;
				case IToken.t_if :
					return Keywords.IF ;
				case IToken.t_inline :
					return Keywords.INLINE ;
				case IToken.t_int :
					return Keywords.INT ;
				case IToken.t_long :
					return Keywords.LONG ;
				case IToken.t_mutable :
					return Keywords.MUTABLE ;
				case IToken.t_namespace :
					return Keywords.NAMESPACE ;
				case IToken.t_new :
					return Keywords.NEW ;
				case IToken.t_not :
					return Keywords.NOT ;
				case IToken.t_not_eq :
					return Keywords.NOT_EQ; 
				case IToken.t_operator :
					return Keywords.OPERATOR ;
				case IToken.t_or :
					return Keywords.OR ;
				case IToken.t_or_eq :
					return Keywords.OR_EQ;
				case IToken.t_private :
					return Keywords.PRIVATE ;
				case IToken.t_protected :
					return Keywords.PROTECTED ;
				case IToken.t_public :
					return Keywords.PUBLIC ;
				case IToken.t_register :
					return Keywords.REGISTER ;
				case IToken.t_reinterpret_cast :
					return Keywords.REINTERPRET_CAST ;
				case IToken.t_return :
					return Keywords.RETURN ;
				case IToken.t_short :
					return Keywords.SHORT ;
				case IToken.t_sizeof :
					return Keywords.SIZEOF ;
				case IToken.t_static :
					return Keywords.STATIC ;
				case IToken.t_static_cast :
					return Keywords.STATIC_CAST ;
				case IToken.t_signed :
					return Keywords.SIGNED ;
				case IToken.t_struct :
					return Keywords.STRUCT ;
				case IToken.t_switch :
					return Keywords.SWITCH ;
				case IToken.t_template :
					return Keywords.TEMPLATE ;
				case IToken.t_this :
					return Keywords.THIS ;
				case IToken.t_throw :
					return Keywords.THROW ;
				case IToken.t_true :
					return Keywords.TRUE ;
				case IToken.t_try :
					return Keywords.TRY ;
				case IToken.t_typedef :
					return Keywords.TYPEDEF ;
				case IToken.t_typeid :
					return Keywords.TYPEID ;
				case IToken.t_typename :
					return Keywords.TYPENAME ;
				case IToken.t_union :
					return Keywords.UNION ;
				case IToken.t_unsigned :
					return Keywords.UNSIGNED ;
				case IToken.t_using :
					return Keywords.USING ;
				case IToken.t_virtual :
					return Keywords.VIRTUAL ;
				case IToken.t_void :
					return Keywords.VOID ;
				case IToken.t_volatile :
					return Keywords.VOLATILE;
				case IToken.t_wchar_t :
					return Keywords.WCHAR_T ;
				case IToken.t_while :
					return Keywords.WHILE ;
				case IToken.t_xor :
					return Keywords.XOR ;
				case IToken.t_xor_eq :
					return Keywords.XOR_EQ ;
				case IToken.t__Bool :
					return Keywords._BOOL ;
				case IToken.t__Complex :
					return Keywords._COMPLEX ;
				case IToken.t__Imaginary :
					return Keywords._IMAGINARY ;
				case IToken.t_restrict :
					return Keywords.RESTRICT ;
				case IScanner.tPOUND:
					return "#"; //$NON-NLS-1$
				case IScanner.tPOUNDPOUND:
					return "##"; //$NON-NLS-1$
				
				default :
					// we should never get here!
					// assert false : getType();
					return ""; //$NON-NLS-1$ 
		}			
	}
   
	
	/* (non-Javadoc)
	 * @see org.eclipse.cdt.core.parser.IToken#setImage()
	 */
	public void setImage( String i ) {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see org.eclipse.cdt.core.parser.IToken#getCharImage()
	 */
	public char[] getCharImage() {
		return getImage().toCharArray(); //TODO - fix me!
	}

	/* (non-Javadoc)
	 * @see org.eclipse.cdt.core.parser.IToken#setImage(char[])
	 */
	public void setImage(char[] i) {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see org.eclipse.cdt.core.parser.ITokenDuple#toCharArray()
	 */
	public char[] toCharArray() {
		return getCharImage();
	}

}
