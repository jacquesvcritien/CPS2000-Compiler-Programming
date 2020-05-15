package antlrSrc;

import lexer.Token;
import lexer.TypeToken;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import parser.node.ASTProgram;
import parser.node.Type;
import parser.node.expression.*;
import parser.node.expression.identifier.ASTIdentifier;
import parser.node.statement.*;
import parser.node.statement.declaration.ASTVariableDecl;

import java.util.ArrayList;
import java.util.List;

public class TransformerVisitor extends SmallLangBaseVisitor<Object> {
    SmallLangLexer lexer;

    public TransformerVisitor(SmallLangLexer lexer)
    {
        this.lexer = lexer;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     * @return
     */
    @Override public ASTExpression visitLiteral(SmallLangParser.LiteralContext ctx) {

        //get type of literal
        TerminalNodeImpl terminalNode = (TerminalNodeImpl) ctx.children.get(0);
        //get actual name from vocabulary
        String type = this.lexer.getVocabulary().getSymbolicName(terminalNode.getSymbol().getType());

        //get type of literal to return
        switch (type)
        {
            case "IntegerLiteral" : return new ASTIntegerLiteral(new Token(TypeToken.INTEGER_LITERAL, terminalNode.toString()));
            case "FloatLiteral" : return new ASTFloatLiteral(new Token(TypeToken.FLOAT_LITERAL, terminalNode.toString()));
            default: return new ASTBooleanLiteral(new Token(TypeToken.BOOLEAN_LITERAL, terminalNode.toString()));
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitActualParams(SmallLangParser.ActualParamsContext ctx) {
        int counter = 0;
        List<ParseTree> trees = ctx.children;

        //arraylist of expressions(params) to return
        ArrayList<ASTExpression> actualParams = new ArrayList<ASTExpression>();

        //get param
        ParseTree expressionNode = ctx.children.get(counter);
        ASTExpression actualParam = (ASTExpression) expressionNode.accept(this);

        //add param to list of params
        actualParams.add(actualParam);

        //increment counter
        counter++;

        while(counter < trees.size())
        {
            //get next node
            TerminalNodeImpl currentNode = (TerminalNodeImpl) ctx.children.get(counter);
            //get the type of the next token
            int type = currentNode.getSymbol().getType();

            //increment counter
            counter++;

            //get another param
            expressionNode = ctx.children.get(counter);
            actualParam = (ASTExpression) expressionNode.accept(this);

            //add param to list of params
            actualParams.add(actualParam);

            //increment counter
            counter++;

            //check size
            if(counter >= trees.size())
                break;

        }

        return new ASTActualParams(actualParams);
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public ASTFunctionCall visitFunctionCall(SmallLangParser.FunctionCallContext ctx) {
        //get identifier
        ParseTree identifierNode = ctx.children.get(0);
        ASTIdentifier identifier = new ASTIdentifier(identifierNode.toString());

        //variable to hold actual params
        ASTActualParams actualParams = null;

        //check if it has actual params, if the third node has children, it must be that there are params
        if(ctx.children.get(2).getChildCount() > 0)
        {
            //get actual params
            actualParams = (ASTActualParams)visitActualParams((SmallLangParser.ActualParamsContext) ctx.children.get(2));
        }
        //else if there are no params
        else
        {
            actualParams = new ASTActualParams();
        }

        return new ASTFunctionCall(identifier, actualParams);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public ASTExpression visitSubExpression(SmallLangParser.SubExpressionContext ctx) {
        //get expression and return it
        ParseTree expressionNode = ctx.children.get(1);
        return (ASTExpression) expressionNode.accept(this);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public ASTUnary visitUnary(SmallLangParser.UnaryContext ctx) {
        //get lexeme
        ParseTree lexeme = ctx.children.get(0);

        //get expression
        ParseTree expressionNode = ctx.children.get(1);
        ASTExpression expression = (ASTExpression)expressionNode.accept(this);

        return new ASTUnary(lexeme.toString(), expression);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public ASTExpression visitFactor(SmallLangParser.FactorContext ctx) {

        //get node
        ParseTree node = ctx.children.get(0);

        ASTExpression toReturn = null;

        //if the node has no children it must be an identifier
        if(node.getChildCount() == 0)
            toReturn = createIdentifier(null, node.toString());
        else
            toReturn = (ASTExpression)visitChildren(ctx);

        return toReturn;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitMultiplicativeOp(SmallLangParser.MultiplicativeOpContext ctx) {
        //return operand
        return ctx.children.get(0).toString();
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitAdditiveOp(SmallLangParser.AdditiveOpContext ctx) {
        //return operand
        return ctx.children.get(0).toString();
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public String visitRelationalOp(SmallLangParser.RelationalOpContext ctx) {
        //return operand
        return ctx.children.get(0).toString();
    }


    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public ASTExpression visitTerm(SmallLangParser.TermContext ctx) {
        List<ParseTree> trees = ctx.children;

        //get expression
        ASTExpression node =visitFactor((SmallLangParser.FactorContext) trees.get(0));


        for(int i = 1; i < trees.size(); i++)
        {
            String operand = visitMultiplicativeOp((SmallLangParser.MultiplicativeOpContext) trees.get(i));
            i++;
            ASTExpression right = (ASTExpression) visitFactor((SmallLangParser.FactorContext) trees.get(i));
            node = new ASTBinExpression(node, operand, right);
        }

        return node;
    }


//    /**
//     * {@inheritDoc}
//     *
//     * <p>The default implementation returns the result of calling
//     * {@link #visitChildren} on {@code ctx}.</p>
//     */
//    @Override public ASTExpression visitTerm(SmallLangParser.TermContext ctx) {
//        int counter = 0;
//        List<ParseTree> trees = ctx.children;
//
//        //get expression
//        ASTExpression node = (ASTExpression) visitFactor((SmallLangParser.FactorContext) trees.get(counter));
//        //increment counter
//        counter++;
//
//        if(counter < trees.size())
//        {
//            //get next node
//            TerminalNodeImpl currentNode = (TerminalNodeImpl) ctx.children.get(counter);
//            //get the type of the next token
//            int type = currentNode.getSymbol().getType();
//
//            //while there is a relational op
//            while (this.lexer.getVocabulary().getSymbolicName(type).equals("MultiplicativeOp")) {
//                //increment counter
//                counter++;
//                //get the expression on the right
//                ASTExpression right = (ASTExpression) visitFactor((SmallLangParser.FactorContext) trees.get(counter));
//                node = new ASTBinExpression(node, currentNode.toString(), right);
//
//                //increment counter
//                counter++;
//
//                //check size
//                if(counter >= trees.size())
//                    break;
//
//                //get expression
//                currentNode = (TerminalNodeImpl) ctx.children.get(counter);
//                //get the type of the next token
//                type = currentNode.getSymbol().getType();
//            }
//        }
//
//
//        return node;
//    }
//    /**
//     * {@inheritDoc}
//     *
//     * <p>The default implementation returns the result of calling
//     * {@link #visitChildren} on {@code ctx}.</p>
//     */
//    @Override public ASTExpression visitSimpleExpression(SmallLangParser.SimpleExpressionContext ctx) {
//        int counter = 0;
//        List<ParseTree> trees = ctx.children;
//
//        //get expression
//        ASTExpression node = (ASTExpression) visitTerm((SmallLangParser.TermContext) trees.get(counter));
//        //increment counter
//        counter++;
//
//        if(counter < trees.size())
//        {
//            //get next node
//            TerminalNodeImpl currentNode = (TerminalNodeImpl) ctx.children.get(counter);
//            //get the type of the next token
//            int type = currentNode.getSymbol().getType();
//
//            //while there is a relational op
//            while (this.lexer.getVocabulary().getSymbolicName(type).equals("AdditiveOp")) {
//                //increment counter
//                counter++;
//                //get the expression on the right
//                ASTExpression right = (ASTExpression) visitTerm((SmallLangParser.TermContext) trees.get(counter));
//                node = new ASTBinExpression(node, currentNode.toString(), right);
//
//                //increment counter
//                counter++;
//
//                //check size
//                if(counter >= trees.size())
//                    break;
//
//                //get expression
//                currentNode = (TerminalNodeImpl) ctx.children.get(counter);
//                //get the type of the next token
//                type = currentNode.getSymbol().getType();
//            }
//        }
//
//
//        return node;
//    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public ASTExpression visitSimpleExpression(SmallLangParser.SimpleExpressionContext ctx) {
        List<ParseTree> trees = ctx.children;

        //get expression
        ASTExpression node = visitTerm((SmallLangParser.TermContext) trees.get(0));


        for(int i = 1; i < trees.size(); i++)
        {
            String operand = visitAdditiveOp((SmallLangParser.AdditiveOpContext) trees.get(i));
            i++;
            ASTExpression right = visitTerm((SmallLangParser.TermContext) trees.get(i));
            node = new ASTBinExpression(node, operand, right);
        }

        return node;
    }

//    /**
//     * {@inheritDoc}
//     *
//     * <p>The default implementation returns the result of calling
//     * {@link #visitChildren} on {@code ctx}.</p>
//     */
//    @Override public ASTExpression visitExpression(SmallLangParser.ExpressionContext ctx) {
//        int counter = 0;
//        List<ParseTree> trees = ctx.children;
//
//        //get expression
//        ASTExpression node = (ASTExpression) visitSimpleExpression((SmallLangParser.SimpleExpressionContext) trees.get(counter));
//        //increment counter
//        counter++;
//
//        if(counter < trees.size())
//        {
//            //get next node
//            TerminalNodeImpl currentNode = (TerminalNodeImpl) ctx.children.get(counter);
//            //get the type of the next token
//            int type = currentNode.getSymbol().getType();
//
//            //while there is a relational op
//            while (this.lexer.getVocabulary().getSymbolicName(type).equals("RelationalOp")) {
//                //increment counter
//                counter++;
//                //get the expression on the right
//                ASTExpression right = (ASTExpression) visitSimpleExpression((SmallLangParser.SimpleExpressionContext) trees.get(counter));
//                node = new ASTBinExpression(node, currentNode.toString(), right);
//
//                //increment counter
//                counter++;
//
//                //check size
//                if(counter >= trees.size())
//                    break;
//
//                //get expression
//                currentNode = (TerminalNodeImpl) ctx.children.get(counter);
//                //get the type of the next token
//                type = currentNode.getSymbol().getType();
//            }
//
//        }
//
//        return node;
//    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public ASTExpression visitExpression(SmallLangParser.ExpressionContext ctx) {
        List<ParseTree> trees = ctx.children;

        //get expression
        ASTExpression node = visitSimpleExpression((SmallLangParser.SimpleExpressionContext) trees.get(0));;


        for(int i = 1; i < trees.size(); i++)
        {
            String operand = visitRelationalOp((SmallLangParser.RelationalOpContext) trees.get(i));
            i++;
            ASTExpression right = visitSimpleExpression((SmallLangParser.SimpleExpressionContext) trees.get(i));;
            node = new ASTBinExpression(node, operand, right);
        }

        return node;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public ASTAssignment visitAssignment(SmallLangParser.AssignmentContext ctx) {
        //get identifier name
        TerminalNodeImpl identifierToken = (TerminalNodeImpl) ctx.children.get(0);
        //get expression
        ParseTree expressionNode = ctx.children.get(2);
        ASTExpression expression = (ASTExpression) expressionNode.accept(this);

        //create identifier
        ASTIdentifier identifier = createIdentifier(null, identifierToken.toString());

        return new ASTAssignment(identifier, expression);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public ASTVariableDecl visitVariableDecl(SmallLangParser.VariableDeclContext ctx) {
        //get identifier name
        TerminalNodeImpl identifierToken = (TerminalNodeImpl) ctx.children.get(1);
        //get identifier's type
        TerminalNodeImpl typeToken = (TerminalNodeImpl) ctx.children.get(3);

        //get expression
        ParseTree expressionNode = ctx.children.get(5);
        ASTExpression expression = (ASTExpression) expressionNode.accept(this);

        //create identifier
        ASTIdentifier identifier = createIdentifier(typeToken.toString(), identifierToken.toString());
        return new ASTVariableDecl(identifier, expression);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public ASTPrint visitPrintStatement(SmallLangParser.PrintStatementContext ctx) {
        //get expression
        ParseTree expressionNode = ctx.children.get(1);
        ASTExpression expression = (ASTExpression) expressionNode.accept(this);

        return new ASTPrint(expression);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public ASTReturn visitRtrnStatement(SmallLangParser.RtrnStatementContext ctx) {
        //get expression
        ParseTree expressionNode = ctx.children.get(1);
        ASTExpression expression = (ASTExpression) expressionNode.accept(this);

        return new ASTReturn(expression);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public ASTIf visitIfStatement(SmallLangParser.IfStatementContext ctx) {
        //get expression
        ParseTree expressionNode = ctx.children.get(2);
        ASTExpression expression = (ASTExpression) expressionNode.accept(this);

        //get block
        ParseTree blockNode = ctx.children.get(4);
        ASTBlock block = (ASTBlock) blockNode.accept(this);

        ASTBlock elseBlock = new ASTBlock();
        //check if there is else, if there are more than 5 means that there is an else block
        if(ctx.children.size() > 5)
        {
            //get else block
            ParseTree elseBlockNode = ctx.children.get(6);
            elseBlock = (ASTBlock) elseBlockNode.accept(this);
        }

        return new ASTIf(expression, block, elseBlock);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitForStatement(SmallLangParser.ForStatementContext ctx) {
        //variable to hold the index of the expression
        int expressionIndex = -1;
        //variable to hold the index of the assignment
        int assignmentIndex = -1;
        //variable to hold the index of the block
        int blockIndex = -1;

        //variable to hold the declaration
        ASTVariableDecl variableDecl = new ASTVariableDecl();
        //variable to hold the expression
        ASTExpression expression = new ASTExpression();
        //variable to hold the assignment
        ASTAssignment assignment = new ASTAssignment();


        //check if there is a declaration
        //if there is a declaration
        if(ctx.children.get(2).getChildCount() > 0)
        {
            expressionIndex = 4;
            assignmentIndex = 6;
            blockIndex = 8;

            //get variable decl
            ParseTree variableDeclarationNode = ctx.children.get(2);
            variableDecl =  (ASTVariableDecl)variableDeclarationNode.accept(this);
        }
        //else if there is no declaration
        else
        {
            expressionIndex = 3;
            assignmentIndex = 5;
            blockIndex = 7;
        }

        //get expression
        ParseTree expressionNode = ctx.children.get(expressionIndex);
        expression =  (ASTExpression)expressionNode.accept(this);

        //check if there is an assignment, and if so get it
        if(ctx.children.get(assignmentIndex).getChildCount() > 0) {
            //get assignment
            ParseTree assignmentNode = ctx.children.get(assignmentIndex);
            assignment =  (ASTAssignment) assignmentNode.accept(this);
        }
        //if there is not, reduce block index
        else
        {
            blockIndex--;
        }

        //get block
        ParseTree blockNode = ctx.children.get(blockIndex);
        ASTBlock block = (ASTBlock) blockNode.accept(this);

        return new ASTFor(variableDecl, expression, assignment, block);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public ASTWhile visitWhileStatement(SmallLangParser.WhileStatementContext ctx) {
        //get expression
        ParseTree expressionNode = ctx.children.get(2);
        ASTExpression expression = (ASTExpression) expressionNode.accept(this);

        //get block
        ParseTree blockNode = ctx.children.get(4);
        ASTBlock block = (ASTBlock) blockNode.accept(this);

        return new ASTWhile(expression, block);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitFormalParam(SmallLangParser.FormalParamContext ctx) {
        //get identifier name
        TerminalNodeImpl identifierToken = (TerminalNodeImpl) ctx.children.get(0);
        //get identifier's type
        TerminalNodeImpl typeToken = (TerminalNodeImpl) ctx.children.get(2);

        ASTIdentifier identifier = createIdentifier(typeToken.toString(), identifierToken.toString());
        return new ASTFormalParam(identifier);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Object visitFormalParams(SmallLangParser.FormalParamsContext ctx) {
        int counter = 0;
        List<ParseTree> trees = ctx.children;

        //arraylist of formal params to return
        ArrayList<ASTFormalParam> formalParams = new ArrayList<ASTFormalParam>();

        //get param
        ParseTree formalParamNode = ctx.children.get(counter);
        ASTFormalParam formalParam = (ASTFormalParam) formalParamNode.accept(this);

        //add param to list of params
        formalParams.add(formalParam);

        //increment counter
        counter++;

        while(counter < trees.size())
        {
            //get next node
            TerminalNodeImpl currentNode = (TerminalNodeImpl) ctx.children.get(counter);
            //get the type of the next token
            int type = currentNode.getSymbol().getType();

            //increment counter
            counter++;

            //get another param
            formalParamNode = ctx.children.get(counter);
            formalParam = (ASTFormalParam) formalParamNode.accept(this);

            //add param to list of params
            formalParams.add(formalParam);

            //increment counter
            counter++;

            //check size
            if(counter >= trees.size())
                break;
        }

        return new ASTFormalParams(formalParams);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public ASTFunctionDecl visitFunctionDecl(SmallLangParser.FunctionDeclContext ctx) {
        //get identifier
        TerminalNodeImpl identifierToken = (TerminalNodeImpl) ctx.children.get(1);

        //this variable holds the index where the type will be
        int typeIndex = -1;
        //this variable holds the index where the block will be
        int blockIndex = -1;

        //check third child node to see if its a ')' of a formal params node
        ParseTree thirdChildNode = ctx.children.get(3);

        //variable to hold formal params
        ASTFormalParams formalParams = new ASTFormalParams(new ArrayList<ASTFormalParam>());

        //if a '('
        if(thirdChildNode.getChildCount() == 0)
        {
            typeIndex = 5;
            blockIndex = 6;
        }
        //if params
        else
        {
            typeIndex = 6;
            blockIndex = 7;
            //get formal params
            formalParams = (ASTFormalParams) thirdChildNode.accept(this);
        }
        //get type
        TerminalNodeImpl typeToken = (TerminalNodeImpl) ctx.children.get(typeIndex);

        //set identifier
        ASTIdentifier identifier = createIdentifier(typeToken.toString(), identifierToken.toString());


        //get block
        ParseTree blockNode = ctx.children.get(blockIndex);
        ASTBlock block = (ASTBlock) blockNode.accept(this);

        return new ASTFunctionDecl(identifier, formalParams, block);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public ASTStatement visitStatement(SmallLangParser.StatementContext ctx) {
        //get actual statement
        ParseTree statement = ctx.children.get(0);
        return (ASTStatement)statement.accept(this);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public ASTBlock visitBlock(SmallLangParser.BlockContext ctx) {
        //holds statements
        ArrayList<ASTStatement> statements = new ArrayList<ASTStatement>();

        //get the statements by not going through the first and last elements since they are used for curly brackets only
        for(int i =1; i < ctx.children.size()-1; i++)
        {
            ParseTree child = ctx.children.get(i);
            ASTStatement statement = (ASTStatement)child.accept(this);
            statements.add(statement);
        }

        return new ASTBlock(statements);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public ASTProgram visitProgram(SmallLangParser.ProgramContext ctx) {
        //holds statements
        ArrayList<ASTStatement> statements = new ArrayList<ASTStatement>();

        for(int i =0; i < ctx.children.size(); i++)
        {
            ParseTree child = ctx.children.get(i);
            ASTStatement statement = (ASTStatement)child.accept(this);
            statements.add(statement);
        }

        return new ASTProgram(statements);

    }

    /**
     * Method to create an identifier node
     * @param type type of identifier
     * @param value value of identifier
     * @return
     */
    public ASTIdentifier createIdentifier(String type, String value)
    {
        //variable to hold actual type
        Type actualType = null;

        //if a type is passed, get actual type enum
        if(type != null)
            actualType = getTypeEnum(type);

        //return identifier
        return new ASTIdentifier(value, actualType);
    }

    /**
     * Method to get enum type from string
     * @param type in string
     * @return type in enum
     */
    public static Type getTypeEnum(String type)
    {
        //set type
        switch(type)
        {
            case "int" : return Type.INT;
            case "float" : return Type.FLOAT;
            case "bool" : return Type.BOOL;
            default : return Type.AUTO;
        }
    }

}
