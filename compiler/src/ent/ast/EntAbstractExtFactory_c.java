package ent.ast;

import polyglot.ast.AbstractExtFactory_c;
import polyglot.ast.Ext;
import polyglot.ast.ExtFactory;

import polyglot.ext.jl7.ast.JL7AbstractExtFactory_c;

public abstract class EntAbstractExtFactory_c
    extends JL7AbstractExtFactory_c implements EntExtFactory {

  public EntAbstractExtFactory_c() { super(); }

  public EntAbstractExtFactory_c(ExtFactory nextExtFactory) { super(nextExtFactory); }

  public final Ext extAmbModeTypeInstantiation() {
    Ext e = extAmbModeTypeInstantiationImpl();
    if (nextExtFactory() != null) {
      Ext e2;
      if (nextExtFactory() instanceof EntExtFactory) {
        e2 = ((EntExtFactory)nextExtFactory()).extAmbModeTypeInstantiation();
      } else {
        e2 = nextExtFactory().extTypeNode();
      }
      e = composeExts(e, e2);
    }
    return postExtAmbModeTypeInstantiation(e);
  }

  protected Ext extAmbModeTypeInstantiationImpl() { return extTypeNodeImpl(); }

  protected Ext postExtAmbModeTypeInstantiation(Ext e) { return postExtTypeNode(e); }

  public final Ext extAttributeDecl() {
    Ext e = extAttributeDeclImpl();
    if (nextExtFactory() != null) {
      Ext e2;
      if (nextExtFactory() instanceof EntExtFactory) {
        e2 = ((EntExtFactory)nextExtFactory()).extAttributeDecl();
      } else {
        e2 = nextExtFactory().extTerm();
      }
      e = composeExts(e, e2);
    }
    return postExtAttributeDecl(e);
  }

  protected Ext extAttributeDeclImpl() { return extTermImpl(); }

  protected Ext postExtAttributeDecl(Ext e) { return postExtTerm(e); }

  public final Ext extCopyDecl() {
    Ext e = extCopyDeclImpl();
    if (nextExtFactory() != null) {
      Ext e2;
      if (nextExtFactory() instanceof EntExtFactory) {
        e2 = ((EntExtFactory)nextExtFactory()).extCopyDecl();
      } else {
        e2 = nextExtFactory().extTerm();
      }
      e = composeExts(e, e2);
    }
    return postExtCopyDecl(e);
  }

  protected Ext extCopyDeclImpl() { return extTermImpl(); }

  protected Ext postExtCopyDecl(Ext e) { return postExtTerm(e); }

  public final Ext extMcaseFieldDecl() {
    Ext e = extMcaseFieldDeclImpl();
    if (nextExtFactory() != null) {
      Ext e2;
      if (nextExtFactory() instanceof EntExtFactory) {
        e2 = ((EntExtFactory)nextExtFactory()).extMcaseFieldDecl();
      } else {
        e2 = nextExtFactory().extTerm();
      }
      e = composeExts(e, e2);
    }
    return postExtMcaseFieldDecl(e);
  }

  protected Ext extMcaseFieldDeclImpl() { return extTermImpl(); }

  protected Ext postExtMcaseFieldDecl(Ext e) { return postExtTerm(e); }

  public final Ext extMcaseLit() {
    Ext e = extMcaseLitImpl();
    if (nextExtFactory() != null) {
      Ext e2;
      if (nextExtFactory() instanceof EntExtFactory) {
        e2 = ((EntExtFactory)nextExtFactory()).extMcaseLit();
      } else {
        e2 = nextExtFactory().extLit();
      }
      e = composeExts(e, e2);
    }
    return postExtMcaseLit(e);
  }

  protected Ext extMcaseLitImpl() { return extLitImpl(); }

  protected Ext postExtMcaseLit(Ext e) { return postExtLit(e); }

  public final Ext extMcaseTypeNode() {
    Ext e = extMcaseTypeNodeImpl();
    if (nextExtFactory() != null) {
      Ext e2;
      if (nextExtFactory() instanceof EntExtFactory) {
        e2 = ((EntExtFactory)nextExtFactory()).extMcaseTypeNode();
      } else {
        e2 = nextExtFactory().extTypeNode();
      }
      e = composeExts(e, e2);
    }
    return postExtMcaseTypeNode(e);
  }

  protected Ext extMcaseTypeNodeImpl() { return extTypeNodeImpl(); }

  protected Ext postExtMcaseTypeNode(Ext e) { return postExtTypeNode(e); }

  public final Ext extModeOrder() {
    Ext e = extModeOrderImpl();
    if (nextExtFactory() != null) {
      Ext e2;
      if (nextExtFactory() instanceof EntExtFactory) {
        e2 = ((EntExtFactory)nextExtFactory()).extModeOrder();
      } else {
        e2 = nextExtFactory().extTerm();
      }
      e = composeExts(e, e2);
    }
    return postExtModeOrder(e);
  }

  protected Ext extModeOrderImpl() { return extTermImpl(); }

  protected Ext postExtModeOrder(Ext e) { return postExtTerm(e); }

  public final Ext extModesDecl() {
    Ext e = extModesDeclImpl();
    if (nextExtFactory() != null) {
      Ext e2;
      if (nextExtFactory() instanceof EntExtFactory) {
        e2 = ((EntExtFactory)nextExtFactory()).extModesDecl();
      } else {
        e2 = nextExtFactory().extTerm();
      }
      e = composeExts(e, e2);
    }
    return postExtModesDecl(e);
  }

  protected Ext extModesDeclImpl() { return extTermImpl(); }

  protected Ext postExtModesDecl(Ext e) { return postExtTerm(e); }

  public final Ext extModeParamTypeNode() {
    Ext e = extModeParamTypeNodeImpl();
    if (nextExtFactory() != null) {
      Ext e2;
      if (nextExtFactory() instanceof EntExtFactory) {
        e2 = ((EntExtFactory)nextExtFactory()).extModeParamTypeNode();
      } else {
        e2 = nextExtFactory().extTypeNode();
      }
      e = composeExts(e, e2);
    }
    return postExtModeParamTypeNode(e);
  }

  protected Ext extModeParamTypeNodeImpl() { return extTypeNodeImpl(); }

  protected Ext postExtModeParamTypeNode(Ext e) { return postExtTypeNode(e); }

  public final Ext extModeTypeNode() {
    Ext e = extModeTypeNodeImpl();
    if (nextExtFactory() != null) {
      Ext e2;
      if (nextExtFactory() instanceof EntExtFactory) {
        e2 = ((EntExtFactory)nextExtFactory()).extModeTypeNode();
      } else {
        e2 = nextExtFactory().extTypeNode();
      }
      e = composeExts(e, e2);
    }
    return postExtModeTypeNode(e);
  }

  protected Ext extModeTypeNodeImpl() { return extTypeNodeImpl(); }

  protected Ext postExtModeTypeNode(Ext e) { return postExtTypeNode(e); }

  public final Ext extModeValue() {
    Ext e = extModeValueImpl();
    if (nextExtFactory() != null) {
      Ext e2;
      if (nextExtFactory() instanceof EntExtFactory) {
        e2 = ((EntExtFactory)nextExtFactory()).extModeValue();
      } else {
        e2 = nextExtFactory().extExpr();
      }
      e = composeExts(e, e2);
    }
    return postExtModeValue(e);
  }

  protected Ext extModeValueImpl() { return extExprImpl(); }

  protected Ext postExtModeValue(Ext e) { return postExtExpr(e); }

  public final Ext extSnapshotExpr() {
    Ext e = extSnapshotExprImpl();
    if (nextExtFactory() != null) {
      Ext e2;
      if (nextExtFactory() instanceof EntExtFactory) {
        e2 = ((EntExtFactory)nextExtFactory()).extSnapshotExpr();
      } else {
        e2 = nextExtFactory().extExpr();
      }
      e = composeExts(e, e2);
    }
    return postExtSnapshotExpr(e);
  }

  protected Ext extSnapshotExprImpl() { return extExprImpl(); }

  protected Ext postExtSnapshotExpr(Ext e) { return postExtExpr(e); }
}
