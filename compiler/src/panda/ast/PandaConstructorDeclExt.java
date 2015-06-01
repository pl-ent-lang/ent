package panda.ast;

import panda.types.PandaContext;
import panda.types.ModeTypeVariable;
import panda.types.PandaTypeSystem;
import panda.types.PandaMethodInstance;

import polyglot.ast.MethodDecl;
import polyglot.ast.Node;
import polyglot.ast.TypeNode;
import polyglot.types.Context;
import polyglot.types.Type;
import polyglot.types.SemanticException;
import polyglot.visit.AmbiguityRemover;
import polyglot.visit.NodeVisitor;
import polyglot.visit.TypeBuilder;
import polyglot.visit.TypeChecker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class PandaConstructorDeclExt extends PandaProcedureDeclExt { 
}
