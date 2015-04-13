package panda.ast;

import polyglot.ast.AbstractExtFactory_c;
import polyglot.ast.Ext;
import polyglot.ast.ExtFactory;

import polyglot.ext.jl7.ast.JL7AbstractExtFactory_c;

public abstract class PandaAbstractExtFactory_c extends JL7AbstractExtFactory_c
        implements PandaExtFactory {

    public PandaAbstractExtFactory_c() {
        super();
    }

    public PandaAbstractExtFactory_c(ExtFactory nextExtFactory) {
        super(nextExtFactory);
    }

    public final Ext extAmbModeTypeInstantiation() {
      Ext e = extAmbModeTypeInstantiationImpl();
      if (nextExtFactory() != null) {
        Ext e2;
        if (nextExtFactory() instanceof PandaExtFactory) {
          e2 = ((PandaExtFactory) nextExtFactory()).extAmbModeTypeInstantiation();
        } else {
          e2 = nextExtFactory().extTypeNode();
        }
        e = composeExts(e, e2);
      }
      return postExtAmbModeTypeInstantiation(e);
    }

    protected Ext extAmbModeTypeInstantiationImpl() { 
      return extTypeNodeImpl();
    }

    protected Ext postExtAmbModeTypeInstantiation(Ext e) { 
      return postExtTypeNode(e);
    }

    public final Ext extModeOrder() {
      Ext e = extModeOrderImpl();
      if (nextExtFactory() != null) {
        Ext e2;
        if (nextExtFactory() instanceof PandaExtFactory) {
          e2 = ((PandaExtFactory) nextExtFactory()).extModeOrder();
        } else {
          e2 = nextExtFactory().extTerm();
        }
        e = composeExts(e, e2);
      }
      return postExtModeOrder(e);
    }

    protected Ext extModeOrderImpl() { 
      return extTermImpl();
    }

    protected Ext postExtModeOrder(Ext e) { 
      return postExtTerm(e);
    }

    public final Ext extModesDecl() {
      Ext e = extModesDeclImpl();
      if (nextExtFactory() != null) {
        Ext e2;
        if (nextExtFactory() instanceof PandaExtFactory) {
          e2 = ((PandaExtFactory) nextExtFactory()).extModesDecl();
        } else {
          e2 = nextExtFactory().extTerm();
        }
        e = composeExts(e, e2);
      }
      return postExtModesDecl(e);
    }

    protected Ext extModesDeclImpl() { 
      return extTermImpl();
    }

    protected Ext postExtModesDecl(Ext e) { 
      return postExtTerm(e);
    }

    public final Ext extModeParamTypeNode() {
      Ext e = extModeParamTypeNodeImpl();
      if (nextExtFactory() != null) {
        Ext e2;
        if (nextExtFactory() instanceof PandaExtFactory) {
          e2 = ((PandaExtFactory) nextExtFactory()).extModeParamTypeNode();
        } else {
          e2 = nextExtFactory().extTypeNode();
        }
        e = composeExts(e, e2);
      }
      return postExtModeParamTypeNode(e);
    }

    protected Ext extModeParamTypeNodeImpl() { 
      return extTypeNodeImpl();
    }

    protected Ext postExtModeParamTypeNode(Ext e) { 
      return postExtTypeNode(e);
    }

    public final Ext extModeTypeNode() {
      Ext e = extModeTypeNodeImpl();
      if (nextExtFactory() != null) {
        Ext e2;
        if (nextExtFactory() instanceof PandaExtFactory) {
          e2 = ((PandaExtFactory) nextExtFactory()).extModeTypeNode();
        } else {
          e2 = nextExtFactory().extTypeNode();
        }
        e = composeExts(e, e2);
      }
      return postExtModeTypeNode(e);
    }

    protected Ext extModeTypeNodeImpl() { 
      return extTypeNodeImpl();
    }

    protected Ext postExtModeTypeNode(Ext e) { 
      return postExtTypeNode(e);
    }

    public final Ext extModeValueNode() {
      Ext e = extModeValueNodeImpl();
      if (nextExtFactory() != null) {
        Ext e2;
        if (nextExtFactory() instanceof PandaExtFactory) {
          e2 = ((PandaExtFactory) nextExtFactory()).extModeValueNode();
        } else {
          e2 = nextExtFactory().extTypeNode();
        }
        e = composeExts(e, e2);
      }
      return postExtModeValueNode(e);
    }

    protected Ext extModeValueNodeImpl() { 
      return extTypeNodeImpl();
    }

    protected Ext postExtModeValueNode(Ext e) { 
      return postExtTypeNode(e);
    }


}
