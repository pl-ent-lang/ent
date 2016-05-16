package ent.ast;

import polyglot.ast.Ext;
import polyglot.ast.ExtFactory;

public final class EntExtFactory_c extends EntAbstractExtFactory_c {

  public EntExtFactory_c() { super(); }

  public EntExtFactory_c(ExtFactory nextExtFactory) { super(nextExtFactory); }

  @Override
  protected Ext extAnnotationElemDeclImpl() {
    return new EntAnnotationElemDeclExt();
  }

  @Override
  protected Ext extNormalAnnotationElemImpl() {
    return new EntAnnotationElemExt();
  }

  @Override
  protected Ext extMarkerAnnotationElemImpl() {
    return new EntAnnotationElemExt();
  }

  @Override
  protected Ext extSingleElementAnnotationElemImpl() {
    return new EntAnnotationElemExt();
  }

  @Override
  protected Ext extCallImpl() {
    return new EntCallExt();
  }

  @Override
  protected Ext extCanonicalTypeNodeImpl() {
    return new EntCanonicalTypeNodeExt();
  }

  @Override
  protected Ext extCaseImpl() {
    return new EntCaseExt();
  }

  @Override
  protected Ext extClassDeclImpl() {
    return new EntClassDeclExt();
  }

  @Override
  protected Ext extConstructorDeclImpl() {
    return new EntConstructorDeclExt();
  }

  @Override
  protected Ext extEnumConstantDeclImpl() {
    return new EntEnumConstantDeclExt();
  }

  @Override
  protected Ext extEnumConstantImpl() {
    return new EntEnumConstantExt();
  }

  @Override
  protected Ext extElementValuePairImpl() {
    return new EntElementValuePairExt();
  }

  @Override
  protected Ext extExtendedForImpl() {
    return new EntExtendedForExt();
  }

  @Override
  protected Ext extFieldImpl() {
    return new EntFieldExt();
  }

  @Override
  protected Ext extFieldDeclImpl() {
    return new EntFieldDeclExt();
  }

  @Override
  protected Ext extFormalImpl() {
    return new EntFormalExt();
  }

  @Override
  protected Ext extLitImpl() {
    return new EntLitExt();
  }

  @Override
  protected Ext extLocalDeclImpl() {
    return new EntLocalDeclExt();
  }

  @Override
  protected Ext extModesDeclImpl() {
    return new EntModesDeclExt();
  }

  @Override
  protected Ext extMethodDeclImpl() {
    return new EntMethodDeclExt();
  }

  @Override
  protected Ext extNewImpl() {
    return new EntNewExt();
  }

  @Override
  protected Ext extNodeImpl() {
    return new EntExt();
  }

  @Override
  protected Ext extParamTypeNodeImpl() {
    return new EntParamTypeNodeExt();
  }

  @Override
  protected Ext extReturnImpl() {
    return new EntReturnExt();
  }

  @Override
  protected Ext extSourceFileImpl() {
    return new EntSourceFileExt();
  }

  @Override
  protected Ext extSwitchImpl() {
    return new EntSwitchExt();
  }
}
