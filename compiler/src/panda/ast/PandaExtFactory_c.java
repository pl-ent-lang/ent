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
  protected Ext extCallImpl() {
    return new PandaCallExt();
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
  protected Ext extLitImpl() {
    return new PandaLitExt();
  }

  @Override
  protected Ext extMethodDeclImpl() {
    return new PandaMethodDeclExt();
  }

  @Override
  protected Ext extNodeImpl() {
    return new PandaExt();
  } 

  @Override
  protected Ext extReturnImpl() {
    return new PandaReturnExt();
  }

  @Override
  protected Ext extSourceFileImpl() {
    return new PandaSourceFileExt();
  }

}
