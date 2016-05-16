package ent.ast;

import polyglot.ast.Ext;
import polyglot.ast.ExtFactory;
import polyglot.ext.jl7.ast.JL7ExtFactory;

/**
 * Extension factory for ent extension.
 */
public interface EntExtFactory extends JL7ExtFactory {

  Ext extAmbModeTypeInstantiation();

  Ext extAttributeDecl();

  Ext extCopyDecl();

  Ext extMcaseFieldDecl();

  Ext extMcaseLit();

  Ext extMcaseTypeNode();

  Ext extModeOrder();

  Ext extModesDecl();

  Ext extModeParamTypeNode();

  Ext extModeTypeNode();

  Ext extModeValue();

  Ext extSnapshotExpr();
}
