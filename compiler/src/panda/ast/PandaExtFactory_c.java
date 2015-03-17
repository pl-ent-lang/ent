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
  protected Ext extNodeImpl() {
    return new PandaExt();
  }

  @Override
  protected Ext extSourceFileImpl() {
    return new PandaSourceFileExt();
  }

  @Override 
  protected Ext extClassDeclImpl() {
    return new PandaClassDeclExt();
  }

  @Override 
  protected Ext extLocalDeclImpl() {
    return new PandaLocalDeclExt();
  }

  @Override 
  protected Ext extFormalImpl() {
    return new PandaFormalExt();
  }

  @Override 
  protected Ext extFieldDeclImpl() {
    return new PandaFieldDeclExt();
  }

}
