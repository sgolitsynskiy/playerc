/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.semanticactions;

import java.util.Stack;

import playerc.SemanticAction;
import playerc.Token;
import playerc.abstractsyntax.MemberList;
import playerc.abstractsyntax.RecordType;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeRecordType extends SemanticAction {
  private String actionName;

  public MakeRecordType(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    MemberList members = (MemberList) semanticStack.pop();
    semanticStack.push(new RecordType(members, lineNumber()));
  }

  public String toString() {
    return actionName;
  }
}