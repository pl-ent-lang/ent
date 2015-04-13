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

  SourceFile SourceFile(Position pos, 
                        PackageNode packageName, 
                        List<Import> imports, 
                        List<TopLevelDecl> decls,
                        ModesDecl modesDecl);

  // Factory Methods for New Nodes
  AmbModeTypeInstantiation AmbModeTypeInstantiation(Position pos, 
                                                    ModeTypeNode baseMode, 
                                                    List<ModeValueNode> modeTypeArguments);
  
  ModeOrder ModeOrder(Position pos, String lower, String upper);

  ModesDecl ModesDecl(Position pos, List<ModeOrder> orders);

  ModeParamTypeNode ModeParamTypeNode(Position pos, Id id);
  
  ModeTypeNode ModeTypeNode(Position pos, Id id);

  ModeTypeNode ModeTypeNode(Position pos);

  ModeValueNode ModeValueNode(Position pos, String name);




}
