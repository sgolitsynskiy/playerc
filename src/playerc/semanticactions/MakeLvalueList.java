/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.semanticactions;

import java.util.Stack;

import playerc.SemanticAction;
import playerc.Token;
import playerc.abstractsyntax.Lvalue;
import playerc.abstractsyntax.LvalueList;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeLvalueList extends SemanticAction {
  private String actionName;

  public MakeLvalueList(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    LvalueList list = new LvalueList(lineNumber());
    while (true)
      if (semanticStack.peek() instanceof Lvalue)
        list.add((Lvalue) semanticStack.pop());
      else
        break;

    semanticStack.push(list);
  }

  public String toString() {
    return actionName;
  }
}