/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.Chess_FiguresAndFields;
import chess.Chess_Board.Board;
/**
 *
 * @author Lucas
 */
public class FigurePawn extends Chessfigure
{

    public FigurePawn(Field field, String color, Board board)
    {
        super(field, color, board);
    }

private boolean didDoubleMove=false;
private int blackOrWhiteDirection=getColor().equalsIgnoreCase(TYPE_COLORS.WHITE_COLOR)? -1:1;
public boolean getDidDoubleMove()
{
    return didDoubleMove;
}
private boolean isEnPassant(Chessfigure figure)
{
    boolean returnvalue=false;
     if (figure!=null)
     {
         
        if (figure.getFigureType().equalsIgnoreCase("FigurePawn") && isChessFigureHitableByMe(figure))
        {
            FigurePawn temppawn=(FigurePawn)figure;
            /*System.out.println(figure.getFigureType());
            System.out.println(getAllMoveCount());
            System.out.println(temppawn.getFigureLastMove());
            System.out.println(temppawn.getFigureMoveCount());
            System.out.println(temppawn.getDidDoubleMove());*/
            
            if (temppawn.getFigureLastMove()==getAllMoveCount() && temppawn.getFigureMoveCount()==1 && temppawn.getDidDoubleMove())
            {
                returnvalue=true;
            }
            
        }
     }
     return returnvalue;
}








   
int maxMoveCount=1;
public boolean canFigureMakeThisMove(MoveInformations moveinformation)
{
    
   boolean returnvalue=false;
   maxMoveCount=1;
   
   
 
   
   if (getFigureMoveCount()==0)
   {
       maxMoveCount=2;
   }
   if (moveinformation.TYPE==TYPE_MOVES.DIAGONAL_MOVE)
   {
       maxMoveCount=1;
   }
   
   
   //System.out.println(maxMoveCount);
   // System.out.println(moveinformation.COUNT);
 
    /*System.out.println(moveinformation.Y);*/
    //System.out.println(moveinformation.TYPE);
   if (moveinformation.COUNT<=maxMoveCount && moveinformation.Y==blackOrWhiteDirection)
   {
   
        if (moveinformation.TYPE==TYPE_MOVES.DIAGONAL_MOVE && maxMoveCount==1)
        {
             Chessfigure tempfigure = getChessFigureByRelativeCoordinates(moveinformation, 1);
             
             moveinformation.TYPE=TYPE_MOVES.STRAIGHT_MOVE;
             moveinformation.Y=0;
             Chessfigure tempfigure2 = getChessFigureByRelativeCoordinates(moveinformation, 1);           
             boolean enPassant=isEnPassant(tempfigure2);
             moveinformation.TYPE=TYPE_MOVES.DIAGONAL_MOVE;
             moveinformation.Y=blackOrWhiteDirection;
             if (tempfigure!=null || enPassant)
                {
                   returnvalue=true; 
                }
        }
        else if (moveinformation.TYPE==TYPE_MOVES.STRAIGHT_MOVE)
        {
           
            for (int i =1; i<=moveinformation.COUNT; i++)
            {
                Chessfigure tempfigure = getChessFigureByRelativeCoordinates(moveinformation, i);           
                if (tempfigure!=null)
                {
                   returnvalue=false; 
                   break;
                }
                else
                {   
                    returnvalue=true;
                }
             }
            didDoubleMove=(returnvalue && maxMoveCount==2)? true:didDoubleMove;
        }
   }
   
   //if (moveinformation.COUNT<=maxMoveCount && moveinformation.Y==1 moveinformation.TYPE==TYPE_MOV)
       
    
    return returnvalue;
}

public void actionsAfterMove(Field oldField)
{
    MoveInformations moveinformation=getMoveInformation(oldField);
    if (moveinformation.TYPE==TYPE_MOVES.DIAGONAL_MOVE)
    {
       //System.out.println("y");
       moveinformation.TYPE=TYPE_MOVES.STRAIGHT_MOVE;
       moveinformation.X=0;
       moveinformation.Y=blackOrWhiteDirection*-1;
       Chessfigure tempfigure=getChessFigureByRelativeCoordinates(moveinformation,1);
       //System.out.println(tempfigure.getFigureType());
       if (isEnPassant(tempfigure))
       {
           //System.out.println("z");
           tempfigure.getChessField().setFigure(null);
       }
           
    }
}
    
    
}