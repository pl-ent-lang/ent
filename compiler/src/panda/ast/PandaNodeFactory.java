package panda.ast;

import polyglot.ast.*;
import polyglot.types.Flags;
import polyglot.types.Package;
import polyglot.types.Type;
import polyglot.types.Qualifier;
import polyglot.util.*;
import java.util.*;

import polyglot.ext.jl5.ast.AnnotationElem;
import polyglot.ext.jl5.ast.ParamTypeNode;
import polyglot.ext.jl7.ast.JL7NodeFactory;

/**
 * NodeFactory for panda extension.
 */
public interface PandaNodeFactory extends JL7NodeFactory {

  // Factory Methods for Ext Nodes
  
  ClassDecl ClassDecl(Position pos,  
                      Flags flags, 
                      List<AnnotationElem> annotations, 
                      Id name, 
                      TypeNode superType, 
                      List<TypeNode> interfaces, 
                      ClassBody body,
                      List<ParamTypeNode> paramTypes,
                      List<ModeParamTypeNode> modeParamTypes); 

  ConstructorDecl ConstructorDecl(Position pos, 
                                  Flags flags, 
                                  List<AnnotationElem> annotations, 
                                  Id name, 
                                  List<Formal> formals, 
                                  List<TypeNode> throwTypes, 
                                  Block body, 
                                  List<ParamTypeNode> typeParams,
                                  List<ModeParamTypeNode> modeParamTypes);

  MethodDecl MethodDecl(Position pos, 
                        Flags flags, 
                        List<AnnotationElem> annotations, 
                        TypeNode returnType, 
                        Id name, 
                        List<Formal> formals, 
                        List<TypeNode> throwTypes, 
                        Block body, 
                        List<ParamTypeNode> typeParams,
                        List<ModeParamTypeNode> modeParams); 

  SourceFile SourceFile(Position pos, 
                        PackageNode packageName, 
                        List<Import> imports, 
                        List<TopLevelDecl> decls,
                        ModesDecl modesDecl);

  // Factory Methods for New Nodes
  AmbModeTypeInstantiation AmbModeTypeInstantiation(Position pos, TypeNode base);

  AmbModeTypeInstantiation AmbModeTypeInstantiation(Position pos, 
                                                    TypeNode base, 
                                                    List<ModeTypeNode> modeTypeArgs);

  AttributeDecl AttributeDecl(Position pos, Block body);

  CopyDecl CopyDecl(Position pos, Block body);

  McaseFieldDecl McaseFieldDecl(Position pos, Id field, Expr init);

  McaseLit McaseLit(Position pos, TypeNode mcaseTypeNode, List<McaseFieldDecl> fields);

  McaseTypeNode McaseTypeNode(Position pos, TypeNode base);
  
  ModeOrder ModeOrder(Position pos, String lower, String upper);

  ModesDecl ModesDecl(Position pos, List<ModeOrder> orders);

  ModeParamTypeNode ModeParamTypeNode(Position pos, Id id, boolean isDynRecv, List<ModeTypeNode> bounds);
  
  ModeTypeNode ModeTypeNode(Position pos, String name);

  ModeValue ModeValue(Position pos, ModeTypeNode modeTypeNode);

  SnapshotExpr SnapshotExpr(Position pos, Expr target, Expr lower, Expr upper);

  SnapshotExpr SnapshotExpr(Position pos, Expr target, Expr lower, Expr upper, boolean saveMode);

}
