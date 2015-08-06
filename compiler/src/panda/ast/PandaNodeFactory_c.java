package panda.ast;

import polyglot.ext.jl7.ast.JL7NodeFactory_c;

import polyglot.ast.*;
import polyglot.util.*;
import polyglot.types.*;

import polyglot.ext.jl5.ast.*;

import java.util.*;


/**
 * NodeFactory for panda extension.
 */
public class PandaNodeFactory_c extends JL7NodeFactory_c implements PandaNodeFactory {
    public PandaNodeFactory_c(PandaLang lang, PandaExtFactory extFactory) {
        super(lang, extFactory);
    }

    @Override
    public PandaExtFactory extFactory() {
        return (PandaExtFactory) super.extFactory();
    } 

    @Override
    public Call Call(Position pos,  
                     Receiver target, 
                     List<TypeNode> typeArgs, 
                     Id name, 
                     List<Expr> args) {
      Call n = super.Call(pos, target, typeArgs, name, args);
      return n;
    } 
    
    @Override
    public ClassDecl ClassDecl(Position pos, 
                               Flags flags, 
                               List<AnnotationElem> annotations, 
                               Id name, 
                               TypeNode superType, 
                               List<TypeNode> interfaces, 
                               ClassBody body, 
                               List<ParamTypeNode> paramTypes,
                               List<ModeParamTypeNode> modeParams) {
      ClassDecl n =
        super.ClassDecl(pos, flags, annotations, name, superType, interfaces, body, paramTypes);
      PandaClassDeclExt ext = (PandaClassDeclExt) PandaExt.ext(n);
      ext.modeParams = CollectionUtil.nonNullList(modeParams);
      return n;
    } 

    public ConstructorDecl ConstructorDecl(Position pos, 
                                           Flags flags, 
                                           List<AnnotationElem> annotations, 
                                           Id name, 
                                           List<Formal> formals, 
                                           List<TypeNode> throwTypes, 
                                           Block body, 
                                           List<ParamTypeNode> typeParams,
                                           List<ModeParamTypeNode> modeParams) {
      ConstructorDecl n = 
        super.ConstructorDecl(pos, flags, annotations, name, formals, throwTypes, body, typeParams);
      PandaConstructorDeclExt ext = (PandaConstructorDeclExt) PandaExt.ext(n);
      ext.modeParams = CollectionUtil.nonNullList(modeParams);
      return n;
    } 

    @Override
    public MethodDecl MethodDecl(Position pos, 
                                 Flags flags, 
                                 List<AnnotationElem> annotations, 
                                 TypeNode returnType, 
                                 Id name, 
                                 List<Formal> formals, 
                                 List<TypeNode> throwTypes, 
                                 Block body, 
                                 List<ParamTypeNode> typeParams,
                                 List<ModeParamTypeNode> modeParams) {
      MethodDecl n = 
        super.MethodDecl(pos, flags, annotations, returnType, name, formals, throwTypes, body, typeParams);
      PandaMethodDeclExt ext = (PandaMethodDeclExt) PandaExt.ext(n);
      ext.modeParams = CollectionUtil.nonNullList(modeParams);
      return n;
    } 

    public SourceFile SourceFile(Position pos, 
                                 PackageNode packageName, 
                                 List<Import> imports, 
                                 List<TopLevelDecl> decls,
                                 ModesDecl modesDecl) {
      SourceFile n = 
        super.SourceFile(pos, packageName, CollectionUtil.nonNullList(imports), CollectionUtil.nonNullList(decls));
      PandaSourceFileExt ext = (PandaSourceFileExt) PandaExt.ext(n);
      ext.modesDecl = modesDecl;
      return n;
    }

    public AmbModeTypeInstantiation AmbModeTypeInstantiation(Position pos, 
                                                             TypeNode base) {
      List<ModeTypeNode> modeTypeArgs = Collections.emptyList();
      return this.AmbModeTypeInstantiation(pos, base, modeTypeArgs);
    }

    public AmbModeTypeInstantiation AmbModeTypeInstantiation(Position pos, 
                                                             TypeNode base,
                                                             List<ModeTypeNode> modeTypeArgs) {
      AmbModeTypeInstantiation n = 
        new AmbModeTypeInstantiation_c(pos, base, modeTypeArgs);
      n = ext(n, extFactory().extAmbModeTypeInstantiation());
      return n;
    }

    public AttributeDecl AttributeDecl(Position pos, Block body) {
      AttributeDecl n = new AttributeDecl_c(pos, body);
      n = ext(n, extFactory().extAttributeDecl());
      return n;
    }

    public CopyDecl CopyDecl(Position pos, Block body) {
      CopyDecl n = new CopyDecl_c(pos, body);
      n = ext(n, extFactory().extCopyDecl());
      return n;
    }

    public McaseFieldDecl McaseFieldDecl(Position pos, Id field, Expr init) {
      McaseFieldDecl n = new McaseFieldDecl_c(pos, field, init);
      n = ext(n, extFactory().extMcaseFieldDecl());
      return n;
    } 

    public McaseLit McaseLit(Position pos, TypeNode mcaseTypeNode, List<McaseFieldDecl> fields) {
      McaseLit n = new McaseLit_c(pos, mcaseTypeNode, fields);
      n = ext(n, extFactory().extMcaseLit());
      return n;
    }

    public McaseTypeNode McaseTypeNode(Position pos, TypeNode base) {
      McaseTypeNode n = new McaseTypeNode_c(pos, base);
      n = ext(n, extFactory().extMcaseTypeNode());
      return n;
    }

    public ModeOrder ModeOrder(Position pos, String lower, String upper) {
      ModeOrder n = new ModeOrder_c(pos, lower, upper);
      n = ext(n, extFactory().extModeOrder());
      return n;
    }

    public ModesDecl ModesDecl(Position pos, List<ModeOrder> orders) {
      ModesDecl n = new ModesDecl_c(pos, orders);
      n = ext(n, extFactory().extModesDecl());
      return n;
    }

    public ModeParamTypeNode ModeParamTypeNode(Position pos, Id id, List<ModeTypeNode> bounds) {
      ModeParamTypeNode n = new ModeParamTypeNode_c(pos, id, bounds);
      n = ext(n, extFactory().extModeParamTypeNode());
      return n;
    }

    public ModeTypeNode ModeTypeNode(Position pos, String name) {
      ModeTypeNode n = new ModeTypeNode_c(pos, name);
      n = ext(n, extFactory().extModeTypeNode());
      return n;
    }

    public ModeValue ModeValue(Position pos, ModeTypeNode modeTypeNode) {
      ModeValue n = new ModeValue_c(pos, modeTypeNode);
      n = ext(n, extFactory().extModeValue());
      return n;
    }

    public SnapshotExpr SnapshotExpr(Position pos, Expr target, Expr lower, Expr upper) {
      SnapshotExpr n = new SnapshotExpr_c(pos, target, lower, upper, false);
      n = ext(n, extFactory().extSnapshotExpr());
      return n;
    }

    public SnapshotExpr SnapshotExpr(Position pos, Expr target, Expr lower, Expr upper, boolean saveMode) {
      SnapshotExpr n = new SnapshotExpr_c(pos, target, lower, upper, saveMode);
      n = ext(n, extFactory().extSnapshotExpr());
      return n;
    }

}
