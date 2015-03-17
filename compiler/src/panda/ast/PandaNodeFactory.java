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

  FieldDecl FieldDecl(Position pos, 
                      Flags flags, 
                      List<AnnotationElem> annotations, 
                      TypeNode type, 
                      Id name, 
                      Expr init,
                      ModeTypeNode modeTypeNode); 

  Formal Formal(Position pos, 
                Flags flags, 
                List<AnnotationElem> annotations, 
                TypeNode type, 
                Id name, 
                boolean var_args,
                ModeTypeNode modeTypeNode);

  LocalDecl LocalDecl(Position pos, 
                      Flags flags, 
                      List<AnnotationElem> annotations, 
                      TypeNode type, 
                      Id name, 
                      Expr init,
                      ModeTypeNode modeTypeNode); 

  SourceFile SourceFile(Position pos, 
                        PackageNode packageName, 
                        List<Import> imports, 
                        List<TopLevelDecl> decls,
                        ModesDecl modesDecl);

  // Factor Methods for New Nodes
  ModeOrder ModeOrder(Position pos, String lower, String upper);

  ModesDecl ModesDecl(Position pos, List<ModeOrder> orders);

  ModeParamTypeNode ModeParamTypeNode(Position pos, Id id);
  
  ModeTypeNode ModeTypeNode(Position pos, Id id);




}
