package visitor;

import exceptions.*;
import lexer.Token;
import parser.node.*;

/**
 * Visitor class for XML generator
 */
public class VisitorXMLGenerator implements Visitor {
    //variable to hold indent
    private int indent = 0;

    @Override
    public void visit(ASTActualParams actualParams) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        //get indent
        String indentation = getIndent();
        //increment indent
        indent++;

        //if no expressions
        if(actualParams.getExpressions().size() == 0)
            System.out.println(indentation+"<ActualParams>Empty</ActualParams>");
        else
        {
            System.out.println(indentation+"<ActualParams>");
            //visit every expression
            for(int i=0; i <actualParams.getExpressions().size(); i++)
            {
                actualParams.getExpressions().get(i).accept(this);
            }
            System.out.println(indentation+"</ActualParams>");
        }
        //decrement indent
        indent--;
    }

    @Override
    public void visit(ASTAssignment assignment) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        //get indent
        String indentation = getIndent();
        //increment indent
        indent++;

        //get identifier
        ASTIdentifier identifier = assignment.getIdentifier();
        //get expression
        ASTExpression expression = assignment.getExpression();

        //if the assignment is null (for example in for loop)
        if(identifier == null)
        {
            System.out.println(indentation+"<Assignment>Empty</Assignment>");
        }
        else
        {
            System.out.println(indentation+"<Assignment>");
            //visit identifier
            identifier.accept(this);
            //visit expression
            expression.accept(this);

            System.out.println(indentation+"</Assignment>");
        }
        indent--;

    }

    @Override
    public void visit(ASTBinExpression expression) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        //get indent
        String indentation = getIndent();
        //increment indent
        indent++;
        //get operand
        String operand = expression.getOperand();
        //get left
        ASTExpression left = expression.getLeft();
        //get right
        ASTExpression right = expression.getRight();

        System.out.println(indentation+"<BinaryExpr Op=\""+operand+"\">");
        //visit left
        left.accept(this);
        //visit right
        right.accept(this);
        System.out.println(indentation+"</BinaryExpr>");

        //decrement indent
        indent--;
    }

    @Override
    public void visit(ASTBlock block) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        //get indent
        String indentation = getIndent();
        //increment indent
        indent++;

        //if block has no statements
        if(block.getStatements().size() == 0)
            System.out.println(indentation+"<Block>Empty</Block>");
        else
        {
            System.out.println(indentation+"<Block>");
            //visit each statement in block
            for(int i=0; i <block.getStatements().size(); i++)
            {
                block.getStatements().get(i).accept(this);
            }                    
            System.out.println(indentation+"</Block>");
        }

        //decrement indent
        indent--;

    }

    @Override
    public void visit(ASTBooleanLiteral booleanLiteral) {
        //get indent
        String indentation = getIndent();
        System.out.println(indentation+"<BooleanLiteral>"+booleanLiteral.getValue()+"</BooleanLiteral>");

    }

    @Override
    public void visit(ASTFloatLiteral floatLiteral) {
        //get indent
        String indentation = getIndent();
        System.out.println(indentation+"<FloatLiteral>"+floatLiteral.getValue()+"</FloatLiteral>");

    }

    @Override
    public void visit(ASTFor forNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        //get indent
        String indentation = getIndent();
        //increment indent
        indent++;

        //get variable declaration
        ASTVariableDecl variableDecl = forNode.getVariableDecl();
        //get expression
        ASTExpression expression = forNode.getExpression();
        //get assignment
        ASTAssignment assignment = forNode.getAssignment();
        //get block
        ASTBlock block = forNode.getBlock();

        System.out.println(indentation+"<For>");
        //visit variable declaration
        variableDecl.accept(this);
        //visit expression
        expression.accept(this);
        //visit assignment
        assignment.accept(this);
        //visit block
        block.accept(this);
        System.out.println(indentation+"</For>");
        //decrement indent
        indent--;
    }

    @Override
    public void visit(ASTFormalParam formalParam) throws AlreadyDeclaredException, UndeclaredException {
        //get indent
        String indentation = getIndent();
        //increment indent
        indent++;

        //get identifier
        ASTIdentifier identifier = formalParam.getIdentifier();

        System.out.println(indentation+"<FormalParam>");
        //visit identifier
        identifier.accept(this);
        System.out.println(indentation+"</FormalParam>");

        //decrement indent
        indent--;
    }

    @Override
    public void visit(ASTFormalParams formalParams) throws AlreadyDeclaredException, UndeclaredException {
        //get indent
        String indentation = getIndent();
        //increment indent
        indent++;
        //if there is no params
        if(formalParams.getFormalParams().size() == 0)
            System.out.println(indentation+"<FormalParams>Empty</FormalParams>");
        else
        {
            System.out.println(indentation+"<FormalParams>");
            //visit all params
            for(int i=0; i <formalParams.getFormalParams().size(); i++)
            {
                formalParams.getFormalParams().get(i).accept(this);
            }
            System.out.println(indentation+"</FormalParams>");
        }
        indent--;
    }

    @Override
    public void visit(ASTFunctionCall functionCall) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        //get indent
        String indentation = getIndent();
        //increment indent
        indent++;

        //get identifier
        ASTIdentifier identifier = functionCall.getIdentifier();
        //get params
        ASTActualParams params = functionCall.getParams();
        System.out.println(indentation+"<FunctionCall>");
        //visit identifier
        identifier.accept(this);
        //visit params
        params.accept(this);
        System.out.println(indentation+"</FunctionCall>");

        //decrement indent
        indent--;
    }

    @Override
    public void visit(ASTFunctionDecl functionDecl) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        //get indent
        String indentation = getIndent();
        //increment indent
        indent++;
        
        //get identifier
        ASTIdentifier identifier = functionDecl.getIdentifier();
        //get formal parameters
        ASTFormalParams formalParams = functionDecl.getFormalParams();
        //get block
        ASTBlock block = functionDecl.getBlock();

        System.out.println(indentation+"<FuncDecl>");
        //visit identifier
        identifier.accept(this);
        //visit formal parameters
        formalParams.accept(this);
        //visit block
        block.accept(this);
        System.out.println(indentation+"</FuncDecl>");
        //decrement indent
        indent--;
    }

    @Override
    public void visit(ASTIdentifier identifier) {
        //get indent
        String indentation = getIndent();
        
        //check type, if there is no type, leave it empty
        String type = (identifier.getType() != null) ? " Type=\""+identifier.getType()+"\"" : "";
        System.out.println(indentation+"<Identifier"+type+">"+identifier.getValue()+"</Identifier>");
    }

    @Override
    public void visit(ASTIf ifNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        //get indent
        String indentation = getIndent();
        //increment indent
        indent++;

        //get expression
        ASTExpression expression = ifNode.getExpression();
        //get block
        ASTBlock block = ifNode.getBlock();
        //get else block
        ASTBlock elseBlock = ifNode.getElseBlock();


        System.out.println(indentation+"<If>");
        expression.accept(this);
        block.accept(this);
        elseBlock.accept(this);
        System.out.println(indentation+"</If>");

        //decrement indent
        indent--;
    }

    @Override
    public void visit(ASTIntegerLiteral integerLiteral) {
        //get indent
        String indentation = getIndent();
        System.out.println(indentation+"<IntegerLiteral>"+integerLiteral.getValue()+"</IntegerLiteral>");
    }

    @Override
    public void visit(ASTPrint printNode) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        //get indent
        String indentation = getIndent();
        //increment indent
        indent++;
        
        //get expression
        ASTExpression expression = printNode.getExpression();
        System.out.println(indentation+"<Print>");
        //visit expression
        expression.accept(this);
        System.out.println(indentation+"</Print>");

        //decrement indent
        indent--;
    }

    @Override
    public void visit(ASTProgram programNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {

        //get indent
        String indentation = getIndent();
        //increment indent
        indent++;
        System.out.println(indentation+"<Program>");
        
        //visit every statment
        for(int i=0; i <programNode.getStatements().size(); i++)
        {
            programNode.getStatements().get(i).accept(this);
        }
        System.out.println(indentation+"</Program>");
    }


    @Override
    public void visit(ASTReturn returnNode) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        //get indent
        String indentation = getIndent();
        //increment indent
        indent++;
        
        //get expression
        ASTExpression expression = returnNode.getExpression();
        System.out.println(indentation+"<Return>");
        //visit expression
        expression.accept(this);
        System.out.println(indentation+"</Return>");
        
        //decrement indent
        indent--;
    }

    @Override
    public void visit(ASTUnary unary) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        //get indent
        String indentation = getIndent();
        //increment indent
        indent++;

        //get unary expression
        ASTExpression expression = unary.getExpression();
        System.out.println(indentation+"<Unary Type=\""+unary.getLexeme()+"\">");
        //visit expression
        expression.accept(this);
        System.out.println(indentation+"</Unary>");

        //decrement indent
        indent--;
    }

    @Override
    public void visit(ASTVariableDecl variableDecl) throws AlreadyDeclaredException, IncorrectTypeException, UndeclaredException, ReturnTypeMismatchException {
        //get indent
        String indentation = getIndent();
        //increment indent
        indent++;

        //get identifier
        ASTIdentifier identifier = variableDecl.getIdentifier();
        //get expression
        ASTExpression expression = variableDecl.getExpression();

        //if there is no identifier - in case of for loop with no variable declaration
        if(identifier == null)
        {
            System.out.println(indentation+"<VarDecl>Empty</VarDecl>");
        }
        else
        {
            System.out.println(indentation+"<VarDecl>");
            //visit identifier
            identifier.accept(this);
            //visit expression
            expression.accept(this);
            System.out.println(indentation+"</VarDecl>");
        }
        
        //decrement indentation
        indent--;
    }

    @Override
    public void visit(ASTWhile whileNode) throws IncorrectTypeException, UndeclaredException, AlreadyDeclaredException, ReturnTypeMismatchException {
        //get indent
        String indentation = getIndent();
        //increment indent
        indent++;
        
        //get expression
        ASTExpression expression = whileNode.getExpression();
        //get block
        ASTBlock block = whileNode.getBlock();

        System.out.println(indentation+"<While>");
        //visit expression
        expression.accept(this);
        //visit block
        block.accept(this);
        System.out.println(indentation+"</While>");
        
        //decrement indent
        indent--;
    }

    @Override
    public void visit(ASTExpression astExpression) {}
    @Override
    public void visit(ASTStatement astStatement) {}

    /**
     * Method to get indent
     * @return get indent as a string
     */
    public String getIndent()
    {
        StringBuilder indentation = new StringBuilder("");

        for(int i =0; i < this.indent; i++)
            indentation.append("\t");

        return indentation.toString();
    }

    /**
     * Method to generate xml
     * @param program program node where to start
     * @throws UndeclaredException
     * @throws IncorrectTypeException
     * @throws ReturnTypeMismatchException
     * @throws AlreadyDeclaredException
     */
    public void generate(ASTProgram program) throws UndeclaredException, IncorrectTypeException, ReturnTypeMismatchException, AlreadyDeclaredException {
        program.accept(this);
    }
}
