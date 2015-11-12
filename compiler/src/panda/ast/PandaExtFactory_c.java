package panda.ast;

import polyglot.ast.Ext;
import polyglot.ast.ExtFactory;

public final class PandaExtFactory_c extends PandaAbstractExtFactory_c {

  public PandaExtFactory_c() {
    super();
  }

  public PandaExtFactory_c(ExtFactory nextExtFactory) {
    super(nextExtFactory);
  }

  @Override 
  protected Ext extAnnotationElemDeclImpl() {
    return new PandaAnnotationElemDeclExt();
  } 

  @Override 
  protected Ext extNormalAnnotationElemImpl() {
    return new PandaAnnotationElemExt();
  } 

  @Override 
  protected Ext extMarkerAnnotationElemImpl() {
    return new PandaAnnotationElemExt();
  } 

  @Override 
  protected Ext extSingleElementAnnotationElemImpl() {
    return new PandaAnnotationElemExt();
  } 

  @Override 
  protected Ext extCallImpl() {
    return new PandaCallExt();
  } 

  @Override 
  protected Ext extCanonicalTypeNodeImpl() {
    return new PandaCanonicalTypeNodeExt();
  } 

  @Override 
  protected Ext extCaseImpl() {
    return new PandaCaseExt();
  }

  @Override 
  protected Ext extClassDeclImpl() {
    return new PandaClassDeclExt();
  }

  @Override 
  protected Ext extConstructorDeclImpl() {
    return new PandaConstructorDeclExt();
  } 

  @Override 
  protected Ext extEnumConstantDeclImpl() {
    return new PandaEnumConstantDeclExt();
  } 

  @Override 
  protected Ext extEnumConstantImpl() {
    return new PandaEnumConstantExt();
  } 

  @Override 
  protected Ext extElementValuePairImpl() {
    return new PandaElementValuePairExt();
  } 

  @Override 
  protected Ext extExtendedForImpl() {
    return new PandaExtendedForExt();
  } 

  @Override 
  protected Ext extFieldImpl() {
    return new PandaFieldExt();
  } 

  @Override 
  protected Ext extFieldDeclImpl() {
    return new PandaFieldDeclExt();
  } 

  @Override 
  protected Ext extFormalImpl() {
    return new PandaFormalExt();
  } 

  @Override
  protected Ext extLitImpl() {
    return new PandaLitExt();
  }

  @Override
  protected Ext extLocalDeclImpl() {
    return new PandaLocalDeclExt();
  }

  @Override
  protected Ext extMethodDeclImpl() {
    return new PandaMethodDeclExt();
  }

  @Override
  protected Ext extNewImpl() {
    return new PandaNewExt();
  } 

  @Override
  protected Ext extNodeImpl() {
    return new PandaExt();
  } 

  @Override
  protected Ext extParamTypeNodeImpl() {
    return new PandaParamTypeNodeExt();
  }

  @Override
  protected Ext extReturnImpl() {
    return new PandaReturnExt();
  }

  @Override
  protected Ext extSourceFileImpl() {
    return new PandaSourceFileExt();
  }

  @Override
  protected Ext extSwitchImpl() {
    return new PandaSwitchExt();
  }

}
