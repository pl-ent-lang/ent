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
  protected Ext extBooleanLitImpl() {
    return new PandaBooleanLitExt();
  } 

  @Override
  protected Ext extCharLitImpl() {
    return new PandaCharLitExt();
  } 

  @Override 
  protected Ext extClassDeclImpl() {
    return new PandaClassDeclExt();
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
  protected Ext extNullLitImpl() {
    return new PandaNullLitExt();
  } 

  @Override
  protected Ext extNumLitImpl() {
    return new PandaNumLitExt();
  }

  @Override
  protected Ext extSourceFileImpl() {
    return new PandaSourceFileExt();
  }

  @Override
  protected Ext extStringLitImpl() {
    return new PandaStringLitExt();
  } 
}
