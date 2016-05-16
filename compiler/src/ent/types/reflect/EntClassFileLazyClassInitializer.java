package ent.types.reflect;

import ent.types.*;

import polyglot.types.*;
import polyglot.types.reflect.*;
import polyglot.ext.jl5.types.reflect.*;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class EntClassFileLazyClassInitializer extends JL5ClassFileLazyClassInitializer {
  public EntClassFileLazyClassInitializer(ClassFile file, TypeSystem ts) { super(file, ts); }

  /*
  @Override
  protected ParsedClassType createType() throws SemanticException {
    ParsedClassType ct = super.createType();

    System.out.println("Have: " + ct);
    return ct;
  }
  */

  @Override
  protected FieldInstance fieldInstance(Field field, ClassType ct) {
    FieldInstance fi = super.fieldInstance(field, ct);
    EntTypeSystem ts = (EntTypeSystem)this.ts;

    Type t = ts.createModeSubst(fi.type(), Arrays.<Type>asList(ts.WildcardModeType()));

    fi = fi.type(t);

    return fi;
  }

  @Override
  public void initSuperclass() {
    if (this.superclassInitialized) {
      return;
    }

    EntTypeSystem ts = (EntTypeSystem)this.ts;
    super.initSuperclass();
    Type superT = this.ct.superType();
    if (superT == null) {
      return;
    }

    if (!(superT instanceof ModeSubstType)) {
      superT = ts.createModeSubst(superT, Arrays.<Type>asList(ts.WildcardModeType()));
      ct.superType(superT);
    }
  }

  @Override
  protected MethodInstance methodInstance(Method method, ClassType ct) {
    EntMethodInstance mi = (EntMethodInstance)super.methodInstance(method, ct);
    EntTypeSystem ts = (EntTypeSystem)this.ts;

    // Refine method to have mode subst types
    Type t = ts.createModeSubst(mi.returnType(), Arrays.<Type>asList(ts.WildcardModeType()));

    mi = (EntMethodInstance)mi.returnType(
        ts.createModeSubst(mi.returnType(), Arrays.<Type>asList(ts.WildcardModeType())));

    List<Type> formalTs = new ArrayList<>(mi.formalTypes());
    for (int i = 0; i < formalTs.size(); ++i) {
      Type st = ts.createModeSubst(formalTs.get(i), Arrays.<Type>asList(ts.WildcardModeType()));
      formalTs.set(i, st);
    }

    mi = (EntMethodInstance)mi.formalTypes(formalTs);

    return mi;
  }
}
