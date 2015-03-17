package panda.ast;

import polyglot.ast.Ext;
import polyglot.ast.ExtFactory;
import polyglot.ext.jl7.ast.JL7ExtFactory;

/**
 * Extension factory for panda extension.
 */
public interface PandaExtFactory extends JL7ExtFactory {

  Ext extModeOrder();

  Ext extModesDecl();

  Ext extModeParamTypeNode();

  Ext extModeTypeNode();

}
