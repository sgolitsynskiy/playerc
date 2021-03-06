/*
 * This code is part of a compiler for the Player programming language
 * Created: 2004-2005
 * Revised: 09/2017
 */
package playerc.semanticactions;

import java.util.Stack;

import playerc.SemanticAction;
import playerc.Token;
import playerc.abstractsyntax.ByExpressionOpt;
import playerc.abstractsyntax.Expression;
import playerc.abstractsyntax.ForStatement;
import playerc.abstractsyntax.Identifier;
import playerc.abstractsyntax.IntegerExpression;
import playerc.abstractsyntax.StatementList;

/**
 * @author Sergey Golitsynskiy
 * @version 3.1
 */
public class MakeForStatement extends SemanticAction {
  private String actionName;

  public MakeForStatement(String actionName, int lineNumber) {
    super(lineNumber);
    this.actionName = actionName;
  }

  public void execute(Stack semanticStack, Token lastToken) {
    // statement -> 'for' identifier make-id ':=' expression 'to' expression
    // by-expression-opt
    // 'do' statements-opt make-statement-list 'end' ';' make-for-statement
    StatementList stms = null;
    if (semanticStack.peek() instanceof StatementList)
      stms = (StatementList) semanticStack.pop();

    Expression expBy = new IntegerExpression(1, lineNumber());
    if (semanticStack.peek() instanceof ByExpressionOpt) {
      ByExpressionOpt temp = (ByExpressionOpt) semanticStack.pop();
      expBy = temp.expression();
    }

    Expression expTo = (Expression) semanticStack.pop();

    Expression expFrom = (Expression) semanticStack.pop();

    Identifier id = (Identifier) semanticStack.pop();

    semanticStack.push(new ForStatement(id, expFrom, expTo, expBy, stms, lineNumber()));
  }

  public String toString() {
    return actionName;
  }
}